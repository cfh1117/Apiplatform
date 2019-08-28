package com.lemon.api.runner.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lemon.api.runner.pojo.Project;
import com.lemon.api.runner.pojo.Result;
import com.lemon.api.runner.pojo.ResultMsg;
import com.lemon.api.runner.service.IProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	private IProjectService projectService;
	
	@RequestMapping("/toList")
	public ModelAndView toList(){
		ModelAndView modelAndView = new ModelAndView();
		List<Project> projects = new ArrayList<Project>();
		//取出列表页的数据
		try {
			projects = projectService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将数据传递到页面
		modelAndView.addObject("projects", projects);
		modelAndView.setViewName("projectList");
		return modelAndView;
	}
	
	@RequestMapping("/add")
	public Result add(Project project){
		Result result = null;
		try {
			projectService.add(project);
			result = new Result("1","添加项目成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(),ResultMsg.SERVER_GO_WRONG.getMsg());
		}
		return result;
	}
}
