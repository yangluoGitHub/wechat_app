package com.weili.wechat.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.weili.wechat.hibernate.SysLogId;
import com.weili.wechat.vo.LogAudit;



/**
 * RequestInterceptor
 * 用户Session拉截器
 * @author zhangyong
 * @since 2007.05.28
 */

public class RequestInterceptor extends HandlerInterceptorAdapter {
	private static Log log = LogFactory.getLog(RequestInterceptor.class);
	private LogAudit logAudit;

	public RequestInterceptor() {

    }
    
	public LogAudit getLogAudit() {
		return this.logAudit;
	}

	public void setLogAudit(LogAudit logAudit) {
		this.logAudit = logAudit;
	}
	
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        
    	UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");
        String url = request.getServletPath();
        String queryString = request.getQueryString();
        
//        System.out.println("URL信息  ServletPath=["+url+"] QueryString=["+queryString+"]");
        
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
//        System.out.println("测试url="+url);
        
        String userID = "";
        String userName = "";
        if(userSession == null){
        	userID = request.getParameter("account");
        	userName = "登录用户";
    	}else{
    		userID = userSession.getAccount();
    		userName = userSession.getName();
    	}
        
        this.getLogAudit().setUserId(userID);
        this.getLogAudit().setDate(date);
        this.getLogAudit().setUserName(userName);
        
        log.debug("userid=["+logAudit.getUserId()+"] date=["+logAudit.getDate()+"]");
//        System.out.println("拦截器ServletPath=["+url+"] QueryString=["+queryString+"]");
       
        if(queryString.indexOf("menuURL")>=0){
        	if(url != null){
        		String tmp[] = url.split("/");
        		if(tmp != null){
        			url = tmp[tmp.length-1];
        		}
        	}
        	request.getSession().setAttribute("menuURL", url+"?"+queryString);
        }
        
        return true;
    }
    
    
}
