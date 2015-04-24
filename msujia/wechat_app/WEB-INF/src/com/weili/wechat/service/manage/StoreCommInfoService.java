package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.vo.StoreCommInfoVO;
import com.weili.wechat.vo.StoreInfoVO;



public interface StoreCommInfoService extends IRetInfo {

	/*
	 * qry storeInfo 
	 * since 2015/03/28
	 */
	public List<?> qryStoreCommInfo(Map<String, Object> params);
	
	/*
	 * add storeInfo
	 * since 2015/03/28
	 */
	public int addStoreCommInfo(StoreCommInfoVO vo);
	/*
	 * mod storeInfo
	 * since 2015/03/28
	 */
	public int modStoreCommInfo(StoreCommInfoVO vo);
	
	/*
	 * del storeInfo
	 * since 2015/03/28
	 */
	public int delStoreCommInfo(String storeCommInfoId);
	
	/*
	 * findById
	 * since 2015/03/28
	 */
	public StoreCommInfoVO findById(String storeCommInfoId);
	
	public List<?> qryCommCfList(Map<String, Object> params);

}