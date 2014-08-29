package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.hibernate.JarTable;

public interface JarUpService extends IRetInfo{
	/*
	 * ��ѯ��Ϣ
	 */
	public List qry(Map<String, Object> params);
	
	/*
	 * ������Ϣ
	 */
	public int addJar(JarTable jarTable);
	/*
	 * ��id ��ѯ
	 */
	public JarTable findById(String id);
	/*
	 * ɾ����Ϣ(���ݿ�)
	 */
	public int delJar(JarTable jarTable);
	
	
}
