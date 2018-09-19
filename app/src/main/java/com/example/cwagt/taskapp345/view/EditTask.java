package com.example.cwagt.taskapp345.view;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.cwagt.taskapp345.helper.EditingTaskAdapter;
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
        List<Task> taskList = DatabaseHelper.readAllTasks(context, userID);

        //keep track of which tasks have been selected
        final List<Task> selectedList = new ArrayList<Task>();

        //get the current user from the database
        User currentUser = DatabaseHelper.readUser(context, userID);
        Log.d("Current User", currentUser.getUserName());
        //TODO: What are we doing with currentUser? Do we need this snippet?

        //Populate the recycler view with the list of tasks we just retrieved for the user
        RecyclerView taskRecyclerView = findViewById(R.id.taskList);
        EditingTaskAdapter mAdapter = new EditingTaskAdapter(context, taskList, selectedList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        taskRecyclerView.setLayoutManager(mLayoutManager);
        taskRecyclerView.setItemAnimator(new DefaultItemAnimator());
        taskRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        taskRecyclerView.setAdapter(mAdapter);




        //Add task button
        final Button taskButton = findViewById(R.id.buttonAddTask);
        taskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Got to add task activity
                finish();
                startActivity(new Intent(context, AddTask.class));

            }
        });

        //Delete selected button
        final Button deleteButton = findViewById(R.id.buttonDeleteTasks);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Got to add task activity
                //show the user a message to let them know they must complete validation
                AlertDialog.Builder builder = new AlertDialog.Builder(EditTask.this);
                builder.setMessage("Are you sure?")
                        .setTitle("Delete Tasks");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button

                        //delete tasks
                        for(Task task: selectedList){
                            DatabaseHelper.deleteTask(context, task);
                        }


                        finish();
                        startActivity(new Intent(context, EditTask.class));

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked NO button
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //submit changes button
        final Button submitButton = findViewById(R.id.submitTaskButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //return to main activity
                finish();
                startActivity(new Intent(context, MainActivity.class));

            }
        });
    }

    /**
     * Populates the options dropdown menu in the top right of the activity
     * @param menu the menu to display
     * @return true when created
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Code that handles what happens when you click on one of the menu items
     * @param item the menu item clicked on
     * @return boolean clicked
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Check if user clicked on the refresh button
            case R.id.menu_refresh:
                //go back to main activity
                Intent refreshIntent = new Intent(this, MainActivity.class);
                finish();
                startActivity(refreshIntent);
                break;

            //Got to select/change user activity
            case R.id.menu_user:
                Intent userIntent = new Intent(this, UserHome.class);
                finish();
                startActivity(userIntent);
                break;

            //Got to avatar screen
            case R.id.menu_avatar:
                Intent avatarIntent = new Intent(this, AvatarHome.class);
                finish();
                startActivity(avatarIntent);
                break;

            //Go to edit task screen
            case R.id.menu_tasks:
                Intent editTaskIntent = new Intent(this, EditTask.class);
                finish();
                startActivity(editTaskIntent);
                break;
            case R.id.menu_home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                finish();
                startActivity(homeIntent);
                //Go to main Activity
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
