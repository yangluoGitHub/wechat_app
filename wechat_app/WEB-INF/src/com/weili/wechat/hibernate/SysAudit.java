package com.weili.wechat.hibernate;

/**
 * SysAudit entity. @author MyEclipse Persistence Tools
 */

public class SysAudit implements java.io.Serializable {

	// Fields

	private SysAuditId id;
	private SysModule sysModule;
	private SysOperate sysOperate;
	private String username;
	private String types;
	private String params;
	private String oldParams;

	// Constructors

	/** default constructor */
	public SysAudit() {
	}

	/** full constructor */
	public SysAudit(SysAuditId id, SysModule sysModule, SysOperate sysOperate,
			String username, String types, String params, String oldParams) {
		this.id = id;
		this.sysModule = sysModule;
		this.sysOperate = sysOperate;
		this.username = username;
		this.types = types;
		this.params = params;
		this.oldParams = oldParams;
	}

	// Property accessors

	public SysAuditId getId() {
		return this.id;
	}

	public void setId(SysAuditId id) {
		this.id = id;
	}

	public SysModule getSysModule() {
		return this.sysModule;
	}

	public void setSysModule(SysModule sysModule) {
		this.sysModule = sysModule;
	}

	public SysOperate getSysOperate() {
		return this.sysOperate;
	}

	public void setSysOperate(SysOperate sysOperate) {
		this.sysOperate = sysOperate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTypes() {
		return this.types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getOldParams() {
		return this.oldParams;
	}

	public void setOldParams(String oldParams) {
		this.oldParams = oldParams;
	}

}