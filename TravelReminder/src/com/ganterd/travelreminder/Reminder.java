package com.ganterd.travelreminder;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ganterd.travelreminder.interfaces.IReminder;
import com.google.android.gms.maps.model.LatLng;

@SuppressWarnings("serial")
public class Reminder implements IReminder, Serializable{
	public static final int MODE_DRIVING = 0;
	public static final int MODE_WALKING = 1;
	public static final int MODE_CYCLING = 2;
	public static final int MODE_PUBLIC_TRANSIT = 3;
	
	String name = null;
	int reminderLeadTimeMinutes = 0;
	int reminderLeadTimeHours = 0;
	Date destinationTargetTime = null;
	String id = null;
	double[] origin = new double[2];
	double[] destination = new double[2];
	private int travelMode = 0; 

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
	
	public void setTravelMode(int mode){
		this.travelMode = mode;
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

	@Override
	public void setOrigin(double[] o){
		this.origin = o;
	}
	
	@Override
	@JsonIgnore
	public void setOriginLatLng(LatLng o) {
		this.origin[0] = o.latitude;
		this.origin[1] = o.longitude;
	}
	
	@Override
	public void setDestination(double[] d){
		this.destination = d;
	}

	@Override
	@JsonIgnore
	public void setDestinationLatLng(LatLng d) {
		this.destination[0] = d.latitude;
		this.destination[1] = d.longitude;
	}
	
	@Override
	public double[] getOrigin(){
		return this.origin;
	}

	@Override
	@JsonIgnore
	public LatLng getOriginLatLng() {
		return new LatLng(this.origin[0], this.origin[1]);
	}
	
	@Override
	public double[] getDestination(){
		return this.destination;
	}

	@Override
	@JsonIgnore
	public LatLng getDestinationLatLng() {
		return new LatLng(this.destination[0], this.destination[1]);
	}
	
	public int getTravelMode(){
		return this.travelMode;
	}
}
