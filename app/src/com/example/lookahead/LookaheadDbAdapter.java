package com.example.lookahead;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LookaheadDbAdapter {

	private LookaheadDbHelper dbHelper;
	private SQLiteDatabase database;
	private Context context;
	
	public LookaheadDbAdapter(Context context) {
		this.context = context;
	}
	
	public LookaheadDbAdapter open() throws SQLException {
		dbHelper = new LookaheadDbHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public long insertAccelLog(float x, float y, float z) {
		ContentValues values = new ContentValues();
		values.put(LookaheadDbHelper.colAccelTime, System.currentTimeMillis());
		values.put(LookaheadDbHelper.colAccelX, x);
		values.put(LookaheadDbHelper.colAccelY, y);
		values.put(LookaheadDbHelper.colAccelZ, z);
		return database.insert(LookaheadDbHelper.accelTable, null, values);
	}

	public Cursor fetchAllAccelLogs() {
		return database.query(LookaheadDbHelper.accelTable, new String[] {LookaheadDbHelper.colAccelTime, LookaheadDbHelper.colAccelX, LookaheadDbHelper.colAccelY, LookaheadDbHelper.colAccelZ}, null, null, null, null, null);
	}
}
