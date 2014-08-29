package com.weili.wechat.dao.system.impl;

import java.io.Serializable;
import java.util.List;

import com.weili.wechat.common.HibernateDAO;
import com.weili.wechat.dao.system.JarDAO;
import com.weili.wechat.hibernate.JarTable;
public class JarDAOImpl  extends HibernateDAO implements JarDAO{

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Object obj) {
		this.saveObject((JarTable) obj);
		
	}

}
