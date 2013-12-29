package com.kingswood.passwordmanager.util;

import android.util.Log;

public class PMLog{
	
	public static final String PM_LOG_PREFIX = "PM_LOG";
	
	public static void log(String msg){
		Log.d(PM_LOG_PREFIX, msg);
		System.out.println(PM_LOG_PREFIX + ":" + msg);
	}
	
}
