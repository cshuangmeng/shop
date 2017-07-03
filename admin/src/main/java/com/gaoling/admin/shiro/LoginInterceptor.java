package com.gaoling.admin.shiro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gaoling.admin.util.AppConstant;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String[] IGNORE_URI = {"/loginSubmit", "/login"};
	 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String url = request.getRequestURL().toString();
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            if (SecurityUtils.getSubject().isAuthenticated()
            		&&null!=SecurityUtils.getSubject().getSession().getAttribute(AppConstant.SESSION_DATA_NAME)){
            		flag = true;
            }
        }
        if(!flag){
	        if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest)request).getHeader("X-Requested-With"))) {
				response.setStatus(401);
			}else{
				response.sendRedirect(request.getContextPath()+"/login");
			}
        }
        return flag;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
	
}
