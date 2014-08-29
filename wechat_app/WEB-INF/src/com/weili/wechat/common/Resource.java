package com.weili.wechat.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.PropertyResourceBundle;



/** 
* 语言包文件加载

* @author:ykliu 

* @version 1.0 

*/ 



public class Resource { 
	private static final String HEAD_FILE = "ATTR";   
	private static final String LAST_FILE=".properties";   
	private static final String FILE_PATH="WEB-INF/classes/";   

	
	private PropertyResourceBundle pr;
	private String local;
	public String Getlocal(){
		return this.local;
	}
	public Resource(String local) {  
		try{
			this.local = local; 
			if(local==null || local.equals("")){
				local = "zh_CN";
			}
			String baseName = new StringBuffer() 
			.append(HEAD_FILE).append("_").append(local) 
			.append(LAST_FILE).toString();   
		
			String fileName =new StringBuffer(this.getfilePath()).append(FILE_PATH).append(baseName).toString(); 

			InputStream is = new FileInputStream(fileName);  
			
			pr = new PropertyResourceBundle(is); 
			is.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	
	

	public synchronized String srcStr(String disStr) { 
		if(disStr==null||disStr.equals(""))
		{
			return "";
		}
		String ret = ""; 
		try { 
			ret = pr.getString(disStr); 
			if(local.equals("zh_CN")){ 
				ret = new String(ret.getBytes("ISO-8859-1"),"GBK"); 
			}   
			else if(local.equals("zh_TW")) {
				ret = new String(ret.getBytes("ISO-8859-1"), "GB18030");
			}
			return ret; 
		}
		catch (Exception e) { 
//			System.out.println("disStr="+disStr);
			//e.printStackTrace(); 
			return disStr; 
		}
	}
	
	//多个key形式：key|key|key，先分割再转换拼接。
	public synchronized String srcMoreStr(String disStr) { 
		if(disStr==null||disStr.equals(""))
		{
			return "";
		}
		StringBuffer resultStr=new StringBuffer();
		String[] Str=disStr.split("\\|", 0);	
		for(int i=0;i<Str.length;i++){
			resultStr.append(srcStr(Str[i]));
		}
		return resultStr.toString();
	}
	
	
	private String getfilePath(){ 
		try {
			String fileName[] = this.getClass().getResource("").toString().split("/");
			String file = "";
			String filePath= new String();
			for (int i = 0; i < fileName.length-6; i++) {
				file = fileName[i].replace("%20", " ");
//				log.debug("file="+file);
				if (file != null){
					filePath=filePath+file+System.getProperty("file.separator");
				}
			}	
			return filePath.substring(5);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
} 