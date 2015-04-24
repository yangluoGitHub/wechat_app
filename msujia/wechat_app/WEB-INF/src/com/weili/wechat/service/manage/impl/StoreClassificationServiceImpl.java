package com.weili.wechat.service.manage.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.service.manage.StoreClassificationService;


public class StoreClassificationServiceImpl extends RetInfo implements StoreClassificationService {
	
	private static Log log = LogFactory.getLog(StoreClassificationServiceImpl.class);
	private CommonData commonData;
	
	public CommonData getCommonData() {
		return commonData;
	}
	
	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}
	
	public List<?> qryStoreClassificationInfo(Map<String, Object> params){

		String id = StringUtil.parseString(params.get("id"));
		
		StringBuffer hql = new StringBuffer();
		hql.append("from StoreClassificationInfo as a where 1=1");
		if (id.length() > 0) {
			hql.append(" and a.clLevel = 2");
			hql.append(" and a.id like '%").append(id).append("%'");
		}else {
			hql.append(" and a.clLevel = 1");
		}
		List<?> list = this.commonData.getAllResult(hql.toString());
		return list;
	}
}
