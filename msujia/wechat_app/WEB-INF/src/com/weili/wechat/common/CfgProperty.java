package com.weili.wechat.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.PropertyResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 通訊配置信息加載
 */

public class CfgProperty {
	private static Log log = LogFactory.getLog(CfgProperty.class);

	private static final String NAME = "cfg";

	private static final String LAST_FILE = ".properties";

	private static final String FILE_PATH = "WEB-INF/";

	private static PropertyResourceBundle pr = null;

	static {
		try {
			String fileName = new StringBuffer(getfilePath(CfgProperty.class)).append(FILE_PATH).append(NAME + LAST_FILE).toString();
			InputStream is = new FileInputStream(fileName);
			pr = new PropertyResourceBundle(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pr == null) {
			log.error("can not find cfg.properties");
		}
	}

	/**
	 * 取得属性文件
	 * 
	 * @param file_path
	 *            应用的相对路径
	 * @param name
	 *            文件名，不包含后缀名
	 */
	public CfgProperty(String file_path, String name) {
		try {
			String fileName = new StringBuffer(CfgProperty.getfilePath(this.getClass())).append(file_path).append(name + LAST_FILE).toString();
			InputStream is = new FileInputStream(fileName);
			// System.out.println(fileName);
			pr = new PropertyResourceBundle(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String disStr) {
		if (disStr == null || disStr.equals("")) {
			return "";
		}
		String ret = "";
		try {
			ret = new String(pr.getString(disStr).getBytes("ISO-8859-1"), "GB18030");
			return ret;
		} catch (Exception e) {
			log.error("disStr=" + disStr+"；异常信息："+e.getMessage());
			// e.printStackTrace();
			return disStr;
		}
	}

	private static String getfilePath(Class cls) {
		try {
			String fileName[] = cls.getResource("").toString().split("/");
			String file = "";
			String filePath = "";
			for (int i = 0; i < fileName.length - 6; i++) {
				file = fileName[i].replace("%20", " ");
				// log.debug("file="+file);
				if (file != null) {
					filePath = filePath + file + System.getProperty("file.separator");
				}
			}
			return filePath.substring(5);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}