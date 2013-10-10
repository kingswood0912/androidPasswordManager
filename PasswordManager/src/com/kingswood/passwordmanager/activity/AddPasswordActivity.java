package com.kingswood.passwordmanager.activity;

import com.kingswood.passwordmanager.PasswordVO;
import com.kingswood.passwordmanager.R;
import com.kingswood.passwordmanager.persistent.IPasswordDAO;
import com.kingswood.passwordmanager.persistent.PasswordDAO;
import com.kingswood.passwordmanager.util.PMLog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddPasswordActivity extends Activity {
	
	private EditText name;
	
	private EditText username;
	
	private EditText password;
	
	private EditText description;

	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_add_password);
		
		name = (EditText)findViewById(R.id.add_password_name);
		username = (EditText)findViewById(R.id.add_password_username);
		password = (EditText)findViewById(R.id.add_password_password);
		description = (EditText)findViewById(R.id.add_password_description);
	}
	
	public void onSave(View v){
		PMLog.log("calling onSave function");
		
		PMLog.log("name is : " + name.getText().toString());
		PMLog.log("username is : " + username.getText().toString());
		PMLog.log("password is : " + password.getText().toString());
		PMLog.log("description is : " + description.getText().toString());
		
		String nameStr = name.getText().toString();
		String usernameStr = username.getText().toString();
		String passwordStr = password.getText().toString();
		String descriptionStr = description.getText().toString();
		
		PasswordVO vo = new PasswordVO();
		vo.setName(nameStr);
		vo.setUsername(usernameStr);
		vo.setPassword(passwordStr);
		vo.setDescription(descriptionStr);
		
		IPasswordDAO dao = new PasswordDAO(getApplicationContext());
		dao.insertPassword(vo);
		
		PasswordVO validateVO = dao.selectPasswordByName(nameStr);
		if(null != validateVO){
			PMLog.log("get last password vo which just added");
		}else{
			PMLog.log("can not get last password vo which just added");
		}
		
		alert();
		
		//this.finish();
		
	}
	
	public void alert(){

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage("The password has been added, do you want to add another one?")
		       .setTitle("message");
		
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   name.setText(null);
	        	   username.setText(null);
	        	   password.setText(null);
	        	   description.setText(null);
	        	   name.requestFocus();
	           }
	       });
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   
	        	   AddPasswordActivity.this.finish();
	        	   
	        	   Intent intent = new Intent();
		           intent.setClass(AddPasswordActivity.this, ListPasswordActivity.class);
		           startActivity(intent);
	           }
	       });

		// 3. Get the AlertDialog from create()
		AlertDialog dialog = builder.create();
		
		dialog.show();
	}
	
}
