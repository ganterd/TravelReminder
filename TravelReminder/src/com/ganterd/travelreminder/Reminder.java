package com.ganterd.travelreminder;

import java.io.Serializable;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ganterd.travelreminder.interfaces.IReminder;
import com.google.android.gms.maps.model.LatLng;

@SuppressWarnings("serial")
public class Reminder implements IReminder, Serializable{
	public static final int MODE_DRIVING = 0;
	public static final int MODE_WALKING = 1;
	public static final int MODE_CYCLING = 2;
	public static final int MODE_PUBLIC_TRANSIT = 3;
	
	public static final int RECCURING_MONDAY = 0;
	public static final int RECCURING_TUESDAY = 1;
	public static final int RECCURING_WEDNESDAY = 2;
	public static final int RECCURING_THURSDAY = 3;
	public static final int RECCURING_FRIDAY = 4;
	public static final int RECCURING_SATURDAY = 5;
	public static final int RECCURING_SUNDAY = 6;
	
	private String name = null;
	private int reminderLeadTimeMinutes = 0;
	private int reminderLeadTimeHours = 0;
	private Calendar destinationTargetTime = null;
	private String id = null;
	private double[] origin = new double[2];
	private double[] destination = new double[2];
	private int travelMode = 0; 
	private boolean[] recurringDayStates = new boolean[7];

	/**
	 * Default constructor. Sets most things to empty values except the destination target time
	 * which is set to the current time when object is created. 
	 */
	public Reminder() {
		this.name = "";
		this.id = "";
		this.destinationTargetTime = Calendar.getInstance();
		this.destinationTargetTime.setTimeInMillis(0);
	}

	/**
	 * Constructor that initializes all values.
	 * @param id The ID of the reminder.
	 * @param name The user friendly name of the reminder.
	 * @param leadTimeMinutes The reminder lead time in minutes.
	 * @param leadTimeHours The reminder lead time in hours.
	 * @param targetTime The reminder destination target time.
	 */
	public Reminder(String id, String name, int leadTimeMinutes, int leadTimeHours, Calendar targetTime) {
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

	@Override
	public void setArrivalTime(Calendar t) {
		this.destinationTargetTime = t;
	}

	@Override
	public Calendar getArrivalTime() {
		return this.destinationTargetTime;
	}

	@Override
	@JsonIgnore
	public void setArrivalTime(int hourOfDay, int minutes) {
		this.destinationTargetTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
		this.destinationTargetTime.set(Calendar.MINUTE, minutes);
	}

	@Override
	@JsonIgnore
	public int getArrivalTimeHour() {
		return this.destinationTargetTime.get(Calendar.HOUR_OF_DAY);
	}

	@Override
	@JsonIgnore
	public int getArrivalTimeMinutes() {
		return this.destinationTargetTime.get(Calendar.MINUTE);
	}
	
	public void setRecurringDayStates(boolean[] states){
		if(states.length != this.recurringDayStates.length)
			return;
		this.recurringDayStates = states;
	}
	
	public boolean[] getRecurringDayStates(){
		return this.recurringDayStates;
	}
	
	public void setRecurring(int day, boolean recurring){
		if(day < 0 || day >= 7)
			return;
		
		this.recurringDayStates[day] = recurring;
	}
	
	public boolean getRecurring(int day){
		if(day < 0 || day > 6)
			return false;
		
		return this.recurringDayStates[day];
	}
	
	@JsonIgnore
	public boolean[] getAllRecurringStates(){
		return this.recurringDayStates;
	}
}
