package com.weili.wechat.common;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ҳ����ʼ�����
 * @author zy
 */

public class Accelerator implements Filter {
	private static Log log = LogFactory.getLog(Accelerator.class);
	private FilterConfig config  = null;
	private HashMap expiresMap = new HashMap();

	public void init(FilterConfig filterConfig) {
		this.config = filterConfig;
		expiresMap.clear();
		Enumeration names = config.getInitParameterNames();
		while( names.hasMoreElements() ) {
			try{
				String name = (String)names.nextElement();
				String value = config.getInitParameter( name );
				Integer expire = Integer.valueOf( value );
				expiresMap.put( name, expire );
			} catch( Exception e)  {
				e.printStackTrace();
				log.error("��ȡ��������"+e.getMessage());
			}
		}
	}

	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
  
		String uri = req.getRequestURI();
		String ext = null;
		int dot =  uri.lastIndexOf(".");
		if( dot != -1 ){
			ext = uri.substring( dot+1 );
		}
  
//		log.info("uri=["+uri+"] ext=["+ext+"]");
		setResponseHeader( res, uri, ext );
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
 
	protected FilterConfig getFilterConfig() {
		return (config);
	}
 
	private void setResponseHeader( HttpServletResponse response, String uri, String ext ) {
		if( ext!= null && ext.length() > 0 ){
			Integer expires = (Integer)expiresMap.get(ext);
			if( expires != null ){
//				log.info( uri + ".Expires: "+ expires.intValue());
				if( expires.intValue() > 0 ){
					response.setHeader("Cache-Control","max-age="+expires.intValue()); //HTTP 1.1
				} else {
					response.setHeader("Cache-Control","no-cache");
					response.setHeader("Pragma","no-cache"); //HTTP 1.0
					response.setDateHeader ("Expires", 0 );
				}
			}
		}
	}
}