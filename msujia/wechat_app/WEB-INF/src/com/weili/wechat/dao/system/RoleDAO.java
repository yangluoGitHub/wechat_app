package com.weili.wechat.dao.system;

import com.weili.wechat.common.DAO;

import java.io.Serializable;
import java.util.List;

/**
 * 角色DAO
 */
public interface RoleDAO extends DAO 
{
   
   /**
    * 获取当前角色编号
    * @return int
    * @roseuid 47CD4D3E0242
    */
   public int getRoleNo();
   
   /**
    * 根据角色名称精确查询
    * 用于判断角色是否重名
    * @param name
    * @return List
    * @roseuid 47CD4C010000
    */
   public List qryRoleByName(String name);
   
   public List getRoleNoByName(String name);
   
   public List qryRoleByOryGrade(Integer orgGradeNo);
   public List qryRoleByOryGrade1(Integer orgGradeNo);
   
   //根据查询机构等级查询角色列表（简单查询，orgNo and orgName）
   public List qryRoleListByOryGrade(Integer orgGradeNo);
   
   public List qryRoleByName(String name, Integer orgGradeNo);
   /**
	 * 是否有权力查看&&修改其他人的：验收任务单/任务单/日结单信息
	 * @param roleNo
	 * @return
	 */
   public boolean isAlmighty(Serializable roleId);
	
}
