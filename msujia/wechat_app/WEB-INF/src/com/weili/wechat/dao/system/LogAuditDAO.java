package com.weili.wechat.dao.system;

import java.util.List;

import com.weili.wechat.common.DAO;
import com.weili.wechat.vo.LogAudit;
import com.weili.wechat.vo.LogAudit_local;

/**
 * ϵͳ��־DAO�ṹ
 */
public interface LogAuditDAO extends DAO 
{   
   /**
    * ��ѯϵͳ������־�б�
    * @return List
    * @roseuid 47560DEE00DA
    */
   public List qry_LogAuditList(String userId,String module, String operate, String startTime, String endTime) ;
   
   public List qry_LogAuditList(String upper1 ,String userId,String module, String operate, String startTime, String endTime) ;
   
   public List qry_LogAuditList1(String orgNo,String module, String operate, String startTime, String endTime);
   /**
    * ������־����
    * 
    */
   public void saveLogAudit(LogAudit logAudit);	
   
   public LogAudit_local qryLogAudit(String userId,String date);
	

}
