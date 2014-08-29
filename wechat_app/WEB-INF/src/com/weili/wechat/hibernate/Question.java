package com.weili.wechat.hibernate;



/**
 * Question entity. @author MyEclipse Persistence Tools
 */

public class Question  implements java.io.Serializable {


    // Fields    

     private String id;
     private String content;
     private String answerId;
     private String keywords;
     private String subAttr;
     private String subject;
     private String verb;
     private String obAttr;
     private String object;
     private Integer qtype;
     private String editTime;
     private Integer askCount;


    // Constructors

    /** default constructor */
    public Question() {
    }

	/** minimal constructor */
    public Question(String id, String content) {
        this.id = id;
        this.content = content;
    }
    
    /** full constructor */
    public Question(String id, String content, String answerId, String keywords, String subAttr, String subject, String verb, String obAttr, String object, Integer qtype, String editTime, Integer askCount) {
        this.id = id;
        this.content = content;
        this.answerId = answerId;
        this.keywords = keywords;
        this.subAttr = subAttr;
        this.subject = subject;
        this.verb = verb;
        this.obAttr = obAttr;
        this.object = object;
        this.qtype = qtype;
        this.editTime = editTime;
        this.askCount = askCount;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswerId() {
        return this.answerId;
    }
    
    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getKeywords() {
        return this.keywords;
    }
    
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSubAttr() {
        return this.subAttr;
    }
    
    public void setSubAttr(String subAttr) {
        this.subAttr = subAttr;
    }

    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getVerb() {
        return this.verb;
    }
    
    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getObAttr() {
        return this.obAttr;
    }
    
    public void setObAttr(String obAttr) {
        this.obAttr = obAttr;
    }

    public String getObject() {
        return this.object;
    }
    
    public void setObject(String object) {
        this.object = object;
    }

    public Integer getQtype() {
        return this.qtype;
    }
    
    public void setQtype(Integer qtype) {
        this.qtype = qtype;
    }

    public String getEditTime() {
        return this.editTime;
    }
    
    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public Integer getAskCount() {
        return this.askCount;
    }
    
    public void setAskCount(Integer askCount) {
        this.askCount = askCount;
    }
   








}