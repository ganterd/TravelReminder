package com.ganterd.travelreminder.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.ganterd.travelreminder.R;
import com.ganterd.travelreminder.Reminder;
import com.ganterd.travelreminder.RemindersHelper;

public class ReminderEditFragment extends Fragment {
	public static final String ARG_EXISTING_REMINDER = "ARG_EXISTING_REMINDER";
	
	private OnClickListener recurringDayOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View b) {
			onRecurringDayToggle(b);
		}
	};
	
	Reminder existingReminder = null;
	
	public ReminderEditFragment(){
		super();
	}
	
	public static ReminderEditFragment newInstance(Reminder existingReminder){
		ReminderEditFragment f = new ReminderEditFragment();
		
		if(existingReminder != null){
			Bundle args = new Bundle();
			args.putSerializable(ReminderEditFragment.ARG_EXISTING_REMINDER, existingReminder);
			f.setArguments(args);
		}
		
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		RelativeLayout rl = (RelativeLayout)inflater.inflate(R.layout.reminder_details, container, false);
		
		Bundle args = getArguments();
		if(args != null){
			this.existingReminder = (Reminder)args.getSerializable(ARG_EXISTING_REMINDER);
			((EditText) rl.findViewById(R.id.textReminderName)).setText(this.existingReminder.getReminderName());
		}
		
		return rl;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		setupRecurringDayButtons();
	}
	
	private void setupRecurringDayButtons(){
		ToggleButton b = (ToggleButton)getView().findViewById(R.id.editTravelReminderToggleMonday);
		b.setChecked(this.existingReminder.getRecurring(Reminder.RECCURING_MONDAY));
		b.setOnClickListener(this.recurringDayOnClickListener);
		b.setTag(Reminder.RECCURING_MONDAY);
		
		b = (ToggleButton)getView().findViewById(R.id.editTravelReminderToggleTuesday);
		b.setChecked(this.existingReminder.getRecurring(Reminder.RECCURING_TUESDAY));
		b.setOnClickListener(this.recurringDayOnClickListener);
		b.setTag(Reminder.RECCURING_TUESDAY);
		
		b = (ToggleButton)getView().findViewById(R.id.editTravelReminderToggleWednesday);
		b.setChecked(this.existingReminder.getRecurring(Reminder.RECCURING_WEDNESDAY));
		b.setOnClickListener(this.recurringDayOnClickListener);
		b.setTag(Reminder.RECCURING_WEDNESDAY);
		
		b = (ToggleButton)getView().findViewById(R.id.editTravelReminderToggleThursday);
		b.setChecked(this.existingReminder.getRecurring(Reminder.RECCURING_THURSDAY));
		b.setOnClickListener(this.recurringDayOnClickListener);
		b.setTag(Reminder.RECCURING_THURSDAY);
		
		b = (ToggleButton)getView().findViewById(R.id.editTravelReminderToggleFriday);
		b.setChecked(this.existingReminder.getRecurring(Reminder.RECCURING_FRIDAY));
		b.setOnClickListener(this.recurringDayOnClickListener);
		b.setTag(Reminder.RECCURING_FRIDAY);
		
		b = (ToggleButton)getView().findViewById(R.id.editTravelReminderToggleSaturday);
		b.setChecked(this.existingReminder.getRecurring(Reminder.RECCURING_SATURDAY));
		b.setOnClickListener(this.recurringDayOnClickListener);
		b.setTag(Reminder.RECCURING_SATURDAY);
		
		b = (ToggleButton)getView().findViewById(R.id.editTravelReminderToggleSunday);
		b.setChecked(this.existingReminder.getRecurring(Reminder.RECCURING_SUNDAY));
		b.setOnClickListener(this.recurringDayOnClickListener);
		b.setTag(Reminder.RECCURING_SUNDAY);
	}
	
	public void onRecurringDayToggle(View view){
		ToggleButton b = (ToggleButton)view;
		
		int day = (Integer)b.getTag();
		
		this.existingReminder.setRecurring(day, b.isChecked());
		RemindersHelper.saveReminder(this.existingReminder);
	}
}
