package com.weili.wechat.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * �������ļ���ز���
 * 
 * @author hjtang
 * @since 2007.07.20
 */

public final class FileUtil {

	private static Log log = LogFactory.getLog(FileUtil.class);
	
	public static final String sysFileSeparator =  System.getProperty("file.separator");

	/**
	 * �ϴ��ļ�Http�����ϴ����ļ�
	 * 
	 * @param srcName
	 *            �����ҳ���DOM����
	 * @param desDoc
	 *            �ϴ�����ļ���(����·��)
	 * @param descFileName
	 *            �ϴ�����ļ��������ļ���(���Ϊ������ԭ���Ŀͻ���������ļ���)
	 * @return false���ϴ��ļ�ʧ�ܣ�true���ϴ��ļ��ɹ�
	 */

	public static String upLoadHttpRequestFile(HttpServletRequest request, String srcName, String desDoc, StringBuffer descFileName)
			throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(srcName);

		byte[] bytes = file.getBytes();

		// ����ļ������ڻ����ļ�Ϊ��
		if (bytes == null || bytes.length == 0) {
			return "�ļ������ڻ��ļ�Ϊ�գ�";
		}
		// if(bytes.length>20971520)
		// {
		// return "�ļ���С����20M����";
		// }

		// String
		// uploadDir=request.getSession().getServletContext().getRealPath(desDoc);

		String sep = System.getProperty("file.separator");// ����ϵͳ�ж����ļ���Ŀ¼б������

		File dirPath = new File(desDoc);

		if (!dirPath.isDirectory()) {
			dirPath.mkdirs();
		}
		String originalFile = file.getOriginalFilename();// ԭ�ļ���

		/* ���δ�����µ��ļ�����ʹ��ԭ�����ļ���������ʹ�ô�����ļ������ļ���׺����ԭ�������ļ���׺ */
		if (descFileName.toString().equals("")) {
			descFileName.append(originalFile);
		} else {
			descFileName.append((originalFile.lastIndexOf(".") > 0 ? originalFile.substring(originalFile.lastIndexOf("."),
					originalFile.length()) : ""));
		}

		File uploadedFile = new File(desDoc + sep + descFileName);

		try {
			FileCopyUtils.copy(bytes, uploadedFile);
		} catch (IOException e) {
			e.printStackTrace();
			return "�ļ��ϴ�ʧ�ܣ�" + e.getMessage();
			// return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "�ļ��ϴ�ʧ�ܣ�" + ex.getMessage();
			// return false;
		}

		return "0000";
	}

	/**
	 * ȡ���ļ���һЩ���ԣ���ֻ�������أ�Ŀ¼��
	 * 
	 * @param locFile
	 *            File����
	 * @return String--�����ļ����Ե�һ���ִ�
	 */
	public static String getFileAttr(File locFile) {
		if (locFile == null) {
			throw new IllegalArgumentException("getFileAttr() ��������");
		}

		int fileAttribute = 0;

		if (locFile.canRead() && !locFile.canWrite()) // ֻ��
		{
			fileAttribute += 1;
		}
		if (locFile.isHidden()) // ����
		{
			fileAttribute += 2;
		}
		if (locFile.isDirectory()) // Ŀ¼
		{
			fileAttribute += 16;
		}
		String ss = new Integer(fileAttribute).toString();

		return ss;
	}

	/**
	 * ��Զ�̷�������ͻ���ȡ�ļ�������
	 * 
	 * @param ip
	 *            Զ�̷�������ͻ���ip��ַ
	 * @param strPort
	 *            Զ�̷�������ͻ��˶˿�
	 * @param locFile
	 *            ���ص��ļ���
	 * @param remoteFile
	 *            Զ�̷�������ͻ����ϵ��ļ���
	 * @param type
	 *            �ļ����䷽ʽ 0:ת������ , 1:ȡ�ļ�
	 * @param remoteIp
	 *            �ͻ��˵�IP��ַ(��汾����������ʱ����Ϊ���ַ���)
	 * @param terminalNo
	 *            �ͻ��˵��ն˺� (��汾����������ʱ����Ϊ���ַ���)
	 * @param vzipType
	 *            v�˱���ѹ����ʽ
	 * @return true���ɹ���false��ʧ��
	 */
	public static String getRvcFile(String ip, String strPort, String locFile, String remoteFile, String remoteIp, String terminalNo,
			int type, int connTime, int soTime, int vzipType) {

		if (ip == null || strPort == null || locFile == null || remoteFile == null || remoteIp == null || terminalNo == null) {
			return "ϵͳ��������";
		}
		Socket s = null;
		DataInputStream r = null;
		DataOutputStream w = null;
		FileOutputStream to = null;

		int port;

		try {
			port = Integer.parseInt(strPort);
		} catch (Exception e) {
			e.printStackTrace();
			return "�������˿ڴ���,strPort:" + strPort;
		}

		try {
			s = new Socket();
			InetSocketAddress isa = new InetSocketAddress(ip, port);
			s.connect(isa, connTime * 1000);
			s.setSoTimeout(soTime * 1000);
			w = new DataOutputStream(s.getOutputStream());
			r = new DataInputStream(s.getInputStream());

			/* �ļ����� */
			String firstSend = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\" ?>").append("<root>").append("<downfiletype>")
					.append(type).append("</downfiletype>").append("<hostinfo>").append("<termno>").append(terminalNo).append("</termno>")
					.append("<hostip>").append(remoteIp).append("</hostip>").append("</hostinfo>").append("<cmdid>0101</cmdid>")
					.append("<filename>").append(remoteFile).append("</filename>").append("</root>").toString();

			File to_file = new File(locFile);
			to = new FileOutputStream(to_file);

			w.write(StringUtil.preProcess(firstSend.getBytes(), vzipType));
			w.flush();
			StringBuffer ret0 = new StringBuffer();
			log.info("v�˷��͵ı���=[" + firstSend + "]");
			byte[] b = SocketUtil.readSocket3(r, ret0);
			if (b == null) {
				return "�����ļ�ʧ��";
			}
			if (!ret0.toString().equals("00")) {
				return "�����ļ�ʧ�ܣ�ʧ��ԭ��:" + tranCode(ret0.toString());
			}

			int maxfileLength = 0;

			try {

				String[] messageArray = (new String(b, 0, b.length)).split("\\|");
				maxfileLength = Integer.parseInt(messageArray[4].replace(" ", ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
			int position = 0;

			/* �����ļ� */
			for (;;) {
				StringBuffer secondSend = new StringBuffer();
				secondSend.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?>").append("<root><cmdid>0102</cmdid>").append("<startpos>")
						.append(position).append("</startpos>").append("</root>");

				w.write(StringUtil.preProcess(secondSend.toString().getBytes(), vzipType));
				w.flush();
				log.info("v�˷��͵ı���=[" + secondSend.toString() + "]");
				StringBuffer ret = new StringBuffer();
				byte[] readByte = SocketUtil.readSocket3(r, ret);

				if (readByte == null) {
					return "�����ļ�ʧ�ܣ�ԭ��:��ѹ��ʧ��";

				}

				if (ret.toString().equals("21") && (position <= maxfileLength)) {
					to.write(readByte);
					to.flush();
					position += readByte.length;

				} else if (ret.toString().equals("04") || ret.toString().equals("20")) {
					break;
				} else {
					log.info("�����ļ�ʧ�ܣ�ʧ��ԭ��:" + tranCode(ret.toString()));
					return "�����ļ�ʧ�ܣ�ʧ��ԭ��:" + tranCode(ret.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "����ʧ�ܣ�" + e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return "����ʧ�ܣ�" + e.getMessage();

		} finally {
			try {
				if (r != null) {
					r.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "����ʧ�ܣ�" + e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
				return "����ʧ�ܣ�" + e.getMessage();
			}

			try {
				if (w != null) {
					w.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "����ʧ�ܣ�" + e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
				return "����ʧ�ܣ�" + e.getMessage();
			}

			try {
				if (s != null) {
					s.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "����ʧ�ܣ�" + e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
				return "����ʧ�ܣ�" + e.getMessage();
			}
			try {
				if (to != null) {
					to.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "����ʧ�ܣ�" + e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
				return "����ʧ�ܣ�" + e.getMessage();
			}
		}
		return "0000";
	}

	/**
	 * ɾ��ָ��Ŀ¼�е��������ݡ�(�ݹ�)
	 * 
	 * @param dir
	 *            Ҫɾ����Ŀ¼
	 * @return ɾ���ɹ�ʱ����true�����򷵻�false��
	 */

	public static boolean deleteDirectory(File dir) {
		if ((dir == null) || !dir.isDirectory()) {
			return false;
		}

		boolean delResult = true;

		try {
			File[] entries = dir.listFiles();

			int sz = entries.length;

			for (int i = 0; i < sz; i++) {
				if (entries[i].isDirectory()) {
					if (!deleteDirectory(entries[i])) {
						delResult = false;
					}
				} else {
					if (!entries[i].delete()) {
						delResult = false;
					}
				}
			}

			if (!dir.delete()) {
				delResult = false;
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return delResult;
	}

	/**
	 * �����ļ��������ϵ��ļ�
	 * 
	 * @param fileName
	 *            ���غ󱣴���ļ���
	 * @param delFlag
	 *            ɾ��ԭ�ļ���־ true:ɾ�� false:��ɾ��
	 * @param fileSrc
	 *            �ļ�����·��(����/shepherd/file/upload/version/10001_10001.zip)
	 * @return ���سɹ���ʧ�ܵı�־
	 * */
	public static boolean downLoadResponseFile(HttpServletRequest request, HttpServletResponse response, String fileName, String fileSrc,
			boolean delFlag) {

		// File downFile=new File( request.getSession().getServletContext().
		// getRealPath(fileSrc));
		File downFile = new File(fileSrc);
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			if (downFile.exists()) {
				response.setContentType("application/octet-stream");
				response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso8859-1"));

				bis = new BufferedInputStream(new FileInputStream(downFile));

				bos = new BufferedOutputStream(response.getOutputStream());

				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
				bis.close();
				bos.flush();
				bos.close();

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
			} catch (Exception ebis) {
				ebis.printStackTrace();
			}
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
				}
			} catch (Exception ebos) {
				ebos.printStackTrace();
			}

			try {
				if (delFlag) {
					downFile.delete();
				}
			} catch (Exception edel) {
				edel.printStackTrace();
			}
		}

	}

	/**
	 * �����ļ����䷵�ش���
	 * */
	public static String tranCode(String retCode) {
		if (retCode.equals("01"))
			return "���ļ�ʧ��";
		if (retCode.equals("02"))
			return "��λ���������ļ���ָ�����ļ�λ��ʧ��";
		if (retCode.equals("04"))
			return "���������ļ������ټ�����ȡ���ݣ��ļ��Ѿ�����";
		if (retCode.equals("30"))
			return "���Ľ�������ָ����XML�ֶ����������в�����";
		if (retCode.equals("20"))
			return "���������ļ����ͽ���";
		if (retCode.equals("21"))
			return "���ĵķ�������Ϊ�ļ����ݣ����ݵĳ���Ϊ(���ĳ���-8)";
		if (retCode.equals("00"))
			return "���������ļ�׼�����";
		if (retCode.equals("05"))
			return "�ļ��ѱ��������ļ�������,��ȡ�ļ���Ϣʧ��";
		if (retCode.equals("06"))
			return "��������";
		if (retCode.equals("07"))
			return "�����ֹͣ�ļ����ͷ���";
		return retCode;
	}

	/**
	 * ��ȡ���޸��ļ�����
	 * */
	public static String decreaseTrace() {
		String path = "./recodeTraceNo.txt";
		File fp = null;
		FileReader fr = null;
		FileWriter fw = null;
		FileInputStream fip = null;
		String trace = null;
		char data[] = new char[6];
		try {
			fp = new File(path);
			log.info("[" + System.getProperty("user.dir") + System.getProperty("file.separator") + "test0.txt]");
			if (fp.exists()) {
				fr = new FileReader(path);
				fr.read(data);
				String str = new String(data);
				str = str.trim();
				if (str != null && !str.equals("")) {
					int num = Integer.parseInt(str);
					if (num >= 999999) {
						num = 1;
					} else {
						num++;
					}
					trace = String.valueOf(num);
				} else {
					trace = "1";
				}
				fw = new FileWriter(fp);
				fw.write(trace);
				fw.close();
				fr.close();
				while (trace.length() != 6) {
					trace = "0" + trace;
				}
				return trace;
			} else {
				fw = new FileWriter(fp);
				fw.write("1");
				fw.close();
				return "000001";
			}
		} catch (Exception e) {
			return trace;
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception ebis) {
				ebis.printStackTrace();
			}
			try {
				if (fw != null) {
					// fw.flush();
					fw.close();
				}
			} catch (Exception ebos) {
				ebos.printStackTrace();
			}
		}
	}

	/**
	 * �ϴ��ļ�Http�����ϴ����ļ�,�������ļ���С
	 * 
	 * @param srcName
	 *            �����ҳ���DOM����
	 * @param desDoc
	 *            �ϴ�����ļ���(����·��)
	 * @param descFileName
	 *            �ϴ�����ļ��������ļ���(���Ϊ������ԭ���Ŀͻ���������ļ���)
	 * @return �ϴ��ɹ����ǲ���Ϣ
	 */

	public static HashMap<String, Object> upLoadLimitFile(HttpServletRequest request, String srcName, String desDoc,
			StringBuffer descFileName, long maxLimit, long minLimit) throws Exception {

		HashMap<String, Object> retMap = new HashMap<String, Object>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(srcName);

		byte[] bytes = file.getBytes();

		// ����ļ������ڻ����ļ�Ϊ��
		if (bytes == null || bytes.length == 0) {
			retMap.put("retCode", "0");
			retMap.put("retMsg", "src.FileNotExists");
			return retMap;
		}
		long fileLength = bytes.length;

		if (minLimit != 0 && fileLength < minLimit) {
			retMap.put("retCode", "0");
			retMap.put("retMsg", "�ļ���С�����������");
			return retMap;
		}

		if (maxLimit != 0 && fileLength > maxLimit) {
			retMap.put("retCode", "0");
			retMap.put("retMsg", "Login.upload_than_max");
			return retMap;
		}

		String sep = System.getProperty("file.separator");// ����ϵͳ�ж����ļ���Ŀ¼б������

		File dirPath = new File(desDoc);

		if (!dirPath.isDirectory()) {
			dirPath.mkdirs();
		}
		String originalFile = file.getOriginalFilename();// ԭ�ļ���

		/* ���δ�����µ��ļ�����ʹ��ԭ�����ļ���������ʹ�ô�����ļ������ļ���׺����ԭ�������ļ���׺ */
		if (descFileName.toString().equals("")) {
			descFileName.append(originalFile);
		} else {
			descFileName.append((originalFile.lastIndexOf(".") > 0 ? originalFile.substring(originalFile.lastIndexOf("."),
					originalFile.length()) : ""));
		}

		File uploadedFile = new File(desDoc + sep + descFileName);

		try {
			FileCopyUtils.copy(bytes, uploadedFile);
		} catch (IOException e) {
			e.printStackTrace();
			retMap.put("retCode", "0");
			retMap.put("retMsg", "src.FileUploadFai");
			return retMap;
		} catch (Exception ex) {
			ex.printStackTrace();
			retMap.put("retCode", "0");
			retMap.put("retMsg", "src.FileUploadFai");
			return retMap;
		}

		retMap.put("retCode", "1");
		retMap.put("retMsg", "SUC000");
		return retMap;
	}

	/**
	 * ���Ŀ¼�Ƿ����
	 * 
	 * @param dir
	 * @return
	 */
	public static boolean checkDirExist(String dir) {
		boolean flag = false;
		try {
			File file = new File(dir);
			flag = file.isDirectory();
		} catch (Exception e) {
			log.error("[checkDirExist]" + e.getMessage());
		}
		return flag;

	}

	/**
	 * ����ļ��Ƿ����
	 * 
	 * @param path
	 * @return
	 */
	public static boolean checkFileExist(String path) {
		boolean flag = false;
		try {
			File file = new File(path);
			flag = file.isFile();
		} catch (Exception e) {
			log.error("[checkFileExist]" + e.getMessage());
		}
		return flag;
	}

	/**
	 * ����Ŀ¼
	 * 
	 * @param path
	 *            ��Ŀ¼
	 * @param dir
	 *            ��Ŀ¼��
	 * @return
	 */
	public static boolean createDir(String path) {
		boolean flag = false;
		try {
			File file = new File(path);
			if (!FileUtil.checkDirExist(path)) {
				flag = file.mkdirs();
			}
		} catch (Exception e) {
			log.error("[checkFileExist]" + e.getMessage());
		}
		return flag;
	}

	/**
	 * ��ȡĿ¼�������ļ�(���ļ���)�ļ���:ȫ·��map
	 * 
	 * @param dir
	 * @return
	 */
	public static HashMap<String, String> getChildFileMapOfDir(String dir) {
		HashMap<String, String> fileNamePathMap = new HashMap<String, String>();

		ArrayList<File> files = getChildFileListOfDir(dir);
		for (File file : files) {
			fileNamePathMap.put(file.getName(), file.getPath());
		}

		return fileNamePathMap;
	}

	/**
	 * ��ȡĿ¼�������ļ�(���ļ���)�б�(���ݹ�)
	 * 
	 * @param dir
	 * @return
	 */
	public static ArrayList<File> getChildFileListOfDir(String dir) {
		ArrayList<File> list = new ArrayList<File>();

		try {
			File dirFile = new File(dir);
			if (!dirFile.isDirectory()) {
				return list;
			}

			File[] childFiles = dirFile.listFiles();
			for (File file : childFiles) {
				if (file.isFile()) {
					list.add(file);
				}
			}
		} catch (Exception e) {
			log.error("[getChildFileListOfDir]" + e.getMessage());
			return list;
		}

		return list;
	}

	public static void main(String[] args) {
		String string = "by_By_BY|BB";
		System.out.println(string);
		System.out.println(string.indexOf("BY"));
	}

}