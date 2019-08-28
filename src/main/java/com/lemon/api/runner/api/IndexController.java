package com.lemon.api.runner.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lemon.api.runner.pojo.ApiClassification;
import com.lemon.api.runner.pojo.MenuVO;
import com.lemon.api.runner.pojo.Result;
import com.lemon.api.runner.pojo.ResultMsg;
import com.lemon.api.runner.service.IApiClassificationSerivce;
import com.lemon.api.runner.service.IApiService;
import com.lemon.api.runner.service.impl.ApiServiceImpl;


@RestController
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private IApiClassificationSerivce apiClassificationService;
	@Autowired
	private IApiService apiService;
	@RequestMapping("/toIndex")
	public ModelAndView toIndex(String projectId,String tab){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectId", projectId);
		//查出改项目底下的所有分类 
		try {
			if("1".equals(tab)){
				List<ApiClassification> apiClassifications = apiClassificationService.findAll(projectId);
				modelAndView.addObject("apiClassifications", apiClassifications);
			}else if("2".equals(tab)){
				//加载测试集合列表数据
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.addObject("tab", tab);
		modelAndView.setViewName("index");
		return modelAndView;
	}
	@RequestMapping("findApiSelectedMenu")
	public Result findApiSelectedMenu(String apiId){
		//根据接口编号查到一级、二级菜单
		Result result = null;
		try {
			MenuVO menuVO = apiService.findApiSelectedMenu(apiId);
			result = new Result("1", menuVO, "查询一级二级菜单成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(), ResultMsg.SERVER_GO_WRONG.getMsg());
		}
		return result;
	}
}
