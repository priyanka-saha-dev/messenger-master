package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;

import org.koushik.javabrains.messenger.exception.DataNotFoundException;
import org.koushik.javabrains.messenger.model.Profile;
import org.koushik.javabrains.messenger.util.AppUtils;
import org.koushik.javabrains.messenger.util.MongoDBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class ProfileService {

	MongoDBConnection mongo;
	private static final Logger logger = LoggerFactory.getLogger(ProfileService.class);
	
	public ProfileService() {
		this.mongo = new MongoDBConnection("messages", "profiles");
	}
	
	public List<Profile> getAllProfiles() {
		
		List<Profile> profiles = new ArrayList<>();
		
		DBCursor cursor = mongo.getDBCollection().find();

		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			profiles.add((Profile) AppUtils.fromDBObject(dbObject, Profile.class));
		}		
		return profiles; 
	}
	
	public Profile getProfile(String profileName) {
		
		Profile profile = null;
		List<Profile> allProfiles = getAllProfiles();
		
		for (Profile profileMember : allProfiles) {
			if(profileName.equals(profileMember.getProfileName())){
				profile = profileMember;
				break;
			}
		}
		
		if (profile == null) {
			throw new DataNotFoundException("Profile with name " + profileName
					+ " not found");
		}
		
		return profile;
	}
	
	public Profile addProfile(Profile profile) {
		
		WriteResult result = mongo.getDBCollection().insert(AppUtils.toDBObject(profile));
		logger.debug("New doc added for id : " + result.getField("_id"));
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if (profile.getId() == null) {
			return null;
		}

		DBObject query = new BasicDBObject("_id", profile.getId());
		WriteResult result = mongo.getDBCollection().update(query,
				AppUtils.toDBObject(profile));

		logger.debug("number of rows updated : " + result.getN());
		return profile;
	}
	
	public void removeProfile(String profileName) {
		DBObject query = new BasicDBObject("profileName", profileName);
		WriteResult result = mongo.getDBCollection().remove(query);

		logger.debug("number of rows deleted : " + result.getN());
	}
}
