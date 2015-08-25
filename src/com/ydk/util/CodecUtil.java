package com.ydk.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.UUID;
/**
 *@author :ydk
 *@date: 2015-7-6
 *@version: 
 */


public class CodecUtil {

    private static final Logger logger = LoggerFactory.getLogger(CodecUtil.class);

    /*
     *  �� URL ����
     */
    public static String urlEncode(String str) {
        String target;
        try {
            target = URLEncoder.encode(str, Constant.UTF_8);
        } catch (Exception e) {
            logger.error("�������", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // �� URL ����
    public static String urlDecode(String str) {
        String target;
        try {
            target = URLDecoder.decode(str, Constant.UTF_8);
        } catch (Exception e) {
            logger.error("�������", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // ���ַ��� Base64 ����
    public static String encodeBase64(byte[] bt) {
        String target;
        try {
            target = Base64.encodeBase64String(bt);
        } catch (Exception e) {
            logger.error("�������", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // ���ַ��� Base64 ����
    public static String encodeBase64(String str) {
        String target;
        try {
            target = Base64.encodeBase64URLSafeString(str.getBytes(Constant.UTF_8));
        } catch (UnsupportedEncodingException e) {
            logger.error("�������", e);
            throw new RuntimeException(e);
        }
        return target;
    }


    // ���ַ��� Base64 ����
    public static String decodeBase64(String str) {
        String target;
        try {
            target = new String(Base64.decodeBase64(str), Constant.UTF_8);
        } catch (UnsupportedEncodingException e) {
            logger.error("�������", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // ���ַ��� Base64 ����
    public static String decodeBase64(byte[] bt) {
        String target;
        try {
            target = new String(Base64.decodeBase64(bt));
        } catch (Exception e) {
            logger.error("�������", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // ���ַ��� Base64 ����
    public static byte[] decodeBase64Byte(byte[] bt) {
        byte[] target;
        try {
            target = Base64.decodeBase64(bt);
        } catch (Exception e) {
            logger.error("�������", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // ���ַ��� Base64 ����
    public static byte[] decodeBase64Byte(String str) {
        byte[] target;
        try {
            target = Base64.decodeBase64(str);
        } catch (Exception e) {
            logger.error("�������", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // ���ַ��� MD5 ����
    public static String encryptMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    // ���ַ��� SHA ����
    public static String encryptSHA(String str) {
        return DigestUtils.shaHex(str);
    }

    // ���������
    public static String createRandom(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    // ��ȡUUID��32λ��
    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    
    /**
     * java.security�Դ���md5����
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {  
    	  
        MessageDigest md5 = MessageDigest.getInstance("md5");  
        md5.update(data);
      
        return md5.digest();  
      
    }  
}

