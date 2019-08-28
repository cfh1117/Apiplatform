package com.lemon.api.runner.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon.api.runner.dao.ApiClassificationMapper;
import com.lemon.api.runner.pojo.ApiClassification;
import com.lemon.api.runner.service.IApiClassificationSerivce;
import com.lemon.api.runner.util.DateUtil;
import com.lemon.api.runner.util.LoginUtils;
@Service
public class ApiClassificationServiceImpl implements IApiClassificationSerivce {
	@Autowired
	private ApiClassificationMapper apiClassificationMapper;

	@Override
	public void add(ApiClassification apiClassification) throws Exception {
		// TODO Auto-generated method stub
		apiClassificationMapper.add(apiClassification);
	}

	@Override
	public List<ApiClassification> findAll(String projectId) throws Exception {
		return apiClassificationMapper.findAll(projectId);
	}

	@Override
	public List<ApiClassification> findAllByApiId(String apiId) throws Exception {
		return apiClassificationMapper.findAllByApiId(apiId);
	}

}
