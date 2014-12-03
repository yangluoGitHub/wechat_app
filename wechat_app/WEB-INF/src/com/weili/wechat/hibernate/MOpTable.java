package com.weili.wechat.hibernate;



/**
 * MOpTable entity. @author MyEclipse Persistence Tools
 */

public class MOpTable  implements java.io.Serializable {


    // Fields    

     private String no;
     private String passwd;
     private String name;
     private String mobile;


    // Constructors

    /** default constructor */
    public MOpTable() {
    }

	/** minimal constructor */
    public MOpTable(String no, String passwd, String mobile) {
        this.no = no;
        this.passwd = passwd;
        this.mobile = mobile;
    }
    
    /** full constructor */
    public MOpTable(String no, String passwd, String name, String mobile) {
        this.no = no;
        this.passwd = passwd;
        this.name = name;
        this.mobile = mobile;
    }

   
    // Property accessors

    public String getNo() {
        return this.no;
    }
    
    public void setNo(String no) {
        this.no = no;
    }

    public String getPasswd() {
        return this.passwd;
    }
    
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
   








}