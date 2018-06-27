package org.koushik.javabrains.messenger.util;

import java.io.IOException;

import org.koushik.javabrains.messenger.model.Users;
import org.koushik.javabrains.messenger.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class AppUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(AppUtils.class);

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
	
	public static void logTraces(WriteResult result, String activity, String originName) {
		logger.debug("number of rows " + activity + "from " + originName + " : " + result.getN());
	}

}
