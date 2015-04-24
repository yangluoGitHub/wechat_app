package com.weili.wechat.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Collection;

import org.springframework.dao.DataAccessException;

/**
 * 数据库操作接口
 * @author hpshen
 */
public interface CommonData {
    
	/*通用操作*/
		
	public Object retrieveObject(Class entityClass, Serializable id) throws DataAccessException;	
	public List retrieveAllObjects(Class entityClass) throws DataAccessException;
	public void updateObject(Object entity) throws DataAccessException;
	public void deleteObject(Object entity) throws DataAccessException;
	public void deleteAllObjects(Collection entities) throws DataAccessException;
	public void createObject(Object entity) throws DataAccessException;
	public void createOrUpdateObject(Object entity) throws DataAccessException;
	public void createOrUpdateAll(Collection entities) throws DataAccessException;
	public List getAllResult(String queryString) throws DataAccessException;        
	public List getAllResult(String queryString,Object value) throws DataAccessException;
	public List getAllResult(String queryString, Object[] values) throws DataAccessException;  
	public List getPageResult(String hql, Object[] args,int pageNo, int pageSize) throws DataAccessException;	
	public List getAllResultPage(String hql, Object[] args,int FirstResult, int pageSize) throws DataAccessException;	
	public int getCount(String hql, Object[] args) throws DataAccessException;
	public int getCountdelSel(String hql, Object[] args) throws DataAccessException;
	
	public void batchDeleteOrUpdate(String queryString) throws DataAccessException;
	public void batchDeleteOrUpdate(String queryString,Object value) throws DataAccessException;
	public void batchDeleteOrUpdate(String queryString,Object[] values) throws DataAccessException;
	
	public boolean isExist(Class entityClass, Serializable id) throws DataAccessException;
	
	/*获取当前可插入表中的记录编号*/
	public int getId(String table,String idName,int baseId);
	
	public int getTotalRow(String HQL,Object[] para) ;
	public HashMap findByPage(String HQL,Object[] param, String sCurPage, String sTotalRow, int pageSizeNum);
	public HashMap findResultAndRowByPage(final String HQL, final Object[] param,String sCurPage, String sTotalRow, int pageSizeNum);
	public List findSQL(final String SQL);
	public List getAllResultUseSQL(String sql, Object [] param);
	public HashMap findByPageSQL(final String SQL, final String[] paramNames,final Object[] values, String sCurPage, String sTotalRow,final String sPageSize);
	
	public void excuteSqlUpdate(String sql);
}
