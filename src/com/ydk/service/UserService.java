package com.ydk.service;

import com.ydk.model.User;

public interface UserService {
	/**
	 * �û�ע��
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
	 * �ʼ���֤
	 * @param user
	 */
	public void updateUserIsValidate(User user);
}
