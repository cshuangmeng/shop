package com.kcx.support.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kcx.support.common.AppConstant;
import com.kcx.support.common.ThreadCache;
import com.kcx.support.pojo.User;
import com.kcx.support.service.UserService;

public class LoginFilter extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//检查用户是否登录
		Map<String, String> param=(Map<String, String>)ThreadCache.getData(AppConstant.HTTP_PARAM_NAME);
		if(StringUtils.isNotEmpty(param.get("userId"))){
			User user=userService.getUser(Integer.parseInt(param.get("userId").toString()));
			if(null!=user){
				ThreadCache.setData(AppConstant.STORE_USER_PARAM_NAME, user);
				return true;
			}
		}
		return false;
	}
	
}
