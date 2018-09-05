package com.example.cwagt.taskapp345.helper;

import android.provider.BaseColumns;

/**
 * Database helper. This has all of the column names and data types for Task, Avatar, and User
 * The format is: [Object (TASK | AVATAR | USER)]_[(NAME | TYPE)]_[Field name]
 */
public final class DatabaseColumnNames {

	private DatabaseColumnNames() {}

	/* Inner classes that defines the table contents */
	public static class Task implements BaseColumns {
		public static final String TABLE_NAME = "task";
		public static final String TASK_NAME_TEXT = "text";
		public static final String TASK_TYPE_TEXT = "TEXT";
		public static final String TASK_NAME_DESCRIPTION = "description";
		public static final String TASK_TYPE_DESCRIPTION = "TEXT";
		public static final String TASK_NAME_FREQUENCY = "frequency";
		public static final String TASK_TYPE_FREQUENCY = "TEXT";
		public static final String TASK_NAME_PRIORITY = "priority";
		public static final String TASK_TYPE_PRIORITY = "NUMERIC";
		public static final String TASK_NAME_STATUS = "status";
		public static final String TASK_TYPE_STATUS = "TEXT";
		public static final String TASK_NAME_REMINDER = "reminder";
		public static final String TASK_TYPE_REMINDER = "INTEGER";
		public static final String TASK_NAME_TIME = "time";
		public static final String TASK_TYPE_TIME = "TEXT";
		public static final String TASK_NAME_USER = "userid";
		public static final String TASK_TYPE_USER = "INTEGER";
	}

	public static class User implements BaseColumns {
		public static final String TABLE_NAME = "user";
		public static final String USER_NAME_NAME = "name";
		public static final String USER_TYPE_NAME = "TEXT";
		public static final String USER_NAME_DESCRIPTION = "description";
		public static final String USER_TYPE_DESCRIPTION = "TEXT";
		public static final String USER_NAME_POINTS = "points";
		public static final String USER_TYPE_POINTS = "INTEGER";


		public static final String AVATAR_NAME_BASE = "base";
		public static final String AVATAR_TYPE_BASE = "INTEGER";
		public static final String AVATAR_NAME_HAT = "hat";
		public static final String AVATAR_TYPE_HAT = "INTEGER";
		public static final String AVATAR_NAME_LEFT_ARM = "leftArm";
		public static final String AVATAR_TYPE_LEFT_ARM = "INTEGER";
		public static final String AVATAR_NAME_RIGHT_ARM = "rightArm";
		public static final String AVATAR_TYPE_RIGHT_ARM = "INTEGER";
		public static final String AVATAR_NAME_LEFT_LEG = "leftLeg";
		public static final String AVATAR_TYPE_LEFT_LEG = "INTEGER";
		public static final String AVATAR_NAME_RIGHT_LEG = "rightLeg";
		public static final String AVATAR_TYPE_RIGHT_LEG = "INTEGER";
		public static final String AVATAR_NAME_BACKGROUND = "background";
		public static final String AVATAR_TYPE_BACKGROUND = "INTEGER";

		public static final String AVATAR_NAME_LEFT_ARM_ROTATION = "leftArmRotation";
		public static final String AVATAR_TYPE_LEFT_ARM_ROTATION = "INTEGER";
		public static final String AVATAR_NAME_RIGHT_ARM_ROTATION = "rightArmRotation";
		public static final String AVATAR_TYPE_RIGHT_ARM_ROTATION = "INTEGER";
		public static final String AVATAR_NAME_LEFT_LEG_ROTATION = "leftLegRotation";
		public static final String AVATAR_TYPE_LEFT_LEG_ROTATION = "INTEGER";
		public static final String AVATAR_NAME_RIGHT_LEG_ROTATION = "rightLegRotation";
		public static final String AVATAR_TYPE_RIGHT_LEG_ROTATION = "INTEGER";

	}
}
