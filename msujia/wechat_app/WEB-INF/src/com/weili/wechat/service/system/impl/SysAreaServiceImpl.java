package com.weili.wechat.service.system.impl;

import java.util.List;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.service.system.SysAreaService;

public class SysAreaServiceImpl extends RetInfo implements SysAreaService {
   
	private CommonData commonData;
	
	public CommonData getCommonData() {
		return commonData;
	}

	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}

	public List qrySysArea() {
		List list = this.commonData.getAllResult("from SysArea ");
		
		return list;
	}
   
}
