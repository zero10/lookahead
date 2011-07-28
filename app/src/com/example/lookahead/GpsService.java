package com.example.lookahead;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GpsService extends Service implements SensorEventListener, LocationListener {

	private LookaheadDbAdapter dbAdapter;
	private static final String TAG = "GpsService";
	private SensorManager sensorManager;
	private Sensor accelerometer;
	private LocationManager locationManager;


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		Toast.makeText(this, "GPS Service Created", Toast.LENGTH_SHORT).show();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);        
    	sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    	locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    	dbAdapter = new LookaheadDbAdapter(this);
    	dbAdapter.open();
		Log.d(TAG, "onCreate");
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "GPS Service Stopped", Toast.LENGTH_SHORT).show();
    	sensorManager.unregisterListener(this);
    	locationManager.removeUpdates(this);
    	dbAdapter.close();
		Log.d(TAG, "onDestroy");
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "GPS Service Started", Toast.LENGTH_SHORT).show();
		Log.d(TAG, "onStart");
	}
	   public void onSensorChanged(SensorEvent sensorEvent) {
	    	 if (sensorEvent.sensor == accelerometer)
	    	 {
		    	 Log.d(TAG, "onSensorChanged: " + sensorEvent.sensor.getName() + ", x: " + 
			    	 	 sensorEvent.values[0] + ", y: " + sensorEvent.values[1] + ", z: " + sensorEvent.values[2]);
	    		 dbAdapter.insertAccelLog(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
	    	 }
	    	 else
	    	 {
	    		 Log.d(TAG,"onSensorChanged(unknown): " + sensorEvent.sensor.getName());
	    	 }
	    }
	    
	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    	 if (sensor == accelerometer)
	    	 {
	    		 Log.d(TAG, "onAccuracyChanged: " + sensor.getName() + ", accuracy: " + accuracy);
	    	 }
	    	 else
	    	 {
	    		 Log.d(TAG,"onAccuracyChanged(unknown): " + sensor.getName());
	    	 }
	    }
	    
	    public void onLocationChanged(Location location)
	    {
	    	location.getLatitude();
	    	location.getLongitude();
	    	String Text = "My current location is: Latitud = " + location.getLatitude() + "Longitud = " + location.getLongitude();
	    	Toast.makeText(this, Text, Toast.LENGTH_SHORT).show();	    
	    }
	    
	    public void onProviderDisabled(String provider)
	    {
			Toast.makeText(this, "GPS Disabled", Toast.LENGTH_SHORT).show();
	    }
	    
	    public void onProviderEnabled(String provider)
	    {
			Toast.makeText(this, "GPS Enabled", Toast.LENGTH_SHORT).show();
	    }
	    
	    public void onStatusChanged(String provider, int status, Bundle extras)
	    {
	    	switch (status) {
			case LocationProvider.OUT_OF_SERVICE:
				Log.v(TAG, "Status Changed: Out of Service");
				Toast.makeText(this, "Status Changed: Out of Service",
						Toast.LENGTH_SHORT).show();
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				Log.v(TAG, "Status Changed: Temporarily Unavailable");
				Toast.makeText(this, "Status Changed: Temporarily Unavailable",
						Toast.LENGTH_SHORT).show();
				break;
			case LocationProvider.AVAILABLE:
				Log.v(TAG, "Status Changed: Available");
				Toast.makeText(this, "Status Changed: Available",
						Toast.LENGTH_SHORT).show();
				break;
			}	    }
}
