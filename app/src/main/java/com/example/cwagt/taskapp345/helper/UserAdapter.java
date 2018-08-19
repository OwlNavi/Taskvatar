package com.example.cwagt.taskapp345.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.object.User;
import com.example.cwagt.taskapp345.view.MainActivity;

import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by cwagt on 15/07/2018.
 *
 * Modified from TaskAdapter
 * Similar class setup to TaskAdapter, modified to populate a list of users rather than a list of
 * tasks
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    //A list of the users in the database to display
    private List<User> userList;
    //the current context
    private Context context;

    /**
     * Class constructor
     * @param userList a list of users from the database to display
     * @param context the current context this class is used from
     */
    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    /**
     * The ViewHolder class implementation finds references to the view items
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView username, userDescription, userID;

        private MyViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.username);
            userDescription = view.findViewById(R.id.userDescription);
            userID = view.findViewById(R.id.userID);
        }
    }

    /**
     * Adds onClick listeners to the list items
     * @param parent the parent ViewGroup
     * @param viewType the viewType
     * @return the new ViewHolder
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_layout, parent, false);

        //Reference to the RecyclerView the tasks are displayed in
        final RecyclerView recyclerView = parent.findViewById(R.id.userList);

        //add onclick listener to each item in the list
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView.getContext(),
                recyclerView, new RecyclerItemClickListener.ClickListener() {
            /**
             * On click method called when this list item is clicked
             * When a user is clicked on from the user select page we want to
             * set them as the current user in the shared preferences
             * @param view the item clicked on
             * @param position the position of the item clicked on
             */
            @Override
            public void onClick(View view, int position) {
                //Get the reference to the User that was clicked on
                User user = userList.get(position);
                //find the userID that identifies them
                Long userID = user.get_id();

                if(userID>0) {

					//Set the current user to the user selected
					//The current user is saved in SharedPreferences accessible from other classes
					SharedPreferences preferences = getDefaultSharedPreferences(context);
					SharedPreferences.Editor editor = preferences.edit();
					editor.putLong("currentUser", userID);
					editor.apply();

					//Change the current activity to the Main Activity
					Intent mainMenuIntent = new Intent(context, MainActivity.class);
					context.startActivity(mainMenuIntent);
				}else{
                	System.out.println("Error: User ID is null. You get the user ID from the createUser method");
				}
            }

            /**
             * Debug method
             * @param view The item clicked on
             * @param position the position of the item clicked on
             */
            @Override
            public void onLongClick(View view, int position) {
                Log.d("debug", "long click");

            }
        }));

        return new MyViewHolder(itemView);
    }

    /**
     * Sets the view text based on the items in the userList
     * @param holder the item to change
     * @param position the position of the item clicked in the list
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = userList.get(position);
        holder.username.setText(user.getUserName());
        holder.userDescription.setText(user.getUserDescription());
        if(user.get_id() > 0) {
        	System.out.println("UserID: " + user.get_id().intValue());
        	//TODO: Apparently "holder.userID" doesn't exist???
			//holder.userID.setText(user.get_id().intValue());
		}else{
        	System.out.println("ERROR: User ID is null. Have you got the users from the database?");
		}
    }

    /**
     * Implements method required by parent class
     * @return number of users in the list from the database
     */
    @Override
    public int getItemCount() {
        return userList.size();
    }
}