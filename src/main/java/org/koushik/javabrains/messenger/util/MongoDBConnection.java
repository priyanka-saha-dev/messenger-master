package org.koushik.javabrains.messenger.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBConnection {

	private MongoClient mongoClient;
	private String dbName;
	private String collectionName;
	private DBCollection userCollection;

	public MongoDBConnection(String collName, String databaseName) {

		try {
			mongoClient = new MongoClient("localhost", 27017);

			this.dbName = databaseName;
			this.collectionName = collName;

			init();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	public void init() {
		DB userDatabase = mongoClient.getDB(getDbName());
		userCollection = userDatabase.getCollection(getCollectionName());
	}

	public String getDbName() {
		return dbName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public DBCollection getUserCollection() {
		return userCollection;
	}

	public List<String> getAllUserNames() {
		final List<String> userNames = new ArrayList<>();

		DBCursor cursor = getUserCollection().find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			userNames.add(fromDBObject(dbObject));
		}

		return userNames;

	}

	private String fromDBObject(DBObject dbObject) {
		return dbObject.toString();
	}

}
