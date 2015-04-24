//Source file: E:\\Program Files\\workspace\\\tsp\\\WEB-INF\\src\\com\\weili\\\tsp\\\dao\\system\\ButtonDAO.java

package com.weili.wechat.dao.system;

import com.weili.wechat.common.DAO;
import java.util.List;

/**
 * 按钮DAO
 */
public interface ButtonDAO extends DAO 
{
   
   /**
    * 根据角色查询按钮
    * @param roleId
    * @return List
    * @roseuid 47CCDF560222
    */
   public List qryButtonByRole(Integer roleId);
   
   /**
    * 根据菜单查询按钮
    * @param menuId
    * @return List
    * @roseuid 47CCDF990242
    */
   public List qryButtonByMenu(String menuId);
}
