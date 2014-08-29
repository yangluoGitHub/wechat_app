package com.weili.wechat.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * SysModule entity. @author MyEclipse Persistence Tools
 */

public class SysModule implements java.io.Serializable {

	// Fields

	private String no;
	private String name;
	private Set sysOperates = new HashSet(0);
	private Set sysLogs = new HashSet(0);
	private Set sysAudits = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysModule() {
	}

	/** minimal constructor */
	public SysModule(String no, String name) {
		this.no = no;
		this.name = name;
	}

	/** full constructor */
	public SysModule(String no, String name, Set sysOperates, Set sysLogs,
			Set sysAudits) {
		this.no = no;
		this.name = name;
		this.sysOperates = sysOperates;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getSysOperates() {
		return this.sysOperates;
	}

	public void setSysOperates(Set sysOperates) {
		this.sysOperates = sysOperates;
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