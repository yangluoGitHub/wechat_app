package com.weili.wechat.common;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.weili.wechat.dao.CommonData;

public class SystemParamLoader implements ServletContextListener{
	private static Log log = LogFactory.getLog(SystemParamLoader.class);	

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent e) {
		log.info("初始化系统参数");
		ServletContext ctx = e.getServletContext();
		ApplicationContext acx = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		CommonData commonData = (CommonData)acx.getBean("commonData");
		List paramList = commonData.getAllResult("select s.paramName,s.paramValue from SysParam s");
		for (Object obj : paramList) {
			Object[] temp = (Object[]) obj;
			SystemCons.sysParamMap.put(String.valueOf(temp[0]), String.valueOf(temp[1]));
		}
		log.info("SysParamTab size = [" + paramList.size() + "]");
		
		SystemCons.setRoot(ctx.getRealPath("/"));  //系统根目录
		
	}

}
