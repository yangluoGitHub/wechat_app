package com.weili.wechat.common;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.weili.wechat.hibernate.SysRole;



/**
 * Spring的异常一般都是RuntimeException，数据访问的根异常是DataAccessException，
 * 所有的Spring的数据访问异常都继承DataAccessException。
 * 此外DataAccessException的nestedRuntimeException子类中，已经将所有的异常栈都进行了记录。Spring的DAO异常对所有底层的DAO访问实现层的异常进行了统一的封装，并给出了更为有意
 * 义的异常:
 * -1.CleanupFailureDataAccessException：删除失败异常
 * -2.DataAccessResourceFailureException：资源访问异常
 * -3.DataIntegrityViolationException：完整性约束异常
 * -4.DataRetrievalFailureException：数据获取异常
 * -5.DeadlockLoserDataAccessException：死锁访问异常
 * -6.IncorrectUpdateSemanticsDataAccessException：更新出错异常
 * -7.InvalidDataAccessApiUsageException：无效数据访问API使用异常
 * -8.InvalidDataAccessResourceUsageException：无效数据访问资源使用异常
 * -9.OptimisticLockingFailureException：乐观锁失败异常
 * -10.TypeMismatchDataAccessException：类型匹配失败异常
 * -11.UncategorizedDataAccessException：其他原因异常
 */

/**
 * Data access object for domain model class .
 * 
 * @see com.weili.pmcm.common.BaseDAO
 * @author zy
 */
public class HibernateDAO extends HibernateDaoSupport {
	protected String retMsg;
	protected int retCode;

	private static final Log log = LogFactory.getLog(HibernateDAO.class);
 

	public void setRetMsg(String retMsg){
		this.retMsg = retMsg;
	}
	
	public String getRetMsg(){
		return this.retMsg;
	}
	
	public void setRetCode(int retCode){
		this.retCode = retCode;
	}
	
	public int getRetCode(){
		return this.retCode;
	}
	
	protected void saveObject(Object transientInstance) {
		log.debug("保存对象...");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("保存对象成功");
		}catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	protected void updateObject(Object persistentInstance) {
		log.debug("更新对象...");
		try {
			getHibernateTemplate().update(persistentInstance);
			log.debug("更新对象成功");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	protected void deleteObject(Object persistentInstance) {
		log.debug("删除对象...");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("删除对象成功");
		}catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	protected Object findById(String clazz,Serializable id) {
		log.debug("根据ID查询对象: " + id);
		try {
			Object instance = getHibernateTemplate().get(
					clazz, id);
			log.debug("查询对象成功");
			return instance;
		}catch (RuntimeException re) {
			log.error("findById failed", re);
			throw re;
		}
	}
	
	protected Object findById(Class clazz,Serializable id) {
		log.debug("根据ID查询对象: " + id);
		try {
			Object instance = getHibernateTemplate().get(
					clazz, id);
			log.debug("查询对象成功");
			return instance;
		}catch (RuntimeException re) {
			log.error("findById failed", re);
			throw re;
		}
	}

	protected List find(String HQL) {
		log.debug("条件查询,HQL=["+HQL+"]");
		try {
			List results = getHibernateTemplate().find(HQL);
			log.debug("条件查询成功");
			return results;
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}

	protected List find(String HQL, Object value) {
		log.debug("条件查询,HQL=["+HQL+"]");
		try {
			List results = getHibernateTemplate().find(HQL, value);
			log.debug("条件查询成功");
			return results;
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}

	protected List find(String HQL, Object[] values) {
		log.debug("条件查询,HQL=["+HQL+"]");
		try {
			List results = getHibernateTemplate().find(HQL, values);
			log.debug("条件查询成功");
			return results;
		}catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}

	protected List findByNamedParam(String HQL, String paramName, Object value) {
		log.debug("条件查询,HQL=["+HQL+"]");
		try {
			List results = getHibernateTemplate().findByNamedParam(HQL,
					paramName, value);
			log.debug("条件查询成功");
			return results;
		} catch (RuntimeException re) {
			log.error("findByNamedParam failed", re);
			throw re;
		}
	}

	protected List findByNamedParam(String HQL, String[] paramNames,
			Object[] values) {
//		log.debug("条件查询,HQL=["+HQL+"]");
		try {
			List results = getHibernateTemplate().findByNamedParam(HQL,
					paramNames, values);
//			log.debug("条件查询成功");
			return results;
		} catch (RuntimeException re) {
			log.error("findByNamedParam failed", re);
			throw re;
		}
	}

	protected List findByCriteria(DetachedCriteria dc) {
//		log.debug("QBC查询...");
		try {
			List results = getHibernateTemplate().findByCriteria(dc);
//			log.debug("QBC查询成功");
			return results;
		}catch (RuntimeException re) {
			log.error("findByCriteria failed", re);
			throw re;
		}
	}

	protected int batchDeleteOrUpdate(String HQL) {
//		log.debug("批量删除更新,HQL=["+HQL+"]");
		try {
			int num = getHibernateTemplate().bulkUpdate(HQL);
//			log.debug("批量删除更新成功");
			return num;
		}catch (RuntimeException re) {
			log.error("batchDeleteOrUpdate failed", re);
			throw re;
		}
	}

	protected int batchDeleteOrUpdate(String HQL, Object value) {
//		log.debug("批量删除更新,HQL=["+HQL+"]");
		try {
			int num = getHibernateTemplate().bulkUpdate(HQL, value);
//			log.debug("批量删除更新成功");
			return num;
		} catch (RuntimeException re) {
			log.error("batchDeleteOrUpdate failed", re);
			throw re;
		}
	}

	protected int batchDeleteOrUpdate(String HQL, Object[] values) {
//		log.debug("批量删除更新,HQL=["+HQL+"]");
		try {
			int num = getHibernateTemplate().bulkUpdate(HQL, values);
//			log.debug("批量删除更新成功");
			return num;
		} catch (RuntimeException re) {
			log.error("batchDeleteOrUpdate failed", re);
			throw re;
		}
	}
	
	protected void batchUpdateRoleName(String roleName,String oldRoleName) {
		try {
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Iterator list = session.createQuery("from SysRole o where o.catalog=9").iterate();
			while(list.hasNext()) {
				SysRole sysRole = (SysRole)list.next();
				String roleNameGroup[] = sysRole.getName().split("\\-");
				for(int i=0 ; i < roleNameGroup.length ; i ++) {
					String upRoleName = "";
					if(oldRoleName.equals(roleNameGroup[i])) {
						upRoleName = getConnectRoleName(i,roleNameGroup.length,roleNameGroup,roleName);
						sysRole.setName(upRoleName);
						//session.update(sysRole);
					}
				}
				session.flush();
				session.evict(sysRole);
			}
			tx.commit();
			session.close();
		} catch (RuntimeException re) {
			log.error("batchUpdateRoleName failed", re);
			throw re;
		}
	}
	//这里排除长度为1的情况,只考虑2,3的情况
	public String getConnectRoleName(int i,int length,String[] roleNameGroup,String roleName) {
		if(length == 2) {
			switch(i) {
				case 0:   return roleName + "-" + roleNameGroup[1];   
				case 1:   return roleNameGroup[0] + "-" + roleName;  
			}
		} else {
			switch(i) {
				case 0:   return roleName + "-" + roleNameGroup[1] + "-" + roleNameGroup[2];   
				case 1:   return roleNameGroup[0] + "-" + roleName + "-" + roleNameGroup[2] ;  
				case 2:   return roleNameGroup[0] + "-" + roleNameGroup[1] + "-" + roleName ;  
		    }
		}
		return "";
	}
	
}