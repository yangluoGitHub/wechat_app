package com.weili.wechat.hibernate;
/**
 * WechatArticle entity. @author MyEclipse Persistence Tools
 */

public class WechatArticle implements java.io.Serializable {

	// Fields

	private String id;
	private String wechatId;
	private String function;
	private String type;
	private String articleId;
	private String articlePicurl;
	private String articleUrl;
	private String articleTitle;
	private String articleDescribe;
	private String createTime;

	// Constructors

	/** default constructor */
	public WechatArticle() {
	}

	/** minimal constructor */
	public WechatArticle(String wechatId, String function, String type,
			String articleId, String articlePicurl, String articleTitle,
			String createTime) {
		this.wechatId = wechatId;
		this.function = function;
		this.type = type;
		this.articleId = articleId;
		this.articlePicurl = articlePicurl;
		this.articleTitle = articleTitle;
		this.createTime = createTime;
	}

	/** full constructor */
	public WechatArticle(String wechatId, String function, String type,
			String articleId, String articlePicurl, String articleUrl,
			String articleTitle, String articleDescribe, String createTime) {
		this.wechatId = wechatId;
		this.function = function;
		this.type = type;
		this.articleId = articleId;
		this.articlePicurl = articlePicurl;
		this.articleUrl = articleUrl;
		this.articleTitle = articleTitle;
		this.articleDescribe = articleDescribe;
		this.createTime = createTime;
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

	public String getFunction() {
		return this.function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getArticleId() {
		return this.articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getArticlePicurl() {
		return this.articlePicurl;
	}

	public void setArticlePicurl(String articlePicurl) {
		this.articlePicurl = articlePicurl;
	}

	public String getArticleUrl() {
		return this.articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public String getArticleTitle() {
		return this.articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleDescribe() {
		return this.articleDescribe;
	}

	public void setArticleDescribe(String articleDescribe) {
		this.articleDescribe = articleDescribe;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}