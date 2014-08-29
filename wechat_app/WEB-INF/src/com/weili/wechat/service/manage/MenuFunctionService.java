package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.hibernate.WechatFunction;
import com.weili.wechat.vo.PubaccountFunctionVO;
import com.weili.wechat.vo.WechatFunctionVO;

public interface MenuFunctionService extends IRetInfo
{
	 
	 /*
	  *查询 
	  */
		public List qry(Map<String, Object> params);
	 /*
	  * 添加	
	  */
		public int addFunction(WechatFunctionVO vo);
	/*
	 * 修改	
	 */
		public int modFunction(WechatFunctionVO vo);
	/*
	 * 删除	
	 */
		public int delFunction(WechatFunctionVO vo);
		
		
		
		/*
		 * 由id查询	
		 */
		public WechatFunction findById(String id);
		/**
		 * 
		 * @param FunId
		 * @return
		 */
		public List<String> qryPubAccountIdByFunId(String FunId);
		
		/**
		 * 删除公众号业务功能权限关系表
		 * @param FunId
		 */
		public void delPubAccountFunction(String FunId);
		
		/**
		 * 删除公众号业务功能权限关系表
		 * @param wechatId
		 */
		public void delPubAccountFunctionByWechatId(String wechatId);
		/**
		 * 根据id查业务功能名称
		 * @param id
		 * @return
		 */
		public String qryFuncNameById(String id);
		
		/**
		 * dwr 前台校验funcName 是否 unique
		 * @param name
		 * @param id
		 * @return
		 */
		public boolean isUniqueFuncName(String name, String id);
		
		
		public int savePubFuncRelations(List<PubaccountFunctionVO> list);
		
}
