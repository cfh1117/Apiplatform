package com.lemon.api.runner.service;

import com.lemon.api.runner.pojo.User;

public interface IUserService {
	void add(User user) throws Exception;

	User find(User user) throws Exception;
}
