package com.weili.wechat.common;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author shp
 * @since 2005.12.12
 */

public final class StringUtil {

	/**
	 * 处理字符串
	 * 
	 * @param arg0
	 *            要处理的字符串
	 * @return 若arg0为空(null)则返回"",否则返回arg0除去前后空格之后的值
	 */

	public static String parseString(String arg0) {
		return arg0 == null ? "" : arg0.trim();
	}

	/**
	 * 处理字符串
	 * 
	 * @param arg0
	 *            要处理的字符串
	 * @return 若arg0为空(null)则返回0,否则返回arg0除去前后空格之后的值
	 */

	public static int parseInteger(String arg0) {
		String retStr = arg0 == null ? "0" : arg0.trim();
		return ch2Int(retStr);
	}

	/**
	 * 返回Integer型，如果传入的字符串为空字符串，则返回 Null
	 * 
	 * @param arg0
	 * @return
	 */
	public static Integer parseInteger2(String arg0) {
		int i = ch2Int(arg0);
		if (i == -1) {
			return null;
		} else {
			return i;
		}
	}
	/**
	 * 返回Integer型，如果传入的字符串为空字符串，则返回 Null
	 * -1在钞箱信息表中的status代表未启用
	 * @param arg0
	 * @return
	 */
	public static Integer parseInteger3(String arg0) {
		int i = ch2Int2(arg0);
		if (i == -2) {
			return null;
		} else {
			return i;
		}
	}

	/**
	 * 处理字符串
	 * 
	 * @param arg0
	 *            要处理的对象
	 * @return 若obj为空(null)则返回"",否则返回obj转换成字符串且除去该字符前后空格之后的值
	 */

	public static String parseString(Object arg0) {
		return arg0 == null ? "" : arg0.toString().trim();
	}

	/**
	 * 将字符串转换为int型整数。
	 * 
	 * @return int 整数
	 */
	public static int ch2Int(String str) {
		try {
			return (Integer.parseInt(str));
		} catch (NumberFormatException e) {
			return (-1);
		}
	}
	/**
	 * 将字符串转换为int型整数。
	 * ch2Int返回catch后返回-1,-1在钞箱信息表中的status为未启用.
	 * @return int 整数
	 */
	public static int ch2Int2(String str) {
		try {
			return (Integer.parseInt(str));
		} catch (NumberFormatException e) {
			return (-2);
		}
	}

	/**
	 * ch2Int(parseString(object))
	 * 
	 * @param object
	 * @return
	 */
	public static int objectToInt(Object object) {
		return ch2Int(parseString(object));
	}

	/**
	 * 将字符串转换为double型。
	 * 
	 * @return double 长整型
	 */
	public static double ch2Double(String str) {
		try {
			return (Double.parseDouble(str));
		} catch (NumberFormatException e) {
			return (-1);
		}
	}
	
	public static Double parseDouble(String str) {
		double d = ch2Double(str);
		if(d == -1){
			return null;
		}else{
			return d;
		}
	}

	/**
	 * 中文转码方法
	 * 
	 * @param arg0
	 *            要转换编码的字符串
	 * @return 转换编码后的字符串
	 */

	public static String toGBK(String arg0) {

		try {
			arg0 = (arg0 == null) ? "" : new String(arg0.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			// System.out.println(e);
		}
		return arg0;
	}

	/**
	 * 过滤字符串方法
	 * 
	 * @param arg0
	 *            需要过滤的字符串
	 * @param arg1
	 *            要过滤掉的字符串
	 * @return 返回过滤后的字符串
	 */
	public static String filterString(String arg0, String arg1) {

		int pos;
		String str1 = StringUtil.parseString(arg0);
		String str2 = StringUtil.parseString(arg1);
		String str = str1;

		while (str.indexOf(str2) >= 0) {
			pos = str.indexOf(str2);
			str = str.substring(0, pos) + str.substring(pos + str2.length());
		}
		return str;
	}

	/**
	 * 发送之前的预处理 将传入的byte数组内容进行zip压缩，并在前面加上长度，转化成新的byte数组返回
	 * 
	 * @param byteArray
	 *            传入的参数
	 * @param zipId
	 *            压缩方式 0：非压缩,2：标准ZIP压缩,3:gzip压缩
	 * @return byte[]：byte数组
	 */
	public static byte[] preProcess(byte[] byteArray, int zipId) {
		if (byteArray == null) {
			throw new IllegalArgumentException("preProcess 传入参数为空");
		}

		byte[] byteZip = null;

		try {
			if (zipId == 2) {
				byteZip = ZipUtil.zip(byteArray);
			} else if (zipId == 3) {
				byteZip = ZipUtil.gzip(byteArray);
			} else {
				byteZip = byteArray;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		int zipLength = byteZip.length;
		int lastLength = zipLength + 8;

		byte[] byteReturn = new byte[lastLength];

		System.arraycopy(String.format("%04d", lastLength).getBytes(), 0, byteReturn, 0, 4);

		if (zipId == 2) {
			System.arraycopy(new String("T200").getBytes(), 0, byteReturn, 4, 4);
		} else if (zipId == 3) {
			System.arraycopy(new String("T300").getBytes(), 0, byteReturn, 4, 4);
		} else {
			System.arraycopy(new String("T000").getBytes(), 0, byteReturn, 4, 4);
		}

		System.arraycopy(byteZip, 0, byteReturn, 8, zipLength); // 压缩后的数据

		return byteReturn;

	}

	/**
	 * 将HashMap转换成带","分割的string List
	 * 
	 * @param hm
	 *            要转换的HashMap
	 * @param ifkey
	 *            是否转换key: 1转换key,0转换value
	 * @return 转换后的字符串
	 */

	public static String hmtoList(HashMap hm, int ifkey) {
		StringBuffer listBuff = new StringBuffer();
		int i = 0;
		if (hm == null) {
			return null;
		}
		Iterator it = hm.keySet().iterator();
		while (it.hasNext()) {
			if (ifkey == 1) {
				listBuff.append("'").append(it.next()).append("'").append(",");
			} else {
				listBuff.append("'").append(hm.get(it.next())).append("'").append(",");
			}

			i++;
		}
		if (i > 0) {
			listBuff.deleteCharAt(listBuff.length() - 1); // 去掉最后一个'号
		}
		// System.out.println("list="+listBuff.toString());
		return listBuff.toString();
	}

	/**
	 * 字符转换ISO-8859-1 To GBK
	 * */
	public static String isoToGBK(String src) {
		try {
			if (src != null) {
				src = new String(src.getBytes("iso-8859-1"), "gbk");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return src;
	}

	/**
	 * 编码"\"
	 * */
	public static String escapea(String src) {
		char ch;
		StringBuffer tmpStr = new StringBuffer();

		for (int i = 0; i < src.length(); i++) {
			ch = src.charAt(i);
			if (ch == '\\') {
				tmpStr.append("\\").append(ch);
			} else {
				tmpStr.append(ch);
			}
		}
		return tmpStr.toString();
	}

	/**
	 * 编码"\"
	 * */
	public static String escapeb(String src) {
		char ch;
		StringBuffer tmpStr = new StringBuffer();

		for (int i = 0; i < src.length(); i++) {
			ch = src.charAt(i);
			if (ch == '/') {
				tmpStr.append("\\/").append(ch);
			} else {
				tmpStr.append(ch);
			}
		}
		return tmpStr.toString();
	}

	/**
	 * 字符串替换,替换所有的数据 content 需替换字符串(String replace) rep1 原字符串 rep2 替换后的字符串
	 * return String NULL 表示替换失败
	 * */
	public static String replaceAllForUnix(String content, String rep1, String rep2) {
		try {
			String newStr = content; // 替换后的字符串

			int index = content.lastIndexOf(rep1);

			StringBuffer rep = new StringBuffer();

			while (index >= 0) {
				rep.append(newStr.substring(0, index));

				rep.append(rep2);

				rep.append(newStr.substring(index + rep1.length(), newStr.length()));

				newStr = rep.toString();

				index = (newStr).lastIndexOf(rep1);

				rep.delete(0, rep.length());
			}

			return newStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 分割短信使用 将字符串按照规定的长度进行拆分，拆分完之后按照"|"进行分割。
	 * 
	 * @param src
	 *            需要差分的字符串
	 * @param len
	 *            拆分长度,以字节为计算(bytes)
	 * @return 分割后的字符串
	 */
	public String getSmsContent(String src, int len) {

		if (src == null || src.equals("") || len <= 5) {
			return src;
		}

		byte[] byteOfSrc = src.getBytes();

		int byteCount = byteOfSrc.length;
		int smsTimes = 0;

		StringBuffer des = new StringBuffer();

		if (byteCount > len) {
			len = len - 5;
		}

		if (byteCount % len != 0) {
			smsTimes = byteCount / len + 1;
		} else {
			smsTimes = byteCount / len;
		}

		int beginIndex = 0;
		boolean flag = true;
		int indeOfSrc = 0;
		String smsSend = null;
		for (int i = 0; i < smsTimes; i++) {
			if (smsTimes - i == 1) {
				len = byteCount - beginIndex;
				flag = false;
			}

			if (smsTimes > 1) {
				indeOfSrc = i + 1;
				des.append("(" + indeOfSrc + "/" + smsTimes + ")");
			}

			smsSend = new String(byteOfSrc, beginIndex, len).trim();

			if (src.indexOf(smsSend) == -1) {
				smsSend = new String(byteOfSrc, beginIndex, len - 1).trim();
				beginIndex = beginIndex + len - 1;
			} else {
				beginIndex = beginIndex + len;
			}
			des.append(smsSend);

			if (flag) {
				des.append("|");
			}
		}
		return des.toString();

	}

	/**
	 * 分割短信使用 将字符串按照规定的长度进行拆分，拆分完之后按照"|"进行分割。
	 * 
	 * @param src
	 *            需要差分的字符串
	 * @param len
	 *            拆分长度,以字符为计算(Char)
	 * @return 分割后的字符串
	 */
	public static String getSmsContentChar(String src, int len) {

		if (src == null || src.equals("") || len <= 5) {
			return src;
		}

		// byte[] byteOfSrc = src.getBytes();

		int byteCount = src.length();
		int smsTimes = 0;

		StringBuffer des = new StringBuffer();

		if (byteCount > len) {
			len = len - 5;
		}

		if (byteCount % len != 0) {
			smsTimes = byteCount / len + 1;
		} else {
			smsTimes = byteCount / len;
		}
		int beginIndex = 0;
		boolean flag = true;
		int indeOfSrc = 0;
		String smsSend = null;
		for (int i = 0; i < smsTimes; i++) {
			if (smsTimes - i == 1) {
				len = byteCount - beginIndex;
				flag = false;
			}
			if (smsTimes > 1) {
				indeOfSrc = i + 1;
				des.append("(" + indeOfSrc + "/" + smsTimes + ")");
			}
			// smsSend = new String(byteOfSrc, beginIndex, len).trim();
			smsSend = src.substring(beginIndex, beginIndex + len).trim();
			if (src.indexOf(smsSend) == -1) {
				// smsSend = new String(byteOfSrc, beginIndex, len - 1).trim();
				smsSend = src.substring(beginIndex, beginIndex + len - 1).trim();
				beginIndex = beginIndex + len - 1;
			} else {
				beginIndex = beginIndex + len;
			}
			des.append(smsSend);

			if (flag) {
				des.append("|");
			}
		}
		return des.toString();

	}

	/**
	 * val为null或者 val长度为0，返回false
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val) {
		if (val == null || val.length() == 0) {
			return false;
		}
		return true;
	}

	public static String collectionToHql(Collection<String> c) {
		if (c == null || c.size() == 0) {
			return null;
		}
		StringBuffer sber = new StringBuffer();
		for (Object obj : c) {
			String str = obj.toString();
			sber.append("'").append(str).append("',");
		}

		String hql = sber.toString();
		return hql.substring(0, hql.length() - 1);
	}

	public static void main(String[] args) {

		System.out.println(ch2Int("005"));
	}

}