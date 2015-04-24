package com.weili.wechat.hibernate;

/**
 * SysConfRoleButton entity. @author MyEclipse Persistence Tools
 */

public class SysConfRoleButton implements java.io.Serializable {

	// Fields

	private SysConfRoleButtonId id;

	// Constructors

	/** default constructor */
	public SysConfRoleButton() {
	}

	/** full constructor */
	public SysConfRoleButton(SysConfRoleButtonId id) {
		this.id = id;
	}

	// Property accessors

	public SysConfRoleButtonId getId() {
		return this.id;
	}

	public void setId(SysConfRoleButtonId id) {
		this.id = id;
	}

}