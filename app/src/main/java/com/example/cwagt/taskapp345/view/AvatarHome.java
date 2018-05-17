package com.example.cwagt.taskapp345.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.object.Avatar;

public class AvatarHome extends AppCompatActivity {
	ImageView base, leftArm, rightArm, leftLeg, rightLeg;
	Avatar avatar = new Avatar();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_home);

		base = findViewById(R.id.base);
		leftArm = findViewById(R.id.left_arm);
		rightArm = findViewById(R.id.right_arm);
		leftLeg = findViewById(R.id.left_leg);
		rightLeg = findViewById(R.id.right_leg);

		leftArm.setRotation(avatar.getLeftArmRotation());
		rightArm.setRotation(avatar.getRightArmRotation());
		leftLeg.setRotation(avatar.getLeftLegRotation());
		rightLeg.setRotation(avatar.getRightLegRotation());
	}
}
