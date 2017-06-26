package com.gaoling.admin.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class SessionExpireFilter extends FormAuthenticationFilter {
	
	@Override
	protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		//AJAX请求特殊处理
		if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest)request).getHeader("X-Requested-With"))) {
			((HttpServletResponse)response).setStatus(401);
		}else{
			saveRequest(request);
			redirectToLogin(request, response);
		}
	}
	

}
