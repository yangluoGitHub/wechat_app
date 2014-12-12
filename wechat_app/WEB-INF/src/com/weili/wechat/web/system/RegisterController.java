package com.weili.wechat.web.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.weili.wechat.common.StringUtil;
import com.weili.wechat.service.system.RegisterService;
import com.weili.wechat.vo.MOpTableVO;



public class RegisterController extends MultiActionController{
	private static Log log = LogFactory.getLog(RegisterController.class);
	private RegisterService registerService;
	
	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response){
		try {
			String username = StringUtil.parseString(request.getParameter("username"));              //手机号
			String passwd = StringUtil.parseString(request.getParameter("password"));                  //密码
			                    //创建者  
			
			//String openId = StringUtil.parseString(request.getParameter("openId"));
			String mobile = "15961859732";
			String openId = "oHNuyt0fVIRCZEh3Xc19zal91rOA";
			MOpTableVO vo = new MOpTableVO();
			vo.setMobile(mobile);
			vo.setName(username);
			vo.setPasswd(passwd);
			vo.setOpenId(openId);
			
			try {
				this.getRegisterService().register(vo);
			    return new ModelAndView("/demo3/login.jsp","message","注册成功").addObject("username", username);
			} catch (Exception e) {
				  return new ModelAndView("info","message","注册失败");
			}
		} catch (Exception e) {
			log.error("注册异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "注册异常!");
		}
	}
	public ModelAndView wechatLogin(HttpServletRequest request, HttpServletResponse response){
		try {
			String username = StringUtil.parseString(request.getParameter("username"));              //手机号
			String passwd = StringUtil.parseString(request.getParameter("password"));                  //密码
			                    //创建者  
			
			//String openId = StringUtil.parseString(request.getParameter("openId"));
			
			MOpTableVO vo = new MOpTableVO();
			vo.setName(username);
			vo.setPasswd(passwd);
			
			try {
				int ret = this.getRegisterService().isRegisterUser(vo);
			    return new ModelAndView("/demo3/success.jsp","message","成功");
			} catch (Exception e) {
				  return new ModelAndView("info","message","登录失败");
			}
		} catch (Exception e) {
			log.error("登录异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "登录异常!");
		}
	}

	
}
