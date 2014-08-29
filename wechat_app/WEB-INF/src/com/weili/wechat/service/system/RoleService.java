package com.weili.wechat.service.system;

import com.weili.wechat.common.IRetInfo;

import java.io.Serializable;
import java.util.List;
import com.weili.wechat.vo.Role;

/**
 * 角色业务逻辑
 */
public interface RoleService extends IRetInfo 
{
   
   /**
    * 根据角色编号获取角色
    * @param roleId
    * @return com.weili.wechat.vo.Role
    * @roseuid 47CD15F200CB
    */
   public Role qryRoleById(Integer roleId);
   
   /**
    * 查询所有角色
    * @return List
    * @roseuid 47CD1604000F
    */
   public List qryRole();
   
   /**
    * 添加角色
    * @param role
    * @roseuid 47CD16100109
    */
   public int addRole(Role role);
   
   /**
    * 修改角色
    * @param role
    * @roseuid 47CD161902DE
    */
   public int modRole(Role role);
   
   /**
    * 删除角色
    * @param roleId
    * @roseuid 47CD162901E4
    */
   public int delRole(Role role);
   
   /**
    * 查询所有菜单
    * @return List
    * @roseuid 47CD163D0148
    */
   public List qryMenu();
   
   /**
    * 根据角色查询菜单
    * @param roleId
    * @return List
    * @roseuid 47CD1646000F
    */
   public List qryMenuByRole(Integer roleId);
   
   /**
    * 查询所有按钮
    * @return List
    * @roseuid 47CD164F03B9
    */
   public List qryButton();
   
   /**
    * 根据角色查询按钮
    * @param roleId
    * @return List
    * @roseuid 47CD1658030D
    */
   public List qryButtonByRole(Integer roleId);
   
   /**
    * 根据菜单查询按钮
    * @param menuId
    * @return List
    * @roseuid 47CD166102DE
    */
   public List qryButtonByMenu(String menuId);
   
   /**
    * 校验角色的合法性
    * @param roleId,roleName
    * @return boolean
    * @roseuid 47CD0FE802FD
    */
   public boolean checkRole(String roleId,String roleName);
   public List qryRole(Integer orgGradeNo);
   
   public List qryGradeNoByAthor(Integer orgGradeNo);
   public List qryRole1(Integer orgGradeNo) ;
   public List qryRole2(String orgNo);
   /**
	 * 是否有权力查看&&修改其他人的：验收任务单/任务单/日结单信息
	 * @param roleNo
	 * @return
	 */
   public boolean isAlmighty (Serializable roleId);

   //根据机构号查询相关的角色列表(role no and role name)
   public List qryRoleListByOrg(String orgNo);
   
   /**
    * 根据角色名判断角色是否存在（不允许角色名重复）
	* @param roleName
	* @return
	*/
   public boolean checkIsExistByName(String roleName);
}
