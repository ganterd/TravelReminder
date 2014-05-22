package com.ganterd.travelreminder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
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
	public View getView(int position, View convertView, ViewGroup parent){
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.reminder_edit_details_general_recurring_day_row, parent, false);
        
        TextView recurringDayLabel = (TextView) rowView.findViewById(R.id.reminder_edit_general_recurring_day_label);
        CheckBox recurringDayCheckBox = (CheckBox) rowView.findViewById(R.id.reminder_edit_general_recurring_day_check);
        
        recurringDayLabel.setText(dayStrings[position]);
        recurringDayCheckBox.setChecked(states[position]);
        
        rowView.setTag(position);
        
        rowView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CheckBox c = (CheckBox)v.findViewById(R.id.reminder_edit_general_recurring_day_check);
				reminder.setRecurring(Integer.parseInt(((String)v.getTag())), c.isChecked());
				RemindersHelper.saveReminder(reminder);
			}
		});

        return rowView;
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3, ViewGroup arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		return null;
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
