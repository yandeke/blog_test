package com.ydk.mapper;

import org.springframework.stereotype.Repository;

import com.ydk.model.User;

@Repository
public interface UserMapper {
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
