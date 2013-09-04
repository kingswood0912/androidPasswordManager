package com.kingswood.passwordmanager.persistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "passwordManager.db";
	
	public static final String TABLE_NAME = "PASSWORD";
	
	private static final int DATABASE_VERSION = 1;
	
	public static final String COLUMN_NAME = "_name";
	public static final String COLUMN_USERNAME = "_username";
	public static final String COLUMN_PASSWORD = "_password";
	public static final String COLUMN_DESCRIPTION = "_description";
	
	private static String CREATE_PASSWORD_TABLE = "create table " + TABLE_NAME
			+ " ( " + COLUMN_NAME + " text, " + COLUMN_USERNAME + " text, "
			+ COLUMN_PASSWORD + " text, " + COLUMN_DESCRIPTION + " text)";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		
		System.out.println("Start onCreate()......");
		
		System.out.println("sql : " + CREATE_PASSWORD_TABLE);
		
		database.execSQL(CREATE_PASSWORD_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(this.getClass().getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		    onCreate(db);

	}
	
	public static void main(String[] args){
		System.out.println(CREATE_PASSWORD_TABLE);
	}

}
