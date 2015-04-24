package com.weili.wechat.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * SysMenu entity. @author MyEclipse Persistence Tools
 */

public class SysMenu  implements java.io.Serializable {


    // Fields    

     private String no;
     private SysMenu sysMenu;
     private String name;
     private String url;
     private Integer menuLevel;
     private Integer menuOrder;
     private String note;
     private Set sysConfRoleMenus = new HashSet(0);
     private Set sysButtons = new HashSet(0);
     private Set sysMenus = new HashSet(0);


    // Constructors

    /** default constructor */
    public SysMenu() {
    }

	/** minimal constructor */
    public SysMenu(String no, String name) {
        this.no = no;
        this.name = name;
    }
    
    /** full constructor */
    public SysMenu(String no, SysMenu sysMenu, String name, String url, Integer menuLevel, Integer menuOrder, String note, Set sysConfRoleMenus, Set sysButtons, Set sysMenus) {
        this.no = no;
        this.sysMenu = sysMenu;
        this.name = name;
        this.url = url;
        this.menuLevel = menuLevel;
        this.menuOrder = menuOrder;
        this.note = note;
        this.sysConfRoleMenus = sysConfRoleMenus;
        this.sysButtons = sysButtons;
        this.sysMenus = sysMenus;
    }

   
    // Property accessors

    public String getNo() {
        return this.no;
    }
    
    public void setNo(String no) {
        this.no = no;
    }

    public SysMenu getSysMenu() {
        return this.sysMenu;
    }
    
    public void setSysMenu(SysMenu sysMenu) {
        this.sysMenu = sysMenu;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getMenuLevel() {
        return this.menuLevel;
    }
    
    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getMenuOrder() {
        return this.menuOrder;
    }
    
    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

    public Set getSysConfRoleMenus() {
        return this.sysConfRoleMenus;
    }
    
    public void setSysConfRoleMenus(Set sysConfRoleMenus) {
        this.sysConfRoleMenus = sysConfRoleMenus;
    }

    public Set getSysButtons() {
        return this.sysButtons;
    }
    
    public void setSysButtons(Set sysButtons) {
        this.sysButtons = sysButtons;
    }

    public Set getSysMenus() {
        return this.sysMenus;
    }
    
    public void setSysMenus(Set sysMenus) {
        this.sysMenus = sysMenus;
    }
   








}