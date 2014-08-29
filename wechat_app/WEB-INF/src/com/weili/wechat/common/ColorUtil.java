package com.weili.wechat.common;

public class ColorUtil {

	/**
	 * 随机获取一种颜色
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
	 * 根据用户编号计算颜色代码
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
