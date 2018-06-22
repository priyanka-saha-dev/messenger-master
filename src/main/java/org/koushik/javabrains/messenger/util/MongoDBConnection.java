package org.koushik.javabrains.messenger.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.koushik.javabrains.messenger.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class MongoDBConnection {

	private MongoClient mongoClient;
	private String dbName;
	private String collectionName;
	private DBCollection dbCollection;
	private static final Logger logger = LoggerFactory.getLogger(MongoDBConnection.class);

	public MongoDBConnection(String databaseName, String collName) {

		try {
			mongoClient = new MongoClient("localhost", 27017);

			this.dbName = databaseName;
			this.collectionName = collName;

			init();

		} catch (UnknownHostException e) {
			logger.error(e.getMessage());
		}

	}

	public void init() {
		DB db = mongoClient.getDB(getDbName());
		dbCollection = db.getCollection(getCollectionName());
	}

	public String getDbName() {
		return dbName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public DBCollection getDBCollection() {
		return dbCollection;
	}

	public List<Users> getAllUserNames() {
		final List<Users> userNames = new ArrayList<>();

		DBCursor cursor = getDBCollection().find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			userNames.add((Users) AppUtils.fromDBObject(dbObject, Users.class));
		}

		return userNames;

	}

}
