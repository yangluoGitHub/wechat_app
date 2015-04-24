package com.weili.wechat.common;

/**
 * @author hjtang
 * @since 2010-10-12
 */

public enum AtmLogRet {
	R01, R02, R04, R05, R06,R07, R30, R20, R21, R00, RFF,S00, SFF;

	/**
	 * @param ret
	 * @return 中文名称
	 */
	public static AtmLogRet getRemoteRet(String ret) {
		if(ret.equals("00"))
		{
			return AtmLogRet.R00;
		}
		else if(ret.equals("02"))
		{
			return AtmLogRet.R02;
		}
		else if(ret.equals("04"))
		{
			return AtmLogRet.R04;
		}
		else if(ret.equals("05"))
		{
			return AtmLogRet.R05;
		}
		else if(ret.equals("06"))
		{
			return AtmLogRet.R06;
		}
		else if(ret.equals("07"))
		{
			return AtmLogRet.R07;
		}
		else if(ret.equals("30"))
		{
			return AtmLogRet.R30;
		}
		else if(ret.equals("20"))
		{
			return AtmLogRet.R20;
		}
		else if(ret.equals("21"))
		{
			return AtmLogRet.R21;
		}
		else
		{
			return AtmLogRet.RFF;
		}		
	}
	/**
	 * @param ret
	 * @return 中文名称
	 */
	public static String getAtmLogRetName(AtmLogRet ret) {
		
		switch (ret) {
		case R01:
			return "客户端返回打开文件失败";
		case R02:
			return "客户端返回定位到指定的文件位置失败";
		case R04:
			return "客户端返回文件不能再继续读取数据,文件已经结束";
		case R05:
			return "服务器端获取要发送的文件信息错误";
		case R06:
			return "其他错误";
		case R07:
			return "客户端返回停止文件传送服务";
		case R30:
			return "客户端返回报文解析错误,指定的XML字段在请求报文中不存在";
		case R20:
			return "客户端返回文件传送结束";
		case R21:
			return "客户端返回报文的返回数据为文件数据，数据的长度为(报文长度-8)";
		case R00:
			return "客户端返回文件准备完毕";	
		case RFF:
			return "客户端未知返回";	
		case S00:
			return "服务端获取文件成功";
		case SFF:
			return "服务端异常失败";
		default:			
			return String.valueOf(ret);
		}
	}
}
