package com.example.cwagt.taskapp345.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.helper.UserAdapter;
import com.example.cwagt.taskapp345.object.User;
import java.util.ArrayList;

/**
 * Created by cwagt on 15/07/2018.
 *
 * Class for managing the user activity where people can add users or change the current user
 */
public class UserHome extends AppCompatActivity {
   //The current context
    private Context context;

    /**
     * Code executed when the activity is loaded
     * @param savedInstanceState the savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set view based on xml layour file
        setContentView(R.layout.user_layout);
        //Get the current context
        context = getApplicationContext();

        //Get the list of users so we can display them in the recycler view list
        ArrayList<User> userList = DatabaseHelper.readAllUsers(context);

        //If there are no users in the database create a default dummy list
        if(userList.size() == 0){
            for(User user: defaultUserlist()){
                DatabaseHelper.createUser(context, user);
            }
            userList = defaultUserlist();
            Log.d("UserHome", "No users found, wrote default list to database");
        }

        //Set up the recycler view to display the users
        RecyclerView userRecyclerView = findViewById(R.id.userList);
        userRecyclerView.setHasFixedSize(true);
        LinearLayoutManager userLayoutManager = new LinearLayoutManager(this);
        userLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        userRecyclerView.setLayoutManager(userLayoutManager);
        UserAdapter userAdapter = new UserAdapter(userList, UserHome.this);
        userRecyclerView.setAdapter(userAdapter);

        //Button code for add user button
        final Button button = findViewById(R.id.buttonAddUser);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Go to the add user activity
                Intent addUserIntent = new Intent(context, AddUser.class);
                startActivity(addUserIntent);
            }
        });
    }

    /**
     * Creates a default userList if the database is found empty
     * for testing purposes
     * @return an ArrayList of users
     */
    private ArrayList<User> defaultUserlist(){
        ArrayList<User> userList = new ArrayList<>();
        User user1 = new User(1L, "Alex", "Alexander Example");
        User user2 = new User(2L, "Ben", "Benjamen Default");
        User user3 = new User(3L, "Chris", "Christopher Template");
        User user4 = new User(4L, "Dave", "David Standard");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        return userList;
    }
}
