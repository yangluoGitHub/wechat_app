package com.weili.wechat.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.SystemCons;
import com.weili.wechat.hibernate.SysParam;


/**
 * 数据库通用操作类
 * @author hpshen
 * 
 */
public class CommonDataImpl extends HibernateDaoSupport implements CommonData {
    
	
	private static Log log = LogFactory.getLog(CommonDataImpl.class);	
	
	
	/**
	 * Persist the given transient instance
	 * @param entity  the transient instance to persist 
	 * @throws DataAccessException - in case of Hibernate errors
	 */
	
	public void createObject(Object entity) throws DataAccessException {
		if (entity == null) {
			throw new NullPointerException("param is null");
		}
		this.getHibernateTemplate().save(entity);
	}
	
	/**
	 * Save or update the given persistent instance
	 * @param entity the persistent instance to save or update 
	 * @throws DataAccessException - in case of Hibernate errors
	 */
	
	public void createOrUpdateObject(Object entity) throws DataAccessException{
		if (entity == null) {
			throw new NullPointerException("param is null");
		}
		this.getHibernateTemplate().saveOrUpdate(entity);
	}
	
	public void createOrUpdateAll(Collection entities) throws DataAccessException{
		if(entities == null){
			throw new NullPointerException("param is null");
		}
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}
	
	/**
	 * Delete the given persistent instance
	 * @param entity the persistent instance to delete 
	 * @throws DataAccessException - in case of Hibernate errors
	 */
	
	public void deleteObject(Object entity) throws DataAccessException {
		if (entity == null) {
			throw new NullPointerException("param is null");
		}
		this.getHibernateTemplate().delete(entity);
	}
	
	/**
	 * Delete all given persistent instances. 
	 * @param entities the persistent instances to delete 
	 * @throws DataAccessException - in case of Hibernate errors
	 */
	
	public void deleteAllObjects(Collection entities) throws DataAccessException{
		if (entities == null) {
			throw new NullPointerException("param is null");
		}
		this.getHibernateTemplate().deleteAll(entities);
	}
	
	/**
	 * Update the given persistent instance
	 * @param entity the persistent instance to update 
	 * @throws DataAccessException - in case of Hibernate errors
	 */
	
	public void updateObject(Object entity) throws DataAccessException {
		if (entity == null) {
			throw new NullPointerException("param is null");
		}
		this.getHibernateTemplate().update(entity);
	}	
	
	/**
	 * Return the persistent instance of the given entity class with the given identifier, or null if not found
	 * @param entityClass  a persistent class
	 * @param id  the identifier of the persistent instance 
	 * @return the persistent instance, or null if not found 
	 * @throws DataAccessException - in case of Hibernate errors
	 */
	
	public Object retrieveObject(Class entityClass, Serializable id) throws DataAccessException {
		if (entityClass == null || id == null) {
			throw new NullPointerException("param is null");
		}
		return this.getHibernateTemplate().get(entityClass, id);	
	}
	
	/**
	 * Return all persistent instances of the given entity class
	 * @param entityClass - a persistent class
	 * @return a List containing 0 or more persistent instances 
	 * @throws DataAccessException - in case of Hibernate errors
	 */
	
	public List retrieveAllObjects(Class entityClass) throws DataAccessException{
		if (entityClass == null) {
			throw new NullPointerException("param is null");
		}
		return this.getHibernateTemplate().loadAll(entityClass);	
	}

	
	/**
	 * Execute an HQL query
	 * @param queryString a query expressed in Hibernate's query language 
	 * @return a List containing the results of the query execution 
	 * @throws DataAccessException - in case of Hibernate errors
	 */
	
	public List getAllResult(String queryString) throws DataAccessException {
		if (queryString == null) {
			throw new NullPointerException("param is null");
		}
		return getHibernateTemplate().find(queryString);
	}

	/**
	 * Execute an HQL query, binding one value to a "?" parameter in the query string
	 * @param queryString  a query expressed in Hibernate's query language
	 * @param value the value of the parameter 
	 * @return a List containing the results of the query execution 
	 * @throws DataAccessException - in case of Hibernate errors
	 */
	
	public List getAllResult(String queryString,Object value) throws DataAccessException{
		if (queryString == null) {
			throw new NullPointerException("param is null");
		}
		return this.getHibernateTemplate().find(queryString,value);	
	}
	
	/**
	 * Execute an HQL query, binding a number of values to "?" parameters in the query string.
	 * @param queryString  a query expressed in Hibernate's query language
	 * @param values the values of the parameters 
	 * @return a List containing the results of the query execution 
	 * @throws DataAccessException - in case of Hibernate errors
	 */

	public List getAllResult(String queryString, Object[] values) throws DataAccessException {
		if (queryString == null || values == null) {
			throw new IllegalArgumentException("param is null");
		}
		return getHibernateTemplate().find(queryString, values);
	}

	/**
	 * add by cy 短信发送
	 * @param hql  查询语句
	 * @param args   传入参数
	 * @param pageNo   页号
	 * @param pageSize   每页记录数
	 * @return List  数据列表
	 * @throws DataAccessException
	 */
	public List getAllResultPage(String hql, Object[] args,int pageNo, int pageSize) throws DataAccessException {

		if (hql == null) {
			throw new IllegalArgumentException("param is null");
		}

	    final String sql = hql;
		final int start = pageNo;
		final int number = pageSize;
		final Object[] params = args;

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(sql);					
				if(params != null){
					for (int i = 0; i < params.length; i++){
						query.setParameter(i, params[i]);
					}
				}					
				query.setFirstResult(start);
				query.setMaxResults(number);
				return query.list();
			}
		});		
	}
	
	
	/**
	 * 分页查询
	 * @param hql  查询语句
	 * @param args   传入参数
	 * @param pageNo   页号
	 * @param pageSize   每页记录数
	 * @return List  数据列表
	 * @throws DataAccessException
	 */
	public List getPageResult(String hql, Object[] args,int pageNo, int pageSize) throws DataAccessException {

		if (hql == null) {
			throw new IllegalArgumentException("param is null");
		}

	    final String sql = hql;
		final int start = (pageNo - 1) * pageSize;
		final int number = pageSize;
		final Object[] params = args;

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(sql);					
				if(params != null){
					for (int i = 0; i < params.length; i++){
						query.setParameter(i, params[i]);
					}
				}					
				query.setFirstResult(start);
				query.setMaxResults(number);
				return query.list();
			}
		});		
	}
	
	
	/**
	 * 取查询记录条数
	 * @param  hql 查询语句，以from开头
	 * @param args 查询语句传入参数
	 * @return 查询记录条数
	 * @throws DataAccessException
	 */
	public int getCount(String hql, Object[] args) throws DataAccessException {
		
        String sql = StringUtil.parseString(hql);
		if(sql.length() == 0){
		    throw new IllegalArgumentException("param is null");
		}
		sql = "select count(*) " + sql;
		List list = args == null ? getHibernateTemplate().find(sql) : getHibernateTemplate().find(sql, args);
		Iterator iterator = list.iterator();
		return iterator.hasNext() ? ((Long)iterator.next()).intValue() : 0;
	}
	
	/**
	 * 取查询记录条数
	 * @param  hql 查询语句，以from开头
	 * @param args 查询语句传入参数
	 * @return 查询记录条数
	 * @throws DataAccessException
	 */
	public int getCountdelSel(String hql, Object[] args) throws DataAccessException {
		
        String sql = StringUtil.parseString(hql);
		if(sql.length() == 0){
		    throw new IllegalArgumentException("param is null");
		}
		int fromPosition = sql.indexOf("from");
		int position = sql.indexOf("order by");
		String HQLcount = "";
		if (position < 0) {							
			position = sql.length();							
		}
		sql = sql.substring(fromPosition, position);
		sql = "select count(*) " + sql;
		List list = args == null ? getHibernateTemplate().find(sql) : getHibernateTemplate().find(sql, args);
		Iterator iterator = list.iterator();
		return iterator.hasNext() ? ((Long)iterator.next()).intValue() : 0;
	}
	
	


	/**
	 * 获取当前可插入表中的记录编号
	 * @param table  表名
	 * @param idName 记录主键
	 * @param baseId 记录基本值
	 * @return 对于原来记录主键为按数字型排序的返回当前最大记录+1，如果为-1表示不能获取最大记录
	 * */
	public int getId(String table,String idName,int baseId)
	{
		if(table==null||table.equals("")||idName==null||idName.equals(""))
		{
			return -1;
		}
		try
		{
			List list = this.getAllResult("select max(a."+idName+") from "+table+" as a");
			
			if(list != null)
			{
				Iterator it = list.iterator();
				if(it.hasNext())
				{
					Object tmp = it.next();
					if(tmp != null)
					{
					    return Integer.parseInt(tmp.toString().trim())+1;					    
					}					
				}
	
			}
			return baseId+1;
		}
		catch(DataAccessException e) 
		{
			return -1;
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	/**
	 * 查询所有记录数
	 * 分页查询用
	 * 
	 * */
	public int getTotalRow(final String HQL,
			final Object[] para) {
		Object totalRow = getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						int fromPosition = HQL.indexOf("from");
						int position = HQL.indexOf("order by");
						String HQLcount = "";
						if (position < 0) {							
							position = HQL.length();							
						}
						HQLcount = HQL.substring(fromPosition, position);
						Query query = session.createQuery("select count(*) "
								+ HQLcount.replace("fetch", ""));
						for (int i = 0; i < para.length; i++) {
							query.setParameter(i, para[i]);
						}
						Object o = query.uniqueResult();
						return o;
					}
				});
		return (int) ((Long) totalRow).longValue();
	}
	/**
	 * 分页查询
	 * 参数：hql语句，hql参数值数组（？的值，无参数送空数组,但不能是null），当前页码，总行数，每页最大行数
	 * 返回：map.totalRow .totalPage .curPage .list(hql查询结果)
	 * */
	public HashMap findByPage(final String HQL, final Object[] param,
			String sCurPage, String sTotalRow, int pageSizeNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		final int pageSize;
		if(pageSizeNum<=0){
			pageSize = 20;
		}else{
			pageSize = pageSizeNum;
		}
		 
		final int curPage;
		int totalRow = 0;

		if (sCurPage == null || sCurPage.equals("null")) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(sCurPage);
		}
		/*
		if (curPage == 1) {
			totalRow = this.getTotalRow(HQL, param);
		} else {
			totalRow = Integer.parseInt(sTotalRow);
		}*/
		totalRow = this.getTotalRow(HQL, param);
		int totalPage = (totalRow - 1) / pageSize + 1;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(HQL);
				for (int i = 0; i < param.length; i++) {
					query.setParameter(i, param[i]);
				}
				List result = query.setFirstResult(pageSize * (curPage - 1))
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		map.put("totalRow", String.valueOf(totalRow));
		map.put("totalPage", String.valueOf(totalPage));
		map.put("curPage", String.valueOf(curPage));
		map.put("list", list);
		return map;
	}

	/**
	 * 分页查询
	 * 参数：hql语句，hql参数值数组（？的值，无参数送空数组,但不能是null），当前页码，总行数，每页最大行数
	 * 返回：map.totalRow .totalPage .curPage .list(hql查询结果)
	 * */
	public HashMap findResultAndRowByPage(final String HQL, final Object[] param,
			String sCurPage, String sTotalRow, int pageSizeNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		final int pageSize;
		if(pageSizeNum<=0){
			pageSize = 20;
		}else{
			pageSize = pageSizeNum;
		}
		 
		final int curPage;
		int t_curPage;
		int totalRow = 0;
		
		totalRow = this.getTotalRow(HQL, param);
		int totalPage = (totalRow - 1) / pageSize + 1;
		
		if (sCurPage == null || sCurPage.equals("null")) {
			t_curPage = 1;
		} else {
			t_curPage = Integer.parseInt(sCurPage);
		}
		
		if(totalPage < t_curPage)
		{
			curPage = 1;
		}
		else
		{
			curPage = t_curPage;
		}
		
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(HQL);
				for (int i = 0; i < param.length; i++) {
					query.setParameter(i, param[i]);
				}
				List result = query.setFirstResult(pageSize * (curPage - 1))
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		map.put("totalRow", String.valueOf(totalRow));
		map.put("totalPage", String.valueOf(totalPage));
		map.put("curPage", String.valueOf(curPage));
		map.put("list", list);
		return map;
	}
	/**
	 * 获取数据库中的参数
	 * */
	private String getDatabaseParam(String paramName)
	{
		String hql = "from SysParam as c where c.paramName=?";
		Object[] args = new Object[] {paramName };
		List list = getHibernateTemplate().find(hql, args);
		if (list.isEmpty()) 
		{
			return null;
		}
		SysParam systemParam = (SysParam) list.iterator().next();
		log.info("getDatabaseParam,paramName="+paramName+",value="+systemParam.getParamValue());
		return systemParam.getParamValue();
	}
	/**
	 * 获取SystemCons中的参数
	 * */
	public void batchDeleteOrUpdate(String queryString) throws DataAccessException {
		if (queryString == null) {
			throw new NullPointerException("param is null");
		}
		this.getHibernateTemplate().bulkUpdate(queryString);
	}

	public void batchDeleteOrUpdate(String queryString, Object value) throws DataAccessException {
		if (queryString == null) {
			throw new NullPointerException("param is null");
		}
		this.getHibernateTemplate().bulkUpdate(queryString,value);
		
	}

	public void batchDeleteOrUpdate(String queryString, Object[] values) throws DataAccessException {
		if (queryString == null) {
			throw new NullPointerException("param is null");
		}
		this.getHibernateTemplate().bulkUpdate(queryString,values);
	}
	
	public HashMap findByPageSQL(final String SQL, final String[] paramNames,
			final Object[] values, String sCurPage, String sTotalRow,
			final String sPageSize) {
		log.debug("findByPage ItemIntMain instance");
		HashMap map = new HashMap();
		final int pageSize;
		final int curPage;
		int totalRow = 0;

		if (sPageSize == null || sPageSize.equals("null")) {
			pageSize = 10;
		} else {
			pageSize = Integer.parseInt(sPageSize);// ??????????????
		}

		if (sCurPage == null || sCurPage.equals("null")) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(sCurPage);// ?±?°????????????
		}
		/*if (curPage == 1) {
			totalRow = this.getTotalRowSQL(SQL, paramNames, values);
		} else {
			totalRow = Integer.parseInt(sTotalRow);
		}*/
		totalRow = this.getTotalRowSQL(SQL, paramNames, values);
		int totalPage = (totalRow - 1) / pageSize + 1;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(SQL);
				for (int i = 0; i < values.length; i++) {
					query.setParameter(paramNames[i], values[i]);
				}
				List result = query.setFirstResult(pageSize * (curPage - 1))
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		map.put("totalRow", String.valueOf(totalRow));
		map.put("totalPage", String.valueOf(totalPage));
		map.put("curPage", String.valueOf(curPage));
		map.put("list", list);
		return map;
	}
	// sql query
	public int getTotalRowSQL(final String SQL, final String[] paramNames,
			final Object[] para) {
		log.debug("getTotalRow ItemIntMain instance");
		Object totalRow = getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						int position = SQL.indexOf(" order by");
						String SQLcount = "";
						if (position >= 0) {
							SQLcount = SQL.substring(0, position);
						} else {
							SQLcount = SQL;
						}

						String SQLcount2 = "select count(*) from ( " + SQLcount
								+ " ) as TEMP";

						Query query = session.createSQLQuery(SQLcount2);
						for (int i = 0; i < para.length; i++) {
							query.setParameter(paramNames[i], para[i]);
						}
						Object o = query.uniqueResult();
						return o;
					}
				});
		return (int) (new Long(String.valueOf(totalRow))).longValue();
	}
	
	public List findSQL(final String SQL) {
		log.debug("findSQL ItemIntMain instance");

		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(SQL);

				List result = query.list();
				return result;
			}
		});

		return list;
	}
	
	public boolean isExist(Class entityClass, Serializable id) throws DataAccessException {
		if (entityClass == null || id == null) {
			throw new NullPointerException("param is null");
		}
		Object po =  this.getHibernateTemplate().get(entityClass, id);
		if (po == null) {
			return false;
		} else {
			return true;
		}
	}

	public List getAllResultUseSQL(final String queryString, final Object[] values) {
		if (queryString == null || values == null) {
			throw new IllegalArgumentException("param is null");
		}
		List list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(queryString);
				for(int i = 0; i < values.length; i ++){
					query.setParameter(i, values[i]);
				}
				List result = query.list();
				return result;
			}
		});
		return list;
	}
	
	/* 
	 * 执行SQL UPDATE
	 */
	public void excuteSqlUpdate(String sql){
		if(sql == null){
			throw new IllegalArgumentException("sql is null");
		}
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.executeUpdate();
	}
}