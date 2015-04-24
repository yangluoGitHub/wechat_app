package com.weili.wechat.service.system;

/**
 * @author zzyun
 * @since 2009\05\04
 * @version 1.0
 */
import java.util.List;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.hibernate.SysRole;

public interface SysRoleService extends IRetInfo {
	
	public List qrySysRoleByOrgType(Integer orgType);
	
	/**
	 * 
	 * @param id 角色编号
	 * @return   编号对应的角色对象
	 */
	public SysRole getRoleById(String id);
	
	/**
	 * 
	 * @return 返回组合角色的编号
	 */
	public int getMaxNo();
	
	/**
	 * 添加创建或修改的对象到数据库
	 * @param sr 
	 */
	public void createOrUpdate(SysRole sr);
	
}
