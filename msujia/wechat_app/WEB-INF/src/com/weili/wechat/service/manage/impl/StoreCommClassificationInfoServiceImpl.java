package com.weili.wechat.service.manage.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.hibernate.StoreCommClassificationInfo;
import com.weili.wechat.hibernate.StoreInfo;
import com.weili.wechat.service.manage.StoreCommClassificationInfoService;
import com.weili.wechat.vo.StoreCommClassificationInfoVO;


public class StoreCommClassificationInfoServiceImpl extends RetInfo implements StoreCommClassificationInfoService {
	
	private static Log log = LogFactory.getLog(StoreCommClassificationInfoServiceImpl.class);

	private CommonData commonData;
	public CommonData getCommonData() {
		return commonData;
	}
	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}
	
	
	public List<?> qryCommClassificationInfo(Map<String, Object> params){
		String storeId = StringUtil.parseString(params.get("storeId"));
		String id = StringUtil.parseString(params.get("id"));
		String name = StringUtil.parseString(params.get("name"));

		StringBuffer hql = new StringBuffer();
		hql.append("from StoreCommClassificationInfo as a where 1=1");

		if (storeId.length() > 0)
			hql.append(" and a.storeInfo.id ='").append(storeId).append("'");
		if (id.length() > 0)
			hql.append(" and a.id like '%").append(id).append("%'");
		if (name.length() > 0)
			hql.append(" and a.commClassification = '%").append(name).append("%' ");
		List<?> list = this.commonData.getAllResult(hql.toString());
		return list;
	}
	
	
	public int addCommClassificationInfo(StoreCommClassificationInfoVO vo){
		this.initRet();
		this.setRetMsg("添加失败!");
		log.info("开始添加！");
		
		StoreCommClassificationInfo commf = vo2_po(vo);
		this.commonData.createOrUpdateObject(commf);
		this.setRetMsg("添加成功!");
		log.info("添加成功！");
		this.setRetOK();
		return this.getRetCode();
	}
	
	public int modCommClassificationInfo(StoreCommClassificationInfoVO vo){
		this.initRet();
		this.setRetMsg("修改失败!");
		log.info("开始修改！");
		
		StoreCommClassificationInfo commf = vo2_po(vo);
		this.commonData.createOrUpdateObject(commf);
		this.setRetMsg("修改成功!");
		log.info("修改成功！");
		this.setRetOK();
		return this.getRetCode();
	}
	
	public int delCommClassificationInfo(String id) {
		this.initRet();
		this.setRetMsg("删除失败!");
		log.info("开始删除！");
		StoreCommClassificationInfo commf = (StoreCommClassificationInfo) commonData.retrieveObject(StoreCommClassificationInfo.class, id);
		commonData.deleteObject(commf);
		this.setRetMsg("删除成功!");
		log.info("删除成功！");
		this.setRetOK();
		return this.getRetCode();
	}
	
	public StoreCommClassificationInfoVO findById(String id){
		StoreCommClassificationInfo commf = (StoreCommClassificationInfo) commonData.retrieveObject(StoreCommClassificationInfo.class, id);
		return this.po2_vo(commf);
	}
	
	 private StoreCommClassificationInfo vo2_po(StoreCommClassificationInfoVO vo){
		 
		 if(vo == null){
			 return null;
		 }
		 StoreCommClassificationInfo commf = new StoreCommClassificationInfo();
		 commf.setId(vo.getId());
		 commf.setCommClassification(vo.getCommClassification());
		 StoreInfo sInfo = (StoreInfo) commonData.retrieveObject(StoreInfo.class, vo.getStoreId());
		 commf.setStoreInfo(sInfo);
		 
		 return commf;
		 
	 }
	 private StoreCommClassificationInfoVO po2_vo(StoreCommClassificationInfo commf){
		 if(commf == null){
			 return null;
		 }
		 StoreCommClassificationInfoVO vo = new StoreCommClassificationInfoVO();
		 vo.setId(commf.getId());
		 vo.setCommClassification(commf.getCommClassification());
		 vo.setStoreId(commf.getStoreInfo().getId());
		 
		 return vo;
	 }
		 
}
