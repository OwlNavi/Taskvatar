package com.example.cwagt.taskapp345.helper;


import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.cwagt.taskapp345.R;
import java.util.List;

public class BodyPartsAdapter extends RecyclerView.Adapter<BodyPartsAdapter.AvatarViewHolder>{

    private List<String> bodyPartsList;
    private LayoutInflater mInflater;

    public BodyPartsAdapter(Context context, List<String> bodyPartsList) {
        this.mInflater = LayoutInflater.from(context);
        this.bodyPartsList = bodyPartsList;
    }

    @Override
    public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.body_part_row, parent, false);

        final RecyclerView recyclerView = parent.findViewById(R.id.bodyPartsRecyclerView);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView.getContext(),
                recyclerView, new RecyclerItemClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //display selected body part debug
                Log.d("Body Part Adapter", "Clicked on: " + position);
            }

            @Override
            public void onLongClick(View view, int position) {
                onClick(view, position);
            }
        }));

        return new AvatarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AvatarViewHolder holder, int position) {
        String bodyPart = bodyPartsList.get(position);
        holder.bodyPartTextView.setText(bodyPart);
    }

    class AvatarViewHolder extends RecyclerView.ViewHolder {
        private TextView bodyPartTextView;

        private AvatarViewHolder(View view) {
            super(view);
            bodyPartTextView = view.findViewById(R.id.bodyPartTextView);
        }
    }

    @Override
    public int getItemCount() {
        return bodyPartsList.size();
    }

    String getItem(int id) {
        return bodyPartsList.get(id);
    }
}