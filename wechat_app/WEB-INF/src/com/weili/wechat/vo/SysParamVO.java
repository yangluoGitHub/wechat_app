package com.weili.wechat.vo;

public class SysParamVO {

	private String logicId;
	private Integer catalog;
	private String paramName;
	private String paramValue;
	private String statement;
	private String description;
	
	
	public String getLogicId() {
		return logicId;
	}
	public void setLogicId(String logicId) {
		this.logicId = logicId;
	}
	public Integer getCatalog() {
		return catalog;
	}
	public void setCatalog(Integer catalog) {
		this.catalog = catalog;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}