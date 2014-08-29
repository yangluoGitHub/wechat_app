package com.weili.wechat.hibernate;



/**
 * Answer entity. @author MyEclipse Persistence Tools
 */

public class Answer  implements java.io.Serializable {


    // Fields    

     private String id;
     private String content;
     private String editTime;
     private Integer adoptCount;


    // Constructors

    /** default constructor */
    public Answer() {
    }

	/** minimal constructor */
    public Answer(String id, String content) {
        this.id = id;
        this.content = content;
    }
    
    /** full constructor */
    public Answer(String id, String content, String editTime, Integer adoptCount) {
        this.id = id;
        this.content = content;
        this.editTime = editTime;
        this.adoptCount = adoptCount;
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

    public String getEditTime() {
        return this.editTime;
    }
    
    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public Integer getAdoptCount() {
        return this.adoptCount;
    }
    
    public void setAdoptCount(Integer adoptCount) {
        this.adoptCount = adoptCount;
    }
   








}