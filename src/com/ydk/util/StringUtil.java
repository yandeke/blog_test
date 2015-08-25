package com.ydk.util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *@author :ydk
 *@date: 2015-7-6
 *@version: 
 */
public class StringUtil {
	
	private static final Logger logger = Logger.getLogger(StringUtil.class);

    /**
     * �ж��ַ����Ƿ�Ϊ�ջ���null
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    /**
     * �ж��ַ����Ƿ�Ϊ�ջ���null
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * BASE64����
     *
     * @param
     * @return
     * @throws Exception
     */
    public static byte[] encodeBase64(String str) throws Exception {
        byte[] b = Base64.encodeBase64(str.getBytes(), true);
        return b;
    }

    /**
     * BASE64����
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decodeBase64(byte[] key) throws Exception {
        //����
        byte[] b = Base64.decodeBase64(key);
        return new String(b);
    }

    /**
     * ���ַ���Ϊ�գ���ȡĬ��ֵ
     * @param str
     * @param defaultValue
     * @return
     */
    public static String defaultIfEmpty(String str, String defaultValue) {
        return StringUtils.defaultIfEmpty(str, defaultValue);
    }

    /**
     * �ַ���XSS����
     *
     * @param str
     * @return
     */
    public static String xssClean(String str) {
        if (StringUtil.isNotEmpty(str)) {
            str = Jsoup.clean(str, Whitelist.basic());
        }
        return str;
    }

    /**
     * �ַ���sql����
     *
     * @param str
     * @return
     */
    public static String sqlClean(String str) {
        if (StringUtil.isNotEmpty(str)) {
            str = StringEscapeUtils.escapeSql(str);
        }
        return str;
    }

    /**
     * �����ַ���
     *
     * @param str
     * @return
     */
    public static String clean(String str) {
        str = xssClean(str);
        str = sqlClean(str);
        return str;
    }

    /**
     * �ж��ַ����ĳ���С�ڸ�������
     * С�ڻ�����򷵻�true
     * ���ڷ���false
     * @param str
     * @param limitLength
     * @return
     */
    public static boolean checkLtLength(String str, long maxLength) {
        if (StringUtil.isNotEmpty(str)) {
            if (StringUtil.length(str) > maxLength) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    /**
     * �ж��ַ������ڻ������С����
     * @param str
     * @param minLength
     * @return
     */
    public static boolean checkGtLength(String str,long minLength){
    	boolean returnValue=false;
    	if(StringUtil.isNotEmpty(str)){
    		if(StringUtil.length(str)>=minLength){
    			returnValue = true;
    		}
    	}
    	return returnValue;
    }
    
    /**
     * �ж��ַ�����minLength-maxLength
     * @param str
     * @param minLength
     * @param maxLenght
     * @return
     */
    public static boolean checkLength(String str,long minLength,long maxLenght){
    	boolean returnValue = false;
    	if(StringUtil.checkLtLength(str, maxLenght)&& StringUtil.checkGtLength(str, minLength)){
    		returnValue = true;
    	}
    	return returnValue;
    }

    /**
     * ���ַ��ĳ��� ÿ�������ַ�ռ��������
     *
     * @param str
     * @return
     */
    public static int length(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int lencounter = 0;
        int index = 0;
        char[] strarray = str.toCharArray();
        try {
            for (; index < strarray.length; index++) {
                char ch = strarray[index];
                int step = 1;
                if (String.valueOf(ch).getBytes("GBK").length > 1) {
                    step = 2;
                }
                lencounter = lencounter + step;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lencounter;
    }

    /**
     * ��ȡ�ַ�������
     *
     * @param str
     * @param length �ַ�����  һ��������2��
     * @param suffix ��׺
     * @return
     */
    public static String substr(String str, int length, String suffix) {
        if (str == null || str.length() == 0 || length < 1) {
            return str;
        }
        if (suffix == null) {
            suffix = "";
        }
        if (suffix.length() > 0 && length > suffix.length()) {
            length = length - suffix.length();
        } else {
            suffix = "";
        }

        int lencounter = 0;
        int index = 0;
        char[] strarray = str.toCharArray();
        try {
            for (; index < strarray.length; index++) {
                char ch = strarray[index];
                int step = 1;
                if (String.valueOf(ch).getBytes("GBK").length > 1) {
                    step = 2;
                }
                if ((lencounter = lencounter + step) > length) {
                    index--;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (index > 0 && index < str.length()) {
            str = str.substring(0, index) + suffix;
        }

        return str;
    }

    private static String ULR_REGULAR = "(http|https):(?://(?:((?:[A-z0-9-._~!$&'()*+,;=:]|%[0-9A-F]{2})*)@)?((?:[A-z0-9-._~!$&'()*+,;=]|%[0-9A-F]{2})*)(?::(\\d*))?(/(?:[A-z0-9-._~!$&'()*+,;=:@/]|%[0-9A-F]{2})*)?|(/?(?:[A-z0-9-._~!$&'()*+,;=:@]|%[0-9A-F]{2})+(?:[A-z0-9-._~!$&'()*+,;=:@/]|%[0-9A-F]{2})*)?)(?:\\?((?:[A-z0-9-._~!$&'()*+,;=:/?@]|%[0-9A-F]{2})*))?(?:#((?:[A-z0-9-._~!$&'()*+,;=:/?@]|%[0-9A-F]{2})*))?";

    /**
     * �滻�ı��е�url
     *
     * @param str
     * @return
     */
    public static String replaceUrl(String str) {
        Pattern p = Pattern.compile(ULR_REGULAR);
        String[] tmpStrs = str.split("http");
        StringBuilder contentCopy = new StringBuilder();
        for (String s : tmpStrs) {
            if (0 < s.length()) {
                String st = "http";
                Matcher m = p.matcher(st+s);
                if (m.find()) {
                    String s1 = str.substring(m.start(), m.end());
                    contentCopy.append("<a target='_blank' href=" + s1 + ">" + s1 + "</a>");
                } else {
                    contentCopy.append(s);
                }
            }
        }
        /*Matcher m = p.matcher(str);
        int offSet = 0;//ƫ����
        StringBuilder contentCopy = new StringBuilder(str);
        while (m.find()) {
            String start = "<a target='_blank' href=" + str.substring(m.start(), m.end()) + ">";
            String end = "</a>";
            contentCopy.insert(m.start() + offSet, start).insert(m.end() + start.length() + offSet, end);
            offSet += start.length() + end.length();
        }*/
        return contentCopy.toString();
    }
    /**
     * ����Ƿ���ת����ȫ�Ƿ��ţ�Ӣ���ַ�ת��Ϊ�����ַ���
     * @param str
     * @return
     */
    public static String changeToFull(String str){
    	 String source = "1234567890!@#$%^&*()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_=+\\|[];:'\",<.>/?";
         String[] decode = { "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                 "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                 "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                 "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                 "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                 "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                 "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                 "'", "\"", "��", "��", "��", "��", "��", "��" };
         String result = "";
         for (int i = 0; i < str.length(); i++) {
             int pos = source.indexOf(str.charAt(i));
             if (pos != -1) {
                 result += decode[pos];
             } else {
                 result += str.charAt(i);
             }
         }
         return result;
    }
    
    /**
     * ��һ���ַ�ת����ʮ�����ƣ���λ��ʮ�����ƣ�
     * @param ch
     * @return
     */
    public static String unicodeEscaped(char ch){
    	if (ch < 0x10) {
            return "\\u000" + Integer.toHexString(ch);
        } else if (ch < 0x100) {
            return "\\u00" + Integer.toHexString(ch);
        } else if (ch < 0x1000) {
            return "\\u0" + Integer.toHexString(ch);
        }
        return "\\u" + Integer.toHexString(ch);
    }
    /**
     * �Ƴ��ַ���
     * @param str
     * @param remove
     * @return
     */
    public static String removeStr(String str,String remove){
    	if(StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(remove)){
    		str = str.replaceAll(remove, "");
    	}
    	return str;
    }
    
	/**
	 * �滻����
	 */
	public static String replaceName(String str1,String str2){
		if(str1.contains(str2)){
			str1=str1.replaceAll(str2, "");
		}
		return str1;
	}
	
	/**
	 * �Ƚ����
	 * @param str
	 * @param val
	 * @return
	 */
	public static boolean compareEqual(String str,int val){
		int n=Integer.parseInt(str);
		if(n==val)
			return true;
		else
			return false;
	}
	
	/**
	 * �Ƚϴ���
	 * @param str
	 * @param val
	 * @return
	 */
	public static boolean compareBig(String str,int val){
		int n=Integer.parseInt(str);
		if(n>val)
			return true;
		else
			return false;
	}
	/**
	 * �Ƚ�С��
	 * @param str
	 * @param val
	 * @return
	 */
	public static boolean compareSmall(String str,int val){
		int n=Integer.parseInt(str);
		if(n<val)
			return true;
		else
			return false;
	}
    
	/**
	 * ���locs��ָ����str�±��ַ�
	 * @param str
	 * @param locs
	 * @return
	 */
	public static String getSimpleString(String str,int[] locs) {
		if (str != null&&locs!=null) {
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<locs.length;i++){
				sb.append(str.substring(locs[i]-1, locs[i]));
			}
			return sb.toString();
		}else{
			logger.info("null gradeHashTable");
			if(str==null){
				str="";
			}
			return str;
		}		
	}
	
    public static void main(String[] args) {
		System.out.println(StringUtil.replaceUrl("http://www.baidu.comwohihttp://www.oschina.comfdsal���Ƕ�Ҫ�úõ�"));
		System.out.println(changeToFull("!@#$%^&*()_+||??\\"));
		System.out.println(unicodeEscaped('��'));
		System.out.println(removeStr("abcdekfdsjak��fjdsal�ҷ������Ͽ�","��"));
		
		System.out.println(getSimpleString("abcdefghigkl",new int[]{5,4,5,6}));
		
		System.out.println(substr("abcdefghijk",6,"..."));
	}
    
}

















