package com.ydk.util;

public final class MathUtil {
	public final static String strArray = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 生成一个length长度额随机码
	 * @param length
	 * @return
	 */
	public static String randomStr(int length){
		if(length<=0){
			return null;
		}
		String str="";
		for(int i = 0; i < length; i++){
			 int index = (int)(Math.random()*36);
			 str = str+strArray.charAt(index);
		}
		return str;
	}
	
	
	public static String randomStrUniq(int length){
		if(length<=0 || length>36){
			return null;
		}
		String str="";
		for (int i = 0; i < length; ) {
			int index = (int)(Math.random()*36);
			
			boolean flag = true;
			for(int j = 0; j<str.length();j++){
				if((byte)strArray.charAt(index) == (byte)str.charAt(j)){
					flag = false;
					break;
				}
			}
			if(flag){
				str = str+strArray.charAt(index);
				i++;
			}
		}
		return str;
	}
}
