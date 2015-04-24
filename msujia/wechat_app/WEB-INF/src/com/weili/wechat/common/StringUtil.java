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
	 * �����ַ���
	 * 
	 * @param arg0
	 *            Ҫ������ַ���
	 * @return ��arg0Ϊ��(null)�򷵻�"",���򷵻�arg0��ȥǰ��ո�֮���ֵ
	 */

	public static String parseString(String arg0) {
		return arg0 == null ? "" : arg0.trim();
	}

	/**
	 * �����ַ���
	 * 
	 * @param arg0
	 *            Ҫ������ַ���
	 * @return ��arg0Ϊ��(null)�򷵻�0,���򷵻�arg0��ȥǰ��ո�֮���ֵ
	 */

	public static int parseInteger(String arg0) {
		String retStr = arg0 == null ? "0" : arg0.trim();
		return ch2Int(retStr);
	}

	/**
	 * ����Integer�ͣ����������ַ���Ϊ���ַ������򷵻� Null
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
	 * ����Integer�ͣ����������ַ���Ϊ���ַ������򷵻� Null
	 * -1�ڳ�����Ϣ���е�status����δ����
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
	 * �����ַ���
	 * 
	 * @param arg0
	 *            Ҫ����Ķ���
	 * @return ��objΪ��(null)�򷵻�"",���򷵻�objת�����ַ����ҳ�ȥ���ַ�ǰ��ո�֮���ֵ
	 */

	public static String parseString(Object arg0) {
		return arg0 == null ? "" : arg0.toString().trim();
	}

	/**
	 * ���ַ���ת��Ϊint��������
	 * 
	 * @return int ����
	 */
	public static int ch2Int(String str) {
		try {
			return (Integer.parseInt(str));
		} catch (NumberFormatException e) {
			return (-1);
		}
	}
	/**
	 * ���ַ���ת��Ϊint��������
	 * ch2Int����catch�󷵻�-1,-1�ڳ�����Ϣ���е�statusΪδ����.
	 * @return int ����
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
	 * ���ַ���ת��Ϊdouble�͡�
	 * 
	 * @return double ������
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
	 * ����ת�뷽��
	 * 
	 * @param arg0
	 *            Ҫת��������ַ���
	 * @return ת���������ַ���
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
	 * �����ַ�������
	 * 
	 * @param arg0
	 *            ��Ҫ���˵��ַ���
	 * @param arg1
	 *            Ҫ���˵����ַ���
	 * @return ���ع��˺���ַ���
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
	 * ����֮ǰ��Ԥ���� �������byte�������ݽ���zipѹ��������ǰ����ϳ��ȣ�ת�����µ�byte���鷵��
	 * 
	 * @param byteArray
	 *            ����Ĳ���
	 * @param zipId
	 *            ѹ����ʽ 0����ѹ��,2����׼ZIPѹ��,3:gzipѹ��
	 * @return byte[]��byte����
	 */
	public static byte[] preProcess(byte[] byteArray, int zipId) {
		if (byteArray == null) {
			throw new IllegalArgumentException("preProcess �������Ϊ��");
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

		System.arraycopy(byteZip, 0, byteReturn, 8, zipLength); // ѹ���������

		return byteReturn;

	}

	/**
	 * ��HashMapת���ɴ�","�ָ��string List
	 * 
	 * @param hm
	 *            Ҫת����HashMap
	 * @param ifkey
	 *            �Ƿ�ת��key: 1ת��key,0ת��value
	 * @return ת������ַ���
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
			listBuff.deleteCharAt(listBuff.length() - 1); // ȥ�����һ��'��
		}
		// System.out.println("list="+listBuff.toString());
		return listBuff.toString();
	}

	/**
	 * �ַ�ת��ISO-8859-1 To GBK
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
	 * ����"\"
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
	 * ����"\"
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
	 * �ַ����滻,�滻���е����� content ���滻�ַ���(String replace) rep1 ԭ�ַ��� rep2 �滻����ַ���
	 * return String NULL ��ʾ�滻ʧ��
	 * */
	public static String replaceAllForUnix(String content, String rep1, String rep2) {
		try {
			String newStr = content; // �滻����ַ���

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
	 * �ָ����ʹ�� ���ַ������չ涨�ĳ��Ƚ��в�֣������֮����"|"���зָ
	 * 
	 * @param src
	 *            ��Ҫ��ֵ��ַ���
	 * @param len
	 *            ��ֳ���,���ֽ�Ϊ����(bytes)
	 * @return �ָ����ַ���
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
	 * �ָ����ʹ�� ���ַ������չ涨�ĳ��Ƚ��в�֣������֮����"|"���зָ
	 * 
	 * @param src
	 *            ��Ҫ��ֵ��ַ���
	 * @param len
	 *            ��ֳ���,���ַ�Ϊ����(Char)
	 * @return �ָ����ַ���
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
	 * valΪnull���� val����Ϊ0������false
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