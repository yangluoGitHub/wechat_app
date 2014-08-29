package com.weili.wechatCom.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.weili.wechatCom.message.resp.Article;
import com.weili.wechatCom.message.resp.NewsMessage;
import com.weili.wechatCom.message.resp.TextMessage;
import com.weili.wechatCom.util.MessageUtil;

/**
 * ���ķ�����
 * 
 * @author liufeng
 * @date 2013-10-17
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml��ʽ����Ϣ����
		String respXml = null;
		try {
			// ����parseXml��������������Ϣ
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺ�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// �¼�����
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage.setContent("���ã���ӭ��עΪ���Ƽ������������ھ�׼���Ͷ�ţ����Ի��Ƽ���");
					// ����Ϣ����ת����xml
					respXml = MessageUtil.messageToXml(textMessage);
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO �ݲ�������
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// �¼�KEYֵ���봴���˵�ʱ��keyֵ��Ӧ
					String eventKey = requestMap.get("EventKey");
					// ����keyֵ�ж��û�����İ�ť
					if (eventKey.equals("oschina")) {
						Article article = new Article();
						article.setTitle("��Դ�й�");
						article.setDescription("��Դ�й�����������2008��8�£���Ŀǰ�й����Ŀ�Դ����������\n\n��Դ�й���Ŀ����Ϊ�й���IT������Ա�ṩһ��ȫ��ġ���ݸ��µ�����������Դ����Լ�������Դ�����ƽ̨��\n\n�������ϵĸĽ�,Ŀǰ��Դ�й������Ѿ��γ����ɿ�Դ����⡢���������Ѷ���������Ͳ��͵ȼ���Ƶ�����ݡ�");
						article.setPicUrl("");
						article.setUrl("http://weixingtest20140807.duapp.com/wechat_ser/Ch10/index.htm");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// ����ͼ����Ϣ
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("iteye")) {
						textMessage.setContent("ITeye��������2003��9�µ�JavaEye,�������������Java����Ϊ���ļ�����̳���Ѿ��𽥷�չ��Ϊ���������������������ۺ�����վ��\n\nhttp://www.iteye.com");
						respXml = MessageUtil.messageToXml(textMessage);
					}
				}
			}
			// ���û�����Ϣʱ
			else {
				textMessage.setContent("���������У��������ע��");
				respXml = MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
