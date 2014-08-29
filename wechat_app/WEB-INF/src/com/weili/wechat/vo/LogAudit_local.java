package com.weili.wechat.vo;

public class LogAudit_local 
{
	   
	   /**
	    * 用户ID
	    */
	   private String userId;
	   
	   /**
	    * 用户名
	    */
	   private String userName;
	   
	   /**
	    * 时间
	    */
	   private String date;
	   
	   /**
	    * 详细信息
	    */
	   private String xml;
	   /**
	    * 原始对象信息
	    */
	   private String oldXml;
	   private Module module;
	   private Operate operate;
	   
	   private String note;
	   
	   public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

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
	    * Access method for the xml property.
	    * 
	    * @return   the current value of the xml property
	    */
	   public String getXml() 
	   {
	      return xml;    
	   }
	   
	   /**
	    * Sets the value of the xml property.
	    * 
	    * @param aXml the new value of the xml property
	    */
	   public void setOldXml(String aXml) 
	   {
	      oldXml = aXml;    
	   }

	   /**
	    * Access method for the xml property.
	    * 
	    * @return   the current value of the xml property
	    */
	   public String getOldXml() 
	   {
	      return oldXml;    
	   }
	   
	   /**
	    * Sets the value of the xml property.
	    * 
	    * @param aXml the new value of the xml property
	    */
	   public void setXml(String aXml) 
	   {
	      xml = aXml;    
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
