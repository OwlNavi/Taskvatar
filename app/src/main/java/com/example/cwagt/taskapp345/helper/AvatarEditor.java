package com.example.cwagt.taskapp345.helper;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.object.Avatar;
import com.example.cwagt.taskapp345.object.User;
import com.example.cwagt.taskapp345.view.Avatar_Fragment;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static android.provider.BaseColumns._ID;

/**
 * Created by cwagt on 9/08/2018.
 * This class is used to handle the interactions with the avatar fragment
 * It is used to edit body parts and save the avatar to the database
 */
public class AvatarEditor {
    //Current view
    private Avatar avatar;
    private Avatar_Fragment avatar_fragment;

    //currentCategory numbers
    private final int BASE = 0;
	private final int HAT = 1;
    private final int LEFT_ARM = 2;
    private final int RIGHT_ARM = 3;
    private final int LEFT_LEG = 4;
    private final int RIGHT_LEG = 5;
    private final int BACKGROUND = 6;

    //a data structure linking the name of the body part with the R.drawable id describing the resource
    private HashMap<String, Integer> bodyParts;

	public AvatarEditor(){}

    /**
     * Avatar Editer constructer
     * Given the view of the avatar fragment it will locate the body parts to initialise itself
     * @param avatarFragment the View of the avatar fragment
     */
    public AvatarEditor(Avatar_Fragment avatarFragment, Avatar avatar){
		this.avatar_fragment = avatarFragment;
		this.avatar = avatar;
		/*
		if(avatar.getBodyParts().isEmpty()) { //crashes here
			//if avatar doesnt have default values, initialise them
			initBodyParts();
		}
		*/

		//overwrites whatevers in the avatar with default values
		initBodyParts();
    }

	/**
	 * Initialises the bodyParts hash map to default values
	 */
    private void initBodyParts() {
		bodyParts = new HashMap<>();
		bodyParts.put("base", R.drawable.base);
		bodyParts.put("hat", R.drawable.hat_crown);
		bodyParts.put("leftArm", R.drawable.left_arm);
		bodyParts.put("rightArm", R.drawable.right_arm);
		bodyParts.put("leftLeg", R.drawable.left_leg);
		bodyParts.put("rightLeg", R.drawable.right_leg);
		bodyParts.put("background", R.drawable.white);
		avatar.setBodyParts(bodyParts);
	}

    /**
     * Modifies the avatar by changing the body part identified by category and item name
     * @param currentCategory the body part category
     * @param itemSelected the style selected
     */
    public void setImage(int currentCategory, String itemSelected) {
        switch (currentCategory){
			case(BASE):
				setBase(itemSelected);
				break;
            case(HAT):
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
            case(BACKGROUND):
                setBackground(itemSelected);
                break;
            default:
                break;
        }
    }

    /**
     * Sets the Base based on the item selected
     * @param itemSelected the name of the item selected
     */
    private void setBase(String itemSelected) {
    	switch(itemSelected) {
			case "Surprised":
				avatar_fragment.setBase(R.drawable.surprised);
				bodyParts.put("base", R.drawable.surprised);
				break;
			case "Silly":
				avatar_fragment.setBase(R.drawable.base_red);
				bodyParts.put("base", R.drawable.base_red);
				break;
			default:
				Log.e("AvatarEditor", "Error: Cannot set base to " + itemSelected + " because it does not exist");
		}

        saveAvatar();
    }

	/**
	 * Sets the hat based on the given string
	 * @param itemSelected the name of the item selected
	 */
	private void setHat(String itemSelected) {
		switch(itemSelected){
			case "Crown":
				avatar_fragment.setHat(R.drawable.hat_crown);
				bodyParts.put("hat", R.drawable.hat_crown);
				break;
			case "Pirate":
				avatar_fragment.setHat(R.drawable.hat_pirate);
				bodyParts.put("hat", R.drawable.hat_pirate);
				break;
			default:
				Log.e("helper.AvatarEditor", "Error: Cannot set hat to " + itemSelected + " because it does not exist");
		}

		saveAvatar();
	}

	/**
	 * Sets the left arm based on the item selected
	 * @param itemSelected the name of the item selected
	 */
	private void setLeftArm(String itemSelected) {
		switch(itemSelected){
			case "Strong":
				avatar_fragment.setLeftArm(R.drawable.left_arm_muscly);
				bodyParts.put("leftArm", R.drawable.left_arm_muscly);
				break;
			case "Robot":
				avatar_fragment.setLeftArm(R.drawable.left_arm_robot);
				bodyParts.put("leftArm", R.drawable.left_arm_robot);
				break;
			case "Cartoon":
				avatar_fragment.setLeftArm(R.drawable.left_arm);
				bodyParts.put("leftArm", R.drawable.left_arm);
				break;
			default:
				Log.e("AvatarEditor", "Error: Cannot set left arm to " + itemSelected + " because it does not exist");
		}

		saveAvatar();
	}

	/**
	 * Sets the right arm based on the item selected
	 * @param itemSelected the item selected's name
	 */
	private void setRightArm(String itemSelected) {
		switch(itemSelected){
			case "Strong":
				avatar_fragment.setRightArm(R.drawable.right_arm_muscly);
				bodyParts.put("rightArm", R.drawable.right_arm_muscly);
				break;
			case "Robot":
				avatar_fragment.setRightArm(R.drawable.right_arm_robot);
				bodyParts.put("rightArm", R.drawable.right_arm_robot);
				break;
			case "Cartoon":
				avatar_fragment.setRightArm(R.drawable.right_arm);
				bodyParts.put("rightArm", R.drawable.right_arm);
				break;
			default:
				Log.e("AvatarEditor", "Error: Cannot set right arm to " + itemSelected + " because it does not exist");
		}
		saveAvatar();
	}

	/**
     * Sets the left leg based on the item selected
     * @param itemSelected the name of the item selected
     */
    private void setLeftLeg(String itemSelected) {
    	switch(itemSelected){
			case "Strong":
				avatar_fragment.setLeftLeg(R.drawable.left_leg_muscly);
				bodyParts.put("leftLeg", R.drawable.left_arm_muscly);
				break;
			case "Robot":
				avatar_fragment.setLeftLeg(R.drawable.left_leg_robot);
				bodyParts.put("leftLeg", R.drawable.left_leg_robot);
				break;
			case "Cartoon":
				avatar_fragment.setLeftLeg(R.drawable.left_leg);
				bodyParts.put("leftLeg", R.drawable.left_leg);
				break;
			default:
				Log.e("AvatarEditor", "Error: Cannot set left leg to " + itemSelected + " because it does not exist");
        }

        saveAvatar();
    }

    /**
     * Sets the right leg based on the item selected
     * @param itemSelected the name of the item selected
     */
    private void setRightLeg(String itemSelected) {
    	switch(itemSelected){
			case "Strong":
				avatar_fragment.setRightLeg(R.drawable.right_leg_muscly);
				bodyParts.put("rightLeg", R.drawable.right_leg_muscly);
				break;
			case "Robot":
				avatar_fragment.setRightLeg(R.drawable.right_leg_robot);
				bodyParts.put("rightLeg", R.drawable.right_leg_robot);
				break;
			case "Cartoon":
				avatar_fragment.setRightLeg(R.drawable.right_leg);
				bodyParts.put("rightLeg", R.drawable.right_leg);
				break;
			default:
				Log.e("AvatarEditor", "Error: Cannot set right leg to " + itemSelected + " because it does not exist");
        }

        saveAvatar();
    }

	/**
	 * Sets the background based on the item selected
	 * @param itemSelected the name of the item selected
	 */
	private void setBackground(String itemSelected) {
		switch(itemSelected){
			case "Beach":
				avatar_fragment.setBackground(R.drawable.beach);
				bodyParts.put("background", R.drawable.beach);
				break;
			case "Desert":
				avatar_fragment.setBackground(R.drawable.desert);
				bodyParts.put("background", R.drawable.desert);
				break;
			case "Jungle":
				avatar_fragment.setBackground(R.drawable.jungle);
				bodyParts.put("background", R.drawable.jungle);
				break;
			case "White":
				avatar_fragment.setBackground(R.drawable.white);
				bodyParts.put("background", R.drawable.white);
				break;
			default:
				Log.e("AvatarEditor", "Error: Cannot set base to " + itemSelected + " because it does not exist");
		}

		saveAvatar();
	}

	/**
     * Saves the avatar so it can be retrieved
     */
    private Boolean saveAvatar(){

		Log.d("helper.AvatarEditor", "Saving avatar to database");

		//AvatarEditor ae = new AvatarEditor();
		Context context = null;
		try {
			context = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null, (Object[]) null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//get current user
		SharedPreferences preferences = getDefaultSharedPreferences(context); //TODO CRASHES HERE :(
		Long userID = preferences.getLong("currentUser", 0);
		ArrayList<User> users = DatabaseHelper.readUsers(context, _ID + " = ?", new String[]{Long.toString(userID)});
		if(users.isEmpty()){
			Log.e("helper.AvatarEditor", "There are 0 users in database with ID " + userID);
			return false;
		}else {
			User user = users.get(0); //gets the name, descr, etc
			avatar.setBodyParts(bodyParts);
			user.setAvatar(avatar);
			return DatabaseHelper.updateUser(context, userID, user);

		}

    }

}
