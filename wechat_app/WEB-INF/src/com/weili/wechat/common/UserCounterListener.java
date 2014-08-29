package com.weili.wechat.common;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.web.login.SessionController;


/**
 * UserCounterListener class used to count the current number
 * of active users for the applications.  Does this by counting
 * how many user objects are stuffed into the session.  It Also grabs
 * these users and exposes them in the servlet context.
 *
 * @author 
 *
 * @web.listener
 */
public class UserCounterListener implements ServletContextListener,
                                            HttpSessionAttributeListener, HttpSessionListener {
    public static final String COUNT_KEY = "UserOnLineNum";
    private static Log log = LogFactory.getLog(UserCounterListener.class);
    private static ServletContext servletContext;
    private int counter;
    private HashMap users;

    public synchronized void contextInitialized(ServletContextEvent sce) {
    	log.debug("-------------contextInitialized-------------------");
        servletContext = sce.getServletContext();
        servletContext.setAttribute((COUNT_KEY), Integer.toString(counter));
    }

    public synchronized void contextDestroyed(ServletContextEvent event) {
        servletContext = null;
        users = null;
        counter = 0;
    }

    synchronized void incrementUserCounter() {
        counter = Integer.parseInt((String) servletContext.getAttribute(COUNT_KEY));
        counter++;
        servletContext.setAttribute(COUNT_KEY, Integer.toString(counter));

//        if (log.isDebugEnabled()) {
//            log.debug("User Count: " + counter);
//        }
        log.info("User Count: " + counter);
    }

    synchronized void decrementUserCounter() {
        int counter = Integer.parseInt((String) servletContext.getAttribute(COUNT_KEY));
        counter--;

        if (counter < 0) {
            counter = 0;
        }

        servletContext.setAttribute(COUNT_KEY, Integer.toString(counter));

//        if (log.isDebugEnabled()) {
//            log.debug("User Count: " + counter);
//        }
        log.info("User Count: " + counter);
    }

    /**
    * This method is designed to catch when user's login and record their name
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event) {
//    	log.info("----------attributeAdded ------------------");
        if (event.getName().equals("userSession")) {
        	incrementUserCounter();
        }
    }

    /**
    * When user's logout, remove their name from the hashMap
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event) {
//    	log.info("----------attributeRemoved ------------------");
        if (event.getName().equals( "userSession")) {
        	UserSession user_Client = (UserSession)event.getValue();
        	if(user_Client == null) {
        		return;
        	}
        	HttpSession session = SessionController.queSession(user_Client.getAccount());
        	if(session != null)
        	{
        		try
        		{
        			if((((String)session.getAttribute("IP_Available")).equals(user_Client.getLoginIp_curr())) && (((String)session.getAttribute("Time_Available")).equals(user_Client.getLoginTime_curr())))
        				SessionController.removeSession(user_Client.getAccount());
        		}
        		catch(IllegalStateException e)
        		{
        			SessionController.removeSession(user_Client.getAccount());
        		}
        	}
        	decrementUserCounter();
        }
    }

    /**
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event) {
        // I don't really care if the user changes their information
    }
    
    public static int getOnlineUserCount(){
    	log.debug("-------getOnlineUserCount----------");
    	Object o = servletContext.getAttribute(COUNT_KEY);
    	if(o == null){
    		log.debug("servletContext.getAttribute(COUNT_KEY) is NUll");
    	}else{
    		log.debug("servletContext.getAttribute(COUNT_KEY) = ["+servletContext.getAttribute(COUNT_KEY)+"]");
    	}
    	return Integer.parseInt((String) servletContext.getAttribute(COUNT_KEY));
    	
    }
    
    public int getTest(){
    	return 500;
    }
    
    public void sessionCreated(HttpSessionEvent arg0) {
//		log.info("session created -------------");
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
//		log.info("session destroyed -----------");
		if(arg0.getSession().getAttribute("userSession") != null) {
			arg0.getSession().removeAttribute("userSession");
		}
	}
}
