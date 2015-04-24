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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.hibernate.StoreInfo;
import com.weili.wechat.service.manage.StoreInfoService;
import com.weili.wechat.vo.StoreClassificationInfoVO;
import com.weili.wechat.vo.StoreInfoVO;


public class StoreInfoController extends MultiActionController{
	private static Log log = LogFactory.getLog(StoreInfoController.class);
	
	private StoreInfoService storeInfoService;
	
	
	public StoreInfoService getStoreInfoService() {
		return storeInfoService;
	}

	public void setStoreInfoService(StoreInfoService storeInfoService) {
		this.storeInfoService = storeInfoService;
	}
	public ModelAndView qry(HttpServletRequest request, HttpServletResponse response){
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String storeId = userSession.getStoreId();
			String storeNo = StringUtil.parseString(request.getParameter("storeNo"));
			String storeName = StringUtil.parseString(request.getParameter("storeName"));
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("storeId", storeId);
			params.put("storeNo", storeNo);
			params.put("storeName", storeName);
			
			List storeInfoList = this.getStoreInfoService().qryStoreInfo(params); 
			ModelAndView mv = new ModelAndView("manage/store_info_qry")
								  .addObject("storeInfoList", storeInfoList);
								  //.addObject("storeNo", storeNo)
								  //.addObject("storeName", storeName);
			return mv;
		} catch (Exception e) {
			 log.error("查询异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "查询异常!");
		
		}
	}
	
	public void qryM(HttpServletRequest request, HttpServletResponse response){
		try {
			
			String cfId = StringUtil.parseString(request.getParameter("cfId")); 
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cfId", cfId);
			
			List storeInfoList = this.getStoreInfoService().qryStoreInfo(params); 
			List<JSONObject> arrayList = new ArrayList<JSONObject>();

			StoreInfo sInfo = null;
			for (Object obj : storeInfoList) {
				sInfo = (StoreInfo)obj;
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", sInfo.getId());
				jsonObject.put("storeNo", sInfo.getStoreNo());
				jsonObject.put("storeName", sInfo.getStoreName());
				jsonObject.put("cfId", sInfo.getStoreClassificationInfoBySecClassification1().getId());
				jsonObject.put("clName", sInfo.getStoreClassificationInfoBySecClassification1().getClName());
				jsonObject.put("phone", sInfo.getPhone());
				jsonObject.put("address", sInfo.getAddress());
				jsonObject.put("businessHours", sInfo.getBusinessHours());
				jsonObject.put("serviceRadius", sInfo.getServiceRadius());
				jsonObject.put("deliveryArea", sInfo.getDeliveryArea());
				jsonObject.put("deliveryCharges", sInfo.getDeliveryCharges());
				jsonObject.put("flagFallPrice", sInfo.getFlagFallPrice());
				jsonObject.put("deliveryTime", sInfo.getDeliveryTime());
//				jsonObject.put("onLine", sInfo.getOnLine());
				
				arrayList.add(jsonObject);
			}
			
			log.info("qryM in");
	        
	        response.setContentType("text/json"); 
            /*设置字符集为'UTF-8'*/
            response.setCharacterEncoding("UTF-8"); 
            PrintWriter out = response.getWriter();
	        try {
				out.println(JSONArray.fromObject(arrayList));
				out.flush();
				out.close();
			} catch (Exception e) {
				// TODO: handle exception
				out.flush();
				out.close();
			}
	       
		} catch (Exception e) {
			 log.error("查询异常："+ e.getMessage());
//			 /return new ModelAndView("info", "message", "查询异常!");
		}
	}
	
	public ModelAndView addPage(HttpServletRequest request, HttpServletResponse response){
		try {
			ModelAndView mv=new ModelAndView("manage/store_info_add");
			Map<String, Object> params = new HashMap<String, Object>();
			List<?> scfList = this.getStoreInfoService().qryScfList(params);
			mv.addObject("scfList", scfList);
			return mv;
		} catch (Exception e) {
			log.error("进入添加页面异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "进入添加页面异常!");
		}
	}
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response){
		try {
			String storeNo = StringUtil.parseString(request.getParameter("storeNo"));  
			String storeName = StringUtil.parseString(request.getParameter("storeName"));
			String phone = StringUtil.parseString(request.getParameter("phone"));
			String address = StringUtil.parseString(request.getParameter("address"));
			String passwd = StringUtil.parseString(request.getParameter("passwd"));
			String businessHours = StringUtil.parseString(request.getParameter("businessHours"));
			String serviceRadius = StringUtil.parseString(request.getParameter("serviceRadius"));
			String deliveryArea = StringUtil.parseString(request.getParameter("deliveryArea"));
			
			String storeClassificationId = StringUtil.parseString(request.getParameter("storeClassificationId"));  
			
			int deliveryCharges = Integer.parseInt(request.getParameter("deliveryCharges"));
			int flagFallPrice = Integer.parseInt(request.getParameter("flagFallPrice"));
			int deliveryTime = Integer.parseInt(request.getParameter("deliveryTime"));
			int onLine = Integer.parseInt(request.getParameter("onLine"));
			
			
			StoreInfoVO sInfoVO = new StoreInfoVO();
			sInfoVO.setAddress(address);
			sInfoVO.setBusinessHours(businessHours);
			sInfoVO.setDeliveryArea(deliveryArea);
			sInfoVO.setDeliveryCharges(deliveryCharges);
			sInfoVO.setDeliveryTime(deliveryTime);
			sInfoVO.setFlagFallPrice(flagFallPrice);
			sInfoVO.setOnLine(onLine);
			sInfoVO.setPasswd(passwd);
			sInfoVO.setPhone(phone);
			sInfoVO.setServiceRadius(serviceRadius);
			sInfoVO.setStoreName(storeName);
			sInfoVO.setStoreNo(storeNo);
			
			sInfoVO.setId(UUID.randomUUID().toString());
			/*
			String[] clArry = null;
			if (!"".equals(storeClStr)) {
				clArry = storeClStr.split("\\|");
			}
			for (int i = 0; i < clArry.length; i++) {
				if (i == 0) {
					StoreClassificationInfoVO storeClVO1 = new StoreClassificationInfoVO(clArry[i]);
					sInfoVO.setStoreClVO1(storeClVO1);
				}else if (i == 1) {
					StoreClassificationInfoVO storeClVO2 = new StoreClassificationInfoVO(clArry[i]);
					sInfoVO.setStoreClVO2(storeClVO2);
				}else if (i == 2) {
					StoreClassificationInfoVO storeClVO3 = new StoreClassificationInfoVO(clArry[i]);
					sInfoVO.setStoreClVO3(storeClVO3);
				}else {
					//...
				}
			}
			*/
			
			StoreClassificationInfoVO storeClVO1 = new StoreClassificationInfoVO(storeClassificationId);
			sInfoVO.setStoreClVO1(storeClVO1);
			try {
				if (this.getStoreInfoService().addStoreInfo(sInfoVO) != 0) {
					return new ModelAndView("info","message", this.getStoreInfoService().getRetMsg());
				}
			    return new ModelAndView("pageinfo","message","添加成功").addObject("menuURL","storeInfo.do?action=qry");
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
			ModelAndView mv = new ModelAndView("manage/store_info_mod");
			String id = StringUtil.parseString(request.getParameter("id"));
			StoreInfoVO sInfoVO = (StoreInfoVO) this.getStoreInfoService().findById(id);
			mv.addObject("sInfoVO", sInfoVO);
			
			Map<String, Object> params = new HashMap<String, Object>();
			List<?> scfList = this.getStoreInfoService().qryScfList(params);
			mv.addObject("scfList", scfList);
			
			return mv;
		} catch (Exception e) {
			log.error("进入修改页面异常：" + e.getMessage());
			return new ModelAndView("info", "message", "进入修改页面异常!");
		}
	}
	public ModelAndView mod(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id"));
			
			String storeNo = StringUtil.parseString(request.getParameter("storeNo"));  
			String storeName = StringUtil.parseString(request.getParameter("storeName"));
			String phone = StringUtil.parseString(request.getParameter("phone"));
			String address = StringUtil.parseString(request.getParameter("address"));
			String passwd = StringUtil.parseString(request.getParameter("passwd"));
			String businessHours = StringUtil.parseString(request.getParameter("businessHours"));
			String serviceRadius = StringUtil.parseString(request.getParameter("serviceRadius"));
			String deliveryArea = StringUtil.parseString(request.getParameter("deliveryArea"));
			
//			String storeClStr = StringUtil.parseString(request.getParameter("storeClStr"));  
			String storeClassificationId = StringUtil.parseString(request.getParameter("storeClassificationId")); 
			
			int deliveryCharges = Integer.parseInt(request.getParameter("deliveryCharges"));
			int flagFallPrice = Integer.parseInt(request.getParameter("flagFallPrice"));
			int deliveryTime = Integer.parseInt(request.getParameter("deliveryTime"));
			int onLine = Integer.parseInt(request.getParameter("onLine"));
			
			
			StoreInfoVO sInfoVO = new StoreInfoVO();
			sInfoVO.setAddress(address);
			sInfoVO.setBusinessHours(businessHours);
			sInfoVO.setDeliveryArea(deliveryArea);
			sInfoVO.setDeliveryCharges(deliveryCharges);
			sInfoVO.setDeliveryTime(deliveryTime);
			sInfoVO.setFlagFallPrice(flagFallPrice);
			sInfoVO.setOnLine(onLine);
			sInfoVO.setPasswd(passwd);
			sInfoVO.setPhone(phone);
			sInfoVO.setServiceRadius(serviceRadius);
			sInfoVO.setStoreName(storeName);
			sInfoVO.setStoreNo(storeNo);
			
			sInfoVO.setId(id);
			/*
			String[] clArry = null;
			if (!"".equals(storeClStr)) {
				clArry = storeClStr.split("\\|");
			}
			for (int i = 0; i < clArry.length; i++) {
				if (i == 0) {
					StoreClassificationInfoVO storeClVO1 = new StoreClassificationInfoVO(clArry[i]);
					sInfoVO.setStoreClVO1(storeClVO1);
				}else if (i == 1) {
					StoreClassificationInfoVO storeClVO2 = new StoreClassificationInfoVO(clArry[i]);
					sInfoVO.setStoreClVO2(storeClVO2);
				}else if (i == 2) {
					StoreClassificationInfoVO storeClVO3 = new StoreClassificationInfoVO(clArry[i]);
					sInfoVO.setStoreClVO3(storeClVO3);
				}else {
					//...
				}
			}
			*/
			StoreClassificationInfoVO storeClVO1 = new StoreClassificationInfoVO(storeClassificationId);
			sInfoVO.setStoreClVO1(storeClVO1);
			try {
				if(this.getStoreInfoService().modStoreInfo(sInfoVO) != 0){
					return new ModelAndView("info","message", this.getStoreInfoService().getRetMsg());
				}
			    return new ModelAndView("pageinfo","message","修改成功").addObject("menuURL","storeInfo.do?action=qry");
			} catch (Exception e) {
				  return new ModelAndView("info","message","修改失败:"+e);
			}
		} catch (Exception e) {
			log.error("修改异常："+ e);
			 return new ModelAndView("info", "message", "修改异常!");
		}
	}
	@RequestMapping(value = "del.json")
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response){
		try {		
			String id = StringUtil.parseString(request.getParameter("id"));
			StoreInfoVO sInfoVO = (StoreInfoVO) this.getStoreInfoService().findById(id);
			if(sInfoVO == null)
			{
				throw new DataIntegrityViolationException("该门店已经不存在");
			}
			if (this.getStoreInfoService().delStoreInfo(id) != 0) {
				return new ModelAndView("info", "message", this.getStoreInfoService().getRetMsg());
			}
			return new ModelAndView("pageinfo_pagedecrease", "message","删除成功").addObject("url", "storeInfo.do?action=qry");
		
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
	public void saveMenuBtn(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		PrintWriter out = response.getWriter();
		try {
			
			String wechatOriginId = StringUtil.parseString(request.getParameter("id"));
			String data =request.getParameter("data");
			data = (data == null) ? "" : new String(data.getBytes("ISO-8859-1"), "utf-8");
			data = java.net.URLDecoder.decode(data,"UTF-8");
			
			/**
			 * 默认一个公众号只用一套 菜单
			 */
			
			out.print("error");
			out.close();
			out = null;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			out.print("error");
			out.close();
			out = null;
			
		}

	}
	
}
