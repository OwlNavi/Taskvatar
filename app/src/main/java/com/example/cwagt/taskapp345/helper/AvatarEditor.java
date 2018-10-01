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
import java.util.HashMap;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

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
     * Avatar Editor constructor
     * Given the view of the avatar fragment it will locate the body parts to initialise itself
     * @param avatarFragment the View of the avatar fragment
     */
    public AvatarEditor(Avatar_Fragment avatarFragment, Avatar avatar){
		this.avatar_fragment = avatarFragment;
		this.avatar = avatar;
		this.bodyParts = avatar.getBodyParts();
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
    	//the id of the image resource for the body part
    	Integer bodyPart = 0;
    	switch(itemSelected) {
			case "Surprised":
				bodyPart = R.drawable.surprised;
				break;
			case "Silly":
				bodyPart = R.drawable.base_red;
				break;
			default:
				Log.e("AvatarEditor", "Error: Cannot set base to " + itemSelected + " because it does not exist");
		}
		//change the displayed image
		avatar_fragment.setBase(bodyPart);
    	//record which image is set
		bodyParts.put("base", bodyPart);
		//update database
        saveAvatar();
    }

	/**
	 * Sets the hat based on the given string
	 * @param itemSelected the name of the item selected
	 */
	private void setHat(String itemSelected) {
		//the id of the image resource for the body part
		Integer bodyPart = 0;
		switch(itemSelected){
			case "Crown":
				bodyPart = R.drawable.hat_crown;
				break;
			case "Pirate":
				bodyPart = R.drawable.hat_pirate;
				break;
			case "Roses":
				bodyPart = R.drawable.hat_rose;
				break;
			default:
				Log.e("helper.AvatarEditor", "Error: Cannot set hat to " + itemSelected + " because it does not exist");
		}
		//change displayed image
		avatar_fragment.setHat(bodyPart);
		//record which body part is set
		bodyParts.put("hat", bodyPart);
		//update database
		saveAvatar();
	}

	/**
	 * Sets the left arm based on the item selected
	 * @param itemSelected the name of the item selected
	 */
	private void setLeftArm(String itemSelected) {
		//the id of the image resource for the body part
		Integer bodyPart = 0;
		switch(itemSelected){
			case "Strong":
				bodyPart = R.drawable.left_arm_muscly;
				break;
			case "Robot":
				bodyPart = R.drawable.left_arm_robot;
				break;
			case "Cartoon":
				bodyPart = R.drawable.left_arm;
				break;
			case "Princess":
				bodyPart = R.drawable.left_arm_princess;
				break;
			default:
				Log.e("AvatarEditor", "Error: Cannot set left arm to " + itemSelected + " because it does not exist");
		}
		//update displayed image
		avatar_fragment.setLeftArm(bodyPart);
		//record which image is displayed
		bodyParts.put("leftArm", bodyPart);
		//update the database
		saveAvatar();
	}

	/**
	 * Sets the right arm based on the item selected
	 * @param itemSelected the item selected's name
	 */
	private void setRightArm(String itemSelected) {
		//the id of the image resource for the body part
		Integer bodyPart = 0;
		switch(itemSelected){
			case "Strong":
				bodyPart = R.drawable.right_arm_muscly;
				break;
			case "Robot":
				bodyPart = R.drawable.right_arm_robot;
				break;
			case "Cartoon":
				bodyPart = R.drawable.right_arm;
				break;
			case "Princess":
				bodyPart = R.drawable.right_arm_princess;
				break;
			default:
				Log.e("AvatarEditor", "Error: Cannot set right arm to " + itemSelected + " because it does not exist");
		}
		//change image displayed
		avatar_fragment.setRightArm(bodyPart);
		//record the currently set bodyparts
		bodyParts.put("rightArm", bodyPart);
		//update database
		saveAvatar();
	}

	/**
     * Sets the left leg based on the item selected
     * @param itemSelected the name of the item selected
     */
    private void setLeftLeg(String itemSelected) {
		Integer bodyPart = 0;
    	switch(itemSelected){
			case "Strong":
				bodyPart = R.drawable.left_leg_muscly;
				break;
			case "Robot":
				bodyPart = R.drawable.left_leg_robot;
				break;
			case "Cartoon":
				bodyPart = R.drawable.left_leg;
				break;
			case "Princess":
				bodyPart = R.drawable.left_leg_princess;
				break;
			default:
				Log.e("AvatarEditor", "Error: Cannot set left leg to " + itemSelected + " because it does not exist");
        }
		avatar_fragment.setLeftLeg(bodyPart);
		bodyParts.put("leftLeg", bodyPart);
        saveAvatar();
    }

    /**
     * Sets the right leg based on the item selected
     * @param itemSelected the name of the item selected
     */
    private void setRightLeg(String itemSelected) {
		Integer bodyPart = 0;
    	switch(itemSelected){
			case "Strong":
				bodyPart = R.drawable.right_leg_muscly;
				break;
			case "Robot":
				bodyPart = R.drawable.right_leg_robot;
				break;
			case "Cartoon":
				bodyPart = R.drawable.right_leg;
				break;
			case "Princess":
				bodyPart = R.drawable.right_leg_princess;
				break;
			default:
				Log.e("AvatarEditor", "Error: Cannot set right leg to " + itemSelected + " because it does not exist");
        }
		avatar_fragment.setRightLeg(bodyPart);
		bodyParts.put("rightLeg", bodyPart);
        saveAvatar();
    }

	/**
	 * Sets the background based on the item selected
	 * @param itemSelected the name of the item selected
	 */
	private void setBackground(String itemSelected) {
		Integer bodyPart = 0;
		switch(itemSelected){
			case "Beach":
				bodyPart = R.drawable.beach;
				break;
			case "Desert":
				bodyPart = R.drawable.desert;
				break;
			case "Jungle":
				bodyPart = R.drawable.jungle;
				break;
			case "White":
				bodyPart = R.drawable.white;
				break;
			default:
				Log.e("AvatarEditor", "Error: Cannot set base to " + itemSelected + " because it does not exist");
		}
		avatar_fragment.setBackground(bodyPart);
		bodyParts.put("background", bodyPart);
		saveAvatar();
	}

	/**
     * Saves the avatar to the database so it can be retrieved persistently
     */
    private Boolean saveAvatar(){

		Log.d("helper.AvatarEditor", "Saving avatar to database");

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
		SharedPreferences preferences = getDefaultSharedPreferences(context);
		Long userID = preferences.getLong("currentUser", 0);
		User user = DatabaseHelper.readUser(context, userID);

		avatar.setBodyParts(bodyParts);
		user.setAvatar(avatar);
		return DatabaseHelper.updateUser(context, userID, user);
    }
}
