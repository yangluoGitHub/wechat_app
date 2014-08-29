package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.hibernate.WechatMedia;

public interface MediaService extends IRetInfo{
	
	/**
	 * 根据参数查询多媒体资源
	 * @param params
	 * @return
	 */
	public List qryMedia(Map<String, Object> params);
	
	/**
	 * 新增多媒体资源
	 * @param media
	 * @return
	 */
	public int addMedia(WechatMedia media);
	
	/**
	 * 修改多媒体资源(名称，描述)
	 * @param media
	 * @return
	 */
	public int modMedia(WechatMedia media);
	
	/**
	 * 删除多媒体资源(连同文件)
	 * @param media
	 * @return
	 */
	public int delMedia(WechatMedia media);
	
	public WechatMedia qryMediaById(String id);
}
