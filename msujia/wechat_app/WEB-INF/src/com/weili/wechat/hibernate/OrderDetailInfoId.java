package com.weili.wechat.hibernate;

/**
 * OrderDetailInfoId entity. @author MyEclipse Persistence Tools
 */

public class OrderDetailInfoId implements java.io.Serializable {

	// Fields

	private String orderId;
	private StoreCommInfo storeCommInfo;

	// Constructors

	/** default constructor */
	public OrderDetailInfoId() {
	}

	/** full constructor */
	public OrderDetailInfoId(String orderId, StoreCommInfo storeCommInfo) {
		this.orderId = orderId;
		this.storeCommInfo = storeCommInfo;
	}

	// Property accessors

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public StoreCommInfo getStoreCommInfo() {
		return this.storeCommInfo;
	}

	public void setStoreCommInfo(StoreCommInfo storeCommInfo) {
		this.storeCommInfo = storeCommInfo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrderDetailInfoId))
			return false;
		OrderDetailInfoId castOther = (OrderDetailInfoId) other;

		return ((this.getOrderId() == castOther.getOrderId()) || (this
				.getOrderId() != null && castOther.getOrderId() != null && this
				.getOrderId().equals(castOther.getOrderId())))
				&& ((this.getStoreCommInfo() == castOther.getStoreCommInfo()) || (this
						.getStoreCommInfo() != null
						&& castOther.getStoreCommInfo() != null && this
						.getStoreCommInfo()
						.equals(castOther.getStoreCommInfo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrderId() == null ? 0 : this.getOrderId().hashCode());
		result = 37
				* result
				+ (getStoreCommInfo() == null ? 0 : this.getStoreCommInfo()
						.hashCode());
		return result;
	}

}