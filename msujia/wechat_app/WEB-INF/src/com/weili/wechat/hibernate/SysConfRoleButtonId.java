package com.weili.wechat.hibernate;

/**
 * SysConfRoleButtonId entity. @author MyEclipse Persistence Tools
 */

public class SysConfRoleButtonId implements java.io.Serializable {

	// Fields

	private SysRole sysRole;
	private SysButton sysButton;

	// Constructors

	/** default constructor */
	public SysConfRoleButtonId() {
	}

	/** full constructor */
	public SysConfRoleButtonId(SysRole sysRole, SysButton sysButton) {
		this.sysRole = sysRole;
		this.sysButton = sysButton;
	}

	// Property accessors

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysButton getSysButton() {
		return this.sysButton;
	}

	public void setSysButton(SysButton sysButton) {
		this.sysButton = sysButton;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysConfRoleButtonId))
			return false;
		SysConfRoleButtonId castOther = (SysConfRoleButtonId) other;

		return ((this.getSysRole() == castOther.getSysRole()) || (this
				.getSysRole() != null && castOther.getSysRole() != null && this
				.getSysRole().equals(castOther.getSysRole())))
				&& ((this.getSysButton() == castOther.getSysButton()) || (this
						.getSysButton() != null
						&& castOther.getSysButton() != null && this
						.getSysButton().equals(castOther.getSysButton())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSysRole() == null ? 0 : this.getSysRole().hashCode());
		result = 37 * result
				+ (getSysButton() == null ? 0 : this.getSysButton().hashCode());
		return result;
	}

}