package com.ganterd.travelreminder;

import java.util.Date;

import com.ganterd.travelreminder.interfaces.IReminder;

public class Reminder implements IReminder{
	String name = null;
	int reminderLeadTimeMinutes = 0;
	int reminderLeadTimeHours = 0;
	Date destinationTargetTime = null;
	String id = null;

	/**
	 * Default constructor. Sets most things to empty values except the destination target time
	 * which is set to the current time when object is created. 
	 */
	public Reminder() {
		this.name = "";
		this.id = "";
		this.destinationTargetTime = new Date();
	}

	/**
	 * Constructor that initializes all values.
	 * @param id The ID of the reminder.
	 * @param name The user friendly name of the reminder.
	 * @param leadTimeMinutes The reminder lead time in minutes.
	 * @param leadTimeHours The reminder lead time in hours.
	 * @param targetTime The reminder destination target time.
	 */
	public Reminder(String id, String name, int leadTimeMinutes, int leadTimeHours, Date targetTime) {
		this.id = id;
		this.name = name;
		this.setReminderLeadTimeHours(leadTimeHours);
		this.setReminderLeadTimeMinutes(leadTimeMinutes);
		this.destinationTargetTime = targetTime;
	}

	@Override
	public void setReminderID(String newID) {
		this.id = newID;
	}

	@Override
	public void setReminderName(String newName) {
		this.name = newName;
	}

	@Override
	public void setReminderLeadTimeMinutes(int minutes) {
		if(minutes >= 60){
			int hours = minutes / 60;
			reminderLeadTimeHours += hours;
			minutes = minutes % 60;
		}
		
		this.reminderLeadTimeMinutes = minutes;
	}

	@Override
	public void setReminderLeadTimeHours(int hours) {
		this.reminderLeadTimeHours = hours;	
	}

	@Override
	public String getReminderID() {
		return this.id;
	}

	@Override
	public String getReminderName() {
		return this.name;
	}

	@Override
	public int getReminderLeadTimeMinutes() {
		return this.reminderLeadTimeMinutes;
	}

	@Override
	public int getReminderLeadTimeHours() {
		return this.reminderLeadTimeHours;
	}
}
