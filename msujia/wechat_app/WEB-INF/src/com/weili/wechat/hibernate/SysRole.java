package com.weili.wechat.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */

public class SysRole implements java.io.Serializable {

	// Fields

	private Integer no;
	private String name;
	private Integer catalog;
	private Integer orgType;
	private String note;
	private Set sysConfRoleMenus = new HashSet(0);
	private Set sysConfRoleButtons = new HashSet(0);
	private Set opTables = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** minimal constructor */
	public SysRole(Integer no, String name, Integer catalog) {
		this.no = no;
		this.name = name;
		this.catalog = catalog;
	}

	/** full constructor */
	public SysRole(Integer no, String name, Integer catalog, Integer orgType,
			String note, Set sysConfRoleMenus, Set sysConfRoleButtons,
			Set opTables) {
		this.no = no;
		this.name = name;
		this.catalog = catalog;
		this.orgType = orgType;
		this.note = note;
		this.sysConfRoleMenus = sysConfRoleMenus;
		this.sysConfRoleButtons = sysConfRoleButtons;
		this.opTables = opTables;
	}

	// Property accessors

	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCatalog() {
		return this.catalog;
	}

	public void setCatalog(Integer catalog) {
		this.catalog = catalog;
	}

	public Integer getOrgType() {
		return this.orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getSysConfRoleMenus() {
		return this.sysConfRoleMenus;
	}

	public void setSysConfRoleMenus(Set sysConfRoleMenus) {
		this.sysConfRoleMenus = sysConfRoleMenus;
	}

	public Set getSysConfRoleButtons() {
		return this.sysConfRoleButtons;
	}

	public void setSysConfRoleButtons(Set sysConfRoleButtons) {
		this.sysConfRoleButtons = sysConfRoleButtons;
	}

	public Set getOpTables() {
		return this.opTables;
	}

	public void setOpTables(Set opTables) {
		this.opTables = opTables;
	}

}