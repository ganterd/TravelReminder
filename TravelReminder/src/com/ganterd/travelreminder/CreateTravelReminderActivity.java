package com.ganterd.travelreminder;

import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;

import com.ganterd.travelreminder.fragments.ReminderEditFragment;
import com.ganterd.travelreminder.fragments.ReminderEditLocationInfo;

// TODO: Add Google Maps Selection
public class CreateTravelReminderActivity extends FragmentActivity {
	/* Intent Parameters */
	public static final String PARAM_REMINDER_ID = "PARAM_REMINDER_ID";
	
	ViewPager viewPager;
	
	private Reminder existingReminder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_travel_reminder);
		
		Intent intent = getIntent();
		String existingReminderID = intent.getStringExtra(PARAM_REMINDER_ID);
		
		if(existingReminderID != null && !existingReminderID.isEmpty()){
			existingReminder = RemindersHelper.getReminder(existingReminderID);
		}

		setupActionBar();
		
		ReminderEditFragment reminderEditFragment = ReminderEditFragment.newInstance(existingReminder);
		ReminderEditLocationInfo reminderLocationFragment = ReminderEditLocationInfo.newInstance(existingReminder);
		
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.edit_reminder_general_fragment, reminderEditFragment);
		ft.replace(R.id.edit_reminder_location_fragment, reminderLocationFragment);
		ft.commit();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	public void createNewTravelReminder(View view){
		/* Get reminder name */
		EditText editTextReminder = (EditText) findViewById(R.id.textReminderName);
		String reminderName = editTextReminder.getText().toString();

		if(existingReminder != null){
			existingReminder.setReminderName(reminderName);
			RemindersHelper.saveReminder(existingReminder);
		}else{
			/* Create reminder ID */
			Date d = new Date();
			String reminderID = d.toString().replace(" ", "");
			
			RemindersHelper.saveReminder(new Reminder(reminderID, reminderName, 10, 2, Calendar.getInstance()));
		}
		
		finish();
	}
}
