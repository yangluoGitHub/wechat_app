package com.weili.wechat.service.manage.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.dao.system.UserDAO;
import com.weili.wechat.hibernate.StoreInfo;
import com.weili.wechat.service.manage.OrderInfoService;
import com.weili.wechat.vo.OrderInfoVO;
import com.weili.wechat.vo.StoreInfoVO;


public class OrderInfoServiceImpl extends RetInfo implements OrderInfoService {
	
	private static Log log = LogFactory.getLog(OrderInfoServiceImpl.class);

	private CommonData commonData;
	
	public CommonData getCommonData() {
		return commonData;
	}
	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}
	
	
	public List<?> qryOrderInfo(Map<String, Object> params){
		String storeId = StringUtil.parseString(params.get("storeId"));
		String storeNo = StringUtil.parseString(params.get("storeNo"));
		String storeName = StringUtil.parseString(params.get("storeName"));

		StringBuffer hql = new StringBuffer();
		hql.append("from StoreInfo as a where 1=1");

		if (storeId.length() > 0)
			hql.append(" and a.id ='").append(storeId).append("'");
		if (storeNo.length() > 0)
			hql.append(" and a.storeNo like '%").append(storeNo).append("%'");
		if (storeName.length() > 0)
			hql.append(" and a.storeName = '%").append(storeName).append("%' ");
		List<?> list = this.commonData.getAllResult(hql.toString());
		return list;
	}
	
	public int addOrderInfo(OrderInfoVO vo) {
		this.initRet();
		this.setRetMsg("添加失败!");
		log.info("开始添加!");
		
		
		this.setRetMsg("添加成功!");
		log.info("添加成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	
	public int modOrderInfo(OrderInfoVO vo){
		this.initRet();
		this.setRetMsg("修改失败!");
		log.info("开始修改！");
		
		
		
		this.setRetMsg("修改成功!");
		log.info("修改成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	public int delOrderInfo(String orderId) {
		this.initRet();
		this.setRetMsg("删除失败!");
		log.info("开始删除!");
		
		
		this.setRetMsg("删除成功!");
		log.info("删除成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	public OrderInfoVO findById(String orderId){
		return null;
	}
	
	 private StoreInfo vo2_po(StoreInfoVO vo){
		 
		 if(vo == null){
			 return null;
		 }
		 return null;
		 
	 }
	 
	 private StoreInfoVO po2_vo(StoreInfo sInfo){
		 if(sInfo == null){
			 return null;
		 }
		 return null;
	 }
	
}
