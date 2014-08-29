package com.weili.wechat.hibernate;

/**
 * SysConfRoleMenuId entity. @author MyEclipse Persistence Tools
 */

public class SysConfRoleMenuId implements java.io.Serializable {

	// Fields

	private SysRole sysRole;
	private SysMenu sysMenu;

	// Constructors

	/** default constructor */
	public SysConfRoleMenuId() {
	}

	/** full constructor */
	public SysConfRoleMenuId(SysRole sysRole, SysMenu sysMenu) {
		this.sysRole = sysRole;
		this.sysMenu = sysMenu;
	}

	// Property accessors

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysMenu getSysMenu() {
		return this.sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysConfRoleMenuId))
			return false;
		SysConfRoleMenuId castOther = (SysConfRoleMenuId) other;

		return ((this.getSysRole() == castOther.getSysRole()) || (this
				.getSysRole() != null && castOther.getSysRole() != null && this
				.getSysRole().equals(castOther.getSysRole())))
				&& ((this.getSysMenu() == castOther.getSysMenu()) || (this
						.getSysMenu() != null && castOther.getSysMenu() != null && this
						.getSysMenu().equals(castOther.getSysMenu())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSysRole() == null ? 0 : this.getSysRole().hashCode());
		result = 37 * result
				+ (getSysMenu() == null ? 0 : this.getSysMenu().hashCode());
		return result;
	}

}