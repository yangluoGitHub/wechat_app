package com.weili.wechat.common;

import java.io.File;
import java.util.HashMap;
/**
 * ϵͳ���в���
 * */
public class  SystemCons
{
	public static final String FILE_SEPARATOR = File.separator;  // ����ϵͳ��ϵͳ�ָ���
	
	private static String root;
	public static HashMap<String, String> sysParamMap =new HashMap<String, String>();

	public static String getRoot() {
		return root;
	}
	public static void setRoot(String root) {
		SystemCons.root = root;
	}
	
}
