package com.example.cwagt.taskapp345.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.cwagt.taskapp345.R;
import org.w3c.dom.Text;

import java.util.List;

public class BodyPartsAdapter extends RecyclerView.Adapter<BodyPartsAdapter.AvatarViewHolder>{

	private List<String> Body_Parts;
	private LayoutInflater mInflater;

	public BodyPartsAdapter(Context context, List<String> body_Parts) {
		this.mInflater = LayoutInflater.from(context);
		this.Body_Parts = body_Parts;
	}

	@Override
	public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = mInflater.inflate(R.layout.bodyparts_row, parent, false);
		return new AvatarViewHolder(view);
	}

	@Override
	public void onBindViewHolder(AvatarViewHolder holder, int position) {
		String Body_Part = Body_Parts.get(position);
		holder.Body_Text.setText(Body_Part);
	}

	class AvatarViewHolder extends RecyclerView.ViewHolder {
		private TextView Body_Text;

		private AvatarViewHolder(View view) {
			super(view);
			Body_Text = view.findViewById(R.id.body_part_text);
		}

	}

	@Override
	public int getItemCount() {
		return Body_Parts.size();
	}


	String getItem(int id) {
		return Body_Parts.get(id);
	}


	}





