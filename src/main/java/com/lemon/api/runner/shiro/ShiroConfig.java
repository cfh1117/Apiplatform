package com.lemon.api.runner.shiro;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;

/**shiro的配置
 * @author Administrator
 *
 */
@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shiroFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/user/login", "anon");
		filterChainDefinitionMap.put("/user/checkRepeat", "anon");
		filterChainDefinitionMap.put("/user/toRegister", "anon");
		filterChainDefinitionMap.put("/user/register", "anon");
		// 过滤链定义，从上向下顺序执行
		// authc:url都必须认证通过才可以访问; anon:url都可以匿名访问
//		filterChainDefinitionMap.put("/**", "authc");
		filterChainDefinitionMap.put("/**", "anon");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login"页面
		shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
		return shiroFilterFactoryBean;
	}

	// 重新设置SecurityManager，通过自动以的MyRealm完成登录校验：
	@Bean
	public MyRealm myReal() {
		return new MyRealm();
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm
		securityManager.setRealm(myReal());
		return securityManager;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
