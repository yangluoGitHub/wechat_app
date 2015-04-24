package com.weili.wechat.common;

public class RetInfo {
	private String retMsg;
	private int retCode;
	public int getRetCode() {
		// TODO Auto-generated method stub
		return this.retCode;
	}

	public String getRetMsg() {
		// TODO Auto-generated method stub
		return this.retMsg;
	}

	public void setRetCode(int retCode) {
		// TODO Auto-generated method stub
		this.retCode = retCode;
	}

	public void setRetMsg(String retMsg) {
		// TODO Auto-generated method stub
		this.retMsg = retMsg;
		
	}
	
	public void initRet(){
		this.setRetCode(-99);
		this.setRetMsg("操作失败!");
	}
	
	public void setRetOK(){
		this.setRetCode(0);
		this.setRetMsg("操作成功!");
	}
}
