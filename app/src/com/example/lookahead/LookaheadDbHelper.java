package com.example.lookahead;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LookaheadDbHelper extends SQLiteOpenHelper {

	private static final String TAG = "LookaheadDbHelper";
	static public final String dbName = "lookaheadDb";
	static public final String accelTable = "Accel";
	static public final String colAccelTime = "Timestamp";
	static public final String colAccelX = "X";
	static public final String colAccelY = "Y";
	static public final String colAccelZ = "Z";
	static public final String gpsTable = "GPS";
	static public final String colGpsTime = "Timestamp";
	
	public LookaheadDbHelper(Context context) {
		super(context, dbName, null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + accelTable + " (" + colAccelTime + " INTEGER PRIMARY KEY, " +
				colAccelX + " REAL NOT NULL, " + colAccelY + " REAL NOT NULL, " + colAccelZ + " REAL NOT NULL)");
		db.execSQL("CREATE TABLE " + gpsTable + " (" + colGpsTime + " INTEGER PRIMARY KEY)");
		
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ".");
		db.execSQL("DROP TABLE IF EXISTS " + accelTable);
		db.execSQL("DROP TABLE IF EXISTS " + gpsTable);
		onCreate(db);
	}
	
	public static void addAccelLog(float x, float y, float z)
	{
		
	}

}
