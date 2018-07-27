package com.example.cwagt.taskapp345;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.cwagt.taskapp345.object.Avatar;
import com.example.cwagt.taskapp345.object.Task;
import com.example.cwagt.taskapp345.object.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Random;

import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.Task.TASK_NAME_DESCRIPTION;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.Task.TASK_NAME_TEXT;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.Task.TASK_NAME_TIME;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.User.USER_NAME_DESCRIPTION;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.User.USER_NAME_ID;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.User.USER_NAME_NAME;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.deleteAvatarFromDatabase;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.deleteTaskFromDatabase;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.deleteUserFromDatabase;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.getAvatarsFromDatabase;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.getTasksFromDatabase;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.getUsersFromDatabase;
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.writeAvatarToDatabase;
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


    private Task createRandomTask(){
		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;

		String name = "Dummy task " + n;
		String descr = "Dummy descr";
		String time = "12:00";

		return new Task(name, descr, time);
	}

	private Task createTask(String name, String desc, String time){
		return new Task(name, desc, time);
	}

	@Test
	public void writeTaskToDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		//create task
		Task task = createRandomTask();
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
		
		String name = "Dummy task " + n;
		String descr = "Dummy descr";
		String time = "12:00";
		
		Task task = createTask(name, descr, time);
		long rowID = writeTaskToDatabase(context, task);
		assertNotEquals(-1, rowID);

		//read task
		ArrayList<Task> allTasks = getTasksFromDatabase(context, TASK_NAME_TEXT + " = ? AND " + TASK_NAME_DESCRIPTION + " = ? AND " + TASK_NAME_TIME + " = ?", new String[]{name, descr, time});
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

	private User createRandomUser(){
		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;
		
		String userName = "Dummy user " + n;
		String userDescription = "Testing only";
		
		return new User(userName, n, userDescription);
	}

	@Test
	public void writeUserToDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		//create user
		User user = createRandomUser();
		long rowID = writeUserToDatabase(context, user);
		assertNotEquals(-1, rowID);

		//delete user
		int isDeleted = deleteUserFromDatabase(context, user);
		assertNotEquals(-1, isDeleted);
	}
	
	private User createUser(String userName, int userID, String descr){
		return new User(userName, userID, descr);
	}
	
	@Test
	public void readUserFromDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		Random rand = new Random();
		int userID = rand.nextInt(1000) + 1;
		String userName = "Dummy user " + userID;
		String descr = "Dummy descr";

		//create user
		User user = createUser(userName, userID, descr);
		long rowID = writeUserToDatabase(context, user);
		assertNotEquals(-1, rowID);

		//read user
		ArrayList<User> allUsers = getUsersFromDatabase(context, USER_NAME_NAME + " = ? AND " + USER_NAME_ID + " = ? AND " + USER_NAME_DESCRIPTION + " = ?", new String[]{userName, String.valueOf(userID), descr});
		assertEquals(1, allUsers.size());
		User userFromDb = allUsers.get(0);
		//assertArrayEquals(task, taskFromDb);
		assertEquals(userName, userFromDb.getUserName());
		assertEquals(userID, userFromDb.getUserID());
		assertEquals(descr, userFromDb.getUserDescription());

		//delete task
		int isDeleted = deleteUserFromDatabase(context, user);
		assertNotEquals(-1, isDeleted);
	}


	private Avatar createRandomAvatar(User user){
		Random rand = new Random();
		
		float leftArm = rand.nextInt(360) + 1;
		float rightArm = rand.nextInt(360) + 1;
		float leftLeg = rand.nextInt(360) + 1;
		float rightLeg = rand.nextInt(360) + 1;
		
		return new Avatar(leftArm, rightArm, leftLeg, rightLeg, user);
	}
	
	@Test
	public void writeAvatarToDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		User user = createRandomUser();

		//create avatar
		Avatar avatar = createRandomAvatar(user);
		long rowID = writeAvatarToDatabase(context, avatar);
		assertNotEquals(-1, rowID);

		//delete avatar
		int deleteAvatar = deleteAvatarFromDatabase(context, avatar);
		assertNotEquals(-1, deleteAvatar);

		//delete user
		int isDeleted = deleteUserFromDatabase(context, user);
		assertNotEquals(-1, isDeleted);
	}

	private Avatar createAvatar(float leftArm, float rightArm, float leftLeg, float rightLeg, User user){
    	return new Avatar(leftArm, rightArm, leftLeg, rightLeg, user);
	}
	
	@Test
	public void readAvatarFromDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		Random rand = new Random();
		float leftArm = rand.nextInt(360) + 1;
		float rightArm = rand.nextInt(360) + 1;
		float leftLeg = rand.nextInt(360) + 1;
		float rightLeg = rand.nextInt(360) + 1;

		//create user
		User user = createRandomUser();

		//create avatar
		Avatar avatar = createAvatar(leftArm, rightArm, leftLeg, rightLeg, user);
		long rowID = writeAvatarToDatabase(context, avatar);
		assertNotEquals(-1, rowID);

		//read avatar
		ArrayList<Avatar> allAvatars = getAvatarsFromDatabase(context, user);
		assertEquals(1, allAvatars.size());
		Avatar avatarFromDb = allAvatars.get(0);
		//assertArrayEquals(avatar, avatarFromDb);
		assertEquals(leftArm, avatarFromDb.getLeftArmRotation(), 0.1);
		assertEquals(rightArm, avatarFromDb.getRightArmRotation(), 0.1);
		assertEquals(leftLeg, avatarFromDb.getLeftLegRotation(), 0.1);
		assertEquals(rightLeg, avatarFromDb.getRightLegRotation(), 0.1);

		//delete user
		int delUser = deleteUserFromDatabase(context, user);
		assertNotEquals(-1, delUser);

		//delete avatar
		int isDeleted = deleteAvatarFromDatabase(context, avatar);
		assertNotEquals(-1, isDeleted);
	}
}
