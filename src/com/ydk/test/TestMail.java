package com.ydk.test;

import com.ydk.mail.MailSenderInfo;
import com.ydk.mail.SimpleMailSender;

public class TestMail {
	public static void main(String[] args){   
        //�������Ҫ�������ʼ�   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost("smtp.126.com");  //smtp.126.com  
     mailInfo.setMailServerPort("25");    
     mailInfo.setValidate(true);    
     mailInfo.setUsername("yandeke123@126.com");    
     mailInfo.setPassword("y867904325");//������������    
     mailInfo.setFromAddress("yandeke123@126.com");    
     mailInfo.setToAddress("867904325@qq.com");    
     mailInfo.setSubject("����������� ��http://www.guihua.org �й�����");    
     mailInfo.setContent("������������ ��http://www.guihua.org �й����� ���й�������վ==");    
        //�������Ҫ�������ʼ�   
     SimpleMailSender sms = new SimpleMailSender();   
     sms.sendTextMail(mailInfo);//���������ʽ    
     sms.sendHtmlMail(mailInfo);//����html��ʽ   
   }  
}
