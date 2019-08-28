package com.lemon.api.runner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon.api.runner.dao.UserMapper;
import com.lemon.api.runner.pojo.User;
import com.lemon.api.runner.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public void add(User user) throws Exception {
		userMapper.add(user);
	}

	@Override
	public User find(User user) throws Exception {
		return userMapper.find(user);
	}

}
