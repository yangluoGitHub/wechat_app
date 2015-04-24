package com.weili.wechat.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * StoreCommClassificationInfo entity. @author MyEclipse Persistence Tools
 */

public class StoreCommClassificationInfo implements java.io.Serializable {

	// Fields

	private String id;
	private StoreInfo storeInfo;
	private String commClassification;
	private Set storeCommInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public StoreCommClassificationInfo() {
	}

	/** minimal constructor */
	public StoreCommClassificationInfo(String id, StoreInfo storeInfo,
			String commClassification) {
		this.id = id;
		this.storeInfo = storeInfo;
		this.commClassification = commClassification;
	}

	/** full constructor */
	public StoreCommClassificationInfo(String id, StoreInfo storeInfo,
			String commClassification, Set storeCommInfos) {
		this.id = id;
		this.storeInfo = storeInfo;
		this.commClassification = commClassification;
		this.storeCommInfos = storeCommInfos;
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

	public String getCommClassification() {
		return this.commClassification;
	}

	public void setCommClassification(String commClassification) {
		this.commClassification = commClassification;
	}

	public Set getStoreCommInfos() {
		return this.storeCommInfos;
	}

	public void setStoreCommInfos(Set storeCommInfos) {
		this.storeCommInfos = storeCommInfos;
	}

}