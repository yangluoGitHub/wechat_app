//package com.weili.wechat.common;
//
//import java.io.FileNotFoundException;
//
//import sun.net.ftp.FtpClient;
//
//public class BdeFtpClient  extends FtpClient{
//	
//	BdeFtpClient()
//	{ 
//        super(); 
//    }
//	/**
//	 * 在ftp服务器上创建文件夹
//	 * @param dirName 文件夹具体路径
//	 * */
//    public void mkDir(String dirName) throws Exception
//    {
//        String cmd = "MKD " + dirName;
//        try 
//        { 
//        	this.issueCommandCheck(cmd);
//        } 
//        catch(FileNotFoundException e) 
//        {
//        	throw new FileNotFoundException(e.toString());
//        }
//        catch(Exception e)
//        {
//        	throw new Exception(e.toString());
//        }
//    } 		
//}
