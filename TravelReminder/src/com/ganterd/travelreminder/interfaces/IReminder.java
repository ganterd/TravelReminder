package com.ganterd.travelreminder.interfaces;

import java.util.Calendar;

import com.google.android.gms.maps.model.LatLng;

public interface IReminder {
	public void setReminderID(String newID);
	
	public void setReminderName(String newName);
	
	public void setReminderLeadTimeMinutes(int minutes);
	
	public void setReminderLeadTimeHours(int hours);
	
	public void setOrigin(double[] o);
	
	public void setOriginLatLng(LatLng o);
	
	public void setDestination(double[] d);
	
	public void setDestinationLatLng(LatLng d);
	
	public void setArrivalTime(Calendar t);
	
	public void setArrivalTime(int hourOfDay, int minutes);
	
	public String getReminderID();
	
	public String getReminderName();
	
	public int getReminderLeadTimeMinutes();
	
	public int getReminderLeadTimeHours();
	
	public double[] getOrigin();
	
	public LatLng getOriginLatLng();
	
	public double[] getDestination();
	
	public LatLng getDestinationLatLng();
	
	public Calendar getArrivalTime();
	
	public int getArrivalTimeHour();
	
	public int getArrivalTimeMinutes();
}
