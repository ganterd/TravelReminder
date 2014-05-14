package com.ganterd.travelreminder.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.ganterd.travelreminder.CreateTravelReminderActivity;
import com.ganterd.travelreminder.R;

public class MainActivityReminderListAdapter extends ArrayAdapter<String>{
	private final Context context;
	private final String[] titles;
	private final String[] sub_titles;
	private final boolean[] states;
	private final String[] ids;
	
	public MainActivityReminderListAdapter(Context context, String[] titles, String[] sub_titles, boolean[] states, String[] ids){
		super(context, R.layout.main_reminders_row, titles);
		this.context = context;
		this.titles = titles;
		this.sub_titles = sub_titles;
		this.states = states;
		this.ids = ids;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.main_reminders_row, parent, false);
        
        TextView reminderTitle = (TextView) rowView.findViewById(R.id.reminderTitle);
        Switch reminderOnOffSwitch = (Switch) rowView.findViewById(R.id.reminderOnOffSwitch);
        TextView reminderSubTitle = (TextView) rowView.findViewById(R.id.reminderSubTitle);
        
        reminderTitle.setText(titles[position]);
        reminderSubTitle.setText(sub_titles[position]);
        reminderOnOffSwitch.setChecked(states[position]);
        
        rowView.setTag(ids[position]);
        
        rowView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, CreateTravelReminderActivity.class);
				intent.putExtra(CreateTravelReminderActivity.PARAM_REMINDER_ID, (String)v.getTag());
				context.startActivity(intent);
			}
		});

        return rowView;
	}
}
