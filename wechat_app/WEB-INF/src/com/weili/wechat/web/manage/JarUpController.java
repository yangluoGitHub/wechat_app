package com.weili.wechat.web.manage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.extra.spath.SPathFilter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.File;

import com.weili.wechat.common.CalendarUtil;
import com.weili.wechat.common.FileUtil;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.Tool;
import com.weili.wechat.hibernate.JarTable;
import com.weili.wechat.service.manage.JarUpService;

public class JarUpController extends MultiActionController{
	private static Log log = LogFactory.getLog(JarUpController.class);
	
	private JarUpService jarUpService;

	public JarUpService getJarUpService() {
		return jarUpService;
	}
	
	public void setJarUpService(JarUpService jarUpService) {
		this.jarUpService = jarUpService;
	}
	
	
	public ModelAndView qry(HttpServletRequest request, HttpServletResponse response){
		try {
			String name = StringUtil.parseString(request.getParameter("name"));
			String startDate = StringUtil.parseString(request.getParameter("startDate"));
			String endDate = StringUtil.parseString(request.getParameter("endDate"));
			
			String defaultEndDate = CalendarUtil.getSysTimeYMD();
			String defaultStartDate = CalendarUtil.getFixedDate(defaultEndDate, -30);
			if (startDate.equals("") || endDate.equals("")) {
				endDate = defaultEndDate;
				startDate = defaultStartDate;
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", name);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			
			List jarList = this.getJarUpService().qry(params);
			
			ModelAndView mv = new ModelAndView("system/jar_qry")
										.addObject("jarList",jarList)
										.addObject("startDate",startDate)
										.addObject("endDate",endDate);
			
			return mv;
		} catch (Exception e) {
			log.error("打开异常："+e.getMessage());
			return new ModelAndView("info","message","打开异常！");
		}
	}
	
	public ModelAndView addPage(HttpServletRequest request,HttpServletResponse response){
		try {
			ModelAndView mv=new ModelAndView("system/jar_add");
			return mv;
		} catch (Exception e) {
			log.error("添加异常："+ e.getMessage());
			return new ModelAndView("info", "message", "添加异常!");
		}
	}
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response){
		try {
			String name = StringUtil.parseString(request.getParameter("name"));
			String sender = StringUtil.parseString(request.getParameter("sender"));
			String date =  StringUtil.parseString(Tool.getSysTimeYMDHMS());
			//数据库添加记录
			JarTable jarTable = new JarTable();
			jarTable.setName(name);
			jarTable.setUpSender(sender);
			jarTable.setUpDate(date);
			
			String id = UUID.randomUUID().toString();
			jarTable.setId(id);
			
			//上传jar包
			String applicationFilePath = request.getSession().getServletContext().getRealPath("/file/upclass/");
			String desDoc = applicationFilePath + System.getProperty("file.separator");
			
			StringBuffer descFileName = new StringBuffer(id);	
			try {
				this.jarUpService.addJar(jarTable);                                                         //数据库添加
				String retCode = FileUtil.upLoadHttpRequestFile(request, "jarFile", desDoc, descFileName);	//上传jar包
				if(!"0000".equals(retCode)) {
					return new ModelAndView("info","message","上传jar失败");
				}
			}  catch (Exception e) {
				e.printStackTrace();
				log.error("上传失败"+e.getMessage(),e);
				return new ModelAndView("info","message","上传jar失败");
			}	
			return new ModelAndView("pageinfo","message","上传jar成功！").addObject("menuURL","jarUp.do?action=qry");
		} catch (Exception e) {
			log.error("上传失败："+ e.getMessage(),e);
			return new ModelAndView("info","message","上传失败！");
		}
	}
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = StringUtil.parseString(request.getParameter("id"));
			JarTable jarTable = this.jarUpService.findById(id);
			
			if(jarTable == null)
			{
				throw new DataIntegrityViolationException("该jar包已经不存在");
			}
			//删除jar包文件
			String sep = System.getProperty("file.separator");
			String path = jarTable.getId()+".jar";
			String FilePath = request.getSession().getServletContext().getRealPath("/file/upclass/");
			
			String spath = FilePath +sep +path;
			File file = new File(FilePath,path);
			if(!file.exists()){
				throw new DataIntegrityViolationException("该jar包文件已经不存在");
			}else{
				file.delete();
				log.info("删除文件:"+spath+"成功!");
			}
			if (this.getJarUpService().delJar(jarTable) != 0 ) {
				return new ModelAndView("info", "message", this.getJarUpService().getRetMsg());
			}
			
			return new ModelAndView("pageinfo_pagedecrease", "message","删除成功").addObject("url", "jarUp.do?action=qry");
		
			
		} catch (Exception e) {
			log.error("删除失败："+ e.getMessage(),e);
			return new ModelAndView("info","message","删除失败！");
		}
		
	}
}
