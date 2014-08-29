package com.weili.wechat.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.vo.User;

/**
 * 
 * ��Աservice
 * 
 */
public interface UserService extends IRetInfo {
   
	public List qryUser(Map<String, Object> paramMap);
	
   /**
    * ��ѯ��Ա
    * @param userId - ��Ա���
    * @return com.weili.wechat.vo.User
    * @roseuid 47CD5E7B002E
    */
   public User qryUserById(String userId);
   
   /**
    * ��ѯ��Ա
    * @return List
    * @roseuid 47CD5E7B0030
    */
   public List qryUser();
   
   /**
    * ����Ȩ�ޣ���ѯ��Ա
    * @param orgMap
    * @param orgId
    * @param roleId
    * @return
    */
   public List qryUser(HashMap orgMap, String orgId, String roleId);
   
   public List qryUser(HashMap orgMap, String opNo, String orgId, String roleName);
   
   /**
    * ������Ȩ�ޣ���ѯ������Ա
    * @param orgId
    * @param roleId
    * @return
    */
   public List qryUser(String orgId, String roleId);
   
   /**
    * ������Ա
    * @param user
    * @roseuid 47CD5E7B0044
    */
   public int addUser(User user);
   
   /**
    * �޸���Ա
    * @param user
    * @roseuid 47CD5E7B004F
    */
   public int modUser(User user);
   
   /**
    * ɾ����Ա
    * @param userId - ��Ա���
    * @roseuid 47CD5E7B0051
    */
   public int delUser(User user);
      
   /**
    * ��ѯ��ɫ
    * @return List
    * @roseuid 47CD5F2B0280
    */
   public List qryRole();
   
   /**
    * @param userId
    * @roseuid 47CEA91F0251
    */
   public int resetUserPasswd(User user);
   
   /**
    * @param userId
    * @param oldPasswd
    * @param passwd
    * @roseuid 47CEA92A00AB
    */
   public int modUserPasswd(User user);
   
   public Boolean checkIsExist(String userId);
   
   public List qryRole(Integer orgGradeNo);
  
   /**
    * �����û�ID����ѯ�û��� 
    * @param no
    * @return name
    */
	public String qryUserNameById(String no);
	/**
	 * autocomplete�����������Դ
	 * @throws JSONException 
	 */
	public String qryIdNameById(String no) throws JSONException;
	
	/**
	 * �����û�ID����Ȩ�޵Ĺ��ں���Ϣ
	 * @param userId
	 * @return
	 */
	public List qryUserPubAccount(String userId);
	
	/**
	 * �����û�ID����Ȩ�޵Ĺ��ں�ID
	 * @param userId
	 * @return
	 */
	public List qryUserPubAccountID(String userId);
	
	/**
	 * ��ѯ���й��ں���Ϣ
	 * @return
	 */
	public List qryAllPubAccount();
	
	/**
	 * �ڳɹ�����û�������û����ں�Ȩ��
	 * @param userId
	 * @param PaIDs
	 * @return
	 */
	public int addUserPubAccount(String userId, String[] PaIDs);
	
	/**
	 * �޸��û�֮ǰ��ɾ���û����ں�Ȩ��
	 * @param userId
	 */
	public void delUserPubAccount(String userId);

	/**
	 * ��ѯ�û����л�����Ĺ��ں�{(���ں�ԭʼID, ���ں�����)}
	 * ��ǰ�����ںų���
	 * @param userId ��¼�û��˺�
	 * @param currPubAccount �û���ǰ����Ĺ����˺�
	 */
	public List qrySwitchableUserPubAccount(String userId, String currPubAccount);
	
	
}
