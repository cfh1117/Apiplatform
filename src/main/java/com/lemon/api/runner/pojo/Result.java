package com.lemon.api.runner.pojo;

import lombok.Data;

@Data
public class Result {
	/**
	 * 1：代表业务处理成功，0：代表业务处理失败
	 */
	private String status;
	private Object data;
	private String message;
	
	public Result(String status, Object data, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}
	public Result(String status, Object data) {
		this.status = status;
		this.data = data;
	}
	public Result(String status, String message) {
		this.status = status;
		this.message = message;
	}
	public Result() {
		super();
	}
	
	
	
	
}
