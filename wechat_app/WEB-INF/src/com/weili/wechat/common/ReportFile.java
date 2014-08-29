package com.weili.wechat.common;

public class ReportFile {

	private String path; // 文件全名
	private String dir; // 文件全目录
	private String name; // 文件名

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
