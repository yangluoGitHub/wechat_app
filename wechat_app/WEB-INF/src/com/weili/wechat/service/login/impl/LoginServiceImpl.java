package com.weili.wechat.service.login.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.CalendarUtil;
import com.weili.wechat.common.EncryptUtil;
import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.SystemCons;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.dao.system.ButtonDAO;
import com.weili.wechat.dao.system.MenuDAO;
import com.weili.wechat.dao.system.UserDAO;
import com.weili.wechat.service.login.LoginService;
import com.weili.wechat.vo.User;

public class LoginServiceImpl extends RetInfo implements LoginService {
	private static Log log = LogFactory.getLog(LoginServiceImpl.class);

	private UserDAO userDAO;
	private MenuDAO menuDAO;
	private ButtonDAO buttonDAO;
	private CommonData commonData;

	public CommonData getCommonData() {
		return commonData;
	}

	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}

	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public ButtonDAO getButtonDAO() {
		return buttonDAO;
	}

	public void setButtonDAO(ButtonDAO buttonDAO) {
		this.buttonDAO = buttonDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}




	public User checkLogin(String userId, String passwd) {
		this.setRetMsg("src.loginFail");
		this.setRetCode(-1);

		if (userId == null || userId.equals("")) {
			this.setRetMsg("src.EmptyUserno");
			this.setRetCode(-1);
			return null;
		}

		if (passwd == null || passwd.equals("")) {
			this.setRetMsg("src.passwordEmpty");
			this.setRetCode(-1);
			return null;
		}

		User aUser = (User) this.getUserDAO().qryById(userId);

		if (aUser == null) {
			this.setRetMsg("src.userNotExist");
			this.setRetCode(-1);
			return null;
		} else {
			String maxCount = (String) SystemCons.sysParamMap.get("passwdmaxCount");
			log.debug("maxCount==" + maxCount);
			log.debug("aUser.getPasswdCount====" + aUser.getPasswdError());
			if (!EncryptUtil.MD5(passwd).equals(aUser.getPasswd()) && aUser.getPasswdError() < Integer.parseInt(maxCount)
					&& aUser.getStatus().intValue() != 2) {
				if (aUser.getPasswdError() + 1 == Integer.parseInt(maxCount)) {
					aUser.setStatus(2);
					aUser.setPasswdError(aUser.getPasswdError() + 1);
					this.getUserDAO().updateByHql("update OpTable o set o.status = 2 where o.no='" + aUser.getNo() + "'");
					this.setRetMsg("src.passwdMaxCountLock1|" + maxCount + "|src.passwdMaxCountLock2");
					this.setRetCode(-1);
					return null;
				}
				aUser.setPasswdError(aUser.getPasswdError() + 1);
				this.getUserDAO().updateByHql(
						"update OpTable o set o.passwdError = " + aUser.getPasswdError() + " where o.no='" + aUser.getNo() + "'");
				this.setRetMsg("src.ErrorPassword|src.passwdCount1|" + String.valueOf(Integer.parseInt(maxCount) - aUser.getPasswdError())
						+ "|src.passwdCount2");
				this.setRetCode(-1);
				return null;
			}

			if (aUser.getStatus().intValue() == 0) {
				this.setRetMsg("该用户已被停用!");
				this.setRetCode(-1);
				return null;
			}

			if (aUser.getStatus().intValue() == 2) {
				this.setRetMsg("src.userLocked");
				this.setRetCode(-1);
				return null;
			}
			
//			/**
//			 * 用户默认微信公众号
//			 */
//			if(aUser.getWechatId() == null){
//				this.setRetMsg("用户没有管理的公众号！");
//				this.setRetCode(-1);
//				return null;
//			}
//			
			
			if (aUser.getRole() == null) {
				this.setRetMsg("src.userNotExist");
				this.setRetCode(-1);
				return null;
			}

			aUser.setPasswdError(0); // 用户登录成功后将其密码输入错误次数修改为0
			aUser.setSignFlag(1); // 置签到标志为1
			this.getUserDAO().update(aUser);
		}
		this.setRetMsg("src.loginSuccess");
		this.setRetCode(0);
		return aUser;
	}

	@SuppressWarnings("rawtypes")
	public List qryMenuByRole(Integer roleId) {
		return this.getMenuDAO().qryMenuByRole(roleId);
	}

	@SuppressWarnings("rawtypes")
	public List qryButton() {
		return this.getButtonDAO().qryAll();
	}

	@SuppressWarnings("rawtypes")
	public List qryButtonByRole(Integer roleId) {
		return this.getButtonDAO().qryButtonByRole(roleId);
	}

	public int loginLog() {
		return 0;
	}

	public int logoutLog() {
		return 0;
	}

	@SuppressWarnings("rawtypes")
	public int checkLoginUpdateDate(String no) {
		List list = commonData.getAllResult("select t.loginTime,t.passwdExpiration from OpTable t where t.no = '" + no + "' ");
		List numList = commonData.getAllResult("select s.paramValue from SysParam s where s.paramName = 'passwordUpdateDate' ");
		if (list != null && list.size() == 1) {
			Object[] obj = (Object[]) list.get(0);
			String logTime = (String) obj[0];
			String passwdExpirationTime = "";
			if (numList != null && numList.size() == 1) {
				passwdExpirationTime = CalendarUtil.getFixedDate((String) obj[1], Integer.parseInt((String) numList.get(0)));
				if (logTime != null && passwdExpirationTime != null && logTime.length() > 7) {
					if (logTime.substring(0, 10).toString().compareTo(passwdExpirationTime) >= 0)
						return 1;
				}
			}
		}
		return -1;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int passwdFlarmDay(String no) {
		List list = commonData.getAllResult("select t.loginTime,t.passwdExpiration from OpTable t where t.no = '" + no + "' ");
		List numList = commonData
				.getAllResult("select s.paramName,s.paramValue from SysParam s where s.paramName = 'passwdtxDate' or s.paramName='passwordUpdateDate' ");

		HashMap map = new HashMap();
		for (Object obj : numList) {
			Object[] temp = (Object[]) obj;
			if (temp[0].equals("passwordUpdateDate"))
				map.put("passwordUpdateDate", temp[1]);
			else
				map.put("passwdtxDate", temp[1]);
		}
		int interDay = Integer.parseInt((String) map.get("passwordUpdateDate")) - Integer.parseInt((String) map.get("passwdtxDate"));// 计算密码出有效期与提醒周期的时间差

		if (list != null && list.size() == 1) {
			Object[] obj = (Object[]) list.get(0);
			String logTime = (String) obj[0];
			String passwdExpirationTime = CalendarUtil.getFixedDate((String) obj[1], Integer.parseInt((String) map.get("passwordUpdateDate")));
			;// 密码有效期

			if (logTime != null && passwdExpirationTime != null && logTime.length() > 7) {
				// 判断登陆日期是否是在提醒日期之类，如果在的话返回
				for (int i = 0; i < interDay; i++) {
					String passedDate = CalendarUtil.getFixedDate(passwdExpirationTime, i - interDay);
					if (logTime.substring(0, 10).toString().compareTo(passedDate) == 0)
						return interDay - i;
				}
			}
		}
		return -1;
	}

}
