package com.example.cwagt.taskapp345.object;

/**
 * Class representation of the avatar
 * Creates an avatar with a given rotation for each of the components.
 * Each avatar consists of two arms, two legs and a base.
 * Rotations are stored in the database.
 * Authors: Josh April, Shaun Henderson, Craig Thomas
 */

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.example.cwagt.taskapp345.R;

public class Avatar {
	//Avatar unique identifier
	private Long avatarID;
	//Reference to the user that owns this avatar
	private User user;
	//Stores the rotation amount of each limb
	private float leftArmRotation, rightArmRotation, leftLegRotation, rightLegRotation;
	//filename for each limb's image
	private String base, leftArm, rightArm, leftLeg, rightLeg;
	//A list to store the body parts
	private List<ImageView> body = new ArrayList<>();

	private ImageView la;

	/**
	 * Avatar default constructor
	 */
	public Avatar(){
		this.base = "base_red";
		this.leftArm = "left_arm";
		this.rightArm = "right_arm";
		this.leftLeg = "left_leg";
		this.rightLeg = "right_leg";

		//this.user = ??

		this.leftArmRotation = 0;
		this.rightArmRotation = 0;
		this.leftLegRotation = 0;
		this.rightLegRotation = 0;
	}

	/**
	 * Convenience constructer takes a list of body part filenames
	 * @param bodyParts
	 */
	public Avatar(ArrayList<String> bodyParts){
		if(bodyParts.size() < 5){
			Log.d("Avatar", "Avatar needs at least 5 bodyParts");
			throw new IndexOutOfBoundsException();
		}
		this.base = bodyParts.get(0);
		this.leftArm = bodyParts.get(1);;
		this.rightArm = bodyParts.get(2);;
		this.leftLeg = bodyParts.get(3);;
		this.rightLeg = bodyParts.get(4);

		this.leftArmRotation = 0;
		this.rightArmRotation = 0;
		this.leftLegRotation = 0;
		this.rightLegRotation = 0;
	}

	/**
	 * Avatar full constructor
	 */
	public Avatar(String base, String leftArm, String rightArm, String leftLeg, String rightLeg, User user, float leftArmRotation, float rightArmRotation, float leftLegRotation, float rightLegRotation) {
		this.base = base;
		this.leftArm = leftArm;
		this.rightArm = rightArm;
		this.leftLeg = leftLeg;
		this.rightLeg = rightLeg;

		this.user = user;

		this.leftArmRotation = leftArmRotation;
		this.rightArmRotation = rightArmRotation;
		this.leftLegRotation = leftLegRotation;
		this.rightLegRotation = rightLegRotation;
	}

	public Long getAvatarID() {
		return avatarID;
	}

	public void setAvatarID(long avatarID) {
		this.avatarID = avatarID;
	}

	public float getLeftArmRotation() {
		return leftArmRotation;
	}

	public void setLeftArmRotation(float leftArmRotation) {
		this.leftArmRotation = leftArmRotation;
	}

	public float getRightArmRotation() {
		return rightArmRotation;
	}

	public void setRightArmRotation(float rightArmRotation) {
		this.rightArmRotation = rightArmRotation;
	}

	public float getLeftLegRotation() {
		return leftLegRotation;
	}

	public void setLeftLegRotation(float leftLegRotation) {
		this.leftLegRotation = leftLegRotation;
	}

	public float getRightLegRotation() {
		return rightLegRotation;
	}

	public void setRightLegRotation(float rightLegRotation) {
		this.rightLegRotation = rightLegRotation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getLeftArm() {
		return leftArm;
	}

	public void setLeftArm(String leftArm) {
		this.leftArm = leftArm;
	}

	public String getRightArm() {
		return rightArm;
	}

	public void setRightArm(String rightArm) {
		this.rightArm = rightArm;
	}

	public String getLeftLeg() {
		return leftLeg;
	}

	public void setLeftLeg(String leftLeg) {
		this.leftLeg = leftLeg;
	}

	public String getRightLeg() {
		return rightLeg;
	}

	public void setRightLeg(String rightLeg) {
		this.rightLeg = rightLeg;
	}

	public List<ImageView> getBody() {
		return body;
	}

	public void setBody(List<ImageView> body) {
		this.body = body;
	}

	public Long getUserID(){
		return user.getUserID();
	}

	public Long getID() {
		return avatarID;
	}

	public ArrayList<String> bodyParts(){
		ArrayList<String> result = new ArrayList<>();

		result.add(base);
		result.add(leftArm);
		result.add(rightArm);
		result.add(leftLeg);
		result.add(rightLeg);

		return result;
	}
}
