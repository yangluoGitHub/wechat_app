package com.weili.wechat.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.dao.CommonData;

public interface BasicQryService extends IRetInfo {
	/**
	 * ����ID��Class ��ѯPOʵ�� ��װ�� shepherdData.retrieveObject(entityClass, id)
	 * 
	 * @param entityClass
	 * @param id
	 * @return Object
	 */
	public Object retrieveObject(Class entityClass, Serializable id);
	
	/**
	 * ����ID��Class ����Ƿ����  ��װ�� shepherdData.isExist(entityClass, id)
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public boolean isExist(Class entityClass, Serializable id);

	/**
	 * ����hql����ѯList ��װ�� shepherdData.getAllResult(hqlString);
	 * 
	 * @param hqlString
	 * @return List
	 */
	public List getAllResult(String hqlString);
	
	public List getAllResult(String hqlString, Object value);
	
	public List getAllResult(String hqlString, Object[] values);

	/**
	 * ��ҳ��ѯ
	 * ������hql��䣬hql����ֵ���飨����ֵ���޲����Ϳ�����,��������null������ǰҳ�룬��������ÿҳ�������
	 * ���أ�map.totalRow .totalPage .curPage .list(hql��ѯ���)
	 * */
	public HashMap findByPage(final String HQL, final Object[] param,
			String sCurPage, String sTotalRow, int pageSizeNum);
	
	/**
	 * ��ҳ��ѯ
	 * @param SQL sql���
	 * @param paramNames ����������
	 * @param values ����ֵ����
	 * @param sCurPage ��ǰҳ��
	 * @param sTotalRow ������
	 * @param sPageSize ÿҳ�������
	 * @return
	 */
	public HashMap findByPageSQL(final String SQL, final String[] paramNames,final Object[] values, 
			String sCurPage, String sTotalRow,final String sPageSize);
	
	/**
	 * SQL��ѯ 
	 */
	public List findSQL(String SQL);
	
	public CommonData getCommonData();
	
}
