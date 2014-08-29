package com.weili.wechat.hibernate;

/**
 * PubaccountFunctionId entity. @author MyEclipse Persistence Tools
 */

public class PubaccountFunctionId implements java.io.Serializable {

	// Fields

	private PublicAccount publicAccount;
	private WechatFunction wechatFunction;

	// Constructors

	/** default constructor */
	public PubaccountFunctionId() {
	}

	/** full constructor */
	public PubaccountFunctionId(PublicAccount publicAccount,
			WechatFunction wechatFunction) {
		this.publicAccount = publicAccount;
		this.wechatFunction = wechatFunction;
	}

	// Property accessors

	public PublicAccount getPublicAccount() {
		return this.publicAccount;
	}

	public void setPublicAccount(PublicAccount publicAccount) {
		this.publicAccount = publicAccount;
	}

	public WechatFunction getWechatFunction() {
		return this.wechatFunction;
	}

	public void setWechatFunction(WechatFunction wechatFunction) {
		this.wechatFunction = wechatFunction;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PubaccountFunctionId))
			return false;
		PubaccountFunctionId castOther = (PubaccountFunctionId) other;

		return ((this.getPublicAccount() == castOther.getPublicAccount()) || (this
				.getPublicAccount() != null
				&& castOther.getPublicAccount() != null && this
				.getPublicAccount().equals(castOther.getPublicAccount())))
				&& ((this.getWechatFunction() == castOther.getWechatFunction()) || (this
						.getWechatFunction() != null
						&& castOther.getWechatFunction() != null && this
						.getWechatFunction().equals(
								castOther.getWechatFunction())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPublicAccount() == null ? 0 : this.getPublicAccount()
						.hashCode());
		result = 37
				* result
				+ (getWechatFunction() == null ? 0 : this.getWechatFunction()
						.hashCode());
		return result;
	}

}