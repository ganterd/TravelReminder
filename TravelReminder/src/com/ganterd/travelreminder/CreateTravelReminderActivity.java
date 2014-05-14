package com.ganterd.travelreminder;

import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;

import com.ganterd.travelreminder.fragments.ReminderEditFragment;
import com.ganterd.travelreminder.fragments.ReminderEditLocationInfo;

// TODO: Add Google Maps Selection
public class CreateTravelReminderActivity extends FragmentActivity {
	/* Intent Parameters */
	public static final String PARAM_REMINDER_ID = "PARAM_REMINDER_ID";
	
	CreateTravelReminderPagerAdapter pagerAdapter;
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
		
		pagerAdapter = new CreateTravelReminderPagerAdapter(getSupportFragmentManager(), existingReminder);
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(pagerAdapter);

		setupActionBar();
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
			
			RemindersHelper.saveReminder(new Reminder(reminderID, reminderName, 10, 2, new Date()));
		}
		
		finish();
	}

	/**
	 * Class that handles the pager adapter
	 * 
	 * @author I072979
	 */
	public class CreateTravelReminderPagerAdapter extends FragmentStatePagerAdapter {
		Reminder existingReminder = null;
		
	    public CreateTravelReminderPagerAdapter(FragmentManager fm, Reminder existingReminder) {
	        super(fm);
	        this.existingReminder = existingReminder;
	    }

	    @Override
	    public Fragment getItem(int i) {
	        switch (i) {
				case 0:
					return ReminderEditFragment.newInstance(existingReminder);
				case 1:
					return new ReminderEditLocationInfo();
				default:
					return null;
			}
	    }

	    @Override
	    public int getCount() {
	        return 2;
	    }

	    @Override
	    public CharSequence getPageTitle(int position) {
	    	int stringID = 0;
	    	switch (position) {
				case 0:
					stringID = R.string.reminder_details_tab_title_details;
					break;
				case 1:
					stringID = R.string.reminder_details_tab_title_location_info;
					break;
				default:
					return "";
			}
	    	
	    	return getResources().getString(stringID);
	    }
	}
}
