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
	 * 数字小数化（最多保留两位小数）
	 * 
	 * @param arg0
	 *            - 双精度数
	 * @return 返回小数化之后的字符串
	 */

	public static String decimal(double arg0) {
		DecimalFormat df = new DecimalFormat("#,###.##");
		return df.format(arg0);
	}

	/**
	 * 数字小数化（保留两位小数）
	 * 
	 * @param arg0
	 *            - 双精度数
	 * @return 返回小数化之后的字符串
	 */

	public static String decimal2(double arg0) {
		return new DecimalFormat("#,##0.00").format(arg0);
	}

	/*
	 * 四舍五入保留小数点后两位
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
	 * 四舍五入保留小数点后三位
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
	 * 数字人民币化（保留两位小数）
	 * 
	 * @param arg0
	 *            - 双精度数
	 * @return 返回人民币化之后的字符串
	 */

	public static String RMB(double arg0) {
		NumberFormat df = NumberFormat.getCurrencyInstance(Locale.CHINA);
		return df.format(arg0);
	}

	/**
	 * 数字美元化（保留两位小数）
	 * 
	 * @param arg0
	 *            - 双精度数
	 * @return 返回美元化之后的字符串
	 */

	public static String dollar(double arg0) {
		NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
		return df.format(arg0);
	}

	/**
	 * 数字百分比化（最多保留两位小数）
	 * 
	 * @param arg0
	 *            - 双精度数
	 * @return 返回百分比化之后的字符串
	 */

	public static String percent(double arg0) {
		return new DecimalFormat("#,##0.00%").format(arg0);
	}

	/**
	 * 二进制转换为十六进制
	 * 
	 * @param binary
	 *            无符号的二进制数，转换的时候如果位数不足会右边补0
	 * @return null表示无法转换。
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
	 * 十六进制换为二进制转
	 * 
	 * @param 无符号的十六进制数
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
	 * 数字格式化(CHINA)
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