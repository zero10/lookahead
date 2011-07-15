package com.example.lookahead;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GpsService extends Service {

	private static final String TAG = "GpsService";
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		Toast.makeText(this, "GPS Service Created", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onCreate");
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "GPS Service Stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "GPS Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
	}
}
