package com.ydk.model;

public class User {
	private Long pid;//pid
	private String name;//姓名
	private String username;//用户名
	private String password;//密码
	private String gender;//性别
	private String email;//邮件
	private String phoneNumber;//电话号码
	private String isvalidate;//是否激活
	
	public String getIsvalidate() {
		return isvalidate;
	}
	public void setIsvalidate(String isvalidate) {
		this.isvalidate = isvalidate;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
