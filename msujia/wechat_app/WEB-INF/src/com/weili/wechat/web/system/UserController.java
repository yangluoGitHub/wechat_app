package com.weili.wechat.web.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.weili.wechat.common.CalendarUtil;
import com.weili.wechat.common.GetResource;
import com.weili.wechat.common.OperateAspect;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.hibernate.SysRole;
import com.weili.wechat.service.system.SysParamService;
import com.weili.wechat.service.system.SysRoleService;
import com.weili.wechat.service.system.UserService;
import com.weili.wechat.vo.Role;
import com.weili.wechat.vo.User;

/**
 * 
 * �û�Controller
 * 
 */
public class UserController extends MultiActionController {
	
	private static Log log = LogFactory.getLog(UserController.class);

	private UserService userService;
	private SysRoleService sysRoleService;
	private SysParamService sysParamService;


	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public SysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	/**
	 * ��ѯ����Ա ����ҳ��ѯ(ʹ�û����ؼ�)
	 */
	public ModelAndView qry(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String opNo = StringUtil.parseString(request.getParameter("opNo"));
			String roleNo = StringUtil.parseString(request.getParameter("roleNo_Query"));
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("opNo", opNo);
			paramMap.put("roleNo", roleNo);
			List userList = this.userService.qryUser(paramMap);
			List roleList = this.userService.qryRole();

			return new ModelAndView("system/user_qry")
					.addObject("OpAccount", userSession.getAccount())
					.addObject("OpName", userSession.getName())
					.addObject("userList", userList)
					.addObject("roleList", roleList)
					.addObject("opNo", opNo)
					.addObject("roleNo", roleNo);
		} catch (Exception e) {
			log.error("��ѯ�û��쳣��", e);
			return new ModelAndView("info", "message", "��ѯ�û��쳣��"+e.getMessage());
		}
	}

	/**
	 * ��Ӳ���Ա
	 */
	public ModelAndView add(HttpServletRequest request,	HttpServletResponse response) {
		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {
			
			String opNoCondition = StringUtil.parseString(request.getParameter("opNoCondition"));
			String roleNoCondition = StringUtil.parseString(request.getParameter("roleNoCondition"));
			String no = StringUtil.parseString(request.getParameter("no"));
			String name = StringUtil.parseString(request.getParameter("name"));
			String telephone = StringUtil.parseString(request.getParameter("telephone"));
			String mobile = StringUtil.parseString(request.getParameter("mobile"));
			String email = StringUtil.parseString(request.getParameter("email"));
			int status = 1;//����Ա״̬Ĭ��Ϊ ����
			int roleNo = Integer.parseInt(request.getParameter("roleNo"));
			int roleNo1 = Integer.parseInt((!StringUtil.parseString(request.getParameter("roleNo1")).equals("")) ? request.getParameter("roleNo1") : "0");
			int roleNo2 = Integer.parseInt((!StringUtil.parseString(request.getParameter("roleNo2")).equals("")) ? request.getParameter("roleNo2") : "0");
			
			String[] checkPA = request.getParameterValues("checkPA");

			User aUser = new User();
			aUser.setNo(no);
			aUser.setName(name);
			aUser.setMobile(mobile);
			aUser.setTelephone(telephone);
			aUser.setEmail(email);
			aUser.setStatus(status);
			aUser.setPasswdExpiration(CalendarUtil.getFixedDateYYYY_MM_DD(CalendarUtil.getSysTimeYMD(), 365));//Ĭ��һ����Ч��
			aUser.setPasswdError(0);
			aUser.setOnline_flag(-1);
			aUser.setSignFlag(0);
			Role aRole = new Role();
			// ---------------------ʵ���û����ɫ��������ص��½�ɫ
			int newRoleNo;
			if ((roleNo1 != 0) || (roleNo2 != 0)) {
				SysRole sysRole = new SysRole();
				int maxNo = this.getSysRoleService().getMaxNo();
				if (maxNo == 0) {
					newRoleNo = 90000;
				}
				else {
					newRoleNo = maxNo + 1;
				}
				String roleName = "";
				roleName += this.getSysRoleService().getRoleById(String.valueOf(roleNo)).getName();
				if (roleNo1 != 0) {
					roleName += "-" + this.getSysRoleService().getRoleById(String.valueOf(roleNo1)).getName();
				}
				if (roleNo2 != 0) {
					roleName += "-" + this.getSysRoleService().getRoleById(String.valueOf(roleNo2)).getName();
				}
				sysRole.setNo(newRoleNo);
				sysRole.setName(roleName);
				sysRole.setCatalog(9);
				sysRole.setNote(String.valueOf(roleNo) + "|" + String.valueOf(roleNo1) + "|" + String.valueOf(roleNo2));
				this.getSysRoleService().createOrUpdate(sysRole);
				aRole.setNo(newRoleNo);
			} else {
				aRole.setNo(roleNo);
			}
			aUser.setRole(aRole);
			int retVal = -1;
			if ((retVal = this.getUserService().addUser(aUser)) != 0)
			{
				if (retVal == OperateAspect.AUDIT_FLAG)
					return new ModelAndView("info", "message",
							resource.srcStr(resource.srcStr("Login.wait_preview")));
				else
					return new ModelAndView("info", "message",
							resource.srcStr(this.getUserService().getRetMsg()));
			}
			log.info("��ӳɹ�����ӵ���Ա����ǣ�" + no);
			if (roleNo == 100) {
				return new ModelAndView("pageinfo", "message", resource.srcStr("src.AddBankCrewSuc"));
			} else if (roleNo == 200) {
				return new ModelAndView("pageinfo", "message", resource.srcStr("src.AddMainSuccess"));
			}
			else {
				return new ModelAndView("pageinfo_pagedecrease", "message","����û��ɹ�,�״ε�½�ĳ�ʼ����Ϊ��abcd1234")
					.addObject("url", "user.do?action=qry&opNo=" + opNoCondition
								+ "&roleNo_Query=" + roleNoCondition);
			}
		} catch (Exception e) {
			log.error("����û��쳣��",  e);
			return new ModelAndView("info", "message", "����û��쳣��"+e.getMessage());
		}
	}

	/**
	 * �޸Ĳ���Ա
	 */
	public ModelAndView mod(HttpServletRequest request,HttpServletResponse response) {

		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {
			String opNoCondition = StringUtil.parseString(request.getParameter("opNoCondition"));
			String roleNoCondition = StringUtil.parseString(request.getParameter("roleNoCondition"));
			String wechatId = StringUtil.parseString(request.getParameter("wechatId"));
			ModelAndView mv = new ModelAndView("pageinfo");
			String no = StringUtil.parseString(request.getParameter("no"));
			String name = StringUtil.parseString(request.getParameter("name"));
			String telephone = StringUtil.parseString(request.getParameter("telephone"));
			String mobile = StringUtil.parseString(request.getParameter("mobile"));
			String email = StringUtil.parseString(request.getParameter("email"));
			int status = Integer.parseInt(request.getParameter("status"));
			
			String tmp_roleNo = request.getParameter("roleNo");

			String oldname = StringUtil.parseString(request.getParameter("oldname"));
			int oldRoleNo = Integer.parseInt(request.getParameter("oldRoleNo"));

			int roleNo = 0;
			String roleName = "";
			
			if (!tmp_roleNo.equals("")) {
				roleNo = Integer.parseInt(tmp_roleNo);
			}
			
			// -------------------------------------------------------
			User tempUser = this.getUserService().qryUserById(no);
			if (tempUser == null)
				if (roleNo == 100) {
					return new ModelAndView("info", "message", "�����йܻ�Ա�Ѿ�������");
				} else if (roleNo == 200) {
					return new ModelAndView("info", "message", "���豸ά��Ա�Ѿ�������");
				} else {
					return new ModelAndView("info", "message", "���û��Ѿ�������");
				}
			User aUser = new User();
			aUser.setNo(no);
			aUser.setName(name);
			aUser.setMobile(mobile);
			aUser.setTelephone(telephone);
			aUser.setEmail(email);
			aUser.setStatus(status);
			aUser.setOnline_flag(tempUser.getOnline_flag());
			aUser.setLoginIp(tempUser.getLoginIp());
			aUser.setLoginTime(tempUser.getLoginTime());
			aUser.setPasswdExpiration(tempUser.getPasswdExpiration());

			Role aRole = new Role();
			aRole.setNo(roleNo);
			aUser.setRole(aRole);
			int retVal = -1;
			if ((retVal = this.getUserService().modUser(aUser)) != 0) {
				if (retVal == OperateAspect.AUDIT_FLAG)
					return new ModelAndView("info", "message", resource.srcStr(resource.srcStr("Login.wait_preview")));
				else
					return new ModelAndView("info", "message", resource.srcStr(this.getUserService().getRetMsg()));
			}
			// ���޸ĵ��ǵ�ǰ�û������session
			UserSession aUserSession = (UserSession) request.getSession()
					.getAttribute("userSession");
			if (aUserSession.getAccount().equals(no))
			{
				// �޸��û���
				if (!name.equals(oldname))
				{
					aUserSession.setName(name);
					request.setAttribute("usernamechanged", "true");
				}
				// �޸Ľ�ɫ
				if (roleNo != oldRoleNo)
				{
					aUserSession.setRoleNo(roleNo);
					aUserSession.setRoleName(roleName);
				}
			}
			log.info("�޸ĳɹ����޸ĵ���Ա����ǣ�" + no);
			mv.addObject("message", resource.srcStr("src.ModUserInfoSuc"));
			return new ModelAndView("pageinfo_pagedecrease", "message",
					"�޸��û���Ϣ�ɹ�").addObject("url", "user.do?action=qry&opNo="
					+ opNoCondition + "&roleNo_Query=" + roleNoCondition);

		} catch (Exception e) {
			log.error("�޸��û���Ϣʧ�ܣ�",e);
			return new ModelAndView("info", "message", "�޸��û���Ϣʧ�ܣ�"+e.getMessage());
		}
	}

	/**
	 * �޸�����
	 */
	public ModelAndView modPasswd(HttpServletRequest request,HttpServletResponse response) {

		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String no = StringUtil.parseString(request.getParameter("no"));
			String passwd = StringUtil.parseString(request.getParameter("oldpasswd"));
			String newPasswd = StringUtil.parseString(request.getParameter("newpasswd"));
			log.debug("��Ա���=[" + no + "] ������=[" + passwd + "] ������=["+ newPasswd + "]");

			User aUser = new User();
			aUser.setNo(no);
			aUser.setPasswd(passwd);
			aUser.setNewPasswd(newPasswd);

			Role aRole = new Role();
			aRole.setNo(userSession.getRoleNo());
			aUser.setRole(aRole);

			// List numList = sysParamService.qrySysParamByParamName("passwordUpdateDate");
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
			log.debug("+++++++++++++++++++++++++++++++++++++++++++");

			log.debug("������=[" + this.getUserService().getRetCode() + "] ������Ϣ=["
					+ resource.srcStr(this.getUserService().getRetMsg()) + "]");
			if (this.getUserService().modUserPasswd(aUser) != 0)
			{
				return new ModelAndView("info", "message", resource.srcStr(this
						.getUserService().getRetMsg()));
			}
			return new ModelAndView("info", "message",
					resource.srcStr("src.changePswSuc"));

		} catch (Exception e) {
			log.error("�޸������쳣��", e);
			return new ModelAndView("info", "message", "�޸������쳣"+e.getMessage());
		}
	}

	/**
	 * ɾ������Ա
	 */
	public ModelAndView del(HttpServletRequest request,HttpServletResponse response) {
		Resource resource = (Resource) GetResource.getOneResource(request
				.getSession().getServletContext(), request.getSession()
				.getAttribute("locale").toString());
		try {
			String opNoCondition = StringUtil.parseString(request
					.getParameter("opNoCondition"));
			String roleNoCondition = StringUtil.parseString(request
					.getParameter("roleNoCondition"));

			String no = StringUtil.parseString(request.getParameter("no"));
			User aUser = this.getUserService().qryUserById(no);
			if (aUser == null)
			{
				return new ModelAndView("info", "message", "���û��Ѿ�������!");
			}

			int retVal = -1;
			if ((retVal = this.getUserService().delUser(aUser)) != 0)
			{
				if (retVal == OperateAspect.AUDIT_FLAG)
					return new ModelAndView("info", "message",
							resource.srcStr(resource
									.srcStr("Login.wait_preview")));
				else
					return new ModelAndView("info", "message",
							resource.srcStr(this.getUserService().getRetMsg()));
			}

			log.info("ɾ������Ա�ʺ��ǣ�" + no);

			if (Integer.valueOf(request.getParameter("roleNo")) == 100)
			{
				return new ModelAndView("pageinfo_pagedecrease", "message",
						resource.srcStr("src.delManagerSuc")).addObject("url",
						"user.do?action=qry&roleNo_Query=100");
			}
			else if (Integer.valueOf(request.getParameter("roleNo")) == 200)
			{
				return new ModelAndView("pageinfo_pagedecrease", "message",
						resource.srcStr("src.delDevManagerSuc")).addObject(
						"url", "user.do?action=qry&roleNo_Query=200");
			}
			else
			{
				return new ModelAndView("pageinfo_pagedecrease", "message",
						resource.srcStr("src.delUserInfoSuc")).addObject("url",
						"user.do?action=qry&opNo=" + opNoCondition + "&roleNo_Query=" + roleNoCondition);
			}

		} catch (Exception e) {
			log.error("ɾ���û��쳣��", e);
			return new ModelAndView("info", "message", "ɾ���û��쳣��"+e.getMessage());
		}
	}

	/**
	 * �������ҳ��
	 */
	public ModelAndView addPage(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String opNoCondition = StringUtil.parseString(request
					.getParameter("opNoCondition"));
			String roleNoCondition = StringUtil.parseString(request
					.getParameter("roleNoCondition"));
			List roleList = this.getUserService().qryRole();
			return new ModelAndView("system/user_add")
					.addObject("roleList", roleList)
					.addObject("opNoCondition", opNoCondition)
					.addObject("roleNoCondition", roleNoCondition);
		} catch (Exception e) {
			log.error("��������û�ҳ���쳣��", e);
			return new ModelAndView("info", "message", "��������û�ҳ���쳣��"+e.getMessage());
		}
	}

	/**
	 * �����޸�ҳ��
	 */
	public ModelAndView modPage(HttpServletRequest request,HttpServletResponse response) {
		Resource resource = (Resource) request.getSession().getAttribute("resource");
		try {
			String opNoCondition = StringUtil.parseString(request.getParameter("opNoCondition"));
			String roleNoCondition = StringUtil.parseString(request.getParameter("roleNoCondition"));

			String no = StringUtil.parseString(request.getParameter("no"));

			User user = this.getUserService().qryUserById(no);
			if (user == null) {
				return new ModelAndView("info", "message", "���û��Ѿ�������!");
			}
			
			List roleList = this.getUserService().qryRole();

			log.info(user.getRole().getNo() + "----------------------------");
			SysRole sysRole = this.getSysRoleService().getRoleById(
					String.valueOf(user.getRole().getNo()));
			if (sysRole == null)
			{
				return new ModelAndView("info", "message",
						resource.srcStr("src.ModRole"));
			}
			return new ModelAndView("system/user_mod")
					.addObject("roleList", roleList)
					.addObject("user", user)
					.addObject("roleNo", sysRole.getNo())
					.addObject("opNoCondition", opNoCondition)
					.addObject("roleNoCondition", roleNoCondition);
		}
		catch (Exception e)
		{
			log.error("�����޸��û�ҳ���쳣��", e);
			return new ModelAndView("info", "message", "�����޸��û�ҳ���쳣��"+e.getMessage());
		}
	}

	/**
	 * ������ϸ��Ϣ��ʾҳ��
	 */
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response) {
		try {
			String no = StringUtil.parseString(request.getParameter("no"));
			String roleNo = StringUtil.parseString(request.getParameter("roleNo"));
			User user = this.getUserService().qryUserById(no);
			return new ModelAndView("system/user_detail")
					.addObject("user", user)
					.addObject("roleNo_Query", roleNo);
		} catch (Exception e) {
			log.info("�����û���ϸ��Ϣҳ���쳣��", e);
			return new ModelAndView("info", "message", "�����û���ϸ��Ϣҳ���쳣: "+e.getMessage());
		}
	}

	/**
	 * �����޸�����ҳ��
	 */
	public ModelAndView modPasswdPage(HttpServletRequest request, HttpServletResponse response) {
		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {
			return new ModelAndView("system/user_passwd_mod");
		} catch (Exception e) {
			log.error("�����޸�����ҳ���쳣��", e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}

	/**
	 * ������������ҳ��
	 */
	public ModelAndView resetPasswd(HttpServletRequest request,HttpServletResponse response) {
		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {
			String opNoCondition = StringUtil.parseString(request.getParameter("opNoCondition"));
			String orgNoCondition = StringUtil.parseString(request.getParameter("orgNoCondition"));
			String roleNoCondition = StringUtil.parseString(request.getParameter("roleNoCondition"));

			String no = StringUtil.parseString(request.getParameter("no"));
			User aUser = new User();
			aUser.setNo(no);

			Role aRole = new Role();
			aRole.setNo(Integer.valueOf(request.getParameter("roleNo")));
			aUser.setRole(aRole);

			log.debug("������=[" + this.getUserService().getRetCode() + "] ������Ϣ=["+ resource.srcStr(this.getUserService().getRetMsg()) + "]");
			
//			List numList = sysParamService.qrySysParamByParamName("passwordUpdateDate");
//			int num = 0 ;
//			String currentDate = "";
//			if (numList != null && numList.size() == 1) {
//				if(numList.get(0)!=null) {
//					num = Integer.parseInt((String)numList.get(0));
//					currentDate = CalendarUtil.getFixedDate(CalendarUtil.getSysTimeYMD(), num);
//					aUser.setPasswdExpiration(currentDate);
//				}
//			}
			
			aUser.setPasswdExpiration(CalendarUtil.getSysTimeYMD());
			int retVal = -1;
			if ((retVal = this.getUserService().resetUserPasswd(aUser)) != 0) {
				if (retVal == OperateAspect.AUDIT_FLAG)
					return new ModelAndView("info", "message", resource.srcStr(resource.srcStr("Login.wait_preview")));
				else
					return new ModelAndView("info", "message", resource.srcStr(this.getUserService().getRetMsg()));
			}
			return new ModelAndView("pageinfo_pagedecrease", "message", "��������ɹ�,��ʼ����Ϊ:abcd1234")
				.addObject("url", "user.do?action=qry&opNo=" + opNoCondition
							+ "&orgNo_Query=" + orgNoCondition + "&roleNo_Query=" + roleNoCondition);
		} catch (Exception e) {
			log.error("������������ҳ���쳣��", e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}

	/**
	 * �����û�
	 */

	public ModelAndView unlock(HttpServletRequest request,HttpServletResponse response) {
		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {
			String opNoCondition = StringUtil.parseString(request.getParameter("opNoCondition"));
			String orgNoCondition = StringUtil.parseString(request.getParameter("orgNoCondition"));
			String roleNoCondition = StringUtil.parseString(request.getParameter("roleNoCondition"));
			String no = StringUtil.parseString(request.getParameter("no"));
			// String name =
			// StringUtil.parseString(request.getParameter("name")) ;
			// String roleNo =
			// StringUtil.parseString(request.getParameter("roleNo")) ;
			// String rogNo =
			// StringUtil.parseString(request.getParameter("orgNo")) ;
			// log.debug("��Ա���=["+no+"]"+"��Ա����=["+name+"]"+"��ɫ���=["+roleNo+"] �������=["+rogNo+"]");
			//
			// Role role = new Role() ;
			// role.setNo(Integer.parseInt(roleNo)) ;
			//
			// Org srvOrgVO = new Org() ;
			// org.setNo(rogNo) ;

			User aUser = this.getUserService().qryUserById(no);
			aUser.setNo(no);
			// aUser.setRole(role) ;
			// aUser.setOrg(org) ;
			// aUser.setName(name) ;

			aUser.setStatus(1);
			aUser.setPasswdError(0);
			log.debug("+++++++++++++++++++++++++++++++++++++++++++");

			log.debug("������=[" + this.getUserService().getRetCode() + "] ������Ϣ=["
					+ resource.srcStr(this.getUserService().getRetMsg()) + "]");
			if (this.getUserService().modUser(aUser) != 0) {
				return new ModelAndView("pageinfo", "message", resource.srcStr(this.getUserService().getRetMsg()));
			}
			return new ModelAndView("pageinfo_pagedecrease", "message", "�û������ɹ�")
				.addObject("url", "user.do?action=qry&opNo=" + opNoCondition + "&orgNo_Query=" 
						+ orgNoCondition + "&roleNo_Query=" + roleNoCondition);
		} catch (Exception e){
			log.error("�����û��쳣��", e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}

//	/**
//	 * �л����ں�
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	public ModelAndView switchPubAccount(HttpServletRequest request,HttpServletResponse response) {
//		try{
//			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
//			String wechatId = StringUtil.parseString(request.getParameter("userpa"));
//			userSession.setWechatId(wechatId);
//			return new ModelAndView("pageinfo_pagedecrease", "message", "���ں��л��ɹ�")
//			.addObject("url", "account.do?action=pubAccountInfo");
//		}catch (Exception e){
//			log.error("�л����ں��쳣��", e);
//			return new ModelAndView("info", "message", e.getMessage());
//		}
//	}
}