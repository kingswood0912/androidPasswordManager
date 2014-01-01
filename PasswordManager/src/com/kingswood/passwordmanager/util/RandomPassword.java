package com.kingswood.passwordmanager.util;

import java.util.Random;

public class RandomPassword {
	
	private static final String scope = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String random(int digit){
		
		char[] password = new char[digit];
		
		char[] sourceChars = scope.toCharArray();
		
		int sourceLength = sourceChars.length;
		
		Random random = new Random();
		
		for(int i = 0;i<digit;i++){
			password[i] = sourceChars[random.nextInt(sourceLength)];
		}
		
		String result = new String(password);
		
		//String result = password.toString();
		
		PMLog.log("random password is : " + result);
		
		return result;
		
	}
	
}
