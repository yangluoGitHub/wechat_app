package com.weili.wechat.service.manage.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.weili.wechat.common.CalendarUtil;
import com.weili.wechat.common.EncryptUtil;
import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.dao.system.UserDAO;
import com.weili.wechat.hibernate.OpTable;
import com.weili.wechat.hibernate.StoreClassificationInfo;
import com.weili.wechat.hibernate.StoreInfo;
import com.weili.wechat.service.manage.StoreInfoService;
import com.weili.wechat.vo.Role;
import com.weili.wechat.vo.StoreClassificationInfoVO;
import com.weili.wechat.vo.StoreInfoVO;
import com.weili.wechat.vo.User;


public class StoreInfoServiceImpl extends RetInfo implements StoreInfoService {
	
	private static Log log = LogFactory.getLog(StoreInfoServiceImpl.class);

	private CommonData commonData;
	private UserDAO userDAO;
	
	public CommonData getCommonData() {
		return commonData;
	}
	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public List<?> qryStoreInfo(Map<String, Object> params){
		String storeId = StringUtil.parseString(params.get("storeId"));
		String storeNo = StringUtil.parseString(params.get("storeNo"));
		String storeName = StringUtil.parseString(params.get("storeName"));
		
		String cfId = StringUtil.parseString(params.get("cfId"));

		StringBuffer hql = new StringBuffer();
		hql.append("from StoreInfo as a where 1=1");

		if (storeId.length() > 0)
			hql.append(" and a.id ='").append(storeId).append("'");
		if (storeNo.length() > 0)
			hql.append(" and a.storeNo like '%").append(storeNo).append("%'");
		if (storeName.length() > 0)
			hql.append(" and a.storeName = '%").append(storeName).append("%' ");
		if (cfId.length() > 0) {
			hql.append(" and (a.storeClassificationInfoBySecClassification1.id = '").append(cfId).append("' ");
			hql.append(" or a.storeClassificationInfoBySecClassification1.id like '%").append(cfId).append("%' )");
		}
		List<?> list = this.commonData.getAllResult(hql.toString());
		return list;
	}
	
	public int addStoreInfo(StoreInfoVO vo) {
		this.initRet();
		this.setRetMsg("添加失败!");
		log.info("开始添加!");
		
		//check store info is exist 
		if (checkStoreInfoIsExist(vo.getStoreNo())) {
			this.setRetMsg("门店编号已存在!");
			log.info("门店编号已存在!");
			return this.getRetCode();
		}
		
		//check user is exist
		if (checkUserIsExist(vo.getStoreNo())) {
			this.setRetMsg("门店用户已存在!");
			log.info("门店用户已存在!");
			return this.getRetCode();
		}
		//add user
	    
		
		User aUser = new User();
		aUser.setNo(vo.getStoreNo());
		aUser.setName(vo.getStoreName());
		aUser.setTelephone(vo.getPhone());
		aUser.setStatus(1);
		aUser.setPasswdExpiration(CalendarUtil.getFixedDateYYYY_MM_DD(CalendarUtil.getSysTimeYMD(), 365));//默认一年有效期
		aUser.setPasswd(EncryptUtil.MD5(vo.getPasswd()));
		aUser.setPasswdError(0);
		aUser.setOnline_flag(-1);
		aUser.setSignFlag(0);
		aUser.setStoreId(vo.getId());
		
		Role aRole = new Role();
		aRole.setNo(10002);// 管理员
		aUser.setRole(aRole);
		
		createNewUser(aUser);
		
		//add store info
		StoreInfo sInfo = vo2_po(vo);
		this.commonData.createOrUpdateObject(sInfo);
		this.setRetMsg("添加成功!");
		log.info("添加成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	
	public int modStoreInfo(StoreInfoVO vo){
		this.initRet();
		this.setRetMsg("修改失败!");
		log.info("开始修改！");
		
		StoreInfo sInfo = vo2_po(vo);
		this.commonData.createOrUpdateObject(sInfo);
		this.setRetMsg("修改成功!");
		log.info("修改成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	public int delStoreInfo(String storeInfoId) {
		this.initRet();
		this.setRetMsg("删除失败!");
		log.info("开始删除!");
		StoreInfo sInfo = (StoreInfo) commonData.retrieveObject(StoreInfo.class, storeInfoId);
		commonData.deleteObject(sInfo);
		this.setRetMsg("删除成功!");
		log.info("删除成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	public StoreInfoVO findById(String storeInfoId){
		StoreInfo sInfo = (StoreInfo) commonData.retrieveObject(StoreInfo.class, storeInfoId);
		return this.po2_vo(sInfo);
	}
	
	 private StoreInfo vo2_po(StoreInfoVO vo){
		 
		 if(vo == null){
			 return null;
		 }
		 StoreInfo sInfo = new StoreInfo();
		 sInfo.setId(vo.getId());
		 if (vo.getStoreClVO1() != null) {
			 StoreClassificationInfo scfInfo1 = new StoreClassificationInfo();
			 scfInfo1.setId(vo.getStoreClVO1().getId());
			 sInfo.setStoreClassificationInfoBySecClassification1(scfInfo1);
		 }
		 if (vo.getStoreClVO2() != null) {
			 StoreClassificationInfo scfInfo2 = new StoreClassificationInfo();
			 scfInfo2.setId(vo.getStoreClVO2().getId());
			 sInfo.setStoreClassificationInfoBySecClassification1(scfInfo2);
		 }
		 if (vo.getStoreClVO3() != null) {
			 StoreClassificationInfo scfInfo3 = new StoreClassificationInfo();
			 scfInfo3.setId(vo.getStoreClVO3().getId());
			 sInfo.setStoreClassificationInfoBySecClassification1(scfInfo3);
		 }
		 sInfo.setStoreName(vo.getStoreName());
		 sInfo.setStoreNo(vo.getStoreNo());
		 sInfo.setAddress(vo.getAddress());
		 sInfo.setBusinessHours(vo.getBusinessHours());
		 sInfo.setDeliveryArea(vo.getDeliveryArea());
		 sInfo.setDeliveryCharges(vo.getDeliveryCharges());
		 sInfo.setDeliveryTime(vo.getDeliveryTime());
		 sInfo.setFlagFallPrice(vo.getFlagFallPrice());
		 sInfo.setOnLine(vo.getOnLine());
		 sInfo.setPasswd(vo.getPasswd());
		 sInfo.setPhone(vo.getPhone());
		 sInfo.setServiceRadius(vo.getServiceRadius());
		 //add by yangluo since 2015/04/24
		 sInfo.setStoreLogo(vo.getStoreLogo());
		 if (vo.getStorePic() != null) {
			 sInfo.setStorePic(vo.getStorePic());
		 }
		 
		 //TODO store location
		 
		// sInfo.setStoreLongitude(vo.getStoreLongitude());
		 sInfo.setStoreLongitude("dsadsd");
		// sInfo.setStoreLatitude(vo.getStoreLatitude());
		 sInfo.setStoreLatitude("dsswq");
		 sInfo.setNotifySet(vo.getNotifySet());
		 
		 return sInfo;
		 
	 }
	 
	 private StoreInfoVO po2_vo(StoreInfo sInfo){
		 if(sInfo == null){
			 return null;
		 }
		 StoreInfoVO vo = new StoreInfoVO();
		 vo.setId(sInfo.getId());
		 vo.setStoreName(sInfo.getStoreName());
		 vo.setStoreNo(sInfo.getStoreNo());
		 vo.setAddress(sInfo.getAddress());
		 vo.setBusinessHours(sInfo.getBusinessHours());
		 vo.setDeliveryArea(sInfo.getDeliveryArea());
		 vo.setDeliveryCharges(sInfo.getDeliveryCharges());
		 vo.setDeliveryTime(sInfo.getDeliveryTime());
		 vo.setFlagFallPrice(sInfo.getFlagFallPrice());
		 vo.setOnLine(sInfo.getOnLine());
		 vo.setPasswd(sInfo.getPasswd());
		 vo.setPhone(sInfo.getPhone());
		 vo.setServiceRadius(sInfo.getServiceRadius());
		 
		 StoreClassificationInfo storeClInfo = sInfo.getStoreClassificationInfoBySecClassification1();
		 if (storeClInfo != null) {
			 StoreClassificationInfoVO clVO1 = new StoreClassificationInfoVO();
			 clVO1.setId(storeClInfo.getId());
			 clVO1.setClLevel(storeClInfo.getClLevel());
			 clVO1.setClName(storeClInfo.getClName());
			 vo.setStoreClVO1(clVO1);
		 }
		 storeClInfo = sInfo.getStoreClassificationInfoBySecClassification2();
		 if (storeClInfo != null) {
			 StoreClassificationInfoVO clVO2 = new StoreClassificationInfoVO();
			 clVO2.setId(storeClInfo.getId());
			 clVO2.setClLevel(storeClInfo.getClLevel());
			 clVO2.setClName(storeClInfo.getClName());
			 vo.setStoreClVO2(clVO2);
		 }
		 storeClInfo = sInfo.getStoreClassificationInfoBySecClassification3();
		 if (storeClInfo != null) {
			 StoreClassificationInfoVO clVO3 = new StoreClassificationInfoVO();
			 clVO3.setId(storeClInfo.getId());
			 clVO3.setClLevel(storeClInfo.getClLevel());
			 clVO3.setClName(storeClInfo.getClName());
			 vo.setStoreClVO3(clVO3);
		 }
		 
		 vo.setStoreLogo(sInfo.getStoreLogo());
		 vo.setStoreLongitude(sInfo.getStoreLongitude());
		 vo.setStoreLatitude(sInfo.getStoreLatitude());
		 vo.setStorePic(sInfo.getStorePic());
		 vo.setNotifySet(sInfo.getNotifySet());
		 return vo;
	 }
	 
	public List<?> qryScfList(Map<String, Object> params) {
		StringBuffer hql = new StringBuffer();
		hql.append("from StoreClassificationInfo as a where 1=1 and a.clLevel = 2");
		List<?> list = this.commonData.getAllResult(hql.toString());
		return list;
	}
	
	private boolean checkUserIsExist(String id){
		return this.commonData.isExist(OpTable.class, id);
	}
	
	private boolean checkStoreInfoIsExist(String storeNo){
		StringBuffer hql = new StringBuffer();
		hql.append("from StoreInfo as a where 1=1 and a.storeNo = '"+storeNo+"'");
		List<?> list = this.commonData.getAllResult(hql.toString());
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
	
	private void createNewUser(User user){
		this.getUserDAO().saveUser(user);
	}
	
	public String qryStoreInfo(String inputStr) throws JSONException{
		String hql = "select t.id, t.storeName from StoreInfo t where t.storeName like '%"+inputStr+"%'";
		// makeup
		List resultList = commonData.getAllResult(hql);
		JSONArray jsonArray = new JSONArray();
		for (Object obj : resultList) {
			Object[] idName = (Object[])obj;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", idName[0].toString());
			jsonObject.put("label", idName[1].toString());
			jsonArray.put(jsonObject);
		}
		return jsonArray.toString();
	}
		 
}
