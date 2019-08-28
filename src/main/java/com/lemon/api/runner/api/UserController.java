package com.lemon.api.runner.api;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lemon.api.runner.pojo.Result;
import com.lemon.api.runner.pojo.ResultMsg;
import com.lemon.api.runner.pojo.User;
import com.lemon.api.runner.service.IUserService;
import com.lemon.api.runner.util.DateUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/toLogin")
	public ModelAndView toLogin(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping("/toRegister")
	public ModelAndView toRegister(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	
	@RequestMapping("/register")
	public Result register(User user){
		Result result = null;
		user.setRegtime(DateUtil.formatYmdhms(new Date()));
		//数据入库
		try {
			userService.add(user);
			result = new Result("1", "注册成功"); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(),ResultMsg.SERVER_GO_WRONG.getMsg());
		}
		return result;
	}
	
	@RequestMapping("/login")
	public Result login(User user){
		Result result = null;
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			//shiro的api
			SecurityUtils.getSubject().login(token);
			result = new Result("1","登录成功");
		} catch (Exception e) {
			if(e instanceof UnknownAccountException){
				result = new Result("0","账号信息错误，请检查用户名和密码");
			}else{
				result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(),ResultMsg.SERVER_GO_WRONG.getMsg());
			}
		}
		return result;
	}
	
	/**验重的接口
	 * @return
	 */
	@RequestMapping("/checkRepeat")
	public Result checkRepeat(User user){
		Result result = null;
		//数据库查重
		try {
			User dbUser = userService.find(user);
			//被占用
			if(dbUser!=null){
				result = new Result("0","此账号已被占用，请更换用户名");
			}else{//没被占用
				result = new Result("1","无人使用此账号");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new Result(ResultMsg.SERVER_GO_WRONG.getStatus(),ResultMsg.SERVER_GO_WRONG.getMsg());
		}
		return result;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(){
		//销毁当前的session会话
		SecurityUtils.getSubject().logout();
		//返回到登录页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	

}
