package com.weili.wechat.service.system.impl;

import java.util.UUID;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.hibernate.MOpTable;
import com.weili.wechat.service.system.RegisterService;
import com.weili.wechat.vo.MOpTableVO;


public class RegisterServiceImpl extends RetInfo implements RegisterService{

	private CommonData commonData;
	public CommonData getCommonData() {
		return commonData;
	}
	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}
	@Override
	public int register(MOpTableVO vo) {
		// TODO Auto-generated method stub
		this.initRet();
		this.setRetMsg("×¢²áÊ§°Ü!");
		MOpTable moptable = new MOpTable();
		moptable.setNo(UUID.randomUUID().toString());
		moptable.setMobile(vo.getMobile());
		moptable.setPasswd(vo.getPasswd());
		moptable.setOpenid(vo.getOpenId());
		
		commonData.createObject(moptable);
		this.setRetOK();
		this.setRetMsg("×¢²á³É¹¦!");
		return this.getRetCode();
	}
	
}