package com.weili.wechat.common;

import org.aspectj.lang.ProceedingJoinPoint;

import com.thoughtworks.xstream.XStream;
import com.weili.wechat.service.system.AuditService;
import com.weili.wechat.vo.Audit;
import com.weili.wechat.vo.LogAudit;
import com.weili.wechat.vo.Module;
import com.weili.wechat.vo.Operate;
import com.weili.wechat.vo.User;

public class OperateAspect {
	
	private LogAudit logAudit;
	
	private Audit audit;
	
	private AuditService auditService;
	
	public static final int AUDIT_FLAG = 99;
	
	public LogAudit getLogAudit() {
		return logAudit;
	}


	public void setLogAudit(LogAudit logAudit) {
		this.logAudit = logAudit;
	}


	public Audit getAudit() {
		return audit;
	}


	public void setAudit(Audit audit) {
		this.audit = audit;
	}


	public AuditService getAuditService() {
		return auditService;
	}


	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}


	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		//查询是否需要审核配置
		if (String.valueOf(SystemCons.sysParamMap.get("auditFlag")).equalsIgnoreCase("false"))
			return pjp.proceed();
		
//		System.out.println("操作审核切面--------开始");
		Object retVal = null;		
		if(pjp.getTarget().getClass().getName().equals("com.weili.wechat.service.system.impl.UserServiceImpl")){
			int roleNo = -1;
			User aUser = (User)pjp.getArgs()[0];
			if(aUser.getRole() != null){
				roleNo = aUser.getRole().getNo();	
			}
			if(roleNo == 100 || roleNo == 200 || pjp.getSignature().getName().equals("modUserPasswd")){
				retVal = pjp.proceed();
//				System.out.println("操作审核切面----不用审啦----结束");
			    return retVal;
			}
		}
		
//		if(pjp.getTarget().getClass().getName().equals("com.weili.wechat.service.system.impl.OrgServiceImpl")){
//			int orgType = -1;
//			Org aOrg = (Org)pjp.getArgs()[0];
//			if(aOrg.getOrgType() != null){
//				orgType = aOrg.getOrgType().getNo();
//			}
//			if(orgType == 2){
//				retVal = pjp.proceed();
////				System.out.println("操作审核切面-----不用审啦---结束");
//			    return retVal;
//			}
//		}
		
	 	StackTraceElement stack[] = (new Throwable()).getStackTrace();
	 	
	 	
	 	int ix = 0;
	 	while (ix < stack.length) {
	 		
		 	StackTraceElement frame = stack[ix];
		 	String cname = frame.getClassName();
		 	if(cname.equals("com.weili.wechat.web.system.AuditController")){
				retVal = pjp.proceed();
//				System.out.println("操作审核切面-----从操作审核请求来的,不用切---结束");
			    return retVal;
		 	}
		 	//System.out.println("StackTraceElement["+ix+"]="+cname);
		 	ix++;
	 	}
		
		String source = pjp.getSignature().toLongString();
		String temp = source.substring(source.indexOf("(")+1, source.indexOf(")"));
		String type[] = temp.split(",");
		Class types[]=new Class[type.length];
		Object params[] = pjp.getArgs();
		
		for(int i=0;i<type.length;i++){
//			System.out.println("type=["+type[i]+"]");
			if(type[i].equals("int")) {
				types[i]=int.class;
			}else if(type[i].equals("String")){
				types[i]= String.class ;
			}else{
				types[i]=Class.forName(type[i]);
			}
		}
		XStream xstream = new XStream();
		
		Module aModule = new Module();
		aModule.setNo(pjp.getTarget().getClass().getName());
		Operate aOperate = new Operate();
		aOperate.setNo(pjp.getSignature().getName());
		
		audit.setUserId(logAudit.getUserId());
		audit.setDate(logAudit.getDate());
		audit.setUserName(logAudit.getUserName());
		audit.setModule(aModule);
		
		audit.setOperate(aOperate);
		
		audit.setTypes(xstream.toXML(types));
		
		audit.setParams(xstream.toXML(pjp.getArgs()));
		
		audit.setOldParams(logAudit.getOldXml());
		
//		System.out.println(xstream.toXML(pjp.getArgs()));

		retVal = this.getAuditService().writeAudit(audit);
		
	    // stop stopwatch 
//		System.out.println("操作审核切面--------结束");
		if(retVal.equals(0)) {
			//如果为0，表示审核信息插入成功，此时重置retVal为99,99表示在审核中，
			//controller层根据retVal判断是提交审核，还是提交更新，如果是提交审核(判断为99)，则提示在审核中
			retVal = OperateAspect.AUDIT_FLAG;
		}
	    return retVal;
	}
}
