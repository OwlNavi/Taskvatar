package com.example.cwagt.taskapp345;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	/*
	//Writing to database
	// Gets the data repository in write mode
	SQLiteDatabase db = mDbHelper.getWritableDatabase();

	// Create a new map of values, where column names are the keys
	ContentValues values = new ContentValues();
	values.put(TaskEntry.COLUMN_NAME_TEXT, text);
	values.put(TaskEntry.COLUMN_NAME_DESCRIPTION, description);
	values.put(TaskEntry.COLUMN_NAME_REMINDER, reminder);
	values.put(TaskEntry.COLUMN_NAME_IMPORTANCE, importance);
	values.put(TaskEntry.COLUMN_NAME_FREQUENCY, frequency);
	values.put(TaskEntry.COLUMN_NAME_STATUS, status);

	// Insert the new row, returning the primary key value of the new row
	long newRowId = db.insert(TaskEntry.TABLE_NAME, null, values);
	*/

	/*
	//Reading from database
	SQLiteDatabase db = mDbHelper.getReadableDatabase();

	// Define a projection that specifies which columns from the database
	// you will actually use after this query.
	String[] projection = {
			BaseColumns._ID,
			DatabaseContract.TaskEntry.COLUMN_NAME_TEXT,
			DatabaseContract.TaskEntry.COLUMN_NAME_DESCRIPTION,
			DatabaseContract.TaskEntry.COLUMN_NAME_REMINDER,
			DatabaseContract.TaskEntry.COLUMN_NAME_IMPORTANCE,
			DatabaseContract.TaskEntry.COLUMN_NAME_FREQUENCY,
			DatabaseContract.TaskEntry.COLUMN_NAME_STATUS
	};

	// Filter results WHERE "text" = 'Task text'
	String selection = FeedEntry.COLUMN_NAME_TEXT + " = ?"; //can use multiple "?" as placeholders
	String[] selectionArgs = { "Task text" }; //use comma separated list here

	// How you want the results sorted in the resulting Cursor
	String sortOrder =
			DatabaseContract.TaskEntry.COLUMN_NAME_TEXT + " DESC";

	Cursor cursor = db.query(
			TaskEntry.TABLE_NAME,   // The table to query
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
	  long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(TaskEntry._ID));
	  itemIds.add(itemId);
	}
	//can now access all item IDs

	//maybe something like...
	Task thisTask = new Task();
	while(cursor.moveToNext()) {
		thisTask.setText(cursor.getText(cursor.getColumnIndexOrThrow(TaskEntry.COLUMN_NAME_TEXT)));
	}
	//and now thisTask is an object that can be interacted with?

	cursor.close();
	*/

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Taskvatar.db";

	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + DatabaseContract.TaskEntry.TABLE_NAME + " (" +
					DatabaseContract.TaskEntry._ID + " INTEGER PRIMARY KEY," +
					DatabaseContract.TaskEntry.COLUMN_NAME_TEXT + " TEXT," +
					DatabaseContract.TaskEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
					DatabaseContract.TaskEntry.COLUMN_NAME_FREQUENCY + " TEXT," +
					DatabaseContract.TaskEntry.COLUMN_NAME_IMPORTANCE + " NUMERIC," +
					DatabaseContract.TaskEntry.COLUMN_NAME_STATUS + " TEXT," +
					DatabaseContract.TaskEntry.COLUMN_NAME_REMINDER + " INTEGER" +
			")";

	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + DatabaseContract.TaskEntry.TABLE_NAME;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//delete old database
		db.execSQL(SQL_DELETE_ENTRIES);
		//create new database
		onCreate(db);
	}
}
