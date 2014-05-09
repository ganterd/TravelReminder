package com.ganterd.travelreminder;

import java.util.Date;

import com.ganterd.travelreminder.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

// TODO: Add Google Maps Selection
public class CreateTravelReminderActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_travel_reminder);

		setupActionBar();
		
		Spinner spinner = (Spinner) findViewById(R.id.createTravelReminderAlertTimeSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.array_alert_lead_times, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	public void createNewTravelReminder(View view){
		RemindersHelper.saveReminder(new Reminder("testID", "ReminderName", 10, 2, new Date()));
		
		finish();
	}
}
