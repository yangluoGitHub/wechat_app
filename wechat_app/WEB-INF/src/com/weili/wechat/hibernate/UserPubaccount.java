package com.weili.wechat.hibernate;

/**
 * UserPubaccount entity. @author MyEclipse Persistence Tools
 */

public class UserPubaccount implements java.io.Serializable {

	// Fields

	private UserPubaccountId id;

	// Constructors

	/** default constructor */
	public UserPubaccount() {
	}

	/** full constructor */
	public UserPubaccount(UserPubaccountId id) {
		this.id = id;
	}

	// Property accessors

	public UserPubaccountId getId() {
		return this.id;
	}

	public void setId(UserPubaccountId id) {
		this.id = id;
	}

}