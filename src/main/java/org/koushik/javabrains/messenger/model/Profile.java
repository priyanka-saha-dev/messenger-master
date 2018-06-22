package org.koushik.javabrains.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
@XmlRootElement
public class Profile {

	private ObjectId _id;
    private String profileName;
    private String firstName;
    private String lastName;
    private Date created;
    
    public Profile() {
    	
    }
    
	public Profile(String id, String profileName, String firstName, String lastName) {
		this._id = new ObjectId(id);
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		return _id.toString();
	}
	public void setId(String id) {
		this._id = new ObjectId(id);
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
    
    
}
