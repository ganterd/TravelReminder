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
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ReminderEditLocationInfo extends Fragment {
	private SupportMapFragment mapFragment;
	private GoogleMap map;
	private GoogleMapOptions mapOptions = new GoogleMapOptions();
	
	private GoogleMap.OnMapLongClickListener longClickListener = new GoogleMap.OnMapLongClickListener() {
		@Override
		public void onMapLongClick(LatLng arg0) {
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mapOptions.compassEnabled(true);
		mapOptions.camera(new CameraPosition(new LatLng(53.3282161, -6.2348722), 11, 0, 0));
		
		RelativeLayout rl = (RelativeLayout)inflater.inflate(R.layout.reminder_details_location_info, container, false);
		return rl;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    FragmentManager fm = getChildFragmentManager();
	    mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.mapReminder);
	    if (mapFragment == null) {
	        mapFragment = SupportMapFragment.newInstance(mapOptions);
	        
	        fm.beginTransaction().replace(R.id.mapReminder, mapFragment).commit();
	    }
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    if (map == null) {
	        map = mapFragment.getMap();
	        
	        map.setMyLocationEnabled(true);
	        
	        UiSettings uiSettings = map.getUiSettings();
	        uiSettings.setMyLocationButtonEnabled(true);
	        uiSettings.setCompassEnabled(true);
	        uiSettings.setZoomControlsEnabled(false);
	        
	        map.setOnMapLongClickListener(longClickListener);
	        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)));
	    }
	}
}
