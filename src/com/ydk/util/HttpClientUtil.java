package com.ydk.util;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 *@author :ydk
 *@date: 2015-7-7
 *@version: 
 */
public class HttpClientUtil {

    public static Logger logger = Logger.getLogger(HttpClientUtil.class);

    public static void main(String[] args) throws Exception {

        HttpClient client = new HttpClient();
        StringBuilder sb = new StringBuilder();
        InputStream ins = null;
        // Create a method instance.
        GetMethod method = new GetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ com.ydk.config.Constant.APPID+"&secret="+com.ydk.config.Constant.APP_SECRET+"&code="+"02c988d7a0b5ab1040e99b177ef770b5"+"&grant_type=authorization_code");

        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode == HttpStatus.SC_OK) {
                ins = method.getResponseBodyAsStream();
                byte[] b = new byte[1024];
                int r_len = 0;
                while ((r_len = ins.read(b)) > 0) {
                    sb.append(new String(b, 0, r_len, method
                            .getResponseCharSet()));
                }
            } else {
                System.err.println("Response Code: " + statusCode);
            }
        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
        } finally {
            method.releaseConnection();
            if (ins != null) {
                ins.close();
            }
        }
        System.out.println(sb.toString());
    }

    public static String getHttpResponce(String url) throws IOException {
        HttpClient client = new HttpClient();
        StringBuilder sb = new StringBuilder();
        InputStream ins = null;
        // Create a method instance.
        GetMethod method = new GetMethod(url);

        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode == HttpStatus.SC_OK) {
                ins = method.getResponseBodyAsStream();
                byte[] b = new byte[1024];
                int r_len = 0;
                while ((r_len = ins.read(b)) > 0) {
                    sb.append(new String(b, 0, r_len, method
                            .getResponseCharSet()));
                }
            } else {
                //System.err.println("Response Code: " + statusCode);
                logger.info("Response Code: " + statusCode);
            }
        } catch (HttpException e) {
            //System.err.println("Fatal protocol violation: " + e.getMessage());
            logger.error("Fatal protocol violation: " + e.getMessage());
        } catch (IOException e) {
            //System.err.println("Fatal transport error: " + e.getMessage());
            logger.error("Fatal transport error: " + e.getMessage());
        } finally {
            method.releaseConnection();
            if (ins != null) {
                ins.close();
            }
        }

        return sb.toString();
    }

    /*public static String getHttpResponce(String url) throws IOException {
        System.setProperty ("jsse.enableSNIExtension", "false");
        // 创建URL对象
        URL myURL = new URL(url);
        // 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
        HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection();
        // 取得该连接的输入流，以读取响应内容
        InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());

        StringBuilder sb = new StringBuilder();
        // 读取服务器的响应内容并显示
        int respInt = insr.read();
        while (respInt != -1) {
            sb.append((char) respInt);
            respInt = insr.read();
        }
        return sb.toString();
    }*/

}
