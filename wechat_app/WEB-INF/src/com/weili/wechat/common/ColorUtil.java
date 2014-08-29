package com.weili.wechat.common;

public class ColorUtil {

	/**
	 * �����ȡһ����ɫ
	 * 
	 * @return
	 */
	public static String getRandomColor() {
		String color = "";
		int randomInt;
		while (color.length() < 6) {
			randomInt = (int) (48 + Math.random() * 23);
			if (randomInt <= 57 || randomInt >= 65) {
				color += (char) randomInt;
			}
		}
		return color;
	}

	/**
	 * �����û���ż�����ɫ����
	 * 
	 * @param opNo
	 * @return
	 */
	public static String calColorByOpNo(String opNo) {
		opNo = StringUtil.parseString(opNo);
		if (opNo.length() == 0) {
			return "ffffff";
		}
		char[] opNoChars = opNo.toCharArray();
		StringBuffer opNoHex = new StringBuffer();
		for (int i = 0; i < opNoChars.length; i++) {
			opNoHex.append(Integer.toHexString((opNoChars[i] + i) % 16));
		}
		while (opNoHex.length() < 6) {
			opNoHex.append(opNoHex);
		}
		return opNoHex.substring(0, 6);
	}

}
