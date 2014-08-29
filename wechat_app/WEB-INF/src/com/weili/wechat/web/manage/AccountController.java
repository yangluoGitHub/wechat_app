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
			 log.error("��ѯ�쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "��ѯ�쳣!");
		
		}
	}
	
	public ModelAndView addPage(HttpServletRequest request, HttpServletResponse response){
		try {
			ModelAndView mv=new ModelAndView("system/account_add");
			return mv;
		} catch (Exception e) {
			log.error("�������ҳ���쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "�������ҳ���쳣!");
		}
	}
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response){
		try {
			String wechatName = StringUtil.parseString(request.getParameter("wechatName"));              //���ں�����
			String wechatId = StringUtil.parseString(request.getParameter("wechatId"));                  //΢�ź�
			String id = StringUtil.parseString(request.getParameter("id"));  //΢�ź�ԭʼID
			int wechatType = Integer.parseInt(request.getParameter("wechatType"));                       //����΢�ŷ����������URL
			String url= StringUtil.parseString(request.getParameter("url"));                             //���ͣ�0������ţ�1�����ĺţ�
			String token = StringUtil.parseString(request.getParameter("token"));                        //��֤����
			String appid = StringUtil.parseString(request.getParameter("appid"));                        //
			String appsecret = StringUtil.parseString(request.getParameter("appsecret"));                //
			String creator = StringUtil.parseString(request.getParameter("creator"));;                    //������  
			
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
			    return new ModelAndView("pageinfo","message","��ӳɹ�").addObject("menuURL","account.do?action=qry");
			} catch (Exception e) {
				  return new ModelAndView("info","message","���ʧ��");
			}
		} catch (Exception e) {
			log.error("����쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "����쳣!");
		}
	}
	public ModelAndView modPage(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id"));
			PublicAccount publicAccount = (PublicAccount) this.accountService.findById(id);
			ModelAndView mv = new ModelAndView("system/account_mod").addObject("publicAccount", publicAccount);
			return mv;
		} catch (Exception e) {
			log.error("�����޸�ҳ���쳣��" + e.getMessage());
			return new ModelAndView("info", "message", "�����޸�ҳ���쳣!");
		}
	}
	public ModelAndView mod(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id"));
			String wechatName = StringUtil.parseString(request.getParameter("wechatName"));              //���ں�����
			String wechatId = StringUtil.parseString(request.getParameter("wechatId"));                  //΢�ź�
			int wechatType = Integer.parseInt(request.getParameter("wechatType"));                       //����΢�ŷ����������URL
			String url= StringUtil.parseString(request.getParameter("url"));                             //���ͣ�0������ţ�1�����ĺţ�
			String token = StringUtil.parseString(request.getParameter("token"));                        //��֤����
			String appid = StringUtil.parseString(request.getParameter("appid"));                        //
			String appsecret = StringUtil.parseString(request.getParameter("appsecret"));                //
			String creator = StringUtil.parseString(request.getParameter("creator"));                    //������  
			
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
			    return new ModelAndView("pageinfo","message","�޸ĳɹ�").addObject("menuURL","account.do?action=qry");
			} catch (Exception e) {
				  return new ModelAndView("info","message","�޸�ʧ��");
			}
		} catch (Exception e) {
			log.error("�޸��쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "�޸��쳣!");
		}
	}
	
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response){
		try {		
			String id = StringUtil.parseString(request.getParameter("id"));
			PublicAccount publicAccount = (PublicAccount) this.accountService.findById(id);
			if(publicAccount == null)
			{
				throw new DataIntegrityViolationException("�ù��ں��Ѿ�������");
			}
			if (this.getAccountService().delAccount(publicAccount) != 0) {
				return new ModelAndView("info", "message", this.getAccountService().getRetMsg());
			}
			return new ModelAndView("pageinfo_pagedecrease", "message","ɾ���ɹ�").addObject("url", "account.do?action=qry");
		
		}catch (DataIntegrityViolationException e) {
			log.info("ɾ��ʧ�ܣ�" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		} 
		catch (Exception e) {
			log.error("ɾ���쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "ɾ���쳣!");
		}
	}
	public ModelAndView pubAccountInfo(HttpServletRequest request, HttpServletResponse response){
		
		try{
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			//��session��ȡ�л�ѡ��id
			String id = userSession.getWechatId();
			
			if(id == null || "".equals(id)){
				return new ModelAndView("info", "message", "�û�û�в鿴��������ϢȨ��");
			}
			
			//���� id��ѯ΢�Ź��ں���Ϣ
			PublicAccount publicAccount = (PublicAccount) this.accountService.findById(id);
			 
			return new ModelAndView("manage/pubAccount_info")
						.addObject("publicAccount", publicAccount);
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error("��ȡ���ں���Ϣ�쳣��"+ e);
			return new ModelAndView("info", "message", "��ȡ���ں���Ϣ�쳣!");
		}
		
	}
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id"));
			PublicAccount publicAccount = (PublicAccount) this.accountService.findById(id);
			ModelAndView mv = new ModelAndView("system/account_detail").addObject("publicAccount", publicAccount);
			return mv;
		} catch (Exception e) {
			log.error("������ϸҳ���쳣��" + e.getMessage());
			return new ModelAndView("info", "message", "������ϸҳ���쳣!");
		}
	}
	
}
