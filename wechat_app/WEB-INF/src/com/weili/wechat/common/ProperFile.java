package com.weili.wechat.common;

import java.io.*;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.weili.wechat.service.TcpMessageQueue;

public class ProperFile
{
    private Properties info;
    private File nf;
    private InputStream in;
    private static Log log = LogFactory.getLog(ProperFile.class);
    
    public ProperFile(String fStr)
    {
        info = new Properties();
        in = null;
        in = getClass().getResourceAsStream(fStr);
        try
        {
            info.load(in);
        }
        catch(Exception e)
        {
            log.error("init ProperFile error "+e.getMessage());
        }
    }

    public void setProper(String PName, String PValue, String PStr)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(nf);
            info.setProperty(PName, PValue);
            info.store(fos, PStr);
            fos.close();
        }
        catch(Exception e)
        {
        	log.error(e.getMessage());
        }
    }

    public String getProper(String pName)
    {
        String reStr = "";
        reStr = info.getProperty(pName)==null?"":info.getProperty(pName);
        return reStr;
    }

    public void close()
    {
        try
        {
            if(in != null)
                in.close();
        }
        catch(Exception e)
        {
        	log.error(e.getMessage());
        }
    }

    public static void main(String args[])
    {

    }
}
