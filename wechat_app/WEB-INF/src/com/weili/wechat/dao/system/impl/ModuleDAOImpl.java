package com.weili.wechat.dao.system.impl;

import java.io.Serializable;
import java.util.List;

import com.weili.wechat.common.HibernateDAO;
import com.weili.wechat.dao.system.ModuleDAO;

public class ModuleDAOImpl extends HibernateDAO implements ModuleDAO {

	public void delete(Serializable id) {
		// TODO Auto-generated method stub

	}

	public List qryAll() {
		// TODO Auto-generated method stub
		return this.find("from SysModule o ");
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

	public List qryModuleByName() {
		String no[] = {"com.weili.wechat.scheduling.GenerateReportDataJob",
			 	       "com.weili.wechat.scheduling.DevStatusJob",
			 	       "com.weili.wechat.scheduling.CaseUpgradeJob",
			 	       "com.weili.wechat.scheduling.CaseNotifyJob",
			 	       "com.weili.wechat.scheduling.TempFileDelJob",
			 	       "com.weili.wechat.scheduling.StartJob",
			 	       "com.weili.wechat.service.system.impl.OperateStatusServiceImpl"};
		return this.find("from SysModule o  where o.no!= '"+no[0]+"' and " +
				                                 "o.no!= '"+no[1]+"' and " +
				                                 "o.no!= '"+no[2]+"' and " +
				                                 "o.no!= '"+no[3]+"' and " +
				                                 "o.no!= '"+no[4]+"' and " +
				                                 "o.no!= '"+no[5]+"' and " +
				                                 "o.no!= '"+no[6]+"' " );
	}
}
