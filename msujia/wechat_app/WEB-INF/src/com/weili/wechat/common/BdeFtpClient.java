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
//	 * ��ftp�������ϴ����ļ���
//	 * @param dirName �ļ��о���·��
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
