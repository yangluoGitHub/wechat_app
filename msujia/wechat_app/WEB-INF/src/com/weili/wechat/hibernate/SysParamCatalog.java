package com.weili.wechat.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * SysParamCatalog entity. @author MyEclipse Persistence Tools
 */

public class SysParamCatalog implements java.io.Serializable {

	// Fields

	private Integer catalog;
	private String catalogName;
	private String description;
	private Set sysParams = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysParamCatalog() {
	}

	/** minimal constructor */
	public SysParamCatalog(Integer catalog, String catalogName) {
		this.catalog = catalog;
		this.catalogName = catalogName;
	}

	/** full constructor */
	public SysParamCatalog(Integer catalog, String catalogName,
			String description, Set sysParams) {
		this.catalog = catalog;
		this.catalogName = catalogName;
		this.description = description;
		this.sysParams = sysParams;
	}

	// Property accessors

	public Integer getCatalog() {
		return this.catalog;
	}

	public void setCatalog(Integer catalog) {
		this.catalog = catalog;
	}

	public String getCatalogName() {
		return this.catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getSysParams() {
		return this.sysParams;
	}

	public void setSysParams(Set sysParams) {
		this.sysParams = sysParams;
	}

}