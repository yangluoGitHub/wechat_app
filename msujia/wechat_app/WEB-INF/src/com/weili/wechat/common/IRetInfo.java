package com.weili.wechat.common;

public interface IRetInfo {
	/**
	 * 设置返回信息
	 * @param retMsg
	 * @roseuid 474FA8EB00F2
	 */
	public void setRetMsg(String retMsg);
	   
	/**
	 * 获取返回信息
	 * @return java.lang.String
	 * @roseuid 474FA8EB0382
	*/
	public String getRetMsg();
	   
	/**
	 * 设置返回码
	 * @param retCode
	 * @roseuid 474FA8EC00B3
	*/
	public void setRetCode(int retCode);
	   
	/**
	 * 获取返回码
	 * @return int
	 * @roseuid 474FA8ED0075
	*/
	public int getRetCode();
}
