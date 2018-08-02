package com.example.cwagt.taskapp345.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.DatabaseColumnNames;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.helper.TaskAdapter;
import com.example.cwagt.taskapp345.object.Task;
import com.example.cwagt.taskapp345.object.User;

import java.util.ArrayList;
import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


/**
 * Created by cwagt on 15/07/2018.
 */

public class EditTask extends AppCompatActivity {

    private Context context;
    private User currentUser;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.edit_task);

        //check current user if set in the shared preferences and load their info from database
        //if the preference is not set go back to user page
        SharedPreferences preferences = getDefaultSharedPreferences(context);
        int userID = preferences.getInt("currentUser", 0);
        Log.d("Current User", Integer.toString(userID));

        //if the userID is not set go back to user page
        if (userID == 0) {
            Intent userHomeIntent = new Intent(context, UserHome.class);
            finish();
            startActivity(userHomeIntent);
        }
        List<Task> taskList = DatabaseHelper.readAllTasks(context);

        //get the user from the database
        String selection = DatabaseColumnNames.User.USER_NAME_ID + "=?";
        String[] selectionArgs = new String[]{Integer.toString(userID)};
        ArrayList<User> users = DatabaseHelper.readUsers(context, selection, selectionArgs);
        if(users.size() > 1){
            throw new RuntimeException(context + "There should only be one or zero users with the same id: " + userID);
        }
        if(users.size() != 0){
            currentUser = users.get(0);
            Log.d("Current User", currentUser.getUserName());
        }
        RecyclerView taskRecyclerView = findViewById(R.id.taskList);
        TaskAdapter mAdapter = new TaskAdapter(context, taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        taskRecyclerView.setLayoutManager(mLayoutManager);
        taskRecyclerView.setItemAnimator(new DefaultItemAnimator());
        taskRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        taskRecyclerView.setAdapter(mAdapter);

        //Button code
        final Button taskButton = findViewById(R.id.buttonAddTask);
        taskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                //return to main menu
                startActivity(new Intent(context, AddTask.class));
            }
        });
        final Button submitButton = findViewById(R.id.submitTaskButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                //return to main menu
                startActivity(new Intent(context, MainActivity.class));
            }
        });
    }

}
