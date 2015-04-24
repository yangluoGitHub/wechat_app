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
		//��ѯ�Ƿ���Ҫ�������
		if (String.valueOf(SystemCons.sysParamMap.get("auditFlag")).equalsIgnoreCase("false"))
			return pjp.proceed();
		
//		System.out.println("�����������--------��ʼ");
		Object retVal = null;		
		if(pjp.getTarget().getClass().getName().equals("com.weili.wechat.service.system.impl.UserServiceImpl")){
			int roleNo = -1;
			User aUser = (User)pjp.getArgs()[0];
			if(aUser.getRole() != null){
				roleNo = aUser.getRole().getNo();	
			}
			if(roleNo == 100 || roleNo == 200 || pjp.getSignature().getName().equals("modUserPasswd")){
				retVal = pjp.proceed();
//				System.out.println("�����������----��������----����");
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
////				System.out.println("�����������-----��������---����");
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
//				System.out.println("�����������-----�Ӳ��������������,������---����");
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
//		System.out.println("�����������--------����");
		if(retVal.equals(0)) {
			//���Ϊ0����ʾ�����Ϣ����ɹ�����ʱ����retValΪ99,99��ʾ������У�
			//controller�����retVal�ж����ύ��ˣ������ύ���£�������ύ���(�ж�Ϊ99)������ʾ�������
			retVal = OperateAspect.AUDIT_FLAG;
		}
	    return retVal;
	}
}
