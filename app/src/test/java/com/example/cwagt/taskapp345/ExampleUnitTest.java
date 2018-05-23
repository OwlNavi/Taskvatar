package com.example.cwagt.taskapp345;

import com.example.cwagt.taskapp345.object.Avatar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
	public void avatarGetRotationDefault() {
    	//tests the getter
    	Avatar testAvatar1 = new Avatar();
    	assertEquals(Float.valueOf(0), (Float)testAvatar1.getLeftArmRotation());
		assertEquals(Float.valueOf(0), (Float)testAvatar1.getRightArmRotation());
		assertEquals(Float.valueOf(0), (Float)testAvatar1.getLeftLegRotation());
		assertEquals(Float.valueOf(0), (Float)testAvatar1.getRightLegRotation());
	}

	@Test
	public void avatarGetRotationExplicit() {
		//tests the getter
		Avatar testAvatar2 = new Avatar(1,2,3,4);
		assertEquals(Float.valueOf(1), (Float)testAvatar2.getLeftArmRotation());
		assertEquals(Float.valueOf(2), (Float)testAvatar2.getRightArmRotation());
		assertEquals(Float.valueOf(3), (Float)testAvatar2.getLeftLegRotation());
		assertEquals(Float.valueOf(4), (Float)testAvatar2.getRightLegRotation());
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
		Avatar testAvatar4 = new Avatar(1,2,3,4);
		testAvatar4.setLeftArmRotation(5);
		assertEquals(Float.valueOf(5), (Float)testAvatar4.getLeftArmRotation());
	}
}