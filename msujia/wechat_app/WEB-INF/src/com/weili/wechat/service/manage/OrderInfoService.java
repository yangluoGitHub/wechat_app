package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.vo.OrderInfoVO;



public interface OrderInfoService extends IRetInfo {

	/*
	 * qry orderInfo 
	 * since 2015/03/28
	 */
	public List<?> qryOrderInfo(Map<String, Object> params);
	
	/*
	 * add orderInfo
	 * since 2015/03/28
	 */
	public int addOrderInfo(OrderInfoVO vo);
	/*
	 * mod orderInfo
	 * since 2015/03/28
	 */
	public int modOrderInfo(OrderInfoVO vo);
	
	/*
	 * del orderInfo
	 * since 2015/03/28
	 */
	public int delOrderInfo(String orderId);
	
	/*
	 * findById
	 * since 2015/03/28
	 */
	public OrderInfoVO findById(String orderId);
	

}