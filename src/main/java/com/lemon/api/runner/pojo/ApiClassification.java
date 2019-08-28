package com.lemon.api.runner.pojo;

import java.util.List;

import lombok.Data;

@Data
public class ApiClassification extends BasePojo{
	private String projectId;
	private String name;
	private String description;
	private List<ApiMenuVO> apiMenuVOs;
}
