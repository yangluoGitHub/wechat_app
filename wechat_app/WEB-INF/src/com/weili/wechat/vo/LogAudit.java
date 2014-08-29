
package com.weili.wechat.vo;


/**
 * »’÷æ…Ûº∆
 */
public class LogAudit 
{
   
   public static ThreadLocal<LogAudit_local> log_local = new ThreadLocal<LogAudit_local>()
   {
	   public LogAudit_local initialValue()
	   {
		   return new LogAudit_local();
	   }
   };
   
   public String getNote() {
	return log_local.get().getNote();
}

public void setNote(String note) {
	log_local.get().setNote(note);
}

/**
    * Access method for the userId property.
    * 
    * @return   the current value of the userId property
    */
   public String getUserId() 
   {
      return log_local.get().getUserId();    
   }
   
   /**
    * Sets the value of the userId property.
    * 
    * @param aUserId the new value of the userId property
    */
   public void setUserId(String aUserId) 
   {
      log_local.get().setUserId(aUserId);    
   }
   
   /**
    * Access method for the userName property.
    * 
    * @return   the current value of the userName property
    */
   public String getUserName() 
   {
      return log_local.get().getUserName();    
   }
   
   /**
    * Sets the value of the userName property.
    * 
    * @param aUserName the new value of the userName property
    */
   public void setUserName(String aUserName) 
   {
      log_local.get().setUserName(aUserName);    
   }
   
   /**
    * Access method for the date property.
    * 
    * @return   the current value of the date property
    */
   public String getDate() 
   {
      return log_local.get().getDate();    
   }
   
   /**
    * Sets the value of the date property.
    * 
    * @param aDate the new value of the date property
    */
   public void setDate(String aDate) 
   {
	   log_local.get().setDate(aDate);    
   }
   
   /**
    * Access method for the xml property.
    * 
    * @return   the current value of the xml property
    */
   public String getXml() 
   {
      return log_local.get().getXml();    
   }
   
   /**
    * Sets the value of the xml property.
    * 
    * @param aXml the new value of the xml property
    */
   public void setXml(String aXml) 
   {
	   log_local.get().setXml(aXml);    
   }
   
   /**
    * Access method for the old_xml property.
    * 
    * @return   the current value of the old_xml property
    */
   public String getOldXml() 
   {
      return log_local.get().getOldXml();    
   }
   
   /**
    * Sets the value of the old_xml property.
    * 
    * @param aXml the new value of the old_xml property
    */
   public void setOldXml(String oldXml) 
   {
	   log_local.get().setOldXml(oldXml);    
   }
   
   /**
    * Access method for the module property.
    * 
    * @return   the current value of the module property
    */
   public Module getModule() 
   {
      return log_local.get().getModule();    
   }
   
   /**
    * Sets the value of the module property.
    * 
    * @param aModule the new value of the module property
    */
   public void setModule(Module aModule) 
   {
	   log_local.get().setModule(aModule);
   }
   
   /**
    * Access method for the operate property.
    * 
    * @return   the current value of the operate property
    */
   public Operate getOperate() 
   {
      return log_local.get().getOperate();    
   }
   
   /**
    * Sets the value of the operate property.
    * 
    * @param aOperate the new value of the operate property
    */
   public void setOperate(Operate aOperate) 
   {
	   log_local.get().setOperate(aOperate);
   }
}