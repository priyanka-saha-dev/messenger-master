package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.exception.DataNotFoundException;
import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.util.AppUtils;
import org.koushik.javabrains.messenger.util.MongoDBConnection;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class MessageService {

	private Map<String, Message> messages = DatabaseClass.getMessages();
	MongoDBConnection mongo;

	public MessageService() {
		this.mongo = new MongoDBConnection("messenger", "messages");
	}

	public List<Message> getAllMessages() {

		List<Message> msgs = new ArrayList<>();

		DBCursor cursor = mongo.getUserCollection().find();

		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			msgs.add((Message) AppUtils.fromDBObject(dbObject, Message.class));
		}

		return msgs;
	}

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = getAllMessages();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}

	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> list = getAllMessages();
		if (start + size > list.size())
			return new ArrayList<>();
		return list.subList(start, start + size);
	}

	public Message getMessage(String id) {

		List<Message> msgs = getAllMessages();
		Message message = null;

		for (Message msg : msgs) {
			if (id.equals(msg.getId())) {
				message = msg;
				break;
			}
		}

		if (message == null) {
			throw new DataNotFoundException("Message with id " + id
					+ " not found");
		}
		return message;
	}

	public Message addMessage(Message message) {

		WriteResult result = mongo.getUserCollection().insert(
				AppUtils.toDBObject(message));
		System.out.println("number of rows updated for id : "
				+ result.getField("_id"));
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() == null) {
			return null;
		}

		DBObject query = new BasicDBObject("_id", message.getId());
		WriteResult result = mongo.getUserCollection().update(query,
				AppUtils.toDBObject(message));

		System.out.println("number of rows updated : " + result.getN());
		return message;
	}

	public Message removeMessage(String id) {
		return messages.remove(id);
	}

}
