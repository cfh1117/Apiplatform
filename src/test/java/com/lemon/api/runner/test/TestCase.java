package com.lemon.api.runner.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lemon.api.runner.dao.UserMapper;
import com.lemon.api.runner.pojo.Api;
import com.lemon.api.runner.pojo.User;
import com.lemon.api.runner.service.IApiService;
import com.lemon.api.runner.starter.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
public class TestCase {
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private IApiService apiService;
	
	@Test
	public void testAdd() throws Exception{
		Api api = new Api();
		api.setApiClassificationId(1);
		api.setName("aa");
		api.setMethod("post");
		api.setUrl("/xx/yy");
		apiService.save(api);
		
	}
}
