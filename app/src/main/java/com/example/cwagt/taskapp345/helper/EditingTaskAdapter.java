package com.example.cwagt.taskapp345.helper;

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
public class EditingTaskAdapter extends RecyclerView.Adapter<EditingTaskAdapter.MyViewHolder> {

    //A list of tasks to display owned by the current user
    private List<Task> taskList;
    private List<Task> selected;
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
    public EditingTaskAdapter(Context context, List<Task> taskList, TextView view) {
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
    public EditingTaskAdapter(Context context, List<Task> taskList, List<Task> selectedList) {
        this.taskList = taskList;
        this.context = context;
        this.textTasksCompleted = null;
        this.selected = selectedList;
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
                .inflate(R.layout.edit_task_row, parent, false);

        //default color of the tasks displayed is white
        int DEFAULT_TASK_COLOUR = Color.WHITE;
        itemView.setBackgroundColor(DEFAULT_TASK_COLOUR);

        //find the view item that we display the tasks on
        final RecyclerView recyclerView = parent.findViewById(R.id.taskList);

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //The current task
                Task task = null;

                //get the name of the task clicked on
                TextView taskNameTextView = itemView.findViewById(R.id.title);
                String taskName = taskNameTextView.getText().toString();

                System.out.println("Clicked on " + taskName);

                for(Task task_temp: taskList){
                    if(task_temp.getName().equals(taskName)){
                        task = task_temp;
                    }
                }
                if(task == null) System.err.println("Clicked tasks name not found in task list");

                //The status of the task so we can check if it is already completed
                assert task != null;

                //Toggle selection of tasks
                if(!selected.contains(task)){
                    selected.add(task);
                    view.setBackgroundColor(Color.YELLOW);

                } else {
                    selected.remove(task);
                    view.setBackgroundColor(Color.WHITE);

                }


            }
        });

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