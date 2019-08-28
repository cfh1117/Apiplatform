package com.lemon.api.runner.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lemon.api.runner.pojo.ApiClassification;
import com.lemon.api.runner.pojo.Result;
import com.lemon.api.runner.pojo.ResultMsg;
import com.lemon.api.runner.service.IApiClassificationSerivce;

@RestController
@RequestMapping("/apiClassification")
public class ApiClassificationController {
	@Autowired
	private IApiClassificationSerivce apiClassificationSerivce;
	@RequestMapping("/add")
	public Result add(ApiClassification apiClassification){
		Result result = null;
		try {
			apiClassificationSerivce.add(apiClassification);
			result = new Result("1", "创建接口分类成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(),ResultMsg.SERVER_GO_WRONG.getMsg());
		}
		return result;
	}
	
	
	@RequestMapping("/findAll")
	public Result findAll(String projectId){
		Result result = null;
		try {
			List<ApiClassification> apiClassifications = apiClassificationSerivce.findAll(projectId);
			result = new Result("1", apiClassifications, "查询所有分类成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(),ResultMsg.SERVER_GO_WRONG.getMsg());
		}
		return result;
	}
	
}
