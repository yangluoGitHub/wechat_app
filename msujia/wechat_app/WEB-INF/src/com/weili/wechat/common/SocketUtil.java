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
 * socket����ʵ����
 * 
 * @author hpshen
 * @since 2007.04.10
 */

public class SocketUtil {

	private static Log log = LogFactory.getLog(SocketUtil.class);

	/**
	 * ��socketͨ���϶�ȡ���ݣ��������������ݽ�ѹ��
	 */
	
	public static byte[] readSocket1(DataInputStream in) throws IOException,ZipException {

		if (in == null) {
			throw new NullPointerException("readSocket�����������Ϊ��");
		}

		// ��ȡ������Ϣ��

		byte[] headSection = new byte[8];
		int charsRead;

		charsRead = in.read(headSection, 0, 8);
		log.info("C�˷��ر���ͷ��"+new String(headSection));
		if (charsRead == -1) {
			log.error("��socket��û��������");
			return null;
		}

		int realLength = 0;
		String zipFlag = "0";
		try {
			realLength = Integer.parseInt(new String(headSection, 0, 4)) - 8;
			zipFlag = new String(headSection, 5, 1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error("��ȡ�������ĳ��ȳ���");
			return null;
		}

		// ��ȡ�������ݶ�

		byte[] dataSection = new byte[realLength];
		int readLength = 0;
		while (readLength < realLength) {
			charsRead = in.read(dataSection, readLength, realLength - readLength);
			if (charsRead == -1 || charsRead == 0) {
				log.error("��socket��û��������");
				return null;
			}
			readLength += charsRead;
		}

		// ��ѹ��
		//return ZipUtil.unzip(dataSection);
		byte[] read = null;
		switch (Integer.valueOf(zipFlag)) {
		case 0://��ѹ����ʽ
			read = dataSection;
			break;
//		case 1://1�� LZARIѹ����ʽ
//			break;
		case 2://2�� ZIPѹ����ʽ
			read = ZipUtil.unzip(dataSection);
			break;
		case 3://3�� GZIPѹ����ʽ
			read = ZipUtil.ungzip(dataSection);
			break;
		default:
		    break;
		}
		log.info("C�˷��ر��ģ�"+new String(read));
		return read;
	}
   
	
	/**
	 * ��socketͨ���϶�ȡ���ݣ��������������ݽ�ѹ��
	 */
	
	public static String readSocket2(DataInputStream in) throws IOException,ZipException {
		
		if (in == null){
			throw new NullPointerException("readSocket�����������Ϊ��");
		}

		byte[] dataByte = new byte[100 * 1024];
		int count = 0;
		String zipFlag=null;
		while (true) {
			
     		//��ȡ������Ϣ��

			byte[] headSection = new byte[8];
			int charsRead;

			charsRead = in.read(headSection, 0, 8);
			log.info("C�˷��ر���ͷ��"+new String(headSection));
			zipFlag = new String(headSection, 5, 1);
			if (charsRead == -1) {
				log.error("��socket��û��������");
				return null;
			}

			int realLength = 0;
			try {
				realLength = Integer.parseInt(new String(headSection, 0, 4)) - 8;
			} catch (NumberFormatException e) {
				log.error("��ȡ�������ĳ��ȳ���");
				return null;
			}

			//��ȡ�������ݶ�
			
			byte[] dataSection = new byte[realLength];
			int readLength = 0;
			while (readLength < realLength) {
				charsRead = in.read(dataSection, readLength, realLength - readLength);
				if (charsRead == -1 || charsRead == 0) {
					log.error("��socket��û��������");
					return null;
				}
				readLength += charsRead;
			}
			
			System.arraycopy(dataSection, 0, dataByte, count, realLength);
			count += realLength;
			
			//�ж��Ƿ��к�������T��ʾ�޺�������
			
			String endFlag = new String(headSection, 4, 1);
			if (endFlag.equalsIgnoreCase("T")) {
				break;
			} else {
				continue;
			}
		}		
		
		byte[] beforeUnzip = new byte[count];
		System.arraycopy(dataByte, 0, beforeUnzip, 0, count);
		
		//��ѹ��

//		return new String(ZipUtil.unzip(beforeUnzip));
		String read = null;
		switch (Integer.valueOf(zipFlag)) {
		case 0://��ѹ����ʽ
			read = new String(beforeUnzip);
			break;
//		case 1://1�� LZARIѹ����ʽ
//			break;
		case 2://2�� ZIPѹ����ʽ
			read = new String(ZipUtil.unzip(beforeUnzip));
			break;
		case 3://3�� GZIPѹ����ʽ
			read = new String(ZipUtil.ungzip(beforeUnzip));
			break;
		default:
		    break;
		}
		log.info("C�˷��ر��ģ�"+new String(read));
		return read;
	}

	/**
	 * ��socketͨ���϶�ȡ���ݣ��������������ݽ�ѹ��  ������� 2009��3��9��
	 */
	
	public static byte[] readSocket3(DataInputStream in) throws IOException,ZipException {
		
		if (in == null){
			throw new NullPointerException("readSocket�����������Ϊ��");
		}
		log.info("��ʼ�Ӱ�in["+in+"]");
		byte[] dataByte = new byte[100 * 1024];
		int count = 0;
		String zipFlag=null;
		while (true) {
			
     		//��ȡ������Ϣ��
			byte[] headSection = new byte[8];
			int charsRead;
			charsRead = in.read(headSection, 0, 8);
			log.info("C�˷��ر���ͷ��"+new String(headSection));
			if (charsRead == -1) {
				log.error("��socket��û��������");
				return null;
			}
			String inhead = new String(headSection, 0, 8);
			zipFlag=new String(headSection, 5, 1);
			log.info("��ͷin["+in+"]["+inhead+"]");
			int realLength = 0;
			try {
				realLength = Integer.parseInt(new String(headSection, 0, 4)) - 8;
			} catch (NumberFormatException e) {
				log.error("��ȡ�������ĳ��ȳ���");
				return null;
			}
			//��ȡ�������ݶ�
			
			byte[] dataSection = new byte[realLength];
			int readLength = 0;
			while (readLength < realLength) {
				charsRead = in.read(dataSection, readLength, realLength - readLength);
				if (charsRead == -1 || charsRead == 0) {
					log.error("��socket��û��������");
					return null;
				}
				readLength += charsRead;
			}
			
			System.arraycopy(dataSection, 0, dataByte, count, realLength);
			count += realLength;
			log.info("С��in["+in+"]����["+dataSection.length+"]");
			//�ж��Ƿ��к�������T��ʾ�޺�������
			
			String endFlag = new String(headSection, 4, 1);
			if (endFlag.equalsIgnoreCase("T")) {
				break;
			} else {
				continue;
			}
		}		
		
		byte[] beforeUnzip = new byte[count];
		System.arraycopy(dataByte, 0, beforeUnzip, 0, count);
		log.info("��ѹǰin["+in+"]����["+beforeUnzip.length+"]");
		//��ѹ��
//		return ZipUtil.unzip(beforeUnzip);
		byte[] read = null;
		switch (Integer.valueOf(zipFlag)) {
		case 0://��ѹ����ʽ
			read = beforeUnzip;
			break;
//		case 1://1�� LZARIѹ����ʽ
//			break;
		case 2://2�� ZIPѹ����ʽ
			read = ZipUtil.unzip(beforeUnzip);
			break;
		case 3://3�� GZIPѹ����ʽ
			read = ZipUtil.ungzip(beforeUnzip);
			break;
		default:
		    break;
		}
		log.info("C�˷��ر��ģ�"+new String(read));
		return read;
	}
	
	public static byte[] readSocketNocash(DataInputStream in) throws IOException,ZipException {

		if (in == null) {
			throw new NullPointerException("readSocket�����������Ϊ��");
		}

		// ��ȡ������Ϣ��

		byte[] headSection = new byte[2];
		int charsRead;

		
		charsRead = in.read(headSection, 0, 2);
		if (charsRead == -1) {
			log.error("��socket��û��������");
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
			log.error("��ȡ�������ĳ��ȳ���");
			return null;
		}
		log.info("���ĳ��ȣ�"+realLength);
		// ��ȡ�������ݶ�

		byte[] dataSection = new byte[realLength];
		int readLength = 0;
		while (readLength < realLength) {
			charsRead = in.read(dataSection, readLength, realLength - readLength);
			if (charsRead == -1 || charsRead == 0) {
				log.error("��socket��û��������");
				return null;
			}
			readLength += charsRead;
		}

		// ��ѹ��
		log.info("C�˷��ر��ģ�"+new String(dataSection));
		return dataSection;

	}
	
	/**
	 * ��socketͨ���϶�ȡ���ݣ��������������ݽ�ѹ�����������븳��retCode
	 */

	public static byte[] readSocket3(DataInputStream in, StringBuffer retCode)throws IOException, ZipException {

		if (in == null || retCode == null){
			throw new IllegalArgumentException("readSocket�������Ϊ��");
		}
		
		byte[] dataByte = new byte[100 * 1024];
		int count = 0;

		String zipFlag = "0";
		while (true) {
			
			//��ȡ������Ϣ��

			byte[] headSection = new byte[8];
			int charsRead;

			charsRead = in.read(headSection, 0, 8);
			log.info("C�˷��ر���ͷ��"+new String(headSection));
			if (charsRead == -1) {
				log.error("��socket��û��������");
				return null;
			}

			int realLength = 0;
			try {
				realLength = Integer.parseInt(new String(headSection, 0, 4)) - 8;
			} catch (NumberFormatException e) {
				log.error("��ȡ�������ĳ��ȳ���");
				return null;
			}

			//��ȡ�������ݶ�
			
			byte[] dataSection = new byte[realLength];
			int readLength = 0;
			while (readLength < realLength) {
				charsRead = in.read(dataSection, readLength, realLength - readLength);
				if (charsRead == -1 || charsRead == 0) {
					log.error("��socket��û��������");
					return null;
				}
				readLength += charsRead;
			}

			System.arraycopy(dataSection, 0, dataByte, count, realLength);
			count += realLength;
			
            //�ж��Ƿ��к�������T��ʾ�޺�������
			
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
		
		//��ѹ��
		
		//byte[] byteUnzip = (zipFlag.equals("2"))?ZipUtil.unzip(beforeUnzip):beforeUnzip;
		
		byte[] byteUnzip = null;
		switch (Integer.valueOf(zipFlag)) {
		case 0://��ѹ����ʽ
			byteUnzip = beforeUnzip;
			break;
//		case 1://1�� LZARIѹ����ʽ
//			break;
		case 2://2�� ZIPѹ����ʽ
			byteUnzip = ZipUtil.unzip(beforeUnzip);
			break;
		case 3://3�� GZIPѹ����ʽ
			byteUnzip = ZipUtil.ungzip(beforeUnzip);
			break;
		default:
		    break;
		}
		log.info("C�˷��ر��ģ�"+new String(byteUnzip));
		//ȡ������
		
		String ret = new String(byteUnzip, 1, 2);
		retCode.append(ret);
		
		//ȡ����
		
		int contentLength = byteUnzip.length - 4;
		byte[] contentByte = new byte[contentLength];
		System.arraycopy(byteUnzip, 4, contentByte, 0, contentLength);
		return contentByte;
	}

	
	/**
	 * ����������Ϣ��RVS��������Remote�ͻ��˲������䷵������(���ö������ķ�ʽ)
	 * @param sndBody ��������(����XML���ĸ�ʽ)
	 * @param ip ip��ַ
	 * @param strPort �˿ں�
	 * @param retBody ��������
	 * @param packetLen v��ÿ�������С
	 * @param vzipType v�˱���ѹ����ʽ
	 * @return true:���ͳɹ� false:����ʧ��
	 */
	public static boolean sendCmdToRvcMutil(String sndBody, String ip, String strPort,StringBuffer retBody,int connTime,int soTime,int packetLen,int vzipType) 
	{
		
		if (sndBody == null || ip == null || strPort == null || retBody == null) 
		{
			throw new IllegalArgumentException("�����Ƿ�");
		}

		//log.info("send to RVS:" + sndBody);

		int port = Integer.parseInt(strPort);
		if (port == -1) 
		{
			retBody.append("ͨѶ�˿ڳ���:" + strPort);
			return false;
		}
		
		log.info("v�˷��ͱ���=["+sndBody+"]");
//		log.info("v�˷��ͱ��ĳ���=["+sndBody.length()+"]");
		byte[] byteSnd = sndBody.getBytes();
		byte[] byteZip;
		try 
		{
			switch (vzipType) {
			case 0://��ѹ����ʽ
				byteZip = byteSnd;
				break;
//			case 1://1�� LZARIѹ����ʽ
//				break;
			case 2://2�� ZIPѹ����ʽ
				byteZip = ZipUtil.zip(byteSnd);
				break;
			case 3://3�� GZIPѹ����ʽ
				byteZip = ZipUtil.gzip(byteSnd);
				break;
			default:
				retBody.append("ѹ����ʽ����");
			    return false;
			} 
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			retBody.append("������Ϣǰѹ��ʧ��");
			return false;
		}
		
				
		Socket s = null;
		DataInputStream r = null;
		OutputStream out = null;
		
		
		
		// ��֯���ͱ���
		StringBuffer head = new StringBuffer();
		
		int sendTimes=(byteZip.length/packetLen);// ��������
		
		if(byteZip.length%packetLen>0)
		{
			sendTimes++;
		}		
		
		try 
		{
			s = new Socket();
			InetSocketAddress isa=new InetSocketAddress(ip, port);
			s.connect(isa, connTime*1000);//�������ӳ�ʱ
			s.setSoTimeout(soTime*1000);//���ó�ʱʱ��1����,add by ssli 2008-06-23
			out = s.getOutputStream();

			r = new DataInputStream(s.getInputStream());
			
			int startLen=0;//��ȡѹ������ʼ�ֽ���
			int endLen=0; //��ȡѹ���������ֽ���
			int zipLen=0;//ÿ�η���ѹ�������ֽ���
			//�������
			for(int i=1;i<=sendTimes;i++)
			{								
				if(i==sendTimes)
				{
					endLen=byteZip.length;
					zipLen=endLen-startLen;
					head.append(String.format("%04d",  zipLen+ 8)); // ���ݳ���
					head.append("T"); // ���Ľ���
				}
				else
				{
					zipLen=packetLen;
					endLen+=zipLen;	
					
					head.append(String.format("%04d", zipLen + 8)); // ���ݳ���
					head.append("N"); // ����δ����
				}
				
				//log.info("startLen:"+startLen);
				//log.info("endLen:"+endLen);
				
				head.append(vzipType); // ѹ����ʽ
				head.append("00"); // ����λ
				
				log.info("v�˱���ͷ:"+head.toString());
				
				byte[] byteSend = new byte[8 + zipLen];
				
				System.arraycopy(head.toString().getBytes(), 0, byteSend, 0, 8);		
				System.arraycopy(byteZip, startLen, byteSend, 8, zipLen);
				
				out.write(byteSend);
				out.flush();
				startLen=endLen;
				head.delete(0, head.capacity());//���StringBuffer�е�����
			}


			byte[] dataByte = new byte[100 * 1024];
			int count = 0;
			String zipFlag = "0";
			while (true) 
			{
				byte[] packSection = new byte[10];
				byte[] dataSection = new byte[10 * 1024];
				int byteRead = 0;
				// ����Ϣ������
				byteRead = r.read(packSection, 0, 8);
				
				if (byteRead == -1) 
				{
					retBody.append("��SOCKECT��δ��ȡ�κ��ַ�");
					return false;
				}

				// ������ݳ���
				int realLength = 0;
				try 
				{
					log.info("C�˷��ر���ͷ="+new String(packSection, 0, 8));
					realLength = Integer.parseInt(new String(packSection, 0, 4));	
				} 
				catch (NumberFormatException e) 
				{
					e.printStackTrace();
					retBody.append("����ת������");
					return false;
				}
				// �����ݶ�����
				byteRead = r.read(dataSection, 0, realLength-8);
				
				if (byteRead == -1) 
				{
					retBody.append("��ȡ��Ϣ�������ݳ���");
					return false;
				}
//				StringBuffer sb=null;
//				System.out.println("count="+count+"-----------------------------------------------------");
//				System.out.print("������");
//				for (int i=0;i<realLength-8;i++){
//					System.out.print(dataSection[count+i]);
//				}
//				System.out.println("");
//				System.out.println("dataSection="+new String(dataSection));
////				log.info("������dataSection="+sb.toString());
//				System.out.println("count="+count+"-----------------------------------------------------");
				System.arraycopy(dataSection, 0, dataByte, count,realLength - 8);
				
				count += (realLength - 8);
				
				// �ж��Ƿ��к�����.T��ʾ�޺�����
				String continueFlag = new String(packSection, 4, 1);
				
				zipFlag = new String(packSection, 5, 1);
				
				if (continueFlag.equalsIgnoreCase("T")) 
				{
					break;
				} else {
					continue; 
				}
			}
			
			// ��ѹ��
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
				case 0://��ѹ����ʽ
					read = beforeUnzip;
					break;
//				case 1://1�� LZARIѹ����ʽ
//					break;
				case 2://2�� ZIPѹ����ʽ
					read = ZipUtil.unzip(beforeUnzip);
					break;
				case 3://3�� GZIPѹ����ʽ
					read = ZipUtil.ungzip(beforeUnzip);
					break;
				default:
					retBody.append("ѹ����ʽ����");
				    return false;
				} 
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				retBody.append("��ѹ��IO�쳣");
				return false;
			}
			log.info("C�˷��ر��ģ�"+new String(read));
			
			String rcvStr = (new String(read)).trim();
			retBody.append(rcvStr);
			//log.info("retBody:" + retBody);

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			retBody.append("IO�쳣");
			return false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			retBody.append("�쳣");
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
				retBody.append("�쳣");
				return false;
			}

		}
		return true;
	}
	
	/**
	 * ����������Ϣ��os2�ͻ��˲������䷵������(���ö������ķ�ʽ)
	 * @param sndBody ��������(���ö������ĸ�ʽ)
	 * @param ip ip��ַ
	 * @param strPort �˿ں�
	 * @param retBody ��������
	 * @return true:���ͳɹ� false:����ʧ��
	 */
	public static boolean sendCmdToOs2(String sndBody, String ip, String strPort,StringBuffer retBody,int connTime,int soTime) 
	{
		
		if (sndBody == null || ip == null || strPort == null || retBody == null) 
		{
			throw new IllegalArgumentException("�����Ƿ�");
		}


		int port = Integer.parseInt(strPort);
		if (port == -1) 
		{
			retBody.append("ͨѶ�˿ڳ���:" + strPort);
			return false;
		}
		
		byte[] byteSnd = sndBody.getBytes();
		
		
		
				
		Socket s = null;		
		DataInputStream r = null;
		OutputStream out = null;
		
		int sendTimes=(byteSnd.length/8000);// ��������
		
		if(byteSnd.length%8000>0)
		{
			sendTimes++;
		}		
		
		try 
		{
			s = new Socket();
			InetSocketAddress isa=new InetSocketAddress(ip, port);
			s.connect(isa,connTime*1000);//�������ӳ�ʱ
			s.setSoTimeout(soTime*1000);//���ó�ʱʱ��1����,add by ssli 2008-06-23
			log.info("sendTimes:"+sendTimes);
			out = s.getOutputStream();

			r = new DataInputStream(s.getInputStream());
			
			int startLen=0;//��ȡѹ������ʼ�ֽ���
			int endLen=0; //��ȡѹ���������ֽ���
			int zipLen=0;//ÿ�η���ѹ�������ֽ���
			//�������
			
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
			// ����Ϣ������
			
			byteRead = r.read(dataSection);
			log.info("dataSection:"+byteRead+"|"+new String(dataSection).trim());
			if (byteRead == -1) 
			{
				retBody.append("��SOCKECT��δ��ȡ�κ��ַ�");	
			}

			String rcvStr = (new String(dataSection)).trim();
			retBody.append(rcvStr);
			log.info("retBody:" + retBody);

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			retBody.append("IO�쳣");
			return false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			retBody.append("�쳣");
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
				retBody.append("�쳣");
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
	/*���й���Զ�̲���remote�˺�V�˵�ͨѶsocket��ʱ-----
	���	���״��� 	��������	��ʱʱ��(��)
	1	200024	�豸��λ	300
	2	200027	�߼���	300
	3	200026	�߼���	300
	4	200028	״̬���	600
	5	200030	�豸��������	300
	6	200031	��ȡ�����б�	300*/
	public static int getsoTime(String cmdId) 
	{
		int soTime;//���ó�ʱʱ��
		if(cmdId.equals("200024")||cmdId.equals("200027")||cmdId.equals("200026")||cmdId.equals("200030")||cmdId.equals("200031")){
			soTime=360;
		}else if(cmdId.equals("200028")){
			soTime=660;
		}else{
//			soTime=30;
			soTime=300; //����ʵ���������Ҫ�ӳ���ʱʱ�䡣 
		}
		return soTime;
	}
	
	/**
	 * ���ͱ��ģ�����ͷ+������sendBody����remote
	 * @param OutputStream �����
	 * @param sendBody ��������(����XML���ĸ�ʽ)
	 * @param packetLen v��ÿ�������С
	 * @param vzipType v�˱���ѹ����ʽ
	 * @return true:���ͳɹ� false:����ʧ��
	 */
	public static Boolean sendMessage(OutputStream out,byte[] sendBody,int packetLen,int vzipType,StringBuffer retMsg) 
	{
		log.debug("v�˷��ͱ���=["+new String(sendBody)+"]");
		byte[] byteZip;
		try 
		{
			switch (vzipType) {
			case 0://��ѹ����ʽ
				byteZip = sendBody;
				break;
//			case 1://1�� LZARIѹ����ʽ
//				break;
			case 2://2�� ZIPѹ����ʽ
				byteZip = ZipUtil.zip(sendBody);
				log.debug("v�˷��ͱ���zip=["+new String(byteZip)+"]");
				log.debug("v�˷��͵ı���unzip=["+new String(ZipUtil.unzip(byteZip))+"]");
				break;
			case 3://3�� GZIPѹ����ʽ
				byteZip = ZipUtil.gzip(sendBody);
				log.debug("v�˷��͵ı���gzip=["+new String(byteZip)+"]");
				log.debug("v�˷��͵ı���ungzip=["+new String(ZipUtil.ungzip(byteZip))+"]");
				break;
			default:
				retMsg.append("ѹ����ʽ����");
			    return false;
			} 
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			retMsg.append("������Ϣǰѹ��ʧ��");
			return false;
		}
				
		// ��֯���ͱ���
		StringBuffer head = new StringBuffer();
		
		int sendTimes=(byteZip.length/packetLen);// ��������
		
		if(byteZip.length%packetLen>0)
		{
			sendTimes++;
		}		
		
		try 
		{
			int startLen=0;//��ȡѹ������ʼ�ֽ���
			int endLen=0; //��ȡѹ���������ֽ���
			int zipLen=0;//ÿ�η���ѹ�������ֽ���
			//�������
			for(int i=1;i<=sendTimes;i++)
			{								
				if(i==sendTimes)
				{
					endLen=byteZip.length;
					zipLen=endLen-startLen;
					head.append(String.format("%04d",  zipLen+ 8)); // ���ݳ���
					head.append("T"); // ���Ľ���
				}
				else
				{
					zipLen=packetLen;
					endLen+=zipLen;	
					
					head.append(String.format("%04d", zipLen + 8)); // ���ݳ���
					head.append("N"); // ����δ����
				}
				
				//log.info("startLen:"+startLen);
				//log.info("endLen:"+endLen);
				
				head.append(vzipType); // ѹ����ʽ
				head.append("00"); // ����λ
				log.info("V�˷��ͱ���ͷhead��"+head);
				//log.info("head:"+head.toString());
				
				byte[] byteSend = new byte[8 + zipLen];
				
				System.arraycopy(head.toString().getBytes(), 0, byteSend, 0, 8);		
				System.arraycopy(byteZip, startLen, byteSend, 8, zipLen);
				
				out.write(byteSend);
				out.flush();
				startLen=endLen;
				head.delete(0, head.capacity());//���StringBuffer�е�����
			}
			return true;
		}	
		catch (IOException e) 
		{
			e.printStackTrace();
			retMsg.append("IO�쳣");
			return false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			retMsg.append("�쳣");
			return false;
		} 
	}
	
}