//Source file: E:\\Program Files\\workspace\\\tsp\\\WEB-INF\\src\\com\\weili\\\tsp\\\common\\DAO.java

package com.weili.wechat.common;

import java.io.Serializable;
import java.util.List;

/**
 * DAO�ӿ�
 */
public interface DAO 
{
   
   /**
    * ����ID��ѯ
    * @param id
    * @return java.lang.Object
    * @roseuid 47C3C0400003
    */
   public abstract Object qryById(Serializable id);
   
   /**
    * ��ѯ����
    * @return java.util.List
    * @roseuid 47C3C0400024
    */
   public abstract List qryAll();
   
   /**
    * ɾ��
    * @param id
    * @roseuid 47C3C0400010
    */
   public abstract void delete(Serializable id);
   
   /**
    * ����
    * @param obj
    * @roseuid 47C3C0400020
    */
   public abstract void update(Object obj);
   
   /**
    * ����
    * @param obj
    * @roseuid 47C3C0400022
    */
   public abstract void save(Object obj);
}