package me.paul.yiblog_ssm.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.paul.yiblog_ssm.entity.User;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String str = request.getRequestURI();
		if(str.matches("/.+/.+")){
			str = str.substring(1);
		}
		int index = str.indexOf('/');
		if(index >= 0){
			str = str.substring(index);
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentAdmin");
		if(user == null){
			response.sendRedirect(request.getContextPath() + "/login?target=" + str);
			return false;
		}
		return true;
	}

}
