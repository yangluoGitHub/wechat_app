package com.weili.wechat.hibernate;

/**
 * WechatMenu entity. @author MyEclipse Persistence Tools
 */

public class WechatMenu implements java.io.Serializable {

	// Fields

	private String id;
	private PublicAccount publicAccount;
	private String version;
	private String createTime;
	private String lastModifiedTime;

	// Constructors

	/** default constructor */
	public WechatMenu() {
	}

	/** full constructor */
	public WechatMenu(String id, PublicAccount publicAccount, String version,
			String createTime, String lastModifiedTime) {
		this.id = id;
		this.publicAccount = publicAccount;
		this.version = version;
		this.createTime = createTime;
		this.lastModifiedTime = lastModifiedTime;
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

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModifiedTime() {
		return this.lastModifiedTime;
	}

	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

}