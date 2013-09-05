package com.kingswood.passwordmanager.activity;

import com.kingswood.passwordmanager.PasswordVO;
import com.kingswood.passwordmanager.R;
import com.kingswood.passwordmanager.persistent.DBHelper;
import com.kingswood.passwordmanager.persistent.IPasswordDAO;
import com.kingswood.passwordmanager.persistent.PasswordDAO;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new DBHelper(getApplicationContext());
		
		IPasswordDAO dao = new PasswordDAO(getApplicationContext());
		PasswordVO vo = new PasswordVO();
		vo.setName(String.valueOf(System.currentTimeMillis()));
		vo.setUsername("username");
		vo.setPassword("testing password");
		vo.setDescription("test description");
		
		dao.insertPassword(vo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
