package com.ganterd.travelreminder.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface IReminder {
	public void setReminderID(String newID);
	
	public void setReminderName(String newName);
	
	public void setReminderLeadTimeMinutes(int minutes);
	
	public void setReminderLeadTimeHours(int hours);
	
	public String getReminderID();
	
	public String getReminderName();
	
	public int getReminderLeadTimeMinutes();
	
	public int getReminderLeadTimeHours();
	
	public JSONObject toJSONObject() throws JSONException;
	
	public String toJSONString() throws JSONException;
	
	public void fromJSONObject(JSONObject obj) throws JSONException;
	
	public void fromJSONString(String jsonString) throws JSONException;
}
