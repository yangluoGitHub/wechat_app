package com.weili.wechat.service.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.hibernate.SysParam;
import com.weili.wechat.vo.SysParamVO;

public interface SysParamService extends IRetInfo {
	
	/**
	 * ϵͳ������ѯ
	 * @param params
	 */
	public List qrySysParam(Map<String, Object> params);
	
	public void modSysParam(SysParamVO sysParamVO) throws IOException,ScriptException;

	public SysParam qrySysParamByNo(String logicId);
	
	public List<Object> qrySysParamByType(String paramType);
	
	public List<Object> qrySysParamByParamName(String paramName);

	public SysParamVO po_vo(SysParam sysParam);

	public SysParam vo_po(SysParamVO sysParamVO);

	/**
	 * ���ݲ������Ͳ�ѯϵͳ����(��:ֵ)MAP
	 * 
	 * @param paramType
	 * @return
	 */
	public HashMap<String, String> qrySysParamNVMapByCatalog(int paramCatalog);

	/**
	 * ���ݲ������ͺͲ������ƻ�ȡ����ֵ
	 * 
	 * @param paramCatalog
	 * @param name
	 * @return �����ڣ�null
	 */
	public SysParam qrySysParamValueByCatalogAndName(int paramCatalog, String name);
	
	/**
	 * ����ϵͳ����
	 * @param sysParamVO
	 * @return
	 */
	public void addSysParam(SysParamVO sysParamVO) throws IOException,ScriptException;
	
	/**
	 * ɾ��ϵͳ����
	 * @param sysParamVO
	 */
	public void delSysParam(SysParamVO sysParamVO) throws IOException,ScriptException;

	public List qrySysParamCatalog();
	
	public String getLogicId(Integer catalog);

}
