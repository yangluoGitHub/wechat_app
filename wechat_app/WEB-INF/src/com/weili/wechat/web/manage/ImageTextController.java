package com.weili.wechat.web.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import java_cup.internal_error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.weili.wechat.common.CalendarUtil;
import com.weili.wechat.common.GetResource;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.StatusEnum.MediaType;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.hibernate.WechatArticle;
import com.weili.wechat.hibernate.WechatFunction;
import com.weili.wechat.hibernate.WechatMedia;
import com.weili.wechat.hibernate.WechatResource;
import com.weili.wechat.service.manage.ImageTextService;
import com.weili.wechat.service.manage.MediaService;
import com.weili.wechat.service.manage.MenuFunctionService;
import com.weili.wechat.vo.ImageTextDetail;


/**
 * 图文管理
 * 
 * @author qfxu
 *
 */
public class ImageTextController extends MultiActionController {
	
	private static Log log = LogFactory.getLog(ImageTextController.class);
	
	private ImageTextService imageTextService;
	private MediaService mediaService;
	private MenuFunctionService menuFunctionService;
	
	public MenuFunctionService getMenuFunctionService() {
		return menuFunctionService;
	}
	public void setMenuFunctionService(MenuFunctionService menuFunctionService) {
		this.menuFunctionService = menuFunctionService;
	}
	
	public ImageTextService getImageTextService() {
		return imageTextService;	
	}

	public void setImageTextService(ImageTextService imageTextService) {
		this.imageTextService = imageTextService;
	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	/**
	 * 
	 * 查询图文信息
	 * 
	 */
	public ModelAndView qry(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			//获取请求的微信原始ID
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if("".equals(wechatid)){//id == null
				wechatid = userSession.getWechatId();//从session获取默认id
			}
			if(wechatid == null ){//
				return new ModelAndView("info", "message", "用户没有查看公共号信息权限");
			}
			String title = StringUtil.parseString(request.getParameter("title"));
			String startDate = StringUtil.parseString(request.getParameter("startDate"));
			String endDate = StringUtil.parseString(request.getParameter("endDate"));
			
			String defaultEndDate = CalendarUtil.getSysTimeYMD();
			String defaultStartDate = CalendarUtil.getFixedDate(defaultEndDate, -30);
			if (startDate.equals("") || endDate.equals("")) {
				endDate = defaultEndDate;
				startDate = defaultStartDate;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatid", wechatid);
			params.put("title", title);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			
			List list = imageTextService.qryResource(params);
			
			return new ModelAndView("manage/imageText_qry").addObject("list", list)
					.addObject("startDate", startDate).addObject("endDate", endDate);
		} catch (Exception e) {
			log.error("查询图文信息异常：" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}
	
	/**
	 * 
	 * 进入添加页面
	 * 
	 */
	public ModelAndView addPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			//获取请求的微信原始ID
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if("".equals(wechatid)){//id == null
				wechatid = userSession.getWechatId();//从session获取默认id
			}
			if(wechatid == null ){//
				return new ModelAndView("info", "message", "用户没有查看公共号信息权限");
			}
			Map<String, Object> resParams = new HashMap<String, Object>();
			resParams.put("wechatid", wechatid);
			resParams.put("OtherConditions", "multiresourceId is null");//不为多图文
			Map<String, Object> mediaParams = new HashMap<String, Object>();
			mediaParams.put("wechatid", wechatid);
			mediaParams.put("type", MediaType.IMAGE);
			List resList = imageTextService.qryResource(resParams);
			List mediaList = mediaService.qryMedia(mediaParams);
			return new ModelAndView("manage/imageText_add").addObject("resList", resList)
					.addObject("mediaList", mediaList);
		} catch (Exception e) {
			log.error("进入添加页面异常：" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}
	
	/**
	 * 
	 * 新增图文信息
	 * 
	 */
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		Resource resource = (Resource)GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {
			UserSession userSession  = (UserSession) request.getSession().getAttribute("userSession");
			
			String title = StringUtil.parseString(request.getParameter("title"));
			String cover = StringUtil.parseString(request.getParameter("cover"));
			String content = StringUtil.parseString(request.getParameter("content"));
			String outLink = StringUtil.parseString(request.getParameter("outLink"));
			String multiImageText = StringUtil.parseString(request.getParameter("multiImageTextInfo"));
			WechatResource wr = new WechatResource();
			wr.setId(UUID.randomUUID().toString());
			wr.setCreateDate(CalendarUtil.getSysTimeYMD());
			wr.setCreateTime(CalendarUtil.getSysTimeHMS());
			wr.setResourceTittle(title);
			wr.setResourceContent(content);
			wr.setMediaId(cover);
			wr.setOutLink(outLink);
			wr.setWechatId(userSession.getWechatId());
			wr.setMultiresourceId(multiImageText);
			int retCode = imageTextService.addImageText(wr);
			
			if (retCode != 0) {
				return new ModelAndView("info", "message", resource.srcStr(imageTextService.getRetMsg()));
			}
			
			return new ModelAndView("pageinfo_pagedecrease", "message", "新增图文信息成功")
				.addObject("url", "imageText.do?action=qry");
		} catch (Exception e) {
			log.error("新增图文信息失败：" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}
	
	/**
	 * 
	 * 修改图文信息
	 * 
	 */
	public ModelAndView modPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			//获取请求的微信原始ID
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if("".equals(wechatid)){
				wechatid = userSession.getWechatId();//从session获取管理微信id
			}
			if(wechatid == null ){
				return new ModelAndView("info", "message", "用户没有查看公共号信息权限");
			}
			String id = StringUtil.parseString(request.getParameter("id"));
			WechatResource wechatResource = imageTextService.qryById(id);
			WechatMedia originalMedia = mediaService.qryMediaById(wechatResource.getMediaId());
			Map<String, Object> resParams = new HashMap<String, Object>();
			resParams.put("wechatid", wechatid);
			resParams.put("OtherConditions", "multiresourceId is null");//不为多图文
			Map<String, Object> mediaParams = new HashMap<String, Object>();
			mediaParams.put("wechatid", wechatid);
			mediaParams.put("type", MediaType.IMAGE);
			List resList = imageTextService.qryResource(resParams);
			List mediaList = mediaService.qryMedia(mediaParams);
			return new ModelAndView("manage/imageText_mod")
					.addObject("wechatResource", wechatResource)
					.addObject("resList", resList)
					.addObject("mediaList", mediaList)
					.addObject("originalMedia",originalMedia);
		} catch (Exception e) {
			log.error("修改图文信息失败：" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}
	
	public ModelAndView mod(HttpServletRequest request, HttpServletResponse response) {
		Resource resource = (Resource)GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {
			UserSession userSession  = (UserSession) request.getSession().getAttribute("userSession");
			
			String title = StringUtil.parseString(request.getParameter("title"));
			String cover = StringUtil.parseString(request.getParameter("cover"));
			String content = StringUtil.parseString(request.getParameter("content"));
			String outLink = StringUtil.parseString(request.getParameter("outLink"));
			String multiImageText = StringUtil.parseString(request.getParameter("multiImageTextInfo"));
			String resid = StringUtil.parseString(request.getParameter("resid"));
			WechatResource wr = imageTextService.qryById(resid);
			if(wr == null){
				return new ModelAndView("info", "message", "该图文信息不存在!");
			}
			wr.setCreateDate(CalendarUtil.getSysTimeYMD());
			wr.setCreateTime(CalendarUtil.getSysTimeHMS());
			wr.setResourceTittle(title);
			wr.setResourceContent(content);
			wr.setMediaId(cover);
			wr.setOutLink(outLink);
			wr.setWechatId(userSession.getWechatId());
			wr.setMultiresourceId(multiImageText);
			int retCode = imageTextService.modImageText(wr);
			
			if (retCode != 0) {
				return new ModelAndView("info", "message", resource.srcStr(imageTextService.getRetMsg()));
			}
			
			return new ModelAndView("pageinfo_pagedecrease", "message", "修改图文信息成功")
				.addObject("url", "imageText.do?action=qry");
		} catch (Exception e) {
			log.error("修改图文信息失败：" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}
	
	/**
	 * 
	 * 删除图文信息
	 * 
	 */
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response) {
		Resource resource = (Resource)GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {
			
			String id = StringUtil.parseString(request.getParameter("id"));
			
			int retCode = imageTextService.delImageText(id);
			
			if (retCode != 0) {
				return new ModelAndView("info", "message", resource.srcStr(imageTextService.getRetMsg()));
			}
			
			return new ModelAndView("pageinfo_pagedecrease", "message", resource.srcStr("删除图文信息成功！"))
				.addObject("url", "imageText.do?action=qry");
		} catch (Exception e) {
			log.error("删除图文信息失败:" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}
	
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			// 获取请求的微信原始ID
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if ("".equals(wechatid)) {
				wechatid = userSession.getWechatId();// 从session获取管理微信id
			}
			if (wechatid == null) {
				return new ModelAndView("info", "message", "用户没有查看公共号信息权限");
			}
			String id = StringUtil.parseString(request.getParameter("id"));
			WechatResource wr = imageTextService.qryById(id);
			if (wr == null) {
				return new ModelAndView("info", "message", "图文不存在");
			}
			WechatMedia wm = mediaService.qryMediaById(wr.getMediaId());
			if (wr.getMultiresourceId() == null || wr.getMultiresourceId().equals("")) {
				return new ModelAndView("manage/imageText_detail").addObject("mainResource", wr)
						.addObject("isMulti", false).addObject("mainMedia", wm);
			} else {
				List<ImageTextDetail> resources = imageTextService.qryImageTextDetailByMultiResId(wr.getMultiresourceId());
				return new ModelAndView("manage/imageText_detail").addObject("mainResource", wr)
						.addObject("isMulti", true).addObject("resources", resources)
						.addObject("mainMedia", wm);
			}
		} catch (Exception e) {
			log.error("读取图文详细信息失败：" + e.getMessage(), e);
			return new ModelAndView("info", "message", e.getMessage());
		}
	}
	/**
	 * 
	 * 配置业务功能
	 * 
	 */
	public ModelAndView set(HttpServletRequest request,HttpServletResponse response){
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			// 获取请求的微信原始ID
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if ("".equals(wechatid)) {
				wechatid = userSession.getWechatId();// 从session获取管理微信id
			}
			if (wechatid == null) {
				return new ModelAndView("info", "message", "用户没有查看公共号信息权限");
			}
			String id = StringUtil.parseString(request.getParameter("id"));
			
			Map<String, Object> functionParams = new HashMap<String, Object>();
			functionParams.put("wechatId", wechatid);
			List<WechatFunction> wechatFunction =  menuFunctionService.qry(functionParams);
			WechatResource wechatResource = imageTextService.qryById(id);
			WechatMedia originalMedia = mediaService.qryMediaById(wechatResource.getMediaId());
			
			List<ImageTextDetail> resources = imageTextService.qryImageTextDetailByMultiResId(wechatResource.getMultiresourceId());
			String resFunction =""; 
			WechatArticle wechatArticle = imageTextService.qryArticleById(id);
			if(wechatArticle==null){
				log.info("未配置业务功能");
			}else{
				resFunction=wechatArticle.getFunction();
				log.info("已经配置业务功能："+resFunction);
			}
			
			
			return new ModelAndView("manage/imageText_set")
					.addObject("id",id)
					.addObject("resFunction",resFunction)
					.addObject("wechatResource", wechatResource)
					.addObject("wechatFunction",wechatFunction)
					.addObject("originalMedia",originalMedia)
					.addObject("resources",resources);
			
		} catch (Exception e) {
			log.error("打开配置业务功能失败："+e.getMessage(),e);
			return new ModelAndView("info","message",e.getMessage());
		}
	}
	
	/**
	 * 配置信息保存
	 */
	public ModelAndView up(HttpServletRequest request,HttpServletResponse response){
		Resource resource = (Resource)GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String id = StringUtil.parseString(request.getParameter("id"));
			
			String topTitle = StringUtil.parseString(request.getParameter("topTitle"));
			String title = StringUtil.parseString(request.getParameter("title"));
			String imageId = StringUtil.parseString(request.getParameter("imageId"));
			String outlink = StringUtil.parseString(request.getParameter("outlink"));
			String function = StringUtil.parseString(request.getParameter("function"));
			String creator = StringUtil.parseString(request.getParameter("creator"));
			String mediaPath = StringUtil.parseString(request.getParameter("mediaPath"));
			String describe = StringUtil.parseString(request.getParameter("describe"));
			
			
			WechatArticle wechatArticle = new WechatArticle();
			
			wechatArticle.setId(id);
			wechatArticle.setWechatId(userSession.getWechatId());
			wechatArticle.setFunction(function);
			wechatArticle.setType("title");
			wechatArticle.setArticleId(imageId);
			wechatArticle.setArticlePicurl(mediaPath);
			wechatArticle.setArticleUrl(outlink);
			wechatArticle.setArticleTitle(title);
			wechatArticle.setArticleDescribe(describe);
			wechatArticle.setCreateTime(CalendarUtil.getSysTimeYMDHMS());
			
			int retCode =imageTextService.updateArticle(wechatArticle);
			
			String multiresourceId = StringUtil.parseString(request.getParameter("multiresourceId"));
			
			try {
				List<ImageTextDetail> imageTextDetails=imageTextService.qryImageTextDetailByMultiResId(multiresourceId);
				for(int i = 0 ;i< imageTextDetails.size();i++){
					WechatArticle wechatArticle2 = new WechatArticle();
					WechatResource wechatResource=imageTextDetails.get(i).getResource();
					WechatMedia media=imageTextDetails.get(i).getMedia();
					
					wechatArticle2.setId(id+"|"+i);
					
					wechatArticle2.setWechatId(userSession.getWechatId());
					wechatArticle2.setFunction(function);
					wechatArticle2.setType("body");
					wechatArticle2.setArticleId(media.getId());
					wechatArticle2.setArticlePicurl(media.getMediaPath());
					wechatArticle2.setArticleUrl(wechatResource.getOutLink());
					wechatArticle2.setArticleTitle(wechatResource.getResourceContent());
					wechatArticle2.setArticleDescribe(media.getDescribe());
					wechatArticle2.setCreateTime(CalendarUtil.getSysTimeYMDHMS());
					
					retCode=imageTextService.updateArticle(wechatArticle2);
					if (retCode != 0) {
						return new ModelAndView("info", "message", resource.srcStr(imageTextService.getRetMsg()));
					}
				}
				
			} catch (Exception e2) {
				log.error("获取子图文信息失败"+e2.getMessage(),e2);
				return new ModelAndView("info","message",e2.getMessage());
			}
			
			if (retCode != 0) {
				return new ModelAndView("info", "message", resource.srcStr(imageTextService.getRetMsg()));
			}
			
			return new ModelAndView("pageinfo_pagedecrease", "message", "保存业务配置信息成功")
				.addObject("url", "imageText.do?action=qry");
		} catch (Exception e) {
			log.error("保存业务 配置信息失败："+e.getMessage(),e);
			return new ModelAndView("info","message",e.getMessage());
		}
	}
	
	
}