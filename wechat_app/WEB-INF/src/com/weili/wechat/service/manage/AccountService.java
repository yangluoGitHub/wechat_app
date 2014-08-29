package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.hibernate.PublicAccount;

public interface AccountService extends IRetInfo {
	
	/*
	 *��ѯ���ںŻ�����Ϣ  
	 */
	public List qry(Map<String, Object> params);
	
	/*
	 *���ӹ��ں���Ϣ 
	 */
	 public int addAccount(PublicAccount publicAccount);
	
	 /*
	  *��id��ѯ���ں���Ϣ 
	  */
	 public PublicAccount findById(String id);
	 
	 /*
	  * �޸Ĺ��ں���Ϣ
	  */
	 public int modAccount(PublicAccount publicAccount);
	 
	 /*
	  * ɾ�����ں���Ϣ
	  */
	 public int delAccount(PublicAccount publicAccount);
	 
	 /**
	  * ��ȡ��Ȩ�޵Ĺ��ں��б�
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
