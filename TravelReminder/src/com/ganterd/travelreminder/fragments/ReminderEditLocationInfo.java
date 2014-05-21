package com.ganterd.travelreminder.fragments;

import java.text.DecimalFormat;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.ganterd.travelreminder.Directions;
import com.ganterd.travelreminder.Reminder;
import com.ganterd.travelreminder.Directions.OnDirectionsReadyListener;
import com.ganterd.travelreminder.R;
import com.ganterd.travelreminder.RemindersHelper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class ReminderEditLocationInfo extends Fragment implements OnDirectionsReadyListener{
	public static final String ARG_EXISTING_REMINDER = "ARG_EXISTING_REMINDER";
	
	private SupportMapFragment mapFragment;
	private GoogleMap map;
	private GoogleMapOptions mapOptions = new GoogleMapOptions();
	
	private Reminder existingReminder = null;
	
	private LatLng origin = null;
	private LatLng destination = null;
	
	private PolylineOptions path = null;
	private Directions directions = new Directions();
	
	private GoogleMap.OnMapClickListener clickListener = new GoogleMap.OnMapClickListener() {
		@Override
		public void onMapClick(LatLng arg0) {
			setPosition(arg0);
		}
	};
	
	/**
	 * Create a new instance of the ReminderEditLocationInfo fragment by passing an existing
	 * reminder.
	 * 
	 * @param existingReminder
	 * @return
	 */
	public static ReminderEditLocationInfo newInstance(Reminder existingReminder){
		ReminderEditLocationInfo f = new ReminderEditLocationInfo();
		
		if(existingReminder != null){
			Bundle args = new Bundle();
			args.putSerializable(ReminderEditLocationInfo.ARG_EXISTING_REMINDER, existingReminder);
			f.setArguments(args);
		}
		
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mapOptions.compassEnabled(true);
		mapOptions.camera(new CameraPosition(new LatLng(53.3282161, -6.2348722), 11, 0, 0));
		
		RelativeLayout rl = (RelativeLayout)inflater.inflate(R.layout.reminder_details_location_info, container, false);
		
		EditText startPointInput = (EditText) rl.findViewById(R.id.textReminderStartPoint);
		startPointInput.setInputType(EditorInfo.TYPE_NULL);
		
		EditText endPointInput = (EditText) rl.findViewById(R.id.textReminderEndPoint);
		endPointInput.setInputType(EditorInfo.TYPE_NULL);
		
		Bundle args = getArguments();
		if(args != null){
			this.existingReminder = (Reminder)args.getSerializable(ARG_EXISTING_REMINDER);
		}
		
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
	        
	        map.setOnMapClickListener(clickListener);
	    }
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
	    super.setUserVisibleHint(isVisibleToUser);
	    if (isVisibleToUser) {
	    	updateFragment();
	    }
	}
	
	public void updateFragment(){
		this.existingReminder = RemindersHelper.getReminder(existingReminder.getReminderID());
	    this.origin = this.existingReminder.getOriginLatLng();
		this.destination = this.existingReminder.getDestinationLatLng();
	    
	    updateTextFields();
	    drawMarkers();
	    
	    directions.setDetailsFromReminder(this.existingReminder);
		directions.requestDirections(this);
	}
	
	public void updateTextFields(){
		EditText startPointInput = (EditText) getView().findViewById(R.id.textReminderStartPoint);
		EditText endPointInput = (EditText) getView().findViewById(R.id.textReminderEndPoint);
		
		DecimalFormat df = new DecimalFormat("#.####");
		startPointInput.setText(df.format(this.origin.latitude) + ", " + df.format(this.origin.longitude));
		endPointInput.setText(df.format(this.destination.latitude) + ", " + df.format(this.destination.longitude));
	}
	
	public void drawMarkers(){
		map.clear();
		
		if(origin != null)
			map.addMarker(new MarkerOptions().position(origin).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		
		if(destination != null)
			map.addMarker(new MarkerOptions().position(destination).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
	}
	
	public void drawPath(){
		if(path != null){
			map.addPolyline(path);
		}
	}
	
	@Override
	public void onDirectionsReady(JSONObject directions){
		this.path = Directions.getDirectionsPath(directions);
		drawPath();
	}
	
	public void setPosition(LatLng p){
		EditText originInput = (EditText)getView().findViewById(R.id.textReminderStartPoint);
		EditText destinationInput = (EditText)getView().findViewById(R.id.textReminderEndPoint);
		
		if(originInput.hasFocus()){
			origin = p;
			originInput.setText(origin.toString());

			if(this.existingReminder != null){
				this.existingReminder.setOriginLatLng(this.origin);
				RemindersHelper.saveReminder(this.existingReminder);
			}
		}else if(destinationInput.hasFocus()){
			destination = p;
			destinationInput.setText(destination.toString());
			
			if(this.existingReminder != null){
				this.existingReminder.setDestinationLatLng(this.destination);
				RemindersHelper.saveReminder(this.existingReminder);
			}
		}
		
		if(origin != null && destination != null){
			directions.setDetailsFromReminder(this.existingReminder);
			directions.requestDirections(this);
		}
		
		updateTextFields();
		drawMarkers();
	}
}
