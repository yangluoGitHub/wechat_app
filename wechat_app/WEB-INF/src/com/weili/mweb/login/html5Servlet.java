package com.weili.mweb.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.HttpClientTools;
import com.weili.wechat.vo.MOpTableVO;
import com.weili.wechatCom.service.CoreService;
import com.weili.wechatCom.util.SignUtil;

/**
 * ������ĺ�����
 * 
 * @author liufeng
 * @date 2013-09-29
 */
public class html5Servlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;
	private HttpClientTools httpClientTools = new HttpClientTools();
	private static Log log = LogFactory.getLog(html5Servlet.class);
	
	/**
	 * ����У�飨ȷ����������΢�ŷ�������
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException {
			  // TODO Auto-generated method stub
//		        String code = request.getParameter("code");
//		        log.info("20141205code==" + code);
//		        request.setAttribute("code", code);
//		        httpClientTools.init();
//		        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxf9f85f73e85765db&secret=e3a29aaee0558faf27d026b188143a59&code="+code+"&grant_type=authorization_code";
//		        log.info("20141205requestUrl==" + requestUrl);
//		        String jsonStr = httpClientTools.doGet(requestUrl);
//		        request.setAttribute("jsonStr", jsonStr);
//		        log.info("20141205jsonStr==" + jsonStr);
//		        
//		        
//		        String openId = "";
//		        //if (IsSuccess(jsonStr)) {
//		        	JSONObject object = JSONObject.fromObject(jsonStr);
//					openId = object.getString("openid");
//				//}
//		        log.info("20141205openId==" + openId);
		        //String openId = "oHNuyt0fVIRCZEh3Xc19zal91rOA";
		        String openId = "oHNuyt0fVIRCZEh3Xc19zal91rOA1";
		        request.setAttribute("openId", openId);
		        List<MOpTableVO> list = MySQLUtil1.getUserByOpenId(openId);
		        
		        /*
		         * ����openId, select from M_OP_table �ж��Ƿ��Ѿ�ע��� ����Ѿ�ע��� ֱ���Զ���¼
		         * ���û�д�openId ��ת��ע��ҳ�棬 ע��ɹ�������ת����¼���档
		         */
		        if(list != null && list.size() > 0) {
		        	// ��ת��index.jsp
					 request.getRequestDispatcher("MyJsp.jsp").forward(request, response);
		        	
		        }else{//��ת��ע��ҳ��
		        	request.getRequestDispatcher("/demo/demo.html").forward(request, response);
		        }
		        
			  
			 }
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// ΢�ż���ǩ��
//		String signature = request.getParameter("signature");
//		// ʱ���
//		String timestamp = request.getParameter("timestamp");
//		// �����
//		String nonce = request.getParameter("nonce");
//		// ����ַ���
//		String echostr = request.getParameter("echostr");
//
//		PrintWriter out = response.getWriter();
//		// ����У�飬��У��ɹ���ԭ������echostr����ʾ����ɹ����������ʧ��
//		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
//			out.print(echostr);
//		}
//		out.close();
//		out = null;
//	}

	/**
	 * ����У���봦��
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// ���ղ���΢�ż���ǩ���� ʱ����������
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		PrintWriter out = response.getWriter();
		// ����У��
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			// ���ú��ķ�������մ�������
			String respXml = CoreService.processRequest(request);
			out.print(respXml);
		}
		out.close();
		out = null;
	}

//	@Override
//	public void init() throws ServletException {
//		File indexDir = new File(ChatService.getIndexDir());
//		// �������Ŀ¼�������򴴽�����
//		if (!indexDir.exists())
//			ChatService.createIndex();
//	}
	
	/**
	 * TODO ����΢�ŷ��������ص�Json�ַ������ж������Ƿ񱻳ɹ�����
	 * @author adli 2014-3-25 ����11:33:11
	 * @param jsonString ΢�ŷ��������ص�Json�ַ�����
	 * @return  �����Ƿ񱻳ɹ�����
	 */
	public static boolean IsSuccess(String jsonString)
	{
		try {
			JSONObject object = JSONObject.fromObject(jsonString);
			String errCode=object.getString("errcode");
			if (errCode==null||"0".equals(errCode)) {
				return true;
			}
			else {
				return false;
			}
	} catch (Exception e) {
		log.error("�ж�json���ش�����", e);
		return false;
	}
		
	}
	
	public static void main(String[] args) {
		
		String jsonStr = "{\"access_token\":\"OezXcEiiBSKSxW0eoylIeAGe5WAmHVcHxC4puwv-Mnce7dh-xzFJZgBkCAgxz55ksrZJUi8K0J3XU9Op09OK9l7dh9JNWCcs_uCB2uCCTld3ccokMeov4y7A9qimU6-3_MKc3ak5q4vfFUA-XjEOCw\",\"expires_in\":7200,\"refresh_token\":\"OezXcEiiBSKSxW0eoylIeAGe5WAmHVcHxC4puwv-Mnce7dh-xzFJZgBkCAgxz55klba1IcwKIm0m3NNpvHelBE4ax1e68gaYVmW0u3cQaM-NSW8Gzi97Ptrkx53gxacUO_ybRUSJNZMZUWYwNK-z7A\",\"openid\":\"oHNuyt0fVIRCZEh3Xc19zal91rOA\",\"scope\":\"snsapi_base\"}";
		String openId = "";
       // if (IsSuccess(jsonStr)) {
        	JSONObject object = JSONObject.fromObject(jsonStr);
			openId = object.getString("openid");
		//}
	}
	
}
