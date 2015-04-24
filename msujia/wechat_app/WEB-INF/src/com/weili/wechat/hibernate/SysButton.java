package com.weili.wechat.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * SysButton entity. @author MyEclipse Persistence Tools
 */

public class SysButton implements java.io.Serializable {

	// Fields

	private Integer no;
	private SysMenu sysMenu;
	private String name;
	private String url;
	private Integer buttonOrder;
	private String note;
	private Set sysConfRoleButtons = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysButton() {
	}

	/** minimal constructor */
	public SysButton(Integer no) {
		this.no = no;
	}

	/** full constructor */
	public SysButton(Integer no, SysMenu sysMenu, String name, String url,
			Integer buttonOrder, String note, Set sysConfRoleButtons) {
		this.no = no;
		this.sysMenu = sysMenu;
		this.name = name;
		this.url = url;
		this.buttonOrder = buttonOrder;
		this.note = note;
		this.sysConfRoleButtons = sysConfRoleButtons;
	}

	// Property accessors

	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
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

	public Integer getButtonOrder() {
		return this.buttonOrder;
	}

	public void setButtonOrder(Integer buttonOrder) {
		this.buttonOrder = buttonOrder;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getSysConfRoleButtons() {
		return this.sysConfRoleButtons;
	}

	public void setSysConfRoleButtons(Set sysConfRoleButtons) {
		this.sysConfRoleButtons = sysConfRoleButtons;
	}

}