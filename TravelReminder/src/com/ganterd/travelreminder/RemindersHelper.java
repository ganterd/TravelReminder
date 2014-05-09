package com.ganterd.travelreminder;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.content.Context;
import android.util.Log;


public class RemindersHelper{
	private static Context context;
	private static final String fileExtension = ".reminder"; 
	
	public static void init(Context base) {
		context = base;
	}
	
	public static Reminder[] getAllReminders(){
		File[] files = getAllReminderFiles();
		
		Reminder[] allReminders = new Reminder[files.length];
		Log.d("RemindersHelper", "There are " + files.length + " files in internal storage");
		ObjectMapper mapper = new ObjectMapper();
		for(int i = 0; i < files.length; i++){
			Log.d("RemindersHelper", files[i].getName());
			try {
				allReminders[i] = mapper.readValue(files[i], Reminder.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return allReminders;
	}
	
	public static void saveReminder(Reminder reminder){
		ObjectMapper mapper = new ObjectMapper();
		try {
			File f = new File(context.getFilesDir(), reminder.id + fileExtension);
			Log.i("RemindersHelper", "Saving reminder to " + f.getAbsolutePath());
			mapper.writeValue(f, reminder);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Reminder getReminder(String reminderID){
		File f = new File(context.getFilesDir(), reminderID + fileExtension);
		
		/* Return null if the file doesn't exist */
		if(!f.isFile() || !f.canRead())
			return null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Reminder r = mapper.readValue(f, Reminder.class);
			return r;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static void deleteAllReminders() {
		File[] files = getAllReminderFiles();
		
		for(File f : files){
			f.delete();
		}
	}
	
	private static File[] getAllReminderFiles(){
		File[] files = context.getFilesDir().listFiles(new FileFilter() {
	        @Override
	        public boolean accept(File pathname) {
	           return pathname.getName().endsWith(fileExtension);
	        }
	     });
		return files;
	}
}
