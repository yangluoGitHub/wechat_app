package com.weili.wechat.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StreamTokenizer;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Iterator;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.ScrollableResults;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import com.weili.wechat.web.system.UserController;



/**
 * 此类包含一些常用的方法   
 * 
 * @author hsxu
 * @since 2008.4.18
 */


public class Tool {
	

	private static Log log = LogFactory.getLog(Tool.class);
    
	
	/**
	 * 中文转码方法
	 * 
	 * @param str - 要转换编码的字符串
	 * @return 转换编码后的字符串
	 */
	
	public static String toGBK(String str){

		try{			
			str = (str == null) ? "" : new String(str.getBytes("ISO-8859-1"),"ZHS16GBK");
		}
		catch(UnsupportedEncodingException e){
			log.info("Tool::toGBK(String)运行时出错："+e);
		}					
		return str;
	}
	
	/**
	 * 文件名编码
	 * 
	 * @param str - 要编码的字符串
	 * @return 编码后的字符串
	 * @throws UnsupportedEncodingException 
	 */
	
	public static String fileEncoding(String str,String charSet){
		try{
			str = new String(str.getBytes("GBK"),charSet);	
		}catch(UnsupportedEncodingException e){
			log.info("Tool::fileEncoding(String)运行时出错："+e);
		}
						
		return str;
	}
	
	/**
	 * 解释字符串方法
	 * 
	 * @param str - 要解释的字符串
	 * @return str为null返回""；否则返回str除去前后空格之后的值
	 */
	
	
	public static String parseString(String str){
		return str == null ? "" : str.trim();
	}
	
	/**
	 * 获取系统时间方法(时：分：秒)
	 * 
	 * @return 返回系统当前时间字符串，字符串格式为：yyyy-mm-dd hh:mm:ss
	 */
	
	public static String getSysTimeYMDHMS(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 * 获取系统时间方法(时：分：秒)
	 * 
	 * @return 返回系统当前时间字符串，字符串格式为：yyyy-mm-dd hh:mm:ss
	 */
	
	public static String getSysTimeHMS(){
		return new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}
	/**
	 * 获取系统时间方法(时：分)
	 * @return
	 */
	public static String getSysTimeYMDHM(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 * 获取系统时间方法
	 * 
	 * @return 返回系统当前时间字符串，字符串格式为：yyyy-mm-dd
	 */
	
	public static String getSysTimeYMD(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
	}
	

	/**
	 * MD5加密算法
	 * @param s - 要加密的字符串
	 * @return 加密过后的字符串
	 */
	
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
//	创建文件夹
//	param folderPath 文件夹完整绝对路径

	public static void createFolder(String folderPath) {
	     try {
	    	java.io.File myFilePath=new java.io.File(folderPath);
    		if(!myFilePath.exists())
    		{	
    			myFilePath.mkdirs();
    		}
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}
	
	
	
	
//	删除文件夹
//	param folderPath 文件夹完整绝对路径

	public static void delFolder(String folderPath) {
	     try {
	        delAllFile(folderPath); //删除完里面所有内容
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        java.io.File myFilePath = new java.io.File(filePath);
	        myFilePath.delete(); //删除空文件夹
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}

//	删除指定文件夹下所有文件
//	param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
       boolean flag = false;
       File file = new File(path);
       if (!file.exists()) {
         return flag;
       }
       if (!file.isDirectory()) {
         return flag;
       }
       String[] tempList = file.list();
       File temp = null;
       for (int i = 0; i < tempList.length; i++) {
          if (path.endsWith(File.separator)) {
             temp = new File(path + tempList[i]);
          } else {
              temp = new File(path + File.separator + tempList[i]);
          }
          if (temp.isFile()) {
             temp.delete();
          }
          if (temp.isDirectory()) {
             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
             delFolder(path + "/" + tempList[i]);//再删除空文件夹
             flag = true;
          }
       }
       return flag;
     }
		
	//日期格式化 
		public static String formatDate(String date){
			if(date==null)
			{
				date="";
			}			
			int len=date.length();			
			if(len==8){
				return date;
			}else if(len>8){
				date=date.substring(0, 9);
				date=date.replaceAll("/", "-");
				date=date.replaceAll(":", "-");
				StringTokenizer t = new StringTokenizer(date," ");
				String time1=t.nextToken();//获取日期串
//				String time2=t.nextToken();//获取时间串
				StringTokenizer t1 = new StringTokenizer(time1,"-");
				String year=t1.nextToken();//获取年份
				if(year.length()==2){
					year="20"+year;
				}
				String month=t1.nextToken();//获取月份
				if(month.length()==1){
					month="0"+month;
				}
				String day=t1.nextToken();//获取日期
				if(day.length()==1){
					day="0"+day;
				}
				date=year+month+day;
//				StringTokenizer t2 = new StringTokenizer(time2,"-");
//				String hour=t2.nextToken();//获取小时
//				String minute=t2.nextToken();//获取分钟
//				String second=t2.nextToken();//获取秒数
//				
//				String time=hour+minute+second;
			}else if(len==6){
				date="20"+date;
			}
			return date;
			
		}
		
		/**
		 * 将对象转化为XML
		 * @param obj
		 * @return
		 */
		public static String parseObjectToXML(Object obj) {
			if(obj == null)
				return "";
			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, obj.getClass());
			return xstream.toXML(obj);
		}
		
		/**
		 * 根据request获得授权的机构并组成SQL语句的in的后缀条件
		 * <br>格式为('A','B',...)
		 * @param request
		 */
		public static String getAuthOrg(HttpServletRequest request)
		{
			String authOrg = "";
			TreeMap map1 = (TreeMap) request.getSession().getAttribute("_orgTreeMap");
			Iterator it1 = map1.keySet().iterator();
			while (it1.hasNext())
			{
				Object o = it1.next();
				HashMap tmp = (HashMap) map1.get(o);
				authOrg += "'" + tmp.get("no") + "',";
			}
			authOrg = authOrg.equals("") ? "" : "(" + authOrg.substring(0, authOrg.length() - 1) + ")";
			return authOrg;
		}
		
		/**
		 * 根据request获得授权的机构并组成list
		 * @param request
		 */
		public static ArrayList<String> getAuthOrgList(HttpServletRequest request)
		{
			ArrayList<String> authOrgList = new ArrayList<String>();
			TreeMap map1 = (TreeMap) request.getSession().getAttribute("_orgTreeMap");
			Iterator it1 = map1.keySet().iterator();
			while (it1.hasNext())
			{
				Object o = it1.next();
				HashMap tmp = (HashMap) map1.get(o);
				authOrgList.add((String)tmp.get("no"));
			}
			return authOrgList;
		}
		
		
}
