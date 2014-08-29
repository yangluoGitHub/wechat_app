package com.weili.wechat.key;
import java.util.Properties;   
import org.hibernate.HibernateException;   
import org.hibernate.cfg.Environment;   
import org.hibernate.connection.DriverManagerConnectionProvider;   

  
public class CustomDriverManagerConnectionProvider extends  
        DriverManagerConnectionProvider {   
  
    public CustomDriverManagerConnectionProvider() {   
        super();   
    }   
    @Override     
    public void configure(Properties props) throws HibernateException{   
        String user = props.getProperty(Environment.USER);   
        String password = props.getProperty(Environment.PASS);   
        props.setProperty(Environment.USER, ProxoolUtil.dncryptData(user));   
        props.setProperty(Environment.PASS, ProxoolUtil.dncryptData(password));   
        super.configure(props);   
    }   
       
}  

