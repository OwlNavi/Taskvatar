package com.example.cwagt.taskapp345;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.cwagt.taskapp345.helper.DatabaseHelper;
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
import static com.example.cwagt.taskapp345.helper.DatabaseHelper.*;
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

}
