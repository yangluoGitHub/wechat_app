package com.weili.wechat.common.IdentityAuthentication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

public class Identifier implements InitializingBean {
	public String[] authorizedIp;

	private static Log log = LogFactory.getLog(Identifier.class);
	
	public String[] getAuthorizedIp() {
		return authorizedIp;
	}

	public void setAuthorizedIp(String[] authorizedIp) {
		this.authorizedIp = authorizedIp;
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		log.debug("afterPropertiesSet");
	}

	public void init() throws Exception{
		log.debug("init Identifier");
		for(String authorizedIp:this.authorizedIp)
		{
			log.debug("authorizedIp=["+authorizedIp+"]");
		}
	}
}
