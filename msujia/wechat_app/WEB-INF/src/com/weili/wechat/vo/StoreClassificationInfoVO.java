package com.weili.wechat.vo;

public class StoreClassificationInfoVO {
	
	private String id;
	private Integer clLevel;
	private String clName;
	
	public StoreClassificationInfoVO() {
	}
	public StoreClassificationInfoVO(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getClLevel() {
		return clLevel;
	}
	public void setClLevel(Integer clLevel) {
		this.clLevel = clLevel;
	}
	public String getClName() {
		return clName;
	}
	public void setClName(String clName) {
		this.clName = clName;
	}
	
}