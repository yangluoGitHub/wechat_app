package com.weili.wechat.hibernate;

/**
 * SysLogId entity. @author MyEclipse Persistence Tools
 */

public class SysLogId implements java.io.Serializable {

	// Fields

	private String userid;
	private String logDate;

	// Constructors

	/** default constructor */
	public SysLogId() {
	}

	/** full constructor */
	public SysLogId(String userid, String logDate) {
		this.userid = userid;
		this.logDate = logDate;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLogDate() {
		return this.logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysLogId))
			return false;
		SysLogId castOther = (SysLogId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getLogDate() == castOther.getLogDate()) || (this
						.getLogDate() != null && castOther.getLogDate() != null && this
						.getLogDate().equals(castOther.getLogDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getLogDate() == null ? 0 : this.getLogDate().hashCode());
		return result;
	}

}