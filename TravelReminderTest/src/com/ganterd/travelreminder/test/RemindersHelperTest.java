package com.ganterd.travelreminder.test;

import java.util.Calendar;

import junit.framework.TestCase;

import com.ganterd.travelreminder.Reminder;
import com.ganterd.travelreminder.RemindersHelper;

public class RemindersHelperTest extends TestCase {
	public void testAddAndGetReminder(){
		Reminder reminder = new Reminder("testReminderID", "Test Reminder", 10, 2, Calendar.getInstance());
		
		RemindersHelper.saveReminder(reminder);
		Reminder savedReminder = RemindersHelper.getReminder(reminder.getReminderID());
		
		assertNotNull("Loaded reminder is null", savedReminder);
		
		assertEquals("Expected reminder and saved/loaded reminder are different", reminder.getReminderID(), savedReminder.getReminderID());
		assertEquals("Expected reminder and saved/loaded reminder are different", reminder.getReminderName(), savedReminder.getReminderName());
		assertEquals("Expected reminder and saved/loaded reminder are different", reminder.getReminderLeadTimeHours(), savedReminder.getReminderLeadTimeHours());
		assertEquals("Expected reminder and saved/loaded reminder are different", reminder.getReminderID(), savedReminder.getReminderID());
	}
}
