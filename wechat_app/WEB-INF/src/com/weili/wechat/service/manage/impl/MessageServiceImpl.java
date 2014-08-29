package com.weili.wechat.service.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.weili.wechat.common.CalendarUtil;
import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.StatusEnum.MessageType;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.hibernate.WechatMessage;
import com.weili.wechat.service.MessageManager;
import com.weili.wechat.service.manage.MessageService;

public class MessageServiceImpl extends RetInfo implements MessageService {
	private MessageManager messageManager;
	private CommonData commonData;

	public MessageManager getMessageManager() {
		return messageManager;
	}

	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}

	public CommonData getCommonData() {
		return commonData;
	}

	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}

	@Override
	public int addMessage(WechatMessage msg) {
		this.setRetMsg("添加微信消息失败");
		this.initRet();
		commonData.createOrUpdateObject(msg);
		this.setRetMsg("添加微信消息成功");
		this.setRetOK();
		return this.getRetCode();
	}

	@Override
	public List<WechatMessage> qryMessage(Map<String, Object> params) {
		String wechatId = StringUtil.parseString(params.get("wechatId"));
		String openId = StringUtil.parseString(params.get("openId"));
		Integer messageMode = StringUtil.objectToInt(params.get("messageMode"));
		String endDate = StringUtil.parseString(params.get("createDate"));
		String endTime = StringUtil.parseString(params.get("createTime"));
		String certainDate = StringUtil.parseString(params.get("certainDate"));
		StringBuffer hql = new StringBuffer("from WechatMessage where 1=1 ").append("and wechatId = '" + wechatId + "' ");
		if (messageMode != -1) {
			hql.append("and messageMode = " + messageMode + " ");
		}
		// 日期+时间：查询此刻之后的消息
		if (!endDate.equals("") && !endTime.equals("")) {
			hql.append("and to_date(createDate||' '||createTime, 'yyyy-mm-dd hh24:mi:ss')>");
			hql.append("to_date('" + endDate + "' '" + endTime + "', 'yyyy-mm-dd hh24:mi:ss') ");
		}
		// 查询特定日期
		if (!certainDate.equals("")) {
			hql.append("and createDate = '" + certainDate + "' ");
		}
		if (!openId.equals("")) {
			hql.append("and openId = '" + openId + "' ");
		}
		hql.append("order by createDate desc, createTime desc ");
		@SuppressWarnings("unchecked")
		List<WechatMessage> list = commonData.getAllResult(hql.toString());
		return list;
	}

	@Override
	public String[] sendMessage(String wechatId, String openId, String message) {
		WechatMessage sendMsg = new WechatMessage();
		sendMsg.setId(UUID.randomUUID().toString());
		sendMsg.setWechatId(wechatId);
		sendMsg.setOpenId(openId);
		String date = CalendarUtil.getSysTimeYMD();
		String time = CalendarUtil.getSysTimeHMS();
		sendMsg.setCreateDate(date);
		sendMsg.setCreateTime(time);
		sendMsg.setMessageMode(1);
		String messageType = MessageType.TEXT.getType();
		sendMsg.setMessageType(messageType);
		sendMsg.setContent(message);
		// 获得48小时内该openid发送的请求数,如果为0，则不能发送消息
		if (qryRequestCountInFixedDay(wechatId, openId, -2) == 0) {
			return new String[] { "fail", "48小时内无用户请求，无法发送消息" };
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fromuser", wechatId);
		params.put("touser", openId);
		params.put("msgtype", messageType);
		params.put("content", message);
//		Map<String, String> retMap = new HashMap<String, String>();
//		retMap.put("errcode", "0");
		Map<String, String> retMap = messageManager.sendMsg(params);
		String errcode = retMap.get("errcode");
		String errmsg = retMap.get("errmsg");
		// 发送成功 = 0
		if (errcode.equals("0")) {
			this.addMessage(sendMsg);
			return new String[] { "success", date + " " + time };
		} else {
			return new String[] { "fail", errmsg };
		}
	}

	private int qryRequestCountInFixedDay(String wechatId, String openId, int fixedDay) {
		String date = CalendarUtil.getFixedDate(fixedDay);
		String time = CalendarUtil.getSysTimeHMS();
		String hql = "from WechatMessage w where wechatId = '" + wechatId + "' " + "and openId = '" + openId + "' and messageMode = 0 "
				+ "and to_date(w.createDate||w.createTime, 'yyyy-mm-dd hh24:mi:ss') > " + "to_date('" + date + time + "', 'yyyy-mm-dd hh24:mi:ss')";
		List<?> list = commonData.getAllResult(hql);
		if (list == null) {
			return 0;
		}
		return list.size();
	}

}
