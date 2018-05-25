package com.example.cwagt.taskapp345.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.cwagt.taskapp345.object.Enums;
import com.example.cwagt.taskapp345.object.Task;
import java.util.ArrayList;
import static com.example.cwagt.taskapp345.helper.DatabaseContract.Task.*;
import static com.example.cwagt.taskapp345.helper.DatabaseContract.Avatar.*;
import static com.example.cwagt.taskapp345.helper.DatabaseContract.User.*;

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
			DatabaseContract.Task.COLUMN_NAME_STATUS,
			DatabaseContract.Task.COLUMN_NAME_TIME
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

	private static final int DATABASE_VERSION = 3;
	private static final String DATABASE_NAME = "Taskvatar.db";

	private static final String SQL_CREATE_TASK_TABLE =
			"CREATE TABLE " + DatabaseContract.Task.TABLE_NAME + " (" +
					DatabaseContract.Task._ID + " INTEGER PRIMARY KEY," +
					COLUMN_NAME_TEXT + " " + COLUMN_TYPE_TEXT + "," +
					COLUMN_NAME_DESCRIPTION + " " + COLUMN_TYPE_DESCRIPTION + "," +
					COLUMN_NAME_FREQUENCY + " " + COLUMN_TYPE_FREQUENCY + "," +
					COLUMN_NAME_PRIORITY + " " + COLUMN_TYPE_PRIORITY + "," +
					COLUMN_NAME_STATUS + " " + COLUMN_TYPE_STATUS + "," +
					COLUMN_NAME_REMINDER + " " + COLUMN_TYPE_REMINDER + "," +
					COLUMN_NAME_TIME + " " + COLUMN_TYPE_TIME +
			")";

	private static final String SQL_CREATE_AVATAR_TABLE =
			"CREATE TABLE " + DatabaseContract.Avatar.TABLE_NAME + " (" +
					DatabaseContract.Avatar._ID + " INTEGER PRIMARY KEY," +
					COLUMN_NAME_BASEIMAGE + " " + COLUMN_TYPE_BASEIMAGE +
			")";

	private static final String SQL_CREATE_USER_TABLE =
			"CREATE TABLE " + DatabaseContract.User.TABLE_NAME + " (" +
					DatabaseContract.User._ID + " INTEGER PRIMARY KEY," +
					COLUMN_NAME_NAME + " " + COLUMN_TYPE_NAME +
			")";

	private static final String SQL_DELETE_TASK_TABLE =
			"DROP TABLE IF EXISTS " + DatabaseContract.Task.TABLE_NAME;

	private static final String SQL_DELETE_AVATAR_TABLE =
			"DROP TABLE IF EXISTS " + DatabaseContract.Avatar.TABLE_NAME;

	private static final String SQL_DELETE_USER_TABLE =
			"DROP TABLE IF EXISTS " + DatabaseContract.User.TABLE_NAME;

	private DatabaseHelper(Context context) {
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

	/** Creates a new row in the database from a task
	 * @param db the database
	 * @param task the task to write
	 * @return long the ID of the newly inserted row, or -1 on failure
	 */
	public long writeTaskToDatabase(SQLiteDatabase db, Task task){
		//Writing to database

		String name = task.getName();
		String description = task.getDescription();
		Boolean reminder = task.getReminder();
		Integer priority = task.getPriority();
		Enums.Frequency frequency = task.getFrequency();
		Enums.Status status = task.getStatus();
		String time = task.getTime();

		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME_TEXT, name);
		values.put(COLUMN_NAME_DESCRIPTION, description);
		values.put(COLUMN_NAME_REMINDER, reminder);
		values.put(COLUMN_NAME_PRIORITY, priority);
		values.put(COLUMN_NAME_FREQUENCY, frequency.name());
		values.put(COLUMN_NAME_STATUS, status.name());
		values.put(COLUMN_NAME_TIME, time);

		// Insert the new row, returning the primary key value of the new row
		long newRowId = -1; //allows cheating e.g. `if(writeTaskToDatabase(){...}`
		try {
			newRowId = db.insert(DatabaseContract.Task.TABLE_NAME, null, values);
		}catch(Exception e){
			e.printStackTrace();
		}
		return newRowId;
	}

	/** gets an arraylist of tasks
	 * @param context the context of the activity (use "this")
	 * @param selection COLUMN_NAME_TEXT + " = ?", can use multiple ? as placeholders
	 * @param selectionArgs Replcase "?" with what? (e.g. Task text)
	 * @return arraylist of tasks
	 */
	public static ArrayList<Task> getTasksFromDatabase(Context context, String selection, String[] selectionArgs) {
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		//String selection = DatabaseContract.Task.COLUMN_NAME_TEXT + " = ?"; //can use multiple "?" as placeholders
		//String[] selectionArgs = { "Task text" }; //use comma separated list here

		String[] projection = {
				COLUMN_NAME_TEXT,
				COLUMN_NAME_DESCRIPTION,
				COLUMN_NAME_REMINDER,
				COLUMN_NAME_PRIORITY,
				COLUMN_NAME_FREQUENCY,
				COLUMN_NAME_STATUS,
				COLUMN_NAME_TIME
		};

		// How you want the results sorted in the resulting Cursor
		String sortOrder =
				COLUMN_NAME_TEXT + " DESC";

		Cursor cursor = db.query(
				DatabaseContract.Task.TABLE_NAME,   // The table to query
				projection,             // The array of columns to return (pass null to get all)
				selection,              // The columns for the WHERE clause
				selectionArgs,          // The values for the WHERE clause
				null,                   // don't group the rows
				null,                   // don't filter by row groups
				sortOrder               // The sort order
		);

		ArrayList<Task> tasks = new ArrayList<>();
		while(cursor.moveToNext()) {
			Task thisTask = new Task(
				cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TEXT)),
				cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_DESCRIPTION)),
				cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TIME)),
				Enums.Frequency.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_FREQUENCY))),
				cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_REMINDER)) > 0,
				Enums.Status.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_STATUS))),
				cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_PRIORITY))
			);
			tasks.add(thisTask);
		}

		if(tasks.size() == 0) tasks = generateDummyData();

		cursor.close();
		return tasks;
	}

	private static ArrayList<Task> generateDummyData() {
		ArrayList<Task> taskList = new ArrayList<>();
		Task task;

		task = new Task("Get up", "Out of bed", "7:00 am");
		taskList.add(task);

		task = new Task("Have breakfast", "Choose something yummy", "7:30 am");
		taskList.add(task);

		task = new Task("Get dressed", "Check weather first", "8:00 am");
		taskList.add(task);

		task = new Task("Have medication", "1 big pill, 1 small pill", "8:30 am");
		taskList.add(task);

		task = new Task("Do homework", "Check spelling", "3:30 pm");
		taskList.add(task);

		task = new Task("Take rubbish out", "Recycling goes in yellow bin", "4:00 pm");
		taskList.add(task);

		task = new Task("Put lunch box out", "Put on bench", "4:30 pm");
		taskList.add(task);

		task = new Task("Do dishes", "Wash or dry", "6:00 pm");
		taskList.add(task);

		task = new Task("Put PJs on", "Put old clothes in hamper", "7:00 pm");
		taskList.add(task);

		task = new Task("Brush teeth", "Put toothbrush away", "8:00 pm");
		taskList.add(task);

		task = new Task("Go to bed", "Sleep tight", "9:00 pm");
		taskList.add(task);

		return taskList;

	}

	public static void closeDatabase(Context context) {
		DatabaseHelper mDbHelper = new DatabaseHelper(context);
		mDbHelper.close(); //close database connection
	}
}
