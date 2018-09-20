package com.example.cwagt.taskapp345.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cwagt on 15/09/2018.
 *
 * This class is user to validate user additions on the AddUser activity
 */

public class AddUserInputValidator {

    /**
     * returns a string containing errors in validation, or an emptpy string if it validates correctly
     * @param userName the name of the user should be a string of characters
     * @param userDescription a description of the user, a string of characters
     * @return "" on success, error on failure
     */
    public static String validateUser(String userName, String userDescription){
        String message = "";

        message += validateName(userName);
        message += validateDescription(userDescription);

        return message;
    }

	/**
	 * Validates a user name
	 * @param name the name of the user
	 * @return
	 */
	private static String validateName(String name){
        //Check length
        if(name.length() > 32) return "User name must be less than 32 characters.\n";

        String patternString = "[a-zA-Z0-9']+";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(name);

        //fauled validation
        if(!matcher.matches()){
            return "User Name must contain only valid characters\n";
        }

        //passes
        return "";
    }

	/**
	 * Validates a user description
	 * @param description the description of the user
	 * @return
	 */
	private static String validateDescription(String description){
        //Check length
        if(description.length() > 32) return "First and Last name must together be less than 32 characters.\n";

        String patternString = "[a-zA-Z' ]+";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(description);

        //failed validation
        if(!matcher.matches()){
            return "First and Last must contain only valid characters\n";
        }

        //passes
        return "";
    }
}
