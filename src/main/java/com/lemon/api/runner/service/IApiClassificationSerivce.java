package com.lemon.api.runner.service;

import java.util.List;

import com.lemon.api.runner.pojo.ApiClassification;

public interface IApiClassificationSerivce {

	void add(ApiClassification apiClassification) throws Exception;

	List<ApiClassification> findAll(String projectId) throws Exception;

	List<ApiClassification> findAllByApiId(String apiId) throws Exception;

}
