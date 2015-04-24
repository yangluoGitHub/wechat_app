package com.weili.wechat.common;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.Socket;
import com.weili.wechat.common.FileUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class ResumeFileUtil 
{

	private static Log log=LogFactory.getLog(ResumeFileUtil .class);	

	/**
	 * 根据上次文件传输情况，决定文件是重新开始传还是续传
	 * */
	public static String resumeFileTrans(String ip,String strPort,String locFile,
			String remoteFile,String remoteIp,String terminalNo,int type,int connTime,int soTime,int vzipType)
	{
		if (ip==null||strPort==null||locFile==null||remoteFile==null||remoteIp==null||terminalNo==null) 
		{
			return "系统参数错误！";			
		}
		File outFile=new File(locFile);
		if(!outFile.exists()||outFile.length()==0)
		{
//			return getRvcFile(ip,strPort,locFile,remoteFile,remoteIp,terminalNo,type,connTime,soTime);
			return FileUtil.getRvcFile(ip, strPort, locFile, remoteFile, remoteIp, terminalNo, type, connTime, soTime, vzipType);
		}
		else
		{
			return fileResume(ip,strPort,locFile,remoteFile,remoteIp,terminalNo,type,connTime,soTime,outFile.length());
		}
	}
	
//	/**
//	 * 从远程服务器或客户端取文件到本地
//	 **/
//	public static String getRvcFile(String ip, String strPort, String locFile,
//			String remoteFile, String remoteIp, String terminalNo, int type,int connTime,int soTime) 
//	{
//		Socket s = null;
//		DataInputStream r = null;
//		DataOutputStream w = null;
//		FileOutputStream to = null;
//		BufferedOutputStream bos=null;
//		int port;
//		
//		try
//		{
//			port = Integer.parseInt(strPort);
//		}
//		catch(Exception e)
//		{			
//			e.printStackTrace();
//			return "服务器端口错误,strPort:" + strPort;			
//		}
//		
//		try 
//		{
//			s = new Socket();
//			InetSocketAddress isa = new InetSocketAddress(ip, port);
//			s.connect(isa, connTime*1000);
//			s.setSoTimeout(soTime*1000);
//			w = new DataOutputStream(s.getOutputStream());
//			r = new DataInputStream(s.getInputStream());
//			
//			/*文件请求*/
//			String firstSend = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\" ?>")
//								   .append("<root>")
//								   .append("<downfiletype>").append(type).append("</downfiletype>")
//								   .append("<hostinfo>")
//								   .append("<termno>").append(terminalNo).append("</termno>")
//								   .append("<hostip>").append(remoteIp).append("</hostip>")
//								   .append("</hostinfo>")
//								   .append("<cmdid>0101</cmdid>")
//								   .append("<filename>").append(remoteFile).append("</filename>")
//								   .append("</root>").toString();
//
//			File to_file = new File(locFile);
//			to = new FileOutputStream(to_file);
//			bos=new BufferedOutputStream(to);
//			w.write(StringUtil.preProcess(firstSend.getBytes(),2));
//			w.flush();
//			StringBuffer ret0 = new StringBuffer();
//			
//			byte[] b = SocketUtil.readSocket3(r, ret0);
//			if (b == null) 
//			{				
//				return "接收文件失败";				
//			}
//			if (!ret0.toString().equals("00")) 
//			{
//				return "接收文件失败，失败原因:" + tranCode(ret0.toString());				
//			}			
//			
//			int maxfileLength=0;
//			
//			try
//			{
//				
//				String [] messageArray=(new String(b,0,b.length)).split("\\|");		
//				maxfileLength = Integer.parseInt(messageArray[4].replace(" ", ""));
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}			
//			int position = 0;
//
//			/*接受文件*/
//			for (;;) 
//			{
//				StringBuffer secondSend = new StringBuffer();
//				secondSend.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?>")
//						  .append("<root><cmdid>0102</cmdid>")
//						  .append("<startpos>").append(position).append("</startpos>")
//						  .append("</root>");				
//				
//				w.write(StringUtil.preProcess(secondSend.toString().getBytes(),2));
//				w.flush();
//
//				StringBuffer ret = new StringBuffer();
//				byte[] readByte =  SocketUtil.readSocket3(r, ret);						
//				
//				if (readByte == null) 
//				{					
//					return "接收文件失败，原因:解压缩失败";
//					
//				}			
//				
//				if (ret.toString().equals("21")&&(position<=maxfileLength)) 
//				{
//					bos.write(readByte);
//					bos.flush();
//					position += readByte.length;					
//					
//				} 
//				else if (ret.toString().equals("04")|| ret.toString().equals("20")) 
//				{
//					break;
//				}
//				else if (ret.toString().equals("01")) 
//				{					
//					return "接收文件失败，，失败原因:" + tranCode(ret.toString());					
//				}
//				else
//				{
//					break;
//				}
//			}
//		} 
//		catch (IOException e) 
//		{			
//			e.printStackTrace();
//			return "操作失败："+e.getMessage();			
//		} 
//		catch (Exception e) 
//		{			
//			e.printStackTrace();
//			return "操作失败："+e.getMessage();
//			
//		} 
//		finally 
//		{
//			try 
//			{
//				if (r != null)
//				{
//					r.close();
//				}
//			}
//			catch (IOException e) 
//			{				
//				e.printStackTrace();
//				return "操作失败："+e.getMessage();				
//			} 
//			catch (Exception e) 
//			{				
//				e.printStackTrace();
//				return "操作失败："+e.getMessage();				
//			}			
//			
//			try 
//			{
//				if (w != null) 
//				{
//					w.close();
//				}
//			}
//			catch (IOException e) 
//			{				
//				e.printStackTrace();
//				return "操作失败："+e.getMessage();				
//			} 
//			catch (Exception e) 
//			{				
//				e.printStackTrace();
//				return "操作失败："+e.getMessage();				
//			}
//			
//			try 
//			{
//				if (s != null) 
//				{
//					s.close();
//				}
//			}
//			catch (IOException e) 
//			{				
//				e.printStackTrace();
//				return "操作失败："+e.getMessage();				
//			} 
//			catch (Exception e) 
//			{				
//				e.printStackTrace();
//				return "操作失败："+e.getMessage();				
//			}
//			try 
//			{
//				if (to != null) 
//				{
//					to.close();
//				}
//			}
//			catch (IOException e) 
//			{				
//				e.printStackTrace();
//				return "操作失败："+e.getMessage();				
//			} 
//			catch (Exception e) 
//			{				
//				e.printStackTrace();
//				return "操作失败："+e.getMessage();				
//			}
//			try 
//			{
//				if (bos != null) 
//				{
//					bos.close();
//				}
//			}
//			catch (IOException e) 
//			{				
//				e.printStackTrace();
//				return "操作失败："+e.getMessage();				
//			} 
//			catch (Exception e) 
//			{				
//				e.printStackTrace();
//				return "操作失败："+e.getMessage();				
//			}			
//		}
//		return "SUC0000";
//	}
	/**
	 * 从远程服务器或客户端取文件到本地（断点续传）
	 **/
	public static String fileResume(String ip, String strPort, String locFile,
			String remoteFile, String remoteIp, String terminalNo, int type,int connTime,int soTime,long startPos) 
	{
		Socket s = null;
		DataInputStream r = null;
		DataOutputStream w = null;
		RandomAccessFile raFile = null;

		int port;
		
		try
		{
			port = Integer.parseInt(strPort);
		}
		catch(Exception e)
		{			
			e.printStackTrace();
			return "服务器端口错误,strPort:" + strPort;			
		}
		
		try 
		{
			s = new Socket();
			InetSocketAddress isa = new InetSocketAddress(ip, port);
			s.connect(isa, connTime*1000);
			s.setSoTimeout(soTime*1000);
			w = new DataOutputStream(s.getOutputStream());
			r = new DataInputStream(s.getInputStream());
			
			/*文件请求*/
			String firstSend = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\" ?>")
								   .append("<root>")
								   .append("<downfiletype>").append(type).append("</downfiletype>")
								   .append("<hostinfo>")
								   .append("<termno>").append(terminalNo).append("</termno>")
								   .append("<hostip>").append(remoteIp).append("</hostip>")
								   .append("</hostinfo>")
								   .append("<cmdid>0101</cmdid>")
								   .append("<filename>").append(remoteFile).append("</filename>")
								   .append("</root>").toString();

			raFile=new RandomAccessFile(locFile,"rw");;			
			w.write(StringUtil.preProcess(firstSend.getBytes(),2));
			w.flush();
			StringBuffer ret0 = new StringBuffer();
			
			byte[] b = SocketUtil.readSocket3(r, ret0);
			if (b == null) 
			{				
				return "接收文件失败";				
			}
			if (!ret0.toString().equals("00")) 
			{
				return "接收文件失败，失败原因:" + tranCode(ret0.toString());				
			}			
			
			int maxfileLength=0;
			
			try
			{
				
				String [] messageArray=(new String(b,0,b.length)).split("\\|");		
				maxfileLength = Integer.parseInt(messageArray[4].replace(" ", ""));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}			
			long position = startPos;

			/*接受文件*/
			for (;;) 
			{
				StringBuffer secondSend = new StringBuffer();
				secondSend.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?>")
						  .append("<root><cmdid>0102</cmdid>")
						  .append("<startpos>").append(position).append("</startpos>")
						  .append("</root>");				
				
				w.write(StringUtil.preProcess(secondSend.toString().getBytes(),2));
				w.flush();

				StringBuffer ret = new StringBuffer();
				byte[] readByte =  SocketUtil.readSocket3(r, ret);						
				
				if (readByte == null) 
				{					
					return "接收文件失败，原因:解压缩失败";
					
				}			
				
				if (ret.toString().equals("21")&&(position<=maxfileLength)) 
				{
					if(fileWrite(raFile,readByte,position,readByte.length)==-1)
					{
						return "写本地文件错误";
					}
					position += readByte.length;
				} 
				else if (ret.toString().equals("04")|| ret.toString().equals("20")) 
				{
					break;
				}
				else if (ret.toString().equals("01")) 
				{					
					return "接收文件失败，，失败原因:" + tranCode(ret.toString());					
				}
				else
				{
					break;
				}
			}
		} 
		catch (IOException e) 
		{			
			e.printStackTrace();
			return "操作失败："+e.getMessage();			
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return "操作失败："+e.getMessage();
			
		} 
		finally 
		{
			try 
			{
				if (r != null)
				{
					r.close();
				}
			}
			catch (IOException e) 
			{				
				e.printStackTrace();
				return "操作失败："+e.getMessage();				
			} 
			catch (Exception e) 
			{				
				e.printStackTrace();
				return "操作失败："+e.getMessage();				
			}			
			
			try 
			{
				if (w != null) 
				{
					w.close();
				}
			}
			catch (IOException e) 
			{				
				e.printStackTrace();
				return "操作失败："+e.getMessage();				
			} 
			catch (Exception e) 
			{				
				e.printStackTrace();
				return "操作失败："+e.getMessage();				
			}
			
			try 
			{
				if (s != null) 
				{
					s.close();
				}
			}
			catch (IOException e) 
			{				
				e.printStackTrace();
				return "操作失败："+e.getMessage();				
			} 
			catch (Exception e) 
			{				
				e.printStackTrace();
				return "操作失败："+e.getMessage();				
			}
			try 
			{
				if (raFile != null) 
				{
					raFile.close();
				}
			}
			catch (IOException e) 
			{				
				e.printStackTrace();
				return "操作失败："+e.getMessage();				
			} 
			catch (Exception e) 
			{				
				e.printStackTrace();
				return "操作失败："+e.getMessage();				
			}
		}
		return "SUC0000";
	}	
	/**
	 * 解析文件传输返回代码
	 * */
	public static String tranCode(String retCode)
	{
		if(retCode.equals("01")) return "打开文件失败";
		if(retCode.equals("02")) return "定位服务器端文件到指定的文件位置失败";
		if(retCode.equals("04")) return "服务器端文件不能再继续读取数据，文件已经结束";
		if(retCode.equals("30")) return "报文解析错误，指定的XML字段在请求报文中不存在";
		if(retCode.equals("20")) return "服务器端文件发送结束";
		if(retCode.equals("21")) return "报文的返回数据为文件数据，数据的长度为(报文长度-8)";
		if(retCode.equals("00")) return "服务器端文件准备完毕";
		if(retCode.equals("05")) return "文件已被更名或文件不存在,获取文件信息失败";
		if(retCode.equals("06")) return "其他错误";
		return retCode;
	}

	/**
	 * 从断点开始写文件，写失败返回-1，否则返回写入的长度
	 * */
	public static synchronized int fileWrite(RandomAccessFile oSavedFile,byte[] b,long startPos,int nLen)
	{
		int n = -1;
		try
		{
			oSavedFile.seek(startPos);//定位断点
			log.info("startPos="+startPos);
			log.info("nLen="+nLen);
			log.info("bSize="+b.length);			
			oSavedFile.write(b,0,nLen);
			n = nLen;
		}
		catch(IOException e)
		{
			e.printStackTrace ();
		}
		return n;
	}
	
	public static void main(String[] args){	

	   
	}	

}