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
import com.example.cwagt.taskapp345.object.User;

import java.util.List;

/**
 * Created by cwagt on 15/07/2018.
 *
 * Modified from TaskAdapter
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView username, userDescription, userID;

        private MyViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.username);
            userDescription = view.findViewById(R.id.userDescription);
            userID = view.findViewById(R.id.userID);
        }
    }

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_layout, parent, false);

        itemView.setBackgroundColor(Color.RED);

        final RecyclerView recyclerView = parent.findViewById(R.id.userList);
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
        User user = userList.get(position);
        Log.d("UserAdapter", user.getUserName() + " " + user.getUserID());
        holder.username.setText(user.getUserName());
        holder.userDescription.setText(user.getUserDescription());
        holder.userID.setText(Integer.toString(user.getUserID()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}