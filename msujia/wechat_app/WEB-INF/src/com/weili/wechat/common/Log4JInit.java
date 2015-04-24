package com.weili.wechat.common;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;

import org.apache.log4j.*;
public class Log4JInit extends HttpServlet {
	public void init() throws ServletException {
		String prefix = getServletContext().getRealPath("/");
		String file = getServletConfig().getInitParameter("log4j-config-file");
		
		// 从Servlet参数读取log4j的配置文件
		if (file != null) {
			//System.out.println("---从Servlet参数读取log4j的配置文件----");
			PropertyConfigurator.configureAndWatch(prefix + file,60000);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	}
}
