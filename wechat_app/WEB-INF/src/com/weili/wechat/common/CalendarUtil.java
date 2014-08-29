package com.weili.wechat.common;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author shp
 * @since 2005.12.12
 */

public final class CalendarUtil {

	private static Log log = LogFactory.getLog(CalendarUtil.class);

	/**
	 * 获取系统时间
	 * 
	 * @return 返回系统当前时间字符串，字符串格式为：yyyy-mm-dd hh:mm:ss
	 */

	public static String getSysTimeYMDHMS() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 获取系统时间
	 * 
	 * @return 返回系统当前时间字符串，字符串格式为：MM-dd HH:mm:ss SSS case_trace使用
	 */
	public static String getSysTimeYMDHMS11() {
		return new SimpleDateFormat("MM-dd HH:mm:ss SSS").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 获取系统时间
	 * 
	 * @return 返回系统当前时间字符串，字符串格式为：MM-dd HH:mm:ss SSS case_trace使用
	 */
	public static String getSysTimeYMDHMS111() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 获取系统时间
	 * 
	 * @return 返回系统当前时间字符串，字符串格式为：yyyyMMddHHmmss
	 */

	public static String getSysTimeYMDHMS1() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 获取系统时间<br>
	 * 
	 * @return 返回系统当前时间字符串，字符串格式为：yyyyMMddHHmmssSSS
	 */

	public static String getSysTimeYMDHMS2() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 获取系统时间
	 * 
	 * @return 返回系统当前时间字符串，字符串格式为：yyyy-mm-dd hh:mm
	 */

	public static String getSysTimeYMDHM() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 获取系统时间
	 * 
	 * @return 返回系统当前时间字符串，字符串格式为：yyyy-mm-dd
	 */

	public static String getSysTimeYMD() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 获取系统时间
	 * 
	 * @return 返回系统当前时间字符串，字符串格式为：yyyy-mm
	 */

	public static String getSysTimeYM() {
		return new SimpleDateFormat("yyyy-MM").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 获取系统时间(季度)
	 * 
	 * @return 以字符串的形式返回系统当前时间(季度)
	 */
	public static String getSysTimeQuarter() {
		String currentMonth = new SimpleDateFormat("yyyy-MM").format(new Date(System.currentTimeMillis())).substring(5, 7);
		String currentQuarter;
		switch (Integer.parseInt(currentMonth)) {
		case 1:
		case 2:
		case 3:
			currentQuarter = "1";
			break;
		case 4:
		case 5:
		case 6:
			currentQuarter = "2";
			break;
		case 7:
		case 8:
		case 9:
			currentQuarter = "3";
			break;
		case 10:
		case 11:
		case 12:
			currentQuarter = "4";
			break;
		default:
			currentQuarter = "";
			break;
		}
		return currentQuarter;
	}

	/**
	 * 获取系统时间
	 * 
	 * @return 返回系统当前时间字符串，字符串格式为：hh:mm:ss
	 */

	public static String getSysTimeHMS() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 取系统时间hhmmss
	 * 
	 * @return String--hhmmss格式的时间字符串
	 */

	public static String getTime() {
		return new SimpleDateFormat("HHmmss").format(new Date().getTime());
	}

	/**
	 * 取系统日期yyyymmdd
	 * 
	 * @return String--yyyymmdd格式的日期字符串
	 */

	public static String getDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date().getTime());
	}

	/**
	 * 根据传入的时间转为YYYYMMDDHHMMSS格式的字符串
	 * 
	 * @param time
	 *            代表时间的长整数
	 * @return 代表时间的字符串
	 */

	public static String getDateTime(long time) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(time);
	}

	/**
	 * 根据传入的时间转为YYYY-MM-DD HH:MM:SS格式的字符串
	 * 
	 * @param time
	 *            代表时间的长整数
	 * @return 代表时间的字符串
	 */
	public static String getYMDHMS(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		int yearFile = cal.get(Calendar.YEAR);
		int monthFile = cal.get(Calendar.MONTH) + 1;
		int dayFile = cal.get(Calendar.DATE);
		int hourFile = cal.get(Calendar.HOUR_OF_DAY);
		int minuteFile = cal.get(Calendar.MINUTE);
		int secondFile = cal.get(Calendar.SECOND);
		String ss = String.format("%04d", yearFile) + "-" + String.format("%02d", monthFile) + "-" + String.format("%02d", dayFile) + " "
				+ String.format("%02d", hourFile) + ":" + String.format("%02d", minuteFile) + ":" + String.format("%02d", secondFile);
		return ss;
	}

	/**
	 * 将字符型yyyy-MM-DD转成Date类型日期
	 * 
	 * @since 2006.01.19
	 * @param stringdate
	 *            以yyyyMMdd表示的日期字符串
	 * @return date类型日期
	 */
	public static Date str2Date(String stringdate) {
		if (stringdate == null)
			return null;
		SimpleDateFormat format = null;
		if (stringdate != null && stringdate.length() < 8)
			return null;

		if (stringdate != null && stringdate.length() == 8)
			format = new SimpleDateFormat("yyyyMMdd");
		if (stringdate != null && stringdate.length() == 10)
			format = new SimpleDateFormat("yyyy-MM-dd");
		if (stringdate != null && stringdate.length() == 14)
			format = new SimpleDateFormat("yyyyMMddHHmmss");
		if (stringdate != null && stringdate.length() == 19)
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try {
			date = format.parse(stringdate);
			return date;
		} catch (Exception e) {
			log.info("日期格式错。");
			// e.printStackTrace();
			return null;
		}
	}

	/**
	 * 转换 date型数据为简要格式
	 * 
	 * @since 2007.09.19
	 * @param dateTime
	 *            date类型日期
	 * @return "MM-dd HH:mm"字符型日期
	 */

	public static String fmtMDHM(Date dateTime) {

		String fmtTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
			if (dateTime != null)
				fmtTime = sdf.format(dateTime);
			return fmtTime;
		} catch (Exception e) {
			log.info("转换日期错。");
			return null;
		}
	}

	/**
	 * 转换 date型数据为简要格式
	 * 
	 * @since 2007.09.19
	 * @param dateTime
	 *            date类型日期
	 * @return "yyyy-MM-dd HH:mm"字符型日期
	 */
	public static String fmtYMDHM(Date dateTime) {
		String fmtTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if (dateTime != null)
				fmtTime = sdf.format(dateTime);
			return fmtTime;
		} catch (Exception e) {
			log.info("转换日期错。");
			return null;
		}
	}

	/**
	 * 转换 yyyyMMddHHmmss型数据为简要格式
	 * 
	 * @since 2010.04.21
	 * @param dateTime
	 *            String类型日期
	 * @return "yyyy-MM-dd HH:mm:ss"字符型日期
	 */
	public static String fmtYMDHMS(String dateTime) {
		StringBuffer fmtTime = new StringBuffer();

		try {
			fmtTime.append(dateTime.substring(0, 4));
			fmtTime.append('-');

			fmtTime.append(dateTime.substring(4, 6));
			fmtTime.append('-');

			fmtTime.append(dateTime.substring(6, 8));
			fmtTime.append(' ');

			fmtTime.append(dateTime.substring(8, 10));
			fmtTime.append(':');

			fmtTime.append(dateTime.substring(10, 12));
			fmtTime.append(':');

			fmtTime.append(dateTime.substring(12, 14));

		} catch (Exception e) {
			log.info("转换日期错。");
			return null;
		}

		return fmtTime.toString();
	}

	/**
	 * 转换 date型数据为简要格式
	 * 
	 * @since 2007.09.19
	 * @param dateTime
	 *            date类型日期
	 * @return "yyyy-MM-dd HH:mm:ss"字符型日期
	 */
	public static String fmtYMDHMS(Date dateTime) {
		String fmtTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (dateTime != null)
				fmtTime = sdf.format(dateTime);
			return fmtTime;
		} catch (Exception e) {
			log.info("转换日期错。");
			return null;
		}
	}

	/**
	 * 计算预期响应时间
	 * 
	 * @param openTime
	 *            开始时间点
	 * @param interval
	 *            时间间隔
	 * @param respondGrade
	 *            响应级别(1-工作时间相应,2-7x24小时响应)
	 * @return 预期响应时间
	 */
	public static Date getPreWorkTime(Date openTime, int interval, int respondGrade) {

		try {
			Date preExpireTime = new Date(openTime.getTime() + interval * 60000);
			return preExpireTime;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算预期响应时间
	 * 
	 * @param interval
	 *            时间间隔
	 * @param respondGrade
	 *            响应级别(1-工作时间相应,2-7x24小时响应)
	 * @return 预期响应时间
	 */
	public static Date getPreWorkTime2(int interval, int respondGrade) {
		try {
			Calendar temp = Calendar.getInstance();
			temp.add(Calendar.MINUTE, interval);
			return temp.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取当前日期前一天的日期，日期格式yyyy-mm-dd
	 * 
	 * @return 当前日期前一天的日期，日期格式yyyy-MM-dd
	 */
	public static String getPreviousDate() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = formatter.format(date.getTime()).toString();
		return sysDate;
	}

	/**
	 * 获取与指定的日期相差i月的日期,日期格式为"yyyy-MM-dd"
	 * 
	 * @param fixDate
	 *            指定的日期（格式为"yyyy-MM-dd"）
	 * @param i
	 *            相隔月数（i为整数）
	 * @return 计算后的日期
	 */
	public static String getFixedMonth(String fixDate, int i) {
		int year = Integer.parseInt(fixDate.substring(0, 4));
		int month = Integer.parseInt(fixDate.substring(5, 7));
		int day = Integer.parseInt(fixDate.substring(8, 10));

		Calendar date = Calendar.getInstance();
		date.set(year, month - 1, day);
		date.add(Calendar.MONTH, i);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = formatter.format(date.getTime()).toString();
		return sysDate;
	}

	/**
	 * 获取与指定的日期相差i月的月份
	 * 
	 * @param fixMonth
	 *            "yyyy-MM"
	 * @param i
	 *            相隔月数（i为整数）
	 * @return "yyyy-MM"
	 */
	public static String getFixedMonthYYYY_MM(String fixMonth, int i) {
		int year = Integer.parseInt(fixMonth.substring(0, 4));
		int month = Integer.parseInt(fixMonth.substring(5, 7));
		int day = 15;

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		cal.add(Calendar.MONTH, i);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String retDate = formatter.format(cal.getTime()).toString();
		return retDate.substring(0, 7);

	}
	
	/**
	 * 获取与系统日期相差i天的日期,日期格式为"yyyy-MM-dd"
	 * 
	 * @param i
	 *            相隔天数（i为整数）
	 * @return 计算后的日期
	 */
	public static String getFixedDate(int i) {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_MONTH, i);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = formatter.format(date.getTime()).toString();
		return sysDate;
	}

	/**
	 * 获取与指定的日期相差i天的日期,日期格式为"yyyy-MM-dd"
	 * 
	 * @param fixDate
	 *            指定的日期（格式为"yyyy-MM-dd"）
	 * @param i
	 *            相隔天数（i为整数）
	 * @return 计算后的日期
	 */
	public static String getFixedDate(String fixDate, int i) {
		int year = Integer.parseInt(fixDate.substring(0, 4));
		int month = Integer.parseInt(fixDate.substring(5, 7));
		int day = Integer.parseInt(fixDate.substring(8, 10));

		Calendar date = Calendar.getInstance();
		date.set(year, month - 1, day);
		date.add(Calendar.DAY_OF_MONTH, i);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = formatter.format(date.getTime()).toString();
		return sysDate;
	}

	/**
	 * 获取与指定的日期相差i天的日期,日期格式为"yyyyMMdd"
	 * 
	 * @param fixDate
	 *            指定的日期（格式为"yyyy-MM-dd"）
	 * @param i
	 *            相隔天数（i为整数）
	 * @return 计算后的日期
	 */
	public static String getFixedDateYYYYMMDD(String fixDate, int i) {
		int year = Integer.parseInt(fixDate.substring(0, 4));
		int month = Integer.parseInt(fixDate.substring(5, 7));
		int day = Integer.parseInt(fixDate.substring(8, 10));

		Calendar date = Calendar.getInstance();
		date.set(year, month - 1, day);
		date.add(Calendar.DAY_OF_MONTH, i);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String sysDate = formatter.format(date.getTime()).toString();
		return sysDate;
	}

	/**
	 * 获取与指定的日期相差i天的日期,日期格式为"yyyy-MM-dd"
	 * 
	 * @param fixDate
	 *            指定的日期（格式为"yyyy-MM-dd"）
	 * @param i
	 *            相隔天数（i为整数）
	 * @return 计算后的日期
	 */
	public static String getFixedDateYYYY_MM_DD(String fixDate, int i) {
		int year = Integer.parseInt(fixDate.substring(0, 4));
		int month = Integer.parseInt(fixDate.substring(5, 7));
		int day = Integer.parseInt(fixDate.substring(8, 10));

		Calendar date = Calendar.getInstance();
		date.set(year, month - 1, day);
		date.add(Calendar.DAY_OF_MONTH, i);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = formatter.format(date.getTime()).toString();
		return sysDate;
	}

	/**
	 * 获取与根据指定时间相差h小时m分钟s秒的时间
	 * 
	 * @param hms
	 *            HH:mm:ss
	 * @param h
	 * @param m
	 * @param s
	 * @return HH:mm:ss
	 */
	public static String getFixedTimeHMS(String hms, int h, int m, int s) {
		int hour = Integer.parseInt(hms.substring(0, 2));
		int minute = Integer.parseInt(hms.substring(3, 5));
		int second = Integer.parseInt(hms.substring(6, 8));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR_OF_DAY, hour);
		date.set(Calendar.MINUTE, minute);
		date.set(Calendar.SECOND, second);
		if (h != 0) {
			date.add(Calendar.HOUR_OF_DAY, h);
		}
		if (m != 0) {
			date.add(Calendar.MINUTE, m);
		}
		if (s != 0) {
			date.add(Calendar.SECOND, s);
		}
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String fixedTime = formatter.format(date.getTime()).toString();
		return fixedTime;
	}

	/**
	 * 获取前一个月前日期，日期格式yyyy-mm
	 * 
	 * @return 前一个月前日期，日期格式yyyy-MM
	 */
	public static String getPreviousMonth() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String sysDate = formatter.format(date.getTime()).toString();
		return sysDate;
	}

	public static String getNextMonth() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MONTH, 1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String sysDate = formatter.format(date.getTime()).toString();
		return sysDate;
	}

	/**
	 * 获取当前日期与参数间隔（分钟）的历史日期
	 * 
	 * @param minute
	 *            分钟
	 * @return 历史日期，日期格式yyyyMMddHHmmss
	 */
	public static String getPreMinuteDateTime(String minute) {
		try {
			int min = Integer.parseInt(minute);
			Calendar date = Calendar.getInstance();
			date.add(Calendar.MINUTE, -min);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String sysDate = formatter.format(date.getTime()).toString();
			return sysDate;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取当前日为周几
	 * 
	 * @return 当前为周几
	 * */
	public static String getDayOfWeek() {

		Calendar cal = Calendar.getInstance();
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case 2:
			return "Monday";
		case 3:
			return "Tuesday";
		case 4:
			return "Wednesday";
		case 5:
			return "Thursday";
		case 6:
			return "Friday";
		case 7:
			return "Staturday";
		case 1:
			return "Sunday";
		}
		return null;
	}

	/**
	 * 获取日期为周几(中文)
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayOfWeekZH(String dateStr) {
		Date date = str2Date(dateStr);
		if (date == null) {
			log.error("日期格式错误");
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int theDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		switch (theDay) {
		case 0:
			return "周日";
		case 1:
			return "周一";
		case 2:
			return "周二";
		case 3:
			return "周三";
		case 4:
			return "周四";
		case 5:
			return "周五";
		case 6:
			return "周六";
		default:
			return null;
		}
	}

	/**
	 * 获取当前日为周几
	 * 
	 * @return 当前为周几(数字：周一至周日分别对应1-7)
	 * */
	public static int getDayIntOfWeek() {
		Calendar cal = Calendar.getInstance();
		int theDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (theDay == 0) {
			return 7;
		}
		return theDay;
	}

	public static int getDayIntOfWeek(Calendar cal) {
		int theDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (theDay == 0) {
			return 7;
		}
		return theDay;
	}

	/**
	 * 获取时间差
	 * 
	 * @return 时间差
	 * */
	public static String getDifOfTime(String time) {
		long diftime = System.currentTimeMillis() - str2Date(time).getTime();
		String mark = "";
		if (diftime < 0) {
			mark = "-";
			diftime = Math.abs(diftime);
		}
		long days = (long) Math.floor(diftime / (1000 * 3600 * 24));
		long hours = (long) Math.floor((diftime - days * 1000 * 3600 * 24) / (1000 * 3600));
		long minitues = (long) Math.floor((diftime - days * 1000 * 3600 * 24 - hours * 1000 * 3600) / (1000 * 60));

		String difofTime = mark + days + "天" + hours + "小时" + minitues + "分";
		return difofTime;
	}

	/**
	 * 获取两者的时间差,时间格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time1
	 *            ,time2
	 * @return double 单位：天
	 */
	public static Double getSubTime(String time1, String time2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = df.parse(time1);
			Date date2 = df.parse(time2);
			return (date1.getTime() - date2.getTime()) * 1.00 / (1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			log.error("时间格式不正确");
			e.printStackTrace();
		}
		return 0.00;
	}

	/**
	 * 获取两者的时间差
	 * 
	 * @return double 单位：天
	 */
	public static Double getSubTime(Calendar cal1, Calendar cal2) {
		Date date1 = cal1.getTime();
		Date date2 = cal2.getTime();
		Double retVal = (date1.getTime() - date2.getTime()) * 1.00 / (1000 * 60 * 60 * 24);
		return retVal;
	}

	public static int getSubDays(Calendar cal1, Calendar cal2) {
		Double subTime = getSubTime(cal1, cal2);
		int subDays = new BigDecimal(subTime).setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
		return subDays;
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param startDate
	 *            endDate 日期格式：yyyy-mm-dd
	 * @return int 单位：天
	 */
	public static int getSubDate(String endDate, String startDate) {
		String endTime = endDate + " 10:00:00";
		String startTime = startDate + " 10:00:00";
		return getSubTime(endTime, startTime).intValue();
	}

	/**
	 * 格式化："YYYY-MM-DD" -> "YYYY年MM/M月"
	 * 
	 * @param yearAndMonth
	 * @return
	 */
	public static String formartYMDtoCYM(String date) {
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		month = month.startsWith("0") ? month.substring(1, 2) : month;
		return year + "年" + month + "月";
	}

	/**
	 * 格式化："YYYY-MM-DD" -> "MM/M月DD/D日"
	 * 
	 * @param yearAndMonth
	 * @return
	 */
	public static String formartYMDtoCMD(String date) {
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);
		month = month.startsWith("0") ? month.substring(1, 2) : month;
		day = day.startsWith("0") ? day.substring(1, 2) : day;
		return month + "月" + day + "日";
	}

	/**
	 * 格式化："HH:MM:SS" -> "HH/H:MM"
	 * 
	 * @param HH
	 *            :MM:SS
	 * @return HH/H:MM
	 */
	public static String formartHMStoCHM(String time) {
		String hour = time.substring(0, 2);
		String minute = time.substring(3, 5);
		hour = hour.startsWith("0") ? hour.substring(1, 2) : hour;
		// minute = minute.startsWith("0") ? minute.substring(1, 2) : minute;
		return hour + ":" + minute;
	}

	/**
	 * 格式化日期：replacement代替"-"&&月和日前除0
	 * 
	 * @param date
	 *            "YYYY-MM-DD"
	 * @param replacement
	 * @return
	 */
	public static String formartYMD(String date, String replacement) {
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		month = month.startsWith("0") ? month.substring(1, 2) : month;
		String day = date.substring(8, 10);
		day = day.startsWith("0") ? day.substring(1, 2) : day;
		return year + replacement + month + replacement + day;
	}

	/**
	 * 格式化日期：格式化："YYYY-MM-DD" -> "YYYY年MM/M月DD/D日"
	 * 
	 * @param date
	 *            "YYYY-MM-DD"
	 * @return
	 */
	public static String formartYMDtoCYMD(String date) {
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		month = month.startsWith("0") ? month.substring(1, 2) : month;
		if (date.length() > 7) {
			String day = date.substring(8, 10);
			day = day.startsWith("0") ? day.substring(1, 2) : day;
			return year + "年" + month + "月" + day + "日";
		} else {
			return year + "年" + month + "月";
		}
	}

	/**
	 * 获取下一个月份
	 * 
	 * @param yearAndMonth
	 *            YYYY-MM
	 * @return
	 */
	public static String getNextMonth(String yearAndMonth) {
		int year = StringUtil.ch2Int(yearAndMonth.substring(0, 4));
		int month = StringUtil.ch2Int(yearAndMonth.substring(5, 7));
		int newMonth = month % 12 + 1;
		int newYear = newMonth < month ? year + 1 : year;
		return newYear + "-" + String.format("%02d", newMonth);
	}

	/**
	 * 获取下一年
	 * 
	 * @param year
	 *            YYYY
	 * @return
	 */
	public static String getNextYear(String year) {
		return (StringUtil.ch2Int(year) + 1) + "";
	}

	/**
	 * 比较两个参数日期字符串的顺序
	 * 
	 * @param strDate1
	 * @param strDate2
	 * @return strDate1==strDate2:返回0; strDate1<strDate2:返回负数; strDate1>strDate2:返回正数
	 * 
	 * @author hongwei
	 */
	public static int compare2Dates(String strDate1, String strDate2) {
		Date date1 = str2Date(strDate1);
		Date date2 = str2Date(strDate2);
		return date1.compareTo(date2);
	}

	/**
	 * 获取日期为周几
	 * 
	 * @param date
	 *            "YYYY-MM-DD"
	 * @return
	 */
	public static int getDayIntOfWeek(String stringdate) {
		Date date = str2Date(stringdate);
		if (date == null) {
			log.error("日期格式错误");
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int theDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (theDay == 0) {
			return 7;
		}
		return theDay;
	}

	/**
	 * 判断某日是否为节日，若是则返回节日名称，否则返回null
	 * 
	 * @param date
	 * @return
	 */
	public static String replaceWithFestivalNameIfIs(String date) {
		if (date.endsWith("01-01")) {
			return "元旦";
		}
		if (date.endsWith("02-14")) {
			return "情人节";
		}
		if (date.endsWith("03-08")) {
			return "妇女节";
		}
		if (date.endsWith("05-01")) {
			return "劳动节";
		}
		if (date.endsWith("06-01")) {
			return "儿童节";
		}
		if (date.endsWith("10-01")) {
			return "国庆节";
		}
		if (date.endsWith("12-25")) {
			return "圣诞节";
		}
		return null;
	}

	/**
	 * 判断是否是该月的最后一天
	 * 
	 * @param date
	 *            YYYY-MM-DD
	 * @return
	 */
	public static boolean IsLastDayOfMonth(String date) {
		String nextDate = getFixedDate(date, 1);
		if (nextDate.endsWith("-01")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 修正年同期
	 * 
	 * @param date
	 * @return
	 */
	public static String fixedYearOfDate(String date, int fixYear) {
		String fixedYear = StringUtil.ch2Int(date.substring(0, 4)) + fixYear + "";
		return fixedYear + date.substring(4);
	}

	/**
	 * 计算某月的天数
	 * 
	 * @author hongwei
	 * @since 2012-06-18
	 */
	public static int daysOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		return cal.getActualMaximum(Calendar.DATE);
	}
	
	/**
	 * 判断日期strDate是否在[startDate, endDate]期间
	 * @param strStartDate 开始日期
	 * @param strEndDate   结束日期
	 * @param strDate
	 * startDate <= endDate
	 * 
	 * @return
	 * @author hongwei
	 * @since 2012-10-16
	 */
	public static boolean isDuringDates(String strStartDate, String strEndDate, String strDate) {
		boolean ret = false;
		Date startDate = str2Date(strStartDate);
		Date endDate = str2Date(strEndDate);
		Date date = str2Date(strDate);
		
		if(date.compareTo(startDate) >= 0 && date.compareTo(endDate) <=0) 
			ret = true;
		
		return ret;
	}

	public static void main(String[] args) {

		System.out.println(CalendarUtil.isDuringDates("2012-10-10", "2012-10-26", "2012-10-16"));
	}

}