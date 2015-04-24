package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.common.IRetInfo;



public interface StoreClassificationService extends IRetInfo {

	/*
	 * qry CommClassificationInfo 
	 * since 2015/03/28
	 */
	public List<?> qryStoreClassificationInfo(Map<String, Object> params);
	
}