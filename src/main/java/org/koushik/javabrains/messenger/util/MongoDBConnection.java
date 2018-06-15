package org.koushik.javabrains.messenger.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.koushik.javabrains.messenger.model.Users;

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

	public MongoDBConnection(String databaseName, String collName) {

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

	public List<Users> getAllUserNames() {
		final List<Users> userNames = new ArrayList<>();

		DBCursor cursor = getUserCollection().find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			userNames.add((Users) AppUtils.fromDBObject(dbObject, Users.class));
		}

		return userNames;

	}

}
