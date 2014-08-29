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
	 * ��ȡϵͳʱ��
	 * 
	 * @return ����ϵͳ��ǰʱ���ַ������ַ�����ʽΪ��yyyy-mm-dd hh:mm:ss
	 */

	public static String getSysTimeYMDHMS() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * ��ȡϵͳʱ��
	 * 
	 * @return ����ϵͳ��ǰʱ���ַ������ַ�����ʽΪ��MM-dd HH:mm:ss SSS case_traceʹ��
	 */
	public static String getSysTimeYMDHMS11() {
		return new SimpleDateFormat("MM-dd HH:mm:ss SSS").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * ��ȡϵͳʱ��
	 * 
	 * @return ����ϵͳ��ǰʱ���ַ������ַ�����ʽΪ��MM-dd HH:mm:ss SSS case_traceʹ��
	 */
	public static String getSysTimeYMDHMS111() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * ��ȡϵͳʱ��
	 * 
	 * @return ����ϵͳ��ǰʱ���ַ������ַ�����ʽΪ��yyyyMMddHHmmss
	 */

	public static String getSysTimeYMDHMS1() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * ��ȡϵͳʱ��<br>
	 * 
	 * @return ����ϵͳ��ǰʱ���ַ������ַ�����ʽΪ��yyyyMMddHHmmssSSS
	 */

	public static String getSysTimeYMDHMS2() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * ��ȡϵͳʱ��
	 * 
	 * @return ����ϵͳ��ǰʱ���ַ������ַ�����ʽΪ��yyyy-mm-dd hh:mm
	 */

	public static String getSysTimeYMDHM() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * ��ȡϵͳʱ��
	 * 
	 * @return ����ϵͳ��ǰʱ���ַ������ַ�����ʽΪ��yyyy-mm-dd
	 */

	public static String getSysTimeYMD() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * ��ȡϵͳʱ��
	 * 
	 * @return ����ϵͳ��ǰʱ���ַ������ַ�����ʽΪ��yyyy-mm
	 */

	public static String getSysTimeYM() {
		return new SimpleDateFormat("yyyy-MM").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * ��ȡϵͳʱ��(����)
	 * 
	 * @return ���ַ�������ʽ����ϵͳ��ǰʱ��(����)
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
	 * ��ȡϵͳʱ��
	 * 
	 * @return ����ϵͳ��ǰʱ���ַ������ַ�����ʽΪ��hh:mm:ss
	 */

	public static String getSysTimeHMS() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * ȡϵͳʱ��hhmmss
	 * 
	 * @return String--hhmmss��ʽ��ʱ���ַ���
	 */

	public static String getTime() {
		return new SimpleDateFormat("HHmmss").format(new Date().getTime());
	}

	/**
	 * ȡϵͳ����yyyymmdd
	 * 
	 * @return String--yyyymmdd��ʽ�������ַ���
	 */

	public static String getDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date().getTime());
	}

	/**
	 * ���ݴ����ʱ��תΪYYYYMMDDHHMMSS��ʽ���ַ���
	 * 
	 * @param time
	 *            ����ʱ��ĳ�����
	 * @return ����ʱ����ַ���
	 */

	public static String getDateTime(long time) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(time);
	}

	/**
	 * ���ݴ����ʱ��תΪYYYY-MM-DD HH:MM:SS��ʽ���ַ���
	 * 
	 * @param time
	 *            ����ʱ��ĳ�����
	 * @return ����ʱ����ַ���
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
	 * ���ַ���yyyy-MM-DDת��Date��������
	 * 
	 * @since 2006.01.19
	 * @param stringdate
	 *            ��yyyyMMdd��ʾ�������ַ���
	 * @return date��������
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
			log.info("���ڸ�ʽ��");
			// e.printStackTrace();
			return null;
		}
	}

	/**
	 * ת�� date������Ϊ��Ҫ��ʽ
	 * 
	 * @since 2007.09.19
	 * @param dateTime
	 *            date��������
	 * @return "MM-dd HH:mm"�ַ�������
	 */

	public static String fmtMDHM(Date dateTime) {

		String fmtTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
			if (dateTime != null)
				fmtTime = sdf.format(dateTime);
			return fmtTime;
		} catch (Exception e) {
			log.info("ת�����ڴ�");
			return null;
		}
	}

	/**
	 * ת�� date������Ϊ��Ҫ��ʽ
	 * 
	 * @since 2007.09.19
	 * @param dateTime
	 *            date��������
	 * @return "yyyy-MM-dd HH:mm"�ַ�������
	 */
	public static String fmtYMDHM(Date dateTime) {
		String fmtTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if (dateTime != null)
				fmtTime = sdf.format(dateTime);
			return fmtTime;
		} catch (Exception e) {
			log.info("ת�����ڴ�");
			return null;
		}
	}

	/**
	 * ת�� yyyyMMddHHmmss������Ϊ��Ҫ��ʽ
	 * 
	 * @since 2010.04.21
	 * @param dateTime
	 *            String��������
	 * @return "yyyy-MM-dd HH:mm:ss"�ַ�������
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
			log.info("ת�����ڴ�");
			return null;
		}

		return fmtTime.toString();
	}

	/**
	 * ת�� date������Ϊ��Ҫ��ʽ
	 * 
	 * @since 2007.09.19
	 * @param dateTime
	 *            date��������
	 * @return "yyyy-MM-dd HH:mm:ss"�ַ�������
	 */
	public static String fmtYMDHMS(Date dateTime) {
		String fmtTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (dateTime != null)
				fmtTime = sdf.format(dateTime);
			return fmtTime;
		} catch (Exception e) {
			log.info("ת�����ڴ�");
			return null;
		}
	}

	/**
	 * ����Ԥ����Ӧʱ��
	 * 
	 * @param openTime
	 *            ��ʼʱ���
	 * @param interval
	 *            ʱ����
	 * @param respondGrade
	 *            ��Ӧ����(1-����ʱ����Ӧ,2-7x24Сʱ��Ӧ)
	 * @return Ԥ����Ӧʱ��
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
	 * ����Ԥ����Ӧʱ��
	 * 
	 * @param interval
	 *            ʱ����
	 * @param respondGrade
	 *            ��Ӧ����(1-����ʱ����Ӧ,2-7x24Сʱ��Ӧ)
	 * @return Ԥ����Ӧʱ��
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
	 * ��ȡ��ǰ����ǰһ������ڣ����ڸ�ʽyyyy-mm-dd
	 * 
	 * @return ��ǰ����ǰһ������ڣ����ڸ�ʽyyyy-MM-dd
	 */
	public static String getPreviousDate() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = formatter.format(date.getTime()).toString();
		return sysDate;
	}

	/**
	 * ��ȡ��ָ�����������i�µ�����,���ڸ�ʽΪ"yyyy-MM-dd"
	 * 
	 * @param fixDate
	 *            ָ�������ڣ���ʽΪ"yyyy-MM-dd"��
	 * @param i
	 *            ���������iΪ������
	 * @return ����������
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
	 * ��ȡ��ָ�����������i�µ��·�
	 * 
	 * @param fixMonth
	 *            "yyyy-MM"
	 * @param i
	 *            ���������iΪ������
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
	 * ��ȡ��ϵͳ�������i�������,���ڸ�ʽΪ"yyyy-MM-dd"
	 * 
	 * @param i
	 *            ���������iΪ������
	 * @return ����������
	 */
	public static String getFixedDate(int i) {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_MONTH, i);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = formatter.format(date.getTime()).toString();
		return sysDate;
	}

	/**
	 * ��ȡ��ָ�����������i�������,���ڸ�ʽΪ"yyyy-MM-dd"
	 * 
	 * @param fixDate
	 *            ָ�������ڣ���ʽΪ"yyyy-MM-dd"��
	 * @param i
	 *            ���������iΪ������
	 * @return ����������
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
	 * ��ȡ��ָ�����������i�������,���ڸ�ʽΪ"yyyyMMdd"
	 * 
	 * @param fixDate
	 *            ָ�������ڣ���ʽΪ"yyyy-MM-dd"��
	 * @param i
	 *            ���������iΪ������
	 * @return ����������
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
	 * ��ȡ��ָ�����������i�������,���ڸ�ʽΪ"yyyy-MM-dd"
	 * 
	 * @param fixDate
	 *            ָ�������ڣ���ʽΪ"yyyy-MM-dd"��
	 * @param i
	 *            ���������iΪ������
	 * @return ����������
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
	 * ��ȡ�����ָ��ʱ�����hСʱm����s���ʱ��
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
	 * ��ȡǰһ����ǰ���ڣ����ڸ�ʽyyyy-mm
	 * 
	 * @return ǰһ����ǰ���ڣ����ڸ�ʽyyyy-MM
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
	 * ��ȡ��ǰ�����������������ӣ�����ʷ����
	 * 
	 * @param minute
	 *            ����
	 * @return ��ʷ���ڣ����ڸ�ʽyyyyMMddHHmmss
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
	 * ��ȡ��ǰ��Ϊ�ܼ�
	 * 
	 * @return ��ǰΪ�ܼ�
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
	 * ��ȡ����Ϊ�ܼ�(����)
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayOfWeekZH(String dateStr) {
		Date date = str2Date(dateStr);
		if (date == null) {
			log.error("���ڸ�ʽ����");
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int theDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		switch (theDay) {
		case 0:
			return "����";
		case 1:
			return "��һ";
		case 2:
			return "�ܶ�";
		case 3:
			return "����";
		case 4:
			return "����";
		case 5:
			return "����";
		case 6:
			return "����";
		default:
			return null;
		}
	}

	/**
	 * ��ȡ��ǰ��Ϊ�ܼ�
	 * 
	 * @return ��ǰΪ�ܼ�(���֣���һ�����շֱ��Ӧ1-7)
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
	 * ��ȡʱ���
	 * 
	 * @return ʱ���
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

		String difofTime = mark + days + "��" + hours + "Сʱ" + minitues + "��";
		return difofTime;
	}

	/**
	 * ��ȡ���ߵ�ʱ���,ʱ���ʽ��yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time1
	 *            ,time2
	 * @return double ��λ����
	 */
	public static Double getSubTime(String time1, String time2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = df.parse(time1);
			Date date2 = df.parse(time2);
			return (date1.getTime() - date2.getTime()) * 1.00 / (1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			log.error("ʱ���ʽ����ȷ");
			e.printStackTrace();
		}
		return 0.00;
	}

	/**
	 * ��ȡ���ߵ�ʱ���
	 * 
	 * @return double ��λ����
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
	 * ��ȡ��������֮�������
	 * 
	 * @param startDate
	 *            endDate ���ڸ�ʽ��yyyy-mm-dd
	 * @return int ��λ����
	 */
	public static int getSubDate(String endDate, String startDate) {
		String endTime = endDate + " 10:00:00";
		String startTime = startDate + " 10:00:00";
		return getSubTime(endTime, startTime).intValue();
	}

	/**
	 * ��ʽ����"YYYY-MM-DD" -> "YYYY��MM/M��"
	 * 
	 * @param yearAndMonth
	 * @return
	 */
	public static String formartYMDtoCYM(String date) {
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		month = month.startsWith("0") ? month.substring(1, 2) : month;
		return year + "��" + month + "��";
	}

	/**
	 * ��ʽ����"YYYY-MM-DD" -> "MM/M��DD/D��"
	 * 
	 * @param yearAndMonth
	 * @return
	 */
	public static String formartYMDtoCMD(String date) {
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);
		month = month.startsWith("0") ? month.substring(1, 2) : month;
		day = day.startsWith("0") ? day.substring(1, 2) : day;
		return month + "��" + day + "��";
	}

	/**
	 * ��ʽ����"HH:MM:SS" -> "HH/H:MM"
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
	 * ��ʽ�����ڣ�replacement����"-"&&�º���ǰ��0
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
	 * ��ʽ�����ڣ���ʽ����"YYYY-MM-DD" -> "YYYY��MM/M��DD/D��"
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
			return year + "��" + month + "��" + day + "��";
		} else {
			return year + "��" + month + "��";
		}
	}

	/**
	 * ��ȡ��һ���·�
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
	 * ��ȡ��һ��
	 * 
	 * @param year
	 *            YYYY
	 * @return
	 */
	public static String getNextYear(String year) {
		return (StringUtil.ch2Int(year) + 1) + "";
	}

	/**
	 * �Ƚ��������������ַ�����˳��
	 * 
	 * @param strDate1
	 * @param strDate2
	 * @return strDate1==strDate2:����0; strDate1<strDate2:���ظ���; strDate1>strDate2:��������
	 * 
	 * @author hongwei
	 */
	public static int compare2Dates(String strDate1, String strDate2) {
		Date date1 = str2Date(strDate1);
		Date date2 = str2Date(strDate2);
		return date1.compareTo(date2);
	}

	/**
	 * ��ȡ����Ϊ�ܼ�
	 * 
	 * @param date
	 *            "YYYY-MM-DD"
	 * @return
	 */
	public static int getDayIntOfWeek(String stringdate) {
		Date date = str2Date(stringdate);
		if (date == null) {
			log.error("���ڸ�ʽ����");
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
	 * �ж�ĳ���Ƿ�Ϊ���գ������򷵻ؽ������ƣ����򷵻�null
	 * 
	 * @param date
	 * @return
	 */
	public static String replaceWithFestivalNameIfIs(String date) {
		if (date.endsWith("01-01")) {
			return "Ԫ��";
		}
		if (date.endsWith("02-14")) {
			return "���˽�";
		}
		if (date.endsWith("03-08")) {
			return "��Ů��";
		}
		if (date.endsWith("05-01")) {
			return "�Ͷ���";
		}
		if (date.endsWith("06-01")) {
			return "��ͯ��";
		}
		if (date.endsWith("10-01")) {
			return "�����";
		}
		if (date.endsWith("12-25")) {
			return "ʥ����";
		}
		return null;
	}

	/**
	 * �ж��Ƿ��Ǹ��µ����һ��
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
	 * ������ͬ��
	 * 
	 * @param date
	 * @return
	 */
	public static String fixedYearOfDate(String date, int fixYear) {
		String fixedYear = StringUtil.ch2Int(date.substring(0, 4)) + fixYear + "";
		return fixedYear + date.substring(4);
	}

	/**
	 * ����ĳ�µ�����
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
	 * �ж�����strDate�Ƿ���[startDate, endDate]�ڼ�
	 * @param strStartDate ��ʼ����
	 * @param strEndDate   ��������
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