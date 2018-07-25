package com.example.cwagt.taskapp345.object;

public class User {

	private String userName;
	private int userID;
	private String userDescription;
	private Avatar avatar;

	public User(String userName, int userID, String userDescription) {
		//get from database
		this.userName = userName;
		this.userID = userID;
		this.userDescription = userDescription;

		//make avatar from db info
		//avatar = new Avatar();
	}

	public int getUserID() {

		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User{" +
				"userName='" + userName + '\'' +
				'}';
	}

}
