package com.example.cwagt.taskapp345.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.object.Task;
import com.example.cwagt.taskapp345.object.User;

import java.util.ArrayList;


/**
 * Created by cwagt on 15/07/2018.
 */

public class AddTask extends AppCompatActivity {

    private Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.add_task);

        //Button code
        final Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Create new user
                TextView taskNameField = findViewById(R.id.edittextTaskName);
                TextView taskDescriptionField = findViewById(R.id.edittextTaskDescription);
                TextView taskTimeField = findViewById(R.id.edittextTaskTime);
                String taskName = taskNameField.getText().toString();
                String taskDescription = taskDescriptionField.getText().toString();
                String taskTime = taskTimeField.getText().toString();

                ArrayList<Task> taskList = DatabaseHelper.getAllTasksFromDatabase(context);

                Task newTask = new Task(taskName, taskDescription, taskTime);

                DatabaseHelper.writeTaskToDatabase(context, newTask);

                // Code here executes on main thread after user presses button
                Intent addUserIntent = new Intent(context, EditTask.class);
                startActivity(addUserIntent);
            }
        });
    }

}
