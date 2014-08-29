package com.weili.wechat.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.dao.CommonData;

public interface BasicQryService extends IRetInfo {
	/**
	 * 根据ID和Class 查询PO实体 封装了 shepherdData.retrieveObject(entityClass, id)
	 * 
	 * @param entityClass
	 * @param id
	 * @return Object
	 */
	public Object retrieveObject(Class entityClass, Serializable id);
	
	/**
	 * 根据ID和Class 检查是否存在  封装了 shepherdData.isExist(entityClass, id)
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public boolean isExist(Class entityClass, Serializable id);

	/**
	 * 根据hql语句查询List 封装了 shepherdData.getAllResult(hqlString);
	 * 
	 * @param hqlString
	 * @return List
	 */
	public List getAllResult(String hqlString);
	
	public List getAllResult(String hqlString, Object value);
	
	public List getAllResult(String hqlString, Object[] values);

	/**
	 * 分页查询
	 * 参数：hql语句，hql参数值数组（？的值，无参数送空数组,但不能是null），当前页码，总行数，每页最大行数
	 * 返回：map.totalRow .totalPage .curPage .list(hql查询结果)
	 * */
	public HashMap findByPage(final String HQL, final Object[] param,
			String sCurPage, String sTotalRow, int pageSizeNum);
	
	/**
	 * 分页查询
	 * @param SQL sql语句
	 * @param paramNames 参数名数组
	 * @param values 参数值数组
	 * @param sCurPage 当前页码
	 * @param sTotalRow 总行数
	 * @param sPageSize 每页最大行数
	 * @return
	 */
	public HashMap findByPageSQL(final String SQL, final String[] paramNames,final Object[] values, 
			String sCurPage, String sTotalRow,final String sPageSize);
	
	/**
	 * SQL查询 
	 */
	public List findSQL(String SQL);
	
	public CommonData getCommonData();
	
}
