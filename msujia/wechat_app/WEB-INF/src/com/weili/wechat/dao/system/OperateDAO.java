//Source file: E:\\Program Files\\workspace\\shepherd_bh\\WEB-INF\\src\\com\\weili\\\tsp\\\dao\\system\\MethodDAO.java

package com.weili.wechat.dao.system;

import com.weili.wechat.common.DAO;
import java.util.List;

public interface OperateDAO extends DAO 
{
   
   /**
    * @param moduleId
    * @return List
    * @roseuid 47DE0F7F00A3
    */
   public List qryOperateByModule(String moduleId);
}
