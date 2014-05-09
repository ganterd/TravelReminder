package com.example.travelreminder;

import android.text.format.Time;

import com.google.gson.annotations.*;

public class Reminder {
	@Expose
	String name = null;
	
	@Expose
	int reminderLeadTime = 0;
	
	@Expose
	Time destinationTargetTime = null;
	
	@Expose
	String id = null;
}
