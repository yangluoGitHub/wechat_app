package com.weili.wechat.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.weili.wechat.hibernate.SysModule;
import com.weili.wechat.hibernate.SysOperate;
import com.weili.wechat.service.system.LogAuditService;
import com.weili.wechat.service.system.UserService;
import com.weili.wechat.vo.LogAudit;
import com.weili.wechat.vo.Module;
import com.weili.wechat.vo.Operate;




/**
 * LogInterceptor
 * 日志
 * @author zhangyong
 * @since 2007.05.28
 */

public class LogInterceptor extends HandlerInterceptorAdapter {
	private static Log log = LogFactory.getLog(LogInterceptor.class);
	private LogAudit logAudit;
	private LogAuditService logAuditService;
	private UserService userService;
	private HashMap moduleMap = new HashMap();
	private HashMap operateMap = new HashMap();
	
	private String module = "";
	private String operate = "";
	public LogAudit getLogAudit() {
		return this.logAudit;
	}

	public void setLogAudit(LogAudit logAudit) {
		this.logAudit = logAudit;
	}
	
	
	public LogAuditService getLogAuditService() {
		return logAuditService;
	}

	public void setLogAuditService(LogAuditService logAuditService) {
		this.logAuditService = logAuditService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void init(){
		log.debug("日志拦截器的初始化  ---- 开始");  

		List moduleList = this.getLogAuditService().qryModule();
		List operateList = this.getLogAuditService().qryOperate();
		SysModule aSysModule = null;
		SysOperate aSysOperate = null;
		for(int i=0;i<moduleList.size();i++){
			aSysModule = (SysModule)moduleList.get(i);
			moduleMap.put(aSysModule.getNo(), aSysModule.getName());
		}
		
		for(int i=0;i<operateList.size();i++){
			aSysOperate = (SysOperate)operateList.get(i);
			operateMap.put(aSysOperate.getNo(), aSysOperate.getName());
		}
		
		log.debug("日志拦截器的初始化  ---- 结束");  	
	}
	public LogInterceptor() {
	}
	
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
    	UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");
        
        String url = request.getServletPath();
        String queryString = request.getQueryString();
//        System.out.println("URL信息  ServletPath=["+url+"] QueryString=["+queryString+"]");
        
        String tmp1[] = url.split("/");
        String tmp2[] = queryString.split("&");
        
        module = tmp1[tmp1.length-1];
        if(tmp2[0].split("=").length>1){
        	operate = tmp2[0].split("=")[1];
        } 
    	
//        System.out.println("URL信息  module=["+module+"] operate=["+operate+"]");
        
        String userID = "";
        String userName = "";
//        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:splits").format(new Date(System.currentTimeMillis()));
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        Enumeration e =  request.getParameterNames();
        Object o = null;
        String xml="";
        //原始对象
        String old_xml = "";
        if(userSession == null){
        	userID = request.getParameter("account");
        	if (userID == null)
        	{
        		userName = "NoID";
        		userID = "NoID";
        	}
        	else
        	{
        		//userName = this.userService.qryUserById(userID).getName();
        		try{//屏蔽数据库异常的情况
	        		if(this.userService.qryUserById(userID)!=null){
	        			userName = this.userService.qryUserById(userID).getName();
	        		}else{
	        			userName = "NoID";
	        		}
        		} catch (Exception e1) {
        			e1.printStackTrace();
        			log.error(e1);
        			userName = "NoID";
        		}
        	}
    	}else{
    		userID = userSession.getAccount();
    		userName = userSession.getName();
    	}

        xml+="<object-array>\r\n";
        while(e.hasMoreElements()){
        	o = e.nextElement();
        	if(o.equals("action")) continue;
        	String value[]=request.getParameterValues(o.toString());
        	for(int i = 0;i<value.length;i++){
        		xml+="  <"+o+">"+value[i]+"</"+o+">\r\n";
        	}
        	//log.debug("请求参数 key=["+o+"] value=["+request.getParameter(o.toString())+"]");
        }
        xml+="</object-array>";
        log.debug("xml=["+xml+"]");
        
        if(module.equals("org.do") && operate.equals("mod"))
        {
        	old_xml = request.getParameter("old_xml");
        }
        this.getLogAudit().setUserId(userID);
        this.getLogAudit().setUserName(userName);
        this.getLogAudit().setDate(date);
        this.getLogAudit().setXml(xml);  
        this.getLogAudit().setOldXml(old_xml);  
        
		Module aModule = new Module();
		aModule.setNo(module);
		Operate aOperate = new Operate();
		aOperate.setNo(operate);
		
		this.getLogAudit().setModule(aModule);
		this.getLogAudit().setOperate(aOperate);
		log.debug("title=["+request.getParameter("title")+"]");
		//this.getLogAudit().setNote(request.getParameter("title"));
    	return true;
    }
    
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,ModelAndView mv) throws Exception {
		//系统管理相关模块不在此记日志
    	//模块，操作数据库中没有配置的不记日志
    	log.debug("module=["+module+"] operate=["+operate+"]");
    	if(moduleMap.get(module) == null) return ;
    	if(operateMap.get(operate) == null) return ;
    	
		if(!module.equals("login.do")&&
				!module.equals("operateAudit.do")&&
				!module.equals("user.do")&&
				!module.equals("role.do")&&
				!module.equals("vendor.do")&&
				!module.equals("logAudit.do")&&
				!module.equals("param.do")&&
				!module.equals("type.do")&&
				!module.equals("vendor.do")&&
				!module.equals("devcatalog.do")&&
				!module.equals("modcatalog.do")&&
				!module.equals("org.do")&&
				!module.equals("devservicecompany.do")&&
				!module.equals("devserviceperson.do")&&
				!module.equals("devbaseinfo.do")&&
				!module.equals("devadmin.do")&&
				!module.equals("devresponsor.do")&&
				!module.equals("notifymodel.do")&&
				!module.equals("projecttype.do")&&
				!module.equals("staticPages.do")&&
				!module.equals("register.do")				
				){
			if(operate.indexOf("add")>=0 ||
					operate.indexOf("mod")>=0 ||
					operate.indexOf("del")>=0 ||
					operate.indexOf("save")>=0 ||
					operate.indexOf("update")>=0 ||
					operate.indexOf("Create")>=0 ||
					operate.indexOf("Process")>=0 ||
					operate.indexOf("register")>=0){
				if(mv.getModelMap().get("message")==null){
					this.getLogAuditService().writeLog(logAudit);
				}else{
					String retMessage = mv.getModelMap().get("message").toString();
					if(retMessage.indexOf("成功")>=0){
						this.getLogAuditService().writeLog(logAudit);	
					}	
				}
					
			}	
		}
    	
    }
}
