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
 * If you add/delete/change ANYTHING, don't forget to increment the database version
 */
public class DatabaseHelper extends SQLiteOpenHelper{

	//https://developer.android.com/training/data-storage/sqlite

	private static final int DATABASE_VERSION = 5;
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

					", " + AVATAR_NAME_BASE + " " + AVATAR_TYPE_BASE +
					", " + AVATAR_NAME_LEFT_ARM + " " + AVATAR_TYPE_LEFT_ARM +
					", " + AVATAR_NAME_RIGHT_ARM + " " + AVATAR_TYPE_RIGHT_ARM +
					", " + AVATAR_NAME_LEFT_LEG + " " + AVATAR_TYPE_LEFT_LEG +
					", " + AVATAR_NAME_RIGHT_LEG + " " + AVATAR_TYPE_RIGHT_LEG +

					", " + AVATAR_NAME_USER + " " + AVATAR_TYPE_USER +

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

	public static long createTask(Context context, Task task){
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

	public static ArrayList<Task> readTasks(Context context, String selection, String[] selectionArgs) {
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

	public static ArrayList<Task> readAllTasks(Context context) {
		return readTasks(context, "", new String[]{});
	}

	public static boolean updateTask(Context context, Integer Id, Task task){
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		Boolean success = false;
		if(checkDatabase(db)) {

			ContentValues values = new ContentValues();
			values.put(TASK_NAME_TEXT, task.getName());
			values.put(TASK_NAME_DESCRIPTION, task.getDescription());
			values.put(TASK_NAME_REMINDER, task.getReminder());
			values.put(TASK_NAME_PRIORITY, task.getPriority());
			values.put(TASK_NAME_FREQUENCY, task.getFrequency().name());
			values.put(TASK_NAME_STATUS, task.getStatus().name());
			values.put(TASK_NAME_TIME, task.getTime());

			try {
				int count = db.update(DatabaseColumnNames.Task.TABLE_NAME, values, _ID + " = ?", new String[]{String.valueOf(Id)});
				if(count == 1) success = true;
				else{
					//sout("[DatabaseHelper.updateTask] There were " + count + " rows affected");
					throw new Exception("[DatabaseHelper.updateTask] There were " + count + " rows affected");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else{
			sout("Error: could not open database for writing");
			//throw new Exception("Error: could not open database for writing");
			success = false;
		}
		return success;
	}

	public static int deleteTask(Context context, Task task){
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

	public static long createUser(Context context, User user){
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

	public static ArrayList<User> readUsers(Context context, String selection, String[] selectionArgs) {
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

	public static ArrayList<User> readAllUsers(Context context) {
		return readUsers(context, "", new String[]{});
	}

	public static boolean updateUser(Context context, Integer Id, User user){
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		boolean success = false;
		int count;
		if(checkDatabase(db)) {

			ContentValues values = new ContentValues();
			values.put(USER_NAME_NAME, user.getUserName());
			values.put(USER_NAME_ID, user.getUserID());
			values.put(USER_NAME_DESCRIPTION, user.getUserDescription());

			try {
				count = db.update(DatabaseColumnNames.User.TABLE_NAME, values,_ID + " = ?", new String[]{String.valueOf(Id)});
				if(count == 1) success = true;
				else throw new Exception("[DatabaseHelper.updateUser] db.update method returned " + count + " rows");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else{
			sout("Error: could not open database for writing");
		}
		return success;
	}

	public static int deleteUser(Context context, User user){
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

	public static long createAvatar(Context context, Avatar avatar){
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		if(checkDatabase(db)) {

			ContentValues values = new ContentValues();
			values.put(AVATAR_NAME_ID, avatar.getID());

			values.put(AVATAR_NAME_BASE, avatar.getBase());
			values.put(AVATAR_NAME_LEFT_ARM, avatar.getLeftArm());
			values.put(AVATAR_NAME_RIGHT_ARM, avatar.getRightArm());
			values.put(AVATAR_NAME_LEFT_LEG, avatar.getLeftLeg());
			values.put(AVATAR_NAME_RIGHT_LEG, avatar.getRightLeg());

			values.put(AVATAR_NAME_USER, avatar.getUserID());

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

	public static Avatar readAvatar(Context context, User user) {
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		ArrayList<Avatar> avatars = new ArrayList<>();
		String selection = AVATAR_NAME_USER + " = ?";
		String[] selectionArgs = new String[]{String.valueOf(user.getUserID())};
		Avatar thisAvatar = null;
		if(checkDatabase(db)) {

			Cursor cursor = db.query(
					DatabaseColumnNames.Avatar.TABLE_NAME,   // The table to query
					null,             // The array of columns to return (pass null to get all)
					selection,              // The columns for the WHERE clause
					selectionArgs,          // The values for the WHERE clause
					null,                   // don't group the rows
					null,                   // don't filter by row groups
					AVATAR_NAME_ID               // The sort order
			);

			//TODO: Check there is only one avatar. Currently returns the last avatar found

			while (cursor.moveToNext()) {
				thisAvatar = new Avatar(
						cursor.getInt(cursor.getColumnIndexOrThrow(AVATAR_NAME_ID)),

						cursor.getString(cursor.getColumnIndexOrThrow(AVATAR_NAME_BASE)),
						cursor.getString(cursor.getColumnIndexOrThrow(AVATAR_NAME_LEFT_ARM)),
						cursor.getString(cursor.getColumnIndexOrThrow(AVATAR_NAME_RIGHT_ARM)),
						cursor.getString(cursor.getColumnIndexOrThrow(AVATAR_NAME_LEFT_LEG)),
						cursor.getString(cursor.getColumnIndexOrThrow(AVATAR_NAME_RIGHT_LEG)),

						user,

						cursor.getFloat(cursor.getColumnIndexOrThrow(AVATAR_NAME_LEFT_ARM_ROTATION)),
						cursor.getFloat(cursor.getColumnIndexOrThrow(AVATAR_NAME_RIGHT_ARM_ROTATION)),
						cursor.getFloat(cursor.getColumnIndexOrThrow(AVATAR_NAME_LEFT_LEG_ROTATION)),
						cursor.getFloat(cursor.getColumnIndexOrThrow(AVATAR_NAME_RIGHT_LEG_ROTATION))
				);

			}
			cursor.close();

		}else{
			sout("Error: could not open database for reading");
		}
		return thisAvatar;
	}

	public static Boolean updateAvatar(Context context, Integer Id, Avatar avatar){
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		Boolean success = false;
		Integer count;
		if(checkDatabase(db)) {

			ContentValues values = new ContentValues();
			values.put(AVATAR_NAME_ID, avatar.getID());

			values.put(AVATAR_NAME_BASE, avatar.getBase());
			values.put(AVATAR_NAME_LEFT_ARM, avatar.getLeftArm());
			values.put(AVATAR_NAME_RIGHT_ARM, avatar.getRightArm());
			values.put(AVATAR_NAME_LEFT_LEG, avatar.getLeftLeg());
			values.put(AVATAR_NAME_RIGHT_LEG, avatar.getRightLeg());

			values.put(AVATAR_NAME_USER, avatar.getUserID());

			values.put(AVATAR_NAME_LEFT_ARM_ROTATION, avatar.getLeftArmRotation());
			values.put(AVATAR_NAME_RIGHT_ARM_ROTATION, avatar.getRightArmRotation());
			values.put(AVATAR_NAME_LEFT_LEG_ROTATION, avatar.getLeftLegRotation());
			values.put(AVATAR_NAME_RIGHT_LEG_ROTATION, avatar.getRightLegRotation());

			try {
				count = db.update(DatabaseColumnNames.Avatar.TABLE_NAME, values, _ID + " = ?", new String[]{String.valueOf(Id)});
				if(count == 1) success = true;
				else throw new Exception("[DatabaseHelper.updateAvatar] db.update returned " + count + " rows");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else{
			sout("Error: could not open database for writing");
		}
		return success;
	}

	public static int deleteAvatar(Context context, Avatar avatar){
		DatabaseHelper mDbHelper = new DatabaseHelper(context); //needs SQLiteOpenHelper
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		int success = -1;

		if(checkDatabase(db)) {

			String whereClause = AVATAR_NAME_ID + " = ?" +

					" AND " + AVATAR_NAME_BASE + " = ?" +
					" AND " + AVATAR_NAME_LEFT_ARM + " = ?" +
					" AND " + AVATAR_NAME_RIGHT_ARM + " = ?" +
					" AND " + AVATAR_NAME_LEFT_LEG + " = ?" +
					" AND " + AVATAR_NAME_RIGHT_LEG + " = ?" +

					" AND " + AVATAR_NAME_USER + " = ?" +

					" AND " + AVATAR_NAME_LEFT_ARM_ROTATION + " = ?" +
					" AND " + AVATAR_NAME_RIGHT_ARM_ROTATION + " = ?" +
					" AND " + AVATAR_NAME_LEFT_LEG_ROTATION + " = ?" +
					" AND " + AVATAR_NAME_RIGHT_LEG_ROTATION + " = ?"
			;

			String[] values = new String[11];
			int i = 0;
			values[i++] = "" + avatar.getID();

			values[i++] = "" + avatar.getBase();
			values[i++] = "" + avatar.getLeftArm();
			values[i++] = "" + avatar.getRightArm();
			values[i++] = "" + avatar.getLeftLeg();
			values[i++] = "" + avatar.getRightLeg();

			values[i++] = "" + avatar.getUserID();

			values[i++] = "" + avatar.getLeftArmRotation();
			values[i++] = "" + avatar.getRightArmRotation();
			values[i++] = "" + avatar.getLeftLegRotation();
			values[i] = "" + avatar.getRightLegRotation();

			try {
				success = db.delete(
						DatabaseColumnNames.Avatar.TABLE_NAME,
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
