package com.weili.wechat.web.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.hibernate.WechatFunction;
import com.weili.wechat.service.manage.AccountService;
import com.weili.wechat.service.manage.MenuFunctionService;
import com.weili.wechat.service.system.UserService;
import com.weili.wechat.vo.PubaccountFunctionVO;
import com.weili.wechat.vo.PublicAccountVO;
import com.weili.wechat.vo.WechatFunctionVO;
import com.weili.wechat.vo.WechatFunctionVO2;

public class MenuFunctionController extends MultiActionController{
	private static Log log = LogFactory.getLog(MenuFunctionService.class);
	
	private MenuFunctionService menuFunctionService;
	private AccountService accountService;
	private UserService userService;
	
	public MenuFunctionService getMenuFunctionService() {
		return menuFunctionService;
	}
	public void setMenuFunctionService(MenuFunctionService menuFunctionService) {
		this.menuFunctionService = menuFunctionService;
	}
	public AccountService getAccountService() {
		return accountService;
	}
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	
	public ModelAndView qry(HttpServletRequest request, HttpServletResponse response){
		
		
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		try {
			
			String wechatId = StringUtil.parseString(request.getParameter("wechatId"));
			String wechatletName = StringUtil.parseString(request.getParameter("wechatletName"));
			
			// ��ѯ�������ں�Ϊ�� ����Ĭ�Ϲ��ں�
			if("".equals(wechatId)){
				wechatId = userSession.getWechatId();//��ȡsession��ѡ�е�wechatID
			}
			// ��ȡ��Ȩ�޹��ں��б�
			List<PublicAccountVO> pubAccountList = (List<PublicAccountVO>) this.accountService.getAuthPubAccount(userSession.getAccount());
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatId", wechatId);
			params.put("wechatletName", wechatletName);
			
			List functionList = this.getMenuFunctionService().qry(params);
			ModelAndView mv = new ModelAndView("manage/menuFunction_qry")
										.addObject("functionList",functionList)
										.addObject("wechatId", wechatId)
										.addObject("pubAccountList", pubAccountList)
										.addObject("wechatletName", wechatletName);
			return mv;
		} catch (Exception e) {
			 log.error("��ѯ�쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "��ѯ�쳣!");
		}
	}
	public ModelAndView addPage(HttpServletRequest request, HttpServletResponse response){
		try {
			ModelAndView mv = new ModelAndView("manage/menuFunction_add");
			List paList = this.getUserService().qryAllPubAccount();
			mv.addObject("paList", paList);
			return mv;
		} catch (Exception e) {
			log.error("����쳣��"+ e.getMessage());
			return new ModelAndView("info", "message", "����쳣!");
		}
	}
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response){
		try {
			String wechatletName = StringUtil.parseString(request.getParameter("wechatletName"));              //���ܵ�����
			String description = StringUtil.parseString(request.getParameter("description"));                      //����
			String classname = StringUtil.parseString(request.getParameter("classname"));                              //·��
			
			String[] checkPA = request.getParameterValues("checkPA");
			
			String[] paIDs = null;
			if (checkPA != null) {
				paIDs = new String[checkPA.length];
				for (int i = 0; i < checkPA.length; i++) {
					String paid = checkPA[i].split("\\|")[0];
					paIDs[i] = paid;
				}
			}
			
			WechatFunctionVO vo = new WechatFunctionVO();
			vo.setClassname(classname);
			vo.setDescription(description);
			vo.setWechatletName(wechatletName);
			
			String id = UUID.randomUUID().toString();
			vo.setId(id);
			
			vo.setPaIDs(paIDs);
			
            
			int retCode = this.menuFunctionService.addFunction(vo);
			if(retCode != 0){
				return new ModelAndView("info", "message", menuFunctionService.getRetMsg());
			}
			/**
			 * sync ҵ������Ϣ
			 */
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatId", id);
			List<WechatFunction> list = this.getMenuFunctionService().qry(params);
			
			//����wechat_web ��������list
			
			//end
			
			return new ModelAndView("pageinfo","message","��ӳɹ�")
					.addObject("menuURL","menuFunction.do?action=qry");
			
			
		} catch (Exception e) {
			log.error("����쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "����쳣!");
		}
	}
	public ModelAndView modPage(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id"));
			WechatFunction wechatFunction = (WechatFunction) this.menuFunctionService.findById(id);
			
			if (wechatFunction == null) {
				return new ModelAndView("info", "message", "��ҵ�����Ѿ�������!");
			}
			
			List paList = this.getUserService().qryAllPubAccount();
			List pubFunIdList = this.getMenuFunctionService().qryPubAccountIdByFunId(id);			
			ModelAndView mv = new ModelAndView("manage/menuFunction_mod")
											.addObject("wechatFunction",wechatFunction)
											.addObject("paList", paList)
											.addObject("pubFunIdList", pubFunIdList);
			return mv;
		} catch (Exception e) {
			log.error("����쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "����쳣!");
		}
	}
	public ModelAndView mod(HttpServletRequest request,HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id"));
			
			String wechatletName = StringUtil.parseString(request.getParameter("wechatletName"));              //���ܵ�����
			String description = StringUtil.parseString(request.getParameter("description"));                              //·��
			String classname = StringUtil.parseString(request.getParameter("classname"));                      //����
			
			String[] checkPA = request.getParameterValues("checkPA");
			this.getMenuFunctionService().delPubAccountFunction(id);
			String[] paIDs = null;
			if (checkPA != null) {
				paIDs = new String[checkPA.length];
				for (int i = 0; i < checkPA.length; i++) {
					String paid = checkPA[i].split("\\|")[0];
					paIDs[i] = paid;
				}
			}
			WechatFunctionVO vo = new WechatFunctionVO();
			vo.setClassname(classname);
			vo.setDescription(description);
			vo.setWechatletName(wechatletName);
			vo.setId(id);
			vo.setPaIDs(paIDs);
			
			int retCode = this.menuFunctionService.modFunction(vo);
			if(retCode != 0){
				return new ModelAndView("info", "message", this.menuFunctionService.getRetMsg());
			}    
			
			/**
			 * sync ҵ������Ϣ
			 */
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatId", id);
			List<WechatFunction> list = this.getMenuFunctionService().qry(params);
			
			//����wechat_web ��������list
			
			//end
			
			return new ModelAndView("pageinfo","message","�޸ĳɹ�").addObject("menuURL","menuFunction.do?action=qry");
			
		} catch (Exception e) {
			log.error("�޸��쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "�޸��쳣!");
		}
	}
	
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response){
		try {		
			String id = StringUtil.parseString(request.getParameter("id"));
			WechatFunction wechatFunction = (WechatFunction) this.menuFunctionService.findById(id);
			
			if(wechatFunction == null){
				return new ModelAndView("info", "message", "��ҵ�����Ѿ������ڣ�");
			}
			
			WechatFunctionVO vo = new WechatFunctionVO();
			vo.setId(id);
			if (this.getMenuFunctionService().delFunction(vo) != 0) {
				return new ModelAndView("info", "message", this.getMenuFunctionService().getRetMsg());
			}
			/**
			 * sync ҵ������Ϣ
			 */
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatId", id);
			List<WechatFunction> list = this.getMenuFunctionService().qry(params);
			
			//����wechat_web ��������list
			
			//end
			
			return new ModelAndView("pageinfo_pagedecrease", "message","ɾ���ɹ�").addObject("url", "menuFunction.do?action=qry");
		
		}catch (DataIntegrityViolationException e) {
			log.info("ɾ��ʧ�ܣ�" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}
	
	public ModelAndView qryFuncInfo(HttpServletRequest request, HttpServletResponse response){
		
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		try {
			
			String wechatId = StringUtil.parseString(request.getParameter("wechatId"));
			
			// ��ѯ�������ں�Ϊ�� ����Ĭ�Ϲ��ں�
			if("".equals(wechatId)){
				wechatId = userSession.getWechatId();//��ȡsession��ѡ�е�wechatID
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatId", wechatId);
			
			List<WechatFunction> auxfunctionList = this.getMenuFunctionService().qry(params);
			
			params.put("wechatId", "");
			List<WechatFunction> functionList = this.getMenuFunctionService().qry(params);
			
			
			List<WechatFunctionVO2> retList = new ArrayList<WechatFunctionVO2>();
//			WechatFunctionVO2
			for(WechatFunction wef : functionList){
				WechatFunctionVO2 vo = new WechatFunctionVO2();
				vo.setId(wef.getId());
				vo.setWechatletName(wef.getWechatletName());
				vo.setClassname(wef.getClassname());
				vo.setDescription(wef.getDescription());
				vo.setSelected(auxfunctionList.contains(wef) == true ? "1" : "0");
				
				retList.add(vo);
			}
			
			
			
			ModelAndView mv = new ModelAndView("manage/menuFunction_set")
										.addObject("retList",retList)
										.addObject("wechatId", wechatId);
			return mv;
		} catch (Exception e) {
			 log.error("��ѯ�쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "��ѯ�쳣!");
		}
	}
	
	public ModelAndView configureFunction(HttpServletRequest request,HttpServletResponse response){
		
		try {
			
			String wechatOriginId = StringUtil.parseString(request.getParameter("id"));
			String wechatFuncs = StringUtil.parseString(request.getParameter("data"));
			
			// ɾ����ϵ��
			this.getMenuFunctionService().delPubAccountFunctionByWechatId(wechatOriginId);
			
			List<PubaccountFunctionVO> list = new ArrayList<PubaccountFunctionVO>();
			
			if(!"".equals(wechatFuncs)){
				String[] arry = wechatFuncs.split("\\|",-1);
				PubaccountFunctionVO vo = null;
				for (int i = 0; i < arry.length; i++){
					vo = new PubaccountFunctionVO();
					vo.setWechatFunctionId(arry[i]);
					vo.setWechatId(wechatOriginId);
					
					list.add(vo);
				}
			}
//			int retCode = 0;
			
			int retCode = this.getMenuFunctionService().savePubFuncRelations(list);
			
			if (retCode != 0){
				return new ModelAndView("info", "message", this.menuFunctionService.getRetMsg());
			}
			return new ModelAndView("pageinfo_pagedecrease", "message","���óɹ�")
			.addObject("url", "menuFunction.do?action=qryFuncInfo");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("����ʧ�ܣ�" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
			
		}
	}
	
}
