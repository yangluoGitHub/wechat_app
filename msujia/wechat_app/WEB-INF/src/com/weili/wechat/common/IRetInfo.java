package com.weili.wechat.common;

public interface IRetInfo {
	/**
	 * ���÷�����Ϣ
	 * @param retMsg
	 * @roseuid 474FA8EB00F2
	 */
	public void setRetMsg(String retMsg);
	   
	/**
	 * ��ȡ������Ϣ
	 * @return java.lang.String
	 * @roseuid 474FA8EB0382
	*/
	public String getRetMsg();
	   
	/**
	 * ���÷�����
	 * @param retCode
	 * @roseuid 474FA8EC00B3
	*/
	public void setRetCode(int retCode);
	   
	/**
	 * ��ȡ������
	 * @return int
	 * @roseuid 474FA8ED0075
	*/
	public int getRetCode();
}
