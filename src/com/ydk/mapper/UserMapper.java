package com.ydk.mapper;

import org.springframework.stereotype.Repository;

import com.ydk.model.User;

@Repository
public interface UserMapper {
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
