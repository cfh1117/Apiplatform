package com.lemon.api.runner.service;

import java.util.List;

import com.lemon.api.runner.pojo.Project;

public interface IProjectService {
	void add(Project project) throws Exception;

	List<Project> findAll() throws Exception;
}
