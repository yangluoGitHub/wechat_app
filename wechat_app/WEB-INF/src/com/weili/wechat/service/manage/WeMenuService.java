package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.hibernate.WechatMenu;
import com.weili.wechat.vo.WechatMenuItemVO;
import com.weili.wechat.vo.WechatMenuVO;

public interface WeMenuService extends IRetInfo{
	
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public List<?> qry(Map<String, Object> params);
	
	/**
	 * 
	 * @param wechatMenuVo
	 * @return
	 */
	public int saveMenuBtn(WechatMenuVO wechatMenuVo);
	
	
	/**
	 * 
	 * @param wemenuId
	 * @return WechatMenu
	 */
	public WechatMenu getWechatMenuById(String wemenuId);
	
	/**
	 * 
	 * @param wemenuId
	 * @return
	 */
	public String getCreateTime(String wemenuId);
	
	/**
	 * DWR_ÏÂÀ­¿ò²¹×ã
	 * @param devNo
	 * @return
	 * @throws JSONException
	 */
	public String qryWechatFuc(String inputStr, String wechatId) throws JSONException;
	
	/**
	 * DWR_ÅÐ¶ÏÊÇ·ñ´æÔÚ
	 * @param funcName
	 * @return
	 */
	public boolean isExitFunctionName(String funcName, String wechatId);
	
	
	public List<WechatMenuItemVO> qryMenItemsForDeploy(Map<String, Object> params);
}
