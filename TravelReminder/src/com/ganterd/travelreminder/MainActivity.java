package com.ganterd.travelreminder;

import com.ganterd.travelreminder.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

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
    	
    	String[] values = new String[reminders.length];
        for(int i = 0; i < reminders.length; i++){
            values[i] = reminders[i].getReminderName();
        }
    	
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.main_reminders_row, values);
        setListAdapter(adapter);
    }
    
    public void actionDeleteAllReminders(MenuItem menuItem){
    	RemindersHelper.deleteAllReminders();
    	fillRemindersList();
    }
}
