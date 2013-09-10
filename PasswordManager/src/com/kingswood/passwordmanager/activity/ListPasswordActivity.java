package com.kingswood.passwordmanager.activity;

import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import com.kingswood.passwordmanager.PasswordVO;
import com.kingswood.passwordmanager.persistent.IPasswordDAO;
import com.kingswood.passwordmanager.persistent.PasswordDAO;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

public class ListPasswordActivity extends ListActivity {

	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		IPasswordDAO dao = new PasswordDAO(getApplicationContext());
		
		List<PasswordVO> passwordList = dao.selectAllPasswords();
		
		ArrayAdapter<PasswordVO> adapter = null;
		
		adapter = new ArrayAdapter (this, android.R.layout.simple_list_item_2, android.R.id.text1, passwordList) ;

			

		
		setListAdapter(null);
		
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		
		
		return null;
		
	}
	
}
