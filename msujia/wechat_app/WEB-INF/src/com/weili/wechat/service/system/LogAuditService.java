//Source file: E:\\Program Files\\workspace\\pmcm\\src\\com\\weili\\pmcm\\service\\LogAuditService.java

package com.weili.wechat.service.system;

import java.util.List;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.vo.LogAudit;
import com.weili.wechat.vo.LogAudit_local;

public interface LogAuditService extends IRetInfo 
{
	
	public void writeLog(LogAudit logAudit);

	public List qryAllUser();

	public List qry_LogAuditList(String userId,String module, String operate, String startTime, String endTime);
	
	public List qry_LogAuditList1(String orgNo,String module, String operate, String startTime, String endTime);
	
	public List qry_LogAuditList(String upper1 ,String userId,String module, String operate, String startTime, String endTime) ;
	
	public List qryModule();
	
	public List qryOperate();
	
	public List qryName();
	
	public List qryOperateByModule(String moduleId);
	
	public LogAudit_local qryLogAudit(String userId, String date);
	
	public List qryUser(String orgId);
	
	public List qryAllUserByRole(String orgId);
}
