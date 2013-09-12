package com.kingswood.passwordmanager.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.kingswood.passwordmanager.PasswordVO;
import com.kingswood.passwordmanager.persistent.IPasswordDAO;
import com.kingswood.passwordmanager.persistent.PasswordDAO;

public class DataUtil {
	
	/**
	 * Prepare testing data, if the database is empty, insert new records
	 */
	public static void initializeData(Context context){
		
		IPasswordDAO dao = new PasswordDAO(context);
		
		List<PasswordVO> list = dao.selectAllPasswords();
		
		if(null == list || list.size() == 0){
			List<PasswordVO> passwordList = prepareTestingData();
			for(int i = 0;i<passwordList.size();i++){
				dao.insertPassword(passwordList.get(i));
			}
		}
		
	}
	
	private static List<PasswordVO> prepareTestingData(){
		
		List<PasswordVO> list = new ArrayList<PasswordVO>();
		
		for(int i = 0;i<50;i++){
			PasswordVO vo = new PasswordVO();
			vo.setName("name" + i);
			vo.setDescription(String.valueOf(System.currentTimeMillis()));
			vo.setUsername("username" + i);
			vo.setPassword("password" + i);
			
			list.add(vo);
		}
		
		return list;
	}
	
}
