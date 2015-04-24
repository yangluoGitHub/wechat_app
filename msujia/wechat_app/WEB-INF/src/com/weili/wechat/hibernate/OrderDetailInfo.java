package com.weili.wechat.hibernate;

/**
 * OrderDetailInfo entity. @author MyEclipse Persistence Tools
 */

public class OrderDetailInfo implements java.io.Serializable {

	// Fields

	private OrderDetailInfoId id;
	private Integer num;
	private double subTotal;

	// Constructors

	/** default constructor */
	public OrderDetailInfo() {
	}

	/** full constructor */
	public OrderDetailInfo(OrderDetailInfoId id, Integer num, double subTotal) {
		this.id = id;
		this.num = num;
		this.subTotal = subTotal;
	}

	// Property accessors

	public OrderDetailInfoId getId() {
		return this.id;
	}

	public void setId(OrderDetailInfoId id) {
		this.id = id;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public double getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

}