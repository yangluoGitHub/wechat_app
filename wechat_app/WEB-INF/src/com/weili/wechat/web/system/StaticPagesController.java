package com.weili.wechat.web.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class StaticPagesController extends MultiActionController {
	
	private static Log log=LogFactory.getLog(StaticPagesController.class);	

	/**
	 * 进入帮助下载页面
	 */
	public ModelAndView help(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/helpindex");		
	}	
	
	/**
	 * 进入下载CHM帮助下载页面
	 */
	public ModelAndView downloadChm(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/helpchm");		
	}
	
	/**
	 * 进入帮助DOC帮助下载页面
	 */
	public ModelAndView downloadDoc(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/helpdoc");		
	}	
	
	/**
	 * 进入atmHelp页面
	 */
	public ModelAndView atmHelp(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/atm_help");		
	}
	
	/**
	 * 进入atmHelp1页面
	 */
	public ModelAndView atmHelp1(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/atm_help1");		
	}
	
	/**
	 * 进入atmHelp2页面
	 */
	public ModelAndView atmHelp2(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/atm_help2");		
	}
	
	/**
	 * 进入atmHelp3页面
	 */
	public ModelAndView atmHelp3(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/atm_help3");		
	}	
	
	/**
	 * 进入atmHelp3页面
	 */
	public ModelAndView aboutShepherd(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/about_tauro");		
	}
	
	/**
	 * 进入atmHelp4页面
	 */
	public ModelAndView atmHelp4(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/atm_help4");		
	}
	
	/**
	 * 进入atmHelp5页面
	 */
	public ModelAndView atmHelp5(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/atm_help5");		
	}
	
	/**
	 * 进入下载根证书
	 */
	public ModelAndView downloadRootCer(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/root_cer");		
	}
	
	/**
	 * 多路复用安装包下载
	 */
	public ModelAndView download_GPRS_Sim900(HttpServletRequest request,HttpServletResponse response) {
		return new ModelAndView("help/GPRS_Sim900");
	}
	
}
