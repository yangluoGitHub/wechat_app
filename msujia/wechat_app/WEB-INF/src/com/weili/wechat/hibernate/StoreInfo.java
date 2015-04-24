package com.weili.wechat.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * StoreInfo entity. @author MyEclipse Persistence Tools
 */

public class StoreInfo implements java.io.Serializable {

	// Fields

	private String id;
	private StoreClassificationInfo storeClassificationInfoBySecClassification1;
	private StoreClassificationInfo storeClassificationInfoBySecClassification3;
	private StoreClassificationInfo storeClassificationInfoBySecClassification2;
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
	private Set storeCommInfos = new HashSet(0);
	private Set storeCommClassificationInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public StoreInfo() {
	}

	/** minimal constructor */
	public StoreInfo(
			String id,
			StoreClassificationInfo storeClassificationInfoBySecClassification1,
			String storeNo, String storeName, String phone, String address,
			String passwd, String businessHours, String serviceRadius,
			String deliveryArea, Integer deliveryCharges,
			Integer flagFallPrice, Integer deliveryTime, Integer onLine) {
		this.id = id;
		this.storeClassificationInfoBySecClassification1 = storeClassificationInfoBySecClassification1;
		this.storeNo = storeNo;
		this.storeName = storeName;
		this.phone = phone;
		this.address = address;
		this.passwd = passwd;
		this.businessHours = businessHours;
		this.serviceRadius = serviceRadius;
		this.deliveryArea = deliveryArea;
		this.deliveryCharges = deliveryCharges;
		this.flagFallPrice = flagFallPrice;
		this.deliveryTime = deliveryTime;
		this.onLine = onLine;
	}

	/** full constructor */
	public StoreInfo(
			String id,
			StoreClassificationInfo storeClassificationInfoBySecClassification1,
			StoreClassificationInfo storeClassificationInfoBySecClassification3,
			StoreClassificationInfo storeClassificationInfoBySecClassification2,
			String storeNo, String storeName, String phone, String address,
			String passwd, String businessHours, String serviceRadius,
			String deliveryArea, Integer deliveryCharges,
			Integer flagFallPrice, Integer deliveryTime, Integer onLine,
			Set storeCommInfos, Set storeCommClassificationInfos) {
		this.id = id;
		this.storeClassificationInfoBySecClassification1 = storeClassificationInfoBySecClassification1;
		this.storeClassificationInfoBySecClassification3 = storeClassificationInfoBySecClassification3;
		this.storeClassificationInfoBySecClassification2 = storeClassificationInfoBySecClassification2;
		this.storeNo = storeNo;
		this.storeName = storeName;
		this.phone = phone;
		this.address = address;
		this.passwd = passwd;
		this.businessHours = businessHours;
		this.serviceRadius = serviceRadius;
		this.deliveryArea = deliveryArea;
		this.deliveryCharges = deliveryCharges;
		this.flagFallPrice = flagFallPrice;
		this.deliveryTime = deliveryTime;
		this.onLine = onLine;
		this.storeCommInfos = storeCommInfos;
		this.storeCommClassificationInfos = storeCommClassificationInfos;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StoreClassificationInfo getStoreClassificationInfoBySecClassification1() {
		return this.storeClassificationInfoBySecClassification1;
	}

	public void setStoreClassificationInfoBySecClassification1(
			StoreClassificationInfo storeClassificationInfoBySecClassification1) {
		this.storeClassificationInfoBySecClassification1 = storeClassificationInfoBySecClassification1;
	}

	public StoreClassificationInfo getStoreClassificationInfoBySecClassification3() {
		return this.storeClassificationInfoBySecClassification3;
	}

	public void setStoreClassificationInfoBySecClassification3(
			StoreClassificationInfo storeClassificationInfoBySecClassification3) {
		this.storeClassificationInfoBySecClassification3 = storeClassificationInfoBySecClassification3;
	}

	public StoreClassificationInfo getStoreClassificationInfoBySecClassification2() {
		return this.storeClassificationInfoBySecClassification2;
	}

	public void setStoreClassificationInfoBySecClassification2(
			StoreClassificationInfo storeClassificationInfoBySecClassification2) {
		this.storeClassificationInfoBySecClassification2 = storeClassificationInfoBySecClassification2;
	}

	public String getStoreNo() {
		return this.storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getBusinessHours() {
		return this.businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public String getServiceRadius() {
		return this.serviceRadius;
	}

	public void setServiceRadius(String serviceRadius) {
		this.serviceRadius = serviceRadius;
	}

	public String getDeliveryArea() {
		return this.deliveryArea;
	}

	public void setDeliveryArea(String deliveryArea) {
		this.deliveryArea = deliveryArea;
	}

	public Integer getDeliveryCharges() {
		return this.deliveryCharges;
	}

	public void setDeliveryCharges(Integer deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public Integer getFlagFallPrice() {
		return this.flagFallPrice;
	}

	public void setFlagFallPrice(Integer flagFallPrice) {
		this.flagFallPrice = flagFallPrice;
	}

	public Integer getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getOnLine() {
		return this.onLine;
	}

	public void setOnLine(Integer onLine) {
		this.onLine = onLine;
	}

	public Set getStoreCommInfos() {
		return this.storeCommInfos;
	}

	public void setStoreCommInfos(Set storeCommInfos) {
		this.storeCommInfos = storeCommInfos;
	}

	public Set getStoreCommClassificationInfos() {
		return this.storeCommClassificationInfos;
	}

	public void setStoreCommClassificationInfos(Set storeCommClassificationInfos) {
		this.storeCommClassificationInfos = storeCommClassificationInfos;
	}

}