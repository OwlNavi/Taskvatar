package com.example.cwagt.taskapp345;

import android.provider.BaseColumns;

public final class DatabaseContract {
	// To prevent someone from accidentally instantiating the contract class,
	// make the constructor private.
	private DatabaseContract() {}

	/* Inner classes that defines the table contents */
	public static class Task implements BaseColumns {
		public static final String TABLE_NAME = "task";
		public static final String COLUMN_NAME_TEXT = "text";
		public static final String COLUMN_TYPE_TEXT = "TEXT";
		public static final String COLUMN_NAME_DESCRIPTION = "description";
		public static final String COLUMN_TYPE_DESCRIPTION = "TEXT";
		public static final String COLUMN_NAME_FREQUENCY = "frequency";
		public static final String COLUMN_TYPE_FREQUENCY = "TEXT";
		public static final String COLUMN_NAME_IMPORTANCE = "importance";
		public static final String COLUMN_TYPE_IMPORTANCE = "NUMERIC";
		public static final String COLUMN_NAME_STATUS = "status";
		public static final String COLUMN_TYPE_STATUS = "TEXT";
		public static final String COLUMN_NAME_REMINDER = "reminder";
		public static final String COLUMN_TYPE_REMINDER = "INTEGER";
	}

	public static class Avatar implements BaseColumns {
		public static final String TABLE_NAME = "avatar";
		public static final String COLUMN_NAME_BASEIMAGE = "base_image";
		public static final String COLUMN_TYPE_BASEIMAGE = "TEXT";
	}

	public static class User implements BaseColumns {
		public static final String TABLE_NAME = "user";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_TYPE_NAME = "TEXT";
	}
}
