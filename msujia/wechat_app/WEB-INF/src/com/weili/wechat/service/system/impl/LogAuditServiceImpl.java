//Source file: E:\\Program Files\\workspace\\pmcm\\src\\com\\weili\\pmcm\\service\\LogAuditService.java

package com.weili.wechat.service.system.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.dao.system.LogAuditDAO;
import com.weili.wechat.dao.system.ModuleDAO;
import com.weili.wechat.dao.system.NameDAO;
import com.weili.wechat.dao.system.OperateDAO;
import com.weili.wechat.dao.system.UserDAO;
import com.weili.wechat.service.system.LogAuditService;
import com.weili.wechat.vo.LogAudit;
import com.weili.wechat.vo.LogAudit_local;

public class LogAuditServiceImpl extends RetInfo implements LogAuditService 
{
	private Log log = LogFactory.getLog(LogAuditServiceImpl.class);

	private OperateDAO operateDAO;

	private ModuleDAO moduleDAO;

	private UserDAO userDAO;

	private LogAuditDAO logAuditDAO;
	
	private NameDAO nameDAO;
   
	
	public LogAuditDAO getLogAuditDAO() {
		return logAuditDAO;
	}

	public void setLogAuditDAO(LogAuditDAO logAuditDAO) {
		this.logAuditDAO = logAuditDAO;
	}

	public OperateDAO getOperateDAO() {
		return operateDAO;
	}

	public void setOperateDAO(OperateDAO operateDAO) {
		this.operateDAO = operateDAO;
	}

	public ModuleDAO getModuleDAO() {
		return moduleDAO;
	}

	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public NameDAO getNameDAO() {
		return nameDAO;
	}

	public void setNameDAO(NameDAO nameDAO) {
		this.nameDAO = nameDAO;
	}

	public void writeLog(LogAudit logAudit){
		log.debug("添加日志---->开始!");
		
		log.debug("userid=["+logAudit.getUserId()+"] date=["+logAudit.getDate()+"]");
		
		this.getLogAuditDAO().saveLogAudit(logAudit);
		
		log.debug("添加日志---->结束!");
	}
	
	public List qryAllUser() {
		log.debug("查询系统用户信息------------------>");		
		List userList = this.getUserDAO().qryAll();
		return userList;
	}

	public List qryOperateByModule(String moduleId) {
		// TODO Auto-generated Operate stub
		return this.getOperateDAO().qryOperateByModule(moduleId);
	}

	public List qryModule() {
		// TODO Auto-generated Operate stub
		return this.getModuleDAO().qryModuleByName();
	}

	public List qry_LogAuditList(String userId, String module, String operate, String startTime, String endTime) {
		// TODO Auto-generated Operate stub
		log.debug("查询日志信息列表---------------------->");
		List logAuditList = this.getLogAuditDAO().qry_LogAuditList(userId, module,operate,startTime, endTime);
		return logAuditList;
	}
	
	public List qry_LogAuditList(String upper1 ,String userId, String module, String operate, String startTime, String endTime) {
		// TODO Auto-generated Operate stub
		log.debug("查询日志信息列表---------------------->");
		List logAuditList = this.getLogAuditDAO().qry_LogAuditList(upper1 ,userId, module,operate,startTime, endTime);
		return logAuditList;
	}
	
	public List qry_LogAuditList1(String OrgNo,String module, String operate, String startTime, String endTime) {
		// TODO Auto-generated Operate stub
		log.debug("查询日志信息列表---------------------->");
		List logAuditList = this.getLogAuditDAO().qry_LogAuditList1(OrgNo, module,operate,startTime, endTime);
		return logAuditList;
	}
	
	public LogAudit_local qryLogAudit(String userId, String date) {
		// TODO Auto-generated method stub
		return this.getLogAuditDAO().qryLogAudit(userId, date);
	}
	
	public List qryUser(String orgId) {
		// TODO Auto-generated method stub
		//return this.getUserDAO().qryUser(orgId);
		return null;
	}
	
	public List qryOperate(){
		return this.getOperateDAO().qryAll();
	}
	
	public List qryName() {
		// TODO Auto-generated method stub
		return this.getNameDAO().qryAll();
	}
	public List qryAllUserByRole(String orgId) {
		//return this.getUserDAO().qryAllUserByRole(orgId);
		return null;
	}
}
