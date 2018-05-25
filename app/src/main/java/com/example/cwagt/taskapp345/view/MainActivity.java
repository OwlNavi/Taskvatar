package com.example.cwagt.taskapp345.view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cwagt.taskapp345.helper.DatabaseContract;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.helper.TaskAdapter;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.object.Task;
//import com.example.cwagt.taskapp345.object.TaskOld;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    View avatarImage;
    RecyclerView recyclerView;
	//List<TaskOld> taskOlds = new ArrayList<>();

	private List<Task> taskList = new ArrayList<>();
	private TaskAdapter mAdapter;

	public DatabaseHelper mDbHelper = new DatabaseHelper(this); //needs SQLiteOpenHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getColor(android.R.color.white));
        //toolbar.inflateMenu(R.menu.menu_main);

		//Reading from database
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

        avatarImage = findViewById(R.id.avatar);
        avatarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AvatarHome.class);
                startActivity(intent);
            }

        });
        if(!getAvatar(db)) sout("Error getting avatar");

		taskList = DatabaseHelper.getTasksFromDatabase(db, DatabaseContract.Task.COLUMN_NAME_TEXT + " = ?", new String[]{"TaskOld text"});
		recyclerView = findViewById(R.id.taskList);
		mAdapter = new TaskAdapter(taskList);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
		recyclerView.setAdapter(mAdapter);
		//prepareTaskData();

		/*
		taskOlds.add(new TaskOld(
				"task name",
				"description",
				Frequency.DAILY,
				Status.INCOMPLETE,
				1,
				true,
				Frequency.DAILY
		));
		*/

		//if(!getTasks(db)) sout("Error getting tasks");

	}

	private boolean getAvatar(SQLiteDatabase db) {

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
				BaseColumns._ID,
				DatabaseContract.Avatar.COLUMN_NAME_BASEIMAGE
		};

		// Filter results WHERE "text" = 'TaskOld text'
		String selection = DatabaseContract.Avatar.COLUMN_NAME_BASEIMAGE + " = ?"; //can use multiple "?" as placeholders
		String[] selectionArgs = { "Avatar text" }; //use comma separated list here

		// How you want the results sorted in the resulting Cursor
		String sortOrder =
				DatabaseContract.Avatar.COLUMN_NAME_BASEIMAGE + " DESC";

		Cursor cursor = db.query(
				DatabaseContract.Avatar.TABLE_NAME,   // The table to query
				projection,             // The array of columns to return (pass null to get all)
				selection,              // The columns for the WHERE clause
				selectionArgs,          // The values for the WHERE clause
				null,                   // don't group the rows
				null,                   // don't filter by row groups
				sortOrder               // The sort order
		);
		if(cursor.isClosed()) return false;

		//now put data into an arraylist
		while(cursor.moveToNext()) {
			//TODO: update avatarHome. can use cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.AvatarHome.COLUMN_NAME_BASEIMAGE)),
		}
		//sout("All taskOlds: " + taskOlds.toString());

		cursor.close();
		return true;
	}

	private boolean getTasks(SQLiteDatabase db) {

    	taskList = DatabaseHelper.getTasksFromDatabase(db, DatabaseContract.Task.COLUMN_NAME_TEXT + " = ?", new String[]{"TaskOld text"});

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
				BaseColumns._ID,
				DatabaseContract.Task.COLUMN_NAME_TEXT,
				DatabaseContract.Task.COLUMN_NAME_DESCRIPTION,
				DatabaseContract.Task.COLUMN_NAME_REMINDER,
				DatabaseContract.Task.COLUMN_NAME_PRIORITY,
				DatabaseContract.Task.COLUMN_NAME_FREQUENCY,
				DatabaseContract.Task.COLUMN_NAME_STATUS
		};

		// Filter results WHERE "text" = 'TaskOld text'
		String selection = DatabaseContract.Task.COLUMN_NAME_TEXT + " = ?"; //can use multiple "?" as placeholders
		String[] selectionArgs = { "TaskOld text" }; //use comma separated list here

		// How you want the results sorted in the resulting Cursor
		String sortOrder =
				DatabaseContract.Task.COLUMN_NAME_PRIORITY + " DESC";

		Cursor cursor = db.query(
				DatabaseContract.Task.TABLE_NAME,   // The table to query
				projection,             // The array of columns to return (pass null to get all)
				selection,              // The columns for the WHERE clause
				selectionArgs,          // The values for the WHERE clause
				null,                   // don't group the rows
				null,                   // don't filter by row groups
				sortOrder               // The sort order
		);
		if(cursor.isClosed()) return false;
/*
		//now put data into an arraylist
		while(cursor.moveToNext()) {
			taskOlds.add(new TaskOld(
					cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_TEXT)),
					cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_DESCRIPTION)),
					Frequency.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_FREQUENCY))),
					Status.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_STATUS))),
					cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_PRIORITY)),
					(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_REMINDER)) > 0),
					Frequency.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_FREQUENCY)))
			));
		}
		sout("All taskOlds: " + taskOlds.size());
		for(int i = 0; i< taskOlds.size(); i++){
			sout("\t" + i + ": " + taskOlds.get(i).toString());
		}
*/
		cursor.close();
		return true;
	}

	private void sout(String s) {
    	System.out.println(s);
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
				// Signal SwipeRefreshLayout to start the progress indicator
				finish();
				startActivity(getIntent());


				// Start the refresh background task.
				// This method calls setRefreshing(false) when it's finished.


				return true;
		}

        return super.onOptionsItemSelected(item);
    }

	@Override
	protected void onDestroy() {
		mDbHelper.close(); //close database connection
		super.onDestroy();
	}

}
