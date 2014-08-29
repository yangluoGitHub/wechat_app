package com.weili.wechat.service.system;

import com.weili.wechat.common.IRetInfo;

import java.io.Serializable;
import java.util.List;
import com.weili.wechat.vo.Role;

/**
 * ��ɫҵ���߼�
 */
public interface RoleService extends IRetInfo 
{
   
   /**
    * ���ݽ�ɫ��Ż�ȡ��ɫ
    * @param roleId
    * @return com.weili.wechat.vo.Role
    * @roseuid 47CD15F200CB
    */
   public Role qryRoleById(Integer roleId);
   
   /**
    * ��ѯ���н�ɫ
    * @return List
    * @roseuid 47CD1604000F
    */
   public List qryRole();
   
   /**
    * ��ӽ�ɫ
    * @param role
    * @roseuid 47CD16100109
    */
   public int addRole(Role role);
   
   /**
    * �޸Ľ�ɫ
    * @param role
    * @roseuid 47CD161902DE
    */
   public int modRole(Role role);
   
   /**
    * ɾ����ɫ
    * @param roleId
    * @roseuid 47CD162901E4
    */
   public int delRole(Role role);
   
   /**
    * ��ѯ���в˵�
    * @return List
    * @roseuid 47CD163D0148
    */
   public List qryMenu();
   
   /**
    * ���ݽ�ɫ��ѯ�˵�
    * @param roleId
    * @return List
    * @roseuid 47CD1646000F
    */
   public List qryMenuByRole(Integer roleId);
   
   /**
    * ��ѯ���а�ť
    * @return List
    * @roseuid 47CD164F03B9
    */
   public List qryButton();
   
   /**
    * ���ݽ�ɫ��ѯ��ť
    * @param roleId
    * @return List
    * @roseuid 47CD1658030D
    */
   public List qryButtonByRole(Integer roleId);
   
   /**
    * ���ݲ˵���ѯ��ť
    * @param menuId
    * @return List
    * @roseuid 47CD166102DE
    */
   public List qryButtonByMenu(String menuId);
   
   /**
    * У���ɫ�ĺϷ���
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
	 * �Ƿ���Ȩ���鿴&&�޸������˵ģ���������/����/�սᵥ��Ϣ
	 * @param roleNo
	 * @return
	 */
   public boolean isAlmighty (Serializable roleId);

   //���ݻ����Ų�ѯ��صĽ�ɫ�б�(role no and role name)
   public List qryRoleListByOrg(String orgNo);
   
   /**
    * ���ݽ�ɫ���жϽ�ɫ�Ƿ���ڣ��������ɫ���ظ���
	* @param roleName
	* @return
	*/
   public boolean checkIsExistByName(String roleName);
}
