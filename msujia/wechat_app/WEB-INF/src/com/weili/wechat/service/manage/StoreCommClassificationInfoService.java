package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.vo.StoreCommClassificationInfoVO;



public interface StoreCommClassificationInfoService extends IRetInfo {

	/*
	 * qry CommClassificationInfo 
	 * since 2015/03/28
	 */
	public List<?> qryCommClassificationInfo(Map<String, Object> params);
	
	/*
	 * add CommClassificationInfo
	 * since 2015/03/28
	 */
	public int addCommClassificationInfo(StoreCommClassificationInfoVO vo);
	/*
	 * mod CommClassificationInfo
	 * since 2015/03/28
	 */
	public int modCommClassificationInfo(StoreCommClassificationInfoVO vo);
	
	/*
	 * del CommClassificationInfo
	 * since 2015/03/28
	 */
	public int delCommClassificationInfo(String id);
	
	
	public StoreCommClassificationInfoVO findById(String id);
	
}