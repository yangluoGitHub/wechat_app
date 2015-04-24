//Source file: E:\\Program Files\\workspace\\\tsp\\\WEB-INF\\src\\com\\weili\\\tsp\\\dao\\system\\UserDAO.java

package com.weili.wechat.dao.system;

import com.weili.wechat.common.DAO;

import java.io.Serializable;
import java.util.List;

/**
 * ��ԱDAO
 */
public interface UserDAO extends DAO 
{
   
   /**
    * ��ѯ��Ա
    * @param orgId - �������
    * @return List
    * @roseuid 47CD5AA000FA
    */
//   public List qryUser(String orgId);
   
   /**
    * ��ѯ��Ա
    * @param roleId - ��ɫ���
    * @return List
    * @roseuid 47CD5ACB03A9
    */
   public List qryUser(Integer roleId);
   
   /**
    * ��ѯ��Ա
    * @param orgId - �������
    * @param roleId - ��ɫ���
    * @return List
    * @roseuid 47CD5ADA032C
    */
//   public List qryUser(String orgId, Integer roleId);
   
   /**
    * ��ѯ��Ա ����ͬһ�����ж��Ƿ�����
    * @param userName - ��Ա����
    * @param orgId - �������
    * @return List
    * @roseuid 47CD5ADA032C
    */
//   public List qryUser(String userName,String orgId,Integer roleId);
   
//   public List qryUser(String opNo,String orgId,String roleName);
   
   /**
    * ��ѯ��Ա ���ڸ��ݽ�ɫ����,������ģ����ѯ�û���Ϣ
    * @param roleName - ��ɫ����
    * @return List
    * @roseuid 47CD5ADA032C
    */
//   public List qryUserByName(String orgId,String roleName);
   
   /**
    * ��ѯ��Ա ���ڸ��ݽ�ɫ����ģ����ѯ�û���Ϣ
    * @param roleName - ��ɫ����
    * @return List
    * @roseuid 47CD5ADA032C
    */
   public List qryUserByName(String roleName);
   
//   public List qryAllUserByRole(String orgId);
   
   /**
    * ����
    * @param obj
    * @roseuid 47C3C0400020
    */
   public void updateByHql(String updateHql);
   
   public void saveUser(Object obj);
   
   public String qryNameById(Serializable id);
}
