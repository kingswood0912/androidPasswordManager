package com.kingswood.passwordmanager.persistent;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kingswood.passwordmanager.PasswordVO;

public class PasswordDAO implements IPasswordDAO {

	public static final String COLUMN_NAME = "_name";
	public static final String COLUMN_USERNAME = "_username";
	public static final String COLUMN_PASSWORD = "_password";
	public static final String COLUMN_DESCRIPTION = "_description";
	
	private SQLiteDatabase database;
	private DBHelper dbHelper;
	
	public PasswordDAO(Context context){
		dbHelper = new DBHelper(context);
		if(null == database){
			database = dbHelper.getWritableDatabase();
		}
	}
	
	
	@Override
	public void insertPassword(PasswordVO passwordVO) {
		
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, passwordVO.getName());
		values.put(COLUMN_USERNAME, passwordVO.getUsername());
		values.put(COLUMN_PASSWORD, passwordVO.getPassword());
		values.put(COLUMN_DESCRIPTION, passwordVO.getDescription());
		
		database.insert(DBHelper.TABLE_NAME, null, values);

		Log.d("My APP","new record is added into table PASSWORD");

	}

	@Override
	public List<PasswordVO> selectAllPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePassword(PasswordVO passwordVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePassword(String name) {
		// TODO Auto-generated method stub

	}

}
