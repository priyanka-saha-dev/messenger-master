package org.koushik.javabrains.messenger.model;

import java.util.Date;

import org.bson.types.ObjectId;

public class Comment {

	private ObjectId _id;
	private String message;
	private Date created;
	private String author;

	public Comment() {

	}

	public Comment(String id, String message, String author) {
		this._id = new ObjectId(id);
		this.message = message;
		this.author = author;
		this.created = new Date();
	}

	public String getId() {
		return _id.toString();
	}

	public void setId(String id) {
		this._id = new ObjectId(id);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
