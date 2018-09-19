package com.example.cwagt.taskapp345.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.AddUserInputValidator;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.object.User;

/**
 * Created by cwagt on 15/07/2018.
 *
 * This class allows the user to create a new user account
 */

public class AddUser extends AppCompatActivity {
    //The context of the current activity
    private Context context;

    //the current user
    User user = null;

    /**
     * Code executed when the activity is loaded
     * @param savedInstanceState the savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get the current context
        context = getApplicationContext();
        //Set the activity layout based on xml file
        setContentView(R.layout.add_user);

        //check and see if we are editing a user
        //check and see if we are editing a task
        final Long userID = getIntent().getLongExtra("userID", 0);
        if(userID != 0) {
            //we have a task
            user = DatabaseHelper.readUser(context, userID);
            assert user != null;
            TextView editUsername = findViewById(R.id.edittextUserName);
            TextView editFirstName = findViewById(R.id.edittextUserFirstName);
            TextView editLastName = findViewById(R.id.edittextUserLastName);
            editUsername.setText(user.getUserName());

            String[] fullName = user.getUserDescription().split(" ");
            editFirstName.setText(fullName[0]);
            editLastName.setText(fullName[1]);

            //Button code when a user wants to add a new user
            final Button submitButton = findViewById(R.id.submitButton);
            submitButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Retrieve the information the user inputted
                    TextView usernameField = findViewById(R.id.edittextUserName);
                    TextView lastNameField = findViewById(R.id.edittextUserLastName);
                    TextView firstNameField = findViewById(R.id.edittextUserFirstName);
                    String username = usernameField.getText().toString();
                    String firstName = firstNameField.getText().toString();
                    String lastName = lastNameField.getText().toString();
                    String fullName = firstName + " " + lastName;

                    String validationMessage = AddUserInputValidator.validateTask(username,
                            fullName);

                    if(validationMessage.equals("")){
                        //Save them to the database
                        //Create the new user
                        user.setUserName(username);
                        user.setUserDescription(fullName);
                        DatabaseHelper.updateUser(context, user.get_id(), user);

                        //Go back to the choose user page now the user has been saved
                        Intent addUserIntent = new Intent(context, UserHome.class);
                        startActivity(addUserIntent);
                    } else {
                        //failed to validate

                        //show the user a message to let them know they must complete validation
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddUser.this);
                        builder.setMessage(validationMessage)
                                .setTitle("Input Errors");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                }
            });
        } else {
            //Button code when a user wants to add a new user
            final Button submitButton = findViewById(R.id.submitButton);
            submitButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Retrieve the information the user inputted
                    TextView usernameField = findViewById(R.id.edittextUserName);
                    TextView lastNameField = findViewById(R.id.edittextUserLastName);
                    TextView firstNameField = findViewById(R.id.edittextUserFirstName);
                    String username = usernameField.getText().toString();
                    String firstName = firstNameField.getText().toString();
                    String lastName = lastNameField.getText().toString();
                    String fullName = firstName + " " + lastName;

                    //Create the new user
                    User newUser = new User(username, fullName);

                    String validationMessage = AddUserInputValidator.validateTask(newUser.getUserName(),
                            newUser.getUserDescription());

                    if(validationMessage.equals("")){
                        //Save them to the database
                        long userID = DatabaseHelper.createUser(context, newUser);
                        newUser.set_id(userID);

                        //Go back to the choose user page now the user has been saved
                        Intent addUserIntent = new Intent(context, UserHome.class);
                        startActivity(addUserIntent);
                    } else {
                        //failed to validate

                        //show the user a message to let them know they must complete validation
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddUser.this);
                        builder.setMessage(validationMessage)
                                .setTitle("Input Errors");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                }
            });
        }


    }
}
