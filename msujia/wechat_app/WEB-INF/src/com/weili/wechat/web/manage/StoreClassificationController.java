package com.weili.wechat.web.manage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.weili.wechat.common.StringUtil;
import com.weili.wechat.hibernate.StoreClassificationInfo;
import com.weili.wechat.hibernate.StoreCommInfo;
import com.weili.wechat.service.manage.StoreClassificationService;

public class StoreClassificationController extends MultiActionController{
	private static Log log = LogFactory.getLog(StoreClassificationController.class);
	
	private StoreClassificationService storeClassificationService;
	
	public StoreClassificationService getStoreClassificationService() {
		return storeClassificationService;
	}

	public void setStoreClassificationService(
			StoreClassificationService storeClassificationService) {
		this.storeClassificationService = storeClassificationService;
	}

	public ModelAndView qry(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id")); 
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id);
			
			List storeCfInfoList = this.getStoreClassificationService().qryStoreClassificationInfo(params); 
			ModelAndView mv = new ModelAndView("manage/comm_classification_info_qry")
								  .addObject("storeCfInfoList", storeCfInfoList);
			return mv;
		} catch (Exception e) {
			 log.error("查询异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "查询异常!");
		
		}
	}
	public void qryM(HttpServletRequest request, HttpServletResponse response){
		try {
			
			String id = StringUtil.parseString(request.getParameter("id")); 
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id);
			
			List storeCfInfoList = this.getStoreClassificationService().qryStoreClassificationInfo(params); 
			
			log.info("qryM in");
			List<JSONObject> arrayList = new ArrayList<JSONObject>();

			StoreClassificationInfo scf = null;
			for (Object obj : storeCfInfoList) {
				scf = (StoreClassificationInfo)obj;
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", scf.getId());
				jsonObject.put("clLevel", scf.getClLevel());
				jsonObject.put("clName", scf.getClName());
				jsonObject.put("picLink", scf.getPicLink());
				
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
	
}
