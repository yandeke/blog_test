package com.ydk.mail;

import java.util.Properties;

/**
 * 
 * @author user
 * �����ʼ�Ҫʹ�õĻ�����Ϣ
 */
public class MailSenderInfo {
	//�����ʼ��ķ�������ip�Ͷ˿�
	private String mailServerHost;
	private String mailServerPort = "25";
	
	//�ʼ������ߵ�ַ
	private String fromAddress;
	
	//�ʼ������ߵ�ַ
	private String toAddress;
	
	//��¼�ʼ����ͷ��������û���������
	private String username;
	private String password;
	
	//�Ƿ���Ҫ�����֤
	private boolean validate = false;
	
	//�ʼ�����
	private String subject;
	
	//�ʼ����ı�����
	private String content;
	
	//�ʼ��������ļ���
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
	 * ����ʼ��ĻỰ����
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
