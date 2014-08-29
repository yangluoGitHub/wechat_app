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
	 * 根据ID和Class 查询PO实体 封装了 commonData.retrieveObject(entityClass, id)
	 * 
	 * @param entityClass
	 * @param id
	 * @return Object
	 */
	public Object retrieveObject(Class entityClass, Serializable id) {
		this.initRet();
		setRetMsg("根据主键查实体失败--" + entityClass.getName() + "--");
		Object obj = null;
		obj = commonData.retrieveObject(entityClass, id);
		log.debug("根据主键查实体成功--" + entityClass.getName() + "--");
		setRetOK();
		setRetMsg("根据主键查实体成功");
		return obj;
	}

	public boolean isExist(Class entityClass, Serializable id) {
		this.initRet();
		setRetMsg("根据主键检查实体是否存在失败--" + entityClass.getName() + "--");
		boolean isExist = commonData.isExist(entityClass, id);
		log.debug("根据主键检查实体是否存在成功--" + entityClass.getName() + "--");
		setRetOK();
		setRetMsg("根据主键检查实体是否存在成功");
		return isExist;
	}

	/**
	 * 根据hql语句查询List 封装了 commonData.getAllResult(hqlString);
	 * 
	 * @param hqlString
	 * @return List
	 */
	public List getAllResult(String hqlString) {
		this.initRet();
		setRetMsg("根据hql语句查询失败：" + hqlString);
		List resultList = null;
		resultList = commonData.getAllResult(hqlString);
		log.debug("根据hql语句查询成功");
		setRetOK();
		setRetMsg("根据hql语句查询成功");
		return resultList;
	}
	
	public List getAllResult(String hqlString,Object value){
		this.initRet();
		setRetMsg("根据hql语句查询失败：" + hqlString);
		List resultList = null;
		resultList = commonData.getAllResult(hqlString, value);
		log.debug("根据hql语句查询成功");
		setRetOK();
		setRetMsg("根据hql语句查询成功");
		return resultList;
	}

	public List getAllResult(String hqlString, Object[] values) {
		this.initRet();
		setRetMsg("根据hql语句查询失败：" + hqlString);
		List resultList = null;
		resultList = commonData.getAllResult(hqlString, values);
		log.debug("根据hql语句查询成功");
		setRetOK();
		setRetMsg("根据hql语句查询成功");
		return resultList;
	}

	/**
	 * 分页查询 参数：hql语句，hql参数值数组（？的值，无参数送空数组,但不能是null），当前页码，总行数，每页最大行数 返回：map.totalRow .totalPage .curPage .list(hql查询结果)
	 * */
	public HashMap findByPage(final String HQL, final Object[] param, String sCurPage, String sTotalRow, int pageSizeNum) {
		this.initRet();
		setRetMsg("根据hql语句分页查询失败：" + HQL + "\n参数：" + param);
		HashMap resultMap = commonData.findByPage(HQL, param, sCurPage, sTotalRow, pageSizeNum);
		log.debug("根据hql语句分页查询成功");
		setRetOK();
		setRetMsg("根据hql语句分页查询成功");
		return resultMap;
	}

	public HashMap findByPageSQL(String SQL, String[] paramNames, Object[] values, String sCurPage, String sTotalRow, String sPageSize) {
		this.initRet();
		setRetMsg("根据sql语句分页查询失败：" + SQL + "\n参数名:" + paramNames + "\n参数值:" + values);
		HashMap resultMap = commonData.findByPageSQL(SQL, paramNames, values, sCurPage, sTotalRow, sPageSize);
		log.debug("根据sql语句分页查询成功");
		setRetOK();
		setRetMsg("根据sql语句分页查询成功");
		return resultMap;
	}

	/**
	 * SQL查询
	 */
	public List findSQL(String SQL) {
		List returnList = commonData.findSQL(SQL);
		return returnList;
	}

}
