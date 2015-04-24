package com.weili.wechat.hibernate;

/**
 * OpTable entity. @author MyEclipse Persistence Tools
 */

public class OpTable implements java.io.Serializable {

	// Fields

	private String no;
	private SysRole sysRole;
	private String passwd;
	private String name;
	private Integer status;
	private Integer onlineFlag;
	private String phone;
	private String mobile;
	private String email;
	private String photo;
	private String loginIp;
	private String loginTime;
	private String passwdExpiration;
	private Integer passwdError;
	private Integer signFlag;
	private String storeId;
	private String longitude;
	private String latitude;

	// Constructors

	/** default constructor */
	public OpTable() {
	}

	/** minimal constructor */
	public OpTable(String no, SysRole sysRole, String passwd, String name,
			Integer status, Integer onlineFlag, Integer passwdError,
			Integer signFlag) {
		this.no = no;
		this.sysRole = sysRole;
		this.passwd = passwd;
		this.name = name;
		this.status = status;
		this.onlineFlag = onlineFlag;
		this.passwdError = passwdError;
		this.signFlag = signFlag;
	}

	/** full constructor */
	public OpTable(String no, SysRole sysRole, String passwd, String name,
			Integer status, Integer onlineFlag, String phone, String mobile,
			String email, String photo, String loginIp, String loginTime,
			String passwdExpiration, Integer passwdError, Integer signFlag,
			String storeId, String longitude, String latitude) {
		this.no = no;
		this.sysRole = sysRole;
		this.passwd = passwd;
		this.name = name;
		this.status = status;
		this.onlineFlag = onlineFlag;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.photo = photo;
		this.loginIp = loginIp;
		this.loginTime = loginTime;
		this.passwdExpiration = passwdExpiration;
		this.passwdError = passwdError;
		this.signFlag = signFlag;
		this.storeId = storeId;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	// Property accessors

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOnlineFlag() {
		return this.onlineFlag;
	}

	public void setOnlineFlag(Integer onlineFlag) {
		this.onlineFlag = onlineFlag;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getPasswdExpiration() {
		return this.passwdExpiration;
	}

	public void setPasswdExpiration(String passwdExpiration) {
		this.passwdExpiration = passwdExpiration;
	}

	public Integer getPasswdError() {
		return this.passwdError;
	}

	public void setPasswdError(Integer passwdError) {
		this.passwdError = passwdError;
	}

	public Integer getSignFlag() {
		return this.signFlag;
	}

	public void setSignFlag(Integer signFlag) {
		this.signFlag = signFlag;
	}

	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}