package com.example.cwagt.taskapp345.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.helper.TaskAdapter;
import com.example.cwagt.taskapp345.object.Task;

import java.util.List;

/**
 * Creates an avatar with a given rotation for each of the components.
 * Each avatar consists of two arms, two legs and a base.
 * Rotations are stored in the database
 * Authors: Josh April, Shaun Henderson, Craig Thomas
 */
public class MainActivity extends AppCompatActivity implements Avatar_Fragment.clickedBase {

    RecyclerView taskRecyclerView;

    private Context context = MainActivity.this;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getColor(android.R.color.white));

        //if(!getAvatar(db)) sout("Error getting avatar");
		List<Task> taskList = DatabaseHelper.getTasksFromDatabase(context);

		taskRecyclerView = findViewById(R.id.taskList);
		TaskAdapter mAdapter = new TaskAdapter(taskList);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
		taskRecyclerView.setLayoutManager(mLayoutManager);
		taskRecyclerView.setItemAnimator(new DefaultItemAnimator());
		taskRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
		taskRecyclerView.setAdapter(mAdapter);

	}
    @Override
    public void clicked() {
        Intent intent = new Intent(this, AvatarHome.class);
        startActivity(intent);
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

	@Override
	protected void onDestroy() {
		//mDbHelper.close(); //close database connection
		DatabaseHelper.closeDatabase(this);
		super.onDestroy();
	}

}
