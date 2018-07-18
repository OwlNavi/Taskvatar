package com.example.cwagt.taskapp345;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.cwagt.taskapp345.object.Task;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.cwagt.taskapp345.helper.DatabaseHelper.deleteTaskFromDatabase;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.writeTaskToDatabase;
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

		//create task
		Task task = new Task("Dummy", "Dummy descr", "12:00");
		long rowID = writeTaskToDatabase(context, task);
		assertNotEquals(-1, rowID);

		//delete task
		int isDeleted = deleteTaskFromDatabase(context, task);
		assertNotEquals(-1, isDeleted);
	}

}
