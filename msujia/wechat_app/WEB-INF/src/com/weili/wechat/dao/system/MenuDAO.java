//Source file: E:\\Program Files\\workspace\\tsp\\WEB-INF\\src\\com\\weili\\tsp\\dao\\system\\MenuDAO.java

package com.weili.wechat.dao.system;

import com.weili.wechat.common.DAO;
import java.util.List;
import com.weili.wechat.vo.Menu;

/**
 * 菜单DAO
 */
public interface MenuDAO extends DAO 
{
   
   /**
    * 根据角色查询菜单
    * @param roleId
    * @return List
    * @roseuid 47CCDF180196
    */
   public List qryMenuByRole(Integer roleId);
   
   /**
    * @param menuId
    * @return com.weili.wechat.vo.Menu
    * @roseuid 47CFD3B9037A
    */
   public Menu qryMenuTree(String menuId);
}
