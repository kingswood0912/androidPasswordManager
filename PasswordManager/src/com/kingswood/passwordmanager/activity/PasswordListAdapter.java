package com.kingswood.passwordmanager.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import com.kingswood.passwordmanager.PasswordVO;

import android.content.ClipData.Item;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PasswordListAdapter extends ArrayAdapter<Item> {
	
	private ArrayList<Item> objects;

	
	public PasswordListAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {
		super(context, textViewResourceId, objects);
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View view = super.getView(position, convertView, parent);
		TextView text1 = (TextView) view.findViewById(android.R.id.text1);
		TextView text2 = (TextView) view.findViewById(android.R.id.text2);
		
		Object obj = objects.get(0);
		
		//List<PasswordVO> list = (List<PasswordVO>)objects;

		//BasicNameValuePair data = objects.get(position);

		//text1.setText(data.getName());
		//text2.setText(data.getValue());
		return view;
	}

}
