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
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.object.Avatar;
import com.example.cwagt.taskapp345.view.AvatarHome;
import com.example.cwagt.taskapp345.view.MainActivity;

public class Avatar {

	private float leftArmRotation;
	private float rightArmRotation;
	private float leftLegRotation;
	private float rightLegRotation;


	ImageView base, leftArm, rightArm, leftLeg, rightLeg;
	List<ImageView> body = new ArrayList<>();




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

	public View avatarCreation(View view) {

		base = view.findViewById(R.id.base);
		leftArm = view.findViewById(R.id.left_arm);
		rightArm = view.findViewById(R.id.right_arm);
		leftLeg = view.findViewById(R.id.left_leg);
		rightLeg = view.findViewById(R.id.right_leg);

		body.add(base);
		body.add(leftArm);
		body.add(rightArm);
		body.add(leftLeg);
		body.add(rightLeg);

		leftArm.setRotation(getRightArmRotation());
		rightArm.setRotation(getRightArmRotation());
		leftLeg.setRotation(getLeftLegRotation());
		rightLeg.setRotation(getRightLegRotation());



		return view;
	}





}
