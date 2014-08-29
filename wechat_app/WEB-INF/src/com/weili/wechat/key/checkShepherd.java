package com.weili.wechat.key;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import com.weili.wechat.common.CalendarUtil;

public class checkShepherd {

	public static Integer checkTimes(StringBuffer result,String file) throws Exception{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		fis.close();
		
		String load_time=null;
		String last_time=null;
		String times=null;
		try {
			load_time=ProxoolUtil.dncryptData(prop.getProperty("load_time"));
			last_time=ProxoolUtil.dncryptData(prop.getProperty("last_time"));
			times=ProxoolUtil.dncryptData(prop.getProperty("times"));
			//System.out.println("load_time="+load_time);
			//System.out.println("last_time="+last_time);
			//System.out.println("times="+times);
		} catch (Exception e) {
			// TODO: handle exception
			result.append("系统参数被修改，无法登录！");
			return 0;
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			 Date date1 = df.parse(load_time); 
			 //Date date2 = df.parse(last_time); 
		} catch (Exception e) {
			// TODO: handle exception
			result.append("系统参数被修改，无法登录！");
			return 0;
		}
		
		try {
			 Integer i=Integer.valueOf(times);
		} catch (Exception e) {
			// TODO: handle exception
			result.append("系统参数被修改，无法登录！");
			return 0;
		}
		
		//第一次登陆
		if (load_time.equals("1900-01-01")){
			load_time=CalendarUtil.getSysTimeYMDHMS();
			prop.setProperty("load_time", ProxoolUtil.encryptData(load_time));
			prop.setProperty("first_time", load_time);
			if(!times.equals("0")){
				last_time=CalendarUtil.getFixedDate(CalendarUtil.getSysTimeYMD(), Integer.valueOf(times));
				prop.setProperty("last_time", ProxoolUtil.encryptData(last_time));
				result.append("试用期还有"+times+"天结束！");
			}else{
				if (getDifOfDay(last_time,load_time)==-1){
					result.append("系统参数设置错误，无法登录！");
				}else{
					result.append("试用期还有"+getDifOfDay(last_time,load_time)+"天结束！");
				}
			}
			FileOutputStream fos = new FileOutputStream(file);
			prop.store(fos, "#config#");
			fos.close();
			return 1;
		}
		
		//判断系统时间与上次登录时间比较
		long diftime = System.currentTimeMillis() - CalendarUtil.str2Date(load_time).getTime();
		
		if (diftime<=0){
			result.append("系统时间被修改，无法登录！");
			return 0;
		}else{
			//判断即止日期与当前登录日期比较
			if(getDifOfDay(CalendarUtil.getSysTimeYMD(),last_time)>=0){
				result.append("试用期结束，无法登录！");
				return -1;
			}else{
				//替换登录时间
				load_time=CalendarUtil.getSysTimeYMDHMS();
				prop.setProperty("load_time", ProxoolUtil.encryptData(load_time));
				FileOutputStream fos = new FileOutputStream(file);
				prop.store(fos, "#config#");
				fos.close();
				result.append("试用期还有"+getDifOfDay(last_time,load_time)+"天结束！");
				return 1;
			}
		}
	}
	
	/**
	 * 获取时间差
	 * @return 时间差，不足一天算一天
	 * */
	private static long getDifOfDay(String time1,String time2)
	{				
		long diftime = CalendarUtil.str2Date(time1).getTime() - CalendarUtil.str2Date(time2).getTime();
		String mark="";
		if(diftime<0)
		{
			mark="-";
			diftime = Math.abs(diftime);
			return -1;
		}
		long days = (long)Math.floor(diftime/(1000*3600*24));
		long hours = (long)Math.floor((diftime - days*1000*3600*24)/(1000*3600));
		long minitues = (long)Math.floor((diftime - days*1000*3600*24 - hours*1000*3600)/(1000*60));
		if (hours>0||minitues>0){
			days++;
		}
		return days;
	}
	
	public static void main(String[] args) throws Exception{
		StringBuffer result=new StringBuffer();
		checkTimes(result,"c:\\test.txt");
	}
}
