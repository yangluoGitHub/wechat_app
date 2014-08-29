package com.weili.wechat.dao.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.HibernateDAO;
import com.weili.wechat.common.RetInfo;
import com.weili.wechat.dao.system.AuditDAO;
import com.weili.wechat.hibernate.SysAudit;
import com.weili.wechat.hibernate.SysAuditId;
import com.weili.wechat.hibernate.SysLog;
import com.weili.wechat.hibernate.SysLogId;
import com.weili.wechat.hibernate.SysModule;
import com.weili.wechat.hibernate.SysOperate;
import com.weili.wechat.vo.Audit;
import com.weili.wechat.vo.Module;
import com.weili.wechat.vo.Operate;

public class AuditDAOImpl extends HibernateDAO implements AuditDAO {

	private Log log = LogFactory.getLog(AuditDAOImpl.class);
	
    private Class getRefClass() {
        return com.weili.wechat.hibernate.SysAudit.class;
    }
    
	public SysAudit vo_po(Audit audit){
		if(audit == null) return null;
		SysAuditId aSysAuditId = new SysAuditId();	
		aSysAuditId.setUserid(audit.getUserId());
		aSysAuditId.setAuditDate(audit.getDate());
		
		SysAudit aSysAudit = new SysAudit();
		aSysAudit.setId(aSysAuditId);
		aSysAudit.setUsername(audit.getUserName());
		
		if(audit.getOperate() != null){
			SysOperate aSysOperate = new SysOperate();
			aSysOperate.setNo(audit.getOperate().getNo());
			
			aSysAudit.setSysOperate(aSysOperate);
			
		}
		if(audit.getModule() != null){
			SysModule aSysmodule = new SysModule();
			aSysmodule.setNo(audit.getModule().getNo());
			
			aSysAudit.setSysModule(aSysmodule);
		}
		aSysAudit.setTypes(audit.getTypes());
		aSysAudit.setParams(audit.getParams());
		aSysAudit.setOldParams(audit.getOldParams());
		
		return aSysAudit;	
	}
	
	public Audit po_vo(SysAudit sysAudit){
		if(sysAudit == null) return null;
		Audit audit = new Audit();
		audit.setUserId(sysAudit.getId().getUserid());
		audit.setDate(sysAudit.getId().getAuditDate());
		audit.setUserName(sysAudit.getUsername());
		audit.setTypes(sysAudit.getTypes());
		audit.setParams(sysAudit.getParams());
		audit.setOldParams(sysAudit.getOldParams());
		
		if(sysAudit.getSysModule() != null){
			Module aModule = new Module();
			aModule.setNo(sysAudit.getSysModule().getNo());
			aModule.setName(sysAudit.getSysModule().getName());
			
			audit.setModule(aModule);
		}
		
		if(sysAudit.getSysOperate() != null){
			Operate aOperate = new Operate();
			aOperate.setNo(sysAudit.getSysOperate().getNo());
			aOperate.setName(sysAudit.getSysOperate().getName());
			
			audit.setOperate(aOperate);
		}
		return audit;
	}
	
	public List po_vo_List(List poList){
		List<Audit> voList = new ArrayList<Audit>();
		log.debug("-----------------"+poList.size());
		for(int i=0;i<poList.size();i++){
			SysAudit sysAudit = (SysAudit)poList.get(i);
			Audit audit = po_vo(sysAudit);
			voList.add(audit);
		}
		return voList;
	}
	
	public List qryMyAudits(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List qyrOtherAudits(String userId) {
		// TODO Auto-generated method stub
		return null;
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
		SysAudit aSysAudit = vo_po((Audit)obj);
		this.saveObject(aSysAudit);
	}

	public void update(Object obj) {
		// TODO Auto-generated method stub
		
	}

	public Audit qryAudit(String userId, String date) {
		// TODO Auto-generated method stub
		SysAuditId aSysAuditId = new SysAuditId();	
		aSysAuditId.setUserid(userId);
		aSysAuditId.setAuditDate(date);

		SysAudit aSysAudit=(SysAudit)super.findById(getRefClass(), aSysAuditId);
		return po_vo(aSysAudit);
	}

	public List qry_MyAuditList(String userId, String module, String operate, String startTime, String endTime) {
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
		String HQL="from SysAudit s where s.id.auditDate >='"+startTime+"'"+" and s.id.auditDate <='"+endTime+"'";
		if (userId.length()>0  && userId!=null){
			HQL=HQL+" and s.id.userid = '"+userId+"'";
		}
		if(module!=null && module.length()>0){
			HQL=HQL+" and s.sysModule.no= '"+module+"'";  	
		}
		if(operate!=null && operate.length()>0){
			HQL=HQL+" and s.sysOperate.no= '"+operate+"'";  	
		}
		HQL = HQL + " order by s.id.auditDate desc";
		log.debug("HQL="+HQL);
		List auditList = this.find(HQL);
		
		auditList = po_vo_List(auditList);
		return auditList;
	}

	public List qry_OtherAuditList(String userId, String module, String operate, String startTime, String endTime) {
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
		String HQL="from SysAudit s where s.id.auditDate >='"+startTime+"'"+" and s.id.auditDate <='"+endTime+"'";
		if (userId.length()>0  && userId!=null){
			HQL=HQL+" and s.id.userid != '"+userId+"'";
		}
		if(module!=null && module.length()>0){
			HQL=HQL+" and s.sysModule.no= '"+module+"'";  	
		}
		if(operate!=null && operate.length()>0){
			HQL=HQL+" and s.sysOperate.no= '"+operate+"'";  	
		}
		HQL = HQL + " order by s.id.auditDate desc"; 
		log.debug("HQL="+HQL);
		List auditList = this.find(HQL);
		
		auditList = po_vo_List(auditList);
		return auditList;
	}

	public void deleteAudit(Audit audit) {
		// TODO Auto-generated method stub
		
		SysAuditId aSysAuditId = new SysAuditId();
		aSysAuditId.setUserid(audit.getUserId());
		aSysAuditId.setAuditDate(audit.getDate());
		
		SysAudit aSysAudit = (SysAudit)this.findById(getRefClass(), aSysAuditId);
		this.deleteObject(aSysAudit);
			
	}

	public List qry_MyAuditList(String userId) {
		// TODO Auto-generated method stub
		String HQL="from SysAudit s ";
		if (userId.length()>0  && userId!=null){
			HQL=HQL+" where s.id.userid = '"+userId+"'";
		}
		HQL = HQL + " order by s.id.auditDate desc";
		log.debug("HQL="+HQL);
		List auditList = this.find(HQL);
		
		auditList = po_vo_List(auditList);
		return auditList;
	}

	public List qry_OtherAuditList(String userId) {
		// TODO Auto-generated method stub
		String HQL="from SysAudit s ";
		if (userId.length()>0  && userId!=null){
			HQL=HQL+" where s.id.userid != '"+userId+"'";
		}
		HQL = HQL + " order by s.id.auditDate desc";
		log.debug("HQL="+HQL);
		List auditList = this.find(HQL);
		
		auditList = po_vo_List(auditList);
		return auditList;
	}

}
