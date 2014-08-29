package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.hibernate.PublicAccount;

public interface AccountService extends IRetInfo {
	
	/*
	 *查询公众号基本信息  
	 */
	public List qry(Map<String, Object> params);
	
	/*
	 *增加公众号信息 
	 */
	 public int addAccount(PublicAccount publicAccount);
	
	 /*
	  *由id查询公众号信息 
	  */
	 public PublicAccount findById(String id);
	 
	 /*
	  * 修改公众号信息
	  */
	 public int modAccount(PublicAccount publicAccount);
	 
	 /*
	  * 删除公众号信息
	  */
	 public int delAccount(PublicAccount publicAccount);
	 
	 /**
	  * 获取有权限的公众号列表
	  * @return
	  */
	 public List<?> getAuthPubAccount(String opNo);
	 /**
	  * 
	  * @param wechatid
	  * @return
	  */
	 public String getLogoLocation(String wechatid);
	 
}
