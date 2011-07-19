package com.example.lookahead;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class LogScreen extends Activity implements OnClickListener, SensorEventListener  {
	
	private static final String TAG = "LogScreen";
	SensorManager sensorManager;
	Sensor accelerometer;
	TextView axis1 = null;
	TextView axis2 = null;
	TextView axis3 = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        axis1 = (TextView) findViewById(R.id.textViewAxis1);
        axis2 = (TextView) findViewById(R.id.textViewAxis2);
        axis3 = (TextView) findViewById(R.id.textViewAxis3);
		axis1.setText("X: ");
		axis2.setText("Y: ");
		axis2.setText("Z: ");
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
		TextView sensorText = (TextView) findViewById(R.id.textViewSensors);
		sensorText.setText("");
		for (int i = 0;i < sensors.size();i++)
		{
			sensorText.setText(sensorText.getText() + sensors.get(i).getName() + "\n");			
		}
    }
    
    public void onClick(View view)
    {
   	 switch (view.getId()) {
	    case R.id.buttonStart:
	      Log.d(TAG, "onClick: starting service");
	      startService(new Intent(this, GpsService.class));
	      break;
	    case R.id.buttonStop:
	      Log.d(TAG, "onClick: stopping service");
	      stopService(new Intent(this, GpsService.class));
	      break;
	    case R.id.buttonTest:
	    	EditText textFieldSupported = (EditText) findViewById(R.id.textFieldSupported);
	    	EditText textFieldNumAxis = (EditText) findViewById(R.id.textFieldNumAxis);
	    	if (accelerometer != null)
	    		textFieldSupported.setText("Yes");
	    	textFieldNumAxis.setText("3");
	    	break;
	    }
    }

    public void onStop() {
    	sensorManager.unregisterListener(this);
    	super.onStop();
    }
    
    public void onResume() {
    	super.onResume();
    	sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }	
    
    public void onSensorChanged(SensorEvent sensorEvent) {
    	 Log.d(TAG, "onSensorChanged: " + sensorEvent.sensor.getName() + ", x: " + 
    	 	 sensorEvent.values[0] + ", y: " + sensorEvent.values[1] + ", z: " + sensorEvent.values[2]);
    	 synchronized (this) {
    		axis1.setText("X: " + sensorEvent.values[0]);
    		axis2.setText("Y: " + sensorEvent.values[1]);
    		axis3.setText("Z: " + sensorEvent.values[2]);    		
    	}
    	 
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	Log.d(TAG, "onAccuracyChanged: " + sensor.getName() + ", accuracy: " + accuracy);
    }
}
