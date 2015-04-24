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
			 log.error("��ѯ�쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "��ѯ�쳣!");
		
		}
	}
	
	public ModelAndView addPage(HttpServletRequest request, HttpServletResponse response){
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String storeId = userSession.getStoreId();
			if ("".equals(storeId) || storeId.length() == 0) {
				return new ModelAndView("info","message","�û�Ĭ���ŵ�Ϊ�գ�����ӵ������ŵ����Ȩ�ޣ�������ϵ����Ա������");
			}
			ModelAndView mv=new ModelAndView("manage/comm_classification_info_add");
			return mv;
		} catch (Exception e) {
			log.error("�������ҳ���쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "�������ҳ���쳣!");
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
			    return new ModelAndView("pageinfo","message","��ӳɹ�").addObject("menuURL","storeCommClassificationInfo.do?action=qry");
			} catch (Exception e) {
				  return new ModelAndView("info","message","���ʧ��:"+e);
			}
		} catch (Exception e) {
			log.error("����쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "����쳣!");
		}
	}
	public ModelAndView modPage(HttpServletRequest request, HttpServletResponse response){
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String storeId = userSession.getStoreId();
			if ("".equals(storeId) || storeId.length() == 0) {
				return new ModelAndView("info","message","�û�Ĭ���ŵ�Ϊ�գ�����ӵ���޸��ŵ����Ȩ�ޣ�������ϵ����Ա������");
			}
			
			String id = StringUtil.parseString(request.getParameter("id"));
			StoreCommClassificationInfoVO vo = (StoreCommClassificationInfoVO) this.getStoreCommClassificationInfoService().findById(id);
			ModelAndView mv = new ModelAndView("manage/comm_classification_info_mod").addObject("vo", vo);
			return mv;
		} catch (Exception e) {
			log.error("�����޸�ҳ���쳣��" + e.getMessage());
			return new ModelAndView("info", "message", "�����޸�ҳ���쳣!");
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
			    return new ModelAndView("pageinfo","message","�޸ĳɹ�").addObject("menuURL","storeCommClassificationInfo.do?action=qry");
			} catch (Exception e) {
				  return new ModelAndView("info","message","�޸�ʧ��:" + e);
			}
		} catch (Exception e) {
			log.error("�޸��쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "�޸��쳣!");
		}
	}
	
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response){
		try {		
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String storeId = userSession.getStoreId();
			if ("".equals(storeId) || storeId.length() == 0) {
				return new ModelAndView("info","message","�û�Ĭ���ŵ�Ϊ�գ�����ӵ��ɾ���ŵ����Ȩ�ޣ�������ϵ����Ա������");
			}
			
			String id = StringUtil.parseString(request.getParameter("id"));
			StoreCommClassificationInfoVO vo = (StoreCommClassificationInfoVO) this.getStoreCommClassificationInfoService().findById(id);
			if(vo == null)
			{
				throw new DataIntegrityViolationException("���ŵ��Ѿ�������");
			}
			if (this.getStoreCommClassificationInfoService().delCommClassificationInfo(id) != 0) {
				return new ModelAndView("info", "message", this.getStoreCommClassificationInfoService().getRetMsg());
			}
			return new ModelAndView("pageinfo_pagedecrease", "message","ɾ���ɹ�").addObject("url", "storeCommClassificationInfo.do?action=qry");
		
		}catch (DataIntegrityViolationException e) {
			log.info("ɾ��ʧ�ܣ�" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		} 
		catch (Exception e) {
			log.error("ɾ���쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "ɾ���쳣!");
		}
	}
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response){
		try {
			return null;
		} catch (Exception e) {
			log.error("������ϸҳ���쳣��" + e.getMessage());
			return new ModelAndView("info", "message", "������ϸҳ���쳣!");
		}
	}
	
}
