package com.weili.wechat.hibernate;



/**
 * WechatMessage entity. @author MyEclipse Persistence Tools
 */

public class WechatMessage  implements java.io.Serializable {


    // Fields    

     private String id;
     private String wechatId;
     private String openId;
     private Integer messageMode;
     private String createDate;
     private String createTime;
     private String messageType;
     private String content;
     private String picUrl;
     private String mediaId;
     private String format;
     private String thumbMediaId;
     private String locationX;
     private String locationY;
     private String scale;
     private String label;
     private String title;
     private String description;
     private String url;
     private String musicUrl;
     private String hqMusicUrl;
     private Integer articleCount;
     private String articles;


    // Constructors

    /** default constructor */
    public WechatMessage() {
    }

	/** minimal constructor */
    public WechatMessage(String id, String wechatId, String openId, Integer messageMode, String createDate, String createTime, String messageType) {
        this.id = id;
        this.wechatId = wechatId;
        this.openId = openId;
        this.messageMode = messageMode;
        this.createDate = createDate;
        this.createTime = createTime;
        this.messageType = messageType;
    }
    
    /** full constructor */
    public WechatMessage(String id, String wechatId, String openId, Integer messageMode, String createDate, String createTime, String messageType, String content, String picUrl, String mediaId, String format, String thumbMediaId, String locationX, String locationY, String scale, String label, String title, String description, String url, String musicUrl, String hqMusicUrl, Integer articleCount, String articles) {
        this.id = id;
        this.wechatId = wechatId;
        this.openId = openId;
        this.messageMode = messageMode;
        this.createDate = createDate;
        this.createTime = createTime;
        this.messageType = messageType;
        this.content = content;
        this.picUrl = picUrl;
        this.mediaId = mediaId;
        this.format = format;
        this.thumbMediaId = thumbMediaId;
        this.locationX = locationX;
        this.locationY = locationY;
        this.scale = scale;
        this.label = label;
        this.title = title;
        this.description = description;
        this.url = url;
        this.musicUrl = musicUrl;
        this.hqMusicUrl = hqMusicUrl;
        this.articleCount = articleCount;
        this.articles = articles;
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

    public String getOpenId() {
        return this.openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getMessageMode() {
        return this.messageMode;
    }
    
    public void setMessageMode(Integer messageMode) {
        this.messageMode = messageMode;
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

    public String getMessageType() {
        return this.messageType;
    }
    
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return this.picUrl;
    }
    
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return this.mediaId;
    }
    
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return this.format;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }

    public String getThumbMediaId() {
        return this.thumbMediaId;
    }
    
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getLocationX() {
        return this.locationX;
    }
    
    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return this.locationY;
    }
    
    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getScale() {
        return this.scale;
    }
    
    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getMusicUrl() {
        return this.musicUrl;
    }
    
    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
        return this.hqMusicUrl;
    }
    
    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    public Integer getArticleCount() {
        return this.articleCount;
    }
    
    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public String getArticles() {
        return this.articles;
    }
    
    public void setArticles(String articles) {
        this.articles = articles;
    }
   








}