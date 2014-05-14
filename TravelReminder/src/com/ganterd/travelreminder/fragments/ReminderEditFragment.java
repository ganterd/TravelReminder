package com.ganterd.travelreminder.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.ganterd.travelreminder.R;
import com.ganterd.travelreminder.Reminder;

public class ReminderEditFragment extends Fragment {
	public static final String ARG_EXISTING_REMINDER_NAME = "ARG_EXISTING_REMINDER_NAME";
	public static final String ARG_EXISTING_REMINDER_LEAD_TIME = "ARG_EXISTING_REMINDER_LEAD_TIME";
	
	Reminder existingReminder = null;
	
	public static ReminderEditFragment newInstance(Reminder existingReminder){
		ReminderEditFragment f = new ReminderEditFragment();
		
		if(existingReminder != null){
			Bundle args = new Bundle();
			args.putString(ReminderEditFragment.ARG_EXISTING_REMINDER_NAME, existingReminder.getReminderName());
			f.setArguments(args);
		}
		
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Bundle args = getArguments();
		
		RelativeLayout rl = (RelativeLayout)inflater.inflate(R.layout.reminder_details, container, false);
		
		Spinner spinner = (Spinner) rl.findViewById(R.id.createTravelReminderAlertTimeSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.array_alert_lead_times, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		if(args != null){
			EditText reminderNameInput = (EditText) rl.findViewById(R.id.textReminderName);
			reminderNameInput.setText(args.getString(ARG_EXISTING_REMINDER_NAME));
		}
		
		return rl;
	}
}
