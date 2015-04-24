package com.weili.wechat.web.manage;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.hibernate.StoreCommInfo;
import com.weili.wechat.hibernate.StoreInfo;
import com.weili.wechat.service.manage.StoreCommInfoService;
import com.weili.wechat.vo.StoreCommClassificationInfoVO;
import com.weili.wechat.vo.StoreCommInfoVO;
import com.weili.wechat.vo.StoreInfoVO;

public class StoreCommInfoController extends MultiActionController{
	private static Log log = LogFactory.getLog(StoreCommInfoController.class);
	
	private StoreCommInfoService storeCommInfoService;
	
	public StoreCommInfoService getStoreCommInfoService() {
		return storeCommInfoService;
	}

	public void setStoreCommInfoService(StoreCommInfoService storeCommInfoService) {
		this.storeCommInfoService = storeCommInfoService;
	}

	public ModelAndView qry(HttpServletRequest request, HttpServletResponse response){
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String storeId = userSession.getStoreId();
			String commId = StringUtil.parseString(request.getParameter("commId"));
			String commName = StringUtil.parseString(request.getParameter("commName"));
			String commCfId = StringUtil.parseString(request.getParameter("commCfId"));
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("storeId", storeId);
			params.put("commId", commId);
			params.put("commName", commName);
			params.put("commCfId", commCfId);
			
			List storeCommInfoList = this.getStoreCommInfoService().qryStoreCommInfo(params); 
			ModelAndView mv = new ModelAndView("manage/store_comm_info_qry")
								  .addObject("storeCommInfoList", storeCommInfoList)
								  .addObject("commId", commId)
								  .addObject("commName", commName)
								  .addObject("commCfId", commCfId);
			return mv;
		} catch (Exception e) {
			 log.error("查询异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "查询异常!");
		
		}
	}
	
	public void qryM(HttpServletRequest request, HttpServletResponse response){
		try {
			log.info("qryM in");
			String storeId = StringUtil.parseString(request.getParameter("storeId")); 
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("storeId", storeId);
			
			List storeCommInfoList = this.getStoreCommInfoService().qryStoreCommInfo(params); 
			
			
			List<JSONObject> arrayList = new ArrayList<JSONObject>();

			StoreCommInfo commInfo = null;
			for (Object obj : storeCommInfoList) {
				commInfo = (StoreCommInfo)obj;
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("commId", commInfo.getId());
				jsonObject.put("commName", commInfo.getName());
				jsonObject.put("storeId", commInfo.getStoreInfo().getId());
				jsonObject.put("storeName", commInfo.getStoreInfo().getStoreName());
				jsonObject.put("commCfId", commInfo.getStoreCommClassificationInfo().getId());
				jsonObject.put("commClName", commInfo.getStoreCommClassificationInfo().getCommClassification());
				jsonObject.put("price", commInfo.getPrice());
				jsonObject.put("preferentialPrice", commInfo.getPreferentialPrice());
				jsonObject.put("onhand", commInfo.getOnhand());
				jsonObject.put("pictureLink", commInfo.getPictureLink());
				
				arrayList.add(jsonObject);
			}
			
	        
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
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String storeId = userSession.getStoreId();
			if ("".equals(storeId) || storeId.length() == 0) {
				return new ModelAndView("info","message","用户默认门店为空，不该拥有添加门店商品权限！！请联系管理员！！！");
			}
			
			ModelAndView mv=new ModelAndView("manage/store_comm_info_add");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("storeId", storeId);
			List<?> commCfList = this.getStoreCommInfoService().qryCommCfList(params);
			mv.addObject("commCfList", commCfList);
			mv.addObject("storeId", storeId);
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
			String commName = StringUtil.parseString(request.getParameter("commName"));
			String commClassificationId = StringUtil.parseString(request.getParameter("commClassificationId"));  
			double price = Double.parseDouble(request.getParameter("price"));
			double preferentialPrice = Double.parseDouble(request.getParameter("preferentialPrice"));
			int onhand = Integer.parseInt(request.getParameter("onhand"));
			int onShelves = Integer.parseInt(request.getParameter("onShelves"));
			
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("file");
			if (file == null) {
				return new ModelAndView("info", "message", "请选择要上传的文件!");
			}
			String fileOriginalName = file.getOriginalFilename();
			String pictureLink = "upload/" + storeId + "/" + commClassificationId + "/" + fileOriginalName;
			String storePath = request.getSession().getServletContext().getRealPath("upload/" + storeId + "/" + commClassificationId);
			File targetFile = new File(storePath, fileOriginalName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				log.error("上传图片失败：" + e.getMessage());
				return new ModelAndView("info", "message", "上传图片失败");
			}
			
			StoreCommInfoVO commInfoVO = new StoreCommInfoVO();
			commInfoVO.setId(UUID.randomUUID().toString());
			
			StoreInfoVO storeInfoVO = new StoreInfoVO();
			storeInfoVO.setId(storeId);
			commInfoVO.setStoreInfoVO(storeInfoVO);
			
			StoreCommClassificationInfoVO sCommCfInfoVO = new StoreCommClassificationInfoVO();
			sCommCfInfoVO.setId(commClassificationId);
			commInfoVO.setStoreCommClassificationInfoVO(sCommCfInfoVO);
			
			commInfoVO.setName(commName);
			commInfoVO.setPrice(price);
			commInfoVO.setPreferentialPrice(preferentialPrice);
			commInfoVO.setPictureLink(pictureLink);
			commInfoVO.setOnhand(onhand);
			commInfoVO.setOnShelves(onShelves);
			
			try {
				if (this.getStoreCommInfoService().addStoreCommInfo(commInfoVO) != 0) {
					if (targetFile.isFile() && targetFile.exists()) {
						  targetFile.delete();
					}
					return new ModelAndView("info", "message", this.getStoreCommInfoService().getRetMsg());
				}
			    return new ModelAndView("pageinfo","message","添加成功").addObject("menuURL","storeCommInfo.do?action=qry");
			} catch (Exception e) {
				  if (targetFile.isFile() && targetFile.exists()) {
					  targetFile.delete();
				  }
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
				return new ModelAndView("info","message","用户默认门店为空，不该拥有修改门店商品权限！！请联系管理员！！！");
			}
			
			ModelAndView mv = new ModelAndView("manage/store_comm_info_mod");
			String id = StringUtil.parseString(request.getParameter("id"));
			StoreCommInfoVO commInfoVO = (StoreCommInfoVO) this.getStoreCommInfoService().findById(id);
			mv.addObject("commInfoVO", commInfoVO);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("storeId", storeId);
			List<?> commCfList = this.getStoreCommInfoService().qryCommCfList(params);
			mv.addObject("commCfList", commCfList);
			
			return mv;
		} catch (Exception e) {
			log.error("进入修改页面异常：" + e.getMessage());
			return new ModelAndView("info", "message", "进入修改页面异常!");
		}
	}
	public ModelAndView mod(HttpServletRequest request, HttpServletResponse response){
		try {
			String commId = StringUtil.parseString(request.getParameter("id"));  
			String storeId = StringUtil.parseString(request.getParameter("storeId"));  
			String commName = StringUtil.parseString(request.getParameter("commName"));
			String commClassificationId = StringUtil.parseString(request.getParameter("commClassificationId"));  
			double price = Double.parseDouble(request.getParameter("price"));
			double preferentialPrice = Double.parseDouble(request.getParameter("preferentialPrice"));
			int onhand = Integer.parseInt(request.getParameter("onhand"));
			int onShelves = Integer.parseInt(request.getParameter("onShelves"));
			String oldPictureLink = StringUtil.parseString(request.getParameter("oldPictureLink"));
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("file");
			if (file == null) {
				return new ModelAndView("info", "message", "请选择要上传的文件!");
			}
			String fileOriginalName = file.getOriginalFilename();
			String pictureLink = "upload/" + storeId + "/" + commClassificationId + "/" + fileOriginalName;
			String storePath = request.getSession().getServletContext().getRealPath("upload/" + storeId + "/" + commClassificationId);
			File targetFile = new File(storePath, fileOriginalName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				log.error("上传图片失败：" + e.getMessage());
				return new ModelAndView("info", "message", "上传图片失败");
			}
			
			StoreCommInfoVO commInfoVO = new StoreCommInfoVO();
			commInfoVO.setId(commId);
			
			StoreInfoVO storeInfoVO = new StoreInfoVO();
			storeInfoVO.setId(storeId);
			commInfoVO.setStoreInfoVO(storeInfoVO);
			
			StoreCommClassificationInfoVO sCommCfInfoVO = new StoreCommClassificationInfoVO();
			sCommCfInfoVO.setId(commClassificationId);
			commInfoVO.setStoreCommClassificationInfoVO(sCommCfInfoVO);
			
			commInfoVO.setName(commName);
			commInfoVO.setPrice(price);
			commInfoVO.setPreferentialPrice(preferentialPrice);
			commInfoVO.setPictureLink(pictureLink);
			commInfoVO.setOnhand(onhand);
			commInfoVO.setOnShelves(onShelves);
			
			
			String oppositePath = "upload/" + storeId + "/" + commClassificationId+"/";
			String oldFileName = oldPictureLink.substring(oldPictureLink.indexOf(oppositePath)+oppositePath.length());
			File oldFile = new File(storePath, oldFileName);
			try {
				if (this.getStoreCommInfoService().modStoreCommInfo(commInfoVO) != 0) {
					if (oldFile.isFile() && oldFile.exists()) {
						oldFile.delete();
					}
					return new ModelAndView("info","message",this.getStoreCommInfoService().getRetMsg());
				} 
			    return new ModelAndView("pageinfo","message","修改成功").addObject("menuURL","storeCommInfo.do?action=qry");
			} catch (Exception e) {
				  if (targetFile.isFile() && targetFile.exists()) {
					  targetFile.delete();
				  }
				  return new ModelAndView("info","message","修改失败:"+e);
			}
		} catch (Exception e) {
			log.error("修改异常："+ e);
			 return new ModelAndView("info", "message", "修改异常!");
		}
	}
	
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response){
		try {		
			String id = StringUtil.parseString(request.getParameter("id"));
			StoreCommInfoVO commInfoVO = (StoreCommInfoVO) this.getStoreCommInfoService().findById(id);
			if(commInfoVO == null)
			{
				throw new DataIntegrityViolationException("该商品已经不存在");
			}
			if (this.getStoreCommInfoService().delStoreCommInfo(id) != 0) {
				return new ModelAndView("info", "message", this.getStoreCommInfoService().getRetMsg());
			}
			
			
			String storeId = commInfoVO.getStoreInfoVO().getId();
			String commClassificationId = commInfoVO.getStoreCommClassificationInfoVO().getId();
			String oppositePath = "upload/" + storeId + "/" + commClassificationId+"/";
			String pictureLink = commInfoVO.getPictureLink();
			String storePath = request.getSession().getServletContext().getRealPath(oppositePath);
			String fileOriginalName = pictureLink.substring(pictureLink.indexOf(oppositePath)+oppositePath.length());
			File targetFile = new File(storePath, fileOriginalName);
			if (targetFile.isFile() && targetFile.exists()) {
				  targetFile.delete();
			}
			
			return new ModelAndView("pageinfo_pagedecrease", "message","删除成功").addObject("url", "storeCommInfo.do?action=qry");
		
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
