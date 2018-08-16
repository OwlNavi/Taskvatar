package com.example.cwagt.taskapp345.helper;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import com.example.cwagt.taskapp345.R;

import java.util.HashMap;

/**
 * Created by cwagt on 9/08/2018.
 * This class is used to handle the interactions with the avatar fragment
 * It is used to edit body parts and save the avatar to the database
 */
public class AvatarEditer {
    //Image Views used to display the Avatar
    private ImageView base;
    private ImageView hat;
    private ImageView leftArm;
    private ImageView rightArm;
    private ImageView leftLeg;
    private ImageView rightLeg;
    //The background of the Constraint container
    private ConstraintLayout background;
    //Current view
    private View avatar;
    //currentCategory numbers
    private final int HEAD = 0;
    private final int LEFT_ARM = 1;
    private final int RIGHT_ARM = 2;
    private final int LEFT_LEG = 3;
    private final int RIGHT_LEG = 4;
    private final int TORSO = 5;
    private final int BACKGROUND = 6;

    //a data structure linking the name of the body part with the R.drawable id describing the resource
    private HashMap<String, Integer> bodyParts;

    /**
     * Avatar Editer constructer
     * Given the view of the avatar fragment it will locate the body parts to initialise itself
     * @param avatarFragment the View of the avatar fragment
     */
    public AvatarEditer(View avatarFragment){
        this.avatar = avatarFragment;
        this.base = avatar.findViewById(R.id.base);
        this.leftArm = avatar.findViewById(R.id.left_arm);
        this.hat = avatar.findViewById(R.id.hat);
        this.leftLeg = avatar.findViewById(R.id.left_leg);
        this.rightLeg = avatar.findViewById(R.id.right_leg);
        this.rightArm = avatar.findViewById(R.id.right_arm);
        this.background = avatar.findViewById(R.id.avatar_container);

        bodyParts = new HashMap<>();
        bodyParts.put("base", R.id.base);
        bodyParts.put("hat", R.id.hat);
        bodyParts.put("leftArm", R.id.left_arm);
        bodyParts.put("rightArm", R.id.right_arm);
        bodyParts.put("leftLeg", R.id.left_leg);
        bodyParts.put("rightLeg", R.id.right_leg);
        bodyParts.put("background", R.id.avatar_container);
        //TODO: we never do anything with bodyParts
    }

    /**
     * Modifies the avatar by changing the body part identified by category and item name
     * @param currentCategory the body part category
     * @param itemSelected the style selected
     */
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
            case(TORSO):
                setTorso(itemSelected);
                break;
            case(BACKGROUND):
                setBackground(itemSelected);
                break;
            default:
                break;
        }
    }

    /**
     * Sets the Torso based on the item selected
     * @param itemSelected the name of the item selected
     */
    private void setTorso(String itemSelected) {

        if(itemSelected.equals("Surprised")) {
            base.setImageResource(R.drawable.surprised);
            bodyParts.put("base", R.drawable.surprised);
        }
        if(itemSelected.equals("Silly")){
            base.setImageResource(R.drawable.base_red);
            bodyParts.put("base", R.drawable.surprised);
        }

        saveAvatar();
    }

    /**
     * Sets the left leg based on the item selected
     * @param itemSelected the name of the item selected
     */
    private void setLeftLeg(String itemSelected) {
        if(itemSelected.equals("Strong")){
            leftLeg.setImageResource(R.drawable.left_leg_muscly);
            bodyParts.put("leftLeg", R.drawable.left_arm_muscly);
        }
        if(itemSelected.equals("Robot")){
            leftLeg.setImageResource(R.drawable.left_leg_robot);
            bodyParts.put("leftLeg", R.drawable.left_leg_robot);
        }
        if(itemSelected.equals("Cartoon")){
            leftLeg.setImageResource(R.drawable.left_leg);
            bodyParts.put("leftLeg", R.drawable.left_leg);
        }

        saveAvatar();
    }
    /**
     * Sets the background based on the item selected
     * @param itemSelected the name of the item selected
     */
    private void setBackground(String itemSelected) {
        if(itemSelected.equals("Beach")) {
            avatar.setBackgroundResource(R.drawable.beach);
            bodyParts.put("background", R.drawable.beach);
        }
        if(itemSelected.equals("Desert")) {
            avatar.setBackgroundResource(R.drawable.desert);
            bodyParts.put("background", R.drawable.desert);
        }
        if(itemSelected.equals("Jungle")) {
            avatar.setBackgroundResource(R.drawable.jungle);
            bodyParts.put("background", R.drawable.jungle);
        }
        if(itemSelected.equals("White")) {
            avatar.setBackgroundResource(R.drawable.base);
            bodyParts.put("background", R.drawable.base);
            avatar.setBackgroundColor(Color.WHITE);
        }

        saveAvatar();
    }

    /**
     * Sets the right leg based on the item selected
     * @param itemSelected the name of the item selected
     */
    private void setRightLeg(String itemSelected) {
        if(itemSelected.equals("Strong")){
            rightLeg.setImageResource(R.drawable.right_leg_muscly);
            bodyParts.put("rightLeg", R.drawable.right_leg_muscly);
        }
        if(itemSelected.equals("Robot")){
            rightLeg.setImageResource(R.drawable.right_leg_robot);
            bodyParts.put("rightLeg", R.drawable.right_leg_robot);
        }
        if(itemSelected.equals("Cartoon")){
            rightLeg.setImageResource(R.drawable.right_leg);
            bodyParts.put("rightLeg", R.drawable.right_leg);
        }

        saveAvatar();
    }

    /**
     * Sets the right arm based on the item selected
     * @param itemSelected the item selected's name
     */
    private void setRightArm(String itemSelected) {
        if(itemSelected.equals("Strong")){
            rightArm.setImageResource(R.drawable.right_arm_muscly);
            bodyParts.put("rightArm", R.drawable.right_arm_muscly);
        }
        if(itemSelected.equals("Robot")){
            rightArm.setImageResource(R.drawable.right_arm_robot);
            bodyParts.put("rightArm", R.drawable.right_arm_robot);
        }
        if(itemSelected.equals("Cartoon")){
            rightArm.setImageResource(R.drawable.right_arm);
            bodyParts.put("rightArm", R.drawable.right_arm);
        }

        saveAvatar();
    }

    /**
     * Sets the left arm based on the item selected
     * @param itemSelected the name of the item selected
     */
    private void setLeftArm(String itemSelected) {
        if(itemSelected.equals("Strong")){
            leftArm.setImageResource(R.drawable.left_arm_muscly);
            bodyParts.put("leftArm", R.drawable.left_arm_muscly);
        }
        if(itemSelected.equals("Robot")){
            leftArm.setImageResource(R.drawable.left_arm_robot);
            bodyParts.put("leftArm", R.drawable.left_arm_robot);
        }
        if(itemSelected.equals("Cartoon")){
            leftArm.setImageResource(R.drawable.left_arm);
            bodyParts.put("leftArm", R.drawable.left_arm);
        }

        saveAvatar();
    }

    /**
     * Sets the hat based on the given string
     * @param itemSelected the name of the item selected
     */
    private void setHat(String itemSelected) {
        if(itemSelected.equals("Crown")){
            hat.setImageResource(R.drawable.hat_crown);
            bodyParts.put("hat", R.drawable.hat_crown);
        }
        if(itemSelected.equals("Pirate")){
            hat.setImageResource(R.drawable.hat_pirate);
            bodyParts.put("hat", R.drawable.hat_pirate);
        }

        saveAvatar();
    }

    /**
     * Saves the avatar so it can be retrieved
     */
    private void saveAvatar(){
        //SAVE IN DATABASE
    }

    /**
     * Sets the avatar in the view based on the provided hashmap values
     * @param bodyParts lit of body parts
     */
    public void setAvatar(HashMap<String, Integer> bodyParts){
        hat.setImageResource(bodyParts.get("hat"));
        leftArm.setImageResource(bodyParts.get("leftArm"));
        rightArm.setImageResource(bodyParts.get("rightArm"));
        leftLeg.setImageResource(bodyParts.get("leftLeg"));
        rightLeg.setImageResource(bodyParts.get("rightLeg"));
    }

    public HashMap<String, Integer> getBodyParts() {
        return bodyParts;
    }
}
