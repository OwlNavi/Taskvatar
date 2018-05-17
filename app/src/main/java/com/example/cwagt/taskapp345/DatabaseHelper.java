package com.example.cwagt.taskapp345;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	//https://developer.android.com/training/data-storage/sqlite

	/*
	//Writing to database
	// Gets the data repository in write mode
	SQLiteDatabase db = mDbHelper.getWritableDatabase();

	// Create a new map of values, where column names are the keys
	ContentValues values = new ContentValues();
	values.put(Task.COLUMN_NAME_TEXT, text);
	values.put(Task.COLUMN_NAME_DESCRIPTION, description);
	values.put(Task.COLUMN_NAME_REMINDER, reminder);
	values.put(Task.COLUMN_NAME_PRIORITY, importance);
	values.put(Task.COLUMN_NAME_FREQUENCY, frequency);
	values.put(Task.COLUMN_NAME_STATUS, status);

	// Insert the new row, returning the primary key value of the new row
	long newRowId = db.insert(Task.TABLE_NAME, null, values);
	*/

	/*
	//Reading from database
	SQLiteDatabase db = mDbHelper.getReadableDatabase();

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
	//can now access all item IDs

	//maybe something like...
	Task thisTask = new Task();
	while(cursor.moveToNext()) {
		thisTask.setText(cursor.getText(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_TEXT)));
		thisTask.setDescription(cursor.getText(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_DESCRIPTION)));
		thisTask.setFrequency(cursor.getText(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_FREQUENCY)));
		//...
	}
	//and now thisTask is an object that can be interacted with?

	cursor.close();
	*/

	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "Taskvatar.db";

	private static final String SQL_CREATE_TASK_TABLE =
			"CREATE TABLE " + DatabaseContract.Task.TABLE_NAME + " (" +
					DatabaseContract.Task._ID + " INTEGER PRIMARY KEY," +
					DatabaseContract.Task.COLUMN_NAME_TEXT + " " + DatabaseContract.Task.COLUMN_TYPE_TEXT + "," +
					DatabaseContract.Task.COLUMN_NAME_DESCRIPTION + " " + DatabaseContract.Task.COLUMN_TYPE_DESCRIPTION + "," +
					DatabaseContract.Task.COLUMN_NAME_FREQUENCY + " " + DatabaseContract.Task.COLUMN_TYPE_FREQUENCY + "," +
					DatabaseContract.Task.COLUMN_NAME_PRIORITY + " " + DatabaseContract.Task.COLUMN_TYPE_PRIORITY + "," +
					DatabaseContract.Task.COLUMN_NAME_STATUS + " " + DatabaseContract.Task.COLUMN_TYPE_STATUS + "," +
					DatabaseContract.Task.COLUMN_NAME_REMINDER + " " + DatabaseContract.Task.COLUMN_TYPE_REMINDER +
			")";

	private static final String SQL_CREATE_AVATAR_TABLE =
			"CREATE TABLE " + DatabaseContract.Avatar.TABLE_NAME + " (" +
					DatabaseContract.Avatar._ID + " INTEGER PRIMARY KEY," +
					DatabaseContract.Avatar.COLUMN_NAME_BASEIMAGE + " " + DatabaseContract.Avatar.COLUMN_TYPE_BASEIMAGE +
			")";

	private static final String SQL_CREATE_USER_TABLE =
			"CREATE TABLE " + DatabaseContract.User.TABLE_NAME + " (" +
					DatabaseContract.User._ID + " INTEGER PRIMARY KEY," +
					DatabaseContract.User.COLUMN_NAME_NAME + " " + DatabaseContract.User.COLUMN_TYPE_NAME +
					")";

	private static final String SQL_DELETE_TASK_TABLE =
			"DROP TABLE IF EXISTS " + DatabaseContract.Task.TABLE_NAME;

	private static final String SQL_DELETE_AVATAR_TABLE =
			"DROP TABLE IF EXISTS " + DatabaseContract.Avatar.TABLE_NAME;

	private static final String SQL_DELETE_USER_TABLE =
			"DROP TABLE IF EXISTS " + DatabaseContract.User.TABLE_NAME;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_TASK_TABLE);
		db.execSQL(SQL_CREATE_AVATAR_TABLE);
		db.execSQL(SQL_CREATE_USER_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//delete old database
		db.execSQL(SQL_DELETE_TASK_TABLE);
		db.execSQL(SQL_DELETE_AVATAR_TABLE);
		db.execSQL(SQL_DELETE_USER_TABLE);
		//create new database
		onCreate(db);
	}
}
