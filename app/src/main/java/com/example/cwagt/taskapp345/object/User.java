package com.example.cwagt.taskapp345.object;

import java.util.HashMap;

/**
 * This is the class representation of Users
 */
public class User {
	//The chosen username for the user
	private String userName;
	//The unique identifier for the user
	private Long userID;
	//A description for the user, currently their full name
	private String userDescription;
	//Each user has their own avatar
	private Avatar avatar;
	//List of body parts the user wants for their avatar
	private HashMap<String, Integer> bodyParts;
	/**
	 * Constructor for Users
	 * @param userID the User's unique identifier
	 * @param userName the username of the User
	 * @param userDescription the user's fullname
	 */
	public User(Long userID, String userName, String userDescription) {
		this.userID = userID;
		this.userName = userName;
		this.userDescription = userDescription;

		//make avatar from db info from the userID
		//avatar = new Avatar();
	}

	public User(String userName, String userDescription) {
		this.userName = userName;
		this.userDescription = userDescription;
	}

	/**
	 * Returns the users unique identifier ID
	 * @return the user's ID
	 */
	public Long getUserID() { return userID;	}

	public String getUserDescription() {
		return userDescription;
	}

	public String getUserName() {
		return userName;
	}

	public HashMap<String, Integer> getBodyParts(){
		return bodyParts;
	}

}
