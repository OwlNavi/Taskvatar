package com.example.cwagt.taskapp345.object;

/**
 * This is the class representation of Users
 * Each User also contains an Avatar
 */
public class User {
	//The unique identifier for the user
	private Long userID;
	//The chosen username for the user
	private String userName;
	//A description for the user, currently their full name
	private String userDescription;
	//Each user has their own avatar
	private Avatar avatar;

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

		this.avatar = new Avatar();
	}

	public User(Long userID, String userName, String userDescription, Avatar avatar) {
		this.userID = userID;
		this.userName = userName;
		this.userDescription = userDescription;

		this.avatar = avatar;
	}

	public User(String userName, String userDescription) {
		this.userName = userName;
		this.userDescription = userDescription;

		this.avatar = new Avatar();
	}

	public User(String userName, String userDescription, Avatar avatar) {
		this.userName = userName;
		this.userDescription = userDescription;

		this.avatar = avatar;
	}

	public Long getUserID() { return userID;	}

	public String getUserDescription() {
		return userDescription;
	}

	public String getUserName() {
		return userName;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
}
