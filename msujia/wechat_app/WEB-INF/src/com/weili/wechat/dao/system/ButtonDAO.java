//Source file: E:\\Program Files\\workspace\\\tsp\\\WEB-INF\\src\\com\\weili\\\tsp\\\dao\\system\\ButtonDAO.java

package com.weili.wechat.dao.system;

import com.weili.wechat.common.DAO;
import java.util.List;

/**
 * ��ťDAO
 */
public interface ButtonDAO extends DAO 
{
   
   /**
    * ���ݽ�ɫ��ѯ��ť
    * @param roleId
    * @return List
    * @roseuid 47CCDF560222
    */
   public List qryButtonByRole(Integer roleId);
   
   /**
    * ���ݲ˵���ѯ��ť
    * @param menuId
    * @return List
    * @roseuid 47CCDF990242
    */
   public List qryButtonByMenu(String menuId);
}
