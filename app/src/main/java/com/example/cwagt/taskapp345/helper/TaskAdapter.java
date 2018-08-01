package com.example.cwagt.taskapp345.helper;

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
import com.example.cwagt.taskapp345.object.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

	private List<Task> taskList;

	public class MyViewHolder extends RecyclerView.ViewHolder {
		private TextView title, time, description;

		private MyViewHolder(View view) {
			super(view);
			title = view.findViewById(R.id.title);
			description = view.findViewById(R.id.description);
			time = view.findViewById(R.id.time);
		}
	}

	public TaskAdapter(List<Task> taskList) {
		this.taskList = taskList;
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
                //change background colour
                //see https://stackoverflow.com/questions/36352945/how-to-get-color-of-a-button-with-ripple-drawable
                Drawable background = view.getBackground();

                int backgroundColor = 0;

                if (background instanceof ColorDrawable)
                    backgroundColor = ((ColorDrawable) background).getColor();

                //Log.d("debug", "Background color: " + backgroundColor);

                //change color
                switch (backgroundColor){
                    case Color.WHITE:
                        view.setBackgroundColor(Color.GREEN);
                        break;
                    case Color.RED:
                        view.setBackgroundColor(Color.GREEN);
                        break;
                    case Color.YELLOW:
                        view.setBackgroundColor(Color.GREEN);
                        break;
                    case Color.GREEN:
                        view.setBackgroundColor(Color.WHITE);
                        break;
                    default:
                        view.setBackgroundColor(Color.WHITE);
                        break;
                }

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
	}

	@Override
	public int getItemCount() {
		return taskList.size();
	}
}