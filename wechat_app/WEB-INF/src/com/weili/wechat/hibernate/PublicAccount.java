package com.weili.wechat.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * PublicAccount entity. @author MyEclipse Persistence Tools
 */

public class PublicAccount implements java.io.Serializable {

	// Fields

	private String id;
	private String wechatName;
	private String wechatId;
	private Integer wechatType;
	private String url;
	private String token;
	private String appid;
	private String appsecret;
	private String creator;
	private String accessToken;
	private String logoLocation;
	private Set userPubaccounts = new HashSet(0);
	private Set pubaccountFunctions = new HashSet(0);
	private Set wechatMenus = new HashSet(0);
	private Set wechatMenuItems = new HashSet(0);

	// Constructors

	/** default constructor */
	public PublicAccount() {
	}

	/** minimal constructor */
	public PublicAccount(String id, String wechatName, String wechatId,
			Integer wechatType) {
		this.id = id;
		this.wechatName = wechatName;
		this.wechatId = wechatId;
		this.wechatType = wechatType;
	}

	/** full constructor */
	public PublicAccount(String id, String wechatName, String wechatId,
			Integer wechatType, String url, String token, String appid,
			String appsecret, String creator, String accessToken,
			String logoLocation, Set userPubaccounts, Set pubaccountFunctions,
			Set wechatMenus, Set wechatMenuItems) {
		this.id = id;
		this.wechatName = wechatName;
		this.wechatId = wechatId;
		this.wechatType = wechatType;
		this.url = url;
		this.token = token;
		this.appid = appid;
		this.appsecret = appsecret;
		this.creator = creator;
		this.accessToken = accessToken;
		this.logoLocation = logoLocation;
		this.userPubaccounts = userPubaccounts;
		this.pubaccountFunctions = pubaccountFunctions;
		this.wechatMenus = wechatMenus;
		this.wechatMenuItems = wechatMenuItems;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWechatName() {
		return this.wechatName;
	}

	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}

	public String getWechatId() {
		return this.wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public Integer getWechatType() {
		return this.wechatType;
	}

	public void setWechatType(Integer wechatType) {
		this.wechatType = wechatType;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppid() {
		return this.appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return this.appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getLogoLocation() {
		return this.logoLocation;
	}

	public void setLogoLocation(String logoLocation) {
		this.logoLocation = logoLocation;
	}

	public Set getUserPubaccounts() {
		return this.userPubaccounts;
	}

	public void setUserPubaccounts(Set userPubaccounts) {
		this.userPubaccounts = userPubaccounts;
	}

	public Set getPubaccountFunctions() {
		return this.pubaccountFunctions;
	}

	public void setPubaccountFunctions(Set pubaccountFunctions) {
		this.pubaccountFunctions = pubaccountFunctions;
	}

	public Set getWechatMenus() {
		return this.wechatMenus;
	}

	public void setWechatMenus(Set wechatMenus) {
		this.wechatMenus = wechatMenus;
	}

	public Set getWechatMenuItems() {
		return this.wechatMenuItems;
	}

	public void setWechatMenuItems(Set wechatMenuItems) {
		this.wechatMenuItems = wechatMenuItems;
	}

}