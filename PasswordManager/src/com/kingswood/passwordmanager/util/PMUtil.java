package com.kingswood.passwordmanager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PMUtil {

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String convertDate(Date date){
		
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
		
		return format.format(date);
		
	}
	
	public static Date convertString2Date(String source){
		
		if(null == source || source.equals("")){
			return null;
		}
		
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
		try {
			return format.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
