package com.lemon.api.runner.pojo;

import lombok.Data;

/**平台用户实体类
 * @author Administrator
 *
 */
@Data
public class User extends BasePojo{
	private String username;
	private String password;
	private String regtime;
}
