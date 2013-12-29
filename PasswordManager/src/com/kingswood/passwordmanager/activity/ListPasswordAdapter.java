package com.kingswood.passwordmanager.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kingswood.passwordmanager.PMConstants;
import com.kingswood.passwordmanager.R;
import com.kingswood.passwordmanager.util.PMLog;

public class ListPasswordAdapter extends BaseAdapter {

	private List<PasswordVO> passwordList;
	private LayoutInflater inflater;
	
	public ListPasswordAdapter(List<PasswordVO> passwordList, Context context){
		this.passwordList = passwordList;
		inflater = LayoutInflater.from(context);
	}
	
	public void setPasswordList(List<PasswordVO> passwordList){
		this.passwordList = passwordList;
	}
	
	@Override
	public int getCount() {
		return passwordList.size();
	}

	@Override
	public Object getItem(int position) {
		return passwordList.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = inflater.inflate(R.layout.list_view_item, null);
		TextView name = (TextView) convertView
				.findViewById(R.id.item_view_name);
		TextView description = (TextView) convertView
				.findViewById(R.id.item_view_description);
		name.setText(passwordList.get(position).getTitle());
		description.setText(passwordList.get(position).getDescription());
		convertView.setBackgroundColor(Color.BLACK);

		// set text color
		name.setTextColor(Color.WHITE);
		description.setTextColor(Color.WHITE);

		return convertView;
	}
	
}
