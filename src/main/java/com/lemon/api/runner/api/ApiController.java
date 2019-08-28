package com.lemon.api.runner.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lemon.api.runner.pojo.Api;
import com.lemon.api.runner.pojo.ApiClassification;
import com.lemon.api.runner.pojo.ApiEditVO;
import com.lemon.api.runner.pojo.ApiListVO;
import com.lemon.api.runner.pojo.ApiOnlineRunResult;
import com.lemon.api.runner.pojo.ApiRunVO;
import com.lemon.api.runner.pojo.ApiViewVO;
import com.lemon.api.runner.pojo.Result;
import com.lemon.api.runner.pojo.ResultMsg;
import com.lemon.api.runner.service.IApiClassificationSerivce;
import com.lemon.api.runner.service.IApiService;

/**
 * <p>
 * InnoDB free: 6144 kB 前端控制器
 * </p>
 *
 * @author nickjiang
 * @since 2019-08-12
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private IApiService apiSerive;
	@Autowired
	private IApiClassificationSerivce apiClassificationService;
	@RequestMapping("/toApiList")
	public ModelAndView toApiList(String projectId,String apiClassificationId){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectId", projectId);
		modelAndView.addObject("apiClassificationId",apiClassificationId);
		modelAndView.setViewName("apiList");
		return modelAndView;
	}
	
	@RequestMapping("/add")
	public Result add(Api api){
		Result result = null;
		try {
			apiSerive.save(api);
			result = new Result("1", "添加api成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(),ResultMsg.SERVER_GO_WRONG.getMsg());
		}
		return result;
	}
	
	@RequestMapping("/showApiUnderProject")
	public Result showApiUnderProject(String projectId){
		Result result = null;
		try {
			List<ApiListVO> apiListVOs = apiSerive.showApiListByProjectId(projectId);
			result = new Result("1", apiListVOs, "添加api成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(),ResultMsg.SERVER_GO_WRONG.getMsg());
		}
		return result;
	}
	
	@RequestMapping("/showApiUnderApiClassification")
	public Result showApiUnderApiClassification(String apiClassificationId){
		Result result = null;
		try {
			List<ApiListVO> apiListVOs = apiSerive.showApiListByApiClassification(apiClassificationId);
			result = new Result("1", apiListVOs, "添加api成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(),ResultMsg.SERVER_GO_WRONG.getMsg());
		}
		return result;
	}
	
	@RequestMapping("/toApiView")
	public ModelAndView toApiView(String apiId){
		ModelAndView modelAndView = new ModelAndView();
		//根据接口编号查到接口的基本信息
		try {
			ApiViewVO apiViewVO = apiSerive.findApiViewVO(apiId);
			modelAndView.addObject("apiViewVO",apiViewVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.addObject("apiId", apiId);
		modelAndView.setViewName("apiView");
		return modelAndView;
	}
	@RequestMapping("/toApiEdit")
	public ModelAndView toApiEdit(String apiId){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("apiId", apiId);
		//根据apiId查出接口信息，传递到页面
		ApiEditVO apiEditVO;
		try {
			apiEditVO = apiSerive.findApiEditVO(apiId);
			modelAndView.addObject("apiEditVO", apiEditVO);
			//接口所在項目底下的所有分類
			List<ApiClassification> apiClassifications = apiClassificationService.findAllByApiId(apiId);
			modelAndView.addObject("apiClassifications", apiClassifications);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.setViewName("apiEdit");
		return modelAndView;
	}
	
	@RequestMapping("/toApiRun")
	public ModelAndView toApiRun(String apiId){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("apiId", apiId);
		//根据接口编号，查到接口相关信息
		try {
			ApiRunVO apiRunVO = apiSerive.findApiRunVO(apiId);
			modelAndView.addObject("apiRunVO", apiRunVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.setViewName("apiRun");
		return modelAndView;
	}
	
	@RequestMapping("/edit")
	public Result edit(ApiEditVO apiEditVO){
		Result result = null;
		try {
			apiSerive.edit(apiEditVO);
			result = new Result("1","接口更新成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(),ResultMsg.SERVER_GO_WRONG.getMsg());
		}
		return result;
	}
	
	@RequestMapping("/run")
	public Result run(ApiRunVO apiRunVO){
		Result result = null;
		try {
			ApiOnlineRunResult apiOnlineRunResult = apiSerive.run(apiRunVO);
			result = new Result("1","success");
			result.setData(apiOnlineRunResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(), ResultMsg.SERVER_GO_WRONG.getMsg());
		}
		return result;
	}
}
