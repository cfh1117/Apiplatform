package com.lemon.api.runner.dao;

import java.util.List;

import com.lemon.api.runner.pojo.ApiClassification;

public interface ApiClassificationMapper {

	void add(ApiClassification apiClassification) throws Exception;

	List<ApiClassification> findAll(String projectId) throws Exception;

	List<ApiClassification> findAllByApiId(String apiId) throws Exception;

}
