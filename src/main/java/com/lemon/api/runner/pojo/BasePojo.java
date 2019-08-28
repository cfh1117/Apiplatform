package com.lemon.api.runner.pojo;

import java.util.Date;

import com.lemon.api.runner.util.DateUtil;
import com.lemon.api.runner.util.LoginUtils;

import lombok.Data;

@Data
public class BasePojo {
	private String id;
	private String createUser = LoginUtils.getCurrentUserId();
	private String createTime = DateUtil.formatYmdhms(new Date());
}
