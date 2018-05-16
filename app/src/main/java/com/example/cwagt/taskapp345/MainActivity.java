package com.example.cwagt.taskapp345;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageView avatar;
	DatabaseHelper mDbHelper = new DatabaseHelper(this); //needs SQLiteOpenHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        avatar = findViewById(R.id.avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AvatarHome.class);
                startActivity(intent);
            }

        });

		//Reading from database
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

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

		//now put data into an arraylist
		List itemIds = new ArrayList<>();
		while(cursor.moveToNext()) {
			long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.Task._ID));
			itemIds.add(itemId);
		}

		cursor.close();
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
