package com.ydk.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ydk.mapper.UserMapper;
import com.ydk.model.User;
import com.ydk.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserMapper userMapper;
	
	public void registerUser(User user) {
		try{
			userMapper.registerUser(user);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public User findUserByUserName(String username) {
		User user = null;
		try{
			user = userMapper.findUserByUserName(username);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}

	public void updateUserIsValidate(User user) {
		try{
			userMapper.updateUserIsValidate(user);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
