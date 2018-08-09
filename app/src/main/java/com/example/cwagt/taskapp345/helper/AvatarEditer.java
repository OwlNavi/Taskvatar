package com.example.cwagt.taskapp345.helper;

import android.view.View;
import android.widget.ImageView;

import com.example.cwagt.taskapp345.R;

/**
 * Created by cwagt on 9/08/2018.
 */

public class AvatarEditer {
    View avatar;
    ImageView hat;
    ImageView leftArm;
    ImageView rightArm;
    ImageView leftLeg;
    ImageView rightLeg;
    ImageView base;

    final int HEAD = 0;
    final int LEFT_ARM = 1;
    final int RIGHT_ARM = 2;
    final int LEFT_LEG = 3;
    final int RIGHT_LEG = 4;
    final int TORSO = 5;
    final int BACKGROUND = 6;


    public AvatarEditer(View avatarFragment){
        this.avatar = avatarFragment;
        this.base = avatar.findViewById(R.id.base);
        this.leftArm = avatar.findViewById(R.id.left_arm);
        this.hat = avatar.findViewById(R.id.hat);
        this.leftLeg = avatar.findViewById(R.id.left_leg);
        this.rightLeg = avatar.findViewById(R.id.right_leg);
        this.rightArm = avatar.findViewById(R.id.right_arm);
    }

    public void setImage(int currentCategory, String itemSelected) {
        switch (currentCategory){
            case(HEAD):
                setHat(itemSelected);
                break;
            case(LEFT_ARM):
                setLeftArm(itemSelected);
                break;
            case(RIGHT_ARM):
                setRightArm(itemSelected);
                break;
            case(LEFT_LEG):
                setLeftLeg(itemSelected);
                break;
            case(RIGHT_LEG):
                setRightLeg(itemSelected);
                break;
            default:
                break;
        }
    }
    private void setLeftLeg(String itemSelected) {
        if(itemSelected.equals("Strong")){
            leftLeg.setImageResource(R.drawable.left_leg_muscly);
        }
        if(itemSelected.equals("Robot")){
            leftLeg.setImageResource(R.drawable.left_leg_robot);
        }
        if(itemSelected.equals("Cartoon")){
            leftLeg.setImageResource(R.drawable.left_leg);
        }
    }
    private void setRightLeg(String itemSelected) {
        if(itemSelected.equals("Strong")){
            rightLeg.setImageResource(R.drawable.right_leg_muscly);
        }
        if(itemSelected.equals("Robot")){
            rightLeg.setImageResource(R.drawable.right_leg_robot);
        }
        if(itemSelected.equals("Cartoon")){
            rightLeg.setImageResource(R.drawable.right_leg);
        }
    }
    private void setRightArm(String itemSelected) {
        if(itemSelected.equals("Strong")){
            rightArm.setImageResource(R.drawable.right_arm_muscly);
        }
        if(itemSelected.equals("Robot")){
            rightArm.setImageResource(R.drawable.right_arm_robot);
        }
        if(itemSelected.equals("Cartoon")){
            rightArm.setImageResource(R.drawable.right_arm);
        }
    }

    private void setLeftArm(String itemSelected) {
        if(itemSelected.equals("Strong")){
            leftArm.setImageResource(R.drawable.left_arm_muscly);
        }
        if(itemSelected.equals("Robot")){
            leftArm.setImageResource(R.drawable.left_arm_robot);
        }
        if(itemSelected.equals("Cartoon")){
            leftArm.setImageResource(R.drawable.left_arm);
        }
    }

    private void setHat(String itemSelected) {
        if(itemSelected.equals("Crown")){
            hat.setImageResource(R.drawable.hat_crown);
        }
        if(itemSelected.equals("Pirate")){
            hat.setImageResource(R.drawable.hat_pirate);
        }
    }
}
