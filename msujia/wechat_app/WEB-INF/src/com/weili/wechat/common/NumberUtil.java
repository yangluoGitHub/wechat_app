package com.weili.wechat.common;

import java.util.Locale;
import java.text.NumberFormat;
import java.text.DecimalFormat;

/**
 * @author shp
 * @since 2005.12.12
 */

public final class NumberUtil {

	/**
	 * ����С��������ౣ����λС����
	 * 
	 * @param arg0
	 *            - ˫������
	 * @return ����С����֮����ַ���
	 */

	public static String decimal(double arg0) {
		DecimalFormat df = new DecimalFormat("#,###.##");
		return df.format(arg0);
	}

	/**
	 * ����С������������λС����
	 * 
	 * @param arg0
	 *            - ˫������
	 * @return ����С����֮����ַ���
	 */

	public static String decimal2(double arg0) {
		return new DecimalFormat("#,##0.00").format(arg0);
	}

	/*
	 * �������뱣��С�������λ
	 * 
	 * @param double
	 * 
	 * @return double
	 * 
	 * @author hongwei
	 */
	public static double roundDecimal2(double arg0) {
		return Math.round(arg0 * 100) / 100.0;
	}

	/*
	 * �������뱣��С�������λ
	 * 
	 * @param double
	 * 
	 * @return double
	 * 
	 * @author hongwei
	 */
	public static double roundDecimal3(double arg0) {
		return Math.round(arg0 * 1000) / 1000.0;
	}

	/**
	 * ��������һ���������λС����
	 * 
	 * @param arg0
	 *            - ˫������
	 * @return ��������һ�֮����ַ���
	 */

	public static String RMB(double arg0) {
		NumberFormat df = NumberFormat.getCurrencyInstance(Locale.CHINA);
		return df.format(arg0);
	}

	/**
	 * ������Ԫ����������λС����
	 * 
	 * @param arg0
	 *            - ˫������
	 * @return ������Ԫ��֮����ַ���
	 */

	public static String dollar(double arg0) {
		NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
		return df.format(arg0);
	}

	/**
	 * ���ְٷֱȻ�����ౣ����λС����
	 * 
	 * @param arg0
	 *            - ˫������
	 * @return ���ذٷֱȻ�֮����ַ���
	 */

	public static String percent(double arg0) {
		return new DecimalFormat("#,##0.00%").format(arg0);
	}

	/**
	 * ������ת��Ϊʮ������
	 * 
	 * @param binary
	 *            �޷��ŵĶ���������ת����ʱ�����λ��������ұ߲�0
	 * @return null��ʾ�޷�ת����
	 * */
	public static final String binaryToHexString(String binary) {
		if (binary == null || binary.equals("")) {
			return null;
		}
		try {
			StringBuffer Hex = new StringBuffer();

			int Octal = 0;

			String tempBinary = null;
			do {
				if (binary.length() <= 4) {
					Octal = Integer.parseInt(binary, 2);

					return Hex.append(Integer.toHexString(Octal)).toString().toUpperCase();
				}
				tempBinary = binary.substring(0, 4);

				binary = binary.substring(4, binary.length());

				Octal = Integer.parseInt(tempBinary, 2);

				Hex.append(Integer.toHexString(Octal));
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ʮ�����ƻ�Ϊ������ת
	 * 
	 * @param �޷��ŵ�ʮ��������
	 **/

	public static final String HexToBinaryString(String binary) {
		if (binary == null || binary.equals("")) {
			return null;
		}
		try {
			int Hex = 0;

			StringBuffer Binary = new StringBuffer();
			String binaryHex = null;
			for (int i = 0; i < binary.length(); i++) {
				Hex = Integer.parseInt(binary.substring(i, i + 1), 16);

				binaryHex = "0000" + String.valueOf(Integer.toBinaryString(Hex));

				Binary.append(binaryHex.subSequence(binaryHex.length() - 4, binaryHex.length()));
			}

			return Binary.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ���ָ�ʽ��(CHINA)
	 * 
	 * @param number
	 * @return 
	 */
	public static String formartNumber(double number) {
		NumberFormat nf = NumberFormat.getInstance(Locale.CHINA); 
		return nf.format(number);
	}

	public static void main(String[] args) {
		// System.out.println(NumberUtil.roundDecimal2(CalendarUtil.getSubTime("2012-01-03 08:59:59",
		// "2011-12-31 14:01:12")));
		System.out.println(NumberUtil.formartNumber(123456789.123456789));
	}

}