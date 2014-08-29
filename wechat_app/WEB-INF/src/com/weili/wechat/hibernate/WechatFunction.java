package com.weili.wechat.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * WechatFunction entity. @author MyEclipse Persistence Tools
 */

public class WechatFunction implements java.io.Serializable {

	// Fields

	private String id;
	private String wechatletName;
	private String description;
	private String classname;
	private Set pubaccountFunctions = new HashSet(0);
	private Set wechatMenuItems = new HashSet(0);

	// Constructors

	/** default constructor */
	public WechatFunction() {
	}

	/** minimal constructor */
	public WechatFunction(String id, String wechatletName, String classname) {
		this.id = id;
		this.wechatletName = wechatletName;
		this.classname = classname;
	}

	/** full constructor */
	public WechatFunction(String id, String wechatletName, String description,
			String classname, Set pubaccountFunctions, Set wechatMenuItems) {
		this.id = id;
		this.wechatletName = wechatletName;
		this.description = description;
		this.classname = classname;
		this.pubaccountFunctions = pubaccountFunctions;
		this.wechatMenuItems = wechatMenuItems;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWechatletName() {
		return this.wechatletName;
	}

	public void setWechatletName(String wechatletName) {
		this.wechatletName = wechatletName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Set getPubaccountFunctions() {
		return this.pubaccountFunctions;
	}

	public void setPubaccountFunctions(Set pubaccountFunctions) {
		this.pubaccountFunctions = pubaccountFunctions;
	}

	public Set getWechatMenuItems() {
		return this.wechatMenuItems;
	}

	public void setWechatMenuItems(Set wechatMenuItems) {
		this.wechatMenuItems = wechatMenuItems;
	}

}