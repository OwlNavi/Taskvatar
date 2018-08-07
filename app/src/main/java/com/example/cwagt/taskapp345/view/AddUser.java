package com.example.cwagt.taskapp345.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.object.User;
import java.util.ArrayList;

/**
 * Created by cwagt on 15/07/2018.
 *
 * This class allows the user to create a new user account
 */

public class AddUser extends AppCompatActivity {
    //The context of the current activity
    private Context context;

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

        //Button code when a user wants to add a new user
        final Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Retrieve the information the user inputted
                TextView usernameField = findViewById(R.id.edittextUserName);
                TextView lastNameField = findViewById(R.id.edittextUserLastName);
                TextView firstNameField = findViewById(R.id.edittextUserFirstName);
                String username = usernameField.getText().toString();
                String firstName = firstNameField.getText().toString();
                String lastName = lastNameField.getText().toString();
                String fullName = firstName + " " + lastName;

                //Retrieve the list of users from the database so we can check the number of users
                ArrayList<User> users = DatabaseHelper.readAllUsers(context);

                //The new user needs to have a uniqueID so we set it to the number of users plus
                //one, this way all the users should have a unique id
                Long userID = (long) (users.size() + 1);

                //Create the new user
                User newUser = new User(username, userID, fullName);
                //Save them to the database
                DatabaseHelper.createUser(context, newUser);

                //Go back to the choose user page now the user has been saved
                Intent addUserIntent = new Intent(context, UserHome.class);
                startActivity(addUserIntent);
            }
        });
    }
}
