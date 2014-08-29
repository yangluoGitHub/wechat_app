//Source file: E:\\Program Files\\workspace\\\tsp\\\WEB-INF\\src\\com\\weili\\\tsp\\\vo\\Menu.java

package com.weili.wechat.vo;


/**
 * 菜单
 */
public class Menu 
{
   
   /**
    * 菜单编号
    */
   private String no;
   
   /**
    * 菜单名
    */
   private String name;
   
   /**
    * 链接
    */
   private String URL;
   
   /**
    * 层次
    */
   private Integer level;
   
   /**
    * 顺序
    */
   private Integer order;
   
   /**
    * 备注
    */
   private String note;
   
   /**
    * 下级菜单
    */
   private java.util.Set<Menu> subMenu = new java.util.HashSet<Menu>(0);
   
   /**
    * 上级菜单
    */
   private com.weili.wechat.vo.Menu superMenu;
   
   /**
    * Access method for the no property.
    * 
    * @return   the current value of the no property
    */
   public String getNo() 
   {
      return no;
   }
   
   /**
    * Sets the value of the no property.
    * 
    * @param aNo the new value of the no property
    */
   public void setNo(String aNo) 
   {
      no = aNo;
   }
   
   /**
    * Access method for the name property.
    * 
    * @return   the current value of the name property
    */
   public String getName() 
   {
      return name;
   }
   
   /**
    * Sets the value of the name property.
    * 
    * @param aName the new value of the name property
    */
   public void setName(String aName) 
   {
      name = aName;
   }
   
   /**
    * Access method for the URL property.
    * 
    * @return   the current value of the URL property
    */
   public String getURL() 
   {
      return URL;
   }
   
   /**
    * Sets the value of the URL property.
    * 
    * @param aURL the new value of the URL property
    */
   public void setURL(String aURL) 
   {
      URL = aURL;
   }
   
   /**
    * Access method for the level property.
    * 
    * @return   the current value of the level property
    */
   public Integer getLevel() 
   {
      return level;
   }
   
   /**
    * Sets the value of the level property.
    * 
    * @param aLevel the new value of the level property
    */
   public void setLevel(Integer aLevel) 
   {
      level = aLevel;
   }
   
   /**
    * Access method for the order property.
    * 
    * @return   the current value of the order property
    */
   public Integer getOrder() 
   {
      return order;
   }
   
   /**
    * Sets the value of the order property.
    * 
    * @param aOrder the new value of the order property
    */
   public void setOrder(Integer aOrder) 
   {
      order = aOrder;
   }
   
   /**
    * Access method for the note property.
    * 
    * @return   the current value of the note property
    */
   public String getNote() 
   {
      return note;
   }
   
   /**
    * Sets the value of the note property.
    * 
    * @param aNote the new value of the note property
    */
   public void setNote(String aNote) 
   {
      note = aNote;
   }
   
   /**
    * Access method for the subMenu property.
    * 
    * @return   the current value of the subMenu property
    */
   public java.util.Set<Menu> getSubMenu() 
   {
      return subMenu;
   }
   
   /**
    * Sets the value of the subMenu property.
    * 
    * @param aSubMenu the new value of the subMenu property
    */
   public void setSubMenu(java.util.Set<Menu> aSubMenu) 
   {
      subMenu = aSubMenu;
   }
   
   /**
    * Access method for the superMenu property.
    * 
    * @return   the current value of the superMenu property
    */
   public com.weili.wechat.vo.Menu getSuperMenu() 
   {
      return superMenu;
   }
   
   /**
    * Sets the value of the superMenu property.
    * 
    * @param aSuperMenu the new value of the superMenu property
    */
   public void setSuperMenu(com.weili.wechat.vo.Menu aSuperMenu) 
   {
      superMenu = aSuperMenu;
   }
}
