package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.hibernate.WechatMedia;

public interface MediaService extends IRetInfo{
	
	/**
	 * ���ݲ�����ѯ��ý����Դ
	 * @param params
	 * @return
	 */
	public List qryMedia(Map<String, Object> params);
	
	/**
	 * ������ý����Դ
	 * @param media
	 * @return
	 */
	public int addMedia(WechatMedia media);
	
	/**
	 * �޸Ķ�ý����Դ(���ƣ�����)
	 * @param media
	 * @return
	 */
	public int modMedia(WechatMedia media);
	
	/**
	 * ɾ����ý����Դ(��ͬ�ļ�)
	 * @param media
	 * @return
	 */
	public int delMedia(WechatMedia media);
	
	public WechatMedia qryMediaById(String id);
}
