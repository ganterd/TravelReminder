package com.ganterd.travelreminder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.ganterd.travelreminder.R;
import com.ganterd.travelreminder.Reminder;
import com.ganterd.travelreminder.RemindersHelper;

public class ReminderEditInfoRecurringDaysAdapter extends BaseExpandableListAdapter{
	private final Context context;
	private final String[] dayStrings;
	private final boolean[] states;
	private Reminder reminder;
	
	public ReminderEditInfoRecurringDaysAdapter(Context context, Reminder r){
		this.reminder = r;
		this.context = context;
		this.dayStrings = context.getResources().getStringArray(R.array.days);
		this.states = r.getAllRecurringStates();
	}

	@Override
	public Object getChild(int arg0, int position) {
		return null;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.reminder_edit_details_general_recurring_day_row, parent, false);
		}
        
        TextView recurringDayLabel = (TextView) convertView.findViewById(R.id.reminder_edit_general_recurring_day_label);
        CheckBox recurringDayCheckBox = (CheckBox) convertView.findViewById(R.id.reminder_edit_general_recurring_day_check);
        
        recurringDayLabel.setText(dayStrings[childPosition]);
        recurringDayCheckBox.setChecked(states[childPosition]);
        
        convertView.setTag(childPosition);
        
        convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CheckBox c = (CheckBox)v.findViewById(R.id.reminder_edit_general_recurring_day_check);
				c.setChecked(!c.isChecked());
				int day = (Integer) v.getTag();
				reminder.setRecurring(day, c.isChecked());
				RemindersHelper.saveReminder(reminder);
			}
		});

        return convertView;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		return 1;
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(convertView == null)
			convertView = inflater.inflate(R.layout.reminder_edit_details_general_recurring_group, null);
		
		((CheckedTextView)convertView).setChecked(isExpanded);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
}
