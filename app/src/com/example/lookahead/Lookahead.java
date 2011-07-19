package com.example.lookahead;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Lookahead extends Activity implements OnClickListener {

	private static final String TAG = "Lookahead";
	Button buttonStart, buttonStop;
	
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
    
    public void onClick(View view)
    {
   	 switch (view.getId()) {
	    case R.id.button1:
	    	Intent intent = new Intent(getBaseContext(), LogScreen.class);
	    	startActivity(intent);
	    	break;
	    }
    }
}