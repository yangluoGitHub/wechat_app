package com.weili.wechat.web.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import sun.net.idn.Punycode;

import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.hibernate.PublicAccount;
import com.weili.wechat.service.manage.AccountService;

public class AccountController extends MultiActionController{
	private static Log log = LogFactory.getLog(AccountController.class);
	
	private AccountService accountService;
	public AccountService getAccountService() {
		return accountService;
	}
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	public ModelAndView qry(HttpServletRequest request, HttpServletResponse response){
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String wechatName = StringUtil.parseString(request.getParameter("wechatName"));
			String wechatId = StringUtil.parseString(request.getParameter("wechatId"));
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatName", wechatName);
			params.put("wechatId", wechatId);
			
			List accountList = this.getAccountService().qry(params); 
			ModelAndView mv = new ModelAndView("system/account_qry")
								  .addObject("accountList", accountList);
			return mv;
		} catch (Exception e) {
			 log.error("查询异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "查询异常!");
		
		}
	}
	
	public ModelAndView addPage(HttpServletRequest request, HttpServletResponse response){
		try {
			ModelAndView mv=new ModelAndView("system/account_add");
			return mv;
		} catch (Exception e) {
			log.error("进入添加页面异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "进入添加页面异常!");
		}
	}
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response){
		try {
			String wechatName = StringUtil.parseString(request.getParameter("wechatName"));              //公众号名称
			String wechatId = StringUtil.parseString(request.getParameter("wechatId"));                  //微信号
			String id = StringUtil.parseString(request.getParameter("id"));  //微信号原始ID
			int wechatType = Integer.parseInt(request.getParameter("wechatType"));                       //接受微信服务器请求的URL
			String url= StringUtil.parseString(request.getParameter("url"));                             //类型（0：服务号；1：订阅号）
			String token = StringUtil.parseString(request.getParameter("token"));                        //验证令牌
			String appid = StringUtil.parseString(request.getParameter("appid"));                        //
			String appsecret = StringUtil.parseString(request.getParameter("appsecret"));                //
			String creator = StringUtil.parseString(request.getParameter("creator"));;                    //创建者  
			
			PublicAccount publicAccount = new PublicAccount();
			publicAccount.setWechatName(wechatName);
			publicAccount.setWechatId(wechatId);
			publicAccount.setId(id);
			publicAccount.setWechatType(wechatType);
			publicAccount.setUrl(url);
			publicAccount.setToken(token);
			publicAccount.setAppid(appid);
			publicAccount.setAppsecret(appsecret);
			publicAccount.setCreator(creator);
			try {
				this.accountService.addAccount(publicAccount);
			    return new ModelAndView("pageinfo","message","添加成功").addObject("menuURL","account.do?action=qry");
			} catch (Exception e) {
				  return new ModelAndView("info","message","添加失败");
			}
		} catch (Exception e) {
			log.error("添加异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "添加异常!");
		}
	}
	public ModelAndView modPage(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id"));
			PublicAccount publicAccount = (PublicAccount) this.accountService.findById(id);
			ModelAndView mv = new ModelAndView("system/account_mod").addObject("publicAccount", publicAccount);
			return mv;
		} catch (Exception e) {
			log.error("进入修改页面异常：" + e.getMessage());
			return new ModelAndView("info", "message", "进入修改页面异常!");
		}
	}
	public ModelAndView mod(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id"));
			String wechatName = StringUtil.parseString(request.getParameter("wechatName"));              //公众号名称
			String wechatId = StringUtil.parseString(request.getParameter("wechatId"));                  //微信号
			int wechatType = Integer.parseInt(request.getParameter("wechatType"));                       //接受微信服务器请求的URL
			String url= StringUtil.parseString(request.getParameter("url"));                             //类型（0：服务号；1：订阅号）
			String token = StringUtil.parseString(request.getParameter("token"));                        //验证令牌
			String appid = StringUtil.parseString(request.getParameter("appid"));                        //
			String appsecret = StringUtil.parseString(request.getParameter("appsecret"));                //
			String creator = StringUtil.parseString(request.getParameter("creator"));                    //创建者  
			
			PublicAccount publicAccount = new PublicAccount();
			publicAccount.setId(id);
			publicAccount.setWechatName(wechatName);
			publicAccount.setWechatId(wechatId);
			publicAccount.setWechatType(wechatType);
			publicAccount.setUrl(url);
			publicAccount.setToken(token);
			publicAccount.setAppid(appid);
			publicAccount.setAppsecret(appsecret);
			publicAccount.setCreator(creator);
			
			try {
				this.accountService.modAccount(publicAccount);
			    return new ModelAndView("pageinfo","message","修改成功").addObject("menuURL","account.do?action=qry");
			} catch (Exception e) {
				  return new ModelAndView("info","message","修改失败");
			}
		} catch (Exception e) {
			log.error("修改异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "修改异常!");
		}
	}
	
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response){
		try {		
			String id = StringUtil.parseString(request.getParameter("id"));
			PublicAccount publicAccount = (PublicAccount) this.accountService.findById(id);
			if(publicAccount == null)
			{
				throw new DataIntegrityViolationException("该公众号已经不存在");
			}
			if (this.getAccountService().delAccount(publicAccount) != 0) {
				return new ModelAndView("info", "message", this.getAccountService().getRetMsg());
			}
			return new ModelAndView("pageinfo_pagedecrease", "message","删除成功").addObject("url", "account.do?action=qry");
		
		}catch (DataIntegrityViolationException e) {
			log.info("删除失败：" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		} 
		catch (Exception e) {
			log.error("删除异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "删除异常!");
		}
	}
	public ModelAndView pubAccountInfo(HttpServletRequest request, HttpServletResponse response){
		
		try{
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			//从session获取切换选中id
			String id = userSession.getWechatId();
			
			if(id == null || "".equals(id)){
				return new ModelAndView("info", "message", "用户没有查看公共号信息权限");
			}
			
			//根据 id查询微信公众号信息
			PublicAccount publicAccount = (PublicAccount) this.accountService.findById(id);
			 
			return new ModelAndView("manage/pubAccount_info")
						.addObject("publicAccount", publicAccount);
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error("获取公众号信息异常："+ e);
			return new ModelAndView("info", "message", "获取公众号信息异常!");
		}
		
	}
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id"));
			PublicAccount publicAccount = (PublicAccount) this.accountService.findById(id);
			ModelAndView mv = new ModelAndView("system/account_detail").addObject("publicAccount", publicAccount);
			return mv;
		} catch (Exception e) {
			log.error("进入详细页面异常：" + e.getMessage());
			return new ModelAndView("info", "message", "进入详细页面异常!");
		}
	}
	
}
