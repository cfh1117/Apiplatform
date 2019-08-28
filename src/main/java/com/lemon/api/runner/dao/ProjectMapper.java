package com.lemon.api.runner.dao;

import java.util.List;

import com.lemon.api.runner.pojo.Project;

public interface ProjectMapper {
	void add(Project project) throws Exception;

	List<Project> findAll() throws Exception;
}
