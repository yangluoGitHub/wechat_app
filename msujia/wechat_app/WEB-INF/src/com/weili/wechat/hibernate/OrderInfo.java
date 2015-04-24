package com.weili.wechat.hibernate;

/**
 * OrderInfo entity. @author MyEclipse Persistence Tools
 */

public class OrderInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String orderNo;
	private String opNo;
	private double orderTotal;
	private double deliveryCharges;
	private String phone;
	private String address;
	private Integer deliveryTime;
	private Integer status;
	private String note;

	// Constructors

	/** default constructor */
	public OrderInfo() {
	}

	/** minimal constructor */
	public OrderInfo(String id, String orderNo, String opNo, double orderTotal,
			double deliveryCharges, String phone, String address, Integer status) {
		this.id = id;
		this.orderNo = orderNo;
		this.opNo = opNo;
		this.orderTotal = orderTotal;
		this.deliveryCharges = deliveryCharges;
		this.phone = phone;
		this.address = address;
		this.status = status;
	}

	/** full constructor */
	public OrderInfo(String id, String orderNo, String opNo, double orderTotal,
			double deliveryCharges, String phone, String address,
			Integer deliveryTime, Integer status, String note) {
		this.id = id;
		this.orderNo = orderNo;
		this.opNo = opNo;
		this.orderTotal = orderTotal;
		this.deliveryCharges = deliveryCharges;
		this.phone = phone;
		this.address = address;
		this.deliveryTime = deliveryTime;
		this.status = status;
		this.note = note;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOpNo() {
		return this.opNo;
	}

	public void setOpNo(String opNo) {
		this.opNo = opNo;
	}

	public double getOrderTotal() {
		return this.orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public double getDeliveryCharges() {
		return this.deliveryCharges;
	}

	public void setDeliveryCharges(double deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}