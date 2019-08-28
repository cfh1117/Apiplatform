package com.lemon.api.runner.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.lemon.api.runner.pojo.Result;
import com.lemon.api.runner.pojo.ResultMsg;
import com.lemon.api.runner.pojo.Suite;
import com.lemon.api.runner.service.ISuiteService;

/**
 * <p>
 * InnoDB free: 6144 kB 前端控制器
 * </p>
 *
 * @author nickjiang
 * @since 2019-08-28
 */
@RestController
@RequestMapping("/suite")
public class SuiteController {
	@Autowired
	private ISuiteService suiteService;
	@RequestMapping("/add")
	public Result add(Suite suite){
		Result result = null;
		try {
			suiteService.save(suite);
			result = new Result("1","success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(), ResultMsg.SERVER_GO_WRONG.getMsg());
		}
		return result;
	}
}
