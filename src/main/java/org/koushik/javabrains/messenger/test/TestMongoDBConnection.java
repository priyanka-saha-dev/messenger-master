package org.koushik.javabrains.messenger.test;

import java.util.List;

import org.koushik.javabrains.messenger.model.Users;
import org.koushik.javabrains.messenger.util.MongoDBConnection;

public class TestMongoDBConnection {

	public static void main(String[] args) {
		MongoDBConnection mongoDB = new MongoDBConnection("userDetails","users");
		final List<Users> userNames = mongoDB.getAllUserNames();
		
		System.out.println(userNames);
	}

}
