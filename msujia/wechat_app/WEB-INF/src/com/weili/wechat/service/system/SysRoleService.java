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
	 * @param id ��ɫ���
	 * @return   ��Ŷ�Ӧ�Ľ�ɫ����
	 */
	public SysRole getRoleById(String id);
	
	/**
	 * 
	 * @return ������Ͻ�ɫ�ı��
	 */
	public int getMaxNo();
	
	/**
	 * ��Ӵ������޸ĵĶ������ݿ�
	 * @param sr 
	 */
	public void createOrUpdate(SysRole sr);
	
}
