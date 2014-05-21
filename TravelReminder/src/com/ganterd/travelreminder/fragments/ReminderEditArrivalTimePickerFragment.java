package com.ganterd.travelreminder.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.ganterd.travelreminder.Reminder;
import com.ganterd.travelreminder.RemindersHelper;

public class ReminderEditArrivalTimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	public static final String ARG_REMINDER = "ARG_REMINDER";
	
	private Reminder reminder;
	private OnArrivalTimeUpdatedListener listener;
	
	public interface OnArrivalTimeUpdatedListener{
		public void newTimeSelected();
	}
	
	public static ReminderEditArrivalTimePickerFragment newInstance(Reminder reminder){
		ReminderEditArrivalTimePickerFragment f = new ReminderEditArrivalTimePickerFragment();
		
		Bundle args = new Bundle();
		args.putSerializable(ReminderEditArrivalTimePickerFragment.ARG_REMINDER, reminder);
		f.setArguments(args);
		
		return f;
	}
	
	public void setListener(OnArrivalTimeUpdatedListener l){
		this.listener = l;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Bundle args = getArguments();
		reminder = (Reminder)args.getSerializable(ReminderEditArrivalTimePickerFragment.ARG_REMINDER);
		
		int hour = this.reminder.getArrivalTimeHour();
		int minute = this.reminder.getArrivalTimeMinutes();
		
		return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
	}
	
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		this.reminder.setArrivalTime(hourOfDay, minute);
		RemindersHelper.saveReminder(this.reminder);
		listener.newTimeSelected();
	}
}