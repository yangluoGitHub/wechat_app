package com.weili.wechatCom.menu;

/**
 * click类型的按钮
 * 
 * @author liufeng
 * @date 2013-10-14
 */
public class ClickButtonAPI extends ButtonAPI {
	private String type;
	private String key;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}