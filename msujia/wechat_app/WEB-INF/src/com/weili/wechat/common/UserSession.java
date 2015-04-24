package com.weili.wechat.common;

import java.util.List;

public class UserSession {

	private String account; // 用户帐号

	private String name;   // 用户名称
	
	private int roleNo; // 用户角色编号
	
	private String roleName; // 用户角色名称
	
	private int roleCatalog;  //用户角色类型

	private List menuList; // 用户权限菜单列表
	
	private String loginIp;//用户上次登录IP
	
	private String loginTime;//用户上次登录时间

	private int online_flag;//登录标志
	
	private String loginIp_curr; //用户此次登录IP
	
	private String loginTime_curr;//用户此次登录时间
	
	private String StoreId; // 用户所属门店ID
	
	

	public String getStoreId() {
		return StoreId;
	}

	public void setStoreId(String storeId) {
		StoreId = storeId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(int roleNo) {
		this.roleNo = roleNo;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public int getOnline_flag() {
		return online_flag;
	}

	public void setOnline_flag(int online_flag) {
		this.online_flag = online_flag;
	}

	public String getLoginIp_curr() {
		return loginIp_curr;
	}

	public void setLoginIp_curr(String loginIp_curr) {
		this.loginIp_curr = loginIp_curr;
	}

	public String getLoginTime_curr() {
		return loginTime_curr;
	}

	public void setLoginTime_curr(String loginTime_curr) {
		this.loginTime_curr = loginTime_curr;
	}

	public int getRoleCatalog() {
		return roleCatalog;
	}

	public void setRoleCatalog(int roleCatalog) {
		this.roleCatalog = roleCatalog;
	}
	
}
