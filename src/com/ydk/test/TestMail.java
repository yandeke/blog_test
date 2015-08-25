package com.ydk.test;

import com.ydk.mail.MailSenderInfo;
import com.ydk.mail.SimpleMailSender;

public class TestMail {
	public static void main(String[] args){   
        //这个类主要是设置邮件   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost("smtp.126.com");  //smtp.126.com  
     mailInfo.setMailServerPort("25");    
     mailInfo.setValidate(true);    
     mailInfo.setUsername("yandeke123@126.com");    
     mailInfo.setPassword("y867904325");//您的邮箱密码    
     mailInfo.setFromAddress("yandeke123@126.com");    
     mailInfo.setToAddress("867904325@qq.com");    
     mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国桂花网");    
     mailInfo.setContent("设置邮箱内容 如http://www.guihua.org 中国桂花网 是中国最大桂花网站==");    
        //这个类主要来发送邮件   
     SimpleMailSender sms = new SimpleMailSender();   
     sms.sendTextMail(mailInfo);//发送文体格式    
     sms.sendHtmlMail(mailInfo);//发送html格式   
   }  
}
