package com.weili.wechat.common;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;

import org.apache.log4j.*;
public class Log4JInit extends HttpServlet {
	public void init() throws ServletException {
		String prefix = getServletContext().getRealPath("/");
		String file = getServletConfig().getInitParameter("log4j-config-file");
		
		// ��Servlet������ȡlog4j�������ļ�
		if (file != null) {
			//System.out.println("---��Servlet������ȡlog4j�������ļ�----");
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
