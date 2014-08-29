package com.weili.wechat.hibernate;

/**
 * UserPubaccountId entity. @author MyEclipse Persistence Tools
 */

public class UserPubaccountId implements java.io.Serializable {

	// Fields

	private OpTable opTable;
	private PublicAccount publicAccount;

	// Constructors

	/** default constructor */
	public UserPubaccountId() {
	}

	/** full constructor */
	public UserPubaccountId(OpTable opTable, PublicAccount publicAccount) {
		this.opTable = opTable;
		this.publicAccount = publicAccount;
	}

	// Property accessors

	public OpTable getOpTable() {
		return this.opTable;
	}

	public void setOpTable(OpTable opTable) {
		this.opTable = opTable;
	}

	public PublicAccount getPublicAccount() {
		return this.publicAccount;
	}

	public void setPublicAccount(PublicAccount publicAccount) {
		this.publicAccount = publicAccount;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserPubaccountId))
			return false;
		UserPubaccountId castOther = (UserPubaccountId) other;

		return ((this.getOpTable() == castOther.getOpTable()) || (this
				.getOpTable() != null && castOther.getOpTable() != null && this
				.getOpTable().equals(castOther.getOpTable())))
				&& ((this.getPublicAccount() == castOther.getPublicAccount()) || (this
						.getPublicAccount() != null
						&& castOther.getPublicAccount() != null && this
						.getPublicAccount()
						.equals(castOther.getPublicAccount())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOpTable() == null ? 0 : this.getOpTable().hashCode());
		result = 37
				* result
				+ (getPublicAccount() == null ? 0 : this.getPublicAccount()
						.hashCode());
		return result;
	}

}