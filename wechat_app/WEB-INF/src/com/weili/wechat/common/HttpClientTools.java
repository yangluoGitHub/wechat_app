package com.weili.wechat.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjft.wechat.vo.WechatMenuItem;


/**
 * @author adli 2014-3-14 下午5:04:37
 * TODO HttpClient工具类
 */
public final class HttpClientTools {
	
	private CloseableHttpClient httpClient=null;
	
	private static Logger logger = LoggerFactory.getLogger(HttpClientTools.class);
	
	/**
	 * TODO 初始化HttpClient实例
	 * @author adli 2014-3-24 下午4:42:35
	 */
	public void init()
	{
		initHttpClient();
	}
	
	/**
	 * TODO 销毁HttpClient实例类，释放相应系统资源。 在系统退出时调用此方法
	 * @author adli 2014-3-24 下午4:39:49
	 */
	public void destroy()
	{
		shutdownHttpClient();
	}
	
	/**
	 * TODO 初始化CloseableHttpClient  支持双http和https方案。
	 * @author adli 2014-3-24 下午4:43:05
	 */
	private void initHttpClient() {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
		.register("http", plainsf)
		.register("https", sslsf)
		.build();
		
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		// Increase max total connection to 200
		cm.setMaxTotal(20);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(5);
		// Increase max connections for localhost:80 to 50
		HttpHost tencentHost = new HttpHost("api.weixin.qq.com", 80);
		cm.setMaxPerRoute(new HttpRoute(tencentHost), 10);
		this.httpClient = HttpClients.custom()
		.setConnectionManager(cm)
		.build();
	}
	
	
	/**
	 * TODO 关闭CloseableHttpClient
	 * @author adli 2014-3-24 下午4:44:29
	 */
	private void shutdownHttpClient()
	{
		try {
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("httpClient关闭失败！", e);
		}
	}
	
	
	
	/**
	 * TODO
	 * @author adli 2014-3-24 下午4:45:34
	 * @param url 请求URL字符串 包括方案、请求参数等
	 * @return 以字符串形式返回的http响应
	 */
	public String doGet(String url) {
		String ret=null;
		  try {
			  HttpContext context = HttpClientContext.create();
			  HttpGet httpget = new HttpGet(url);
              CloseableHttpResponse response = httpClient.execute(
                      httpget, context);
              try {
                  HttpEntity entity = response.getEntity();
                  ret=EntityUtils.toString(entity, "UTF-8");
                  logger.info("http响应详情：");
                  logger.info("content-type"+String.valueOf(entity.getContentType()));
                  logger.info("content-length"+String.valueOf(entity.getContentLength()));
                  logger.info("content-encoding"+String.valueOf(entity.getContentEncoding()));
                  logger.info(ret);
              } finally {
                  response.close();
              }
          } catch (ClientProtocolException ex) {
             logger.error("httpGet请求处理出错！", ex);
          } catch (IOException ex) {
        	 logger.error("httpGet请求处理出错！", ex);
          }
		return ret;

	}
	
	/**
	 * TODO 处理post请求并发回数据
	 * @author adli 2014-3-24 下午5:15:51
	 * @param url  请求URL字符串 包括方案、请求参数等
	 * @param content 实体主体
	 * @return  以字符串形式返回的http响应
	 */
	public String doPost(String url,String content) {
		String ret=null;
		  try {
			  HttpContext context = HttpClientContext.create();
			  HttpPost  httppost = new HttpPost(url);
			  HttpEntity entity=new StringEntity(content, "UTF-8");
			  httppost.setEntity(entity);
              CloseableHttpResponse response = httpClient.execute(
            		  httppost, context);
              
              try {
                  HttpEntity ResEntity = response.getEntity();
                   ret=EntityUtils.toString(ResEntity, "UTF-8");
                   logger.info("http响应详情：");
                   logger.info("content-type"+String.valueOf(entity.getContentType()));
                   logger.info("content-length"+String.valueOf(entity.getContentLength()));
                   logger.info("content-encoding"+String.valueOf(entity.getContentEncoding()));
                   logger.info(ret);
              } finally {
                  response.close();
              }
          } catch (ClientProtocolException ex) {
        	  logger.error("httpGet请求处理出错！", ex);
          } catch (IOException ex) {
        	  logger.error("httpGet请求处理出错！", ex);
          }
		return ret;

	}
	
	/**
	 * TODO 多媒体文件上传
	 * @author adli 2014-3-24 下午5:29:17
	 * @param content 文件二进制内容
	 * @return
	 * @throws Exception 
	 */
//	public String uploadFile(String url,byte[] content,MediaType mediaType) throws Exception {
//		String ret=null;
//		if (content.length>mediaType.getLength()) {
//			throw new Exception("文件长度超出限制！文件实际长度为："+content.length+" 最大允许长度为："+mediaType.getLength());
//		}
//		  try {
//			  HttpContext context = HttpClientContext.create();
//			  HttpPost  httppost = new HttpPost (url);
//			  @SuppressWarnings("deprecation")
//			ByteArrayBody body=new ByteArrayBody(content, mediaType.getMime().getMine(), "temp."+mediaType.getMime().getExtension());
//			  HttpEntity reqEntity = MultipartEntityBuilder.create()
//	                    .addPart("body", body)
//	                    .build();
//			  httppost.setEntity(reqEntity);
//            CloseableHttpResponse response = httpClient.execute(
//            		httppost, context);
//            try {
//                HttpEntity entity = response.getEntity();
//                 ret=EntityUtils.toString(entity, "UTF-8");
//                 EntityUtils.consume(entity);
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException ex) {
//            // Handle protocol errors
//        	logger.error("httpPost请求处理出错！", ex);
//        } catch (IOException ex) {
//            // Handle I/O errors
//        	logger.error("httpPost请求处理出错！", ex);
//        }
//		return ret;
//	}
	
	public byte[] downloadFile(String url)
	{
		
			byte[] ret=null;
	  try {
		  HttpContext context = HttpClientContext.create();
		  HttpGet httpget = new HttpGet(url);

      CloseableHttpResponse response = httpClient.execute(
    		  httpget, context);
      try {
          HttpEntity entity = response.getEntity();
           ret=EntityUtils.toByteArray(entity);
           EntityUtils.consume(entity);
      } finally {
          response.close();
      }
  } catch (ClientProtocolException ex) {
      // Handle protocol errors
	  	logger.error("文件下载出错！", ex);
  } catch (IOException ex) {
      // Handle I/O errors
	  	logger.error("文件下载出错！", ex);
  }
	return ret;
	}
	/**
	 * TODO 使用Http协议上传文件到指定URL
	 * @author adli 2014-3-14 下午5:07:41
	 * @param targetURL 文件上传目标URL
	 * @param srcFilePath 文件系统本地路径
	 * @return
	 */
	public static boolean uploadFile(String targetURL,String srcFilePath) {
		/*   File srcFile = null;// TODO 指定上传文件
		  
		   srcFile = new File(srcFilePath);
		   
		   PostMethod filePost = new PostMethod(targetURL);  //若没有commons-codec-1.4-bin.zip, 这里将会出错
		   try
		   {

		    //通过以下方法可以模拟页面参数提交
		    //filePost.setParameter("name", "中文");
		    //filePost.setParameter("pass", "1234");

		   Part[] parts = { new FilePart(srcFile.getName(), srcFile) };
		    filePost.setRequestEntity(new MultipartRequestEntity(parts,filePost.getParams()));
		    HttpClient client = new HttpClient();
		    client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		    int status = client.executeMethod(filePost);
		    if (status == HttpStatus.SC_OK)
		    {
		     logger.info("上传成功！");
		     return true;
		    }
		    else
		    {
		     logger.info("上传失败！"); 
		     return false;
		    }
		   }
		   catch (Exception ex)
		   {
		    logger.error("http上传文件失败！", ex);
		   }
		   finally
		   {
		    filePost.releaseConnection();
		   } 

	
*/
		   return false;
	}
	/**
	 * TODO
	 * @author adli 2014-3-14 下午5:04:37
	 * @param args
	 */
	public static void main(String[] args) {


//		test1();
           

	}
	
	public static void test2() {
		HttpClientTools utils=new HttpClientTools();
        utils.init();
        InputStream inputStream=null;
		try {
			inputStream=new FileInputStream("C:/照片/add.txt");
			byte[] temp=new byte[8196];
			int length=inputStream.read(temp);
			String content=new String(temp, 0, length, "UTF-8");
			String retString= utils.doPost("http://localhost:8080/wechat_web/haha/wechat.do?action=dispatcher&sid=gh_217235c45d02", content);
			System.out.println(retString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
         
         utils.destroy();
	}
	
//	public static void test1() {
//     //{"button":[{"name":"微信银行","sub_button":[{"name":"网页凭条","type":"view","url":"http://61.155.108.146/wechat/receipt.do?action=handle"}]}]}
//		HttpClientTools httpClientTools=new HttpClientTools();
//		httpClientTools.init();
//        String requestStr=URLTools.AccessToken_URL("wxa388b7e078944af0", "d1ce81721e4b0649da8ab1d3f8f641b5");
//		String jsonStr=httpClientTools.doGet(requestStr);
//		JSONObject object = JSON.parseObject(jsonStr);
//		String accessTokenString=null;
//		if(object.getString("errcode")!=null)
//		{
//			System.out.println("加载accessToken出错！");
//		}
//		else {
//			accessTokenString=object.getString("access_token");
//		}
//		System.out.println(accessTokenString);
//		requestStr = URLTools.ApplyMenu_URL(accessTokenString);
//		List< WechatMenuItem> items=new ArrayList<WechatMenuItem>();
//		items.add(new WechatMenuItem("1", "微信银行", "你好", "", 2, 1, null));
//		items.add(new WechatMenuItem("2", "网页凭条内网", "http://10.2.72.201/wechat/receipt.do?action=handle", "view", 3, 1, "1"));
//		items.add(new WechatMenuItem("3", "ATM凭条", "29594c86-c34a-40d6-83f2-2de861a4ab4d", "click", 3, 2, "1"));
//		items.add(new WechatMenuItem("4", "卡绑定", "555eb686-274a-4418-adcf-8a9675e66214", "click", 3, 3, "1"));
//		items.add(new WechatMenuItem("5", "查询余额", "f1d47026-5d1c-40e2-8a82-a58e67179c10", "click", 3, 4, "1"));
//		
//		
//		String content=JSON.toJSONString(Menu.generateMenu(items));
//		jsonStr = httpClientTools.doPost(requestStr, content);
//		if (WechatTools.IsSuccess(jsonStr)) {
//			System.out.println("应用成功");
//		} else {
//
//			System.out.println("应用失败");
//		}
//	}

}
