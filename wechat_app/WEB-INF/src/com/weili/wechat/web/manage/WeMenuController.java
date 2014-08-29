package com.weili.wechat.web.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.weili.wechat.common.CalendarUtil;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.service.manage.WeMenuService;
import com.weili.wechat.vo.WechatMenuItemVO;
import com.weili.wechat.vo.WechatMenuVO;
import com.weili.wechatCom.main.MenuManager;
import com.weili.wechatCom.menu.ButtonAPI;
import com.weili.wechatCom.menu.ClickButtonAPI;
import com.weili.wechatCom.menu.ComplexButtonAPI;
import com.weili.wechatCom.menu.MenuAPI;
import com.weili.wechatCom.menu.ViewButtonAPI;

public class WeMenuController extends MultiActionController{
	private static Log log = LogFactory.getLog(WeMenuController.class);
	
	private WeMenuService weMenuService;
	
	
	public void setWeMenuService(WeMenuService weMenuService) {
		this.weMenuService = weMenuService;
	}
	public WeMenuService getWeMenuService() {
		return weMenuService;
	}
	
	public ModelAndView qry(HttpServletRequest request,HttpServletResponse response){
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			//从session获取切换选中id
			String id = userSession.getWechatId();
			
			if(id == null || "".equals(id)){//
				return new ModelAndView("info", "message", "用户没有查看公共号信息权限");
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatId", id);
			
			List retList = this.weMenuService.qry(params);
			return new ModelAndView("manage/wechatMenu_set")
					.addObject("retList", retList)
			        .addObject("wechatId", id);
		} catch (Exception e) {
			log.error("查询异常:"+e.getMessage());
			return new ModelAndView("info","message","查询异常！");
		}
	}
	
	public void saveMenuBtn(HttpServletRequest request,HttpServletResponse response) throws IOException{
			
		PrintWriter out = response.getWriter();
		try {
			
			String wechatOriginId = StringUtil.parseString(request.getParameter("id"));
			String data =request.getParameter("data");
			data = (data == null) ? "" : new String(data.getBytes("ISO-8859-1"), "utf-8");
			data = java.net.URLDecoder.decode(data,"UTF-8");
			
			/**
			 * 默认一个公众号只用一套 菜单
			 */
			
			String wechatMenuId = wechatOriginId+"001";
//			WechatMenu wechatMenu = this.weMenuService.getWechatMenuById(wechatMenuId);
			String createTime = this.weMenuService.getCreateTime(wechatMenuId);
			
			String systime = CalendarUtil.getSysTimeYMDHMS();
			WechatMenuVO wechatMenuVo = new WechatMenuVO();
			wechatMenuVo.setId(wechatMenuId);
			wechatMenuVo.setCreateTime(createTime == null ? systime : createTime);
			wechatMenuVo.setWechatOriginId(wechatOriginId);
			wechatMenuVo.setLastModifiedTime(systime);
			wechatMenuVo.setVersion(CalendarUtil.getDate());
			
			WechatMenuItemVO wechatMenuItemVo = null;
			List<WechatMenuItemVO> menuItemVOlist = new ArrayList<WechatMenuItemVO>();
			if(!"".equals(data)){
				JSONArray subButton = null;
				JSONArray addedArray = new JSONArray(data);
				for(int i = 0; i < addedArray.length(); i++) {
					JSONObject jo = (JSONObject)addedArray.get(i);
					wechatMenuItemVo = new WechatMenuItemVO();
					
					if(jo.has("sub_button")){//有至少一个子菜单
						
						wechatMenuItemVo.setId(wechatMenuId + i+"0");
						wechatMenuItemVo.setMenuId(wechatMenuId);
						wechatMenuItemVo.setWechatOrginId(wechatOriginId);
						wechatMenuItemVo.setMenuLevel("1");
						wechatMenuItemVo.setMenuOrder(""+(i+1));
						wechatMenuItemVo.setName(jo.getString("name"));
//						wechatMenuItemVo.setWechatFunctionId(jo.getString("key"));//这个字段名要改
						menuItemVOlist.add(wechatMenuItemVo);
						
						subButton = jo.getJSONArray("sub_button");
						
						for(int j = 0; j < subButton.length(); j++){
							JSONObject jo1 = (JSONObject)subButton.get(j);
							wechatMenuItemVo = new WechatMenuItemVO();
							wechatMenuItemVo.setId(wechatMenuId + i + (j+1));
							wechatMenuItemVo.setMenuId(wechatMenuId);
							wechatMenuItemVo.setWechatOrginId(wechatOriginId);
							wechatMenuItemVo.setMenuLevel("2");
							wechatMenuItemVo.setMenuOrder(""+(j+1));
							wechatMenuItemVo.setName(jo1.getString("name"));
                            if(jo1.getString("type").equals("view")){
                            	wechatMenuItemVo.setWechatUrl(jo1.getString("url"));
                            }else if(jo1.getString("type").equals("click"))
                            {
                        	    wechatMenuItemVo.setWechatFuncName(jo1.getString("key"));
                            }	
							wechatMenuItemVo.setMenuType(jo1.getString("type"));
							wechatMenuItemVo.setParentId(wechatMenuId + i+"0");
							menuItemVOlist.add(wechatMenuItemVo);
						}
						
					}else{//没有子菜单
						//获取主菜单
						wechatMenuItemVo.setId(wechatMenuId + i+"0");
						wechatMenuItemVo.setMenuId(wechatMenuId);
						wechatMenuItemVo.setWechatOrginId(wechatOriginId);
						wechatMenuItemVo.setMenuLevel("1");
						wechatMenuItemVo.setMenuOrder(""+(i+1));
						wechatMenuItemVo.setName(jo.getString("name"));
						if(jo.getString("type").equals("view")){
                        	wechatMenuItemVo.setWechatUrl(jo.getString("url"));
                        }else if(jo.getString("type").equals("click"))
                        {
                    	    wechatMenuItemVo.setWechatFuncName(jo.getString("key"));
                        }	
						wechatMenuItemVo.setMenuType(jo.getString("type"));
						menuItemVOlist.add(wechatMenuItemVo);
					}
				}
			}
			
			wechatMenuVo.setMenuItemVOlist(menuItemVOlist);
			
			int retCode = this.weMenuService.saveMenuBtn(wechatMenuVo);
			
			if (retCode != 0){
				out.print("error");
			}
			out.close();
			out = null;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			out.print("error");
			out.close();
			out = null;
			
		}

	}
	public void deployMenuBtn(HttpServletRequest request,HttpServletResponse response)throws IOException{
		
		PrintWriter out = response.getWriter();
		try {
			//获取微信公众号
			String wechatOriginId = StringUtil.parseString(request.getParameter("id"));
			
			// 由公众号获取菜单list
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("wechatId", wechatOriginId);
			
			List<WechatMenuItemVO> retList = this.weMenuService.qryMenItemsForDeploy(params);
//			List<?> retList = this.weMenuService.qry(params);
			System.out.println("+菜单list<wechatMenuItemVo>------------------------------+");
//			for(int i= 0;i<retList.size();i++){
//			System.out.println(retList.get(i).getId());
//			System.out.println(retList.get(i).getMenuId());
//			System.out.println(retList.get(i).getMenuLevel());
//			System.out.println(retList.get(i).getMenuOrder());
//			System.out.println(retList.get(i).getMenuType());
//			System.out.println(retList.get(i).getName());
//			System.out.println(retList.get(i).getParentId());
//			System.out.println(retList.get(i).getWechatFuncName());
//			System.out.println(retList.get(i).getWechatFunctionId());
//			System.out.println(retList.get(i).getWechatOrginId());
//			System.out.println(retList.get(i).getWechatUrl());
//			System.out.println("+------------------------------+");
//			}
			WechatMenuItemVO vo = null;
			String level_1_order = null;
			int count_0 = 0;
			int count_1 = 0;
			int count_2 = 0;
			for(int i= 0;i<retList.size();i++){
				vo = retList.get(i);
				level_1_order = vo.getId().substring(vo.getId().length()-2,vo.getId().length()-1);
				if("0".equals(level_1_order)){
					count_0 ++;
				}else if ("1".equals(level_1_order)){
					count_1 ++;
				}else{
					count_2 ++;
				}
			}
			
			
			ButtonAPI[] button_0 = new ButtonAPI[count_0-1 >= 0 ? count_0-1 : 0];
			ButtonAPI[] button_1 = new ButtonAPI[count_1-1 >= 0 ? count_1-1 : 0];
			ButtonAPI[] button_2 = new ButtonAPI[count_2-1 >= 0 ? count_2-1 : 0];
			ButtonAPI buttonAPI = new ButtonAPI();
			for(int j= 0;j<retList.size();j++){
				vo = retList.get(j);
				level_1_order = vo.getId().substring(vo.getId().length()-2,vo.getId().length()-1);
				if("0".equals(level_1_order)){
					if("2".equals(vo.getMenuLevel())){//子菜单
						buttonAPI = convertvo2ButtonAPI(vo);
						button_0[StringUtil.parseInteger(vo.getMenuOrder())-1] = buttonAPI;
					}
				}else if ("1".equals(level_1_order)){
					if("2".equals(vo.getMenuLevel())){//子菜单
						buttonAPI = convertvo2ButtonAPI(vo);
						button_1[StringUtil.parseInteger(vo.getMenuOrder())-1] = buttonAPI;
					}
				}else{
					if("2".equals(vo.getMenuLevel())){//子菜单
						buttonAPI = convertvo2ButtonAPI(vo);
						button_2[StringUtil.parseInteger(vo.getMenuOrder())-1] = buttonAPI;
					}
				}
			}
			
			
			MenuAPI menuapi = new MenuAPI();
			int length = (count_0 > 0 ? 1 : 0) + (count_1 > 0 ? 1 : 0) + (count_2 > 0 ? 1 : 0);
			ButtonAPI[] menuButtonList = new ButtonAPI[length];
			for(int k= 0;k<retList.size();k++){
				vo = retList.get(k);
				level_1_order = vo.getId().substring(vo.getId().length()-2,vo.getId().length()-1);
				if("0".equals(level_1_order)){
					if("1".equals(vo.getMenuLevel())){//1级菜单
						if(count_0 == 1){
							ButtonAPI button0 = convertvo2ButtonAPI(vo);
							menuButtonList[0] = button0;
						}else{
							ComplexButtonAPI button0 = new ComplexButtonAPI();
							button0.setName(vo.getName());
							button0.setSub_button(button_0);
							menuButtonList[0] = button0;
						}
					}
				}else if ("1".equals(level_1_order)){
					if("1".equals(vo.getMenuLevel())){//1级菜单
						if(count_1 == 1){
							ButtonAPI button1 = convertvo2ButtonAPI(vo);
							menuButtonList[1] = button1;
						}else{
							ComplexButtonAPI button1 = new ComplexButtonAPI();
							button1.setName(vo.getName());
							button1.setSub_button(button_1);
							menuButtonList[1] = button1;
						}
					}
				}else{
					if("1".equals(vo.getMenuLevel())){//1级菜单
						if(count_2 == 1){
							ButtonAPI button2 = convertvo2ButtonAPI(vo);
							menuButtonList[2] = button2;
						}else{
							ComplexButtonAPI button2 = new ComplexButtonAPI();
							button2.setName(vo.getName());
							button2.setSub_button(button_2);
							menuButtonList[2] = button2;
						}
					}
				}
			}
			
			menuapi.setButton(menuButtonList);
			
			
			
			boolean ret = MenuManager.deployMenuBtn(menuapi);
			
			/*菜单接口*/
//			boolean ret = this.getMeunManagerWebService().deployMenu(wechatOriginId, retList);
			//调用微信server接口
//			System.out.println(ret);
			
//			if(retList == null || retList.size() == 0){
//				out.print("no_data");
//			}else{
//				//组装成Json串
//				List<WechatMenuItemVO> list = null;
//				int size = 0;
//				JSONArray jsonArray = new JSONArray();
//				JSONArray jsonArray1 = null;
//				JSONObject jsonObject = null;
//				JSONObject jsonObject1 = null;
//				WechatMenuItemVO menuItemvo = null;
//				for(int i = 0; i < 3; i++){
//					list = (List<WechatMenuItemVO>) retList.get(i);
//					size = list.size();
//					if(list != null && size > 0){
//						
//						menuItemvo = list.get(0);
//						if(size == 1){//只有rootmenu
//							jsonObject = new JSONObject();
//							jsonObject.put("type", menuItemvo.getMenuType());
//							jsonObject.put("name", menuItemvo.getName());
//							if("click".equals(menuItemvo.getMenuType())){
//								jsonObject.put("key", menuItemvo.getWechatFunctionId());
//							}else{//view
//								jsonObject.put("url", menuItemvo.getWechatFunctionId());//url
//							}
//							
//						}else{//有子菜单
//							jsonObject = new JSONObject();
//							jsonObject.put("name", menuItemvo.getName());
//							
//							//组装子菜单 jsonArray
//							//start
//							jsonArray1 = new JSONArray();
//							for(int j = 1; j < size; j++){
//								jsonObject1 = new JSONObject();
//								menuItemvo = list.get(j);
//								
//								jsonObject1.put("type", menuItemvo.getMenuType());
//								jsonObject1.put("name", menuItemvo.getName());
//								if("click".equals(menuItemvo.getMenuType())){
//									jsonObject1.put("key", menuItemvo.getWechatFunctionId());
//								}else{//view
//									jsonObject1.put("url", menuItemvo.getWechatFunctionId());//url
//								}
//								jsonArray1.put(jsonObject1);
//							}
//							//end
//							jsonObject.put("sub_button", jsonArray1);
//							
//						}
//						jsonArray.put(jsonObject);
//					}
//					
//				}
//				
//				String str = jsonArray.toString();
//				//httpClient
//				String requestUrl = "127.0.0.1";//wechat_web 地址
//				String content = str;
//				HttpClientTools httpClientTools = null;
//				// 返回结果
//				String jsonStr = httpClientTools.doPost(requestUrl, content);
//				
//				
//				System.out.println(str);
//				
//				
//				//调用微信server 生成菜单接口
//			}
			
			if(!ret){
				out.print("error");
			}
			out.close();
			out = null;
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			out.print("error");
			out.close();
			out = null;
		}
	}
	public void delMenuBtn(HttpServletRequest request,HttpServletResponse response)throws IOException{
		
	}
	
	
	private ButtonAPI convertvo2ButtonAPI(WechatMenuItemVO vo){
		
		if("click".equals(vo.getMenuType())){//0 click
			ClickButtonAPI button = new ClickButtonAPI();
			button.setName(vo.getName());
			button.setType("click");
			button.setKey(vo.getWechatFuncName());
			return button;
		}else{//1 view
			ViewButtonAPI button = new ViewButtonAPI();
			button.setName(vo.getName());
			button.setType("view");
			button.setUrl(vo.getWechatUrl());
			return button;
		}
	}
	
}
