package com.lemon.api.runner.dao;

import com.lemon.api.runner.pojo.User;

public interface UserMapper {
	public void add(User user) throws Exception;

	public User find(User user) throws Exception;
}
