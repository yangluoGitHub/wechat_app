package com.weili.wechat.dao.system.impl;

import java.io.Serializable;
import java.util.List;

import com.weili.wechat.common.HibernateDAO;
import com.weili.wechat.dao.system.OperateDAO;

public class OperateDAOImpl extends HibernateDAO implements OperateDAO {

	public List qryOperateByModule(String moduleId) {
		// TODO Auto-generated method stub
		return this.find("from SysOperate o where o.sysModule.no=?",moduleId);
	}

	public void delete(Serializable id) {
		// TODO Auto-generated method stub

	}

	public List qryAll() {
		// TODO Auto-generated method stub
		return this.find("from SysOperate o ");
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
