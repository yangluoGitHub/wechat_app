package com.weili.wechat.hibernate;

/**
 * SysAuditId entity. @author MyEclipse Persistence Tools
 */

public class SysAuditId implements java.io.Serializable {

	// Fields

	private String userid;
	private String auditDate;

	// Constructors

	/** default constructor */
	public SysAuditId() {
	}

	/** full constructor */
	public SysAuditId(String userid, String auditDate) {
		this.userid = userid;
		this.auditDate = auditDate;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysAuditId))
			return false;
		SysAuditId castOther = (SysAuditId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getAuditDate() == castOther.getAuditDate()) || (this
						.getAuditDate() != null
						&& castOther.getAuditDate() != null && this
						.getAuditDate().equals(castOther.getAuditDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getAuditDate() == null ? 0 : this.getAuditDate().hashCode());
		return result;
	}

}