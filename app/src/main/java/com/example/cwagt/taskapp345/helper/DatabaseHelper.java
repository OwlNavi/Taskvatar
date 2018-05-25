package com.example.cwagt.taskapp345.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.cwagt.taskapp345.object.Enums;
import com.example.cwagt.taskapp345.object.Task;
import java.util.ArrayList;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.Task.*;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.Avatar.*;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.User.*;

public class DatabaseHelper extends SQLiteOpenHelper{

	//https://developer.android.com/training/data-storage/sqlite

	/*
	//Writing to database
	// Gets the data repository in write mode
	SQLiteDatabase db = mDbHelper.getWritableDatabase();

	// Create a new map of values, where column names are the keys
	ContentValues values = new ContentValues();
	values.put(TASK_NAME_TEXT, text);
	values.put(TASK_NAME_DESCRIPTION, description);
	values.put(TASK_NAME_REMINDER, reminder);
	values.put(TASK_NAME_PRIORITY, importance);
	values.put(TASK_NAME_FREQUENCY, frequency);
	values.put(TASK_NAME_STATUS, status);
	values.put(TASK_NAME_TIME, time);

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
			TASK_NAME_TEXT,
			TASK_NAME_DESCRIPTION,
			TASK_NAME_REMINDER,
			TASK_NAME_PRIORITY,
			TASK_NAME_FREQUENCY,
			TASK_NAME_STATUS,
			TASK_NAME_TIME
	};

	// Filter results WHERE "text" = 'Task text'
	String selection = TASK_NAME_TEXT + " = ?"; //can use multiple "?" as placeholders
	String[] selectionArgs = { "Task text" }; //use comma separated list here

	// How you want the results sorted in the resulting Cursor
	String sortOrder =
			TASK_NAME_TEXT + " DESC";

	Cursor cursor = db.query(
			DatabaseColumnNames.Task.TABLE_NAME,   // The table to query
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
	  long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseColumnNames.Task._ID));
	  itemIds.add(itemId);
	}
	//can now access all item IDs

	//maybe something like...
	Task thisTask = new Task();
	while(cursor.moveToNext()) {
		thisTask.setText(cursor.getText(cursor.getColumnIndexOrThrow(TASK_NAME_TEXT)));
		thisTask.setDescription(cursor.getText(cursor.getColumnIndexOrThrow(TASK_NAME_DESCRIPTION)));
		thisTask.setFrequency(cursor.getText(cursor.getColumnIndexOrThrow(TASK_NAME_FREQUENCY)));
		//...
	}
	//and now thisTask is an object that can be interacted with?

	cursor.close();
	*/

	private static final int DATABASE_VERSION = 3;
	private static final String DATABASE_NAME = "Taskvatar.db";

	private static final String SQL_CREATE_TASK_TABLE =
			"CREATE TABLE " + DatabaseColumnNames.Task.TABLE_NAME + " (" +
					DatabaseColumnNames.Task._ID + " INTEGER PRIMARY KEY," +
					TASK_NAME_TEXT + " " + TASK_TYPE_TEXT + "," +
					TASK_NAME_DESCRIPTION + " " + TASK_TYPE_DESCRIPTION + "," +
					TASK_NAME_FREQUENCY + " " + TASK_TYPE_FREQUENCY + "," +
					TASK_NAME_PRIORITY + " " + TASK_TYPE_PRIORITY + "," +
					TASK_NAME_STATUS + " " + TASK_TYPE_STATUS + "," +
					TASK_NAME_REMINDER + " " + TASK_TYPE_REMINDER + "," +
					TASK_NAME_TIME + " " + TASK_TYPE_TIME +
			")";

	private static final String SQL_CREATE_AVATAR_TABLE =
			"CREATE TABLE " + DatabaseColumnNames.Avatar.TABLE_NAME + " (" +
					DatabaseColumnNames.Avatar._ID + " INTEGER PRIMARY KEY," +
					AVATAR_NAME_BASEIMAGE + " " + AVATAR_TYPE_BASEIMAGE +
			")";

	private static final String SQL_CREATE_USER_TABLE =
			"CREATE TABLE " + DatabaseColumnNames.User.TABLE_NAME + " (" +
					DatabaseColumnNames.User._ID + " INTEGER PRIMARY KEY," +
					USER_NAME_NAME + " " + USER_TYPE_NAME +
			")";

	private static final String SQL_DELETE_TASK_TABLE =
			"DROP TABLE IF EXISTS " + DatabaseColumnNames.Task.TABLE_NAME;

	private static final String SQL_DELETE_AVATAR_TABLE =
			"DROP TABLE IF EXISTS " + DatabaseColumnNames.Avatar.TABLE_NAME;

	private static final String SQL_DELETE_USER_TABLE =
			"DROP TABLE IF EXISTS " + DatabaseColumnNames.User.TABLE_NAME;

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
	 * @param context the context of the activity (use 'this')
	 * @param task the task to write
	 * @return long the ID of the newly inserted row, or -1 on failure
	 */
	public static long writeTaskToDatabase(Context context, Task task){
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TASK_NAME_TEXT, task.getName());
		values.put(TASK_NAME_DESCRIPTION, task.getDescription());
		values.put(TASK_NAME_REMINDER, task.getReminder());
		values.put(TASK_NAME_PRIORITY, task.getPriority());
		values.put(TASK_NAME_FREQUENCY, task.getFrequency().name());
		values.put(TASK_NAME_STATUS, task.getStatus().name());
		values.put(TASK_NAME_TIME, task.getTime());

		// Insert the new row, returning the primary key value of the new row
		long newRowId = -1; //allows cheating e.g. `if(writeTaskToDatabase(){...}`
		try {
			newRowId = db.insert(DatabaseColumnNames.Task.TABLE_NAME, null, values);
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
		//String selection = TASK_NAME_TEXT + " = ?"; //can use multiple "?" as placeholders
		//String[] selectionArgs = { "Task text" }; //use comma separated list here

		String[] projection = {
				TASK_NAME_TEXT,
				TASK_NAME_DESCRIPTION,
				TASK_NAME_REMINDER,
				TASK_NAME_PRIORITY,
				TASK_NAME_FREQUENCY,
				TASK_NAME_STATUS,
				TASK_NAME_TIME
		};

		// How you want the results sorted in the resulting Cursor
		String sortOrder =
				TASK_NAME_TEXT + " DESC";

		Cursor cursor = db.query(
				DatabaseColumnNames.Task.TABLE_NAME,   // The table to query
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
				cursor.getString(cursor.getColumnIndexOrThrow(TASK_NAME_TEXT)),
				cursor.getString(cursor.getColumnIndexOrThrow(TASK_NAME_DESCRIPTION)),
				cursor.getString(cursor.getColumnIndexOrThrow(TASK_NAME_TIME)),
				Enums.Frequency.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(TASK_NAME_FREQUENCY))),
				cursor.getInt(cursor.getColumnIndexOrThrow(TASK_NAME_REMINDER)) > 0,
				Enums.Status.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(TASK_NAME_STATUS))),
				cursor.getInt(cursor.getColumnIndexOrThrow(TASK_NAME_PRIORITY))
			);
			tasks.add(thisTask);
		}
/*
		if(tasks.size() == 0){
			//tasks = generateDummyData();
			Task newTask = new Task("new task", "new desc", "12:00");
			long newID = writeTaskToDatabase(context, newTask);
			if(newID > 0) sout("New task created with ID: " + newID);
			else sout("New task could not be created");
			tasks.add(newTask);
		}
*/
		cursor.close();
		return tasks;
	}

	private static void sout(String string) {
		System.out.println(string);
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
