package com.example.cwagt.taskapp345.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.helper.TaskAdapter;
import com.example.cwagt.taskapp345.object.Enums;
import com.example.cwagt.taskapp345.object.Task;
import com.example.cwagt.taskapp345.object.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	@SuppressLint("SetTextI18n")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the layout based on the xml file
        setContentView(R.layout.activity_main);

        //create and add an AvatarFragment to the activity
		displayAvatar();



        //Toolbar  on the top of the screen
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
		Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //add back button
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		//Apply a white colour to elements of toolbar
		Objects.requireNonNull(toolbar.getNavigationIcon()).setColorFilter(getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
		toolbar.setTitleTextColor(getColor(android.R.color.white));

        //check current user if set in the shared preferences and load their info from database
		//if the preference is not set go back to user page
		SharedPreferences preferences = getDefaultSharedPreferences(context);
		Long userID = preferences.getLong("currentUser", 0);
		Log.d("MainActivity", "Current User " + Long.toString(userID));


		/*
		//get the current user from the database
		String selection = DatabaseColumnNames.User._ID + " = ?";
		String[] selectionArgs = new String[]{Long.toString(userID)};
		ArrayList<User> users = DatabaseHelper.readUsers(context, selection, selectionArgs);
		User currentUser = users.get(0);
		*/
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

		//Check the number of completed tasks and update tasksCompleted
		if(taskList.size() > 0){
			int completed = 0;
			for(Task task: taskList){
				if(task.getStatus() == Enums.Status.COMPLETED){
					completed++;
				}
			}
			textTasksCompleted.setText("" + completed);
		}

		//use the time to reset completed tasks
/*
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				//setAllTasksToIncomplete(context);
				Log.d("MainActivity", "Timer called");


			}
		}, 3000, 30*1000); //1000 is one second

		//find the avatar fragment
		View avatarFragment = findViewById(R.id.main_avatar_container);
		AvatarEditor editer = new AvatarEditor(avatarFragment);
*/

    }

    @Override
	protected void onResume() {
		super.onResume();
		//recreate();
	}

	/**
	 * Display the avatar fragment within the main activity
	 *
	 * */

    private void displayAvatar() {
		//create instance of avatar fragment
		Avatar_Fragment main_activity_avatar_fragment = Avatar_Fragment.newInstance();
		//add fragment to main activity
		android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.main_avatar_container,main_activity_avatar_fragment,"main_activity_fragment").commit();
	}

	/**
	 * Sets all tasks in the database to incomplete
	 * @param context the current context
	 */
	private void setAllTasksToIncomplete(Context context) {
		ArrayList<User> users = DatabaseHelper.readAllUsers(context);
		for(User user: users){
			ArrayList<Task> taskList = DatabaseHelper.readAllTasks(context, user.get_id());
			for(Task task: taskList){
				task.setStatus(Enums.Status.INCOMPLETE);
				DatabaseHelper.updateTask(context, user.get_id(), task);
			}
		}
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
				startActivity(avatarIntent);
			break;

			//Go to edit task screen
			case R.id.menu_tasks:
				Intent editTaskIntent = new Intent(this, EditTask.class);
				finish();
				startActivity(editTaskIntent);
				break;
			case android.R.id.home:
				//onDestroy();
				finish();
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
