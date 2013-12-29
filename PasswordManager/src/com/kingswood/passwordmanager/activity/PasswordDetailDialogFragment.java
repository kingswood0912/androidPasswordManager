package com.kingswood.passwordmanager.activity;

import java.util.Date;

import com.kingswood.passwordmanager.R;
import com.kingswood.passwordmanager.util.PMLog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.EditText;


public class PasswordDetailDialogFragment extends DialogFragment {
	
	private EditText title;
	
	private EditText username;
	
	private EditText password;
	
	private EditText description;
	
	private View view;
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		view = inflater.inflate(R.layout.password_detail, null);
		
		
		
		builder = builder.setView(view);
		builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				PMLog.log("save button is clicked.");
				
				PMLog.log("view is : " + view);
				
				// get each text fields from view
				title = (EditText)view.findViewById(R.id.text_field_name);
				PMLog.log("title is : " + title);
				username = (EditText)view.findViewById(R.id.text_field_username);
				password = (EditText)view.findViewById(R.id.text_field_password);
				description = (EditText)view.findViewById(R.id.text_field_description);
				
				// construct password vo object and then pass to parent activity to process
				String titleStr = title.getText().toString();
				String usernameStr = username.getText().toString();
				String passwordStr = password.getText().toString();
				String descriptionStr = description.getText().toString();
				
				PasswordVO vo = new PasswordVO();
				vo.setTitle(titleStr);
				vo.setUsername(usernameStr);
				vo.setPassword(passwordStr);
				vo.setDescription(descriptionStr);
				Date now = new Date();
				vo.setCreatedDate(now);
				vo.setUpdatedDate(now);
				
				ListPasswordActivity listPasswordActivity = (ListPasswordActivity)getActivity();
				listPasswordActivity.onCreateNewPassword(vo);
				
				dismiss();
			}
			
		});
		
		builder.setNeutralButton(R.string.random, new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				PMLog.log("cancel button is clicked.");
			}
			
		});
		
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				PMLog.log("cancel button is clicked.");
			}
			
		});
		
		Dialog dialog = builder.create();
		
		
		return dialog;
	}
}
