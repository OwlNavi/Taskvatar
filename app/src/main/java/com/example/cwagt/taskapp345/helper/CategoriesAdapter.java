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

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.AvatarViewHolder>{

	private List<String> categories;
	private LayoutInflater mInflater;

	public CategoriesAdapter(Context context, List<String> categories) {
		this.mInflater = LayoutInflater.from(context);
		this.categories = categories;
	}

	@Override
	public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = mInflater.inflate(R.layout.categories_row, parent, false);

		final RecyclerView recyclerView = parent.findViewById(R.id.categoriesRecyclerView);
		recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView.getContext(),
                recyclerView, new RecyclerItemClickListener.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				//display selected category debug
                Log.d("CategoriesAdapter", "Clicked on: " + position);
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
		String category = categories.get(position);
		holder.categoryTextView.setText(category);
	}

	class AvatarViewHolder extends RecyclerView.ViewHolder {
		private TextView categoryTextView;

		private AvatarViewHolder(View view) {
			super(view);
            categoryTextView = view.findViewById(R.id.categoryTextView);
		}
	}

	@Override
	public int getItemCount() {
		return categories.size();
	}

}