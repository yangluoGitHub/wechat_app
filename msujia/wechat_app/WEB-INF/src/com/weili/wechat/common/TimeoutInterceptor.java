package com.weili.wechat.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.weili.wechat.common.UserSession;

/**
 * 该拦截器检验是否登陆和是否会话超时
 * 
 * @author hpshen
 * @since 2007.04.10
 */

public class TimeoutInterceptor extends HandlerInterceptorAdapter {
	
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        
		String uri = request.getRequestURI();
		String sid_session = request.getSession().getId();
		String sid_url = request.getParameter("sid");
		
		
		if(uri.endsWith("register.do")){
			return true;
		}
		if(uri.endsWith("login.do")){
			if(sid_url!=null&&sid_url!=""){
				if (request.getSession().getAttribute("userSession") == null){
					response.sendRedirect("timeout.jsp");
					return false;			
				}
				else if(!((Boolean)request.getSession().getAttribute("Available")))
				{
					HttpSession clientSession = request.getSession();
					response.sendRedirect("intercityLogin.jsp?loginIP=" + clientSession.getAttribute("IP_Available") + "&loginTime=" + clientSession.getAttribute("Time_Available"));
					return false;			
				}
				else if(!sid_session.equals(sid_url)) {
					response.sendRedirect("loginagain.jsp");
					return false;	
				}
			}
			return true;
		}

		try
		{
			if (request.getSession().getAttribute("userSession") == null){
				response.sendRedirect("timeout.jsp");
				return false;			
			}
			else if(!((Boolean)request.getSession().getAttribute("Available")))
			{
				HttpSession clientSession = request.getSession();
				response.sendRedirect("intercityLogin.jsp?loginIP=" + clientSession.getAttribute("IP_Available") + "&loginTime=" + clientSession.getAttribute("Time_Available"));
				return false;			
			}
			else if(sid_url!=null&&sid_url!=""){
				if(!sid_session.equals(sid_url)) {
					response.sendRedirect("loginagain.jsp");
					return false;	
				}
				return true;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.sendRedirect("timeout.jsp");
			return false;
		}
	}

}
