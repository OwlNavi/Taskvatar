package com.example.cwagt.taskapp345;

import com.example.cwagt.taskapp345.object.Avatar;
import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class AvatarUnitTest {

	@Test
	public void avatarGetRotationDefault() {
		//tests the getter, default constructor
		Avatar testAvatar1 = new Avatar();
		assertEquals(Float.valueOf(0), (Float)testAvatar1.getLeftArmRotation());
		assertEquals(Float.valueOf(0), (Float)testAvatar1.getRightArmRotation());
		assertEquals(Float.valueOf(0), (Float)testAvatar1.getLeftLegRotation());
		assertEquals(Float.valueOf(0), (Float)testAvatar1.getRightLegRotation());
	}

	@Test
	public void avatarGetRotationExplicit() {
		//tests the getter, explicit constructor
		Random rand = new Random();

		Float degrees = (float)360;

		Float leftArmRotation = rand.nextFloat() * degrees;
		Float rightArmRotation = rand.nextFloat() * degrees;
		Float leftLegRotation = rand.nextFloat() * degrees;
		Float rightLegRotation = rand.nextFloat() * degrees;

		HashMap<String, Integer> bodyParts = new HashMap<>();
		Integer intVal = 1;
		bodyParts.put("base", intVal++);
		bodyParts.put("hat", intVal++);
		bodyParts.put("leftArm", intVal++);
		bodyParts.put("rightArm", intVal++);
		bodyParts.put("leftLeg", intVal++);
		bodyParts.put("rightLeg", intVal++);
		bodyParts.put("background", intVal);

		Avatar testAvatar2 = new Avatar(bodyParts, leftArmRotation, rightArmRotation, leftLegRotation, rightLegRotation);
		assertEquals(leftArmRotation, testAvatar2.getLeftArmRotation(), 0.1);
		assertEquals(rightArmRotation, testAvatar2.getRightArmRotation(), 0.1);
		assertEquals(leftLegRotation, testAvatar2.getLeftLegRotation(), 0.1);
		assertEquals(rightLegRotation, testAvatar2.getRightLegRotation(), 0.1);
	}

	@Test
	public void avatarSetLeftArmRotation() {
		//tests the setter (and the getter)
		Avatar testAvatar3 = new Avatar();
		testAvatar3.setLeftArmRotation(1);
		assertEquals(Float.valueOf(1), (Float)testAvatar3.getLeftArmRotation());
	}

	@Test
	public void avatarSetLeftArmRotationExplicit() {
		//tests the setter (and the getter)
		Random rand = new Random();

		Float degrees = (float)360;

		Float leftArmRotation = rand.nextFloat() * degrees + 10;
		Float rightArmRotation = rand.nextFloat() * degrees + 10;
		Float leftLegRotation = rand.nextFloat() * degrees + 10;
		Float rightLegRotation = rand.nextFloat() * degrees + 10;

		HashMap<String, Integer> bodyParts = new HashMap<>();
		Integer intVal = 1;
		bodyParts.put("base", intVal++);
		bodyParts.put("hat", intVal++);
		bodyParts.put("leftArm", intVal++);
		bodyParts.put("rightArm", intVal++);
		bodyParts.put("leftLeg", intVal++);
		bodyParts.put("rightLeg", intVal++);
		bodyParts.put("background", intVal);

		Avatar testAvatar4 = new Avatar(bodyParts, leftArmRotation, rightArmRotation, leftLegRotation, rightLegRotation);

		testAvatar4.setLeftArmRotation(2);
		testAvatar4.setRightArmRotation(3);
		testAvatar4.setLeftLegRotation(4);
		testAvatar4.setRightLegRotation(5);

		assertEquals(Float.valueOf(2), (Float)testAvatar4.getLeftArmRotation());
		assertEquals(Float.valueOf(3), (Float)testAvatar4.getRightArmRotation());
		assertEquals(Float.valueOf(4), (Float)testAvatar4.getLeftLegRotation());
		assertEquals(Float.valueOf(5), (Float)testAvatar4.getRightLegRotation());
	}
}
