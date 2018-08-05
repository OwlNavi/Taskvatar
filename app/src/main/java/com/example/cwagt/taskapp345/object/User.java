package com.example.cwagt.taskapp345.object;

/**
 * This is the class representation of Users
 */
public class User {
	//The chosen username for the user
	private String userName;
	//The unique identifier for the user
	private int userID;
	//A description for the user, currently their full name
	private String userDescription;
	//Each user has their own avatar
	private Avatar avatar;

	/**
	 * Constructor for Users
	 * @param userName the username of the User
	 * @param userID the User's unique identifier
	 * @param userDescription the user's fullname
	 */
	public User(String userName, int userID, String userDescription) {
		this.userName = userName;
		this.userID = userID;
		this.userDescription = userDescription;

		//make avatar from db info from the userID
		//avatar = new Avatar();
	}

	/**
	 * Returns the users unique identifier ID
	 * @return the user's ID
	 */
	public int getUserID() { return userID;	}

	public String getUserDescription() {
		return userDescription;
	}

	public String getUserName() {
		return userName;
	}

}
