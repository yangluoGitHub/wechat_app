package com.weili.wechat.hibernate;

/**
 * PubaccountFunction entity. @author MyEclipse Persistence Tools
 */

public class PubaccountFunction implements java.io.Serializable {

	// Fields

	private PubaccountFunctionId id;

	// Constructors

	/** default constructor */
	public PubaccountFunction() {
	}

	/** full constructor */
	public PubaccountFunction(PubaccountFunctionId id) {
		this.id = id;
	}

	// Property accessors

	public PubaccountFunctionId getId() {
		return this.id;
	}

	public void setId(PubaccountFunctionId id) {
		this.id = id;
	}

}