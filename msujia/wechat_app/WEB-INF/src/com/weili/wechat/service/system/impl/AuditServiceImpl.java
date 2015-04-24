package com.weili.wechat.service.system.impl;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtworks.xstream.XStream;
import com.weili.wechat.common.RetInfo;
import com.weili.wechat.dao.system.AuditDAO;
import com.weili.wechat.dao.system.ModuleDAO;
import com.weili.wechat.dao.system.NameDAO;
import com.weili.wechat.dao.system.OperateDAO;
import com.weili.wechat.dao.system.UserDAO;
import com.weili.wechat.service.system.AuditService;
import com.weili.wechat.service.system.RoleService;
import com.weili.wechat.service.system.UserService;
import com.weili.wechat.vo.Audit;

public class AuditServiceImpl extends RetInfo implements AuditService {

	private Log log = LogFactory.getLog(AuditServiceImpl.class);

	private AuditDAO auditDAO;
	
	private OperateDAO operateDAO;

	private ModuleDAO moduleDAO;

	private UserDAO userDAO;
	
	private NameDAO nameDAO;
	
	private UserService userService;
	private RoleService roleService;
	
	
	

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public AuditDAO getAuditDAO() {
		return auditDAO;
	}

	public void setAuditDAO(AuditDAO auditDAO) {
		this.auditDAO = auditDAO;
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

	public int writeAudit(Audit audit) {
		// TODO Auto-generated method stub
		log.debug("添加操作审核---->开始!");
		
		log.debug("userid=["+audit.getUserId()+"] date=["+audit.getDate()+"]");
		
		this.getAuditDAO().save(audit);
		
		log.debug("添加操作审核---->结束!");
		return 0;
	}

	public Object audit(Audit audit) throws Exception{
		// TODO Auto-generated method stub
		Audit aAudit = this.qryAudit(audit.getUserId(), audit.getDate());
		
		XStream xstream = new XStream();
		log.info("参数类型=["+aAudit.getTypes()+"]");
		Class types[]= (Class[])xstream.fromXML(aAudit.getTypes());
		for (int i=0;i<types.length;i++){
			log.info("types["+i+"]="+types[i]);
		}
		log.info("参数=["+aAudit.getParams()+"]");
		Object params[]= (Object[])xstream.fromXML(aAudit.getParams());
		for (int i=0;i<params.length;i++){
			log.info("params["+i+"]="+params[i]);
		}
		Class c=Class.forName(aAudit.getModule().getNo()).getInterfaces()[0];
		log.info("类=["+aAudit.getModule().getNo()+"]");
        Method m=c.getDeclaredMethod(aAudit.getOperate().getNo(),types);
        log.info("方法=["+aAudit.getOperate().getNo()+"]");
        
        Object retVal = null;
//        if(aAudit.getModule().getNo().equals("com.weili.wechat.service.system.impl.OrgServiceImpl")){
//        	log.debug("执行com.weili.wechat.service.system.impl.OrgServiceImpl");
//        	retVal=m.invoke(this.getOrgService(),params);
//        	this.setRetMsg(this.getOrgService().getRetMsg());
//        }else if(aAudit.getModule().getNo().equals("com.weili.wechat.service.system.impl.RoleServiceImpl")){
//        	log.debug("执行com.weili.wechat.service.system.impl.RoleServiceImpl");
//        	retVal=m.invoke(this.getRoleService(),params);
//        	this.setRetMsg(this.getRoleService().getRetMsg());
//        }else if(aAudit.getModule().getNo().equals("com.weili.wechat.service.system.impl.UserServiceImpl")){
//        	log.debug("执行com.weili.wechat.service.system.impl.UserServiceImpl");
//        	retVal=m.invoke(this.getUserService(),params);
//        	this.setRetMsg(this.getUserService().getRetMsg());
//        }
//        if(retVal != null && retVal.toString().equals("0") || retVal == null){
//        	this.getAuditDAO().deleteAudit(audit);	
//        }
		return retVal;
	}

	public List qryAllUser() {
		// TODO Auto-generated method stub
		log.debug("查询系统用户信息------------------>");		
		List userList = this.getUserDAO().qryAll();
		return userList;
	}

	public Audit qryAudit(String userId, String date) {
		// TODO Auto-generated method stub
		return this.getAuditDAO().qryAudit(userId, date);
	}

	public List qryModule() {
		// TODO Auto-generated method stub
		return this.getModuleDAO().qryAll();
	}

	public List qryOperateByModule(String moduleId) {
		// TODO Auto-generated method stub
		return this.getOperateDAO().qryOperateByModule(moduleId);
	}

	public List qry_MyAuditList(String userId, String module, String operate, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return this.getAuditDAO().qry_MyAuditList(userId, module, operate, startTime, endTime);
	}

	public List qry_OtherAuditList(String userId, String module, String operate, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return this.getAuditDAO().qry_OtherAuditList(userId, module, operate, startTime, endTime);
	}

	public List qry_MyAuditList(String userId) {
		// TODO Auto-generated method stub
		return this.getAuditDAO().qry_MyAuditList(userId);
	}

	public List qry_OtherAuditList(String userId) {
		// TODO Auto-generated method stub
		return this.getAuditDAO().qry_OtherAuditList(userId);
	}

	public int refuse(Audit audit) {
		// TODO Auto-generated method stub
		
		this.getAuditDAO().deleteAudit(audit);
		return 0;
	}

	public List qryUser(String orgId) {
		// TODO Auto-generated method stub
//		return this.getUserDAO().qryUser(orgId);
		return null;
	}

	public List qryName() {
		// TODO Auto-generated method stub
		return this.getNameDAO().qryAll();
	}

}
