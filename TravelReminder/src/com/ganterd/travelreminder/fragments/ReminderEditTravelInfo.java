package com.ganterd.travelreminder.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.ganterd.travelreminder.R;
import com.ganterd.travelreminder.Reminder;
import com.ganterd.travelreminder.RemindersHelper;
import com.ganterd.travelreminder.adapters.ReminderEditInfoRecurringDaysAdapter;

public class ReminderEditTravelInfo extends Fragment implements ReminderEditArrivalTimePickerFragment.OnArrivalTimeUpdatedListener{
	public static final String ARG_EXISTING_REMINDER = "ARG_EXISTING_REMINDER";
	
	/* Variables */
	Reminder reminder = null;
	
	/* Listeners */
	OnItemSelectedListener travelModeOnItemSelectedListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			reminder.setTravelMode((int)id);
			RemindersHelper.saveReminder(reminder);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};
	
	public static ReminderEditTravelInfo newInstance(Reminder existingReminder){
		ReminderEditTravelInfo f = new ReminderEditTravelInfo();
		
		if(existingReminder != null){
			Bundle args = new Bundle();
			args.putSerializable(ReminderEditTravelInfo.ARG_EXISTING_REMINDER, existingReminder);
			f.setArguments(args);
		}
		
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Bundle args = getArguments();
		this.reminder = (Reminder)args.getSerializable(ReminderEditTravelInfo.ARG_EXISTING_REMINDER);
		
		RelativeLayout rl = (RelativeLayout)inflater.inflate(R.layout.reminder_edit_travel_info, container, false);
		
		EditText arrivalTimeEditText = (EditText) rl.findViewById(R.id.reminder_edit_travel_info_arrival_time);
		arrivalTimeEditText.setInputType(EditorInfo.TYPE_NULL);
		arrivalTimeEditText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showTimePickerDialog(getView());
			}
		});
		
		/* Set the options in the travel mode spinner */
		Spinner travelModeSpinner = (Spinner) rl.findViewById(R.id.reminder_edit_travel_info_mode);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.reminder_edit_travel_info_modes, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		travelModeSpinner.setAdapter(adapter);
		
		ExpandableListView recurringDays = (ExpandableListView) rl.findViewById(R.id.reminder_edit_travel_info_recurring_options_list);
		ReminderEditInfoRecurringDaysAdapter recurringDaysAdapter = new ReminderEditInfoRecurringDaysAdapter(getActivity(), this.reminder);
		recurringDays.setAdapter(recurringDaysAdapter);
		
		return rl;
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
	    super.setUserVisibleHint(isVisibleToUser);
	    if (isVisibleToUser) {
	    	updateFragment();
	    	((Spinner) getView().findViewById(R.id.reminder_edit_travel_info_mode)).setOnItemSelectedListener(this.travelModeOnItemSelectedListener);
	    }
	}
	
	public void showTimePickerDialog(View v){
		ReminderEditArrivalTimePickerFragment timePicker = ReminderEditArrivalTimePickerFragment.newInstance(this.reminder);
		timePicker.setListener(this);
		timePicker.show(getFragmentManager(), "timePicker");
	}
	
	public void updateFragment(){
		this.reminder = RemindersHelper.getReminder(reminder.getReminderID());
		
		EditText arrivalTimeEditText = (EditText) getView().findViewById(R.id.reminder_edit_travel_info_arrival_time);
		arrivalTimeEditText.setText(this.reminder.getArrivalTimeHour() + ":" + this.reminder.getArrivalTimeMinutes());
		
		Spinner travelModeSpinner = (Spinner) getView().findViewById(R.id.reminder_edit_travel_info_mode);
		travelModeSpinner.setSelection(this.reminder.getTravelMode());
	}

	@Override
	public void newTimeSelected() {
		updateFragment();
	}
}
