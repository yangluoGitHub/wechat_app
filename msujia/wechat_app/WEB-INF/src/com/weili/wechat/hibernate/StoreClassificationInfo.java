package com.weili.wechat.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * StoreClassificationInfo entity. @author MyEclipse Persistence Tools
 */

public class StoreClassificationInfo implements java.io.Serializable {

	// Fields

	private String id;
	private Integer clLevel;
	private String clName;
	private String picLink;
	private Set storeInfosForSecClassification1 = new HashSet(0);
	private Set storeInfosForSecClassification3 = new HashSet(0);
	private Set storeInfosForSecClassification2 = new HashSet(0);

	// Constructors

	/** default constructor */
	public StoreClassificationInfo() {
	}

	/** minimal constructor */
	public StoreClassificationInfo(String id, Integer clLevel, String clName) {
		this.id = id;
		this.clLevel = clLevel;
		this.clName = clName;
	}

	/** full constructor */
	public StoreClassificationInfo(String id, Integer clLevel, String clName,
			String picLink, Set storeInfosForSecClassification1,
			Set storeInfosForSecClassification3,
			Set storeInfosForSecClassification2) {
		this.id = id;
		this.clLevel = clLevel;
		this.clName = clName;
		this.picLink = picLink;
		this.storeInfosForSecClassification1 = storeInfosForSecClassification1;
		this.storeInfosForSecClassification3 = storeInfosForSecClassification3;
		this.storeInfosForSecClassification2 = storeInfosForSecClassification2;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getClLevel() {
		return this.clLevel;
	}

	public void setClLevel(Integer clLevel) {
		this.clLevel = clLevel;
	}

	public String getClName() {
		return this.clName;
	}

	public void setClName(String clName) {
		this.clName = clName;
	}

	public String getPicLink() {
		return this.picLink;
	}

	public void setPicLink(String picLink) {
		this.picLink = picLink;
	}

	public Set getStoreInfosForSecClassification1() {
		return this.storeInfosForSecClassification1;
	}

	public void setStoreInfosForSecClassification1(
			Set storeInfosForSecClassification1) {
		this.storeInfosForSecClassification1 = storeInfosForSecClassification1;
	}

	public Set getStoreInfosForSecClassification3() {
		return this.storeInfosForSecClassification3;
	}

	public void setStoreInfosForSecClassification3(
			Set storeInfosForSecClassification3) {
		this.storeInfosForSecClassification3 = storeInfosForSecClassification3;
	}

	public Set getStoreInfosForSecClassification2() {
		return this.storeInfosForSecClassification2;
	}

	public void setStoreInfosForSecClassification2(
			Set storeInfosForSecClassification2) {
		this.storeInfosForSecClassification2 = storeInfosForSecClassification2;
	}

}