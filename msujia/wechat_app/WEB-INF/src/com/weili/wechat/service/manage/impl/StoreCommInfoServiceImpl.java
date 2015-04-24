package com.weili.wechat.service.manage.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.dao.system.UserDAO;
import com.weili.wechat.hibernate.StoreCommClassificationInfo;
import com.weili.wechat.hibernate.StoreCommInfo;
import com.weili.wechat.hibernate.StoreInfo;
import com.weili.wechat.service.manage.StoreCommInfoService;
import com.weili.wechat.vo.StoreCommClassificationInfoVO;
import com.weili.wechat.vo.StoreCommInfoVO;
import com.weili.wechat.vo.StoreInfoVO;
import com.weili.wechat.vo.User;


public class StoreCommInfoServiceImpl extends RetInfo implements StoreCommInfoService {
	
	private static Log log = LogFactory.getLog(StoreCommInfoServiceImpl.class);

	private CommonData commonData;
	
	public CommonData getCommonData() {
		return commonData;
	}
	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}
	
	public List<?> qryStoreCommInfo(Map<String, Object> params){
		String storeId = StringUtil.parseString(params.get("storeId"));
		String commId = StringUtil.parseString(params.get("commId"));
		String commName = StringUtil.parseString(params.get("commName"));
		String commCfId = StringUtil.parseString(params.get("commCfId"));
		
		StringBuffer hql = new StringBuffer();
		hql.append("from StoreCommInfo as a where 1=1");

		if (storeId.length() > 0)
			hql.append(" and a.storeInfo.id ='").append(storeId).append("'");
		if (commId.length() > 0)
			hql.append(" and a.id ='").append(commId).append("'");
		if (commName.length() > 0)
			hql.append(" and a.name = '%").append(commName).append("%' ");
		if (commCfId.length() > 0)
			hql.append(" and a.storeCommClassificationInfo.id = '").append(commCfId).append("' ");
		List<?> list = this.commonData.getAllResult(hql.toString());
		return list;
	}
	
	public int addStoreCommInfo(StoreCommInfoVO vo){
		this.initRet();
		this.setRetMsg("添加失败!");
		log.info("开始添加!");
		
		//check commName(UK) is exist in this store
		if (checkCommNameIsExist(vo.getName(), vo.getStoreInfoVO().getId())) {
			this.setRetMsg("商品名称已存在!");
			log.info("商品名称已存在!");
			return this.getRetCode();
		}
		
		//add storeCommInfo
		StoreCommInfo commInfo = vo2_po(vo);
		if (commInfo == null) {
			log.info("添加失败! commInfo==null");
			return this.getRetCode();
		}
		this.commonData.createOrUpdateObject(commInfo);
		this.setRetMsg("添加成功!");
		log.info("添加成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	
	public int modStoreCommInfo(StoreCommInfoVO vo){
		this.initRet();
		this.setRetMsg("修改失败!");
		log.info("开始修改！");
		
		StoreCommInfo commInfo = vo2_po(vo);
		if (commInfo == null) {
			log.info("修改失败! commInfo==null");
			return this.getRetCode();
		}
		this.commonData.createOrUpdateObject(commInfo);
		this.setRetMsg("修改成功!");
		log.info("修改成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	
	public int delStoreCommInfo(String storeCommInfoId) {
		this.initRet();
		this.setRetMsg("删除失败!");
		log.info("开始删除!");
		StoreCommInfo commInfo = (StoreCommInfo) commonData.retrieveObject(StoreCommInfo.class, storeCommInfoId);
		commonData.deleteObject(commInfo);
		this.setRetMsg("删除成功!");
		log.info("删除成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	public StoreCommInfoVO findById(String storeCommInfoId){
		StoreCommInfo commInfo = (StoreCommInfo) commonData.retrieveObject(StoreCommInfo.class, storeCommInfoId);
		return this.po2_vo(commInfo);
	}
	
	 private StoreCommInfo vo2_po(StoreCommInfoVO vo){
		 
		 if(vo == null){
			 return null;
		 }
		 StoreCommInfo commInfo = new StoreCommInfo();
		 commInfo.setId(vo.getId());
		 
		 String storeId = vo.getStoreInfoVO().getId();
		 StoreInfo sInfo = (StoreInfo) commonData.retrieveObject(StoreInfo.class, storeId);
		 if (sInfo == null) {
			 this.setRetMsg("商品所属门店不存在，门店id["+storeId+"]");
			 return null;
		 }
		 commInfo.setStoreInfo(sInfo);
		 
		 String commClassificationId = vo.getStoreCommClassificationInfoVO().getId();
		 StoreCommClassificationInfo commCfInfo = (StoreCommClassificationInfo) commonData.retrieveObject(StoreCommClassificationInfo.class, commClassificationId);
		 if (commCfInfo == null) {
			 this.setRetMsg("商品所属分类不存在，分类id["+commClassificationId+"]");
			 return null;
		 }
		 commInfo.setStoreCommClassificationInfo(commCfInfo);
		 
		 commInfo.setName(vo.getName());
		 commInfo.setPrice(vo.getPrice());
		 commInfo.setPictureLink(vo.getPictureLink());
		 commInfo.setPreferentialPrice(vo.getPreferentialPrice());
		 commInfo.setOnhand(vo.getOnhand());
		 commInfo.setOnShelves(vo.getOnShelves());
		 
		 return commInfo;
		 
	 }
	 
	 private StoreCommInfoVO po2_vo(StoreCommInfo commInfo){
		 if(commInfo == null){
			 return null;
		 }
		 StoreCommInfoVO commInfoVO = new StoreCommInfoVO();
		 commInfoVO.setId(commInfo.getId());
		 commInfoVO.setName(commInfo.getName());
		 commInfoVO.setPrice(commInfo.getPrice());
		 commInfoVO.setPictureLink(commInfo.getPictureLink());
		 commInfoVO.setPreferentialPrice(commInfo.getPreferentialPrice());
		 commInfoVO.setOnhand(commInfo.getOnhand());
		 commInfoVO.setOnShelves(commInfo.getOnShelves());
		 
		 StoreInfoVO sInfoVO = new StoreInfoVO();
		 sInfoVO.setId(commInfo.getStoreInfo().getId());
		 sInfoVO.setStoreNo(commInfo.getStoreInfo().getStoreNo());
		 sInfoVO.setStoreName(commInfo.getStoreInfo().getStoreName());
		 
		 commInfoVO.setStoreInfoVO(sInfoVO);
		 
		 StoreCommClassificationInfoVO commCfInfoVO = new StoreCommClassificationInfoVO();
		 commCfInfoVO.setId(commInfo.getStoreCommClassificationInfo().getId());
		 commCfInfoVO.setCommClassification(commInfo.getStoreCommClassificationInfo().getCommClassification());
		 
		 commInfoVO.setStoreCommClassificationInfoVO(commCfInfoVO);
		 
		 return commInfoVO;
	 }
	 
	/*
	 *  (non-Javadoc)
	 * @see com.weili.wechat.service.manage.StoreCommInfoService#qryCommCfList(java.util.Map)
	 */
	/*
	public List<?> qryCommCfList(Map<String, Object> params) {
		
		String storeId = StringUtil.parseString(params.get("storeId"));
		StringBuffer hql = new StringBuffer();
		hql.append("from StoreCommClassificationInfo as a where 1=1 ");
		if (storeId.length() > 0)
			hql.append(" and a.storeInfo.id ='").append(storeId).append("'");
		List<?> list = this.commonData.getAllResult(hql.toString());
		return list;
	}
	*/
	 public List<?> qryCommCfList(Map<String, Object> params) {
		 String storeId = StringUtil.parseString(params.get("storeId"));
		 String SQL = "select * from STORE_COMM_CLASSIFICATION_INFO a where a.store_id='"+storeId+"'";
		 List<?> list = this.commonData.findSQL(SQL);
		 return list;
	 }
	
	private boolean checkCommNameIsExist(String commName, String storeId){
		StringBuffer hql = new StringBuffer();
		hql.append("from StoreCommInfo as a where 1=1 and a.name = '"+commName+"' and a.storeInfo.id = '"+storeId+"'");
		List<?> list = this.commonData.getAllResult(hql.toString());
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
	
}
