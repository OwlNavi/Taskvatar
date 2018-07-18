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


        final RecyclerView recyclerView = parent.findViewById(R.id.userList);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView.getContext(), recyclerView, new RecyclerItemClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //do nothing
                User user = userList.get(position);
                int userID = user.getUserID();

                Log.d("UserAdapter", "userid: " + userID);
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.d("debug", "long click");

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