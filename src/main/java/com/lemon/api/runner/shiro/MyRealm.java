package com.lemon.api.runner.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lemon.api.runner.pojo.User;
import com.lemon.api.runner.service.IUserService;

public class MyRealm extends AuthorizingRealm{
	@Autowired
	private IUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/* 自定义登录校验
	 * (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = token.getPrincipal().toString();
		User user = new User();
		user.setUsername(username);
		try {
			User dbUser = userService.find(user);
			if(dbUser==null){
				return null;
			}else {
				SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(dbUser, dbUser.getPassword(), getName());
				return simpleAuthenticationInfo;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
