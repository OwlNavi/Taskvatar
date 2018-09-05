package com.example.cwagt.taskapp345.object;

/**
 * This is the class representation of Users
 * Each User also contains an Avatar
 */
public class User {

	//The unique identifier for the user
	private Long _id;
	//The chosen username for the user
	private String userName;
	//A description for the user, currently their full name
	private String userDescription;
	//Point accumulation
	private Integer points;
	//Each user has their own avatar (1:1 map)
	private Avatar avatar;

	/**
	 * Constructor for Users
	 * @param userName the username of the User
	 * @param userDescription the user's fullname
	 */
	public User(String userName, String userDescription) {
		this._id = null;
		this.userName = userName;
		this.userDescription = userDescription;
		this.points = 0;
		this.avatar = new Avatar();
	}

	public User(String userName, String userDescription, Avatar avatar) {
		this._id = null;
		this.userName = userName;
		this.userDescription = userDescription;
		this.points = 0;
		this.avatar = avatar;
	}

	public User(String userName, String userDescription, Integer points, Avatar avatar) {
		this._id = null;
		this.userName = userName;
		this.userDescription = userDescription;
		this.points = points;
		this.avatar = avatar;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public String getUserName() {
		return userName;
	}

	public Integer getPoints() {
		return points;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "User{" +
				"_id=" + _id +
				", userName='" + userName + '\'' +
				", userDescription='" + userDescription + '\'' +
				", points='" + points + '\'' +
				", avatar=" + avatar +
		'}';
	}
}
