package com.example.cwagt.taskapp345.object;

import android.util.Log;
import com.example.cwagt.taskapp345.R;

import java.util.HashMap;

/**
 * Class representation of the avatar
 * Creates an avatar with a given rotation for each of the components.
 * Each avatar consists of two arms, two legs and a base.
 * Rotations are stored in the database.
 * Authors: Josh April, Shaun Henderson, Craig Thomas
 */
public class Avatar {
	//Stores the rotation amount of each limb
	private float leftArmRotation, rightArmRotation, leftLegRotation, rightLegRotation;

	//filename for each limb's image
	private HashMap<String, Integer> bodyParts;

	/**
	 * Avatar default constructor
	 */
	public Avatar(){
		HashMap<String, Integer> bodyPartsNew = new HashMap<>();
		bodyPartsNew.put("base", R.drawable.base);
		bodyPartsNew.put("hat", R.id.hat);
		bodyPartsNew.put("leftArm", R.id.left_arm);
		bodyPartsNew.put("rightArm", R.id.right_arm);
		bodyPartsNew.put("leftLeg", R.id.left_leg);
		bodyPartsNew.put("rightLeg", R.id.right_leg);
		bodyPartsNew.put("background", R.id.avatar_container);
		this.bodyParts = bodyPartsNew;

		this.leftArmRotation = 0;
		this.rightArmRotation = 0;
		this.leftLegRotation = 0;
		this.rightLegRotation = 0;
	}

	/**
	 * Convenience constructer takes a list of body part filenames
	 * @param bodyPartsNew the list of bodyparts
	 */
	public Avatar(HashMap<String, Integer> bodyPartsNew){
		if(bodyPartsNew.size() < 5){
			Log.d("Avatar", "Avatar needs at least 5 bodyParts");
			throw new IndexOutOfBoundsException();
		}
		bodyParts = bodyPartsNew;

		this.leftArmRotation = 0;
		this.rightArmRotation = 0;
		this.leftLegRotation = 0;
		this.rightLegRotation = 0;
	}

	/**
	 * Avatar full constructor
	 */
	public Avatar(HashMap<String, Integer> bodyPartsNew, float leftArmRotation, float rightArmRotation, float leftLegRotation, float rightLegRotation) {
		if(bodyPartsNew.size() < 5){
			Log.d("Avatar", "Avatar needs at least 5 bodyParts");
			throw new IndexOutOfBoundsException();
		}
		this.bodyParts = bodyPartsNew;

		this.leftArmRotation = leftArmRotation;
		this.rightArmRotation = rightArmRotation;
		this.leftLegRotation = leftLegRotation;
		this.rightLegRotation = rightLegRotation;
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

	public HashMap<String, Integer> getBodyParts() {
		return bodyParts;
	}

	@Override
	public String toString() {
		return "Avatar{" +
				"leftArmRotation=" + leftArmRotation +
				", rightArmRotation=" + rightArmRotation +
				", leftLegRotation=" + leftLegRotation +
				", rightLegRotation=" + rightLegRotation +
				", bodyParts=" + bodyParts +
		'}';
	}

	public void setBodyParts(HashMap<String, Integer> bodyParts) {
		this.bodyParts = bodyParts;
	}
}
