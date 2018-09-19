package com.example.cwagt.taskapp345.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;
import com.example.cwagt.taskapp345.R;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){

		final Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		return new TimePickerDialog(getActivity(),2,this,hour,minute,true);

	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		TextView text = getActivity().findViewById(R.id.edittextTaskTime);
		Log.e("Time set"," "+hourOfDay+minute);
		text.setText(""+hourOfDay+":"+minute); //add time to textview

	}


}
