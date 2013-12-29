package com.kingswood.passwordmanager.persistent;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kingswood.passwordmanager.activity.PasswordVO;
import com.kingswood.passwordmanager.util.PMLog;
import com.kingswood.passwordmanager.util.PMUtil;

public class PasswordDAO implements IPasswordDAO {

	public static final String COLUMN_NAME = "_name";
	public static final String COLUMN_USERNAME = "_username";
	public static final String COLUMN_PASSWORD = "_password";
	public static final String COLUMN_DESCRIPTION = "_description";
	public static final String COLUMN_CREATEDATE = "_created_date";
	public static final String COLUMN_UPDATEDDATE = "_updated_date";

	private String[] allColumns = { COLUMN_NAME, COLUMN_USERNAME,
			COLUMN_PASSWORD, COLUMN_DESCRIPTION, COLUMN_CREATEDATE, COLUMN_UPDATEDDATE};

	private SQLiteDatabase database;
	private DBHelper dbHelper;

	public PasswordDAO(Context context) {
		dbHelper = new DBHelper(context);
		if (null == database) {
			database = dbHelper.getWritableDatabase();
		}
	}

	@Override
	public void insertPassword(PasswordVO passwordVO) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, passwordVO.getTitle());
		values.put(COLUMN_USERNAME, passwordVO.getUsername());
		values.put(COLUMN_PASSWORD, passwordVO.getPassword());
		values.put(COLUMN_DESCRIPTION, passwordVO.getDescription());
		values.put(COLUMN_CREATEDATE, PMUtil.convertDate(passwordVO.getCreatedDate()));
		values.put(COLUMN_UPDATEDDATE, PMUtil.convertDate(passwordVO.getUpdatedDate()));

		database.insert(DBHelper.TABLE_PASSWORD, null, values);

		Log.d("My APP", "new record is added into table PASSWORD");

	}

	@Override
	public List<PasswordVO> selectAllPasswords() {

		PMLog.log("Start calling selectAllPassword");

		List<PasswordVO> allPasswords = new ArrayList<PasswordVO>();

		Cursor cursor = database.query(DBHelper.TABLE_PASSWORD, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			PasswordVO vo = new PasswordVO();
			vo.setTitle(cursor.getString(0));
			vo.setUsername(cursor.getString(1));
			vo.setPassword(cursor.getString(2));
			vo.setDescription(cursor.getString(3));

			allPasswords.add(vo);

			cursor.moveToNext();
		}

		cursor.close();

		PMLog.log("Stop calling selectAllPassword, total size of password list is : "
				+ allPasswords.size());

		return allPasswords;
	}

	@Override
	public void updatePassword(PasswordVO passwordVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePassword(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public PasswordVO selectPasswordByTitle(String title) {

		Cursor cursor = database.query(DBHelper.TABLE_PASSWORD, allColumns,
				COLUMN_NAME + "=?", new String[] { title }, null, null, null);

		cursor.moveToFirst();

		if (!cursor.isAfterLast()) {

			PasswordVO vo = new PasswordVO();
			vo.setTitle(cursor.getString(0));
			vo.setUsername(cursor.getString(1));
			vo.setPassword(cursor.getString(2));
			vo.setDescription(cursor.getString(3));
			vo.setCreatedDate(PMUtil.convertString2Date(cursor.getString(4)));
			vo.setUpdatedDate(PMUtil.convertString2Date(cursor.getString(5)));

			return vo;
		}

		return null;

	}

	@Override
	public List<PasswordVO> selectPasswordsByNameOrDescription(
			String nameOrDescription) {

		PMLog.log("Start calling selectPasswordsByNameOrDescription");
		
		List<PasswordVO> passwords = new ArrayList<PasswordVO>();

		Cursor cursor = database
				.query(DBHelper.TABLE_PASSWORD, allColumns, COLUMN_NAME
						+ " like ? or " + COLUMN_DESCRIPTION + " like ? ",
						new String[] { "%"+nameOrDescription+"%", "%"+nameOrDescription+"%" },
						null, null, null);
		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			PasswordVO vo = new PasswordVO();
			vo.setTitle(cursor.getString(0));
			vo.setUsername(cursor.getString(1));
			vo.setPassword(cursor.getString(2));
			vo.setDescription(cursor.getString(3));

			passwords.add(vo);

			cursor.moveToNext();
		}

		cursor.close();

		PMLog.log("End calling selectPasswordsByNameOrDescription");
		
		return passwords;
	}

}
