package com.example.cwagt.taskapp345.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
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
import java.util.List;

import static android.app.PendingIntent.getActivity;

/**
 * Created by cwagt on 15/07/2018.
 */

public class UserHome extends AppCompatActivity {
    private RecyclerView userRecyclerView;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);
        context = getApplicationContext();

        //List<User> userList = DatabaseHelper.getUsersFromDatabase(context, "", new String[]{});
        ArrayList<User> userList = DatabaseHelper.readAllUsers(context);
        if(userList.size() == 0){
            for(User user: defaultUserlist()){
                DatabaseHelper.createUser(context, user);
            }
            userList = defaultUserlist();
            Log.d("UserHome", "No users found, wrote default list to database");
        }

        userRecyclerView = findViewById(R.id.userList);
        userRecyclerView.setHasFixedSize(true);


        LinearLayoutManager userLayoutManager = new LinearLayoutManager(this);
        userLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        userRecyclerView.setLayoutManager(userLayoutManager);

        UserAdapter userAdapter = new UserAdapter(userList, UserHome.this);

        //userRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //userRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        userRecyclerView.setAdapter(userAdapter);

        //Button code
        final Button button = findViewById(R.id.buttonAddUser);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent addUserIntent = new Intent(context, AddUser.class);
                startActivity(addUserIntent);
            }
        });
    }

    //creates a userlist separate from the database
    private ArrayList<User> defaultUserlist(){
        ArrayList<User> userList = new ArrayList<>();
        User user1 = new User("Alex", 1, "Alexander Example");
        User user2 = new User("Ben", 2, "Benjamen Default");
        User user3 = new User("Chris", 3, "Christopher Template");
        User user4 = new User("Dave", 4, "David Standard");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        return userList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Check if user triggered a refresh:
            case R.id.menu_refresh:
                Intent refreshIntent = new Intent(this, MainActivity.class);
                finish();
                startActivity(refreshIntent);
                break;

            case R.id.menu_user:
                Intent userIntent = new Intent(this, UserHome.class);
                startActivity(userIntent);
                break;

            case R.id.menu_avatar:
                Intent avatarIntent = new Intent(this, AvatarHome.class);
                startActivity(avatarIntent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
