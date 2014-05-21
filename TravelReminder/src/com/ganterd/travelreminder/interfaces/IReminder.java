package com.ganterd.travelreminder.interfaces;

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
	
	public String getReminderID();
	
	public String getReminderName();
	
	public int getReminderLeadTimeMinutes();
	
	public int getReminderLeadTimeHours();
	
	public double[] getOrigin();
	
	public LatLng getOriginLatLng();
	
	public double[] getDestination();
	
	public LatLng getDestinationLatLng();
}
