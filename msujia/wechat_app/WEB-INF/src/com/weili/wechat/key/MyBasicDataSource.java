package com.weili.wechat.key;

import org.apache.commons.dbcp.BasicDataSource;

public class MyBasicDataSource extends BasicDataSource {

	    public MyBasicDataSource() {   
	        super();   
	    } 
	    @Override
	    public synchronized void setUsername(String username){   
	        String user=ProxoolUtil.dncryptData(username);   
	        super.setUsername(user);
	    }   
	    @Override
	    public synchronized void setPassword(String password){   
	        String pass=ProxoolUtil.dncryptData(password);   
	        super.setPassword(pass);
	    }   
	           
}
