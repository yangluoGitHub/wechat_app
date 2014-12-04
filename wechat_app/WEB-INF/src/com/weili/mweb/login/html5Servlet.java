package com.weili.mweb.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weili.wechat.common.HttpClientTools;
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
	private HttpClientTools httpClientTools = null;
	
	public HttpClientTools getHttpClientTools() {
		return httpClientTools;
	}

	public void setHttpClientTools(HttpClientTools httpClientTools) {
		this.httpClientTools = httpClientTools;
	}
	/**
	 * ����У�飨ȷ����������΢�ŷ�������
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException {
			  // TODO Auto-generated method stub
		        String code = request.getParameter("code");
		        System.out.print("code==" + code);
		        request.setAttribute("code", code);
		        
		        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxf9f85f73e85765db&secret=e3a29aaee0558faf27d026b188143a59&code="+code+"&grant_type=authorization_code";
		        String jsonStr = httpClientTools.doGet(requestUrl);
		        request.setAttribute("jsonStr", jsonStr);
		        
		        /*
		         * ����openId, select from M_OP_table �ж��Ƿ��Ѿ�ע��� ����Ѿ�ע��� ֱ���Զ���¼
		         * ���û�д�openId ��ת��ע��ҳ�棬 ע��ɹ�������ת����¼���档
		         */
		        
			  // ��ת��index.jsp
				 request.getRequestDispatcher("index1.jsp").forward(request, response);
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
}
