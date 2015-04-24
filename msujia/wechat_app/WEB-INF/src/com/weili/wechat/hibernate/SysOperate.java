package com.weili.wechat.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * SysOperate entity. @author MyEclipse Persistence Tools
 */

public class SysOperate implements java.io.Serializable {

	// Fields

	private String no;
	private SysModule sysModule;
	private String name;
	private Set sysLogs = new HashSet(0);
	private Set sysAudits = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysOperate() {
	}

	/** minimal constructor */
	public SysOperate(String no, SysModule sysModule, String name) {
		this.no = no;
		this.sysModule = sysModule;
		this.name = name;
	}

	/** full constructor */
	public SysOperate(String no, SysModule sysModule, String name, Set sysLogs,
			Set sysAudits) {
		this.no = no;
		this.sysModule = sysModule;
		this.name = name;
		this.sysLogs = sysLogs;
		this.sysAudits = sysAudits;
	}

	// Property accessors

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public SysModule getSysModule() {
		return this.sysModule;
	}

	public void setSysModule(SysModule sysModule) {
		this.sysModule = sysModule;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getSysLogs() {
		return this.sysLogs;
	}

	public void setSysLogs(Set sysLogs) {
		this.sysLogs = sysLogs;
	}

	public Set getSysAudits() {
		return this.sysAudits;
	}

	public void setSysAudits(Set sysAudits) {
		this.sysAudits = sysAudits;
	}

}