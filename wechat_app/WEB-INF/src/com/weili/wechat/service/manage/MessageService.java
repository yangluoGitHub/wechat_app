package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.hibernate.WechatMessage;

public interface MessageService {

	/**
	 * ���ݿ������Ϣ
	 * 
	 * @param msg
	 * @return
	 */
	public int addMessage(WechatMessage msg);

	/**
	 * ��ѯ��Ϣ
	 * @param id
	 * @return
	 */
	public List<WechatMessage> qryMessage(Map<String, Object> params);
	
	/**
	 * ͨ�����ں����ע�߷�����Ϣ
	 * @param wechatId
	 * @param opendId
	 * @param message
	 * @return
	 */
	public String[] sendMessage(String wechatId, String openId, String message);
}
