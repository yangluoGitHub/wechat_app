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
			String mobile = StringUtil.parseString(request.getParameter("uid"));              //手机号
			String passwd = StringUtil.parseString(request.getParameter("psw1"));                  //密码
			                    //创建者  
			
			MOpTableVO vo = new MOpTableVO();
			vo.setMobile(mobile);
			vo.setPasswd(passwd);
			
			try {
				this.getRegisterService().register(vo);
			    return new ModelAndView("demo.html","message","注册成功");
			} catch (Exception e) {
				  return new ModelAndView("info","message","注册失败");
			}
		} catch (Exception e) {
			log.error("注册异常："+ e.getMessage());
			 return new ModelAndView("info", "message", "注册异常!");
		}
	}

	
}
