package com.weili.wechatCom.menu;

/**
 * �������͵İ�ť
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class ComplexButtonAPI extends ButtonAPI {
	private ButtonAPI[] sub_button;

	public ButtonAPI[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(ButtonAPI[] sub_button) {
		this.sub_button = sub_button;
	}
}
