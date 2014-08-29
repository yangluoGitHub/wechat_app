package com.weili.wechat.hibernate;



/**
 * WechatMedia entity. @author MyEclipse Persistence Tools
 */

public class WechatMedia  implements java.io.Serializable {


    // Fields    

     private String id;
     private String wechatId;
     private String mediaName;
     private String mediaType;
     private String mediaPath;
     private Integer mediaResource;
     private String mediaSuffix;
     private String mediaState;
     private String createDate;
     private String createTime;
     private String creator;
     private String describe;


    // Constructors

    /** default constructor */
    public WechatMedia() {
    }

	/** minimal constructor */
    public WechatMedia(String id, String wechatId, String mediaName, String mediaType, String mediaPath, Integer mediaResource, String mediaSuffix, String mediaState, String createDate, String createTime, String creator) {
        this.id = id;
        this.wechatId = wechatId;
        this.mediaName = mediaName;
        this.mediaType = mediaType;
        this.mediaPath = mediaPath;
        this.mediaResource = mediaResource;
        this.mediaSuffix = mediaSuffix;
        this.mediaState = mediaState;
        this.createDate = createDate;
        this.createTime = createTime;
        this.creator = creator;
    }
    
    /** full constructor */
    public WechatMedia(String id, String wechatId, String mediaName, String mediaType, String mediaPath, Integer mediaResource, String mediaSuffix, String mediaState, String createDate, String createTime, String creator, String describe) {
        this.id = id;
        this.wechatId = wechatId;
        this.mediaName = mediaName;
        this.mediaType = mediaType;
        this.mediaPath = mediaPath;
        this.mediaResource = mediaResource;
        this.mediaSuffix = mediaSuffix;
        this.mediaState = mediaState;
        this.createDate = createDate;
        this.createTime = createTime;
        this.creator = creator;
        this.describe = describe;
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

    public String getMediaName() {
        return this.mediaName;
    }
    
    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaType() {
        return this.mediaType;
    }
    
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaPath() {
        return this.mediaPath;
    }
    
    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public Integer getMediaResource() {
        return this.mediaResource;
    }
    
    public void setMediaResource(Integer mediaResource) {
        this.mediaResource = mediaResource;
    }

    public String getMediaSuffix() {
        return this.mediaSuffix;
    }
    
    public void setMediaSuffix(String mediaSuffix) {
        this.mediaSuffix = mediaSuffix;
    }

    public String getMediaState() {
        return this.mediaState;
    }
    
    public void setMediaState(String mediaState) {
        this.mediaState = mediaState;
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

    public String getCreator() {
        return this.creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescribe() {
        return this.describe;
    }
    
    public void setDescribe(String describe) {
        this.describe = describe;
    }
   








}