package com.lemon.api.runner.pojo;

public enum ResultMsg {
	SERVER_GO_WRONG("0","服务器内部异常，请联系管理员");
	private String status;
	private String msg;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	private ResultMsg(String status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	private ResultMsg() {
	}
	
}
