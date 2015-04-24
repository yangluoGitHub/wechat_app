package com.weili.wechat.dao.system;

import com.weili.wechat.common.DAO;

import java.io.Serializable;
import java.util.List;

/**
 * ��ɫDAO
 */
public interface RoleDAO extends DAO 
{
   
   /**
    * ��ȡ��ǰ��ɫ���
    * @return int
    * @roseuid 47CD4D3E0242
    */
   public int getRoleNo();
   
   /**
    * ���ݽ�ɫ���ƾ�ȷ��ѯ
    * �����жϽ�ɫ�Ƿ�����
    * @param name
    * @return List
    * @roseuid 47CD4C010000
    */
   public List qryRoleByName(String name);
   
   public List getRoleNoByName(String name);
   
   public List qryRoleByOryGrade(Integer orgGradeNo);
   public List qryRoleByOryGrade1(Integer orgGradeNo);
   
   //���ݲ�ѯ�����ȼ���ѯ��ɫ�б��򵥲�ѯ��orgNo and orgName��
   public List qryRoleListByOryGrade(Integer orgGradeNo);
   
   public List qryRoleByName(String name, Integer orgGradeNo);
   /**
	 * �Ƿ���Ȩ���鿴&&�޸������˵ģ���������/����/�սᵥ��Ϣ
	 * @param roleNo
	 * @return
	 */
   public boolean isAlmighty(Serializable roleId);
	
}
