package com.weili.wechat.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.dao.CommonData;

public class BasicQryServiceImpl extends RetInfo implements BasicQryService {

	private static Log log = LogFactory.getLog(BasicQryServiceImpl.class);

	private CommonData commonData;

	public CommonData getCommonData() {
		return commonData;
	}

	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}

	/**
	 * ����ID��Class ��ѯPOʵ�� ��װ�� commonData.retrieveObject(entityClass, id)
	 * 
	 * @param entityClass
	 * @param id
	 * @return Object
	 */
	public Object retrieveObject(Class entityClass, Serializable id) {
		this.initRet();
		setRetMsg("����������ʵ��ʧ��--" + entityClass.getName() + "--");
		Object obj = null;
		obj = commonData.retrieveObject(entityClass, id);
		log.debug("����������ʵ��ɹ�--" + entityClass.getName() + "--");
		setRetOK();
		setRetMsg("����������ʵ��ɹ�");
		return obj;
	}

	public boolean isExist(Class entityClass, Serializable id) {
		this.initRet();
		setRetMsg("�����������ʵ���Ƿ����ʧ��--" + entityClass.getName() + "--");
		boolean isExist = commonData.isExist(entityClass, id);
		log.debug("�����������ʵ���Ƿ���ڳɹ�--" + entityClass.getName() + "--");
		setRetOK();
		setRetMsg("�����������ʵ���Ƿ���ڳɹ�");
		return isExist;
	}

	/**
	 * ����hql����ѯList ��װ�� commonData.getAllResult(hqlString);
	 * 
	 * @param hqlString
	 * @return List
	 */
	public List getAllResult(String hqlString) {
		this.initRet();
		setRetMsg("����hql����ѯʧ�ܣ�" + hqlString);
		List resultList = null;
		resultList = commonData.getAllResult(hqlString);
		log.debug("����hql����ѯ�ɹ�");
		setRetOK();
		setRetMsg("����hql����ѯ�ɹ�");
		return resultList;
	}
	
	public List getAllResult(String hqlString,Object value){
		this.initRet();
		setRetMsg("����hql����ѯʧ�ܣ�" + hqlString);
		List resultList = null;
		resultList = commonData.getAllResult(hqlString, value);
		log.debug("����hql����ѯ�ɹ�");
		setRetOK();
		setRetMsg("����hql����ѯ�ɹ�");
		return resultList;
	}

	public List getAllResult(String hqlString, Object[] values) {
		this.initRet();
		setRetMsg("����hql����ѯʧ�ܣ�" + hqlString);
		List resultList = null;
		resultList = commonData.getAllResult(hqlString, values);
		log.debug("����hql����ѯ�ɹ�");
		setRetOK();
		setRetMsg("����hql����ѯ�ɹ�");
		return resultList;
	}

	/**
	 * ��ҳ��ѯ ������hql��䣬hql����ֵ���飨����ֵ���޲����Ϳ�����,��������null������ǰҳ�룬��������ÿҳ������� ���أ�map.totalRow .totalPage .curPage .list(hql��ѯ���)
	 * */
	public HashMap findByPage(final String HQL, final Object[] param, String sCurPage, String sTotalRow, int pageSizeNum) {
		this.initRet();
		setRetMsg("����hql����ҳ��ѯʧ�ܣ�" + HQL + "\n������" + param);
		HashMap resultMap = commonData.findByPage(HQL, param, sCurPage, sTotalRow, pageSizeNum);
		log.debug("����hql����ҳ��ѯ�ɹ�");
		setRetOK();
		setRetMsg("����hql����ҳ��ѯ�ɹ�");
		return resultMap;
	}

	public HashMap findByPageSQL(String SQL, String[] paramNames, Object[] values, String sCurPage, String sTotalRow, String sPageSize) {
		this.initRet();
		setRetMsg("����sql����ҳ��ѯʧ�ܣ�" + SQL + "\n������:" + paramNames + "\n����ֵ:" + values);
		HashMap resultMap = commonData.findByPageSQL(SQL, paramNames, values, sCurPage, sTotalRow, sPageSize);
		log.debug("����sql����ҳ��ѯ�ɹ�");
		setRetOK();
		setRetMsg("����sql����ҳ��ѯ�ɹ�");
		return resultMap;
	}

	/**
	 * SQL��ѯ
	 */
	public List findSQL(String SQL) {
		List returnList = commonData.findSQL(SQL);
		return returnList;
	}

}
