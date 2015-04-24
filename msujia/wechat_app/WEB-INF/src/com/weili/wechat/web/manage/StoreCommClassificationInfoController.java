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

import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.service.manage.StoreCommClassificationInfoService;
import com.weili.wechat.vo.StoreCommClassificationInfoVO;

public class StoreCommClassificationInfoController extends MultiActionController{
	private static Log log = LogFactory.getLog(StoreCommClassificationInfoController.class);
	
	private StoreCommClassificationInfoService storeCommClassificationInfoService;
	

	public StoreCommClassificationInfoService getStoreCommClassificationInfoService() {
		return storeCommClassificationInfoService;
	}

	public void setStoreCommClassificationInfoService(
			StoreCommClassificationInfoService storeCommClassificationInfoService) {
		this.storeCommClassificationInfoService = storeCommClassificationInfoService;
	}

	public ModelAndView qry(HttpServletRequest request, HttpServletResponse response){
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String storeId = userSession.getStoreId();
			String id = StringUtil.parseString(request.getParameter("id"));
			String name = StringUtil.parseString(request.getParameter("name"));
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("storeId", storeId);
			params.put("id", id);
			params.put("name", name);
			
			List commClassificationInfoList = this.getStoreCommClassificationInfoService().qryCommClassificationInfo(params); 
			ModelAndView mv = new ModelAndView("manage/comm_classification_info_qry")
								  .addObject("commClassificationInfoList", commClassificationInfoList)
								  .addObject("id", id)
								  .addObject("name", name);
			return mv;
		} catch (Exception e) {
			 log.error("查询异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "查询异常!");
		
		}
	}
	
	public ModelAndView addPage(HttpServletRequest request, HttpServletResponse response){
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String storeId = userSession.getStoreId();
			if ("".equals(storeId) || storeId.length() == 0) {
				return new ModelAndView("info","message","用户默认门店为空，不该拥有添加门店分类权限！！请联系管理员！！！");
			}
			ModelAndView mv=new ModelAndView("manage/comm_classification_info_add");
			return mv;
		} catch (Exception e) {
			log.error("进入添加页面异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "进入添加页面异常!");
		}
	}
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response){
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String storeId = userSession.getStoreId();
			String name = StringUtil.parseString(request.getParameter("name"));
			
			StoreCommClassificationInfoVO vo = new StoreCommClassificationInfoVO();
			vo.setCommClassification(name);
			vo.setStoreId(storeId);
			vo.setId(UUID.randomUUID().toString());
			
			try {
				if (this.getStoreCommClassificationInfoService().addCommClassificationInfo(vo) != 0) {
					return new ModelAndView("info", "message", this.getStoreCommClassificationInfoService().getRetMsg());
				}
			    return new ModelAndView("pageinfo","message","添加成功").addObject("menuURL","storeCommClassificationInfo.do?action=qry");
			} catch (Exception e) {
				  return new ModelAndView("info","message","添加失败:"+e);
			}
		} catch (Exception e) {
			log.error("添加异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "添加异常!");
		}
	}
	public ModelAndView modPage(HttpServletRequest request, HttpServletResponse response){
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String storeId = userSession.getStoreId();
			if ("".equals(storeId) || storeId.length() == 0) {
				return new ModelAndView("info","message","用户默认门店为空，不该拥有修改门店分类权限！！请联系管理员！！！");
			}
			
			String id = StringUtil.parseString(request.getParameter("id"));
			StoreCommClassificationInfoVO vo = (StoreCommClassificationInfoVO) this.getStoreCommClassificationInfoService().findById(id);
			ModelAndView mv = new ModelAndView("manage/comm_classification_info_mod").addObject("vo", vo);
			return mv;
		} catch (Exception e) {
			log.error("进入修改页面异常：" + e.getMessage());
			return new ModelAndView("info", "message", "进入修改页面异常!");
		}
	}
	public ModelAndView mod(HttpServletRequest request, HttpServletResponse response){
		try {
			
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String storeId = userSession.getStoreId();
			
			String id = StringUtil.parseString(request.getParameter("id"));
			String name = StringUtil.parseString(request.getParameter("name"));
			
			StoreCommClassificationInfoVO vo = new StoreCommClassificationInfoVO();
			vo.setCommClassification(name);
			vo.setStoreId(storeId);
			vo.setId(id);
			
			try {
				if (this.getStoreCommClassificationInfoService().modCommClassificationInfo(vo) != 0) {
					return new ModelAndView("info", "message", this.getStoreCommClassificationInfoService().getRetMsg());
				}
			    return new ModelAndView("pageinfo","message","修改成功").addObject("menuURL","storeCommClassificationInfo.do?action=qry");
			} catch (Exception e) {
				  return new ModelAndView("info","message","修改失败:" + e);
			}
		} catch (Exception e) {
			log.error("修改异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "修改异常!");
		}
	}
	
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response){
		try {		
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String storeId = userSession.getStoreId();
			if ("".equals(storeId) || storeId.length() == 0) {
				return new ModelAndView("info","message","用户默认门店为空，不该拥有删除门店分类权限！！请联系管理员！！！");
			}
			
			String id = StringUtil.parseString(request.getParameter("id"));
			StoreCommClassificationInfoVO vo = (StoreCommClassificationInfoVO) this.getStoreCommClassificationInfoService().findById(id);
			if(vo == null)
			{
				throw new DataIntegrityViolationException("该门店已经不存在");
			}
			if (this.getStoreCommClassificationInfoService().delCommClassificationInfo(id) != 0) {
				return new ModelAndView("info", "message", this.getStoreCommClassificationInfoService().getRetMsg());
			}
			return new ModelAndView("pageinfo_pagedecrease", "message","删除成功").addObject("url", "storeCommClassificationInfo.do?action=qry");
		
		}catch (DataIntegrityViolationException e) {
			log.info("删除失败：" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		} 
		catch (Exception e) {
			log.error("删除异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "删除异常!");
		}
	}
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response){
		try {
			return null;
		} catch (Exception e) {
			log.error("进入详细页面异常：" + e.getMessage());
			return new ModelAndView("info", "message", "进入详细页面异常!");
		}
	}
	
}
