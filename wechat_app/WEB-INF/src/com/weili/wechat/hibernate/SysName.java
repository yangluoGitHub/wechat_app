package com.weili.wechat.hibernate;



/**
 * SysName entity. @author MyEclipse Persistence Tools
 */

public class SysName  implements java.io.Serializable {


    // Fields    

     private String no;
     private String name;


    // Constructors

    /** default constructor */
    public SysName() {
    }

    
    /** full constructor */
    public SysName(String no, String name) {
        this.no = no;
        this.name = name;
    }

   
    // Property accessors

    public String getNo() {
        return this.no;
    }
    
    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
   








}