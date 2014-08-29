package com.weili.wechat.dao.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.HibernateDAO;
import com.weili.wechat.dao.system.LogAuditDAO;
import com.weili.wechat.hibernate.SysLog;
import com.weili.wechat.hibernate.SysLogId;
import com.weili.wechat.hibernate.SysModule;
import com.weili.wechat.hibernate.SysOperate;
import com.weili.wechat.vo.LogAudit;
import com.weili.wechat.vo.LogAudit_local;
import com.weili.wechat.vo.Module;
import com.weili.wechat.vo.Operate;

public class LogAuditDAOImpl extends HibernateDAO implements LogAuditDAO
{
	private Log log = LogFactory.getLog(LogAuditDAOImpl.class);
	
    private Class getRefClass() {
        return com.weili.wechat.hibernate.SysLog.class;
    }
    
	public List qry_LogAuditList(String userName,String operate,String startTime,String endTime){
		if ( startTime.equals("") )
     	{
			startTime = "1900-01-01";
     	}
     	if ( endTime.equals("") )
     	{
     		endTime = "9999-12-31";
     	}
		String HQL="from SysLog s where s.id.logDate >'"+startTime+"'"+" and s.id.logDate <'"+endTime+"'";
		if (userName.length()>0  && userName!=null){
			HQL=HQL+" and s.username = '"+userName+"'";
		}
		
		HQL=HQL+" and s.operate like '%"+operate+"%'";  
		HQL = HQL + " order by s.id.logDate desc";
		log.debug("HQL="+HQL);
		List logAuditList = this.find(HQL);
		
		logAuditList = po_vo_List(logAuditList);
		return logAuditList;
	}
	
	public void saveLogAudit(LogAudit logAudit){
		SysLog sysLog = vo_po(logAudit);
		log.debug("记日志----->");
		log.debug("userid=["+sysLog.getId().getUserid()+"] date=["+sysLog.getId().getLogDate()+"]");
		log.debug("module=["+sysLog.getSysModule().getNo()+"]");
		log.debug("operate=["+sysLog.getSysOperate().getNo()+"]");
		log.debug("xml=["+sysLog.getXml()+"]");
		this.saveObject(sysLog);
	}
	
	
	public SysLog vo_po(LogAudit logAudit){
		if(logAudit == null) return null;
		SysLogId aSysLogId = new SysLogId();	
		aSysLogId.setUserid(logAudit.getUserId());
		aSysLogId.setLogDate(logAudit.getDate());
		
		SysLog aSysLog = new SysLog();
		aSysLog.setId(aSysLogId);
		aSysLog.setUsername(logAudit.getUserName());
		
		if(logAudit.getOperate() != null){
			SysOperate aSysOperate = new SysOperate();
			aSysOperate.setNo(logAudit.getOperate().getNo());
			aSysLog.setSysOperate(aSysOperate);
			
		}
		if(logAudit.getModule() != null){
			SysModule aSysmodule = new SysModule();
			aSysmodule.setNo(logAudit.getModule().getNo());
			aSysLog.setSysModule(aSysmodule);
		}
		aSysLog.setXml(logAudit.getXml());
		aSysLog.setXmlOld(logAudit.getOldXml());
		aSysLog.setNote(logAudit.getNote());
		return aSysLog;	
	}
	
	public LogAudit_local po_vo(SysLog sysLog){
		if(sysLog == null) return null;
		LogAudit_local logAudit_local = new LogAudit_local();
//		log.info("sysLog.getId().getLogDate())="+sysLog.getId().getLogDate());
		logAudit_local.setUserId(sysLog.getId().getUserid());
		logAudit_local.setDate(sysLog.getId().getLogDate());
		logAudit_local.setUserName(sysLog.getUsername());
		
		//System.out.println("日志数据＝"+sysLog.getXml());
		logAudit_local.setXml(sysLog.getXml());
		logAudit_local.setOldXml(sysLog.getXmlOld());
		
		if(sysLog.getSysModule() != null){
			Module aModule = new Module();
			aModule.setNo(sysLog.getSysModule().getNo());
			aModule.setName(sysLog.getSysModule().getName());
			
			logAudit_local.setModule(aModule);
		}
		
		if(sysLog.getSysOperate() != null){
			Operate aOperate = new Operate();
			aOperate.setNo(sysLog.getSysOperate().getNo());
			aOperate.setName(sysLog.getSysOperate().getName());
			
			logAudit_local.setOperate(aOperate);
		}
		
		logAudit_local.setNote(sysLog.getNote());
		return logAudit_local;
	}
	
	public List po_vo_List(List poList){
		List<LogAudit_local> voList = new ArrayList<LogAudit_local>();
		log.debug("-----------------"+poList.size());
		for(int i=0;i<poList.size();i++){
			SysLog sysLog = (SysLog)poList.get(i);
			LogAudit_local logAudit_local = po_vo(sysLog);
//			log.info("logAudit.getDate()="+logAudit.getDate());
			voList.add(logAudit_local);
		}
		return voList;
	}

	public List qry_LogAuditList(String userId, String module, String operate, String startTime, String endTime) {
		// TODO Auto-generated method stub
		if ( startTime.equals("") )
     	{
			startTime = "1900-01-01 00:00:00";
     	}else{
     		startTime = startTime + " 00:00:00";
     	}
     	if ( endTime.equals("") )
     	{
     		endTime = "9999-12-31 23:59:59";
     	}else{
     		endTime = endTime + " 23:59:59";
     	}
		String HQL=" from SysLog s where s.id.logDate >='"+startTime+"'"+" and s.id.logDate <='"+endTime+"'";
		if (userId.length()>0  && userId!=null){
			HQL=HQL+" and s.id.userid = '"+userId+"'";
		}
		if(module!=null && module.length()>0){
			HQL=HQL+" and s.sysModule.no= '"+module+"'";  	
		}
		if(operate!=null && operate.length()>0){
			HQL=HQL+" and s.sysOperate.no= '"+operate+"'";  	
		}
		HQL = HQL + " order by s.id.logDate desc"; 
		log.info("HQL="+HQL);
		List logAuditList = this.find(HQL);
		
		logAuditList = po_vo_List(logAuditList);
		return logAuditList;
	}
	public List qry_LogAuditList(String upper1 ,String userId, String module, String operate, String startTime, String endTime) {
		// TODO Auto-generated method stub
		if ( startTime.equals("") )
     	{
			startTime = "1900-01-01 00:00:00";
     	}else{
     		startTime = startTime + " 00:00:00";
     	}
     	if ( endTime.equals("") )
     	{
     		endTime = "9999-12-31 23:59:59";
     	}else{
     		endTime = endTime + " 23:59:59";
     	}
		String HQL="select s  from SysLog s,OpTable o,OrgTable t where s.id.logDate >='"+startTime+"'"+" and s.id.logDate <='"+endTime+"'";
		if(upper1!=null&&upper1.length()>0){
			HQL=HQL+" and o.no=s.id.userid and t.no=o.orgTable.no and (t.no='"+upper1+"' or t.upper1='"+upper1+"' or t.upper2='"+upper1+"' or t.upper3='"+upper1+"' or t.upper4='"+upper1+"' or t.upper5='"+upper1+"') ";
		}
		if (userId.length()>0  && userId!=null){
			HQL=HQL+" and s.id.userid = '"+userId+"'";
		}
		if(module!=null && module.length()>0){
			HQL=HQL+" and s.sysModule.no= '"+module+"'";  	
		}
		if(operate!=null && operate.length()>0){
			HQL=HQL+" and s.sysOperate.no= '"+operate+"'";  	
		}
		HQL = HQL + " order by s.id.logDate desc"; 
		log.debug("HQL="+HQL);
		List logAuditList = this.find(HQL);
		
		logAuditList = po_vo_List(logAuditList);
		return logAuditList;
	}
	
	public List qry_LogAuditList1(String orgNo,String module, String operate, String startTime, String endTime) {
		// TODO Auto-generated method stub
		if ( startTime.equals("") )
     	{
			startTime = "1900-01-01 00:00:00";
     	}else{
     		startTime = startTime + " 00:00:00";
     	}
     	if ( endTime.equals("") )
     	{
     		endTime = "9999-12-31 23:59:59";
     	}else{
     		endTime = endTime + " 23:59:59";
     	}
		String HQL="select s  from SysLog s,OpTable o,OrgTable t where s.id.logDate >='"+startTime+"'"+" and s.id.logDate <='"+endTime+"'";

		if(orgNo!=null&&orgNo.length()>0){
			HQL=HQL+" and o.no=s.id.userid and t.no=o.orgTable.no and (t.no='"+orgNo+"' or t.upper1='"+orgNo+"' or t.upper2='"+orgNo+"' or t.upper3='"+orgNo+"' or t.upper4='"+orgNo+"' or t.upper5='"+orgNo+"') ";
		}
		if(module!=null && module.length()>0){
			HQL=HQL+" and s.sysModule.no= '"+module+"'";  	
		}
		if(operate!=null && operate.length()>0){
			HQL=HQL+" and s.sysOperate.no= '"+operate+"'";  	
		}
		HQL = HQL + " order by s.id.logDate desc"; 
		log.debug("HQL="+HQL);
		List logAuditList = this.find(HQL);
		
		logAuditList = po_vo_List(logAuditList);
		return logAuditList;
	}

	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	public List qryAll() {
		// TODO Auto-generated method stub
		return null;
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

	public LogAudit_local qryLogAudit(String userId, String date) {
		// TODO Auto-generated method stub
		SysLogId aSysLogId = new SysLogId();	
		aSysLogId.setUserid(userId);
		aSysLogId.setLogDate(date);

		SysLog aSysLog=(SysLog)super.findById(getRefClass(), aSysLogId);
		return po_vo(aSysLog);
	}
}
