package com.example.cwagt.taskapp345.view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cwagt.taskapp345.DatabaseContract;
import com.example.cwagt.taskapp345.DatabaseHelper;
import com.example.cwagt.taskapp345.Enums.Frequency;
import com.example.cwagt.taskapp345.Enums.Status;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.object.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    View avatarImage;
    Avatar avatar = new Avatar();
    RecyclerView taskList;
	List<Task> tasks = new ArrayList<>();
	public DatabaseHelper mDbHelper = new DatabaseHelper(this); //needs SQLiteOpenHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getColor(android.R.color.white));

		//Reading from database
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

        avatarImage = findViewById(R.id.avatar);
        avatarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Avatar.class);
                startActivity(intent);
            }

        });
        if(!getAvatar(db)) sout("Error getting avatar");

		taskList = findViewById(R.id.taskList);
		if(!getTasks(db)) sout("Error getting tasks");

	}

	private boolean getAvatar(SQLiteDatabase db) {

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
				BaseColumns._ID,
				DatabaseContract.Avatar.COLUMN_NAME_BASEIMAGE
		};

		// Filter results WHERE "text" = 'Task text'
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
			//TODO: update avatar. can use cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Avatar.COLUMN_NAME_BASEIMAGE)),
		}
		//sout("All tasks: " + tasks.toString());

		cursor.close();
		return true;
	}

	private boolean getTasks(SQLiteDatabase db) {

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
				BaseColumns._ID,
				DatabaseContract.Task.COLUMN_NAME_TEXT,
				DatabaseContract.Task.COLUMN_NAME_DESCRIPTION,
				DatabaseContract.Task.COLUMN_NAME_REMINDER,
				DatabaseContract.Task.COLUMN_NAME_IMPORTANCE,
				DatabaseContract.Task.COLUMN_NAME_FREQUENCY,
				DatabaseContract.Task.COLUMN_NAME_STATUS
		};

		// Filter results WHERE "text" = 'Task text'
		String selection = DatabaseContract.Task.COLUMN_NAME_TEXT + " = ?"; //can use multiple "?" as placeholders
		String[] selectionArgs = { "Task text" }; //use comma separated list here

		// How you want the results sorted in the resulting Cursor
		String sortOrder =
				DatabaseContract.Task.COLUMN_NAME_TEXT + " DESC";

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

		//now put data into an arraylist
		while(cursor.moveToNext()) {
			tasks.add(new Task(
					cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_TEXT)),
					cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_DESCRIPTION)),
					Frequency.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_FREQUENCY))),
					Status.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_STATUS))),
					cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_IMPORTANCE)),
					(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_REMINDER)) > 0),
					Frequency.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Task.COLUMN_NAME_FREQUENCY)))
			));
		}
		sout("All tasks: " + tasks.toString());

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
