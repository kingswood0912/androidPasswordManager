package com.kingswood.passwordmanager.activity;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.kingswood.passwordmanager.PasswordVO;
import com.kingswood.passwordmanager.R;
import com.kingswood.passwordmanager.persistent.IPasswordDAO;
import com.kingswood.passwordmanager.persistent.PasswordDAO;
import com.kingswood.passwordmanager.util.DataUtil;
import com.kingswood.passwordmanager.util.PMLog;

public class ListPasswordActivity extends ListActivity {

	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		DataUtil.initializeData(getApplicationContext());
		
		IPasswordDAO dao = new PasswordDAO(getApplicationContext());
		
		List<PasswordVO> passwordList = dao.selectAllPasswords();
		
		ListPasswordAdapter adapter = new ListPasswordAdapter(passwordList, getApplicationContext());
			
		setListAdapter(adapter);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_create_new:
	            PMLog.log("click menu option of 'new password'");
	            Intent intent = new Intent();
	            intent.setClass(this, AddPasswordActivity.class);
	            startActivity(intent);
	            return true;
	        case R.id.menu_edit:
	        	PMLog.log("click menu option of 'edit passwords'");
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.list_password_menu, menu);
	    return true;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		
		
		return null;
		
	}
	
}
