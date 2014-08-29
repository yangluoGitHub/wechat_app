package com.weili.wechat.web.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.weili.wechat.common.CalendarUtil;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.hibernate.WechatMessage;
import com.weili.wechat.service.manage.MessageService;

public class MessageController extends MultiActionController {

	private static Log log = LogFactory.getLog(MessageController.class);
	private MessageService messageService;

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public ModelAndView qry(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if ("".equals(wechatid)) {
				wechatid = userSession.getWechatId();
			}
			if (wechatid == null) {
				return new ModelAndView("info", "message", "用户没有查看公共号信息权限");
			}
			String openId = StringUtil.parseString(request.getParameter("openId"));
			String timeType = StringUtil.parseString(request.getParameter("timeType"));
			Map<String, Object> params = new HashMap<String, Object>();
			if (timeType.equals("today")) {
				params.put("certainDate", CalendarUtil.getSysTimeYMD());
			}
			if (timeType.equals("yesterday")) {
				params.put("certainDate", CalendarUtil.getFixedDate(-1));
			}
			params.put("openId", openId);
			params.put("wechatId", wechatid);
			params.put("messageMode", 0);
			List<WechatMessage> msgList = messageService.qryMessage(params);
			
			return new ModelAndView("manage/message_qry").addObject("msgList",msgList)
					.addObject("openId", openId).addObject("timeType", timeType);
		} catch (Exception e) {
			log.error("查询消息异常：" + e.getMessage());
			return new ModelAndView("info", "message", "查询消息异常!");
		}
	}
	
	public ModelAndView sessionPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if ("".equals(wechatid)) {
				wechatid = userSession.getWechatId();
			}
			if (wechatid == null) {
				return new ModelAndView("info", "message", "用户没有查看公共号信息权限");
			}
			String openId = StringUtil.parseString(request.getParameter("openId"));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatId", wechatid);
			params.put("openId", openId);
			List<WechatMessage> msgList = messageService.qryMessage(params);
			
			return new ModelAndView("manage/message_session").addObject("msgList",msgList)
					.addObject("openId",openId);
		}catch (Exception e) {
			log.error("查询会话异常：" + e.getMessage());
			return new ModelAndView("info", "message", "查询会话异常!");
		}
	}
	
	public ModelAndView reply(HttpServletRequest request, HttpServletResponse response) {
		try {
			String sentence = StringUtil.parseString(request.getParameter("s"));
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("openId", "test");
			paramMap.put("wechatId", "gh_217235c45d02");
			paramMap.put("msgType", "text");
			paramMap.put("content", sentence);
//			Map<String, Object> retMap = this.messageReply.service(paramMap);
//			String answer = (String) retMap.get("content");
			String answer = "";
			return new ModelAndView("info", "message", answer);
		} catch (Exception e) {
			return null;
		}
	}

}