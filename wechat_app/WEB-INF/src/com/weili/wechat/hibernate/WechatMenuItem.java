package com.weili.wechat.hibernate;

/**
 * WechatMenuItem entity. @author MyEclipse Persistence Tools
 */

public class WechatMenuItem implements java.io.Serializable {

	// Fields

	private String id;
	private PublicAccount publicAccount;
	private WechatFunction wechatFunction;
	private String menuId;
	private String name;
	private String menuLevel;
	private String parentId;
	private String menuOrder;
	private Integer menuType;
	private String wechatUrl;

	// Constructors

	/** default constructor */
	public WechatMenuItem() {
	}

	/** minimal constructor */
	public WechatMenuItem(String id, PublicAccount publicAccount,
			String menuId, String name, String menuLevel, String menuOrder) {
		this.id = id;
		this.publicAccount = publicAccount;
		this.menuId = menuId;
		this.name = name;
		this.menuLevel = menuLevel;
		this.menuOrder = menuOrder;
		
	}

	/** full constructor */
	public WechatMenuItem(String id, PublicAccount publicAccount,
			WechatFunction wechatFunction, String menuId, String name,
			String menuLevel, String parentId, String menuOrder,
			Integer menuType, String wechatUrl) {
		this.id = id;
		this.publicAccount = publicAccount;
		this.wechatFunction = wechatFunction;
		this.menuId = menuId;
		this.name = name;
		this.menuLevel = menuLevel;
		this.parentId = parentId;
		this.menuOrder = menuOrder;
		this.menuType = menuType;
		this.wechatUrl= wechatUrl;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PublicAccount getPublicAccount() {
		return this.publicAccount;
	}

	public void setPublicAccount(PublicAccount publicAccount) {
		this.publicAccount = publicAccount;
	}

	public WechatFunction getWechatFunction() {
		return this.wechatFunction;
	}

	public void setWechatFunction(WechatFunction wechatFunction) {
		this.wechatFunction = wechatFunction;
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

	public Integer getMenuType() {
		return this.menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public String getWechatUrl() {
		return wechatUrl;
	}

	public void setWechatUrl(String wechatUrl) {
		this.wechatUrl = wechatUrl;
	}
	

}