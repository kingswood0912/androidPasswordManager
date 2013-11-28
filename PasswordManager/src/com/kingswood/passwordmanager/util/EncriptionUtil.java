//package com.kingswood.passwordmanager.util;
//
//import java.io.IOException;
//import java.security.SecureRandom;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.DESKeySpec;
//
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
//public class EncriptionUtil {
//
//	private final static String DES = "DES";
//
//	public static void main(String[] args) throws Exception {
//		String data = "kignswood";
//		String key = "wa@#$%";
//		System.out.println("Start");
//		System.out.println(encrypt(data, key));
//		System.out.println(decrypt(encrypt(data, key), key));
//
//	}
//
//	public static String encrypt(String data, String key) throws Exception {
//		byte[] bt = encrypt(data.getBytes(), key.getBytes());
//		String strs = new BASE64Encoder().encode(bt);
//		return strs;
//	}
//
//	public static String decrypt(String data, String key) throws IOException,
//			Exception {
//		if (data == null)
//			return null;
//		BASE64Decoder decoder = new BASE64Decoder();
//		byte[] buf = decoder.decodeBuffer(data);
//		byte[] bt = decrypt(buf, key.getBytes());
//		return new String(bt);
//	}
//
//	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
//		SecureRandom sr = new SecureRandom();
//
//		DESKeySpec dks = new DESKeySpec(key);
//
//		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
//		SecretKey securekey = keyFactory.generateSecret(dks);
//
//		Cipher cipher = Cipher.getInstance(DES);
//
//		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
//
//		return cipher.doFinal(data);
//	}
//
//	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
//		SecureRandom sr = new SecureRandom();
//
//		DESKeySpec dks = new DESKeySpec(key);
//
//		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
//		SecretKey securekey = keyFactory.generateSecret(dks);
//
//		Cipher cipher = Cipher.getInstance(DES);
//
//		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
//
//		return cipher.doFinal(data);
//	}
//}
