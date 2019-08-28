package com.lemon.api.runner.service.impl;

import com.lemon.api.runner.pojo.Api;
import com.lemon.api.runner.pojo.ApiEditVO;
import com.lemon.api.runner.pojo.ApiListVO;
import com.lemon.api.runner.pojo.ApiOnlineRunResult;
import com.lemon.api.runner.pojo.ApiRequestParam;
import com.lemon.api.runner.pojo.ApiRunVO;
import com.lemon.api.runner.pojo.ApiViewVO;
import com.lemon.api.runner.pojo.MenuVO;
import com.lemon.api.runner.dao.ApiMapper;
import com.lemon.api.runner.service.IApiRequestParamService;
import com.lemon.api.runner.service.IApiService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author nickjiang
 * @since 2019-08-12
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements IApiService {
	@Autowired
	private ApiMapper apiMapper;
	@Autowired
	private IApiRequestParamService apiRequestParamService;
	@Override
	public List<ApiListVO> showApiListByProjectId(String projectId) throws Exception {
		// TODO Auto-generated method stub
		return apiMapper.showApiListByProjectId(projectId);
	}
	@Override
	public List<ApiListVO> showApiListByApiClassification(String apiClassificationId) throws Exception {
		return apiMapper.showApiListByApiClassification(apiClassificationId);
	}
	@Override
	public MenuVO findApiSelectedMenu(String apiId) throws Exception {
		return apiMapper.findApiSelectedMenu(apiId);
	}
	@Override
	public ApiViewVO findApiViewVO(String apiId) throws Exception {
		ApiViewVO apiViewVO = apiMapper.findApiViewVO(apiId);
		if(apiViewVO!=null){
			List<ApiRequestParam> apiRequestParams = apiViewVO.getRequestParams();
			for (ApiRequestParam apiRequestParam : apiRequestParams) {
				if("1".equals(apiRequestParam.getType()+"")){
					apiViewVO.getQueryParams().add(apiRequestParam);
				}else if("2".equals(apiRequestParam.getType()+"")){
					apiViewVO.getBodyParams().add(apiRequestParam);
				}else if("3".equals(apiRequestParam.getType()+"")){
					apiViewVO.getHeaderParams().add(apiRequestParam);
				}else if("4".equals(apiRequestParam.getType()+"")){
					apiViewVO.getBodyRawParams().add(apiRequestParam);
				}
			}
		}
		return apiViewVO;
	}
	@Override
	public void edit(ApiEditVO apiEditVO) throws Exception {
		//更新接口的基本信息
		apiMapper.updateBasicInfo(apiEditVO);
		//删除已关联的参数字段
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("api_id", apiEditVO.getId());
		apiRequestParamService.removeByMap(columnMap);
		//插入接口的参数字段
		List<ApiRequestParam> apiRequestParams = new ArrayList<ApiRequestParam>();
		apiRequestParams.addAll(apiEditVO.getQueryParams());
		apiRequestParams.addAll(apiEditVO.getHeaderParams());
		if(apiEditVO.getBodyRawParams().size()>0){
			//原始参数
			apiRequestParams.addAll(apiEditVO.getBodyRawParams());
		}else{
			//表单参数
			apiRequestParams.addAll(apiEditVO.getBodyParams());
		}
		apiRequestParamService.saveBatch(apiRequestParams);
	}
	@Override
	public ApiEditVO findApiEditVO(String apiId) throws Exception {
		ApiEditVO apiEditVO = apiMapper.findApiEditVO(apiId);
		if(apiEditVO!=null){
			List<ApiRequestParam> apiRequestParams = apiEditVO.getRequestParams();
			for (ApiRequestParam apiRequestParam : apiRequestParams) {
				if("1".equals(apiRequestParam.getType()+"")){
					apiEditVO.getQueryParams().add(apiRequestParam);
				}else if("2".equals(apiRequestParam.getType()+"")){
					apiEditVO.getBodyParams().add(apiRequestParam);
				}else if("3".equals(apiRequestParam.getType()+"")){
					apiEditVO.getHeaderParams().add(apiRequestParam);
				}else if("4".equals(apiRequestParam.getType()+"")){
					apiEditVO.getBodyRawParams().add(apiRequestParam);
				}
			}
		}
		return apiEditVO;
	}
	@Override
	public ApiRunVO findApiRunVO(String apiId) throws Exception {
		ApiRunVO apiRunVO = apiMapper.findApiRunVO(apiId);
		if(apiRunVO!=null){
			List<ApiRequestParam> apiRequestParams = apiRunVO.getRequestParams();
			for (ApiRequestParam apiRequestParam : apiRequestParams) {
				if("1".equals(apiRequestParam.getType()+"")){
					apiRunVO.getQueryParams().add(apiRequestParam);
				}else if("2".equals(apiRequestParam.getType()+"")){
					apiRunVO.getBodyParams().add(apiRequestParam);
				}else if("3".equals(apiRequestParam.getType()+"")){
					apiRunVO.getHeaderParams().add(apiRequestParam);
				}else if("4".equals(apiRequestParam.getType()+"")){
					apiRunVO.getBodyRawParams().add(apiRequestParam);
				}
			}
		}
		return apiRunVO;
	}
	@Override
	public ApiOnlineRunResult run(ApiRunVO apiRunVO) throws Exception {
		//取出接口信息，完成接口調用，返回數據
		String host = apiRunVO.getHost();
		String url = apiRunVO.getUrl();
		url = host+url;
		String method = apiRunVO.getMethod();
		CloseableHttpResponse httpResponse = null;
		ApiOnlineRunResult apiOnlineRunResult = new ApiOnlineRunResult();
		if("get".equalsIgnoreCase(method)){
			//參數拼接在url上
			List<ApiRequestParam> queryParams = apiRunVO.getQueryParams();
			List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
			for (ApiRequestParam apiRequestParam : queryParams) {
				nameValuePairs.add(new BasicNameValuePair(apiRequestParam.getName(), apiRequestParam.getValue()));
			}
			String queryParamString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
			url = url+"?"+queryParamString;
			//創建HttpGet對象模擬get請求
			HttpGet httpGet = new HttpGet(url);
			//準備客戶端
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//设置请求头
			List<ApiRequestParam> headerParams = apiRunVO.getHeaderParams();
			for (ApiRequestParam apiRequestParam : headerParams) {
				httpGet.addHeader(new BasicHeader(apiRequestParam.getName(), apiRequestParam.getValue()));
			}
			//發送請求，執行接口請求
			httpResponse = httpClient.execute(httpGet);
			
		}else if("post".equalsIgnoreCase(method)){
			//參數拼接在url上
			List<ApiRequestParam> queryParams = apiRunVO.getQueryParams();
			List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
			for (ApiRequestParam apiRequestParam : queryParams) {
				nameValuePairs.add(new BasicNameValuePair(apiRequestParam.getName(), apiRequestParam.getValue()));
			}
			String queryParamString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
			if(StringUtils.isNotEmpty(queryParamString)){
				url = url+"?"+queryParamString;
			}
			//創建HttpPost对象来模拟post请求
			HttpPost httpPost = new HttpPost(url);
			//准备参数
			List<ApiRequestParam> bodyParams = apiRunVO.getBodyParams();
			List<ApiRequestParam> bodyRawParams = apiRunVO.getBodyRawParams();
			String bodyParamString = null;
			if(bodyParams.size()>0){
				List<BasicNameValuePair> nameValuePairs2 = new ArrayList<BasicNameValuePair>();
				for (ApiRequestParam apiRequestParam : bodyParams) {
					nameValuePairs2.add(new BasicNameValuePair(apiRequestParam.getName(), apiRequestParam.getValue()));
				}
				bodyParamString = URLEncodedUtils.format(nameValuePairs2, "UTF-8");
			}else if(bodyRawParams.size()>0){
				ApiRequestParam apiRequestParam = bodyParams.get(0);
				String value = apiRequestParam.getValue();
				bodyParamString = value;
			}
			//参数设置到请求体
			httpPost.setEntity(new StringEntity(bodyParamString, "UTF-8"));
			//设置请求头
			List<ApiRequestParam> headerParams = apiRunVO.getHeaderParams();
			for (ApiRequestParam apiRequestParam : headerParams) {
				httpPost.addHeader(new BasicHeader(apiRequestParam.getName(), apiRequestParam.getValue()));
			}
			//执行接口调用
			CloseableHttpClient httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpPost);
		}
		//取出響應頭
		Header [] headers = httpResponse.getAllHeaders();
		Map<String, Object> headerMap = new HashMap<String,Object>();
		for (Header header : headers) {
			String name = header.getName();
			String value = header.getValue();
			headerMap.put(name, value);
		}
		//要写回到页面的响应头数据
		String headerJsonString = JSONObject.toJSONString(headerMap, true);
		//要写回到页面的响应报文
		String result = EntityUtils.toString(httpResponse.getEntity());
		//json字符串--》java对象（反序列化）
		HashMap<String, Object> resultMap = JSONObject.parseObject(result, HashMap.class);
		//java对象---》json字符串（反序列化）
		String resultJsonString = JSONObject.toJSONString(resultMap, true);
		apiOnlineRunResult.setResponseHeaders(headerJsonString);
		apiOnlineRunResult.setResponseBody(resultJsonString);
		return apiOnlineRunResult;
	}

}
