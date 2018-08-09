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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.AvatarEditer;
import com.example.cwagt.taskapp345.helper.DatabaseColumnNames;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.helper.TaskAdapter;
import com.example.cwagt.taskapp345.object.Enums;
import com.example.cwagt.taskapp345.object.Task;
import com.example.cwagt.taskapp345.object.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Creates an avatar with a given rotation for each of the components.
 * Each avatar consists of two arms, two legs and a base.
 * Rotations are stored in the database
 * Authors: Josh April, Shaun Henderson, Craig Thomas
 */
public class MainActivity extends AppCompatActivity  {
	//The recycler view of tasks to display on the main activity
    RecyclerView taskRecyclerView;

    //the current context
	private Context context = MainActivity.this;

	/**
	 * Code executed when the main activity is loaded
	 * @param savedInstanceState the savedInstanceState
	 */
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the layout based on the xml file
        setContentView(R.layout.activity_main);

		//find the avatar fragment
		View avatarFragment = findViewById(R.id.avatar_fragment);
		AvatarEditer editer = new AvatarEditer(avatarFragment);
		//HashMap<String, Integer> avatarBodyParts = DatabaseHelper.loadAvatar();
		//editer.setAvatar(avatarBodyParts);

        //Toolbar  on the top of the screen
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getColor(android.R.color.white));

        //check current user if set in the shared preferences and load their info from database
		//if the preference is not set go back to user page
		SharedPreferences preferences = getDefaultSharedPreferences(context);
		Long userID = preferences.getLong("currentUser", 0);
		Log.d("Current User", Long.toString(userID));

		//if the userID is not set go back to user page
		if (userID == 0) { //not found
			Intent userHomeIntent = new Intent(context, UserHome.class);
			finish();
			startActivity(userHomeIntent);
		}

		//get the current user from the database
		String selection = DatabaseColumnNames.User._ID + " = ?";
		String[] selectionArgs = new String[]{Long.toString(userID)};
		ArrayList<User> users = DatabaseHelper.readUsers(context, selection, selectionArgs);
		if(users.size() > 1){
			throw new RuntimeException(context + "There should only be one or zero users with the same id: " + userID);
		}
		if(users.size() != 0){
			User currentUser = users.get(0);
			Log.d("Current User", currentUser.getUserName());
		}

		//Get a list of tasks for the current user
		final List<Task> taskList = DatabaseHelper.readAllTasks(context, userID);

		//display the task list in the recycler view
		taskRecyclerView = findViewById(R.id.taskList);
		//Reference to the view displaying the number of completed tasks
		TextView textTasksCompleted = findViewById(R.id.textTasksCompleted);
		TaskAdapter mAdapter = new TaskAdapter(context, taskList, textTasksCompleted);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
		taskRecyclerView.setLayoutManager(mLayoutManager);
		taskRecyclerView.setItemAnimator(new DefaultItemAnimator());
		taskRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
		taskRecyclerView.setAdapter(mAdapter);

		//Check the number of compelted tasks and update tasksCompleted
		if(taskList.size() > 0){
			int completed = 0;
			for(Task task: taskList){
				if(task.getStatus() == Enums.Status.COMPLETED){
					completed++;
				}
			}
			textTasksCompleted.setText(Integer.toString(completed));
		}

		//use the time to reset completed tasks

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				SimpleDateFormat timeFormat = new SimpleDateFormat("hh");
				String currentTime = timeFormat.format(new Date());
				if(Integer.parseInt(currentTime) == 0){ //midnight
					//clear database
				}

			}
		}, 3000, 60*60*1000); //every hour 3 seconds after loeading

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
				startActivity(userIntent);
				break;

			//Got to avatar screen
			case R.id.menu_avatar:
				Intent avatarIntent = new Intent(this, AvatarHome.class);
				startActivity(avatarIntent);
				break;

			//Go to edit task screen
			case R.id.menu_tasks:
				Intent editTaskIntent = new Intent(this, EditTask.class);
				startActivity(editTaskIntent);
				break;
		}

        return super.onOptionsItemSelected(item);
    }

	/**
	 * When the activity is closed
	 */
	@Override
	protected void onDestroy() {
		//mDbHelper.close(); //close database connection
		DatabaseHelper.closeDatabase(this);
		super.onDestroy();
	}

}
