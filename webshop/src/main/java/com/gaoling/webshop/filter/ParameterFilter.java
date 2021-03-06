package com.gaoling.webshop.filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gaoling.webshop.common.AppConstant;
import com.gaoling.webshop.common.ThreadCache;
import com.gaoling.webshop.user.pojo.User;

public class ParameterFilter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//拼装请求参数
		String queryString="";
		Enumeration<?> pNames = request.getParameterNames();
        Map<String, String> params = new HashMap<String, String>();
        while (pNames.hasMoreElements()) {
            String pName = (String) pNames.nextElement();
            params.put(pName, request.getParameter(pName));
            queryString+=queryString.length()>0?"&"+pName+"="+request.getParameter(pName):pName+"="+request.getParameter(pName);
        }
        User user=(User)request.getSession().getAttribute(AppConstant.STORE_USER_PARAM_NAME);
        ThreadCache.setData(AppConstant.HTTP_PARAM_NAME, params);
        if(null!=user){
        	ThreadCache.setData(AppConstant.STORE_USER_PARAM_NAME, user);
        }
		Logger.getLogger("ParameterFilter").info(request.getRequestURI()+(queryString.length()>0?"?"+queryString:""));
		return true;
	}
	
}
