package com.weili.wechat.common;

public class ReportFile {

	private String path; // �ļ�ȫ��
	private String dir; // �ļ�ȫĿ¼
	private String name; // �ļ���

	public ReportFile(String dir, String name) {
		this.dir = dir;
		this.name = name;
		this.path = this.dir + FileUtil.sysFileSeparator + this.name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
