package com.example.cwagt.taskapp345.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.object.Avatar;

import java.util.ArrayList;
import java.util.List;

public class AvatarHome extends AppCompatActivity {
	ImageView base, leftArm, rightArm, leftLeg, rightLeg;
	List<ImageView> body = new ArrayList<>();
	Avatar avatar = new Avatar();

    final int DELAY = 1; //how fast the avatar should move

	private boolean moveAvatar = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_home);

		base = findViewById(R.id.base);
		leftArm = findViewById(R.id.left_arm);
		rightArm = findViewById(R.id.right_arm);
		leftLeg = findViewById(R.id.left_leg);
		rightLeg = findViewById(R.id.right_leg);

		//build-a-body
        body.add(base);
        body.add(leftArm);
        body.add(rightArm);
        body.add(leftLeg);
        body.add(rightLeg);

		leftArm.setRotation(45);

		rightArm.setRotation(avatar.getRightArmRotation());
		leftLeg.setRotation(avatar.getLeftLegRotation());
		rightLeg.setRotation(avatar.getRightLegRotation());

        //runs without a timer by reposting this handler at the end of the runnable
        //see https://stackoverflow.com/questions/4597690/android-timer-how-to
        final Handler timerHandler = new Handler();
        final Runnable timerRunnable = new Runnable() {
            int midX = (int) base.getX();
            //int midY = (int) base.getY();
            final int maxDistance = 75;
            final int maxRotation = 25;
            int direction = 0; //0 right 1 left

            int midRotation = (int) leftArm.getRotation();
            int rotationDirection = 0;
            @Override
            public void run() {

                if(base.getX() > midX + maxDistance) direction = 1; //turn left
                if(base.getX() < midX - maxDistance) direction = 0; //turn right
                if(leftArm.getRotation() > midRotation + maxRotation) rotationDirection = 1;
                if(leftArm.getRotation() < midRotation - maxRotation) rotationDirection = 0;

                //move
                moveAvatar(15, 0, direction);

                wave_left_arm(rotationDirection);

                timerHandler.postDelayed(this, DELAY);
            }
        };


        //move avatar
        base.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!moveAvatar) {
                    timerHandler.removeCallbacks(timerRunnable);
                    moveAvatar = true;
                } else {
                    timerHandler.postDelayed(timerRunnable, DELAY);
                }
            }
        });

	}


    //roatates the left arm in a certain direction
	public void wave_left_arm(int direction) {
	    int armRotation = (int) avatar.getLeftArmRotation();

	    if(direction == 0) avatar.setLeftArmRotation(armRotation + 10);
	    else avatar.setLeftArmRotation(armRotation - 10);

	    leftArm.setRotation(avatar.getLeftArmRotation());
	}

    //moves the avatar by given x and y coords to the right, no direction
    public void moveAvatar(int deltaX, int deltaY){
        for(ImageView limb: body){
            limb.setX(limb.getX() + deltaX);
            limb.setY(limb.getY() + deltaY);
        }
    }

	//moves the avatar by given x and y coords and direction 0 right 1 left
	public void moveAvatar(int deltaX, int deltaY, int direction){
	    if(direction == 0){ //move right
            for(ImageView limb: body){
                limb.setX(limb.getX() + deltaX);
                limb.setY(limb.getY() + deltaY);
            }
        } else { //move left
            for(ImageView limb: body){
                limb.setX(limb.getX() - deltaX);
                limb.setY(limb.getY() - deltaY);
            }
        }

    }
}
