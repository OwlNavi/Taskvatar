package com.example.cwagt.taskapp345.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.object.Enums;
import com.example.cwagt.taskapp345.object.Task;

import java.util.List;

/**
 * This class manages the Task RecyclerView list.
 * We use this class to populate and handle the list items.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

	//A list of tasks to display owned by the current user
	private List<Task> taskList;
	private Context context;

	//Reference to the text field showing the number of tasks completed.
	//We need to update this when we change tasks in this class
	private TextView textTasksCompleted;

	/**
	 * Class constructor
	 *
	 * @param context the current context when this class is required
	 * @param taskList the list of tasks to display in the RecyclerView
	 * @param view the view where the number of tasks completed should be displayed
	 */
	public TaskAdapter(Context context, List<Task> taskList, TextView view) {
		this.taskList = taskList;
		this.context = context;
		this.textTasksCompleted = view;
	}

    /**
     * Class constructor without TextView for when you do not need to track the number of tasks
     * completed
     *
     * @param context the current context when this class is required
     * @param taskList the list of tasks to display in the RecyclerView
     */
    public TaskAdapter(Context context, List<Task> taskList) {
        this.taskList = taskList;
        this.context = context;
        this.textTasksCompleted = null;
    }

	/**
	 * This subclass contains references to the on-screen display elements for each Task
	 * in the RecyclerView
	 */
	class MyViewHolder extends RecyclerView.ViewHolder {
		private TextView title, time, description;
		private View view;

		private MyViewHolder(View view) {
			super(view);
			title = view.findViewById(R.id.title);
			description = view.findViewById(R.id.description);
			time = view.findViewById(R.id.time);
			this.view = view;
		}
	}

	/**
	 * This class is used to handle the list items dispalyed
	 * @param parent the parent of this class
	 * @param viewType the viewType of the class
	 * @return returns the new ViewHolder
	 */
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.task_list_row, parent, false);

		//default color of the tasks displayed is white
		int DEFAULT_TASK_COLOUR = Color.WHITE;
		itemView.setBackgroundColor(DEFAULT_TASK_COLOUR);

		//find the view item that we display the tasks on
		final RecyclerView recyclerView = parent.findViewById(R.id.taskList);

		//add onclick listeners to the task items so they can be clicked on
		recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView.getContext(),
				recyclerView, new RecyclerItemClickListener.ClickListener() {

			/**
			 * When a task is clicked on
			 * @param view the current task's view
			 * @param position the position in the list identifying the task
			 */
			@SuppressLint("SetTextI18n")
			@Override
			public void onClick(View view, int position) {

                //The current task
                Task task = taskList.get(position);
                //The status of the task so we can check if it is already completed
                Enums.Status status = task.getStatus();

                //If the task is clicked on it should toggle the colour between:
				//Green for completed and White for incomplete
                if(status == Enums.Status.COMPLETED){
                	//clicked on a completed task -> set incomplete
					task.setStatus(Enums.Status.INCOMPLETE);
					view.setBackgroundColor(Color.WHITE);
				} else if (status == Enums.Status.INCOMPLETE){
                	//clicked on an incomplete task -> set complete
					task.setStatus(Enums.Status.COMPLETED);
					view.setBackgroundColor(Color.GREEN);
				}

				//Update task in database
                if(DatabaseHelper.updateTask(context, task.get_id(), task)){
                	//the task was updated
				}else{
                	//the task was not updated
				}

				//update display of tasks completed
                if(textTasksCompleted != null){
                    int completed = 0;
                    for(Task local_task: taskList){
                        if(local_task.getStatus() == Enums.Status.COMPLETED) completed++;
                    }
                    textTasksCompleted.setText(Integer.toString(completed));
                }
			}

			/**
			 * Debug method
			 */
			@Override
			public void onLongClick(View view, int position) {
				Log.d("debug", "long click");

                view.setBackgroundColor(Color.RED);
			}
		}));

		return new MyViewHolder(itemView);
	}

	/**
	 * This method is where we change the display to reflect the changes made by this class
	 * @param holder the current viewHolder we want to modify
	 * @param position the index of the task we are currently creating
	 */
	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		Task task = taskList.get(position);
		holder.title.setText(task.getName());
		holder.description.setText(task.getDescription());
		holder.time.setText(task.getTime());

		//Set the color of the view based on the status of the task
		int color = Color.WHITE;
		Enums.Status status = task.getStatus();
		if(status == Enums.Status.INCOMPLETE){
			color = Color.WHITE;
		} else if (status == Enums.Status.COMPLETED){
			color = Color.GREEN;
		}
		holder.view.setBackgroundColor(color);
	}

	/**
	 * Implements required method by parent class
	 * @return the number of tasks in the list
	 */
	@Override
	public int getItemCount() {
		return taskList.size();
	}
}