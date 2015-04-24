package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.vo.StoreInfoVO;



public interface StoreInfoService extends IRetInfo {

	/*
	 * qry storeInfo 
	 * since 2015/03/28
	 */
	public List<?> qryStoreInfo(Map<String, Object> params);
	
	/*
	 * add storeInfo
	 * since 2015/03/28
	 */
	public int addStoreInfo(StoreInfoVO vo);
	/*
	 * mod storeInfo
	 * since 2015/03/28
	 */
	public int modStoreInfo(StoreInfoVO vo);
	
	/*
	 * del storeInfo
	 * since 2015/03/28
	 */
	public int delStoreInfo(String storeInfoId);
	
	/*
	 * findById
	 * since 2015/03/28
	 */
	public StoreInfoVO findById(String storeInfoId);
	
	public List<?> qryScfList(Map<String, Object> params);
	
	
	/**
	 * DWR_ÏÂÀ­¿ò²¹×ã
	 * @param devNo
	 * @return
	 * @throws JSONException
	 */
	public String qryStoreInfo(String inputStr) throws JSONException;

}