package com.kingswood.passwordmanager.activity;

import java.util.Date;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.kingswood.passwordmanager.R;
import com.kingswood.passwordmanager.persistent.IPasswordDAO;
import com.kingswood.passwordmanager.persistent.PasswordDAO;
import com.kingswood.passwordmanager.util.PMLog;
import com.kingswood.passwordmanager.util.PMUtil;


public class PasswordDetailDialogFragment extends DialogFragment {
	
	private EditText title;
	
	private EditText username;
	
	private EditText password;
	
	private EditText description;
	
	private TextView createdDate;
	
	private TextView updatedDate;
	
	private TableRow addedDateRow;
	
	private TableRow updatedDateRow;
	
	private View view;
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		view = inflater.inflate(R.layout.password_detail, null);
		
		Bundle bundle = getArguments();
		
		String action = bundle.getString(ListPasswordActivity.ACTION);
		
		builder = builder.setView(view);
		
		// get each text fields from view
		title = (EditText)view.findViewById(R.id.text_field_name);
		username = (EditText)view.findViewById(R.id.text_field_username);
		password = (EditText)view.findViewById(R.id.text_field_password);
		description = (EditText)view.findViewById(R.id.text_field_description);
		createdDate = (TextView)view.findViewById(R.id.created_date);
		updatedDate = (TextView)view.findViewById(R.id.updated_date);
		addedDateRow = (TableRow)view.findViewById(R.id.tablerow_added_date);
		updatedDateRow = (TableRow)view.findViewById(R.id.tablerow_updated_date);
		
		
		if(action.equals(ListPasswordActivity.ACTION_UPDATE)){
			String titleStr = bundle.getString(ListPasswordActivity.TITLE);
			PMLog.log("title passed from listpasswordactivity is : " + titleStr);
			IPasswordDAO dao = new PasswordDAO(getActivity());
			PasswordVO vo = dao.selectPasswordByTitle(titleStr);
			title.setText(titleStr);
			username.setText(vo.getUsername());
			password.setText(vo.getPassword());
			description.setText(vo.getDescription());
			createdDate.setText(PMUtil.convertDate(vo.getCreatedDate()));
			updatedDate.setText(PMUtil.convertDate(vo.getUpdatedDate()));
		}else{
			// if it is adding password dialog, then hide added date and updated date rows
			addedDateRow.setVisibility(View.GONE);
			updatedDateRow.setVisibility(View.GONE);
		}
		
		builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				PMLog.log("save button is clicked.");
				
				PMLog.log("view is : " + view);
				
				
				
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
