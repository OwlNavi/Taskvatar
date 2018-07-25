package com.example.cwagt.taskapp345;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.cwagt.taskapp345.object.Task;
import com.example.cwagt.taskapp345.object.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Random;

import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.Task.TASK_NAME_TEXT;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.deleteTaskFromDatabase;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.deleteUserFromDatabase;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.getTasksFromDatabase;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.writeTaskToDatabase;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.writeUserToDatabase;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext(){
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.cwagt.taskapp345", appContext.getPackageName());
    }

	@Test
	public void writeTaskToDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;

		String name = "Dummy" + n;
		String descr = "Dummy descr";
		String time = "12:00";

		//create task
		Task task = new Task(name, descr, time);
		long rowID = writeTaskToDatabase(context, task);
		assertNotEquals(-1, rowID);

		//delete task
		int isDeleted = deleteTaskFromDatabase(context, task);
		assertNotEquals(-1, isDeleted);
	}

	@Test
	public void readTaskFromDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;

		String name = "Dummy" + n;
		String descr = "Dummy descr";
		String time = "12:00";

		//create task
		Task task = new Task(name, descr, time);
		long rowID = writeTaskToDatabase(context, task);
		assertNotEquals(-1, rowID);

		//read task
		ArrayList<Task> allTasks = getTasksFromDatabase(context, TASK_NAME_TEXT + " = ?", new String[]{name});
		assertEquals(1, allTasks.size());
		Task taskFromDb = allTasks.get(0);
		//assertArrayEquals(task, taskFromDb);
		assertEquals(name, taskFromDb.getName());
		assertEquals(descr, taskFromDb.getDescription());
		assertEquals(time, taskFromDb.getTime());

		//delete task
		int isDeleted = deleteTaskFromDatabase(context, task);
		assertNotEquals(-1, isDeleted);
	}


	@Test
	public void writeUserToDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;

		String userName = "Shaun" + n;
		int userID = n;
		String userDescription = "Testing only";

		//create user
		User user = new User(userName, userID, userDescription);
		long rowID = writeUserToDatabase(context, user);
		assertNotEquals(-1, rowID);

		//delete user
		int isDeleted = deleteUserFromDatabase(context, user);
		assertNotEquals(-1, isDeleted);
	}

	@Test
	public void readUserFromDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;

		String name = "Dummy" + n;
		String descr = "Dummy descr";
		String time = "12:00";

		//create task
		Task task = new Task(name, descr, time);
		long rowID = writeTaskToDatabase(context, task);
		assertNotEquals(-1, rowID);

		//read task
		ArrayList<Task> allTasks = getTasksFromDatabase(context, TASK_NAME_TEXT + " = ?", new String[]{name});
		assertEquals(1, allTasks.size());
		Task taskFromDb = allTasks.get(0);
		//assertArrayEquals(task, taskFromDb);
		assertEquals(name, taskFromDb.getName());
		assertEquals(descr, taskFromDb.getDescription());
		assertEquals(time, taskFromDb.getTime());

		//delete task
		int isDeleted = deleteTaskFromDatabase(context, task);
		assertNotEquals(-1, isDeleted);
	}

}
