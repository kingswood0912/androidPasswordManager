package com.kingswood.passwordmanager.activity;

import com.kingswood.passwordmanager.PMConstants;
import com.kingswood.passwordmanager.R;
import com.kingswood.passwordmanager.util.PMLog;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class PasswordDetailActivity extends Activity {

	private EditText name;

	private EditText username;

	private EditText password;

	private EditText description;
	
public void onCreate(Bundle savedInstanceState){
		
		PMLog.log("PasswordDetailActivity started");
	
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.password_detail);
		
		name = (EditText)findViewById(R.id.add_password_name);
		username = (EditText)findViewById(R.id.add_password_username);
		password = (EditText)findViewById(R.id.add_password_password);
		description = (EditText)findViewById(R.id.add_password_description);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    String passwordName = extras.getString(PMConstants.PARAM_NAME);
		    PMLog.log("get password name from extras : " + passwordName);
		}else{
			PMLog.log("extras is empty.");
		}

		
	}

}
