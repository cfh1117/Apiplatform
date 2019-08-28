package com.lemon.api.runner.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemon.api.runner.dao.ProjectMapper;
import com.lemon.api.runner.pojo.ApiClassification;
import com.lemon.api.runner.pojo.Project;
import com.lemon.api.runner.pojo.User;
import com.lemon.api.runner.service.IApiClassificationSerivce;
import com.lemon.api.runner.service.IProjectService;
import com.lemon.api.runner.util.DateUtil;
import com.lemon.api.runner.util.LoginUtils;
@Service
public class ProjectServiceImpl implements IProjectService {
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private IApiClassificationSerivce apiClassificationSerivce;
	@Override
	public void add(Project project) throws Exception {
		//设置创建时间
		project.setCreateTime(DateUtil.formatYmdhms(new Date()));
		//设置创建人
		project.setCreateUser(LoginUtils.getCurrentUserId());
		projectMapper.add(project);
		//为项目创建一个初识的分类（公共分类）
		ApiClassification apiClassification = new ApiClassification();
		apiClassification.setName("公共分类");
		apiClassification.setProjectId(project.getId());
		apiClassification.setDescription("公共分类");
		apiClassificationSerivce.add(apiClassification);
	}
	@Override
	public List<Project> findAll() throws Exception {
		return projectMapper.findAll();
	}

}
