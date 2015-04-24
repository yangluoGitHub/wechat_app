//Source file: E:\\Program Files\\workspace\\shepherd_bh\\WEB-INF\\src\\com\\weili\\\tsp\\\dao\\system\\AuditDAO.java

package com.weili.wechat.dao.system;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.common.DAO;
import com.weili.wechat.vo.Audit;

import java.util.List;

public interface AuditDAO extends IRetInfo, DAO 
{
   
	public List qry_MyAuditList(String userId,String module, String operate, String startTime, String endTime) ;
	
	public List qry_MyAuditList(String userId);
	
	public List qry_OtherAuditList(String userId,String module, String operate, String startTime, String endTime) ;
	
	public List qry_OtherAuditList(String userId);
	
	public Audit qryAudit(String userId, String date);
	
	public void deleteAudit(Audit audit);
}
