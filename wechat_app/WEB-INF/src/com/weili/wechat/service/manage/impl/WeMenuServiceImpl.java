package com.weili.wechat.service.manage.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.hibernate.PublicAccount;
import com.weili.wechat.hibernate.WechatFunction;
import com.weili.wechat.hibernate.WechatMenu;
import com.weili.wechat.hibernate.WechatMenuItem;
import com.weili.wechat.service.manage.WeMenuService;
import com.weili.wechat.vo.WechatMenuItemVO;
import com.weili.wechat.vo.WechatMenuVO;


public class WeMenuServiceImpl extends RetInfo implements WeMenuService {
	
	private static Log log = LogFactory.getLog(WeMenuServiceImpl.class);
	
	private CommonData commonData;

	public CommonData getCommonData() {
		return commonData;
	}

	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}
	
	
	public List<?> qry(Map<String, Object> params){
		
		String wechatId = StringUtil.parseString(params.get("wechatId"));

		StringBuffer hql = new StringBuffer();
		hql.append("from WechatMenuItem as a where 1=1");

		if (wechatId.length() > 0){
			hql.append(" and a.publicAccount.id = '").append(wechatId).append("' order by a.id");//由小到大
		}
		List<?> list = this.commonData.getAllResult(hql.toString());
		
		//po2_vo
		WechatMenuItem menuItem = null;
		List<WechatMenuItemVO> menuItemVOlist1 = new ArrayList<WechatMenuItemVO>();
		List<WechatMenuItemVO> menuItemVOlist2 = new ArrayList<WechatMenuItemVO>();
		List<WechatMenuItemVO> menuItemVOlist3 = new ArrayList<WechatMenuItemVO>();
		List<List<WechatMenuItemVO> > retList = new ArrayList<List<WechatMenuItemVO>>();
		WechatMenuItemVO menuItemVo = null;
		String flag = null;
		if (list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				
				menuItem = (WechatMenuItem)list.get(i);
				menuItemVo = this.po2_vo(menuItem);
				flag = menuItem.getId().substring(menuItem.getId().length()-2,menuItem.getId().length()-1);
				
				if("0".equals(flag)){//第一列
					menuItemVOlist1.add(menuItemVo);
				}else if("1".equals(flag)){
					menuItemVOlist2.add(menuItemVo);
				}else{
					menuItemVOlist3.add(menuItemVo);
				}
			}
			retList.add(menuItemVOlist1);
			retList.add(menuItemVOlist2);
			retList.add(menuItemVOlist3);
		}
		
		return retList;
		
	}
	public int saveMenuBtn(WechatMenuVO wechatMenuVo){
		
		this.setRetMsg("菜单设置失败");
		this.initRet();
		
		// delete all wechatMenu
		commonData.batchDeleteOrUpdate("delete from WechatMenuItem t where t.menuId = '"+wechatMenuVo.getId()+"'");
		
		// create or update wechatMenu
		WechatMenu menu = new WechatMenu();
		menu.setId(wechatMenuVo.getId());
		menu.setCreateTime(wechatMenuVo.getCreateTime());
		menu.setLastModifiedTime(wechatMenuVo.getLastModifiedTime());
		menu.setVersion(wechatMenuVo.getVersion());
		
		PublicAccount publicAccount = (PublicAccount)commonData.retrieveObject(PublicAccount.class, wechatMenuVo.getWechatOriginId());
		menu.setPublicAccount(publicAccount);
		
		commonData.createOrUpdateObject(menu);
		
		this.setRetMsg("菜单信息设置成功");
		// create or update wechatMenuItem
		
		List<WechatMenuItemVO> menuItemVOlist = wechatMenuVo.getMenuItemVOlist();
		if(menuItemVOlist !=null && menuItemVOlist.size() > 0){
			WechatMenuItemVO menuItemVo = null;
			WechatMenuItem menuItem = null;
			WechatFunction wechatFuc = null;
			for(int i = 0; i < menuItemVOlist.size(); i++){
				menuItemVo = menuItemVOlist.get(i);
				menuItem = new WechatMenuItem();
				menuItem.setId(menuItemVo.getId());
				menuItem.setMenuId(menuItemVo.getMenuId());
				menuItem.setMenuLevel(menuItemVo.getMenuLevel());
				menuItem.setMenuOrder(menuItemVo.getMenuOrder());
				menuItem.setName(menuItemVo.getName());
				menuItem.setParentId(menuItemVo.getParentId());
				menuItem.setPublicAccount(publicAccount);
				if(menuItemVo.getWechatFuncName() != null){
					List<WechatFunction> list = commonData.getAllResult("from WechatFunction t where t.wechatletName = '"+menuItemVo.getWechatFuncName()+"'");
					if(list != null && list.size() > 0){
						wechatFuc = list.get(0);
						menuItem.setWechatFunction(wechatFuc);
					}
				}
				if(menuItemVo.getMenuType() != null){
					menuItem.setMenuType(menuItemVo.getMenuType().equals("click") ? 0 : 1);
				}
				if(menuItemVo.getWechatUrl() != null){
					menuItem.setWechatUrl(menuItemVo.getWechatUrl());
				}
				commonData.createOrUpdateObject(menuItem);
			}
		}
		
		this.setRetMsg("菜单项设置成功");
		this.setRetOK();
		
		return this.getRetCode();
	}
	
	public WechatMenu getWechatMenuById(String wemenuId){
		
		return (WechatMenu)commonData.retrieveObject(WechatMenu.class, wemenuId);
		 
	}
	
	public String getCreateTime(String wemenuId){
		
		String SQL = "select t.create_time from wechat_menu t where t.id = '"+wemenuId+"'";
		List list = commonData.findSQL(SQL);
		if(list!= null && list.size() >0){
			return list.get(0).toString();
		}
		return null;
	}
	
	private WechatMenuItemVO po2_vo (WechatMenuItem menuItem){
		
		if(menuItem == null){
			return null;
		}
		
		WechatMenuItemVO menuItemVo = new WechatMenuItemVO();
		menuItemVo.setId(menuItem.getId());
		menuItemVo.setMenuId(menuItem.getMenuId());
		menuItemVo.setMenuLevel(menuItem.getMenuLevel());
		menuItemVo.setMenuOrder(menuItem.getMenuOrder());
		menuItemVo.setName(menuItem.getName());
		menuItemVo.setParentId(menuItem.getParentId());
		if(menuItem.getWechatFunction() != null){
			menuItemVo.setWechatFunctionId(menuItem.getWechatFunction().getId());
			menuItemVo.setWechatFuncName(menuItem.getWechatFunction().getWechatletName());
		}
		
		menuItemVo.setWechatOrginId(menuItem.getPublicAccount().getId());
		
		if(menuItem.getMenuType() != null){
			menuItemVo.setMenuType(menuItem.getMenuType() == 0 ? "click" : "view");
		}
		if(menuItem.getWechatUrl() != null){
			menuItemVo.setWechatUrl(menuItem.getWechatUrl());
			
		}
		
		return menuItemVo;
	}
	public String qryWechatFuc(String inputStr, String wechatId) throws JSONException{
		// qry
		String hql = "select t.id.wechatFunction.id, t.id.wechatFunction.wechatletName from PubaccountFunction t where t.id.publicAccount.id = '"+wechatId+"' and (t.id.wechatFunction.id like '%"+inputStr+"%' or t.id.wechatFunction.wechatletName like '%"+inputStr+"%') order by t.id.wechatFunction.id";
		// makeup
		List resultList = commonData.getAllResult(hql);
		JSONArray jsonArray = new JSONArray();
		for (Object obj : resultList) {
			Object[] idName = (Object[])obj;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", idName[0].toString());
			jsonObject.put("label", idName[1].toString());
			jsonArray.put(jsonObject);
		}
		return jsonArray.toString();
	}
	public boolean isExitFunctionName(String funcName, String wechatId){
		List<WechatFunction> list = commonData.getAllResult("from PubaccountFunction t where t.id.wechatFunction.wechatletName = '"+funcName+"' and t.id.publicAccount.id = '"+wechatId+"'");
	    if(list != null && list.size() > 0){
	    	return true;
	    }
	    return false;
	}
	
	public List<WechatMenuItemVO> qryMenItemsForDeploy(Map<String, Object> params){
		String wechatId = StringUtil.parseString(params.get("wechatId"));

		StringBuffer hql = new StringBuffer();
		hql.append("from WechatMenuItem as a where 1=1");

		if (wechatId.length() > 0){
			hql.append(" and a.publicAccount.id = '").append(wechatId).append("' order by a.id");//由小到大
		}
		List<WechatMenuItem> list = this.commonData.getAllResult(hql.toString());
		List<WechatMenuItemVO> menuItemVOlist = new ArrayList<WechatMenuItemVO>();
		WechatMenuItemVO menuItemVo = null;
		for(WechatMenuItem menuItem : list){
			menuItemVo = this.po2_vo(menuItem);
			menuItemVOlist.add(menuItemVo);
		}
		
		return menuItemVOlist;
	}
	
}
