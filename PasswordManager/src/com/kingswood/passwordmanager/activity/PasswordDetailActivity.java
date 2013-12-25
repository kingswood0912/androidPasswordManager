package com.kingswood.passwordmanager.activity;

import com.kingswood.passwordmanager.PMConstants;
import com.kingswood.passwordmanager.R;
import com.kingswood.passwordmanager.persistent.IPasswordDAO;
import com.kingswood.passwordmanager.persistent.PasswordDAO;
import com.kingswood.passwordmanager.util.PMLog;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PasswordDetailActivity extends Activity {

	private EditText name;

	private EditText username;

	private EditText password;

	private EditText description;
	
	private Button saveButton;
	
	private Button cancelButton;

	public void onCreate(Bundle savedInstanceState) {

		PMLog.log("PasswordDetailActivity started");

		super.onCreate(savedInstanceState);

		setContentView(R.layout.password_detail);

		name = (EditText) findViewById(R.id.text_field_name);
		username = (EditText) findViewById(R.id.text_field_username);
		password = (EditText) findViewById(R.id.text_field_password);
		description = (EditText) findViewById(R.id.text_field_description);
		saveButton = (Button) findViewById(R.id.save_button);
		cancelButton = (Button) findViewById(R.id.cancel_button);

		Bundle extras = getIntent().getExtras();
		String passwordName = extras.getString(PMConstants.PARAM_NAME);
		PMLog.log("get password name from extras : " + passwordName);
		
		IPasswordDAO dao = new PasswordDAO(this.getApplicationContext());
		PasswordVO vo = dao.selectPasswordByName(passwordName);
		
		name.setText(vo.getName());
		username.setText(vo.getUsername());
		password.setText(vo.getPassword());
		description.setText(vo.getDescription());
		
		// display back button on the bar
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		
		// add button listener to cancel button to display password list acitivity when the button is clicked
		cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				backToPasswordListActivity();
			}
		});

	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		return backToPasswordListActivity();
	}
	
	public boolean backToPasswordListActivity(){
		Intent intent = new Intent(this.getApplicationContext(), ListPasswordActivity.class);
		startActivity(intent);
		
		return true;
	}

}
