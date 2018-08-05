package com.example.cwagt.taskapp345;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.object.User;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Random;

import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.User.USER_NAME_DESCRIPTION;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.User.USER_NAME_ID;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.User.USER_NAME_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class UserDatabaseTest {

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
		long rowID = DatabaseHelper.createUser(context, user);
		assertNotEquals(-1, rowID);

		//delete user
		int isDeleted = DatabaseHelper.deleteUser(context, user);
		assertNotEquals(-1, isDeleted);
	}

	@Test
	public void readUserFromDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		Random rand = new Random();
		int userID = rand.nextInt(1000) + 1;
		String userName = "Dummy user " + userID;
		String descr = "Dummy descr";

		//create user
		User user = new User(userName, userID, descr);
		long rowID = DatabaseHelper.createUser(context, user);
		assertNotEquals(-1, rowID);

		//read user
		ArrayList<User> allUsers = DatabaseHelper.readUsers(context, USER_NAME_NAME + " = ? AND " + USER_NAME_ID + " = ? AND " + USER_NAME_DESCRIPTION + " = ?", new String[]{userName, String.valueOf(userID), descr});
		assertEquals(1, allUsers.size());
		User userFromDb = allUsers.get(0);
		//assertArrayEquals(task, taskFromDb);
		assertEquals(userName, userFromDb.getUserName());
		assertEquals(userID, userFromDb.getUserID());
		assertEquals(descr, userFromDb.getUserDescription());

		//delete task
		int isDeleted = DatabaseHelper.deleteUser(context, user);
		assertNotEquals(-1, isDeleted);
	}

	//TODO: update user

	//TODO: delete user

}
