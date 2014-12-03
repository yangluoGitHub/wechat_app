package com.weili.wechat.web.login;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.weili.wechat.common.CalendarUtil;
import com.weili.wechat.common.GetResource;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.service.login.LoginService;
import com.weili.wechat.service.system.RegisterService;
import com.weili.wechat.service.system.SysRoleService;
import com.weili.wechat.service.system.UserService;
import com.weili.wechat.vo.Button;
import com.weili.wechat.vo.LogAudit;
import com.weili.wechat.vo.MOpTableVO;
import com.weili.wechat.vo.Menu;
import com.weili.wechat.vo.Role;
import com.weili.wechat.vo.User;

/**
 * �����½ϵͳ���˳�ϵͳ����
 * 
 */
public class LoginController extends MultiActionController {

	private static Log log = LogFactory.getLog(LoginController.class);

	private LogAudit logAudit;
	private LoginService loginService;
	private UserService userService;
	private SysRoleService sysRoleService;
    private RegisterService registerService;
	
	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	public LogAudit getLogAudit() {
		return logAudit;
	}

	public void setLogAudit(LogAudit logAudit) {
		this.logAudit = logAudit;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * ����Ƿ�ϵͳ��æ
	 * 
	 * @return
	 */
	public boolean isSysBusy() {
		boolean ret = false;
		String temp1 = this.getServletContext().getAttribute("UserOnLineNum").toString();
		log.info("UserOnLineNum==" + temp1);
		if (temp1 != null) {
			int userOnLineNum = Integer.parseInt(temp1);
			String temp2 = this.getServletContext().getInitParameter("MaxUserOnLineNum");
			if (temp2 == null || temp2.equals(""))
				temp2 = "100";
			int maxUserOnLineNum = Integer.parseInt(temp2);
			log.info("max=[" + maxUserOnLineNum + "] now=[" + userOnLineNum + "]");
			if (userOnLineNum >= maxUserOnLineNum) {
				ret = true;
			}
		}
		return ret;
	}
	
	/**
	 * ��¼ϵͳ
	 * 
	 * @param request
	 * @param response
	 * @return ��½ʧ�ܣ��ص���¼ҳ�棻��½�ɹ���������ҳ��
	 * @throws Exception
	 */
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView mv = new ModelAndView("login");
//		Translate translate = new Translate();
//		Locale lang;
//		String s = (String) request.getParameter("language");
//		if (s.equals("tw")) {
//			translate.setlanguage(2);
//			lang = Locale.TRADITIONAL_CHINESE;
//		} else if (s.equals("en")) {
//			translate.setlanguage(3);
//			lang = Locale.US;
//		} else {
//			translate.setlanguage(1);
//			lang = Locale.CHINA;
//		}
//		request.getSession().invalidate();
//		request.getSession().setAttribute("locale", lang.toString());
//
//		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(),
//				request.getSession().getAttribute("locale").toString());
//
//		try {
//			if(isSysBusy()) {
//				return mv.addObject("message", resource.srcStr("src.SysBusy"));
//			}
//			
//			String no = request.getParameter("account");
//			String passwd = request.getParameter("password");
//
//			User aUser = this.getLoginService().checkLogin(no, passwd);
//			log.info("������=[" + this.getLoginService().getRetCode() + "] ������Ϣ=[" + resource.srcStr(this.getLoginService().getRetMsg()) + "]");
//			if (this.getLoginService().getRetCode() != 0) {
//				String retMsg = this.getLoginService().getRetMsg();
//				String message = resource.srcStr(retMsg);
//				if (retMsg.indexOf('|') != -1) {
//					String retMsgs[] = retMsg.split("\\|");
//					message = "";
//					for (int i = 0; i < retMsgs.length; i++) {
//						log.debug("retMsgs[" + i + "]==" + retMsgs[i] + "resource.srcStr(retMsgs[" + i + "])" + resource.srcStr(retMsgs[i]));
//						message += resource.srcStr(retMsgs[i]);
//					}
//				}
//				return mv.addObject("message", message);
//			} else {
//				List menuList = new ArrayList();
//				if (aUser.getRole().getNo() < 90000) {
//					menuList = this.getLoginService().qryMenuByRole(aUser.getRole().getNo());
//				} else {
//					String[] notes = this.getSysRoleService().getRoleById(String.valueOf(aUser.getRole().getNo())).getNote().split("\\|");
//					for (String note : notes) {
//						menuList.addAll(this.getLoginService().qryMenuByRole(Integer.parseInt(note)));
//					}
//					for (int i = 0; i < menuList.size() - 1; i++) {
//						for (int j = i + 1; j < menuList.size(); j++) {
//							if (((Menu) menuList.get(i)).getNo().equals(((Menu) menuList.get(j)).getNo())) {
//								menuList.remove(j);
//								j--;
//							}
//						}
//					}
//				}
//
//				if (menuList.size() == 0) {
//					return mv.addObject("message", resource.srcStr("src.userNotLogin"));
//				}
//				UserSession aUserSession = new UserSession();
//				aUserSession.setAccount(aUser.getNo());
//				aUserSession.setName(aUser.getName());
//				aUserSession.setLoginIp(aUser.getLoginIp() == null ? "" : aUser.getLoginIp());
//				aUserSession.setLoginTime(aUser.getLoginTime() == null ? "" : aUser.getLoginTime());
//				
//
//				
//				aUserSession.setRoleNo(aUser.getRole().getNo());
//				aUserSession.setRoleName(aUser.getRole().getName());
//				aUserSession.setRoleCatalog(aUser.getRole().getCatalog());
//				aUserSession.setMenuList(menuList);
//				aUserSession.setOnline_flag(aUser.getOnline_flag());
//
//				aUserSession.setLoginIp_curr(request.getRemoteAddr());
//				aUserSession.setLoginTime_curr(Tool.getSysTimeYMDHMS());
//				
//				aUserSession.setWechatId(aUser.getWechatId());
//				
//				request.getSession().setAttribute("Available", true);
//				request.getSession().setAttribute("IP_Available", request.getRemoteAddr());
//				request.getSession().setAttribute("Time_Available", Tool.getSysTimeYMDHMS());
//				request.getSession().setAttribute("userSession", aUserSession);
//				SessionController.addSession(request, response);// �û���½����ʼ��userSession�����session��¼��
//
//				Integer[] roleNo = new Integer[3];
//				// ����û���ɫ��Ŵ���90000?
//				if (aUser.getRole().getNo() < 90000) {
//					roleNo[0] = aUser.getRole().getNo();
//				} else {
//					String[] notes = this.getSysRoleService().getRoleById(String.valueOf(aUser.getRole().getNo())).getNote().split("\\|");
//					for (int i = 0; i < notes.length; i++) {
//						roleNo[i] = Integer.parseInt(notes[i]);
//					}
//				}
//
//				init(request, roleNo, no);
//
//				aUser.setLoginTime(Tool.getSysTimeYMDHMS());
//				String loginIp = request.getRemoteAddr();
//				aUser.setLoginIp(loginIp);
//				this.getUserService().modUser(aUser);
//				this.getLoginService().loginLog();
//
//				request.getSession().setAttribute("sysDate", CalendarUtil.getSysTimeYMD());
//
//				// ��һ�ε�¼ǿ���޸�����
//				if (aUser.getOnline_flag().intValue() == -1) {
//					return new ModelAndView("login_first").addObject("flag","1");
//				} else {
//					if (this.getLoginService().checkLoginUpdateDate(no) == -1) {
//						return new ModelAndView("login_success").addObject("passtxDate",this.getLoginService().passwdFlarmDay(no));
//					} else { //�������
//						return new ModelAndView("login_first").addObject("flag", "0");
//					}
//				}
//			}
//			
//		} catch (Exception e) {
//			log.error("Error in LoginController.login: " + e.getClass() + ":" , e);
//			return mv.addObject("message", "��¼�쳣!");
//		}
		try {
			String mobile = StringUtil.parseString(request.getParameter("uid"));              //�ֻ���
			String passwd = StringUtil.parseString(request.getParameter("psw1"));                  //����
			                    //������  
			
			MOpTableVO vo = new MOpTableVO();
			vo.setMobile(mobile);
			vo.setPasswd(passwd);
			
			try {
				this.getRegisterService().register(vo);
				return new ModelAndView("info","message","ע��ɹ�");
			} catch (Exception e) {
				  return new ModelAndView("info","message","ע��ʧ��");
			}
		} catch (Exception e) {
			log.error("ע���쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "ע���쳣!");
		}
	}


	/**
	 * �˳�ϵͳ���ͷ�Session��Դ
	 * 
	 * @param request
	 * @param response
	 * @return ���ص���½����
	 * @throws Exception
	 */
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.getLoginService().logoutLog();
		// SessionController.delSession(request, response);// session��¼
		// �����˳�ɾ��session��¼����Ϣ��
		request.getSession().removeAttribute("userSession");
		request.getSession().removeAttribute("resource");
		request.getSession().removeAttribute("locale");
		request.getSession().invalidate();// �ͷ�session
		return new ModelAndView("logout");
	}

	/**
	 * ˢ�²��������²˵�
	 * 
	 * @param request
	 * @param response
	 * @return ���ص������棬����Ȩ�޲˵�
	 * @throws Exception
	 */
	public ModelAndView refresh(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("login");
		if (request.getSession().getAttribute("locale") == null) {
			return new ModelAndView("timeout");
		}
		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(),
				request.getSession().getAttribute("locale").toString());
		UserSession aUserSession = (UserSession) request.getSession().getAttribute("userSession");
		try {
			String no = aUserSession.getAccount();
			User aUser = this.getUserService().qryUserById(no);
			int roleNo = aUser.getRole().getNo();
			if (this.getLoginService().getRetCode() != 0) {
				return mv.addObject("message", resource.srcStr(this.getLoginService().getRetMsg()));
			} else {
				List menuList = new ArrayList();
				if (roleNo < 90000) {
					menuList = this.getLoginService().qryMenuByRole(roleNo);
				} else {
					String[] notes = this.getSysRoleService().getRoleById(String.valueOf(roleNo)).getNote().split("\\|");
					for (String note : notes) {
						menuList.addAll(this.getLoginService().qryMenuByRole(Integer.parseInt(note)));
					}
					for (int i = 0; i < menuList.size() - 1; i++) {
						for (int j = i + 1; j < menuList.size(); j++) {
							if (((Menu) menuList.get(i)).getNo().equals(((Menu) menuList.get(j)).getNo())) {
								menuList.remove(j);
							}
						}
					}
				}

				java.util.Iterator it = menuList.iterator();
				if (!request.getSession().getAttribute("locale").toString().equals("zh_CN")) {
					while (it.hasNext()) {
						Menu menu = (Menu) it.next();
						menu.setName(resource.srcStr(menu.getNo()));
					}
				}

				if (menuList.size() == 0) {
					return mv.addObject("message", resource.srcStr("src.userNotLogin"));
				}
				aUserSession.setAccount(aUser.getNo());
				aUserSession.setName(aUser.getName());


				aUserSession.setRoleNo(aUser.getRole().getNo());
				aUserSession.setRoleName(aUser.getRole().getName());
				aUserSession.setMenuList(menuList);

				request.getSession().setAttribute("userSession", aUserSession);

				Integer[] roleId = new Integer[3];
				if (roleNo < 90000) {
					roleId[0] = roleNo;
				} else {
					String[] notes = this.getSysRoleService().getRoleById(String.valueOf(roleNo)).getNote().split("\\|");
					for (int i = 0; i < notes.length; i++) {
						roleId[i] = Integer.parseInt(notes[i]);
					}

				}
				init(request, roleId, no);
				return new ModelAndView("login_success").addObject("passtxDate", this.getLoginService().passwdFlarmDay(no));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return mv.addObject("message", resource.srcStr("src.DatabaseAbnormal"));
		}
	}

	public ModelAndView modPasswd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(),
				request.getSession().getAttribute("locale").toString());
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String no = StringUtil.parseString(request.getParameter("no"));
			String passwd = StringUtil.parseString(request.getParameter("oldpasswd"));
			String newPasswd = StringUtil.parseString(request.getParameter("newpasswd"));
			log.debug("��Ա���=[" + no + "] ������=[" + passwd + "] ������=[" + newPasswd + "]");

			User aUser = new User();
			aUser.setNo(no);
			aUser.setPasswd(passwd);
			aUser.setNewPasswd(newPasswd);

			Role aRole = new Role();
			aRole.setNo(userSession.getRoleNo());
			aUser.setRole(aRole);
			aUser.setOnline_flag(0);
			// List numList =
			// shepherdData.getAllResult("select s.paramValue from SysParam s where s.paramName = 'passwordUpdateDate' ");
			// int num = 0 ;
			// String currentDate ="";
			// if(numList!=null && numList.size()==1) {
			// if(numList.get(0)!=null)
			// {
			// num = Integer.parseInt((String)numList.get(0));
			// currentDate =
			// CalendarUtil.getFixedDate(CalendarUtil.getSysTimeYMD(), num);
			// aUser.setPasswdExpiration(currentDate);
			// }
			// }
			aUser.setPasswdExpiration(CalendarUtil.getSysTimeYMD());
			if (this.getUserService().modUserPasswd(aUser) != 0) {
				return new ModelAndView("info", "message", resource.srcStr(this.getUserService().getRetMsg()));
			}
			return new ModelAndView("login_success");
		} catch (Exception e) {
			log.error("�޸�����ʧ��",e);
			return new ModelAndView("info", "message", resource.srcStr("src.ModPswFai"));
		}
	}

	/**
	 * ��ȡһЩ�������ݴ���Map��ʽ������session��
	 * 
	 * @param request
	 * @param orgNo
	 *            ������
	 * @throws Exception
	 */
	private void init(HttpServletRequest request, Integer[] roleId, String opNo) throws Exception {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");

		TreeMap<String, String> catalogMap = new TreeMap<String, String>();
		TreeMap<String, String> vendorMap = new TreeMap<String, String>();
		TreeMap<String, String> typeMap = new TreeMap<String, String>();
		TreeMap<String, String> orgMap = new TreeMap<String, String>(); // Ĭ�ϻ�����

		HashSet<String> defButton = new HashSet<String>();
		HashSet<String> authButton = new HashSet<String>();

		List list = null;

//		list = this.getLoginService().qryDevCatalog();
//		for (Object o : list) {
//			DevCatalogTable obj = (DevCatalogTable) o;
//			catalogMap.put(String.valueOf(obj.getNo()), obj.getName());
//		}
//
//		list = this.getLoginService().qryDevVendor();
//		for (Object o : list) {
//			DevVendorTable obj = (DevVendorTable) o;
//			vendorMap.put(String.valueOf(obj.getNo()), obj.getName());
//		}
//
//		list = this.getLoginService().qryDevType();
//		for (Object o : list) {
//			DevTypeTable obj = (DevTypeTable) o;
//			typeMap.put(String.valueOf(obj.getNo()), obj.getName());
//		}

//		if (org.getOrgType().getNo() == 1) {
//			list = this.getLoginService().qryBankOrg();
//		}
//		for (Object o : list) {
//			Org aOrg = (Org) o;
//			orgMap.put(aOrg.getNo(), aOrg.getName());
//		}

		List<Button> buttonList = this.getLoginService().qryButton();
		for (Button aButton : buttonList) {
			defButton.add(aButton.getURL());
			// log.debug("���а�ť=[" + aButton.getURL() + "]");
		}

		buttonList = null;
		if (roleId[2] == null) {
			buttonList = this.getLoginService().qryButtonByRole(roleId[0]);
			for (Button aButton : buttonList) {
				authButton.add(aButton.getURL());
				// log.debug("��Ȩ��ť=[" + aButton.getURL() + "]");
			}
		} else {
			for (int roleNo : roleId) {
				buttonList = this.getLoginService().qryButtonByRole(roleNo);
				for (Button aButton : buttonList) {
					authButton.add(aButton.getURL());
					// log.debug("��Ȩ��ť=[" + aButton.getURL() + "]");
				}
			}
		}

//		request.getSession().setAttribute("_catalogMap", catalogMap);
//		request.getSession().setAttribute("_vendorMap", vendorMap);
//		request.getSession().setAttribute("_typeMap", typeMap);
//		request.getSession().setAttribute("_orgMap", orgMap);
		request.getSession().setAttribute("_Def_Button", defButton);
		request.getSession().setAttribute("_Auth_Button", authButton);
//		request.getSession().setAttribute("_orgTreeMap", this.getLoginService().qryBankOrgTree(org.getNo(), org.getOrgType().getNo()));

	}

	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	/**
	 * ��ȡ���������Լ������������������������Ǵ��ϼ����¼���˳�����map��
	 * 
	 * @param orgNo
	 * @return orgTreeMap �û�����������
	 * @throws Exception
	 */
	// private TreeMap getOrgTreeMap(String orgId,Integer orgTypeId) throws
	// Exception{
	//
	// // String hql = "from OrgTable as a where a.no=? or upper1=? or upper2=?
	// or upper3=? or upper4=? or upper5=? order by a.orgType asc";
	// String hql = "from SysOrg as a where a.no=? or upper1=? or upper2=? or
	// upper3=? or upper4=? or upper5=? order by a.sysOrgType.no asc";
	// Object[] args = new Object[]{orgNo,orgNo,orgNo,orgNo,orgNo,orgNo};
	// List orgList = null;
	//
	// int i = 1;
	// TreeMap<String,HashMap> orgTreeMap = new TreeMap<String,HashMap>();
	//
	// for(Object o : orgList){
	// SysOrg org = (SysOrg)o;
	// java.util.HashMap<String,String> tmp = new HashMap<String,String>();
	// tmp.put("no",org.getNo());
	// tmp.put("name",org.getName());
	// tmp.put("father",org.getUpper1());
	// orgTreeMap.put(String.valueOf(i), tmp);
	// i++;
	// }
	// return orgTreeMap;
	// }
	//
	//
	//
	//
	// i18n����
	//
	public ModelAndView language(HttpServletRequest request, HttpServletResponse response) {
		Locale lang;
		ModelAndView mv = new ModelAndView("login");

		String s = (String) request.getParameter("language");
		if (s.equals("tw")) {
			lang = Locale.TRADITIONAL_CHINESE;

		} else if (s.equals("en")) {
			lang = Locale.US;

		} else {

			lang = Locale.CHINA;
		}
		request.getSession().setAttribute("locale", lang);
		return mv;
	}

}
