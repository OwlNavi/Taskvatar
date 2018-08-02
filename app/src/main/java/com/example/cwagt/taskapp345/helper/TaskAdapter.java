package com.example.cwagt.taskapp345.helper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

	private List<Task> taskList;
	private Context context;
	private TextView textTasksCompleted;


	public class MyViewHolder extends RecyclerView.ViewHolder {
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

	public TaskAdapter(Context context, List<Task> taskList) {
		this.taskList = taskList;
		this.context = context;
	}

	public void setTextCompletedView(TextView view){
		textTasksCompleted = view;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.task_list_row, parent, false);

		itemView.setBackgroundColor(Color.WHITE);

		final RecyclerView recyclerView = parent.findViewById(R.id.taskList);
		recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView.getContext(), recyclerView, new RecyclerItemClickListener.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				//Log.d("debug", "click");
                Drawable background = view.getBackground();

                //Log.d("debug", "Background color: " + backgroundColor);

                //change color
                Task task = taskList.get(position);
                Enums.Status status = task.getStatus();

                if(status == Enums.Status.COMPLETED){
                	//clicked on a completed task -> set uncomplete
					task.setStatus(Enums.Status.INCOMPLETE);
					view.setBackgroundColor(Color.WHITE);
				} else if (status == Enums.Status.INCOMPLETE){
                	//clicked on an incomplete task -> set complete
					task.setStatus(Enums.Status.COMPLETED);
					view.setBackgroundColor(Color.GREEN);
				}

				//Update task in a dirty way
				DatabaseHelper.deleteTaskFromDatabase(context, task);
				DatabaseHelper.writeTaskToDatabase(context, task);

				//update display of tasks completed
				int completed = 0;
				for(Task local_task: taskList){
					if(local_task.getStatus() == Enums.Status.COMPLETED) completed++;
				}
				textTasksCompleted.setText(Integer.toString(completed));

			}

			@Override
			public void onLongClick(View view, int position) {
				Log.d("debug", "long click");

                view.setBackgroundColor(Color.RED);
			}
		}));

		return new MyViewHolder(itemView);
	}

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

	@Override
	public int getItemCount() {
		return taskList.size();
	}
}