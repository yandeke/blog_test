package com.ydk.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 *@author :ydk
 *@date: 2015-7-7
 *@version: 
 */
public class TestRegMd5 {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		System.out.println(MD5Util.getStringMD5("abcd"));
		System.out.println(MD5Util.getFileMD5("E:/a.txt"));
		
		System.out.println("==========================");
		
		System.out.println(RegexUtil.hasSpecialChar("15809517412"));
	}
}
