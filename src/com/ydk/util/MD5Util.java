package com.ydk.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *@author :ydk
 *@date: 2015-7-7
 *@version: 
 */
public class MD5Util {
	
	/**
	 * 获取字符串的md5值
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getStringMD5(String str) throws NoSuchAlgorithmException{
		byte[] byteString = str.getBytes(Charset.forName("utf-8"));
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(byteString);
		StringBuffer sbf = new StringBuffer();
		
		for(int i=0; i<array.length; i++){
			 sbf.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		}
		return sbf.toString();
	}
	
	/**
	 * 获取文件的md5值
	 * @param filePath
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException 
	 */
	public static String getFileMD5(String filePath) throws NoSuchAlgorithmException, IOException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		int length = 0 ;
		byte[] buffer = new byte[1024*10];//10k
		InputStream is = new FileInputStream(new File(filePath));
		while((length=is.read(buffer))!=-1){
			md.update(buffer,0,length);
		}
		byte[] array = md.digest();
		StringBuffer sbf = new StringBuffer();
		for(int i=0; i<array.length;i++){
			 sbf.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		}
		return sbf.toString();
	}
}


















