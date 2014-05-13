package com.ganterd.travelreminder.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ganterd.travelreminder.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ReminderEditLocationInfo extends Fragment {
	private SupportMapFragment mapFragment;
	private GoogleMap map;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		RelativeLayout rl = (RelativeLayout)inflater.inflate(R.layout.reminder_details_location_info, container, false);
		
		return rl;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    FragmentManager fm = getChildFragmentManager();
	    mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.mapReminder);
	    if (mapFragment == null) {
	        mapFragment = SupportMapFragment.newInstance();
	        fm.beginTransaction().replace(R.id.mapReminder, mapFragment).commit();
	    }
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    if (map == null) {
	        map = mapFragment.getMap();
	        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)));
	    }
	}
}
