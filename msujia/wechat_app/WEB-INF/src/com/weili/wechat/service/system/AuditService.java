//Source file: E:\\Program Files\\workspace\\tsp_bh\\WEB-INF\\src\\com\\weili\\tsp\\service\\system\\AuditService.java

package com.weili.wechat.service.system;

import com.weili.wechat.common.IRetInfo;
import java.util.List;
import com.weili.wechat.vo.Audit;
import com.weili.wechat.vo.LogAudit;

public interface AuditService extends IRetInfo 
{
   
   /**
    * @param audit
    * @return int
    * @roseuid 47E32E09032C
    */
   public int writeAudit(Audit audit);
   
   /**
    * @param audit
    * @return int
    * @roseuid 47E32E2201B5
    */
   public Object audit(Audit audit) throws Exception;
   
   public int refuse(Audit audit);
   
   
	public List qryAllUser();

	public List qry_MyAuditList(String userId);
	
	public List qry_MyAuditList(String userId,String module, String operate, String startTime, String endTime) ;
	
	public List qry_OtherAuditList(String userId);
	
	public List qry_OtherAuditList(String userId,String module, String operate, String startTime, String endTime) ;
	
	public List qryModule();
	
	public List qryName();
	
	public List qryOperateByModule(String moduleId);
	
	public Audit qryAudit(String userId, String date);
	
	public List qryUser(String orgId);
}
