package com.ganterd.travelreminder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class Directions {
	protected LatLng origin;
	protected LatLng destination;
	protected OnDirectionsReadyListener listener;
	protected int mode = Reminder.MODE_DRIVING; // See Reminder class for modes
	protected Date departureTime = new Date();
	
	public interface OnDirectionsReadyListener{
		public void onDirectionsReady(JSONObject directions);
	}
	
	public void setDetailsFromReminder(Reminder r){
		this.setOrigin(r.getOriginLatLng());
		this.setDestination(r.getDestinationLatLng());
		this.mode = r.getTravelMode();
	}
	
	public void setOrigin(LatLng origin){
		this.origin = origin;
	}
	
	public void setDestination(LatLng destination){
		this.destination = destination;
	}
	
	public void setMode(int mode){
		this.mode = mode;
	}
	
	public void requestDirections(OnDirectionsReadyListener listener){
		this.listener = listener;
		new Requestor().execute();
	}
        
	public static PolylineOptions getDirectionsPath(JSONObject directions){
		PolylineOptions polyLine = new PolylineOptions();
		
		try {
			
			
			JSONArray routes = directions.getJSONArray("routes");
			
			if(routes.length() > 0){
				JSONObject route = (JSONObject) routes.get(0);
				if(route.has("overview_polyline")){
					JSONObject overview_polyline = (JSONObject)route.get("overview_polyline");
					
					ArrayList<LatLng> points = decodePoly(overview_polyline.getString("points"));
					for(LatLng p : points){
						polyLine.add(p);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return polyLine;
	}
	
	private class Requestor extends AsyncTask<Void, Void, JSONObject>{
		@Override
		protected JSONObject doInBackground(Void... params){
			String url = "http://maps.googleapis.com/maps/api/directions/json?";
			url += "origin=" + origin.latitude + "," + origin.longitude;
			url += "&destination=" + destination.latitude + "," + destination.longitude;
			url += "&sensor=false";
			
			String modeString = "";
			switch(mode){
			case Reminder.MODE_DRIVING:
				modeString = "driving";
				break;
			case Reminder.MODE_WALKING:
				modeString = "walking";
				break;
			case Reminder.MODE_CYCLING:
				modeString = "bicycling";
				break;
			case Reminder.MODE_PUBLIC_TRANSIT:
				modeString = "transit";
				break;
			default:
				modeString = "walking";
			}
			url += "&mode=" + modeString;
			
			if(departureTime != null){
				String t = Long.toString(departureTime.getTime()/1000);
				url += "&departure_time=" + t;
			}
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpGet httpGet = new HttpGet(url);
			
			try {
				
				HttpResponse resp = httpClient.execute(httpGet, localContext);
				
				if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					String data = EntityUtils.toString(resp.getEntity());
					JSONObject obj;
					try {
						obj = new JSONObject(data);
						return obj;
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
				}
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return new JSONObject();
		}
		
		@Override
		protected void onPostExecute(JSONObject directions){
			listener.onDirectionsReady(directions);
		}
	}
	
	private static ArrayList<LatLng> decodePoly(String encoded) {
		ArrayList<LatLng> points = new ArrayList<LatLng>();
		
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;
        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;                 
                shift += 5;             
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;
            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;  
                shift += 5;             
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;
 
            LatLng position = new LatLng((double) lat / 1E5, (double) lng / 1E5);
            points.add(position);
        }
        
        return points;
	}
}
