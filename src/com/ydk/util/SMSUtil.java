package com.ydk.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.Header;

import com.ydk.config.Constant;

public final class SMSUtil {
	
	/**
	 * ���Ͷ���
	 * @param code
	 * @param phoneNo//���������,����
	 */
	public static String sendSMS(String code,String phoneNo){
		
		try{
	
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod(Constant.SMS_URL);
			
			post.addRequestHeader(Constant.CONTENT_NAME,Constant.CONTENT_TYPE_VALUE);//��ͷ�ļ�������ת��
			NameValuePair[] data ={ new NameValuePair("Uid", "yandeke123"),
						new NameValuePair("Key", Constant.SMS_KEY),
						new NameValuePair("smsMob",phoneNo),
						new NameValuePair("smsText",Constant.SMS_TEXT_HEAD+code+Constant.SMS_TEXT_TAIL)};
			post.setRequestBody(data);
			
			client.executeMethod(post);
//			Header[] headers = post.getResponseHeaders();
//			int statusCode = post.getStatusCode();
//			System.out.println("statusCode:"+statusCode);
			String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
//			System.out.println(result); //��ӡ������Ϣ״̬  1 Ϊ����
			post.releaseConnection();
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
