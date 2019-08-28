package com.lemon.api.runner.util;

import org.apache.shiro.SecurityUtils;

import com.lemon.api.runner.pojo.User;

public class LoginUtils {
	
	/**获取当前登录用户
	 * @return
	 */
	public static User getCurrentUser() {
		// 取出当前会话中的用户信息
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
	
	/**获取当前登录用户的id
	 * @return
	 */
	public static String getCurrentUserId() {
		// 取出当前会话中的用户信息
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if(user==null){
			return "0";
		}
		return user.getId();
	}
	
	/**获取当前登录用户的用户名
	 * @return
	 */
	public static String getCurrentUsername() {
		// 取出当前会话中的用户信息
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return user.getUsername();
	}
}
