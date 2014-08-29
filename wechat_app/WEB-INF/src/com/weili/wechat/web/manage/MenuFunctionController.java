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
			
			// 查询条件公众号为空 采用默认公众号
			if("".equals(wechatId)){
				wechatId = userSession.getWechatId();//获取session中选中的wechatID
			}
			// 获取有权限公众号列表
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
			 log.error("查询异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "查询异常!");
		}
	}
	public ModelAndView addPage(HttpServletRequest request, HttpServletResponse response){
		try {
			ModelAndView mv = new ModelAndView("manage/menuFunction_add");
			List paList = this.getUserService().qryAllPubAccount();
			mv.addObject("paList", paList);
			return mv;
		} catch (Exception e) {
			log.error("添加异常："+ e.getMessage());
			return new ModelAndView("info", "message", "添加异常!");
		}
	}
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response){
		try {
			String wechatletName = StringUtil.parseString(request.getParameter("wechatletName"));              //功能点名称
			String description = StringUtil.parseString(request.getParameter("description"));                      //描述
			String classname = StringUtil.parseString(request.getParameter("classname"));                              //路径
			
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
			 * sync 业务功能信息
			 */
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatId", id);
			List<WechatFunction> list = this.getMenuFunctionService().qry(params);
			
			//调用wechat_web 方法推送list
			
			//end
			
			return new ModelAndView("pageinfo","message","添加成功")
					.addObject("menuURL","menuFunction.do?action=qry");
			
			
		} catch (Exception e) {
			log.error("添加异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "添加异常!");
		}
	}
	public ModelAndView modPage(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id"));
			WechatFunction wechatFunction = (WechatFunction) this.menuFunctionService.findById(id);
			
			if (wechatFunction == null) {
				return new ModelAndView("info", "message", "该业务功能已经不存在!");
			}
			
			List paList = this.getUserService().qryAllPubAccount();
			List pubFunIdList = this.getMenuFunctionService().qryPubAccountIdByFunId(id);			
			ModelAndView mv = new ModelAndView("manage/menuFunction_mod")
											.addObject("wechatFunction",wechatFunction)
											.addObject("paList", paList)
											.addObject("pubFunIdList", pubFunIdList);
			return mv;
		} catch (Exception e) {
			log.error("添加异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "添加异常!");
		}
	}
	public ModelAndView mod(HttpServletRequest request,HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id"));
			
			String wechatletName = StringUtil.parseString(request.getParameter("wechatletName"));              //功能点名称
			String description = StringUtil.parseString(request.getParameter("description"));                              //路径
			String classname = StringUtil.parseString(request.getParameter("classname"));                      //描述
			
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
			 * sync 业务功能信息
			 */
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatId", id);
			List<WechatFunction> list = this.getMenuFunctionService().qry(params);
			
			//调用wechat_web 方法推送list
			
			//end
			
			return new ModelAndView("pageinfo","message","修改成功").addObject("menuURL","menuFunction.do?action=qry");
			
		} catch (Exception e) {
			log.error("修改异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "修改异常!");
		}
	}
	
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response){
		try {		
			String id = StringUtil.parseString(request.getParameter("id"));
			WechatFunction wechatFunction = (WechatFunction) this.menuFunctionService.findById(id);
			
			if(wechatFunction == null){
				return new ModelAndView("info", "message", "该业务功能已经不存在！");
			}
			
			WechatFunctionVO vo = new WechatFunctionVO();
			vo.setId(id);
			if (this.getMenuFunctionService().delFunction(vo) != 0) {
				return new ModelAndView("info", "message", this.getMenuFunctionService().getRetMsg());
			}
			/**
			 * sync 业务功能信息
			 */
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatId", id);
			List<WechatFunction> list = this.getMenuFunctionService().qry(params);
			
			//调用wechat_web 方法推送list
			
			//end
			
			return new ModelAndView("pageinfo_pagedecrease", "message","删除成功").addObject("url", "menuFunction.do?action=qry");
		
		}catch (DataIntegrityViolationException e) {
			log.info("删除失败：" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}
	
	public ModelAndView qryFuncInfo(HttpServletRequest request, HttpServletResponse response){
		
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		try {
			
			String wechatId = StringUtil.parseString(request.getParameter("wechatId"));
			
			// 查询条件公众号为空 采用默认公众号
			if("".equals(wechatId)){
				wechatId = userSession.getWechatId();//获取session中选中的wechatID
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
			 log.error("查询异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "查询异常!");
		}
	}
	
	public ModelAndView configureFunction(HttpServletRequest request,HttpServletResponse response){
		
		try {
			
			String wechatOriginId = StringUtil.parseString(request.getParameter("id"));
			String wechatFuncs = StringUtil.parseString(request.getParameter("data"));
			
			// 删除关系表
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
			return new ModelAndView("pageinfo_pagedecrease", "message","配置成功")
			.addObject("url", "menuFunction.do?action=qryFuncInfo");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("配置失败：" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
			
		}
	}
	
}
