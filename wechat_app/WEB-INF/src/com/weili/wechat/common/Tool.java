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
 * �������һЩ���õķ���   
 * 
 * @author hsxu
 * @since 2008.4.18
 */


public class Tool {
	

	private static Log log = LogFactory.getLog(Tool.class);
    
	
	/**
	 * ����ת�뷽��
	 * 
	 * @param str - Ҫת��������ַ���
	 * @return ת���������ַ���
	 */
	
	public static String toGBK(String str){

		try{			
			str = (str == null) ? "" : new String(str.getBytes("ISO-8859-1"),"ZHS16GBK");
		}
		catch(UnsupportedEncodingException e){
			log.info("Tool::toGBK(String)����ʱ����"+e);
		}					
		return str;
	}
	
	/**
	 * �ļ�������
	 * 
	 * @param str - Ҫ������ַ���
	 * @return �������ַ���
	 * @throws UnsupportedEncodingException 
	 */
	
	public static String fileEncoding(String str,String charSet){
		try{
			str = new String(str.getBytes("GBK"),charSet);	
		}catch(UnsupportedEncodingException e){
			log.info("Tool::fileEncoding(String)����ʱ����"+e);
		}
						
		return str;
	}
	
	/**
	 * �����ַ�������
	 * 
	 * @param str - Ҫ���͵��ַ���
	 * @return strΪnull����""�����򷵻�str��ȥǰ��ո�֮���ֵ
	 */
	
	
	public static String parseString(String str){
		return str == null ? "" : str.trim();
	}
	
	/**
	 * ��ȡϵͳʱ�䷽��(ʱ���֣���)
	 * 
	 * @return ����ϵͳ��ǰʱ���ַ������ַ�����ʽΪ��yyyy-mm-dd hh:mm:ss
	 */
	
	public static String getSysTimeYMDHMS(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 * ��ȡϵͳʱ�䷽��(ʱ���֣���)
	 * 
	 * @return ����ϵͳ��ǰʱ���ַ������ַ�����ʽΪ��yyyy-mm-dd hh:mm:ss
	 */
	
	public static String getSysTimeHMS(){
		return new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}
	/**
	 * ��ȡϵͳʱ�䷽��(ʱ����)
	 * @return
	 */
	public static String getSysTimeYMDHM(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 * ��ȡϵͳʱ�䷽��
	 * 
	 * @return ����ϵͳ��ǰʱ���ַ������ַ�����ʽΪ��yyyy-mm-dd
	 */
	
	public static String getSysTimeYMD(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
	}
	

	/**
	 * MD5�����㷨
	 * @param s - Ҫ���ܵ��ַ���
	 * @return ���ܹ�����ַ���
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
	
//	�����ļ���
//	param folderPath �ļ�����������·��

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
	
	
	
	
//	ɾ���ļ���
//	param folderPath �ļ�����������·��

	public static void delFolder(String folderPath) {
	     try {
	        delAllFile(folderPath); //ɾ����������������
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        java.io.File myFilePath = new java.io.File(filePath);
	        myFilePath.delete(); //ɾ�����ļ���
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}

//	ɾ��ָ���ļ����������ļ�
//	param path �ļ�����������·��
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
             delAllFile(path + "/" + tempList[i]);//��ɾ���ļ���������ļ�
             delFolder(path + "/" + tempList[i]);//��ɾ�����ļ���
             flag = true;
          }
       }
       return flag;
     }
		
	//���ڸ�ʽ�� 
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
				String time1=t.nextToken();//��ȡ���ڴ�
//				String time2=t.nextToken();//��ȡʱ�䴮
				StringTokenizer t1 = new StringTokenizer(time1,"-");
				String year=t1.nextToken();//��ȡ���
				if(year.length()==2){
					year="20"+year;
				}
				String month=t1.nextToken();//��ȡ�·�
				if(month.length()==1){
					month="0"+month;
				}
				String day=t1.nextToken();//��ȡ����
				if(day.length()==1){
					day="0"+day;
				}
				date=year+month+day;
//				StringTokenizer t2 = new StringTokenizer(time2,"-");
//				String hour=t2.nextToken();//��ȡСʱ
//				String minute=t2.nextToken();//��ȡ����
//				String second=t2.nextToken();//��ȡ����
//				
//				String time=hour+minute+second;
			}else if(len==6){
				date="20"+date;
			}
			return date;
			
		}
		
		/**
		 * ������ת��ΪXML
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
		 * ����request�����Ȩ�Ļ��������SQL����in�ĺ�׺����
		 * <br>��ʽΪ('A','B',...)
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
		 * ����request�����Ȩ�Ļ��������list
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
