package org.koushik.javabrains.messenger.util;

import java.io.IOException;

import org.koushik.javabrains.messenger.model.Users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class AppUtils {

	public static DBObject toDBObject(Object pojo) {
		String json = new Gson().toJson(pojo);
		return (DBObject) JSON.parse(json);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object fromDBObject(DBObject dbObj, Class clazz) {
		String json = dbObj.toString();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		return gson.fromJson(json, clazz);
	}
	

}
