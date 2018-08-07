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
 *
 * This class manages the activity allowing users to edit tasks, letting them add new tasks
 * to the tasklist
 */
public class EditTask extends AppCompatActivity {
    //the current context
    private Context context;

    /**
     * Code executed when the activity is loaded
     * displays the list of tasks and adds buttons and on click effects for the activity
     * @param savedInstanceState the savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //The current context
        context = getApplicationContext();
        //Set the layout based on the xml file
        setContentView(R.layout.edit_task);

        //check current user if set in the shared preferences and load their info from database
        //if the preference is not set go back to user page
        SharedPreferences preferences = getDefaultSharedPreferences(context);
        Long userID = preferences.getLong("currentUser", 0);
        Log.d("Current User", Long.toString(userID));

        //if the userID is not set go back to user page
        if (userID == 0) { //not found
            Log.d("EditTask", "Went back to user page, current userid not found:" + userID);
            Intent userHomeIntent = new Intent(context, UserHome.class);
            finish();
            startActivity(userHomeIntent);
        }

        //Retrieve the list of tasks from the database to display on the recycler list
        List<Task> taskList = DatabaseHelper.readAllTasks(context);

        //get the current user from the database
        String selection = DatabaseColumnNames.User._ID + "=?";
        String[] selectionArgs = new String[]{Long.toString(userID)};
        ArrayList<User> users = DatabaseHelper.readUsers(context, selection, selectionArgs);
        if(users.size() > 1){
            throw new RuntimeException(context + "There should only be one or zero users with the same id: " + userID);
        }
        if(users.size() != 0){
            User currentUser = users.get(0);
            Log.d("Current User", currentUser.getUserName());
        }

        //Populate the recycler view with the list of tasks we just retrieved for the user
        RecyclerView taskRecyclerView = findViewById(R.id.taskList);
        TaskAdapter mAdapter = new TaskAdapter(context, taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        taskRecyclerView.setLayoutManager(mLayoutManager);
        taskRecyclerView.setItemAnimator(new DefaultItemAnimator());
        taskRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        taskRecyclerView.setAdapter(mAdapter);

        //Button code

        //Add task button
        final Button taskButton = findViewById(R.id.buttonAddTask);
        taskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Got to add task activity
                startActivity(new Intent(context, AddTask.class));
            }
        });

        //submit changes button
        final Button submitButton = findViewById(R.id.submitTaskButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //return to main activity
                startActivity(new Intent(context, MainActivity.class));
            }
        });
    }

}
