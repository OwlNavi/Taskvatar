package com.example.cwagt.taskapp345;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.object.Task;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Random;

import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.Task.TASK_NAME_DESCRIPTION;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.Task.TASK_NAME_TEXT;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.Task.TASK_NAME_TIME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class TaskDatabaseTest {

	private Task createRandomTask(){
		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;

		String name = "Dummy task " + n;
		String descr = "Dummy descr";
		String time = "12:00";

		return new Task(name, descr, time);
	}

	@Test
	public void writeTaskToDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		//create task
		Task task = createRandomTask();
		long rowID = DatabaseHelper.createTask(context, task);
		assertNotEquals(-1, rowID);

		//delete task
		int isDeleted = DatabaseHelper.deleteTask(context, task);
		assertNotEquals(-1, isDeleted);
	}

	@Test
	public void readTaskFromDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;

		String name = "Dummy task " + n;
		String descr = "Dummy descr";
		String time = "12:00";

		Task task = new Task(name, descr, time);
		long rowID = DatabaseHelper.createTask(context, task);
		assertNotEquals(-1, rowID);

		//read task
		ArrayList<Task> allTasks = DatabaseHelper.readTasks(context, TASK_NAME_TEXT + " = ? AND " + TASK_NAME_DESCRIPTION + " = ? AND " + TASK_NAME_TIME + " = ?", new String[]{name, descr, time});
		assertEquals(1, allTasks.size());
		Task taskFromDb = allTasks.get(0);
		//assertArrayEquals(task, taskFromDb);
		assertEquals(name, taskFromDb.getName());
		assertEquals(descr, taskFromDb.getDescription());
		assertEquals(time, taskFromDb.getTime());

		//delete task
		int isDeleted = DatabaseHelper.deleteTask(context, task);
		assertNotEquals(-1, isDeleted);
	}

	//TODO: update task

	//TODO: delete task

}
