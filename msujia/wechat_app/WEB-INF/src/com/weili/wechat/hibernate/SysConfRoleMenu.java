package com.weili.wechat.hibernate;

/**
 * SysConfRoleMenu entity. @author MyEclipse Persistence Tools
 */

public class SysConfRoleMenu implements java.io.Serializable {

	// Fields

	private SysConfRoleMenuId id;

	// Constructors

	/** default constructor */
	public SysConfRoleMenu() {
	}

	/** full constructor */
	public SysConfRoleMenu(SysConfRoleMenuId id) {
		this.id = id;
	}

	// Property accessors

	public SysConfRoleMenuId getId() {
		return this.id;
	}

	public void setId(SysConfRoleMenuId id) {
		this.id = id;
	}

}