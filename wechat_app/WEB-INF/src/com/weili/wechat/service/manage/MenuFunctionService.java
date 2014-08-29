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
	  *��ѯ 
	  */
		public List qry(Map<String, Object> params);
	 /*
	  * ���	
	  */
		public int addFunction(WechatFunctionVO vo);
	/*
	 * �޸�	
	 */
		public int modFunction(WechatFunctionVO vo);
	/*
	 * ɾ��	
	 */
		public int delFunction(WechatFunctionVO vo);
		
		
		
		/*
		 * ��id��ѯ	
		 */
		public WechatFunction findById(String id);
		/**
		 * 
		 * @param FunId
		 * @return
		 */
		public List<String> qryPubAccountIdByFunId(String FunId);
		
		/**
		 * ɾ�����ں�ҵ����Ȩ�޹�ϵ��
		 * @param FunId
		 */
		public void delPubAccountFunction(String FunId);
		
		/**
		 * ɾ�����ں�ҵ����Ȩ�޹�ϵ��
		 * @param wechatId
		 */
		public void delPubAccountFunctionByWechatId(String wechatId);
		/**
		 * ����id��ҵ��������
		 * @param id
		 * @return
		 */
		public String qryFuncNameById(String id);
		
		/**
		 * dwr ǰ̨У��funcName �Ƿ� unique
		 * @param name
		 * @param id
		 * @return
		 */
		public boolean isUniqueFuncName(String name, String id);
		
		
		public int savePubFuncRelations(List<PubaccountFunctionVO> list);
		
}
