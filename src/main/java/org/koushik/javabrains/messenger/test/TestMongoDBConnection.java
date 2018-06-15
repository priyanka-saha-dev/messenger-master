package org.koushik.javabrains.messenger.test;

import java.util.List;

import org.koushik.javabrains.messenger.util.MongoDBConnection;

public class TestMongoDBConnection {

	public static void main(String[] args) {
		MongoDBConnection mongoDB = new MongoDBConnection();
		final List<String> userNames = mongoDB.getAllUserNames();
		
		System.out.println(userNames);
	}

}
