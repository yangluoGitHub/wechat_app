package com.weili.wechat.common;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.zip.ZipException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * socket操作实用类
 * 
 * @author hpshen
 * @since 2007.04.10
 */

public class SocketUtil {

	private static Log log = LogFactory.getLog(SocketUtil.class);

	/**
	 * 从socket通道上读取数据，并将读到的数据解压缩
	 */
	
	public static byte[] readSocket1(DataInputStream in) throws IOException,ZipException {

		if (in == null) {
			throw new NullPointerException("readSocket方法输入参数为空");
		}

		// 读取报文信息段

		byte[] headSection = new byte[8];
		int charsRead;

		charsRead = in.read(headSection, 0, 8);
		log.info("C端返回报文头："+new String(headSection));
		if (charsRead == -1) {
			log.error("从socket中没读到内容");
			return null;
		}

		int realLength = 0;
		String zipFlag = "0";
		try {
			realLength = Integer.parseInt(new String(headSection, 0, 4)) - 8;
			zipFlag = new String(headSection, 5, 1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error("获取报文正文长度出错");
			return null;
		}

		// 读取报文数据段

		byte[] dataSection = new byte[realLength];
		int readLength = 0;
		while (readLength < realLength) {
			charsRead = in.read(dataSection, readLength, realLength - readLength);
			if (charsRead == -1 || charsRead == 0) {
				log.error("从socket中没读到内容");
				return null;
			}
			readLength += charsRead;
		}

		// 解压缩
		//return ZipUtil.unzip(dataSection);
		byte[] read = null;
		switch (Integer.valueOf(zipFlag)) {
		case 0://非压缩方式
			read = dataSection;
			break;
//		case 1://1： LZARI压缩方式
//			break;
		case 2://2： ZIP压缩方式
			read = ZipUtil.unzip(dataSection);
			break;
		case 3://3： GZIP压缩方式
			read = ZipUtil.ungzip(dataSection);
			break;
		default:
		    break;
		}
		log.info("C端返回报文："+new String(read));
		return read;
	}
   
	
	/**
	 * 从socket通道上读取数据，并将读到的数据解压缩
	 */
	
	public static String readSocket2(DataInputStream in) throws IOException,ZipException {
		
		if (in == null){
			throw new NullPointerException("readSocket方法输入参数为空");
		}

		byte[] dataByte = new byte[100 * 1024];
		int count = 0;
		String zipFlag=null;
		while (true) {
			
     		//读取报文信息段

			byte[] headSection = new byte[8];
			int charsRead;

			charsRead = in.read(headSection, 0, 8);
			log.info("C端返回报文头："+new String(headSection));
			zipFlag = new String(headSection, 5, 1);
			if (charsRead == -1) {
				log.error("从socket中没读到内容");
				return null;
			}

			int realLength = 0;
			try {
				realLength = Integer.parseInt(new String(headSection, 0, 4)) - 8;
			} catch (NumberFormatException e) {
				log.error("获取报文正文长度出错");
				return null;
			}

			//读取报文数据段
			
			byte[] dataSection = new byte[realLength];
			int readLength = 0;
			while (readLength < realLength) {
				charsRead = in.read(dataSection, readLength, realLength - readLength);
				if (charsRead == -1 || charsRead == 0) {
					log.error("从socket中没读到内容");
					return null;
				}
				readLength += charsRead;
			}
			
			System.arraycopy(dataSection, 0, dataByte, count, realLength);
			count += realLength;
			
			//判断是否有后续包（T表示无后续包）
			
			String endFlag = new String(headSection, 4, 1);
			if (endFlag.equalsIgnoreCase("T")) {
				break;
			} else {
				continue;
			}
		}		
		
		byte[] beforeUnzip = new byte[count];
		System.arraycopy(dataByte, 0, beforeUnzip, 0, count);
		
		//解压缩

//		return new String(ZipUtil.unzip(beforeUnzip));
		String read = null;
		switch (Integer.valueOf(zipFlag)) {
		case 0://非压缩方式
			read = new String(beforeUnzip);
			break;
//		case 1://1： LZARI压缩方式
//			break;
		case 2://2： ZIP压缩方式
			read = new String(ZipUtil.unzip(beforeUnzip));
			break;
		case 3://3： GZIP压缩方式
			read = new String(ZipUtil.ungzip(beforeUnzip));
			break;
		default:
		    break;
		}
		log.info("C端返回报文："+new String(read));
		return read;
	}

	/**
	 * 从socket通道上读取数据，并将读到的数据解压缩  拆包传输 2009年3月9日
	 */
	
	public static byte[] readSocket3(DataInputStream in) throws IOException,ZipException {
		
		if (in == null){
			throw new NullPointerException("readSocket方法输入参数为空");
		}
		log.info("开始接包in["+in+"]");
		byte[] dataByte = new byte[100 * 1024];
		int count = 0;
		String zipFlag=null;
		while (true) {
			
     		//读取报文信息段
			byte[] headSection = new byte[8];
			int charsRead;
			charsRead = in.read(headSection, 0, 8);
			log.info("C端返回报文头："+new String(headSection));
			if (charsRead == -1) {
				log.error("从socket中没读到内容");
				return null;
			}
			String inhead = new String(headSection, 0, 8);
			zipFlag=new String(headSection, 5, 1);
			log.info("包头in["+in+"]["+inhead+"]");
			int realLength = 0;
			try {
				realLength = Integer.parseInt(new String(headSection, 0, 4)) - 8;
			} catch (NumberFormatException e) {
				log.error("获取报文正文长度出错");
				return null;
			}
			//读取报文数据段
			
			byte[] dataSection = new byte[realLength];
			int readLength = 0;
			while (readLength < realLength) {
				charsRead = in.read(dataSection, readLength, realLength - readLength);
				if (charsRead == -1 || charsRead == 0) {
					log.error("从socket中没读到内容");
					return null;
				}
				readLength += charsRead;
			}
			
			System.arraycopy(dataSection, 0, dataByte, count, realLength);
			count += realLength;
			log.info("小包in["+in+"]包长["+dataSection.length+"]");
			//判断是否有后续包（T表示无后续包）
			
			String endFlag = new String(headSection, 4, 1);
			if (endFlag.equalsIgnoreCase("T")) {
				break;
			} else {
				continue;
			}
		}		
		
		byte[] beforeUnzip = new byte[count];
		System.arraycopy(dataByte, 0, beforeUnzip, 0, count);
		log.info("解压前in["+in+"]包长["+beforeUnzip.length+"]");
		//解压缩
//		return ZipUtil.unzip(beforeUnzip);
		byte[] read = null;
		switch (Integer.valueOf(zipFlag)) {
		case 0://非压缩方式
			read = beforeUnzip;
			break;
//		case 1://1： LZARI压缩方式
//			break;
		case 2://2： ZIP压缩方式
			read = ZipUtil.unzip(beforeUnzip);
			break;
		case 3://3： GZIP压缩方式
			read = ZipUtil.ungzip(beforeUnzip);
			break;
		default:
		    break;
		}
		log.info("C端返回报文："+new String(read));
		return read;
	}
	
	public static byte[] readSocketNocash(DataInputStream in) throws IOException,ZipException {

		if (in == null) {
			throw new NullPointerException("readSocket方法输入参数为空");
		}

		// 读取报文信息段

		byte[] headSection = new byte[2];
		int charsRead;

		
		charsRead = in.read(headSection, 0, 2);
		if (charsRead == -1) {
			log.error("从socket中没读到内容");
			return null;
		}
		//log.info(String.format("%x" , headSection[1]));
		int realLength = 0;
		try {
			realLength = headSection[1]-2;
			if (realLength<0)
			{
				realLength = 256+headSection[1]-2;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error("获取报文正文长度出错");
			return null;
		}
		log.info("报文长度："+realLength);
		// 读取报文数据段

		byte[] dataSection = new byte[realLength];
		int readLength = 0;
		while (readLength < realLength) {
			charsRead = in.read(dataSection, readLength, realLength - readLength);
			if (charsRead == -1 || charsRead == 0) {
				log.error("从socket中没读到内容");
				return null;
			}
			readLength += charsRead;
		}

		// 解压缩
		log.info("C端返回报文："+new String(dataSection));
		return dataSection;

	}
	
	/**
	 * 从socket通道上读取数据，并将读到的数据解压缩，将返回码赋给retCode
	 */

	public static byte[] readSocket3(DataInputStream in, StringBuffer retCode)throws IOException, ZipException {

		if (in == null || retCode == null){
			throw new IllegalArgumentException("readSocket输入参数为空");
		}
		
		byte[] dataByte = new byte[100 * 1024];
		int count = 0;

		String zipFlag = "0";
		while (true) {
			
			//读取报文信息段

			byte[] headSection = new byte[8];
			int charsRead;

			charsRead = in.read(headSection, 0, 8);
			log.info("C端返回报文头："+new String(headSection));
			if (charsRead == -1) {
				log.error("从socket中没读到内容");
				return null;
			}

			int realLength = 0;
			try {
				realLength = Integer.parseInt(new String(headSection, 0, 4)) - 8;
			} catch (NumberFormatException e) {
				log.error("获取报文正文长度出错");
				return null;
			}

			//读取报文数据段
			
			byte[] dataSection = new byte[realLength];
			int readLength = 0;
			while (readLength < realLength) {
				charsRead = in.read(dataSection, readLength, realLength - readLength);
				if (charsRead == -1 || charsRead == 0) {
					log.error("从socket中没读到内容");
					return null;
				}
				readLength += charsRead;
			}

			System.arraycopy(dataSection, 0, dataByte, count, realLength);
			count += realLength;
			
            //判断是否有后续包（T表示无后续包）
			
			String endFlag = new String(headSection, 4, 1);
			zipFlag = new String(headSection, 5, 1);
			if (endFlag.equalsIgnoreCase("T")) {
				break;
			} else {
				continue;
			}
		}
		
		byte[] beforeUnzip = new byte[count];
		System.arraycopy(dataByte, 0, beforeUnzip, 0, count);
		
		//解压缩
		
		//byte[] byteUnzip = (zipFlag.equals("2"))?ZipUtil.unzip(beforeUnzip):beforeUnzip;
		
		byte[] byteUnzip = null;
		switch (Integer.valueOf(zipFlag)) {
		case 0://非压缩方式
			byteUnzip = beforeUnzip;
			break;
//		case 1://1： LZARI压缩方式
//			break;
		case 2://2： ZIP压缩方式
			byteUnzip = ZipUtil.unzip(beforeUnzip);
			break;
		case 3://3： GZIP压缩方式
			byteUnzip = ZipUtil.ungzip(beforeUnzip);
			break;
		default:
		    break;
		}
		log.info("C端返回报文："+new String(byteUnzip));
		//取返回码
		
		String ret = new String(byteUnzip, 1, 2);
		retCode.append(ret);
		
		//取数据
		
		int contentLength = byteUnzip.length - 4;
		byte[] contentByte = new byte[contentLength];
		System.arraycopy(byteUnzip, 4, contentByte, 0, contentLength);
		return contentByte;
	}

	
	/**
	 * 发送命令信息到RVS服务器或Remote客户端并接收其返回内容(采用多包传输的方式)
	 * @param sndBody 发送内容(采用XML报文格式)
	 * @param ip ip地址
	 * @param strPort 端口号
	 * @param retBody 返回内容
	 * @param packetLen v端每包传输大小
	 * @param vzipType v端报文压缩方式
	 * @return true:发送成功 false:发送失败
	 */
	public static boolean sendCmdToRvcMutil(String sndBody, String ip, String strPort,StringBuffer retBody,int connTime,int soTime,int packetLen,int vzipType) 
	{
		
		if (sndBody == null || ip == null || strPort == null || retBody == null) 
		{
			throw new IllegalArgumentException("参数非法");
		}

		//log.info("send to RVS:" + sndBody);

		int port = Integer.parseInt(strPort);
		if (port == -1) 
		{
			retBody.append("通讯端口出错:" + strPort);
			return false;
		}
		
		log.info("v端发送报文=["+sndBody+"]");
//		log.info("v端发送报文长度=["+sndBody.length()+"]");
		byte[] byteSnd = sndBody.getBytes();
		byte[] byteZip;
		try 
		{
			switch (vzipType) {
			case 0://非压缩方式
				byteZip = byteSnd;
				break;
//			case 1://1： LZARI压缩方式
//				break;
			case 2://2： ZIP压缩方式
				byteZip = ZipUtil.zip(byteSnd);
				break;
			case 3://3： GZIP压缩方式
				byteZip = ZipUtil.gzip(byteSnd);
				break;
			default:
				retBody.append("压缩方式错误");
			    return false;
			} 
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			retBody.append("发送消息前压缩失败");
			return false;
		}
		
				
		Socket s = null;
		DataInputStream r = null;
		OutputStream out = null;
		
		
		
		// 组织发送报文
		StringBuffer head = new StringBuffer();
		
		int sendTimes=(byteZip.length/packetLen);// 发包次数
		
		if(byteZip.length%packetLen>0)
		{
			sendTimes++;
		}		
		
		try 
		{
			s = new Socket();
			InetSocketAddress isa=new InetSocketAddress(ip, port);
			s.connect(isa, connTime*1000);//设置连接超时
			s.setSoTimeout(soTime*1000);//设置超时时间1分钟,add by ssli 2008-06-23
			out = s.getOutputStream();

			r = new DataInputStream(s.getInputStream());
			
			int startLen=0;//截取压缩包开始字节数
			int endLen=0; //截取压缩包结束字节数
			int zipLen=0;//每次发送压缩包的字节数
			//多包传输
			for(int i=1;i<=sendTimes;i++)
			{								
				if(i==sendTimes)
				{
					endLen=byteZip.length;
					zipLen=endLen-startLen;
					head.append(String.format("%04d",  zipLen+ 8)); // 数据长度
					head.append("T"); // 报文结束
				}
				else
				{
					zipLen=packetLen;
					endLen+=zipLen;	
					
					head.append(String.format("%04d", zipLen + 8)); // 数据长度
					head.append("N"); // 报文未结束
				}
				
				//log.info("startLen:"+startLen);
				//log.info("endLen:"+endLen);
				
				head.append(vzipType); // 压缩方式
				head.append("00"); // 保留位
				
				log.info("v端报文头:"+head.toString());
				
				byte[] byteSend = new byte[8 + zipLen];
				
				System.arraycopy(head.toString().getBytes(), 0, byteSend, 0, 8);		
				System.arraycopy(byteZip, startLen, byteSend, 8, zipLen);
				
				out.write(byteSend);
				out.flush();
				startLen=endLen;
				head.delete(0, head.capacity());//清除StringBuffer中的内容
			}


			byte[] dataByte = new byte[100 * 1024];
			int count = 0;
			String zipFlag = "0";
			while (true) 
			{
				byte[] packSection = new byte[10];
				byte[] dataSection = new byte[10 * 1024];
				int byteRead = 0;
				// 读信息段数据
				byteRead = r.read(packSection, 0, 8);
				
				if (byteRead == -1) 
				{
					retBody.append("从SOCKECT中未读取任何字符");
					return false;
				}

				// 获得数据长度
				int realLength = 0;
				try 
				{
					log.info("C端返回报文头="+new String(packSection, 0, 8));
					realLength = Integer.parseInt(new String(packSection, 0, 4));	
				} 
				catch (NumberFormatException e) 
				{
					e.printStackTrace();
					retBody.append("长度转换错误");
					return false;
				}
				// 读数据段数据
				byteRead = r.read(dataSection, 0, realLength-8);
				
				if (byteRead == -1) 
				{
					retBody.append("读取消息正文内容出错");
					return false;
				}
//				StringBuffer sb=null;
//				System.out.println("count="+count+"-----------------------------------------------------");
//				System.out.print("包内容");
//				for (int i=0;i<realLength-8;i++){
//					System.out.print(dataSection[count+i]);
//				}
//				System.out.println("");
//				System.out.println("dataSection="+new String(dataSection));
////				log.info("包内容dataSection="+sb.toString());
//				System.out.println("count="+count+"-----------------------------------------------------");
				System.arraycopy(dataSection, 0, dataByte, count,realLength - 8);
				
				count += (realLength - 8);
				
				// 判断是否有后续包.T标示无后续包
				String continueFlag = new String(packSection, 4, 1);
				
				zipFlag = new String(packSection, 5, 1);
				
				if (continueFlag.equalsIgnoreCase("T")) 
				{
					break;
				} else {
					continue; 
				}
			}
			
			// 解压缩
			byte[] beforeUnzip = new byte[count];	
			
			System.arraycopy(dataByte, 0, beforeUnzip, 0, count);
			
		
			byte[] read = null;
			try 
			{
//				if(zipFlag.equals("0"))
//				{
//					read = beforeUnzip;
//				}
//				else if(zipFlag.equals("2"))
//				{
//					read = ZipUtil.unzip(beforeUnzip);
//				}
//				else if(zipFlag.equals("3")){
//					read = ZipUtil.ungzip(beforeUnzip);
//				}else{
//					
//				}
				switch (Integer.valueOf(zipFlag)) {
				case 0://非压缩方式
					read = beforeUnzip;
					break;
//				case 1://1： LZARI压缩方式
//					break;
				case 2://2： ZIP压缩方式
					read = ZipUtil.unzip(beforeUnzip);
					break;
				case 3://3： GZIP压缩方式
					read = ZipUtil.ungzip(beforeUnzip);
					break;
				default:
					retBody.append("压缩方式错误");
				    return false;
				} 
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				retBody.append("解压缩IO异常");
				return false;
			}
			log.info("C端返回报文："+new String(read));
			
			String rcvStr = (new String(read)).trim();
			retBody.append(rcvStr);
			//log.info("retBody:" + retBody);

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			retBody.append("IO异常");
			return false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			retBody.append("异常");
			return false;
		} 
		finally 
		{			
			try 
			{	
				if(r!=null)
		    	{  
			    	r.close();			    
		    	}
				
				if(out!=null)
	    		{		    	
	    			out.close();		    
	    		}	
				
				if (s != null) 
				{
					s.close();
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				retBody.append("异常");
				return false;
			}

		}
		return true;
	}
	
	/**
	 * 发送命令信息到os2客户端并接收其返回内容(采用多包传输的方式)
	 * @param sndBody 发送内容(采用定长报文格式)
	 * @param ip ip地址
	 * @param strPort 端口号
	 * @param retBody 返回内容
	 * @return true:发送成功 false:发送失败
	 */
	public static boolean sendCmdToOs2(String sndBody, String ip, String strPort,StringBuffer retBody,int connTime,int soTime) 
	{
		
		if (sndBody == null || ip == null || strPort == null || retBody == null) 
		{
			throw new IllegalArgumentException("参数非法");
		}


		int port = Integer.parseInt(strPort);
		if (port == -1) 
		{
			retBody.append("通讯端口出错:" + strPort);
			return false;
		}
		
		byte[] byteSnd = sndBody.getBytes();
		
		
		
				
		Socket s = null;		
		DataInputStream r = null;
		OutputStream out = null;
		
		int sendTimes=(byteSnd.length/8000);// 发包次数
		
		if(byteSnd.length%8000>0)
		{
			sendTimes++;
		}		
		
		try 
		{
			s = new Socket();
			InetSocketAddress isa=new InetSocketAddress(ip, port);
			s.connect(isa,connTime*1000);//设置连接超时
			s.setSoTimeout(soTime*1000);//设置超时时间1分钟,add by ssli 2008-06-23
			log.info("sendTimes:"+sendTimes);
			out = s.getOutputStream();

			r = new DataInputStream(s.getInputStream());
			
			int startLen=0;//截取压缩包开始字节数
			int endLen=0; //截取压缩包结束字节数
			int zipLen=0;//每次发送压缩包的字节数
			//多包传输
			
			for(int i=1;i<=sendTimes;i++)
			{								
				if(i==sendTimes)
				{
					endLen=byteSnd.length;
					zipLen=endLen-startLen;
				}
				else
				{
					zipLen=8000;
					endLen+=zipLen;
				}
				
				//log.info("startLen:"+startLen);
				//log.info("endLen:"+endLen);
				

				byte[] byteSend = new byte[zipLen];
						
				System.arraycopy(byteSnd, startLen, byteSend, 0, zipLen);
				
				out.write(byteSend);
				out.flush();
				startLen=endLen;
			}


			byte[] dataSection = new byte[10 * 1024];
			int byteRead = 0;
			// 读信息段数据
			
			byteRead = r.read(dataSection);
			log.info("dataSection:"+byteRead+"|"+new String(dataSection).trim());
			if (byteRead == -1) 
			{
				retBody.append("从SOCKECT中未读取任何字符");	
			}

			String rcvStr = (new String(dataSection)).trim();
			retBody.append(rcvStr);
			log.info("retBody:" + retBody);

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			retBody.append("IO异常");
			return false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			retBody.append("异常");
			return false;
		} 
		finally 
		{			
			try 
			{	
				if(r!=null)
		    	{  
			    	r.close();			    
		    	}
				
				if(out!=null)
	    		{		    	
	    			out.close();		    
	    		}	
				
				if (s != null) 
				{
					s.close();
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				retBody.append("异常");
				return false;
			}

		}
		return true;
	}
	
	public static final String bytesToHexString(byte[] bArray) {
	    StringBuffer sb = new StringBuffer(bArray.length);
	    String sTemp;
	    for (int i = 0; i < bArray.length; i++) {
	     sTemp = Integer.toHexString(0xFF & bArray[i]);
	     sb.append("0x");
	     if (sTemp.length() < 2)
	      sb.append(00);
//	     sb.append(sTemp.toUpperCase());
	     sb.append(sTemp);
	    }
	    return sb.toString();
	}
	/*运行管理远程操作remote端和V端的通讯socket超时-----
	序号	交易代码 	交易名称	超时时间(秒)
	1	200024	设备复位	300
	2	200027	逻辑关	300
	3	200026	逻辑开	300
	4	200028	状态检测	600
	5	200030	设备参数下载	300
	6	200031	获取功能列表	300*/
	public static int getsoTime(String cmdId) 
	{
		int soTime;//设置超时时间
		if(cmdId.equals("200024")||cmdId.equals("200027")||cmdId.equals("200026")||cmdId.equals("200030")||cmdId.equals("200031")){
			soTime=360;
		}else if(cmdId.equals("200028")){
			soTime=660;
		}else{
//			soTime=30;
			soTime=300; //根据实际情况，需要延长超时时间。 
		}
		return soTime;
	}
	
	/**
	 * 发送报文（报文头+报文体sendBody）到remote
	 * @param OutputStream 输出流
	 * @param sendBody 发送内容(采用XML报文格式)
	 * @param packetLen v端每包传输大小
	 * @param vzipType v端报文压缩方式
	 * @return true:发送成功 false:发送失败
	 */
	public static Boolean sendMessage(OutputStream out,byte[] sendBody,int packetLen,int vzipType,StringBuffer retMsg) 
	{
		log.debug("v端发送报文=["+new String(sendBody)+"]");
		byte[] byteZip;
		try 
		{
			switch (vzipType) {
			case 0://非压缩方式
				byteZip = sendBody;
				break;
//			case 1://1： LZARI压缩方式
//				break;
			case 2://2： ZIP压缩方式
				byteZip = ZipUtil.zip(sendBody);
				log.debug("v端发送报文zip=["+new String(byteZip)+"]");
				log.debug("v端发送的报文unzip=["+new String(ZipUtil.unzip(byteZip))+"]");
				break;
			case 3://3： GZIP压缩方式
				byteZip = ZipUtil.gzip(sendBody);
				log.debug("v端发送的报文gzip=["+new String(byteZip)+"]");
				log.debug("v端发送的报文ungzip=["+new String(ZipUtil.ungzip(byteZip))+"]");
				break;
			default:
				retMsg.append("压缩方式错误");
			    return false;
			} 
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			retMsg.append("发送消息前压缩失败");
			return false;
		}
				
		// 组织发送报文
		StringBuffer head = new StringBuffer();
		
		int sendTimes=(byteZip.length/packetLen);// 发包次数
		
		if(byteZip.length%packetLen>0)
		{
			sendTimes++;
		}		
		
		try 
		{
			int startLen=0;//截取压缩包开始字节数
			int endLen=0; //截取压缩包结束字节数
			int zipLen=0;//每次发送压缩包的字节数
			//多包传输
			for(int i=1;i<=sendTimes;i++)
			{								
				if(i==sendTimes)
				{
					endLen=byteZip.length;
					zipLen=endLen-startLen;
					head.append(String.format("%04d",  zipLen+ 8)); // 数据长度
					head.append("T"); // 报文结束
				}
				else
				{
					zipLen=packetLen;
					endLen+=zipLen;	
					
					head.append(String.format("%04d", zipLen + 8)); // 数据长度
					head.append("N"); // 报文未结束
				}
				
				//log.info("startLen:"+startLen);
				//log.info("endLen:"+endLen);
				
				head.append(vzipType); // 压缩方式
				head.append("00"); // 保留位
				log.info("V端发送报文头head："+head);
				//log.info("head:"+head.toString());
				
				byte[] byteSend = new byte[8 + zipLen];
				
				System.arraycopy(head.toString().getBytes(), 0, byteSend, 0, 8);		
				System.arraycopy(byteZip, startLen, byteSend, 8, zipLen);
				
				out.write(byteSend);
				out.flush();
				startLen=endLen;
				head.delete(0, head.capacity());//清除StringBuffer中的内容
			}
			return true;
		}	
		catch (IOException e) 
		{
			e.printStackTrace();
			retMsg.append("IO异常");
			return false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			retMsg.append("异常");
			return false;
		} 
	}
	
}