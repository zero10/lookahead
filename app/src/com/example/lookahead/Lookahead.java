package com.example.lookahead;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lookahead extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void selfDestruct(View view)
    {
    	setContentView(R.layout.log);
	}
    
    public void addLog(View view)
    {
    	TextView logView = (TextView) findViewById(R.id.logText);
    	logView.setText(R.string.gibberish);
    	
    }
}