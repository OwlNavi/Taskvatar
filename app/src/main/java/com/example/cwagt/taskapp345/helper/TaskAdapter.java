package com.example.cwagt.taskapp345.helper;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.object.Task;

import java.lang.reflect.Field;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

	private List<Task> moviesList;

	public class MyViewHolder extends RecyclerView.ViewHolder {
		public TextView title, year, genre;

		public MyViewHolder(View view) {
			super(view);
			title = (TextView) view.findViewById(R.id.title);
			genre = (TextView) view.findViewById(R.id.genre);
			year = (TextView) view.findViewById(R.id.year);
		}
	}

	public TaskAdapter(List<Task> moviesList) {
		this.moviesList = moviesList;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.movie_list_row, parent, false);

		itemView.setBackgroundColor(Color.RED);

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
                        view.setBackgroundColor(Color.RED);
                        break;
                    case Color.RED:
                        view.setBackgroundColor(Color.YELLOW);
                        break;
                    case Color.YELLOW:
                        view.setBackgroundColor(Color.GREEN);
                        break;
                    case Color.GREEN:
                        view.setBackgroundColor(Color.RED);
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
		Task task = moviesList.get(position);
		holder.title.setText(task.getTitle());
		holder.genre.setText(task.getGenre());
		holder.year.setText(task.getYear());
	}

	@Override
	public int getItemCount() {
		return moviesList.size();
	}
}