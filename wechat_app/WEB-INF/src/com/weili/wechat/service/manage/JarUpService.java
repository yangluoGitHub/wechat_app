package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.hibernate.JarTable;

public interface JarUpService extends IRetInfo{
	/*
	 * 查询信息
	 */
	public List qry(Map<String, Object> params);
	
	/*
	 * 增加信息
	 */
	public int addJar(JarTable jarTable);
	/*
	 * 由id 查询
	 */
	public JarTable findById(String id);
	/*
	 * 删除信息(数据库)
	 */
	public int delJar(JarTable jarTable);
	
	
}
