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
			String mobile = StringUtil.parseString(request.getParameter("uid"));              //�ֻ���
			String passwd = StringUtil.parseString(request.getParameter("psw1"));                  //����
			                    //������  
			
			MOpTableVO vo = new MOpTableVO();
			vo.setMobile(mobile);
			vo.setPasswd(passwd);
			
			try {
				this.getRegisterService().register(vo);
			    return new ModelAndView("demo.html","message","ע��ɹ�");
			} catch (Exception e) {
				  return new ModelAndView("info","message","ע��ʧ��");
			}
		} catch (Exception e) {
			log.error("ע���쳣��"+ e.getMessage());
			 return new ModelAndView("info", "message", "ע���쳣!");
		}
	}

	
}
