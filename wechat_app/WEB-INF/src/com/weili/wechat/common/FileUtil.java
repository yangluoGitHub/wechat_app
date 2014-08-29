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
 * 处理与文件相关操作
 * 
 * @author hjtang
 * @since 2007.07.20
 */

public final class FileUtil {

	private static Log log = LogFactory.getLog(FileUtil.class);
	
	public static final String sysFileSeparator =  System.getProperty("file.separator");

	/**
	 * 上传文件Http请求上传的文件
	 * 
	 * @param srcName
	 *            请求的页面的DOM对象
	 * @param desDoc
	 *            上传后的文件夹(绝对路径)
	 * @param descFileName
	 *            上传后的文件保存后的文件名(如果为空则用原来的客户端请求的文件名)
	 * @return false：上传文件失败，true：上传文件成功
	 */

	public static String upLoadHttpRequestFile(HttpServletRequest request, String srcName, String desDoc, StringBuffer descFileName)
			throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(srcName);

		byte[] bytes = file.getBytes();

		// 如果文件不存在或者文件为空
		if (bytes == null || bytes.length == 0) {
			return "文件不存在或文件为空！";
		}
		// if(bytes.length>20971520)
		// {
		// return "文件大小超过20M限制";
		// }

		// String
		// uploadDir=request.getSession().getServletContext().getRealPath(desDoc);

		String sep = System.getProperty("file.separator");// 根据系统判断是文件的目录斜杠问题

		File dirPath = new File(desDoc);

		if (!dirPath.isDirectory()) {
			dirPath.mkdirs();
		}
		String originalFile = file.getOriginalFilename();// 原文件名

		/* 如果未定义新的文件名则使用原来的文件名，否则使用传入的文件名，文件后缀还是原来来的文件后缀 */
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
			return "文件上传失败：" + e.getMessage();
			// return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "文件上传失败：" + ex.getMessage();
			// return false;
		}

		return "0000";
	}

	/**
	 * 取得文件的一些属性，如只读，隐藏，目录等
	 * 
	 * @param locFile
	 *            File对象
	 * @return String--代表文件属性的一个字串
	 */
	public static String getFileAttr(File locFile) {
		if (locFile == null) {
			throw new IllegalArgumentException("getFileAttr() 参数错误");
		}

		int fileAttribute = 0;

		if (locFile.canRead() && !locFile.canWrite()) // 只读
		{
			fileAttribute += 1;
		}
		if (locFile.isHidden()) // 隐藏
		{
			fileAttribute += 2;
		}
		if (locFile.isDirectory()) // 目录
		{
			fileAttribute += 16;
		}
		String ss = new Integer(fileAttribute).toString();

		return ss;
	}

	/**
	 * 从远程服务器或客户端取文件到本地
	 * 
	 * @param ip
	 *            远程服务器或客户端ip地址
	 * @param strPort
	 *            远程服务器或客户端端口
	 * @param locFile
	 *            本地的文件名
	 * @param remoteFile
	 *            远程服务器或客户端上的文件名
	 * @param type
	 *            文件传输方式 0:转发命令 , 1:取文件
	 * @param remoteIp
	 *            客户端的IP地址(与版本服务器交互时可以为空字符串)
	 * @param terminalNo
	 *            客户端的终端号 (与版本服务器交互时可以为空字符串)
	 * @param vzipType
	 *            v端报文压缩方式
	 * @return true：成功，false：失败
	 */
	public static String getRvcFile(String ip, String strPort, String locFile, String remoteFile, String remoteIp, String terminalNo,
			int type, int connTime, int soTime, int vzipType) {

		if (ip == null || strPort == null || locFile == null || remoteFile == null || remoteIp == null || terminalNo == null) {
			return "系统参数错误！";
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
			return "服务器端口错误,strPort:" + strPort;
		}

		try {
			s = new Socket();
			InetSocketAddress isa = new InetSocketAddress(ip, port);
			s.connect(isa, connTime * 1000);
			s.setSoTimeout(soTime * 1000);
			w = new DataOutputStream(s.getOutputStream());
			r = new DataInputStream(s.getInputStream());

			/* 文件请求 */
			String firstSend = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\" ?>").append("<root>").append("<downfiletype>")
					.append(type).append("</downfiletype>").append("<hostinfo>").append("<termno>").append(terminalNo).append("</termno>")
					.append("<hostip>").append(remoteIp).append("</hostip>").append("</hostinfo>").append("<cmdid>0101</cmdid>")
					.append("<filename>").append(remoteFile).append("</filename>").append("</root>").toString();

			File to_file = new File(locFile);
			to = new FileOutputStream(to_file);

			w.write(StringUtil.preProcess(firstSend.getBytes(), vzipType));
			w.flush();
			StringBuffer ret0 = new StringBuffer();
			log.info("v端发送的报文=[" + firstSend + "]");
			byte[] b = SocketUtil.readSocket3(r, ret0);
			if (b == null) {
				return "接收文件失败";
			}
			if (!ret0.toString().equals("00")) {
				return "接收文件失败，失败原因:" + tranCode(ret0.toString());
			}

			int maxfileLength = 0;

			try {

				String[] messageArray = (new String(b, 0, b.length)).split("\\|");
				maxfileLength = Integer.parseInt(messageArray[4].replace(" ", ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
			int position = 0;

			/* 接受文件 */
			for (;;) {
				StringBuffer secondSend = new StringBuffer();
				secondSend.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?>").append("<root><cmdid>0102</cmdid>").append("<startpos>")
						.append(position).append("</startpos>").append("</root>");

				w.write(StringUtil.preProcess(secondSend.toString().getBytes(), vzipType));
				w.flush();
				log.info("v端发送的报文=[" + secondSend.toString() + "]");
				StringBuffer ret = new StringBuffer();
				byte[] readByte = SocketUtil.readSocket3(r, ret);

				if (readByte == null) {
					return "接收文件失败，原因:解压缩失败";

				}

				if (ret.toString().equals("21") && (position <= maxfileLength)) {
					to.write(readByte);
					to.flush();
					position += readByte.length;

				} else if (ret.toString().equals("04") || ret.toString().equals("20")) {
					break;
				} else {
					log.info("接收文件失败，失败原因:" + tranCode(ret.toString()));
					return "接收文件失败，失败原因:" + tranCode(ret.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "操作失败：" + e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败：" + e.getMessage();

		} finally {
			try {
				if (r != null) {
					r.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "操作失败：" + e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
				return "操作失败：" + e.getMessage();
			}

			try {
				if (w != null) {
					w.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "操作失败：" + e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
				return "操作失败：" + e.getMessage();
			}

			try {
				if (s != null) {
					s.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "操作失败：" + e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
				return "操作失败：" + e.getMessage();
			}
			try {
				if (to != null) {
					to.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "操作失败：" + e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
				return "操作失败：" + e.getMessage();
			}
		}
		return "0000";
	}

	/**
	 * 删除指定目录中的所有内容。(递归)
	 * 
	 * @param dir
	 *            要删除的目录
	 * @return 删除成功时返回true，否则返回false。
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
	 * 下载文件服务器上的文件
	 * 
	 * @param fileName
	 *            下载后保存的文件名
	 * @param delFlag
	 *            删除原文件标志 true:删除 false:不删除
	 * @param fileSrc
	 *            文件绝对路径(例如/shepherd/file/upload/version/10001_10001.zip)
	 * @return 下载成功与失败的标志
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
	 * 解析文件传输返回代码
	 * */
	public static String tranCode(String retCode) {
		if (retCode.equals("01"))
			return "打开文件失败";
		if (retCode.equals("02"))
			return "定位服务器端文件到指定的文件位置失败";
		if (retCode.equals("04"))
			return "服务器端文件不能再继续读取数据，文件已经结束";
		if (retCode.equals("30"))
			return "报文解析错误，指定的XML字段在请求报文中不存在";
		if (retCode.equals("20"))
			return "服务器端文件发送结束";
		if (retCode.equals("21"))
			return "报文的返回数据为文件数据，数据的长度为(报文长度-8)";
		if (retCode.equals("00"))
			return "服务器端文件准备完毕";
		if (retCode.equals("05"))
			return "文件已被更名或文件不存在,获取文件信息失败";
		if (retCode.equals("06"))
			return "其他错误";
		if (retCode.equals("07"))
			return "服务端停止文件传送服务";
		return retCode;
	}

	/**
	 * 读取并修改文件内容
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
	 * 上传文件Http请求上传的文件,可限制文件大小
	 * 
	 * @param srcName
	 *            请求的页面的DOM对象
	 * @param desDoc
	 *            上传后的文件夹(绝对路径)
	 * @param descFileName
	 *            上传后的文件保存后的文件名(如果为空则用原来的客户端请求的文件名)
	 * @return 上传成功或是不信息
	 */

	public static HashMap<String, Object> upLoadLimitFile(HttpServletRequest request, String srcName, String desDoc,
			StringBuffer descFileName, long maxLimit, long minLimit) throws Exception {

		HashMap<String, Object> retMap = new HashMap<String, Object>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(srcName);

		byte[] bytes = file.getBytes();

		// 如果文件不存在或者文件为空
		if (bytes == null || bytes.length == 0) {
			retMap.put("retCode", "0");
			retMap.put("retMsg", "src.FileNotExists");
			return retMap;
		}
		long fileLength = bytes.length;

		if (minLimit != 0 && fileLength < minLimit) {
			retMap.put("retCode", "0");
			retMap.put("retMsg", "文件大小低于最低限制");
			return retMap;
		}

		if (maxLimit != 0 && fileLength > maxLimit) {
			retMap.put("retCode", "0");
			retMap.put("retMsg", "Login.upload_than_max");
			return retMap;
		}

		String sep = System.getProperty("file.separator");// 根据系统判断是文件的目录斜杠问题

		File dirPath = new File(desDoc);

		if (!dirPath.isDirectory()) {
			dirPath.mkdirs();
		}
		String originalFile = file.getOriginalFilename();// 原文件名

		/* 如果未定义新的文件名则使用原来的文件名，否则使用传入的文件名，文件后缀还是原来来的文件后缀 */
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
	 * 检查目录是否存在
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
	 * 检查文件是否存在
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
	 * 创建目录
	 * 
	 * @param path
	 *            父目录
	 * @param dir
	 *            子目录名
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
	 * 获取目录下所有文件(非文件夹)文件名:全路径map
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
	 * 获取目录下所有文件(非文件夹)列表(不递归)
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