package com.ganterd.travelreminder.interfaces;

public interface IReminder {
	public void setReminderID(String newID);
	
	public void setReminderName(String newName);
	
	public void setReminderLeadTimeMinutes(int minutes);
	
	public void setReminderLeadTimeHours(int hours);
	
	public String getReminderID();
	
	public String getReminderName();
	
	public int getReminderLeadTimeMinutes();
	
	public int getReminderLeadTimeHours();
}
