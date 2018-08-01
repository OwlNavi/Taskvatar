/**
* Creates an avatar with a given rotation for each of the components.
* Each avatar consists of two arms, two legs and a base. 
* Rotations are stored in the database.
* Auothers: Josh April, Shaun Henderson, Craig Thomas
*/
package com.example.cwagt.taskapp345.object;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.object.Avatar;
import com.example.cwagt.taskapp345.view.AvatarHome;
import com.example.cwagt.taskapp345.view.MainActivity;

public class Avatar {

	private int avatarID;
	private float leftArmRotation, rightArmRotation, leftLegRotation, rightLegRotation;
	private User user;
	private String base, leftArm, rightArm, leftLeg, rightLeg;

	List<ImageView> body = new ArrayList<>();

	public Avatar(){
		this.avatarID = 0;

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

	public Avatar(Integer avatarID, String base, String leftArm, String rightArm, String leftLeg, String rightLeg, User user, float leftArmRotation, float rightArmRotation, float leftLegRotation, float rightLegRotation) {
		this.avatarID = avatarID;

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

	public int getAvatarID() {
		return avatarID;
	}

	public void setAvatarID(int avatarID) {
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

	public int getUserID(){
		return user.getUserID();
	}

	public int getID() {
		return avatarID;
	}
}
