package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.koushik.javabrains.messenger.model.Comment;
import org.koushik.javabrains.messenger.model.ErrorMessage;
import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.util.AppUtils;
import org.koushik.javabrains.messenger.util.MongoDBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class CommentService {
	
	MongoDBConnection mongo;
	
	Message message;
	private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
	
	public CommentService(String messageId) {
		this.mongo = new MongoDBConnection("messages", "comments");
		
		MessageService messageService = new MessageService();
		message = messageService.getMessage(messageId);
	}
	
	public List<Comment> getAllComments() {		
		Map<Long, Comment> comments = message.getComments();
		return new ArrayList<>(comments.values());
	}
	
	public Comment getComment(String commentId) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "http://javabrains.koushik.org");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		
		if (message == null) {
			throw new WebApplicationException(response);
		}
		
		Map<Long, Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new NotFoundException(response);
		}
		return comment;
	}
	
	public Comment addComment(Comment comment) {
		
		WriteResult result = mongo.getDBCollection().insert(
				AppUtils.toDBObject(comment));
		logger.debug("New doc added for id : " + result.getField("_id"));
		
		return comment;
	}
	
	public Comment updateComment(Comment comment) {
		
		if (comment.getId() == null) {
			return null;
		}

		DBObject query = new BasicDBObject("_id", comment.getId());
		WriteResult result = mongo.getDBCollection().update(query, AppUtils.toDBObject(comment));

		logger.debug("number of rows updated : " + result.getN());
		
		return comment;
	}
	
	public void removeComment(String commentId) {
		
		DBObject query = new BasicDBObject("_id", commentId);
		WriteResult result = mongo.getDBCollection().remove(query);

		logger.debug("number of rows deleted : " + result.getN());
	}
		
}
