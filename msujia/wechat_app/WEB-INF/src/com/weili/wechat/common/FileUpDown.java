package com.weili.wechat.common;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 文件上传下载类

 * @author hsxu
 * @since 2008.06.10
 */

public final class FileUpDown 
{

	private static Log log=LogFactory.getLog(FileUpDown .class);	
	
	/**
	 * 上传文件
	 * @param  filePath   初始文件路径
	 * @param  charSet    文件名编码方式
	 * @param  childPath  子文件夹名
	 * @param  number     上传文件数量
	 * @note  fileName   请求的页面上的DOM对象路径(请用"Accessory"+i表示)
	 */
	
	public static String upLoadFile(HttpServletRequest request,String charSet,String filePath,String number,String childPathName) throws Exception
	{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String accessory="";
		
		Tool.createFolder(filePath);
		filePath=filePath+System.getProperty("file.separator")+childPathName+System.getProperty("file.separator");
		Tool.createFolder(filePath);
		
		for (int i=1;i<=Integer.valueOf(number);i++)
		{
   			String filename="Accessory"+i;
   			log.debug("filename->"+filename);
   			log.debug("filenamepath->"+request.getParameter(filename));
   			String oldfile=request.getParameter(filename);
   			if (oldfile!=null && oldfile!="")
   			{
   				accessory=accessory+oldfile+"|";
   			}
   			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(filename);
			if ( file != null&&!"".equals(file.getOriginalFilename()) )
   			{
   	    		accessory=accessory+file.getOriginalFilename()+"|";
   	    		String fullpath = filePath+file.getOriginalFilename();
   	    		fullpath = Tool.fileEncoding(fullpath,charSet);
   	    		File upFile = new File(fullpath);
   	    		file.transferTo(upFile);     
   			}
		}
        return accessory;
	}
	
	/**
	 * 上传文件
	 * @param  filePath   初始文件路径
	 * @param  charSet    文件名编码方式
	 * @param  childPath  子文件夹名
	 * @param  fileName   请求的页面上的DOM对象路径
	 */
	
	public static boolean downLoadFile(HttpServletRequest request,HttpServletResponse response,String charSet,String filePath,String childPathName,String fileName) throws Exception
	{
		filePath=filePath+System.getProperty("file.separator")+childPathName+System.getProperty("file.separator")+fileName;		
		log.debug("filenamedownload=["+filePath+"]");
		File file = new File(Tool.fileEncoding(filePath,charSet));
		if(!file.exists()){   	    
    		return false;
		}    		
		response.reset();
		response.setContentType("application/x-download;charset=GBK");//设置为下载application/x-download   
		fileName = new String(fileName.getBytes("GBK"),"iso8859-1");
		response.addHeader("Content-Disposition","attachment;filename=" + fileName);
		response.addHeader("Content-Length", String.valueOf(file.length()));
		
		OutputStream output = null;
		FileInputStream fis = null;
		try
		{
			output  = response.getOutputStream();
			fis = new FileInputStream(Tool.fileEncoding(filePath,charSet));

			byte[] b = new byte[1024];
			int i = 0;

			while((i = fis.read(b)) > 0)
			{
				output.write(b, 0, i);
			}
			output.flush();
		}
		catch(Exception e)
		{
			//System.out.println("Error!");
			e.printStackTrace();
		}
		finally
		{
			if(fis != null)
			{
				fis.close();
				fis = null;
			}
			if(output != null)
			{
				output.close();
				output = null;
			}
		}
		return true;
	}

}
	