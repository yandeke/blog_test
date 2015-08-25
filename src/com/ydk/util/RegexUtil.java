package com.ydk.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *@author :ydk
 *@date: 2015-7-7
 *@version: 
 */
public class RegexUtil {
	/**
	 * �ж�ip��ַ�Ƿ���ȷ
	 * @param ip
	 * @return
	 */
	public static boolean isIp(String ip){
		boolean returnValue = false;
		if(StringUtil.isNotEmpty(ip)){
			String regex="^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
			returnValue = ip.matches(regex);
		}
		return returnValue;
	}
	/**
	 * �ж��Ƿ�����ȷ�������ַ
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		boolean returnValue = false;
		if(StringUtil.isNotEmpty(email)){
			String regex="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
			returnValue = email.matches(regex);
		}
		return returnValue;
	}
	
	/**
	 * �ж��Ƿ�������
	 * @param text
	 * @return
	 */
	public static boolean isChinese(String text){
		boolean returnValue = false;
		if(StringUtil.isNotEmpty(text)){
			Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
			Matcher m = pattern.matcher(text);
			returnValue = m.find();
		}
		return returnValue;
	}
	
	/**
	 * �ж��Ƿ���������
	 * @param number
	 * @return
	 */
	public static boolean isNumber(String number){
		boolean returnValue = false;
		if(StringUtil.isNotEmpty(number)){
			String regex = "[0-9]*";
			returnValue = number.matches(regex);
		}
		return returnValue;
	}
	/**
	 * �ж��Ƿ��ǵ绰����
	 * @param phoneNumber
	 * @return
	 */
	public static boolean isPhoneNumber(String phoneNumber){
		boolean returnValue = false;
		if(StringUtil.isNotEmpty(phoneNumber)){
			String regex = "^1[3|4|5|8][0-9]\\d{8}$";
			returnValue = phoneNumber.matches(regex);
		}
		return returnValue;
	}
	
	/**
	 * �ж��Ƿ��������ַ�
	 * ��������true
	 * ����������false
	 * @param text
	 * @return
	 */
	public static boolean hasSpecialChar(String text){
		boolean returnValue = true;
		if(StringUtil.isNotEmpty(text)){
			if(text.replaceAll("[a-z]*[A-Z]*\\d*-*_*\\s*", "").length()==0){
			    //��������������ַ�
				returnValue = false;
			 }
		}
		return returnValue;
	}
	
	/**
	 * �ж��Ƿ������ַ
	 * �з���true
	 * �޷���false
	 * @param text
	 * @return
	 */
	public static boolean hasUrl(String text){
		boolean returnValue = false;
		if(StringUtil.isNotEmpty(text)){
			Pattern pattern=Pattern.compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
			Matcher matcher=pattern.matcher(text);
			if(matcher.find()){
				returnValue = true;
			}
		}
		return returnValue;
	}
	
	public static void main(String[] args) {
			System.out.println(hasUrl("fjsadklfjdkhttp://www.baidu.comfdjasklhttp://www.abi.comdafd"));
	}
}













