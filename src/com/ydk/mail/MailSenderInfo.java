package com.ydk.mail;

import java.util.Properties;

/**
 * 
 * @author user
 * 发送邮件要使用的基本信息
 */
public class MailSenderInfo {
	//发送邮件的服务器的ip和端口
	private String mailServerHost;
	private String mailServerPort = "25";
	
	//邮件发送者地址
	private String fromAddress;
	
	//邮件接受者地址
	private String toAddress;
	
	//登录邮件发送服务器的用户名和密码
	private String username;
	private String password;
	
	//是否需要身份验证
	private boolean validate = false;
	
	//邮件主题
	private String subject;
	
	//邮件的文本内容
	private String content;
	
	//邮件附件的文件名
	private String[] attachFileName;
	
	
	
	public String getMailServerHost() {
		return mailServerHost;
	}



	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}



	public String getMailServerPort() {
		return mailServerPort;
	}



	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}



	public String getFromAddress() {
		return fromAddress;
	}



	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}



	public String getToAddress() {
		return toAddress;
	}



	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isValidate() {
		return validate;
	}



	public void setValidate(boolean validate) {
		this.validate = validate;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String[] getAttachFileName() {
		return attachFileName;
	}



	public void setAttachFileName(String[] attachFileName) {
		this.attachFileName = attachFileName;
	}



	/**
	 * 获得邮件的会话属性
	 * @return
	 */
	public Properties getProperties(){
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate?"true":"false");
		return p;
	}
	
	
}
