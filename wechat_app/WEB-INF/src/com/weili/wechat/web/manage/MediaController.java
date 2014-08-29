package com.weili.wechat.web.manage;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.weili.wechat.common.CalendarUtil;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.hibernate.WechatMedia;
import com.weili.wechat.service.manage.MediaService;

public class MediaController extends MultiActionController {

	private static Log log = LogFactory.getLog(MediaController.class);
	private MediaService mediaService;

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	public ModelAndView qry(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if ("".equals(wechatid)) {
				wechatid = userSession.getWechatId();
			}
			if (wechatid == null) {
				return new ModelAndView("info", "message", "�û�û�в鿴��������ϢȨ��");
			}
			String mediaName = StringUtil.parseString(request.getParameter("mediaName"));
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
			params.put("mediaName", mediaName);
			params.put("startDate", startDate);
			params.put("endDate", endDate);

			List list = mediaService.qryMedia(params);

			return new ModelAndView("manage/media_qry").addObject("startDate", startDate).addObject("endDate", endDate).addObject("mediaName", mediaName)
					.addObject("list", list);
		} catch (Exception e) {
			log.error("��ѯ�쳣��" + e.getMessage());
			return new ModelAndView("info", "message", "��ѯ�쳣!");
		}
	}

	public ModelAndView addPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if ("".equals(wechatid)) {
				wechatid = userSession.getWechatId();
			}
			if (wechatid == null) {
				return new ModelAndView("info", "message", "�û�û�в鿴��������ϢȨ��");
			}
			return new ModelAndView("manage/media_add");

		} catch (Exception e) {
			log.error("����������Դҳ���쳣��" + e.getMessage());
			return new ModelAndView("info", "message", "����������Դҳ���쳣!");
		}
	}

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if ("".equals(wechatid)) {
				wechatid = userSession.getWechatId();
			}
			if (wechatid == null) {
				return new ModelAndView("info", "message", "�û�û�в鿴��������ϢȨ��");
			}
			String mediaType = StringUtil.parseString(request.getParameter("mediaType"));
			String mediaName = StringUtil.parseString(request.getParameter("mediaName"));
			Integer mediaResource = StringUtil.parseInteger2(request.getParameter("mediaResource"));
			String link = StringUtil.parseString(request.getParameter("link"));
			String describe = StringUtil.parseString(request.getParameter("describe"));
			String mediaPath;
			String mediaSuffix;
			String mediaState = "1";
			if (mediaResource == 0) {//�����ϴ�
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile file = multipartRequest.getFile("file");
				if (file == null) {
					return new ModelAndView("info", "message", "��ѡ��Ҫ�ϴ����ļ�!");
				}
				String fileOriginalName = file.getOriginalFilename();
				mediaPath = "upload/" + mediaType + "/" + fileOriginalName;
				mediaSuffix = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1);
				String storePath = request.getSession().getServletContext().getRealPath("upload/" + mediaType);
				File targetFile = new File(storePath, fileOriginalName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					file.transferTo(targetFile);
				} catch (Exception e) {
					log.error("�ϴ��ļ�ʧ�ܣ�" + e.getMessage());
					return new ModelAndView("info", "message", "�ϴ��ļ�ʧ��");
				}
			} else {//����
				mediaPath = link;
				mediaSuffix = link.substring(link.lastIndexOf(".") + 1);
			}
			WechatMedia media = new WechatMedia();
			media.setId(UUID.randomUUID().toString());
			media.setCreateDate(CalendarUtil.getSysTimeYMD());
			media.setCreateTime(CalendarUtil.getSysTimeHMS());
			media.setMediaName(mediaName);
			media.setCreator(userSession.getAccount());
			media.setDescribe(describe);
			media.setMediaPath(mediaPath);
			media.setMediaSuffix(mediaSuffix);
			media.setMediaState(mediaState);
			media.setWechatId(wechatid);
			media.setMediaType(mediaType);
			media.setMediaResource(mediaResource);
			int retVal = mediaService.addMedia(media);
			if (retVal < 0) {
				return new ModelAndView("info", "message", "������Դʧ��");
			}
			return new ModelAndView("pageinfo_pagedecrease", "message", "������Դ�ɹ�")
			.addObject("url", "media.do?action=qry");
		} catch (Exception e) {
			log.error("����쳣��" + e.getMessage());
			return new ModelAndView("info", "message", "������Դ�쳣!");
		}
	}
	
	public ModelAndView modPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if ("".equals(wechatid)) {
				wechatid = userSession.getWechatId();
			}
			if (wechatid == null) {
				return new ModelAndView("info", "message", "�û�û�в鿴��������ϢȨ��");
			}
			String id = StringUtil.parseString(request.getParameter("id"));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatid", wechatid);
			params.put("id", id);
			WechatMedia media =  (WechatMedia) mediaService.qryMedia(params).get(0);
			if(media == null){
				return new ModelAndView("info", "message", "��Դ������");
			}
			return new ModelAndView("manage/media_mod").addObject("media",media);
		} catch (Exception e) {
			log.error("�����޸���Դҳ���쳣��" + e.getMessage());
			return new ModelAndView("info", "message", "�����޸���Դҳ���쳣!");
		}
	}
	
	
	public ModelAndView mod(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if ("".equals(wechatid)) {
				wechatid = userSession.getWechatId();
			}
			if (wechatid == null) {
				return new ModelAndView("info", "message", "�û�û�в鿴��������ϢȨ��");
			}
			String id = StringUtil.parseString(request.getParameter("id"));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatid", wechatid);
			params.put("id", id);
			WechatMedia media =  (WechatMedia) mediaService.qryMedia(params).get(0);
			if(media == null){
				return new ModelAndView("info", "message", "��Դ������");
			}
			String mediaName = StringUtil.parseString(request.getParameter("mediaName"));
			String describe = StringUtil.parseString(request.getParameter("describe"));
			media.setMediaName(mediaName);
			media.setDescribe(describe);
			media.setCreateDate(CalendarUtil.getSysTimeYMD());
			media.setCreateTime(CalendarUtil.getSysTimeHMS());
			media.setCreator(userSession.getAccount());
			int retCode = mediaService.modMedia(media);
			if (retCode != 0) {
				return new ModelAndView("info", "message", "�޸���Դʧ��");
			}
			return new ModelAndView("pageinfo_pagedecrease", "message", "�޸���Դ�ɹ�")
			.addObject("url", "media.do?action=qry");
		} catch (Exception e) {
			log.error("�޸���Դ�쳣��" + e.getMessage());
			return new ModelAndView("info", "message", "�޸���Դ�쳣!");
		}
	}

	public ModelAndView del(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if ("".equals(wechatid)) {
				wechatid = userSession.getWechatId();
			}
			if (wechatid == null) {
				return new ModelAndView("info", "message", "�û�û�в鿴��������ϢȨ��");
			}
			String id = StringUtil.parseString(request.getParameter("id"));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatid", wechatid);
			params.put("id", id);
			WechatMedia media =  (WechatMedia) mediaService.qryMedia(params).get(0);
			if(media == null){
				return new ModelAndView("info", "message", "��Դ������");
			}
			//����ɾ��
			String storePath = request.getSession().getServletContext().getRealPath(media.getMediaPath());
			File file = new File(storePath);
			if (!file.exists()) {
				log.error("�ļ�:" + media.getMediaPath() + "������");
			}
			if (!file.delete()) {
				log.error("�ļ�:" + media.getMediaPath() + "ɾ��ʧ��");
			}
			int retCode = mediaService.delMedia(media);
			if (retCode != 0) {
				return new ModelAndView("info", "message", "ɾ����Դʧ��");
			}
			return new ModelAndView("pageinfo_pagedecrease", "message", "ɾ����Դ�ɹ�")
				.addObject("url", "media.do?action=qry");
		} catch (Exception e) {
			log.error("ɾ����Դ�쳣��" + e.getMessage());
			return new ModelAndView("info", "message", "ɾ����Դ�쳣!");
		}
	}
	
	public ModelAndView previewImage(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			String wechatid = StringUtil.parseString(request.getParameter("wechatid"));
			if ("".equals(wechatid)) {
				wechatid = userSession.getWechatId();
			}
			if (wechatid == null) {
				return new ModelAndView("info_close", "message", "�û�û�в鿴��������ϢȨ��");
			}
			String id = StringUtil.parseString(request.getParameter("id"));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatid", wechatid);
			params.put("id", id);
			WechatMedia media =  (WechatMedia) mediaService.qryMedia(params).get(0);
			if(media == null){
				return new ModelAndView("info_close", "message", "��Դ������");
			}
			if (media.getMediaResource() == 0) {
				String storePath = request.getSession().getServletContext().getRealPath(media.getMediaPath());
				File file = new File(storePath);
				if (!file.exists()) {
					return new ModelAndView("info_close", "message", "����ͼƬ������");
				}
			}
			return new ModelAndView("manage/media_previewImage")
					.addObject("media", media);
		} catch (Exception e) {
			log.error("Ԥ��ͼƬ�쳣��" + e.getMessage());
			return new ModelAndView("info_close", "message", "Ԥ��ͼƬ�쳣!");
		}
	}
}