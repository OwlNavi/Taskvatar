package com.example.cwagt.taskapp345.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cwagt on 15/09/2018.
 *
 * This class is user to validate user additions on the AddUser activity
 */

public class AddUserInputValidator {

    //returns a string containing errors in validation, or an emptpy string if it validates correctly
    // params
    // String taskName, the name of the task should be a string of characters
    // String taskDescription, a description of the task, a string of characters
    // String time, the time, in valid HH:MM format
    public static String validateTask(String userName, String userDescription){
        String message = "";

        message += validateName(userName);
        message += validateDescription(userName);

        return message;
    }

    //validates a task name
    private static String validateName(String name){
        //Check length
        if(name.length() > 32) return "User name must be less than 32 characters.\n";

        String patternString = "[a-zA-Z0-9'?!]+";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(name);

        //fauled validation
        if(!matcher.matches()){
            return "User Name must contain only valid characters\n";
        }

        //passes
        return "";
    }

    //validates a task name
    private static String validateDescription(String description){
        //Check length
        if(description.length() > 32) return "First and Last name must together be less than 32 characters.\n";

        String patternString = "[a-zA-Z']";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(description);

        //fauled validation
        if(!matcher.matches()){
            return "First and Last must contain only valid characters\n";
        }

        //passes
        return "";
    }
}
