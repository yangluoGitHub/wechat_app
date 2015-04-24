package com.weili.wechat.common;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * AuthInterceptor
 * URL����Ȩ�޿���
 * @author zhangyong
 * @since 2007.05.28
 */

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static Log log = LogFactory.getLog(AuthInterceptor.class);

	public AuthInterceptor() {

    }

	
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        
    	UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");
    	//System.out.println("����ǰ");
    	//System.out.println("URL����Ȩ�޿��� ----AuthInterceptor ");
        String url = request.getServletPath();
        String queryString = request.getQueryString();
        String tmp1[] = url.split("/");
        String tmp2[] = queryString.split("&");
        String action = tmp1[tmp1.length-1]+"?"+tmp2[0];
        
        if (tmp2.length>1 && ( tmp2[1].contains("orgType_Query") || tmp2[1].contains("roleNo_Query") ) ){
        	action=action+"&"+tmp2[1];
        }
        
        if(userSession != null){
            int roleId = userSession.getRoleNo();
            String roleName = userSession.getRoleName();	
            //System.out.println("��ɫ��Ϣ  roleId=["+roleId+"] roleName=["+roleName+"]");
//            if(roleId==10001) {
//            	if(action.equals("user.do?action=del") || action.equals("org.do?action=del&orgType_Query=1") || action.equals("role.do?action=del")){
//            		if(bankVersion!=null&&bankVersion.equalsIgnoreCase("BH")){ // ������˴���
//            			response.sendRedirect("autodelback.jsp");
//            		}
//            	}
//            	return true;
//            }
            
            Set defButton = (Set)request.getSession().getAttribute("_Def_Button");
            
            if(!defButton.contains(action)) return true;
            
            Set authButton = (Set)request.getSession().getAttribute("_Auth_Button");

            if(!authButton.contains(action)){
            	//System.out.println("������û����Ӳ�����Ȩ�ޣ�");
//            	if(action.equals("user.do?action=del") || action.equals("org.do?action=del&orgType_Query=1") || action.equals("role.do?action=del")){
//            		//response.sendRedirect("autodelback.jsp");
//            	}else{
//            		response.sendRedirect("autoback.jsp");
//            	}
            	response.sendRedirect("autoback.jsp");
            	return false;
            }
        }
        //System.out.println("����������Ӳ�����Ȩ�ޣ�");
        return true;	
    }
}
