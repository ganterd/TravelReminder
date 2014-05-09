package com.ganterd.travelreminder.test;

import com.ganterd.travelreminder.Reminder;

import junit.framework.TestCase;

public class ReminderTest extends TestCase {
	Reminder reminder;
	
	@Override
	public void setUp(){
		reminder = new Reminder();
	}

	public void testReminderTestMinutesOverflow(){
		reminder.setReminderLeadTimeMinutes(130);
		
		assertEquals("Minutes didn't overflow into hours.", 2, reminder.getReminderLeadTimeHours());
		assertEquals("Minutes weren't carried as expected", 10, reminder.getReminderLeadTimeMinutes());
	}
}
