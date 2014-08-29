package com.weili.wechat.common.IdentityAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	private static Log log = LogFactory.getLog(AuthenticationInterceptor.class);
	public Identifier identifier;
	public AuthenticationInterceptor() {

    }

	 public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
		 boolean isAuthorizedIp = false;
		 for(String authorizedIp:identifier.getAuthorizedIp())
		 {
			 log.debug(authorizedIp);
			 if(request.getRemoteAddr() == null)
			 {
				 break;
			 }

			 if(request.getRemoteAddr().equals(authorizedIp))
			 {
				 isAuthorizedIp = true;
				 break;
			 }
		 }
		 if(!isAuthorizedIp)
		 {
			 log.error("IP=["+request.getRemoteAddr()+"]不是合法地址");
		 }
		 return isAuthorizedIp;
	 }

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}
}
