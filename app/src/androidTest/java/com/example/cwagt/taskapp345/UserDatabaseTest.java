package com.example.cwagt.taskapp345;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.object.User;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class UserDatabaseTest {

	private long getRandomLong(long upperLimit) {
		return (long) (Math.random() * upperLimit);
	}

	private User createRandomUser(){
		String userName = "Dummy user";
		String userDescription = "Testing only";

		return new User(userName, userDescription);
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

		String userName = "Dummy user";
		String descr = "Dummy descr";

		//create user
		User user = new User(userName, descr);
		long userID = DatabaseHelper.createUser(context, user);
		assertNotEquals(-1, userID);
		user.set_id(userID);

		//read user
		ArrayList<User> allUsers = DatabaseHelper.readUsers(context, _ID + " = ?", new String[]{ String.valueOf(userID) });
		assertEquals(1, allUsers.size());
		User userFromDb = allUsers.get(0);
		//assertArrayEquals(task, taskFromDb);
		assertEquals(userName, userFromDb.getUserName());
		assertEquals(userID, userFromDb.get_id(), 0.1);
		assertEquals(descr, userFromDb.getUserDescription());

		//delete task
		int isDeleted = DatabaseHelper.deleteUser(context, user);
		assertNotEquals(-1, isDeleted);
	}

	@Test
	public void updateUserInDb() {
		Context context = InstrumentationRegistry.getTargetContext();

		//create old user
		User oldUser = createRandomUser();
		long oldRowID = DatabaseHelper.createUser(context, oldUser);
		assertNotEquals(-1, oldRowID);

		//create new user
		User newUser = createRandomUser();
		//long newRowID = DatabaseHelper.createUser(context, newUser);
		assertNotEquals(-1, oldRowID);

		//update user
		Boolean success = DatabaseHelper.updateUser(context, oldRowID, newUser);
		Boolean truebool = true;
		assertEquals(truebool, success);

		//delete users
		//DatabaseHelper.deleteUser(context, oldUser);
		DatabaseHelper.deleteUser(context, newUser);
	}

	@Test
	public void deleteUserByUserFromDb() {
		Context context = InstrumentationRegistry.getTargetContext();

		//create user
		User user = createRandomUser();
		long rowID = DatabaseHelper.createUser(context, user);
		assertNotEquals(-1, rowID);

		//delete user
		int numDeleted = DatabaseHelper.deleteUser(context, user);
		assertEquals(1, numDeleted);
	}

	@Test
	public void deleteUserByIdFromDb() {
		Context context = InstrumentationRegistry.getTargetContext();

		//create user
		User user = createRandomUser();
		long rowID = DatabaseHelper.createUser(context, user);
		assertNotEquals(-1, rowID);

		//delete user
		int numDeleted = DatabaseHelper.deleteUser(context, rowID);
		assertEquals(1, numDeleted);
	}

}
