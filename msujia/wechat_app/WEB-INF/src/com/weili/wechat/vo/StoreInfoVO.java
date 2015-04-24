package com.weili.wechat.vo;


public class StoreInfoVO {
	
	private String id;
	private StoreClassificationInfoVO storeClVO1;
	private StoreClassificationInfoVO storeClVO2;
	private StoreClassificationInfoVO storeClVO3;
	private String storeNo;
	private String storeName;
	private String phone;
	private String address;
	private String passwd;
	private String businessHours;
	private String serviceRadius;
	private String deliveryArea;
	private Integer deliveryCharges;
	private Integer flagFallPrice;
	private Integer deliveryTime;
	private Integer onLine;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public StoreClassificationInfoVO getStoreClVO1() {
		return storeClVO1;
	}
	public void setStoreClVO1(StoreClassificationInfoVO storeClVO1) {
		this.storeClVO1 = storeClVO1;
	}
	public StoreClassificationInfoVO getStoreClVO2() {
		return storeClVO2;
	}
	public void setStoreClVO2(StoreClassificationInfoVO storeClVO2) {
		this.storeClVO2 = storeClVO2;
	}
	public StoreClassificationInfoVO getStoreClVO3() {
		return storeClVO3;
	}
	public void setStoreClVO3(StoreClassificationInfoVO storeClVO3) {
		this.storeClVO3 = storeClVO3;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getBusinessHours() {
		return businessHours;
	}
	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}
	public String getServiceRadius() {
		return serviceRadius;
	}
	public void setServiceRadius(String serviceRadius) {
		this.serviceRadius = serviceRadius;
	}
	public String getDeliveryArea() {
		return deliveryArea;
	}
	public void setDeliveryArea(String deliveryArea) {
		this.deliveryArea = deliveryArea;
	}
	public Integer getDeliveryCharges() {
		return deliveryCharges;
	}
	public void setDeliveryCharges(Integer deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}
	public Integer getFlagFallPrice() {
		return flagFallPrice;
	}
	public void setFlagFallPrice(Integer flagFallPrice) {
		this.flagFallPrice = flagFallPrice;
	}
	public Integer getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Integer getOnLine() {
		return onLine;
	}
	public void setOnLine(Integer onLine) {
		this.onLine = onLine;
	}
}