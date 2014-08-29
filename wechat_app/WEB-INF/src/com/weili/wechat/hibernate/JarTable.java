package com.weili.wechat.hibernate;
/**
 * JarTable entity. @author MyEclipse Persistence Tools
 */

public class JarTable implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String upDate;
	private String upSender;

	// Constructors

	/** default constructor */
	public JarTable() {
	}

	/** minimal constructor */
	public JarTable(String id, String upDate) {
		this.id = id;
		this.upDate = upDate;
	}

	/** full constructor */
	public JarTable(String id, String name, String upDate, String upSender) {
		this.id = id;
		this.name = name;
		this.upDate = upDate;
		this.upSender = upSender;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpDate() {
		return this.upDate;
	}

	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}

	public String getUpSender() {
		return this.upSender;
	}

	public void setUpSender(String upSender) {
		this.upSender = upSender;
	}

}