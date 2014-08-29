package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.hibernate.WechatMessage;

public interface MessageService {

	/**
	 * 数据库添加消息
	 * 
	 * @param msg
	 * @return
	 */
	public int addMessage(WechatMessage msg);

	/**
	 * 查询消息
	 * @param id
	 * @return
	 */
	public List<WechatMessage> qryMessage(Map<String, Object> params);
	
	/**
	 * 通过公众号向关注者发送消息
	 * @param wechatId
	 * @param opendId
	 * @param message
	 * @return
	 */
	public String[] sendMessage(String wechatId, String openId, String message);
}
