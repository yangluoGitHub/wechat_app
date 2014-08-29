package com.weili.wechat.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.vo.User;

/**
 * 
 * 人员service
 * 
 */
public interface UserService extends IRetInfo {
   
	public List qryUser(Map<String, Object> paramMap);
	
   /**
    * 查询人员
    * @param userId - 人员编号
    * @return com.weili.wechat.vo.User
    * @roseuid 47CD5E7B002E
    */
   public User qryUserById(String userId);
   
   /**
    * 查询人员
    * @return List
    * @roseuid 47CD5E7B0030
    */
   public List qryUser();
   
   /**
    * 控制权限，查询人员
    * @param orgMap
    * @param orgId
    * @param roleId
    * @return
    */
   public List qryUser(HashMap orgMap, String orgId, String roleId);
   
   public List qryUser(HashMap orgMap, String opNo, String orgId, String roleName);
   
   /**
    * 不控制权限，查询所有人员
    * @param orgId
    * @param roleId
    * @return
    */
   public List qryUser(String orgId, String roleId);
   
   /**
    * 增加人员
    * @param user
    * @roseuid 47CD5E7B0044
    */
   public int addUser(User user);
   
   /**
    * 修改人员
    * @param user
    * @roseuid 47CD5E7B004F
    */
   public int modUser(User user);
   
   /**
    * 删除人员
    * @param userId - 人员编号
    * @roseuid 47CD5E7B0051
    */
   public int delUser(User user);
      
   /**
    * 查询角色
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
    * 根据用户ID，查询用户名 
    * @param no
    * @return name
    */
	public String qryUserNameById(String no);
	/**
	 * autocomplete插件的数据来源
	 * @throws JSONException 
	 */
	public String qryIdNameById(String no) throws JSONException;
	
	/**
	 * 根据用户ID查有权限的公众号信息
	 * @param userId
	 * @return
	 */
	public List qryUserPubAccount(String userId);
	
	/**
	 * 根据用户ID查有权限的公众号ID
	 * @param userId
	 * @return
	 */
	public List qryUserPubAccountID(String userId);
	
	/**
	 * 查询所有公众号信息
	 * @return
	 */
	public List qryAllPubAccount();
	
	/**
	 * 在成功添加用户后，添加用户公众号权限
	 * @param userId
	 * @param PaIDs
	 * @return
	 */
	public int addUserPubAccount(String userId, String[] PaIDs);
	
	/**
	 * 修改用户之前，删除用户公众号权限
	 * @param userId
	 */
	public void delUserPubAccount(String userId);

	/**
	 * 查询用户可切换管理的公众号{(公众号原始ID, 公众号名称)}
	 * 当前管理公众号除外
	 * @param userId 登录用户账号
	 * @param currPubAccount 用户当前管理的公众账号
	 */
	public List qrySwitchableUserPubAccount(String userId, String currPubAccount);
	
	
}
