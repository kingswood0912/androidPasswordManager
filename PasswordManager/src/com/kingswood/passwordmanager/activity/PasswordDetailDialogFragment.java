package com.kingswood.passwordmanager.activity;

import com.kingswood.passwordmanager.R;
import com.kingswood.passwordmanager.util.PMLog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;


public class PasswordDetailDialogFragment extends DialogFragment {
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		builder.setView(inflater.inflate(R.layout.password_detail, null)).setPositiveButton(R.string.save, new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				PMLog.log("save button is clicked.");
			}
			
		}).setNeutralButton(R.string.random, new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				PMLog.log("cancel button is clicked.");
			}
			
		}).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				PMLog.log("cancel button is clicked.");
			}
			
		});
		
		
		
		
		return builder.create();
	}
}
