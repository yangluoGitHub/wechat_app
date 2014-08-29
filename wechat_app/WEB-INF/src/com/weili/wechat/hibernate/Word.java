package com.weili.wechat.hibernate;



/**
 * Word entity. @author MyEclipse Persistence Tools
 */

public class Word  implements java.io.Serializable {


    // Fields    

     private String id;
     private String word;
     private String synonymId;
     private String pos;
     private Integer wordCount;
     private Integer key;


    // Constructors

    /** default constructor */
    public Word() {
    }

	/** minimal constructor */
    public Word(String id, String word) {
        this.id = id;
        this.word = word;
    }
    
    /** full constructor */
    public Word(String id, String word, String synonymId, String pos, Integer wordCount, Integer key) {
        this.id = id;
        this.word = word;
        this.synonymId = synonymId;
        this.pos = pos;
        this.wordCount = wordCount;
        this.key = key;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return this.word;
    }
    
    public void setWord(String word) {
        this.word = word;
    }

    public String getSynonymId() {
        return this.synonymId;
    }
    
    public void setSynonymId(String synonymId) {
        this.synonymId = synonymId;
    }

    public String getPos() {
        return this.pos;
    }
    
    public void setPos(String pos) {
        this.pos = pos;
    }

    public Integer getWordCount() {
        return this.wordCount;
    }
    
    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getKey() {
        return this.key;
    }
    
    public void setKey(Integer key) {
        this.key = key;
    }
   








}