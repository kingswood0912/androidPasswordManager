package com.kingswood.passwordmanager.activity;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingswood.passwordmanager.PasswordVO;
import com.kingswood.passwordmanager.R;
import com.kingswood.passwordmanager.util.PMLog;

public class ListPasswordAdapter extends BaseAdapter {

	private List<PasswordVO> passwordList;
	private Context context;
	private LayoutInflater inflater;
	
	public ListPasswordAdapter(List<PasswordVO> passwordList, Context context){
		this.passwordList = passwordList;
		this.context = context;
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

	/*@Override
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
	}*/
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		 
        if (convertView == null) {
        	convertView = inflater.inflate(R.layout.list_view_item, null);
        	TextView name = (TextView)convertView.findViewById(R.id.item_view_name);
        	TextView description = (TextView)convertView.findViewById(R.id.item_view_description);
        	name.setText(passwordList.get(position).getName());
        	description.setText(passwordList.get(position).getDescription());
        	convertView.setBackgroundColor(Color.BLACK);
        	
        	ImageView deleteView = (ImageView)convertView.findViewById(R.id.item_view_delete_item);
        	deleteView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					
					PMLog.log("calling onClick method-------------");
					
				}
        	});
        }  
        
//        if(null != convertView){
//        	convertView.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					System.out.println("detecting onclick event.");
//				}
//			});
//        }
		
		return convertView;
	}

}
