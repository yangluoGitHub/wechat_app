package com.weili.wechat.hibernate;

/**
 * SysParam entity. @author MyEclipse Persistence Tools
 */

public class SysParam implements java.io.Serializable {

	// Fields

	private String logicId;
	private SysParamCatalog sysParamCatalog;
	private String paramName;
	private String paramValue;
	private String statement;
	private String description;

	// Constructors

	/** default constructor */
	public SysParam() {
	}

	/** minimal constructor */
	public SysParam(String logicId, SysParamCatalog sysParamCatalog,
			String paramName) {
		this.logicId = logicId;
		this.sysParamCatalog = sysParamCatalog;
		this.paramName = paramName;
	}

	/** full constructor */
	public SysParam(String logicId, SysParamCatalog sysParamCatalog,
			String paramName, String paramValue, String statement,
			String description) {
		this.logicId = logicId;
		this.sysParamCatalog = sysParamCatalog;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.statement = statement;
		this.description = description;
	}

	// Property accessors

	public String getLogicId() {
		return this.logicId;
	}

	public void setLogicId(String logicId) {
		this.logicId = logicId;
	}

	public SysParamCatalog getSysParamCatalog() {
		return this.sysParamCatalog;
	}

	public void setSysParamCatalog(SysParamCatalog sysParamCatalog) {
		this.sysParamCatalog = sysParamCatalog;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getStatement() {
		return this.statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}