package com.kingswood.passwordmanager.activity;

import java.util.List;

import com.kingswood.passwordmanager.PasswordVO;
import com.kingswood.passwordmanager.util.PMLog;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

public class ListPasswordAdapter extends BaseAdapter {

	private List<PasswordVO> passwordList;
	private Context context;
	
	public ListPasswordAdapter(List<PasswordVO> passwordList, Context context){
		this.passwordList = passwordList;
		this.context = context;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		PMLog.log("calling ListPasswordAdapter getView method-------------");
		
		TwoLineListItem twoLineListItem;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            twoLineListItem = (TwoLineListItem) inflater.inflate(
                    android.R.layout.simple_list_item_2, null);
        } else {
            twoLineListItem = (TwoLineListItem) convertView;
        }

        TextView text1 = twoLineListItem.getText1();
        TextView text2 = twoLineListItem.getText2();

        text1.setText(passwordList.get(position).getName());
        text2.setText(passwordList.get(position).getDescription());
        
        // set background color as black
        twoLineListItem.setBackgroundColor(Color.BLACK);

        return twoLineListItem;
	}

}
