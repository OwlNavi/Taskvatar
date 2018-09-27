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

/**
 * Creates a timer picker fragment allowing the user
 * to select a time.
 * Authors: Josh April, Shaun Henderson, Craig Thomas
 *
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

	/**
	 * Creates a calender instance to save the
	 * selected time.
	 * @param savedInstanceState
	 * @return
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){

		final Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		return new TimePickerDialog(getActivity(),2,this,hour,minute,true);

	}

	/**
	 * Sets the time selected by the user to the textView.
	 * @param view view
	 * @param hourOfDay
	 * @param minute
	 */
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		TextView text = getActivity().findViewById(R.id.edittextTaskTime);
		Log.e("Time set"," "+hourOfDay+minute);
		String newTime = "";

		if(hourOfDay < 10) newTime += "0";
		newTime += hourOfDay + ":";

		if(minute < 10) newTime += "0";
		newTime += minute;

		text.setText(newTime); //add time to textview

	}


}
