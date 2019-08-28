package com.lemon.api.runner.pojo;

import lombok.Data;

@Data
public class Project extends BasePojo{
	private String name;
	private String host;
	private String description;
}
