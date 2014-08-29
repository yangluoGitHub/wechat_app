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
	 * �����������ҳ��
	 */
	public ModelAndView help(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/helpindex");		
	}	
	
	/**
	 * ��������CHM��������ҳ��
	 */
	public ModelAndView downloadChm(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/helpchm");		
	}
	
	/**
	 * �������DOC��������ҳ��
	 */
	public ModelAndView downloadDoc(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/helpdoc");		
	}	
	
	/**
	 * ����atmHelpҳ��
	 */
	public ModelAndView atmHelp(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/atm_help");		
	}
	
	/**
	 * ����atmHelp1ҳ��
	 */
	public ModelAndView atmHelp1(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/atm_help1");		
	}
	
	/**
	 * ����atmHelp2ҳ��
	 */
	public ModelAndView atmHelp2(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/atm_help2");		
	}
	
	/**
	 * ����atmHelp3ҳ��
	 */
	public ModelAndView atmHelp3(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/atm_help3");		
	}	
	
	/**
	 * ����atmHelp3ҳ��
	 */
	public ModelAndView aboutShepherd(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/about_tauro");		
	}
	
	/**
	 * ����atmHelp4ҳ��
	 */
	public ModelAndView atmHelp4(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/atm_help4");		
	}
	
	/**
	 * ����atmHelp5ҳ��
	 */
	public ModelAndView atmHelp5(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/atm_help5");		
	}
	
	/**
	 * �������ظ�֤��
	 */
	public ModelAndView downloadRootCer(HttpServletRequest request,HttpServletResponse response) {	
		return new ModelAndView("help/root_cer");		
	}
	
	/**
	 * ��·���ð�װ������
	 */
	public ModelAndView download_GPRS_Sim900(HttpServletRequest request,HttpServletResponse response) {
		return new ModelAndView("help/GPRS_Sim900");
	}
	
}
