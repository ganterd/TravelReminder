package com.ganterd.travelreminder.fragments;

import java.util.Date;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.ganterd.travelreminder.R;
import com.ganterd.travelreminder.Reminder;
import com.ganterd.travelreminder.RemindersHelper;

public class ReminderEditFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		RelativeLayout rl = (RelativeLayout)inflater.inflate(R.layout.reminder_details, container, false);
		
		Spinner spinner = (Spinner) rl.findViewById(R.id.createTravelReminderAlertTimeSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.array_alert_lead_times, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		return rl;
	}
	
	public void createNewTravelReminder(View view){
		Date d = new Date();
		
		String reminderID = d.toString().replace(" ", "");
		
		EditText editTextReminder = (EditText) getActivity().findViewById(R.id.textReminderName);
		String reminderName = editTextReminder.getText().toString();

		RemindersHelper.saveReminder(new Reminder(reminderID, reminderName, 10, 2, new Date()));
		
		getActivity().finish();
	}
}
