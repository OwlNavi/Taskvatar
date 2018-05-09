package com.example.cwagt.taskapp345;

import android.provider.BaseColumns;

public final class DatabaseContract {
	// To prevent someone from accidentally instantiating the contract class,
	// make the constructor private.
	private DatabaseContract() {}

	/* Inner class that defines the table contents */
	public static class TaskEntry implements BaseColumns {
		public static final String TABLE_NAME = "task";
		public static final String COLUMN_NAME_TEXT = "text";
		public static final String COLUMN_NAME_DESCRIPTION = "description";
		public static final String COLUMN_NAME_FREQUENCY = "frequency";
		public static final String COLUMN_NAME_IMPORTANCE = "importance";
		public static final String COLUMN_NAME_STATUS = "status";
		public static final String COLUMN_NAME_REMINDER = "reminder";
	}

}
