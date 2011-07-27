package com.example.lookahead;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GpsService extends Service implements SensorEventListener {

	private LookaheadDbAdapter dbAdapter;
	private static final String TAG = "GpsService";
	private SensorManager sensorManager;
	private Sensor accelerometer;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		Toast.makeText(this, "GPS Service Created", Toast.LENGTH_LONG).show();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    	sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    	dbAdapter = new LookaheadDbAdapter(this);
		Log.d(TAG, "onCreate");
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "GPS Service Stopped", Toast.LENGTH_LONG).show();
    	sensorManager.unregisterListener(this);
		Log.d(TAG, "onDestroy");
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "GPS Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
	}
	   public void onSensorChanged(SensorEvent sensorEvent) {
	    	 Log.d(TAG, "onSensorChanged: " + sensorEvent.sensor.getName() + ", x: " + 
	    	 	 sensorEvent.values[0] + ", y: " + sensorEvent.values[1] + ", z: " + sensorEvent.values[2]);
	    	 dbAdapter.insertAccelLog(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
	    }
	    
	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    	Log.d(TAG, "onAccuracyChanged: " + sensor.getName() + ", accuracy: " + accuracy);
	    }
}
