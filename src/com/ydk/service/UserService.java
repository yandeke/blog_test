package com.ydk.service;

import com.ydk.model.User;

public interface UserService {
	/**
	 * 用户注册
	 * @param user
	 */
	public void registerUser(User user);
	
	/**
	 *  
	 * @param username
	 * @return
	 */
	public User findUserByUserName(String username);
	
	/**
	 * 邮件验证
	 * @param user
	 */
	public void updateUserIsValidate(User user);
}
