//Source file: E:\\Program Files\\workspace\\tsp\\WEB-INF\\src\\com\\weili\\tsp\\vo\\User.java

package com.weili.wechat.vo;

/**
 * �û�
 */
public class User {

	/**
	 * ��ԱID
	 */
	private String no;

	/**
	 * ����
	 */
	private String passwd;

	private String newPasswd;

	/**
	 * �û�����
	 */
	private String name;

	/**
	 * ״̬
	 */
	private Integer status;

	/**
	 * �ϴε�½ʱ��
	 */
	private String loginTime;

	/**
	 * �ϴε�½IP
	 */
	private String loginIp;

	/**
	 * ����״̬
	 */
	private Integer online_flag;

	/**
	 * ǩ����־
	 */
	private Integer signFlag;

	/**
	 * ��Ա��ɫ
	 */
	private Role role;

	/**
	 * �绰
	 */
	private String telephone;

	/**
	 * �ֻ�
	 */
	private String mobile;

	/**
	 * �����ʼ�
	 */
	private String email;

	/**
	 * ��Ƭλ��
	 */
	private String photo;

	/**
	 * ����ʱ��
	 */
	private String passwdExpiration;

	/**
	 * ��������������
	 */
	private Integer passwdError;

	private String wechatId; // ΢�Ź�����

	private String wechatName;

	public Integer getPasswdError() {
		return passwdError;
	}

	public void setPasswdError(Integer passwdError) {
		this.passwdError = passwdError;
	}

	public String getPasswdExpiration() {
		return passwdExpiration;
	}

	public void setPasswdExpiration(String passwdExpiration) {
		this.passwdExpiration = passwdExpiration;
	}

	/**
	 * Access method for the no property.
	 * 
	 * @return the current value of the no property
	 */
	public String getNo() {
		return no;
	}

	/**
	 * Sets the value of the no property.
	 * 
	 * @param aNo
	 *            the new value of the no property
	 */
	public void setNo(String aNo) {
		no = aNo;
	}

	/**
	 * Access method for the name property.
	 * 
	 * @return the current value of the name property
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param aName
	 *            the new value of the name property
	 */
	public void setName(String aName) {
		name = aName;
	}

	/**
	 * Access method for the telephone property.
	 * 
	 * @return the current value of the telephone property
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Sets the value of the telephone property.
	 * 
	 * @param aTelephone
	 *            the new value of the telephone property
	 */
	public void setTelephone(String aTelephone) {
		telephone = aTelephone;
	}

	/**
	 * Access method for the mobile property.
	 * 
	 * @return the current value of the mobile property
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the value of the mobile property.
	 * 
	 * @param aMobile
	 *            the new value of the mobile property
	 */
	public void setMobile(String aMobile) {
		mobile = aMobile;
	}

	/**
	 * Access method for the email property.
	 * 
	 * @return the current value of the email property
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the value of the email property.
	 * 
	 * @param aEmail
	 *            the new value of the email property
	 */
	public void setEmail(String aEmail) {
		email = aEmail;
	}

	public Integer getOnline_flag() {
		return online_flag;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getPhoto() {
		return photo;
	}

	public Role getRole() {
		return role;
	}

	public Integer getStatus() {
		return status;
	}

	public void setOnline_flag(Integer online_flag) {
		this.online_flag = online_flag;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNewPasswd() {
		return newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Integer getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(Integer signFlag) {
		this.signFlag = signFlag;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getWechatName() {
		return wechatName;
	}

	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}

}
