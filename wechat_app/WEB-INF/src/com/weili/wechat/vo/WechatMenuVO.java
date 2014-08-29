package com.weili.wechat.vo;

import java.util.List;



public class WechatMenuVO {
	
	private String id;    // 菜单id
	private String wechatOriginId;//微信公众号原始ID
	private String version;
	private String createTime;
	private String lastModifiedTime;
	
	private List<WechatMenuItemVO> menuItemVOlist;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public String getWechatOriginId() {
		return wechatOriginId;
	}
	public void setWechatOriginId(String wechatOriginId) {
		this.wechatOriginId = wechatOriginId;
	}
	public List<WechatMenuItemVO> getMenuItemVOlist() {
		return menuItemVOlist;
	}
	public void setMenuItemVOlist(List<WechatMenuItemVO> menuItemVOlist) {
		this.menuItemVOlist = menuItemVOlist;
	}
	
	
	
}