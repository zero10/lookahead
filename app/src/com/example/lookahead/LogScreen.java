package com.example.lookahead;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class LogScreen extends Activity implements OnClickListener {
	
	private static final String TAG = "LogScreen";
	TextView axis1 = null;
	TextView axis2 = null;
	TextView axis3 = null;
	TextView intervalTextView = null;
	TextView minimumTimeTextView = null;
	long lastUpdate;
	long minTime;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);
        axis1 = (TextView) findViewById(R.id.textViewAxis1);
        axis2 = (TextView) findViewById(R.id.textViewAxis2);
        axis3 = (TextView) findViewById(R.id.textViewAxis3);
        intervalTextView = (TextView) findViewById(R.id.textViewInterval);
        minimumTimeTextView = (TextView) findViewById(R.id.textViewMinimumTime);
		axis1.setText("X: ");
		axis2.setText("Y: ");
		axis2.setText("Z: ");
		minTime = -1;

		/*List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
		TextView sensorText = (TextView) findViewById(R.id.textViewSensors);
		sensorText.setText("");
		for (int i = 0;i < sensors.size();i++)
		{
			sensorText.setText(sensorText.getText() + sensors.get(i).getName() + "\n");			
		}*/
    }
    
    public void onClick(View view)
    {
   	 switch (view.getId()) {
	    case R.id.buttonStart:
	      Log.d(TAG, "onClick: starting service");
	      Intent startSvc = new Intent(this, GpsService.class);
	      startService(startSvc);
	      break;
	    case R.id.buttonStop:
	      Log.d(TAG, "onClick: stopping service");
	      Intent stopSvc = new Intent(this, GpsService.class);
	      stopService(stopSvc);
	      break;
	    case R.id.buttonTest:
	    	EditText textFieldNumAxis = (EditText) findViewById(R.id.textFieldNumAxis);
	    	textFieldNumAxis.setText("3");
	    	break;
	    }
    }

    public void onStop() {
		minimumTimeTextView.setText("0 msec");
		intervalTextView.setText("0 msec");
    	minTime = -1;
    	super.onStop();
    }
    
    public void onResume() {
    	super.onResume();
    }	
}
