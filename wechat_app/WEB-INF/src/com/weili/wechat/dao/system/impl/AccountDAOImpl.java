package com.weili.wechat.dao.system.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.dao.system.AccountDAO;
import com.weili.wechat.hibernate.PublicAccount;
import com.weili.wechat.common.HibernateDAO;
public class AccountDAOImpl extends HibernateDAO implements AccountDAO{
	private Log log = LogFactory.getLog(AccountDAOImpl.class);
	
	@Override
	public Object qryById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List qryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object obj) {
		this.updateObject((PublicAccount) obj);
	}

	@Override
	public void save(Object obj) {
		this.saveObject((PublicAccount) obj);
	}

}
