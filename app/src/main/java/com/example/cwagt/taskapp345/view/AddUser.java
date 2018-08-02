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
 */

public class AddUser extends AppCompatActivity {

    private Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.add_user);

        //Button code
        final Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Create new user
                TextView usernameField = findViewById(R.id.edittextUserName);
                TextView lastNameField = findViewById(R.id.edittextUserLastName);
                TextView firstNameField = findViewById(R.id.edittextUserFirstName);
                String username = usernameField.getText().toString();
                String firstName = firstNameField.getText().toString();
                String lastName = lastNameField.getText().toString();
                String fullName = firstName + " " + lastName;

                ArrayList<User> users = DatabaseHelper.readAllUsers(context);

                int userID = users.size()+1;
                User newUser = new User(username, userID, fullName);

                DatabaseHelper.createUser(context, newUser);

                // Code here executes on main thread after user presses button
                Intent addUserIntent = new Intent(context, UserHome.class);
                startActivity(addUserIntent);
            }
        });
    }

}
