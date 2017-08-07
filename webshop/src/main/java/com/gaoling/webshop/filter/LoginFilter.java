package com.gaoling.webshop.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gaoling.webshop.common.AppConstant;
import com.gaoling.webshop.common.ThreadCache;
import com.gaoling.webshop.user.pojo.User;

import net.sf.json.JSONObject;

public class LoginFilter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//检查用户是否登录
		User user=(User)request.getSession().getAttribute(AppConstant.STORE_USER_PARAM_NAME);
		Logger.getLogger("file").info("LoginFilter | preHandle | "+JSONObject.fromObject(user));
		if(null==user){
			if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest)request).getHeader("X-Requested-With"))) {
				response.setStatus(401);
			}else{
				response.sendRedirect(request.getContextPath()+"/login");
			}
			return false;
		}
		ThreadCache.setData(AppConstant.STORE_USER_PARAM_NAME, user);
		return true;
	}
	
}
