package com.weili.wechat.hibernate;

/**
 * SysLog entity. @author MyEclipse Persistence Tools
 */

public class SysLog implements java.io.Serializable {

	// Fields

	private SysLogId id;
	private SysModule sysModule;
	private SysOperate sysOperate;
	private String username;
	private String xml;
	private String xmlOld;
	private String note;

	// Constructors

	/** default constructor */
	public SysLog() {
	}

	/** minimal constructor */
	public SysLog(SysLogId id, SysModule sysModule, SysOperate sysOperate,
			String username) {
		this.id = id;
		this.sysModule = sysModule;
		this.sysOperate = sysOperate;
		this.username = username;
	}

	/** full constructor */
	public SysLog(SysLogId id, SysModule sysModule, SysOperate sysOperate,
			String username, String xml, String xmlOld, String note) {
		this.id = id;
		this.sysModule = sysModule;
		this.sysOperate = sysOperate;
		this.username = username;
		this.xml = xml;
		this.xmlOld = xmlOld;
		this.note = note;
	}

	// Property accessors

	public SysLogId getId() {
		return this.id;
	}

	public void setId(SysLogId id) {
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

	public String getXml() {
		return this.xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getXmlOld() {
		return this.xmlOld;
	}

	public void setXmlOld(String xmlOld) {
		this.xmlOld = xmlOld;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}