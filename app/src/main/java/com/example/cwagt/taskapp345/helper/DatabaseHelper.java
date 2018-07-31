package com.example.cwagt.taskapp345.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cwagt.taskapp345.object.Avatar;
import com.example.cwagt.taskapp345.object.Enums;
import com.example.cwagt.taskapp345.object.Task;
import com.example.cwagt.taskapp345.object.User;

import java.util.ArrayList;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.Task.*;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.Avatar.*;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.User.*;

/**
 * This is the database helper, which abstracts the database. You just call the methods and the DatabaseHelper class will do it for you. Be sure to use return types sensibly
 * The writeX methods return the rowID
 * If you add/delete/change fields, don't forget to increment the database version
 */
public class DatabaseHelper extends SQLiteOpenHelper{

	//https://developer.android.com/training/data-storage/sqlite

	private static final int DATABASE_VERSION = 4;
	private static final String DATABASE_NAME = "Taskvatar.db";

	private static final String SQL_CREATE_TASK_TABLE =
			"CREATE TABLE " + DatabaseColumnNames.Task.TABLE_NAME + " (" +
					DatabaseColumnNames.Task._ID + " INTEGER PRIMARY KEY" +
					", " + TASK_NAME_TEXT + " " + TASK_TYPE_TEXT +
					", " + TASK_NAME_DESCRIPTION + " " + TASK_TYPE_DESCRIPTION +
					", " + TASK_NAME_FREQUENCY + " " + TASK_TYPE_FREQUENCY +
					", " + TASK_NAME_PRIORITY + " " + TASK_TYPE_PRIORITY +
					", " + TASK_NAME_STATUS + " " + TASK_TYPE_STATUS +
					", " + TASK_NAME_REMINDER + " " + TASK_TYPE_REMINDER +
					", " + TASK_NAME_TIME + " " + TASK_TYPE_TIME +
			")";
	//If you want to add a field, dont forget to change the field names and types in DatabaseColumnNames.java

	private static final String SQL_CREATE_AVATAR_TABLE =
			"CREATE TABLE " + DatabaseColumnNames.Avatar.TABLE_NAME + " (" +
					DatabaseColumnNames.Avatar._ID + " INTEGER PRIMARY KEY" +
					", " + AVATAR_NAME_ID + " " + AVATAR_TYPE_ID +
					", " + AVATAR_NAME_LEFT_ARM_ROTATION + " " + AVATAR_TYPE_LEFT_ARM_ROTATION +
					", " + AVATAR_NAME_RIGHT_ARM_ROTATION + " " + AVATAR_TYPE_RIGHT_ARM_ROTATION +
					", " + AVATAR_NAME_LEFT_LEG_ROTATION + " " + AVATAR_TYPE_LEFT_LEG_ROTATION +
					", " + AVATAR_NAME_RIGHT_LEG_ROTATION + " " + AVATAR_TYPE_RIGHT_LEG_ROTATION +
			")";

	private static final String SQL_CREATE_USER_TABLE =
			"CREATE TABLE " + DatabaseColumnNames.User.TABLE_NAME + " (" +
					DatabaseColumnNames.User._ID + " INTEGER PRIMARY KEY" +
					"," + USER_NAME_NAME + " " + USER_TYPE_NAME +
					"," + USER_NAME_ID + " " + USER_TYPE_ID +
					"," + USER_NAME_DESCRIPTION + " " + USER_TYPE_DESCRIPTION +
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

	/* Tasks */

	public static long writeTaskToDatabase(Context context, Task task){
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		if(checkDatabase(db)) {

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
			} catch (Exception e) {
				e.printStackTrace();
			}
			return newRowId;
		}else{
			sout("Error: could not open database for writing");
			return -1;
		}
	}

	public static int deleteTaskFromDatabase(Context context, Task task){
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		int success = -1;

		if(checkDatabase(db)) {

			String whereClause = TASK_NAME_TEXT + " = ?" +
					" AND " + TASK_NAME_DESCRIPTION + " = ?" +
					" AND " + TASK_NAME_TIME + " = ?";

			String[] values = new String[3];
			values[0] = task.getName();
			values[1] = task.getDescription();
			values[2] = task.getTime();

			try {
				success = db.delete(
						DatabaseColumnNames.Task.TABLE_NAME,
						whereClause,
						values
				);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else{
			sout("Error: could not open database for deleting");
		}
		return success;
	}

	public static ArrayList<Task> getTasksFromDatabase(Context context, String selection, String[] selectionArgs) {
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		ArrayList<Task> tasks = new ArrayList<>();
		if(checkDatabase(db)) {

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
					TASK_NAME_PRIORITY +
					"," + TASK_NAME_TIME +
					"," + TASK_NAME_TEXT
			;

			Cursor cursor = db.query(
					DatabaseColumnNames.Task.TABLE_NAME,   // The table to query
					projection,             // The array of columns to return (pass null to get all)
					selection,              // The columns for the WHERE clause
					selectionArgs,          // The values for the WHERE clause
					null,                   // don't group the rows
					null,                   // don't filter by row groups
					sortOrder               // The sort order
			);

			while (cursor.moveToNext()) {
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

			if (tasks.size() == 0) {
				tasks = generateDummyData();
			}

			cursor.close();

		}else{
			sout("Error: could not open database for reading");
		}
		return tasks;
	}

	public static ArrayList<Task> getAllTasksFromDatabase(Context context) {
		return getTasksFromDatabase(context, "", new String[]{});
	}

	private static ArrayList<Task> generateDummyData() {
		ArrayList<Task> taskList = new ArrayList<>();
		Task task;

		for(int i=0; i<10; i++){
			task = new Task("Example task " + i, "Description", "12:00 am");
			taskList.add(task);
		}

		return taskList;

	}

	/* Users */

	public static long writeUserToDatabase(Context context, User user){
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		if(checkDatabase(db)) {

			ContentValues values = new ContentValues();
			values.put(USER_NAME_NAME, user.getUserName());
			values.put(USER_NAME_ID, user.getUserID());
			values.put(USER_NAME_DESCRIPTION, user.getUserDescription());

			// Insert the new row, returning the primary key value of the new row
			long newRowId = -1; //allows cheating e.g. `if(writeTaskToDatabase(){...}`
			try {
				newRowId = db.insert(DatabaseColumnNames.User.TABLE_NAME, null, values);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return newRowId;
		}else{
			sout("Error: could not open database for writing");
			return -1;
		}
	}

	public static ArrayList<User> getUsersFromDatabase(Context context, String selection, String[] selectionArgs) {
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		ArrayList<User> users = new ArrayList<>();
		if(checkDatabase(db)) {

			//String selection = TASK_NAME_TEXT + " = ?"; //can use multiple "?" as placeholders
			//String[] selectionArgs = { "Task text" }; //use comma separated list here

			String[] projection = {
					USER_NAME_NAME,
					USER_NAME_ID,
					USER_NAME_DESCRIPTION
			};

			Cursor cursor = db.query(
					DatabaseColumnNames.User.TABLE_NAME,   // The table to query
					projection,             // The array of columns to return (pass null to get all)
					selection,              // The columns for the WHERE clause
					selectionArgs,          // The values for the WHERE clause
					null,                   // don't group the rows
					null,                   // don't filter by row groups
					USER_NAME_NAME               // The sort order
			);

			while (cursor.moveToNext()) {
				User thisUser = new User(
						cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME_NAME)),
						cursor.getInt(cursor.getColumnIndexOrThrow(USER_NAME_ID)),
						cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME_DESCRIPTION))
				);
				users.add(thisUser);
			}
			cursor.close();

		}else{
			sout("Error: could not open database for reading");
		}
		return users;
	}

	public static ArrayList<User> getAllUsersFromDatabase(Context context) {
		return getUsersFromDatabase(context, "", new String[]{});
	}

	public static int deleteUserFromDatabase(Context context, User user){
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		int success = -1;

		if(checkDatabase(db)) {

			String whereClause = USER_NAME_NAME + " = ?" +
					" AND " + USER_NAME_ID + " = ?" +
					" AND " + USER_NAME_DESCRIPTION + " = ?";

			String[] values = new String[3];
			values[0] = user.getUserName();
			values[1] = "" + user.getUserID();
			values[2] = user.getUserDescription();

			try {
				success = db.delete(
						DatabaseColumnNames.User.TABLE_NAME,
						whereClause,
						values
				);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else{
			sout("Error: could not open database for deleting");
		}
		return success;
	}

	/* Avatars */

	public static long writeAvatarToDatabase(Context context, Avatar avatar){
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		if(checkDatabase(db)) {

			ContentValues values = new ContentValues();
			values.put(AVATAR_NAME_ID, avatar.getID());
			values.put(AVATAR_NAME_LEFT_ARM_ROTATION, avatar.getLeftArmRotation());
			values.put(AVATAR_NAME_RIGHT_ARM_ROTATION, avatar.getRightArmRotation());
			values.put(AVATAR_NAME_LEFT_LEG_ROTATION, avatar.getLeftLegRotation());
			values.put(AVATAR_NAME_RIGHT_LEG_ROTATION, avatar.getRightLegRotation());

			// Insert the new row, returning the primary key value of the new row
			long newRowId = -1; //allows cheating e.g. `if(writeTaskToDatabase(){...}`
			try {
				newRowId = db.insert(DatabaseColumnNames.Avatar.TABLE_NAME, null, values);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return newRowId;
		}else{
			sout("Error: could not open database for writing");
			return -1;
		}
	}

	public static ArrayList<Avatar> getAvatarsFromDatabase(Context context, User user) {
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		ArrayList<Avatar> avatars = new ArrayList<>();
		String selection = AVATAR_NAME_USER + " = ?";
		String[] selectionArgs = new String[]{String.valueOf(user.getUserID())};
		if(checkDatabase(db)) {

			//String selection = TASK_NAME_TEXT + " = ?"; //can use multiple "?" as placeholders
			//String[] selectionArgs = { "Task text" }; //use comma separated list here

			String[] projection = {
					AVATAR_NAME_ID,
					AVATAR_NAME_LEFT_ARM_ROTATION,
					AVATAR_NAME_RIGHT_ARM_ROTATION,
					AVATAR_NAME_LEFT_LEG_ROTATION,
					AVATAR_NAME_RIGHT_LEG_ROTATION
			};

			Cursor cursor = db.query(
					DatabaseColumnNames.User.TABLE_NAME,   // The table to query
					projection,             // The array of columns to return (pass null to get all)
					selection,              // The columns for the WHERE clause
					selectionArgs,          // The values for the WHERE clause
					null,                   // don't group the rows
					null,                   // don't filter by row groups
					AVATAR_NAME_ID               // The sort order
			);

			while (cursor.moveToNext()) {
				Avatar thisAvatar = new Avatar(
						cursor.getFloat(cursor.getColumnIndexOrThrow(AVATAR_NAME_LEFT_ARM_ROTATION)),
						cursor.getFloat(cursor.getColumnIndexOrThrow(AVATAR_NAME_RIGHT_ARM_ROTATION)),
						cursor.getFloat(cursor.getColumnIndexOrThrow(AVATAR_NAME_LEFT_LEG_ROTATION)),
						cursor.getFloat(cursor.getColumnIndexOrThrow(AVATAR_NAME_RIGHT_LEG_ROTATION)),
						user
				);
				avatars.add(thisAvatar);
			}
			cursor.close();

		}else{
			sout("Error: could not open database for reading");
		}
		return avatars;
	}

	public static int deleteAvatarFromDatabase(Context context, Avatar avatar){
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		int success = -1;

		if(checkDatabase(db)) {

			String whereClause = AVATAR_NAME_ID + " = ?" +
					" AND " + AVATAR_NAME_LEFT_ARM_ROTATION + " = ?" +
					" AND " + AVATAR_NAME_RIGHT_ARM_ROTATION + " = ?" +
					" AND " + AVATAR_NAME_LEFT_LEG_ROTATION + " = ?" +
					" AND " + AVATAR_NAME_RIGHT_LEG_ROTATION + " = ?"
			;

			String[] values = new String[5];
			values[0] = "" + avatar.getID();
			values[1] = "" + avatar.getLeftArmRotation();
			values[2] = "" + avatar.getRightArmRotation();
			values[3] = "" + avatar.getLeftLegRotation();
			values[4] = "" + avatar.getRightLegRotation();

			try {
				success = db.delete(
						DatabaseColumnNames.User.TABLE_NAME,
						whereClause,
						values
				);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else{
			sout("Error: could not open database for deleting");
		}
		return success;
	}

	/* Auxiliary */

	private static boolean checkDatabase(SQLiteDatabase db) {
		if(db == null){
			sout("Error: could not open database");
			return false;
		}else {
			return true;
		}
	}

	public static void closeDatabase(Context context) {
		DatabaseHelper mDbHelper = new DatabaseHelper(context);
		mDbHelper.close(); //close database connection
	}

	private static void sout(String string) {
		System.out.println(string);
	}

}
