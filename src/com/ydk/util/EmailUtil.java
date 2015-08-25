package com.ydk.util;

import com.ydk.mail.MailSenderInfo;
import com.ydk.mail.SimpleMailSender;

/**
 * �����ʼ�
 * @author user
 *
 */
public class EmailUtil {
	/**
	 * �����ʼ�
	 * @param serverHost �ʼ�������
	 * @param serverPort �ʼ��������˿ں�
	 * @param validate  ��֤
	 * @param userName  Դ�ʼ��û���
	 * @param password Դ�ʼ�����
	 * @param fromAddress  Դ�ʼ���ַ
	 * @param toAddress  Ŀ���ʼ���ַ
	 * @param subject  ���⣺�ʼ��ı���
	 * @param content  ���ݣ��ʼ�������
	 */
	public static void sendEmail(String serverHost,String serverPort,boolean validate,String userName,String password,
			String fromAddress,String toAddress,String subject,String content){
		 MailSenderInfo mailInfo = new MailSenderInfo();  
	     mailInfo.setMailServerHost(serverHost);  //smtp.126.com
	     mailInfo.setMailServerPort(serverPort);//25
	     mailInfo.setValidate(validate);
	     mailInfo.setUsername(userName);
	     mailInfo.setPassword(password);//������������
	     mailInfo.setFromAddress(fromAddress);
	     mailInfo.setToAddress(toAddress);
	     mailInfo.setSubject(subject);
	     mailInfo.setContent(content);
	        //�������Ҫ�������ʼ�
	     SimpleMailSender sms = new SimpleMailSender();
//	     sms.sendTextMail(mailInfo);//���������ʽ 
	     sms.sendHtmlMail(mailInfo);//����html��ʽ   
	}
}
