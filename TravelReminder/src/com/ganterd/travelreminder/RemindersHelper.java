package com.ganterd.travelreminder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.ContextWrapper;

public class RemindersHelper{
	private static Context context;
	private final static String fileName = "reminders";
	
	public static void init(Context base) {
		context = base;
	}

	/**
	 * Get the list of reminders from the internal storage
	 * @return
	 */
	private static JSONArray getRemindersJSONList(){
		File f = new File(context.getFilesDir(), fileName);
		if(!f.isFile() || !f.canRead())
			return null;
		
		try {
			FileInputStream inputStream = new FileInputStream(fileName);
			
			byte[] buffer = new byte[(int) f.length()];
			inputStream.read(buffer);
			String fileString = new String(buffer);
			
			JSONArray jsonArray = new JSONArray(fileString);
			return jsonArray;
		} catch (IOException e) {
			return null;
		} catch (JSONException e){
			return null;
		}
	}
	
	public void addReminder(Reminder reminder){

	}
	
	public void updateReminder(Reminder reminder){
		
	}
}
