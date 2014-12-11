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
 * 请求处理的核心类
 * 
 * @author liufeng
 * @date 2013-09-29
 */
public class html5Servlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;
	private HttpClientTools httpClientTools = new HttpClientTools();
	private static Log log = LogFactory.getLog(html5Servlet.class);
	
	/**
	 * 请求校验（确认请求来自微信服务器）
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
		         * 解析openId, select from M_OP_table 判断是否已经注册过 如果已经注册过 直接自动登录
		         * 如果没有此openId 跳转到注册页面， 注册成功后在跳转到登录界面。
		         */
		        if(list != null && list.size() > 0) {
		        	// 跳转到index.jsp
					 request.getRequestDispatcher("MyJsp.jsp").forward(request, response);
		        	
		        }else{//跳转到注册页面
		        	request.getRequestDispatcher("/demo/demo.html").forward(request, response);
		        }
		        
			  
			 }
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 微信加密签名
//		String signature = request.getParameter("signature");
//		// 时间戳
//		String timestamp = request.getParameter("timestamp");
//		// 随机数
//		String nonce = request.getParameter("nonce");
//		// 随机字符串
//		String echostr = request.getParameter("echostr");
//
//		PrintWriter out = response.getWriter();
//		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
//		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
//			out.print(echostr);
//		}
//		out.close();
//		out = null;
//	}

	/**
	 * 请求校验与处理
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 接收参数微信加密签名、 时间戳、随机数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		PrintWriter out = response.getWriter();
		// 请求校验
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			// 调用核心服务类接收处理请求
			String respXml = CoreService.processRequest(request);
			out.print(respXml);
		}
		out.close();
		out = null;
	}

//	@Override
//	public void init() throws ServletException {
//		File indexDir = new File(ChatService.getIndexDir());
//		// 如果索引目录不存在则创建索引
//		if (!indexDir.exists())
//			ChatService.createIndex();
//	}
	
	/**
	 * TODO 根据微信服务器返回的Json字符串，判断请求是否被成功处理。
	 * @author adli 2014-3-25 上午11:33:11
	 * @param jsonString 微信服务器返回的Json字符串。
	 * @return  请求是否被成功处理。
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
		log.error("判断json返回串出错！", e);
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
