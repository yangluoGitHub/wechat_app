package com.weili.wechat.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import com.weili.wechat.service.system.LogAuditService;
import com.weili.wechat.service.system.UserService;
import com.weili.wechat.vo.Audit;
import com.weili.wechat.vo.LogAudit;
import com.weili.wechat.vo.Module;
import com.weili.wechat.vo.Operate;
import com.weili.wechat.vo.User;
import com.weili.wechat.web.system.UserController;

public class LogAspect {
	
	private LogAudit logAudit;
	private LogAuditService logAuditService;
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public LogAudit getLogAudit() {
		return this.logAudit;
	}

	public void setLogAudit(LogAudit logAudit) {
		this.logAudit = logAudit;
	}

	public LogAuditService getLogAuditService() {
		return logAuditService;
	}

	public void setLogAuditService(LogAuditService logAuditService) {
		this.logAuditService = logAuditService;
	}

	public void before(JoinPoint jp) {
		// System.out.println("��־���� ǰ֪ͨ :-------------------------------------------------------");
		// System.out.println("���ԣ�����������������������"+jp.getTarget().getClass());
		// System.out.println("���ԣ�����������������������"+jp.getSignature().getName());
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date(System.currentTimeMillis()));
		this.logAudit.setDate(date);
		// System.out.println("���ԣ�����������������������"+jp.getSignature().getName()+"---date=="+date);

		int ix = 0;
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		while (ix < stack.length) {

			StackTraceElement frame = stack[ix];
			String cname = frame.getClassName();
			if (cname.equals("com.weili.wechat.web.system.AuditController")) {
				return;
			}
			// System.out.println("StackTraceElement["+ix+"]="+cname);
			ix++;
		}

		Module aModule = new Module();
		aModule.setNo(jp.getTarget().getClass().getName());
		Operate aOperate = new Operate();
		aOperate.setNo(jp.getSignature().getName());
		// System.out.println("ģ��=["+jp.getTarget().getClass().getName()+"]");
		// System.out.println("����=["+jp.getSignature().getName()+"]");

		boolean flag = false;
		if (jp.getTarget().getClass().getName().equals("com.weili.wechat.service.system.impl.UserServiceImpl")) {
			int roleNo = ((User) jp.getArgs()[0]).getRole().getNo();
			if (roleNo != 100 && roleNo != 200) {
				flag = true;
			}
			if (jp.getSignature().getName().equals("modUserPasswd")) {
				flag = false;
			}
			// if(jp.getSignature().getName().equals("addUser")){
			// roleNo = ((User)jp.getArgs()[0]).getRole().getNo();
			// if(roleNo!=100&&roleNo!=200){
			// flag = true ;
			// }
			// }else if(jp.getSignature().getName().equals("modUser")){
			// roleNo = ((User)jp.getArgs()[0]).getRole().getNo();
			// if(roleNo!=100&&roleNo!=200){
			// flag = true ;
			// }
			// }else if(jp.getSignature().getName().equals("delUser")){
			// roleNo = ((User)jp.getArgs()[0]).getRole().getNo();
			// if(roleNo!=100&&roleNo!=200){
			// flag = true ;
			// }
			// }
		}

//		if (jp.getTarget().getClass().getName().equals("com.weili.wechat.service.system.impl.OrgServiceImpl")) {
//			int orgType = ((Org) jp.getArgs()[0]).getOrgType().getNo();
//			if (orgType != 2) {
//				flag = true;
//			}
//			// if(jp.getSignature().getName().equals("addOrg")){
//			// int orgType = ((Org)jp.getArgs()[0]).getOrgType().getNo();
//			// if(orgType!=2){
//			// flag = true ;
//			// }
//			// }else if(jp.getSignature().getName().equals("modOrg")){
//			// int orgType = ((Org)jp.getArgs()[0]).getOrgType().getNo();
//			// if(orgType!=2){
//			// flag = true ;
//			// }
//			// }else if(jp.getSignature().getName().equals("delOrg")){
//			// int orgType = ((Org)jp.getArgs()[0]).getOrgType().getNo();
//			// if(orgType!=2){
//			// flag = true ;
//			// }
//			// }
//		}

		if (jp.getTarget().getClass().getName().equals("com.weili.wechat.service.system.impl.RoleServiceImpl")) {
			flag = true;
		}
		if (flag) {
			logAudit.setModule(aModule);

			logAudit.setOperate(aOperate);

			// logAudit.setNote("�����");

			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, UserController.class);
			//Annotations.configureAliases(xstream, Org.class);
			Annotations.configureAliases(xstream, Object[].class);
			xstream.alias("��ϸ����", Object[].class);
			String xml = xstream.toXML(jp.getArgs());
			// System.out.println(xml);
			logAudit.setXml(xml);
			this.getLogAuditService().writeLog(logAudit);
		}
	}

	public void afterReturning(JoinPoint jp, Object retVal) {
		// System.out.println("��־���� ��֪ͨ��ʼ:-------------------------------------------------------");
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date(System.currentTimeMillis()));
		this.logAudit.setDate(date);
		// System.out.println("���ԣ�����������������������"+jp.getSignature().getName()+"---date=="+date);
		// System.out.println("����ֵ=["+retVal+"]");
		// flag �Ƿ���Ҫ���
		// boolean
		// flag=(SystemCons.getBankVersion().equalsIgnoreCase("BJ")||SystemCons.getBankVersion().equalsIgnoreCase("BZ"));
		// �����������ֵ
		boolean flag = SystemCons.sysParamMap.get("auditFlag").equals("false");

		if (retVal != null && retVal.toString().equals("0") || retVal == null) {
			// System.out.println("������=["+this.getLogAudit().getUserName()+"]");
			// System.out.println("����ʱ��=["+this.getLogAudit().getDate()+"]");
			Module aModule = new Module();
			aModule.setNo(jp.getTarget().getClass().getName());
			Operate aOperate = new Operate();
			aOperate.setNo(jp.getSignature().getName());
			// System.out.println("ģ��=["+jp.getTarget().getClass().getName()+"]");
			// System.out.println("����=["+jp.getSignature().getName()+"]");
			if (jp.getTarget().getClass().getName().equals("com.weili.wechat.service.system.impl.UserServiceImpl")) {
				int roleNo = 0;
				if (jp.getSignature().getName().equals("addUser")) {
					roleNo = ((User) jp.getArgs()[0]).getRole().getNo();
					if (roleNo == 100) {
						aOperate.setNo("addDEV_ADMIN");
					} else if (roleNo == 200) {
						aOperate.setNo("addDEV_SERVICE_PERSON");
					} else {
						if (!flag)
							return;
					}
				} else if (jp.getSignature().getName().equals("modUser")) {
					roleNo = ((User) jp.getArgs()[0]).getRole().getNo();
					if (roleNo == 100) {
						aOperate.setNo("modDEV_ADMIN");
					} else if (roleNo == 200) {
						aOperate.setNo("modDEV_SERVICE_PERSON");
					} else {
						if (!flag)
							return;
					}
				} else if (jp.getSignature().getName().equals("delUser")) {
					roleNo = ((User) jp.getArgs()[0]).getRole().getNo();
					if (roleNo == 100) {
						aOperate.setNo("delDEV_ADMIN");
					} else if (roleNo == 200) {
						aOperate.setNo("delDEV_SERVICE_PERSON");
					} else {
						if (!flag)
							return;
					}
				}
				if (roleNo == 100) {
					aModule.setNo("DEV_ADMIN");
				} else if (roleNo == 200) {
					aModule.setNo("DEV_SERVICE_PERSON");
				}

			}

//			if (jp.getTarget().getClass().getName().equals("com.weili.wechat.service.system.impl.OrgServiceImpl")) {
//				if (jp.getSignature().getName().equals("addOrg")) {
//					int orgType = ((Org) jp.getArgs()[0]).getOrgType().getNo();
//					if (orgType == 2) {
//						aModule.setNo("DEV_SERVICE_COMPANY");
//						aOperate.setNo("addDEV_SERVICE_COMPANY");
//					} else {
//						if (!flag)
//							return;
//					}
//
//				} else if (jp.getSignature().getName().equals("modOrg")) {
//					int orgType = ((Org) jp.getArgs()[0]).getOrgType().getNo();
//					if (orgType == 2) {
//						aModule.setNo("DEV_SERVICE_COMPANY");
//						aOperate.setNo("modDEV_SERVICE_COMPANY");
//					} else {
//						if (!flag)
//							return;
//					}
//
//				} else if (jp.getSignature().getName().equals("delOrg")) {
//					int orgType = ((Org) jp.getArgs()[0]).getOrgType().getNo();
//					if (orgType == 2) {
//						aModule.setNo("DEV_SERVICE_COMPANY");
//						aOperate.setNo("delDEV_SERVICE_COMPANY");
//					} else {
//						if (!flag)
//							return;
//					}
//				}
//			}
			if (jp.getTarget().getClass().getName().equals("com.weili.wechat.service.system.impl.RoleServiceImpl")) {
				if (!flag)
					return;
			}
			if (jp.getTarget().getClass().getName().equals("com.weili.wechat.service.system.impl.DevBaseInfoServiceImpl")) {
				if (!flag)
					return;
			}
			String targetName =jp.getTarget().getClass().getName();
			if (targetName.equals("com.weili.wechat.service.manage.impl.MessageServiceImpl")) {
				if (logAudit.getUserId() == null || logAudit.getUserName() == null) {
					logAudit.setUserId("WechatMessageReply");
					logAudit.setUserName("΢����Ϣ");
				}
				if (!flag)
					return;
			}

			logAudit.setModule(aModule);

			logAudit.setOperate(aOperate);

			// logAudit.setNote("�ɹ�");

			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, UserController.class);
			//Annotations.configureAliases(xstream, Org.class);
			Annotations.configureAliases(xstream, Object[].class);
			String xml = xstream.toXML(jp.getArgs());
			// System.out.println("jp.getArgs()={"+jp.getArgs()+"}");
			// System.out.println(xml);
			logAudit.setXml(xml);

			// ������˲��������⴦��
			if ((!flag) && (logAudit.getOperate().getNo().equals("refuse") || logAudit.getOperate().getNo().equals("audit"))) {
				Audit ad = (Audit) jp.getArgs()[0];
				logAudit.setOldXml(xml);
				logAudit.setXml(xml);
			}
			this.getLogAuditService().writeLog(logAudit);
			// System.out.println(logAudit.getOperate().getNo());
			if ((!flag) && (logAudit.getOperate().getNo().equals("refuse") || logAudit.getOperate().getNo().equals("audit"))) {
				Object params[] = (Object[]) xstream.fromXML(xml);
				Audit audit_tmp = (Audit) params[0];
				String userId = audit_tmp.getUserId();
				audit_tmp.setUserId(logAudit.getUserId());
				audit_tmp.setDate(logAudit.getDate());
				logAudit.setUserId(userId);
				if (logAudit.getOperate().getNo().equals("refuse")) {
					aOperate.setNo("beRefuse");
				} else {
					aOperate.setNo("beAudit");
				}
				logAudit.setOperate(aOperate);
				logAudit.setUserName(this.userService.qryUserById(userId).getName());
				params[0] = audit_tmp;
				xml = xstream.toXML(params);
				logAudit.setXml(xml);
				logAudit.setOldXml(xml);
				// logAudit.setXml(audit_tmp.getParams());
				// logAudit.setOldXml(audit_tmp.getOldParams());
				this.getLogAuditService().writeLog(logAudit);
			}
		}
		// System.out.println("��־���� ����:-------------------------------------------------------");
	}
}
