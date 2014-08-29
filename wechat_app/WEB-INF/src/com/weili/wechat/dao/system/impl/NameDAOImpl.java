package com.weili.wechat.dao.system.impl;

import java.io.Serializable;
import java.util.List;

import com.weili.wechat.common.HibernateDAO;
import com.weili.wechat.dao.system.NameDAO;

public class NameDAOImpl extends HibernateDAO implements NameDAO {

	public void delete(Serializable id) {
		// TODO Auto-generated method stub

	}

	public List qryAll() {
		// TODO Auto-generated method stub
		return this.find("from SysName");
	}

	public Object qryById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Object obj) {
		// TODO Auto-generated method stub

	}

	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

}
