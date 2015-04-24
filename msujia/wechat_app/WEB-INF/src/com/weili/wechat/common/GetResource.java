package com.weili.wechat.common;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;
/* 
 * web启动时加载语言包到ServletContext
 * @author ykliu
 * @version 1.0 
 */

public class GetResource implements InitializingBean, ServletContextAware {
	private static ServletContext servletContext;
	
	//所有的语言包信息加载到servletContext
	public void afterPropertiesSet() throws Exception {
		//servletContext.setAttribute("resource_tw", new Resource(Locale.TRADITIONAL_CHINESE.toString()));
		servletContext.setAttribute("resource_cn", new Resource(Locale.CHINA.toString()));
		//servletContext.setAttribute("resource_us", new Resource(Locale.US.toString()));
	}

	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}
	
	//根据语言标识locale取得Resource
	public static Resource getOneResource(ServletContext ctx,String locale) {
		Resource resource;
		if (locale.equals("zh_TW")) {
			resource = (Resource)ctx.getAttribute("resource_tw");
		}else if (locale.equals("zh_CN")){
			resource = (Resource)ctx.getAttribute("resource_cn");
		}else{
			resource = (Resource)ctx.getAttribute("resource_us");
		}
		servletContext.setAttribute("locale",locale);
		return resource;
	}
	
}	
