package com.ydk.util;

import com.ydk.mail.MailSenderInfo;
import com.ydk.mail.SimpleMailSender;

/**
 * 发送邮件
 * @author user
 *
 */
public class EmailUtil {
	/**
	 * 发送邮件
	 * @param serverHost 邮件服务器
	 * @param serverPort 邮件服务器端口号
	 * @param validate  验证
	 * @param userName  源邮件用户名
	 * @param password 源邮件密码
	 * @param fromAddress  源邮件地址
	 * @param toAddress  目标邮件地址
	 * @param subject  主题：邮件的标题
	 * @param content  内容：邮件的内容
	 */
	public static void sendEmail(String serverHost,String serverPort,boolean validate,String userName,String password,
			String fromAddress,String toAddress,String subject,String content){
		 MailSenderInfo mailInfo = new MailSenderInfo();  
	     mailInfo.setMailServerHost(serverHost);  //smtp.126.com
	     mailInfo.setMailServerPort(serverPort);//25
	     mailInfo.setValidate(validate);
	     mailInfo.setUsername(userName);
	     mailInfo.setPassword(password);//您的邮箱密码
	     mailInfo.setFromAddress(fromAddress);
	     mailInfo.setToAddress(toAddress);
	     mailInfo.setSubject(subject);
	     mailInfo.setContent(content);
	        //这个类主要来发送邮件
	     SimpleMailSender sms = new SimpleMailSender();
//	     sms.sendTextMail(mailInfo);//发送文体格式 
	     sms.sendHtmlMail(mailInfo);//发送html格式   
	}
}
