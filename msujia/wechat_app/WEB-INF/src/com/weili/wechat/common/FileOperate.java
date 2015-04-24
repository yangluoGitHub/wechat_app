package com.weili.wechat.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.weili.wechat.vo.LogFile;

public class FileOperate {
	
   public FileOperate() 
   {
	   
   }

   /**
    * 获取文件夹里面的所有文件和目录信息
    * 
    * @author hjtang
    * @param path 文件夹路径 
    * @return List<LogFile>
    */
   
   public final static List<LogFile> getDirFiles(String path) 
   {
     File file = new File(path);
    
     if (!file.isDirectory()) 
     {
       return null;
     }
     
     String[] tempList = file.list();
     List<LogFile> logFileList = new ArrayList<LogFile>();
     
     LogFile logFile = null;
     
     for (int i = 0; i < tempList.length; i++) 
     {
    	 File tempfile = new File(path+File.separator+tempList[i]);
    	 logFile = new LogFile();
    	 
    	 logFile.setFileName(tempList[i]);
    	 logFile.setIsFile("0");
    	 logFile.setIsDir("0");
		 logFile.setFileSize("0");

    	 if(tempfile.isFile()) {
    		 logFile.setIsFile("1");
    		 logFile.setFileSize(String.valueOf(tempfile.length()/1024));
    		 logFile.setFilePath(tempfile.getAbsolutePath());
    		 logFile.setLogDate(logFile.getFileName().substring(logFile.getFileName().indexOf("_")+1, logFile.getFileName().indexOf(".")));
         }
         else if(tempfile.isDirectory()) 
         {
        	 logFile.setIsDir("1");
         }
       
    	 logFileList.add(logFile);
     }
     return logFileList;
   }
}