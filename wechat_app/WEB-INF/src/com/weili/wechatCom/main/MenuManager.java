package com.weili.wechatCom.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weili.wechatCom.menu.ButtonAPI;
import com.weili.wechatCom.menu.ClickButtonAPI;
import com.weili.wechatCom.menu.ComplexButtonAPI;
import com.weili.wechatCom.menu.MenuAPI;
import com.weili.wechatCom.menu.ViewButtonAPI;
import com.weili.wechatCom.pojo.Token;
import com.weili.wechatCom.util.CommonUtil;
import com.weili.wechatCom.util.MenuUtil;

/**
 * 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-10-17
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	private static MenuAPI getMenu() {
		ClickButtonAPI btn11 = new ClickButtonAPI();
		btn11.setName("罗阳");
		btn11.setType("click");
		btn11.setKey("oschina");

		ClickButtonAPI btn12 = new ClickButtonAPI();
		btn12.setName("ITeye");
		btn12.setType("click");
		btn12.setKey("iteye");

		ViewButtonAPI btn13 = new ViewButtonAPI();
		btn13.setName("CocoaChina");
		btn13.setType("view");
		btn13.setUrl("http://www.iteye.com");

		ViewButtonAPI btn21 = new ViewButtonAPI();
		btn21.setName("淘宝");
		btn21.setType("view");
		btn21.setUrl("http://m.taobao.com");

		ViewButtonAPI btn22 = new ViewButtonAPI();
		btn22.setName("京东");
		btn22.setType("view");
		btn22.setUrl("http://m.jd.com");

		ViewButtonAPI btn23 = new ViewButtonAPI();
		btn23.setName("唯品会");
		btn23.setType("view");
		btn23.setUrl("http://m.vipshop.com");

		ViewButtonAPI btn24 = new ViewButtonAPI();
		btn24.setName("当当网");
		btn24.setType("view");
		btn24.setUrl("http://m.dangdang.com");

		ViewButtonAPI btn25 = new ViewButtonAPI();
		btn25.setName("苏宁易购");
		btn25.setType("view");
		btn25.setUrl("http://m.suning.com");

		ViewButtonAPI btn31 = new ViewButtonAPI();
		btn31.setName("多泡");
		btn31.setType("view");
		btn31.setUrl("http://www.duopao.com");

		ViewButtonAPI btn32 = new ViewButtonAPI();
		btn32.setName("一窝88");
		btn32.setType("view");
		btn32.setUrl("http://www.yi588.com");

		ComplexButtonAPI mainBtn1 = new ComplexButtonAPI();
		mainBtn1.setName("技术交流");
		mainBtn1.setSub_button(new ButtonAPI[] { btn11, btn12, btn13 });
        ButtonAPI[] aaa = new ButtonAPI[1];
        aaa[0]=btn11;
//        aaa[1]=btn12;	
//        mainBtn1.setSub_button(aaa);
		ComplexButtonAPI mainBtn2 = new ComplexButtonAPI();
		mainBtn2.setName("购物");
		mainBtn2.setSub_button(new ButtonAPI[] { btn21, btn22, btn23, btn24, btn25 });

		ComplexButtonAPI mainBtn3 = new ComplexButtonAPI();
		mainBtn3.setName("网页游戏");
		mainBtn3.setSub_button(new ButtonAPI[] { btn31, btn32 });

		MenuAPI menu = new MenuAPI();
		menu.setButton(new ButtonAPI[] { btn11, mainBtn2, mainBtn3 });

		return menu;
	}
	
    
	public static boolean deployMenuBtn(MenuAPI menuapi){
		
		// 第三方用户唯一凭证
				String appId = "wxb4653e19ffb108ff";
				// 第三方用户唯一凭证密钥
				String appSecret = "70f901c2390ad67d69f0d6011ad83880";

				// 调用接口获取凭证
				Token token = CommonUtil.getToken(appId, appSecret);

				if (null != token) {
					// 创建菜单
					boolean result = MenuUtil.createMenu(menuapi, token.getAccessToken());

					// 判断菜单创建结果
					if (result)
						log.info("菜单创建成功！");
					else
						log.info("菜单创建失败！");
					return result;
				}
				return false;
				
		
	}
	
	public static void main(String[] args) {
		// 第三方用户唯一凭证
//		String appId = "wxb4653e19ffb108ff";
//		// 第三方用户唯一凭证密钥
//		String appSecret = "70f901c2390ad67d69f0d6011ad83880";
//
//		// 调用接口获取凭证
//		Token token = CommonUtil.getToken(appId, appSecret);
//
//		if (null != token) {
//			// 创建菜单
//			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());
//
//			// 判断菜单创建结果
//			if (result)
//				log.info("菜单创建成功！");
//			else
//				log.info("菜单创建失败！");
//		}
		
		String aa = "6119a175-dcbf-4630-bf3d-6bde56d8107c";
		String aaa= aa.split("\\|")[1];
		System.out.println(aaa);
		
		
	}
}
