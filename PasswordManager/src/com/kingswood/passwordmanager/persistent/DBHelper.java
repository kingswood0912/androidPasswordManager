package com.kingswood.passwordmanager.persistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "passwordManager.db";
	
	public static final String TABLE_PASSWORD = "PASSWORD";
	
	private static final int DATABASE_VERSION = 1;
	
	
	
	//private static final String SQL_INSERT = "insert into " + TABLE_NAME + " (_name, _username, _password, _description) values (?,?,?,?) ";  
	
	private static String CREATE_PASSWORD_TABLE = "create table " + TABLE_PASSWORD
			+ " (_name text, _username text, _password text, _description text, _created_date datetime, _updated_date datetime )";

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
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSWORD);
		    onCreate(db);

	}
	
	public static void main(String[] args){
		System.out.println(CREATE_PASSWORD_TABLE);
	}

}
