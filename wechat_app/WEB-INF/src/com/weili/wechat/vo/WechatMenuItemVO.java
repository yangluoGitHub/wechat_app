package com.weili.wechat.vo;

import java.io.Serializable;



public class WechatMenuItemVO implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2840499149301140795L;
	// fields
	private String id; //菜单项id
	private String wechatOrginId; //菜单项所属公众号id
	private String wechatFunctionId; //业务功能ID
	private String menuId;  //菜单项所属菜单ID
	private String name;
	private String menuLevel;
	private String parentId;
	private String menuOrder;
	
	private String menuType;//菜单类型 ：0：click 1：view
	
	private String wechatFuncName;
	
	private String wechatUrl;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWechatOrginId() {
		return wechatOrginId;
	}
	public void setWechatOrginId(String wechatOrginId) {
		this.wechatOrginId = wechatOrginId;
	}
	public String getWechatFunctionId() {
		return wechatFunctionId;
	}
	public void setWechatFunctionId(String wechatFunctionId) {
		this.wechatFunctionId = wechatFunctionId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getWechatFuncName() {
		return wechatFuncName;
	}
	public void setWechatFuncName(String wechatFuncName) {
		this.wechatFuncName = wechatFuncName;
	}
	public String getWechatUrl() {
		return wechatUrl;
	}
	public void setWechatUrl(String wechatUrl) {
		this.wechatUrl = wechatUrl;
	}
	
	
}