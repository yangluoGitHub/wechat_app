package com.weili.wechat.common;

import java.io.File;
import java.util.HashMap;
/**
 * 系统运行参数
 * */
public class  SystemCons
{
	public static final String FILE_SEPARATOR = File.separator;  // 所在系统的系统分隔符
	
	private static String root;
	public static HashMap<String, String> sysParamMap =new HashMap<String, String>();

	public static String getRoot() {
		return root;
	}
	public static void setRoot(String root) {
		SystemCons.root = root;
	}
	
}
