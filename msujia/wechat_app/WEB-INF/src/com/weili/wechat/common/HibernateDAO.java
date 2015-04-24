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
 * Spring���쳣һ�㶼��RuntimeException�����ݷ��ʵĸ��쳣��DataAccessException��
 * ���е�Spring�����ݷ����쳣���̳�DataAccessException��
 * ����DataAccessException��nestedRuntimeException�����У��Ѿ������е��쳣ջ�������˼�¼��Spring��DAO�쳣�����еײ��DAO����ʵ�ֲ���쳣������ͳһ�ķ�װ���������˸�Ϊ����
 * ����쳣:
 * -1.CleanupFailureDataAccessException��ɾ��ʧ���쳣
 * -2.DataAccessResourceFailureException����Դ�����쳣
 * -3.DataIntegrityViolationException��������Լ���쳣
 * -4.DataRetrievalFailureException�����ݻ�ȡ�쳣
 * -5.DeadlockLoserDataAccessException�����������쳣
 * -6.IncorrectUpdateSemanticsDataAccessException�����³����쳣
 * -7.InvalidDataAccessApiUsageException����Ч���ݷ���APIʹ���쳣
 * -8.InvalidDataAccessResourceUsageException����Ч���ݷ�����Դʹ���쳣
 * -9.OptimisticLockingFailureException���ֹ���ʧ���쳣
 * -10.TypeMismatchDataAccessException������ƥ��ʧ���쳣
 * -11.UncategorizedDataAccessException������ԭ���쳣
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
		log.debug("�������...");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("�������ɹ�");
		}catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	protected void updateObject(Object persistentInstance) {
		log.debug("���¶���...");
		try {
			getHibernateTemplate().update(persistentInstance);
			log.debug("���¶���ɹ�");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	protected void deleteObject(Object persistentInstance) {
		log.debug("ɾ������...");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("ɾ������ɹ�");
		}catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	protected Object findById(String clazz,Serializable id) {
		log.debug("����ID��ѯ����: " + id);
		try {
			Object instance = getHibernateTemplate().get(
					clazz, id);
			log.debug("��ѯ����ɹ�");
			return instance;
		}catch (RuntimeException re) {
			log.error("findById failed", re);
			throw re;
		}
	}
	
	protected Object findById(Class clazz,Serializable id) {
		log.debug("����ID��ѯ����: " + id);
		try {
			Object instance = getHibernateTemplate().get(
					clazz, id);
			log.debug("��ѯ����ɹ�");
			return instance;
		}catch (RuntimeException re) {
			log.error("findById failed", re);
			throw re;
		}
	}

	protected List find(String HQL) {
		log.debug("������ѯ,HQL=["+HQL+"]");
		try {
			List results = getHibernateTemplate().find(HQL);
			log.debug("������ѯ�ɹ�");
			return results;
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}

	protected List find(String HQL, Object value) {
		log.debug("������ѯ,HQL=["+HQL+"]");
		try {
			List results = getHibernateTemplate().find(HQL, value);
			log.debug("������ѯ�ɹ�");
			return results;
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}

	protected List find(String HQL, Object[] values) {
		log.debug("������ѯ,HQL=["+HQL+"]");
		try {
			List results = getHibernateTemplate().find(HQL, values);
			log.debug("������ѯ�ɹ�");
			return results;
		}catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}

	protected List findByNamedParam(String HQL, String paramName, Object value) {
		log.debug("������ѯ,HQL=["+HQL+"]");
		try {
			List results = getHibernateTemplate().findByNamedParam(HQL,
					paramName, value);
			log.debug("������ѯ�ɹ�");
			return results;
		} catch (RuntimeException re) {
			log.error("findByNamedParam failed", re);
			throw re;
		}
	}

	protected List findByNamedParam(String HQL, String[] paramNames,
			Object[] values) {
//		log.debug("������ѯ,HQL=["+HQL+"]");
		try {
			List results = getHibernateTemplate().findByNamedParam(HQL,
					paramNames, values);
//			log.debug("������ѯ�ɹ�");
			return results;
		} catch (RuntimeException re) {
			log.error("findByNamedParam failed", re);
			throw re;
		}
	}

	protected List findByCriteria(DetachedCriteria dc) {
//		log.debug("QBC��ѯ...");
		try {
			List results = getHibernateTemplate().findByCriteria(dc);
//			log.debug("QBC��ѯ�ɹ�");
			return results;
		}catch (RuntimeException re) {
			log.error("findByCriteria failed", re);
			throw re;
		}
	}

	protected int batchDeleteOrUpdate(String HQL) {
//		log.debug("����ɾ������,HQL=["+HQL+"]");
		try {
			int num = getHibernateTemplate().bulkUpdate(HQL);
//			log.debug("����ɾ�����³ɹ�");
			return num;
		}catch (RuntimeException re) {
			log.error("batchDeleteOrUpdate failed", re);
			throw re;
		}
	}

	protected int batchDeleteOrUpdate(String HQL, Object value) {
//		log.debug("����ɾ������,HQL=["+HQL+"]");
		try {
			int num = getHibernateTemplate().bulkUpdate(HQL, value);
//			log.debug("����ɾ�����³ɹ�");
			return num;
		} catch (RuntimeException re) {
			log.error("batchDeleteOrUpdate failed", re);
			throw re;
		}
	}

	protected int batchDeleteOrUpdate(String HQL, Object[] values) {
//		log.debug("����ɾ������,HQL=["+HQL+"]");
		try {
			int num = getHibernateTemplate().bulkUpdate(HQL, values);
//			log.debug("����ɾ�����³ɹ�");
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
	//�����ų�����Ϊ1�����,ֻ����2,3�����
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