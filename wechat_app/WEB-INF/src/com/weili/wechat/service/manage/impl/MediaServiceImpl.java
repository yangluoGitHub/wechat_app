package com.weili.wechat.service.manage.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.StatusEnum.MediaType;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.hibernate.WechatMedia;
import com.weili.wechat.service.manage.MediaService;

public class MediaServiceImpl extends RetInfo implements MediaService {
	private static Log log = LogFactory.getLog(MediaServiceImpl.class);

	private CommonData commonData;

	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}

	public CommonData getCommonData() {
		return commonData;
	}

	public List qryMedia(Map<String, Object> params) {
		String wechatid = StringUtil.parseString(params.get("wechatid"));
		String startDate = StringUtil.parseString(params.get("startDate"));
		String endDate = StringUtil.parseString(params.get("endDate"));
		String mediaName = StringUtil.parseString(params.get("mediaName"));
		String id = StringUtil.parseString(params.get("id"));
		MediaType m = (MediaType) params.get("type");
		StringBuffer hql = new StringBuffer("from WechatMedia where 1=1 ").append(" and mediaState = '1' ");
		if (!"".equals(id)){
			hql.append("and id = '").append(id).append("' ");
		}
		if (!"".equals(wechatid)) {
			hql.append("and wechatId = '").append(wechatid).append("' ");
		}
		if (!"".equals(startDate) && !"".equals(endDate)) {
			hql.append("and createDate >= '" + startDate + "' ");
			hql.append("and createDate <= '" + endDate + "' ");
		}
		if (m != null) {
			hql.append("and mediaType = '" + m.getType() + "' ");
			hql.append("and mediaSuffix in (" + m.getSuffix() + ") ");
		}
		if (!"".equals(mediaName)) {
			hql.append("and mediaName like '%").append(mediaName).append("&' ");
		}
		hql.append("order by createDate desc ");
		List list = this.commonData.getAllResult(hql.toString());
		return list;
	}

	public int addMedia(WechatMedia media) {
		this.initRet();
		commonData.createOrUpdateObject(media);
		this.setRetOK();
		return this.getRetCode();
	}
	
	public int modMedia(WechatMedia media){
		this.initRet();
		commonData.createOrUpdateObject(media);
		this.setRetOK();
		return this.getRetCode();
	}
	
	public int delMedia(WechatMedia media){
		this.initRet();
		commonData.deleteObject(media);
		this.setRetOK();
		return this.getRetCode();
	}

	public WechatMedia qryMediaById(String id) {
		WechatMedia media = (WechatMedia) commonData.retrieveObject(WechatMedia.class, id);
		return media;
	}
	
	
}
