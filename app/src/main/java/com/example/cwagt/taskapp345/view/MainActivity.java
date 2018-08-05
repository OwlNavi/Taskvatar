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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.DatabaseColumnNames;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.helper.TaskAdapter;
import com.example.cwagt.taskapp345.object.Enums;
import com.example.cwagt.taskapp345.object.Task;
import com.example.cwagt.taskapp345.object.User;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Creates an avatar with a given rotation for each of the components.
 * Each avatar consists of two arms, two legs and a base.
 * Rotations are stored in the database
 * Authors: Josh April, Shaun Henderson, Craig Thomas
 */
public class MainActivity extends AppCompatActivity  {

    RecyclerView taskRecyclerView;

    private User currentUser;
    private List<Task> taskList;

    private Context context = MainActivity.this;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getColor(android.R.color.white));

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

		//get the user from the database
		String selection = DatabaseColumnNames.User.USER_NAME_ID + "=?";
		String[] selectionArgs = new String[]{Integer.toString(userID)};
		ArrayList<User> users = DatabaseHelper.getUsersFromDatabase(context, selection, selectionArgs);
		if(users.size() > 1){
			throw new RuntimeException(context + "There should only be one or zero users with the same id: " + userID);
		}
		if(users.size() != 0){
			currentUser = users.get(0);
			Log.d("Current User", currentUser.getUserName());
		}


        //if(!getAvatar(db)) sout("Error getting avatar");
		taskList = DatabaseHelper.getAllTasksFromDatabase(context);

		taskRecyclerView = findViewById(R.id.taskList);
		TextView textTasksCompleted = (TextView) findViewById(R.id.textTasksCompleted);
		TaskAdapter mAdapter = new TaskAdapter(context, taskList, textTasksCompleted);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
		taskRecyclerView.setLayoutManager(mLayoutManager);
		taskRecyclerView.setItemAnimator(new DefaultItemAnimator());
		taskRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
		taskRecyclerView.setAdapter(mAdapter);
        ;
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

			case R.id.menu_tasks:
				Intent editTaskIntent = new Intent(this, EditTask.class);
				startActivity(editTaskIntent);
				break;
		}

        return super.onOptionsItemSelected(item);
    }

	@Override
	protected void onDestroy() {
		//mDbHelper.close(); //close database connection
		DatabaseHelper.closeDatabase(this);
		super.onDestroy();
	}

}
