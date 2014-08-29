package com.weili.wechat.service.system.impl;

/**
 * 
 * @author zzyun
 * @since 2009\05\04
 * @version 1.0
 */

import java.util.List;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.hibernate.SysRole;
import com.weili.wechat.service.system.SysRoleService;

public class SysRoleServiceImpl extends RetInfo implements SysRoleService{
	
	private CommonData commonData;

	public CommonData getCommonData() {
		return commonData;
	}

	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}
	
	public List qrySysRoleByOrgType(Integer orgType) {
		List roleList = commonData.getAllResult("select o.no, o.name from SysRole o where o.catalog!=0 and o.catalog!=9 and o.orgType >= ? order by o.orgType,o.no ", orgType);
		
		return roleList;
	}

	public SysRole getRoleById(String id){
		
		try{
			List resultList = commonData.getAllResult("from SysRole as sr where sr.no="+id);
			if((resultList==null)||(resultList.size()==0)){
				return null;
			}
			else{
				SysRole role=(SysRole)resultList.get(0);
				return role;
			}
		}catch(Exception e){
			return null;
		}
	}
	
	public int getMaxNo(){
		try{
			List resultList=commonData.getAllResult("select max(sr.no) from SysRole sr where sr.catalog=9");
			
			if(resultList.get(0)==null){
				return 90000;
			}
			else{
				return Integer.parseInt(resultList.get(0).toString());
			}
		}catch(Exception e){
			return 0;
		}
	}
	
	public void createOrUpdate(SysRole sr){
		try{
			commonData.createOrUpdateObject(sr);
		}catch(Exception e){
			//System.out.println("½ÇÉ«²Ù×÷Ê§°Ü");
		}
	}

}
