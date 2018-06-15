package org.koushik.javabrains.messenger.model;

import java.util.Date;

import org.bson.types.ObjectId;

public class Users {
	
	private ObjectId _id;
	private int userID;
	private String userName;
	private String contact;
	private Date dob;
	private String company;
	private String post;
	private Date doj;
	
	public Users(String _id, int userID, String userName, String contact, Date dob,
			String company, String post, Date doj) {
		super();
		
		this._id = new ObjectId(_id);
		this.userID = userID;
		this.userName = userName;
		this.contact = contact;
		this.dob = dob;
		this.company = company;
		this.post = post;
		this.doj = doj;
	}
	public String getId() {
		return _id.toString();
	}

	public int getUserID() {
		return userID;
	}

	public String getUserName() {
		return userName;
	}

	public String getContact() {
		return contact;
	}

	public Date getDob() {
		return dob;
	}

	public String getCompany() {
		return company;
	}

	public String getPost() {
		return post;
	}

	public Date getDoj() {
		return doj;
	}

	@Override
	public String toString() {
		return "Users [_id=" + _id + ", userID=" + userID + ", userName="
				+ userName + ", contact=" + contact + ", dob=" + dob
				+ ", company=" + company + ", post=" + post + ", doj=" + doj
				+ "]";
	}
	
}
