package com.weili.wechat.service.system;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.vo.MOpTableVO;




public interface RegisterService extends IRetInfo{
	
	
	/*
	 *ע���˺� 
	 */
	 public int register(MOpTableVO vo);
	 public int isRegisterUser(MOpTableVO vo);
}