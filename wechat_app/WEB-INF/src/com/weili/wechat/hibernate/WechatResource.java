package com.weili.wechat.hibernate;

/**
 * WechatResource entity. @author MyEclipse Persistence Tools
 */

public class WechatResource implements java.io.Serializable {

	// Fields

	private String id;
	private String wechatId;
	private String mediaId;
	private String resourceTittle;
	private String resourceContent;
	private String createDate;
	private String createTime;
	private String outLink;
	private String multiresourceId;

	// Constructors

	/** default constructor */
	public WechatResource() {
	}

	/** minimal constructor */
	public WechatResource(String id, String wechatId, String createDate,
			String createTime) {
		this.id = id;
		this.wechatId = wechatId;
		this.createDate = createDate;
		this.createTime = createTime;
	}

	/** full constructor */
	public WechatResource(String id, String wechatId, String mediaId,
			String resourceTittle, String resourceContent, String createDate,
			String createTime, String outLink, String multiresourceId) {
		this.id = id;
		this.wechatId = wechatId;
		this.mediaId = mediaId;
		this.resourceTittle = resourceTittle;
		this.resourceContent = resourceContent;
		this.createDate = createDate;
		this.createTime = createTime;
		this.outLink = outLink;
		this.multiresourceId = multiresourceId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWechatId() {
		return this.wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getResourceTittle() {
		return this.resourceTittle;
	}

	public void setResourceTittle(String resourceTittle) {
		this.resourceTittle = resourceTittle;
	}

	public String getResourceContent() {
		return this.resourceContent;
	}

	public void setResourceContent(String resourceContent) {
		this.resourceContent = resourceContent;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOutLink() {
		return this.outLink;
	}

	public void setOutLink(String outLink) {
		this.outLink = outLink;
	}

	public String getMultiresourceId() {
		return this.multiresourceId;
	}

	public void setMultiresourceId(String multiresourceId) {
		this.multiresourceId = multiresourceId;
	}

}