package com.ganterd.travelreminder;

import com.ganterd.travelreminder.R;
import com.ganterd.travelreminder.adapters.MainActivityReminderListAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RemindersHelper.init(this);

        fillRemindersList();
    }
    
    @Override
    protected void onResume(){
    	super.onResume();
    	fillRemindersList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }   
    
    public void createNewTravelReminder(View view){
    	Intent intent = new Intent(this, CreateTravelReminderActivity.class);
    	startActivity(intent);
    }
    
    public void fillRemindersList(){
    	Reminder[] reminders = RemindersHelper.getAllReminders();
    	Log.d("MainActivity", "Listing " + reminders.length + " reminders.");
    	
    	String[] titles = new String[reminders.length];
    	String[] sub_titles = new String[reminders.length];
    	boolean[] states = new boolean[reminders.length];
    	String[] ids = new String[reminders.length];
        for(int i = 0; i < reminders.length; i++){
        	if(reminders[i] == null){
        		Log.e("MainActivity", "For some reason one of the loaded reminders is null");
        		continue;
        	}
            titles[i] = reminders[i].getReminderName();
            sub_titles[i] = reminders[i].getReminderLeadTimeHours() + ":" + reminders[i].getReminderLeadTimeMinutes();
            states[i] = true;
            ids[i] = reminders[i].getReminderID();
        }
        
        
        MainActivityReminderListAdapter adapter = new MainActivityReminderListAdapter(this, titles, sub_titles, states, ids);
        setListAdapter(adapter);
    }
    
    public void actionDeleteAllReminders(MenuItem menuItem){
    	RemindersHelper.deleteAllReminders();
    	fillRemindersList();
    }
}
