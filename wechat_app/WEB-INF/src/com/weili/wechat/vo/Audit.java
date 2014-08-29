//Source file: E:\\Program Files\\workspace\\shepherd_bh\\WEB-INF\\src\\com\\weili\\\tsp\\\vo\\Audit.java

package com.weili.wechat.vo;


public class Audit 
{
   private String userId;
   private String userName;
   private String date;
   private String types;
   private String params;
   private Module module;
   private Operate operate;
   private String oldParams;
   
   /**
    * Access method for the userId property.
    * 
    * @return   the current value of the userId property
    */
   public String getUserId() 
   {
      return userId;
   }
   
   /**
    * Sets the value of the userId property.
    * 
    * @param aUserId the new value of the userId property
    */
   public void setUserId(String aUserId) 
   {
      userId = aUserId;
   }
   
   /**
    * Access method for the userName property.
    * 
    * @return   the current value of the userName property
    */
   public String getUserName() 
   {
      return userName;
   }
   
   /**
    * Sets the value of the userName property.
    * 
    * @param aUserName the new value of the userName property
    */
   public void setUserName(String aUserName) 
   {
      userName = aUserName;
   }
   
   /**
    * Access method for the date property.
    * 
    * @return   the current value of the date property
    */
   public String getDate() 
   {
      return date;
   }
   
   /**
    * Sets the value of the date property.
    * 
    * @param aDate the new value of the date property
    */
   public void setDate(String aDate) 
   {
      date = aDate;
   }
   
   /**
    * Access method for the types property.
    * 
    * @return   the current value of the types property
    */
   public String getTypes() 
   {
      return types;
   }
   
   /**
    * Sets the value of the types property.
    * 
    * @param aTypes the new value of the types property
    */
   public void setTypes(String aTypes) 
   {
      types = aTypes;
   }
   
   /**
    * Access method for the params property.
    * 
    * @return   the current value of the params property
    */
   public String getParams() 
   {
      return params;
   }
   
   /**
    * Sets the value of the params property.
    * 
    * @param aParams the new value of the params property
    */
   public void setParams(String aParams) 
   {
      params = aParams;
   }
   
   /**
    * Access method for the module property.
    * 
    * @return   the current value of the module property
    */
   public Module getModule() 
   {
      return module;
   }
   
   /**
    * Access method for the params property.
    * 
    * @return   the current value of the params property
    */
   public String getOldParams() 
   {
      return oldParams;
   }

   /**
    * Sets the value of the params property.
    * 
    * @param aParams the new value of the params property
    */
   public void setOldParams(String oldParams) 
   {
      this.oldParams = oldParams;
   }
   
   /**
    * Sets the value of the module property.
    * 
    * @param aModule the new value of the module property
    */
   public void setModule(Module aModule) 
   {
      module = aModule;
   }
   
   /**
    * Access method for the operate property.
    * 
    * @return   the current value of the operate property
    */
   public Operate getOperate() 
   {
      return operate;
   }
   
   /**
    * Sets the value of the operate property.
    * 
    * @param aOperate the new value of the operate property
    */
   public void setOperate(Operate aOperate) 
   {
      operate = aOperate;
   }
}
