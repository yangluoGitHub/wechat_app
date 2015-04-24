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
 * 用户Controller
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
	 * 查询操作员 不分页查询(使用机构控件)
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
			log.error("查询用户异常：", e);
			return new ModelAndView("info", "message", "查询用户异常："+e.getMessage());
		}
	}

	/**
	 * 添加操作员
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
			int status = 1;//操作员状态默认为 启用
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
			aUser.setPasswdExpiration(CalendarUtil.getFixedDateYYYY_MM_DD(CalendarUtil.getSysTimeYMD(), 365));//默认一年有效期
			aUser.setPasswdError(0);
			aUser.setOnline_flag(-1);
			aUser.setSignFlag(0);
			Role aRole = new Role();
			// ---------------------实现用户多角色，添加隐藏的新角色
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
			log.info("添加成功，添加的人员编号是：" + no);
			if (roleNo == 100) {
				return new ModelAndView("pageinfo", "message", resource.srcStr("src.AddBankCrewSuc"));
			} else if (roleNo == 200) {
				return new ModelAndView("pageinfo", "message", resource.srcStr("src.AddMainSuccess"));
			}
			else {
				return new ModelAndView("pageinfo_pagedecrease", "message","添加用户成功,首次登陆的初始密码为：abcd1234")
					.addObject("url", "user.do?action=qry&opNo=" + opNoCondition
								+ "&roleNo_Query=" + roleNoCondition);
			}
		} catch (Exception e) {
			log.error("添加用户异常：",  e);
			return new ModelAndView("info", "message", "添加用户异常："+e.getMessage());
		}
	}

	/**
	 * 修改操作员
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
					return new ModelAndView("info", "message", "该银行管机员已经不存在");
				} else if (roleNo == 200) {
					return new ModelAndView("info", "message", "该设备维护员已经不存在");
				} else {
					return new ModelAndView("info", "message", "该用户已经不存在");
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
			// 若修改的是当前用户则更新session
			UserSession aUserSession = (UserSession) request.getSession()
					.getAttribute("userSession");
			if (aUserSession.getAccount().equals(no))
			{
				// 修改用户名
				if (!name.equals(oldname))
				{
					aUserSession.setName(name);
					request.setAttribute("usernamechanged", "true");
				}
				// 修改角色
				if (roleNo != oldRoleNo)
				{
					aUserSession.setRoleNo(roleNo);
					aUserSession.setRoleName(roleName);
				}
			}
			log.info("修改成功，修改的人员编号是：" + no);
			mv.addObject("message", resource.srcStr("src.ModUserInfoSuc"));
			return new ModelAndView("pageinfo_pagedecrease", "message",
					"修改用户信息成功").addObject("url", "user.do?action=qry&opNo="
					+ opNoCondition + "&roleNo_Query=" + roleNoCondition);

		} catch (Exception e) {
			log.error("修改用户信息失败：",e);
			return new ModelAndView("info", "message", "修改用户信息失败："+e.getMessage());
		}
	}

	/**
	 * 修改密码
	 */
	public ModelAndView modPasswd(HttpServletRequest request,HttpServletResponse response) {

		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String no = StringUtil.parseString(request.getParameter("no"));
			String passwd = StringUtil.parseString(request.getParameter("oldpasswd"));
			String newPasswd = StringUtil.parseString(request.getParameter("newpasswd"));
			log.debug("人员编号=[" + no + "] 旧密码=[" + passwd + "] 新密码=["+ newPasswd + "]");

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

			log.debug("返回码=[" + this.getUserService().getRetCode() + "] 返回信息=["
					+ resource.srcStr(this.getUserService().getRetMsg()) + "]");
			if (this.getUserService().modUserPasswd(aUser) != 0)
			{
				return new ModelAndView("info", "message", resource.srcStr(this
						.getUserService().getRetMsg()));
			}
			return new ModelAndView("info", "message",
					resource.srcStr("src.changePswSuc"));

		} catch (Exception e) {
			log.error("修改密码异常：", e);
			return new ModelAndView("info", "message", "修改密码异常"+e.getMessage());
		}
	}

	/**
	 * 删除操作员
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
				return new ModelAndView("info", "message", "该用户已经不存在!");
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

			log.info("删除操作员帐号是：" + no);

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
			log.error("删除用户异常：", e);
			return new ModelAndView("info", "message", "删除用户异常："+e.getMessage());
		}
	}

	/**
	 * 进入添加页面
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
			log.error("进入添加用户页面异常：", e);
			return new ModelAndView("info", "message", "进入添加用户页面异常："+e.getMessage());
		}
	}

	/**
	 * 进入修改页面
	 */
	public ModelAndView modPage(HttpServletRequest request,HttpServletResponse response) {
		Resource resource = (Resource) request.getSession().getAttribute("resource");
		try {
			String opNoCondition = StringUtil.parseString(request.getParameter("opNoCondition"));
			String roleNoCondition = StringUtil.parseString(request.getParameter("roleNoCondition"));

			String no = StringUtil.parseString(request.getParameter("no"));

			User user = this.getUserService().qryUserById(no);
			if (user == null) {
				return new ModelAndView("info", "message", "该用户已经不存在!");
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
			log.error("进入修改用户页面异常：", e);
			return new ModelAndView("info", "message", "进入修改用户页面异常："+e.getMessage());
		}
	}

	/**
	 * 进入详细信息显示页面
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
			log.info("进入用户详细信息页面异常：", e);
			return new ModelAndView("info", "message", "进入用户详细信息页面异常: "+e.getMessage());
		}
	}

	/**
	 * 进入修改密码页面
	 */
	public ModelAndView modPasswdPage(HttpServletRequest request, HttpServletResponse response) {
		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {
			return new ModelAndView("system/user_passwd_mod");
		} catch (Exception e) {
			log.error("进入修改密码页面异常：", e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}

	/**
	 * 进入重置密码页面
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

			log.debug("返回码=[" + this.getUserService().getRetCode() + "] 返回信息=["+ resource.srcStr(this.getUserService().getRetMsg()) + "]");
			
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
			return new ModelAndView("pageinfo_pagedecrease", "message", "重置密码成功,初始密码为:abcd1234")
				.addObject("url", "user.do?action=qry&opNo=" + opNoCondition
							+ "&orgNo_Query=" + orgNoCondition + "&roleNo_Query=" + roleNoCondition);
		} catch (Exception e) {
			log.error("进入重置密码页面异常：", e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}

	/**
	 * 解锁用户
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
			// log.debug("人员编号=["+no+"]"+"人员姓名=["+name+"]"+"角色编号=["+roleNo+"] 机构编号=["+rogNo+"]");
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

			log.debug("返回码=[" + this.getUserService().getRetCode() + "] 返回信息=["
					+ resource.srcStr(this.getUserService().getRetMsg()) + "]");
			if (this.getUserService().modUser(aUser) != 0) {
				return new ModelAndView("pageinfo", "message", resource.srcStr(this.getUserService().getRetMsg()));
			}
			return new ModelAndView("pageinfo_pagedecrease", "message", "用户解锁成功")
				.addObject("url", "user.do?action=qry&opNo=" + opNoCondition + "&orgNo_Query=" 
						+ orgNoCondition + "&roleNo_Query=" + roleNoCondition);
		} catch (Exception e){
			log.error("解锁用户异常：", e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}

//	/**
//	 * 切换公众号
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	public ModelAndView switchPubAccount(HttpServletRequest request,HttpServletResponse response) {
//		try{
//			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
//			String wechatId = StringUtil.parseString(request.getParameter("userpa"));
//			userSession.setWechatId(wechatId);
//			return new ModelAndView("pageinfo_pagedecrease", "message", "公众号切换成功")
//			.addObject("url", "account.do?action=pubAccountInfo");
//		}catch (Exception e){
//			log.error("切换公众号异常：", e);
//			return new ModelAndView("info", "message", e.getMessage());
//		}
//	}
}