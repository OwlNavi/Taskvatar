package com.example.cwagt.taskapp345.view;

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

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.UserAdapter;
import com.example.cwagt.taskapp345.object.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwagt on 15/07/2018.
 */

public class UserHome extends AppCompatActivity {
    private RecyclerView userRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        //Create the user activity
        //TODO implement users pull from database
        //List<User> userList = DatabaseHelper.getUsersFromDatabase(context, "", new String[]{});
        List<User> userList = defaultUserlist();
        Log.d("UserHome", "onCreate: userList size: " + userList.size());
        userRecyclerView = findViewById(R.id.userList);
        userRecyclerView.setHasFixedSize(true);


        LinearLayoutManager userLayoutManager = new LinearLayoutManager(this);
        userLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        userRecyclerView.setLayoutManager(userLayoutManager);

        UserAdapter userAdapter = new UserAdapter(userList, UserHome.this);

        //userRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //userRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        userRecyclerView.setAdapter(userAdapter);
    }

    //creates a userlist separate from the database
    private List<User> defaultUserlist(){
        List<User> userList = new ArrayList<>();
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
