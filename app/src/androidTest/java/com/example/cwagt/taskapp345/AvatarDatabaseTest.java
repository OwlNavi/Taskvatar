package com.example.cwagt.taskapp345;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.object.Avatar;
import com.example.cwagt.taskapp345.object.User;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class AvatarDatabaseTest {

	private Avatar createRandomAvatar(User user){
		Random rand = new Random();

		Integer avatarID = rand.nextInt(360) + 1;

		String base = "base_red";
		String leftArm = "left_arm";
		String rightArm = "right_arm";
		String leftLeg = "left_leg";
		String rightLeg = "right_leg";

		float leftArmRotation = rand.nextInt(360) + 1;
		float rightArmRotation = rand.nextInt(360) + 1;
		float leftLegRotation = rand.nextInt(360) + 1;
		float rightLegRotation = rand.nextInt(360) + 1;

		return new Avatar(avatarID, base, leftArm, rightArm, leftLeg, rightLeg, user, leftArmRotation, rightArmRotation, leftLegRotation, rightLegRotation);
	}

	private User createRandomUser(){
		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;

		String userName = "Dummy user " + n;
		String userDescription = "Testing only";

		return new User(userName, n, userDescription);
	}

	@Test
	public void writeAvatarToDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		User user = createRandomUser();

		//create avatar
		Avatar avatar = createRandomAvatar(user);
		long rowID = DatabaseHelper.createAvatar(context, avatar);
		assertNotEquals(-1, rowID);

		//delete avatar
		int deleteAvatar = DatabaseHelper.deleteAvatar(context, avatar);
		assertNotEquals(-1, deleteAvatar);

		//delete user
		int isDeleted = DatabaseHelper.deleteUser(context, user);
		assertNotEquals(-1, isDeleted);
	}

	@Test
	public void readAvatarFromDB(){
		Context context = InstrumentationRegistry.getTargetContext();

		Random rand = new Random();
		int avatarID = rand.nextInt(360) + 1;

		String base = "base_red";
		String leftArm = "left_arm";
		String rightArm = "right_arm";
		String leftLeg = "left_leg";
		String rightLeg = "right_leg";

		User user = createRandomUser();

		float leftArmRotation = rand.nextInt(360) + 1;
		float rightArmRotation = rand.nextInt(360) + 1;
		float leftLegRotation = rand.nextInt(360) + 1;
		float rightLegRotation = rand.nextInt(360) + 1;

		//create avatar
		Avatar avatar = new Avatar(avatarID, base, leftArm, rightArm, leftLeg, rightLeg, user, leftArmRotation, rightArmRotation, leftLegRotation, rightLegRotation);
		long rowID = DatabaseHelper.createAvatar(context, avatar);
		assertNotEquals(-1, rowID);

		//read avatar
		Avatar avatarFromDb = DatabaseHelper.readAvatar(context, user);
		//assertEquals(1, allAvatars.size());
		//Avatar avatarFromDb = allAvatars.get(0);
		//assertArrayEquals(avatar, avatarFromDb);
		assertEquals(avatarID, avatarFromDb.getAvatarID());
		assertEquals(base, avatarFromDb.getBase());

		assertEquals(leftArm, avatarFromDb.getLeftArm());
		assertEquals(rightArm, avatarFromDb.getRightArm());
		assertEquals(leftLeg, avatarFromDb.getLeftLeg());
		assertEquals(rightLeg, avatarFromDb.getRightLeg());

		assertEquals(leftArmRotation, avatarFromDb.getLeftArmRotation(), 0.1);
		assertEquals(rightArmRotation, avatarFromDb.getRightArmRotation(), 0.1);
		assertEquals(leftLegRotation, avatarFromDb.getLeftLegRotation(), 0.1);
		assertEquals(rightLegRotation, avatarFromDb.getRightLegRotation(), 0.1);

		//delete user
		int delUser = DatabaseHelper.deleteUser(context, user);
		assertNotEquals(-1, delUser);

		//delete avatar
		int isDeleted = DatabaseHelper.deleteAvatar(context, avatar);
		assertNotEquals(-1, isDeleted);
	}

	//TODO: Add update

	//TODO: add delete
}
