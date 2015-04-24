package com.weili.wechat.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.NDC;

/**
 * 在日志中记录用户信息
 * @author yuhou
 */

public class LogFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		UserSession userSession = (UserSession)httpReq.getSession().getAttribute("userSession");
		if(userSession == null) {
			chain.doFilter(request, response);
			return;
		} 
		NDC.push(userSession.getAccount());
		chain.doFilter(request, response);
//		response.getOutputStream().write("xxx".getBytes());
		NDC.pop();
		NDC.remove();
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}