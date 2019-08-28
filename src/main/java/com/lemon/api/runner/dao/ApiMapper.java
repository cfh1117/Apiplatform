package com.lemon.api.runner.dao;

import com.lemon.api.runner.pojo.Api;
import com.lemon.api.runner.pojo.ApiEditVO;
import com.lemon.api.runner.pojo.ApiListVO;
import com.lemon.api.runner.pojo.ApiRunVO;
import com.lemon.api.runner.pojo.ApiViewVO;
import com.lemon.api.runner.pojo.MenuVO;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * InnoDB free: 6144 kB Mapper 接口
 * </p>
 *
 * @author nickjiang
 * @since 2019-08-12
 */
public interface ApiMapper extends BaseMapper<Api> {

	List<ApiListVO> showApiListByProjectId(String projectId) throws Exception;

	List<ApiListVO> showApiListByApiClassification(String apiClassificationId) throws Exception;

	MenuVO findApiSelectedMenu(String apiId) throws Exception;

	ApiViewVO findApiViewVO(String apiId) throws Exception;

	void updateBasicInfo(ApiEditVO apiEditVO) throws Exception;

	ApiEditVO findApiEditVO(String apiId) throws Exception;

	ApiRunVO findApiRunVO(String apiId) throws Exception;

}
