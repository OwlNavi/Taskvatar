package com.example.cwagt.taskapp345.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.AddTaskInputValidator;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.object.Task;

import java.util.ArrayList;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by cwagt on 15/07/2018.
 *
 * This class manages the AddTask activity where users can add new tasks to the tasklist
 */
public class AddTask extends AppCompatActivity {
    //The current context of this activity
    private Context context;

    /**
     * This method is called when the activity is first opened
     * @param savedInstanceState the savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get the current context
        context = getApplicationContext();
        //Set the layout based on the xml file
        setContentView(R.layout.add_task);

        //check and see if we are editing a task
		String taskID = getIntent().getStringExtra("taskID");
		if(taskID != null && !taskID.equals("")){
			//we have a task
			SharedPreferences preferences = getDefaultSharedPreferences(context);
			Long userID = preferences.getLong("currentUser", 0);
			ArrayList<Task> taskList = DatabaseHelper.readAllTasks(context, userID);
			Task task = null;
			for(Task temp_task: taskList){
				if(temp_task.get_id() == Integer.parseInt(taskID)){
					task = temp_task;
				}
			}
			assert task != null;
			TextView taskNameField = findViewById(R.id.edittextTaskName);
			TextView taskDescriptionField = findViewById(R.id.edittextTaskDescription);
			TextView taskTimeField = findViewById(R.id.edittextTaskTime);
			taskNameField.setText(task.getName());
			taskDescriptionField.setText(task.getDescription());
			taskTimeField.setText(task.getTime());
		}


        //Button code once the user has finished editing the text fields
        final Button button = findViewById(R.id.submitButton);
        //Set onclick listener
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				//Retrieve what the user entered into the text fields
				TextView taskNameField = findViewById(R.id.edittextTaskName);
				TextView taskDescriptionField = findViewById(R.id.edittextTaskDescription);
				TextView taskTimeField = findViewById(R.id.edittextTaskTime);
				String taskName = taskNameField.getText().toString();
				String taskDescription = taskDescriptionField.getText().toString();
				String taskTime = taskTimeField.getText().toString();

				SharedPreferences preferences = getDefaultSharedPreferences(context);
				Long userID = preferences.getLong("currentUser", -1);

				if(userID == -1) try {
					throw new Exception("UserID is -1");
				} catch (Exception e) {
					e.printStackTrace();
				}

				//Create the new task based on what the user inputted
				Task newTask = new Task(taskName, taskDescription, taskTime, userID);

				String validationMessage = AddTaskInputValidator.validateTask(newTask.getName(),
						newTask.getDescription(),
						newTask.getTime());
				if(validationMessage.equals("")){
					//write the next task to the database
					Long newID = DatabaseHelper.createTask(context, newTask);
					newTask.set_id(newID);
					//DatabaseHelper.updateTask(context, newID, newTask);

					// Once the task has been added go back to the edit task activity
					Intent addUserIntent = new Intent(context, EditTask.class);
					finish();
					startActivity(addUserIntent);
				} else {
					//failed to validate

					//show the user a message to let them know they must complete validation
					AlertDialog.Builder builder = new AlertDialog.Builder(AddTask.this);
					builder.setMessage(validationMessage)
							.setTitle("Input Errors");
					builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked OK button

						}
					});
					AlertDialog dialog = builder.create();
					dialog.show();
				}


            }
        });
    }
}
