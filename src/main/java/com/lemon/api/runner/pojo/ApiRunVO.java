package com.lemon.api.runner.pojo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class ApiRunVO {
	private String id;
	private String method;
	private String url;
	private String host;
	private List<ApiRequestParam> requestParams = new ArrayList<ApiRequestParam>();
	private List<ApiRequestParam> queryParams = new ArrayList<ApiRequestParam>();
	private List<ApiRequestParam> bodyParams = new ArrayList<ApiRequestParam>();
	private List<ApiRequestParam> headerParams = new ArrayList<ApiRequestParam>();
	private List<ApiRequestParam> bodyRawParams = new ArrayList<ApiRequestParam>();
	
}
