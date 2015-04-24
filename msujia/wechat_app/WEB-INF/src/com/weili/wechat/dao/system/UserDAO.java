//Source file: E:\\Program Files\\workspace\\\tsp\\\WEB-INF\\src\\com\\weili\\\tsp\\\dao\\system\\UserDAO.java

package com.weili.wechat.dao.system;

import com.weili.wechat.common.DAO;

import java.io.Serializable;
import java.util.List;

/**
 * 人员DAO
 */
public interface UserDAO extends DAO 
{
   
   /**
    * 查询人员
    * @param orgId - 机构编号
    * @return List
    * @roseuid 47CD5AA000FA
    */
//   public List qryUser(String orgId);
   
   /**
    * 查询人员
    * @param roleId - 角色编号
    * @return List
    * @roseuid 47CD5ACB03A9
    */
   public List qryUser(Integer roleId);
   
   /**
    * 查询人员
    * @param orgId - 机构编号
    * @param roleId - 角色编号
    * @return List
    * @roseuid 47CD5ADA032C
    */
//   public List qryUser(String orgId, Integer roleId);
   
   /**
    * 查询人员 用于同一机构判断是否重名
    * @param userName - 人员名称
    * @param orgId - 机构编号
    * @return List
    * @roseuid 47CD5ADA032C
    */
//   public List qryUser(String userName,String orgId,Integer roleId);
   
//   public List qryUser(String opNo,String orgId,String roleName);
   
   /**
    * 查询人员 用于根据角色名字,机构号模糊查询用户信息
    * @param roleName - 角色名称
    * @return List
    * @roseuid 47CD5ADA032C
    */
//   public List qryUserByName(String orgId,String roleName);
   
   /**
    * 查询人员 用于根据角色名字模糊查询用户信息
    * @param roleName - 角色名称
    * @return List
    * @roseuid 47CD5ADA032C
    */
   public List qryUserByName(String roleName);
   
//   public List qryAllUserByRole(String orgId);
   
   /**
    * 更新
    * @param obj
    * @roseuid 47C3C0400020
    */
   public void updateByHql(String updateHql);
   
   public void saveUser(Object obj);
   
   public String qryNameById(Serializable id);
}
