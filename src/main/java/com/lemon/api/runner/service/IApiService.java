package com.lemon.api.runner.service;

import com.lemon.api.runner.pojo.Api;
import com.lemon.api.runner.pojo.ApiEditVO;
import com.lemon.api.runner.pojo.ApiListVO;
import com.lemon.api.runner.pojo.ApiOnlineRunResult;
import com.lemon.api.runner.pojo.ApiRunVO;
import com.lemon.api.runner.pojo.ApiViewVO;
import com.lemon.api.runner.pojo.MenuVO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * InnoDB free: 6144 kB 服务类
 * </p>
 *
 * @author nickjiang
 * @since 2019-08-12
 */
public interface IApiService extends IService<Api> {

	List<ApiListVO> showApiListByProjectId(String projectId) throws Exception ;

	List<ApiListVO> showApiListByApiClassification(String apiClassificationId) throws Exception;

	MenuVO findApiSelectedMenu(String apiId) throws Exception;

	ApiViewVO findApiViewVO(String apiId) throws Exception;

	void edit(ApiEditVO apiEditVO) throws Exception;

	ApiEditVO findApiEditVO(String apiId) throws Exception;

	ApiRunVO findApiRunVO(String apiId) throws Exception;

	ApiOnlineRunResult run(ApiRunVO apiRunVO) throws Exception;

}
