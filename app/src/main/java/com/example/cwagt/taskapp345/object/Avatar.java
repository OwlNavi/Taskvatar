/**
* Creates an avatar with a given rotation for each of the components.
* Each avatar consists of two arms, two legs and a base. 
* Rotations are stored in the database.
* Auothers: Josh April, Shaun Henderson, Craig Thomas
*/
package com.example.cwagt.taskapp345.object;

public class Avatar {

	private float leftArmRotation;
	private float rightArmRotation;
	private float leftLegRotation;
	private float rightLegRotation;

	public Avatar(){
		this.leftArmRotation = 0;
		this.rightArmRotation = 0;
		this.leftLegRotation = 0;
		this.rightLegRotation = 0;
	}

	public Avatar(float leftArmRotation, float rightArmRotation, float leftLegRotation, float rightLegRotation) {
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



}
