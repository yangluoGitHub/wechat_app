package com.weili.wechat.hibernate;

/**
 * StoreCommInfo entity. @author MyEclipse Persistence Tools
 */

public class StoreCommInfo implements java.io.Serializable {

	// Fields

	private String id;
	private StoreInfo storeInfo;
	private StoreCommClassificationInfo storeCommClassificationInfo;
	private String name;
	private double price;
	private double preferentialPrice;
	private Integer onhand;
	private String pictureLink;
	private Integer onShelves;

	// Constructors

	/** default constructor */
	public StoreCommInfo() {
	}

	/** minimal constructor */
	public StoreCommInfo(String id, StoreInfo storeInfo,
			StoreCommClassificationInfo storeCommClassificationInfo,
			String name, double price, double preferentialPrice,
			String pictureLink, Integer onShelves) {
		this.id = id;
		this.storeInfo = storeInfo;
		this.storeCommClassificationInfo = storeCommClassificationInfo;
		this.name = name;
		this.price = price;
		this.preferentialPrice = preferentialPrice;
		this.pictureLink = pictureLink;
		this.onShelves = onShelves;
	}

	/** full constructor */
	public StoreCommInfo(String id, StoreInfo storeInfo,
			StoreCommClassificationInfo storeCommClassificationInfo,
			String name, double price, double preferentialPrice,
			Integer onhand, String pictureLink, Integer onShelves) {
		this.id = id;
		this.storeInfo = storeInfo;
		this.storeCommClassificationInfo = storeCommClassificationInfo;
		this.name = name;
		this.price = price;
		this.preferentialPrice = preferentialPrice;
		this.onhand = onhand;
		this.pictureLink = pictureLink;
		this.onShelves = onShelves;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StoreInfo getStoreInfo() {
		return this.storeInfo;
	}

	public void setStoreInfo(StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}

	public StoreCommClassificationInfo getStoreCommClassificationInfo() {
		return this.storeCommClassificationInfo;
	}

	public void setStoreCommClassificationInfo(
			StoreCommClassificationInfo storeCommClassificationInfo) {
		this.storeCommClassificationInfo = storeCommClassificationInfo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPreferentialPrice() {
		return this.preferentialPrice;
	}

	public void setPreferentialPrice(double preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}

	public Integer getOnhand() {
		return this.onhand;
	}

	public void setOnhand(Integer onhand) {
		this.onhand = onhand;
	}

	public String getPictureLink() {
		return this.pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public Integer getOnShelves() {
		return this.onShelves;
	}

	public void setOnShelves(Integer onShelves) {
		this.onShelves = onShelves;
	}

}