package com.ganterd.travelreminder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.ganterd.travelreminder.fragments.ReminderEditFragment;
import com.ganterd.travelreminder.fragments.ReminderEditLocationInfo;

// TODO: Add Google Maps Selection
public class CreateTravelReminderActivity extends FragmentActivity {
	CreateTravelReminderPagerAdapter pagerAdapter;
	ViewPager viewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_travel_reminder);
		
		pagerAdapter = new CreateTravelReminderPagerAdapter(getSupportFragmentManager());
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

	/**
	 * Class that handles the pager adapter
	 * 
	 * @author I072979
	 */
	public class CreateTravelReminderPagerAdapter extends FragmentStatePagerAdapter {
	    public CreateTravelReminderPagerAdapter(FragmentManager fm) {
	        super(fm);
	    }

	    @Override
	    public Fragment getItem(int i) {
	        switch (i) {
				case 0:
					return new ReminderEditFragment();
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
