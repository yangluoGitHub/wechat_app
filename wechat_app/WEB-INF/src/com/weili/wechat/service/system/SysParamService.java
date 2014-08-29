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
	 * 系统参数查询
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
	 * 根据参数类型查询系统参数(名:值)MAP
	 * 
	 * @param paramType
	 * @return
	 */
	public HashMap<String, String> qrySysParamNVMapByCatalog(int paramCatalog);

	/**
	 * 根据参数类型和参数名称获取参数值
	 * 
	 * @param paramCatalog
	 * @param name
	 * @return 不存在：null
	 */
	public SysParam qrySysParamValueByCatalogAndName(int paramCatalog, String name);
	
	/**
	 * 新增系统参数
	 * @param sysParamVO
	 * @return
	 */
	public void addSysParam(SysParamVO sysParamVO) throws IOException,ScriptException;
	
	/**
	 * 删除系统参数
	 * @param sysParamVO
	 */
	public void delSysParam(SysParamVO sysParamVO) throws IOException,ScriptException;

	public List qrySysParamCatalog();
	
	public String getLogicId(Integer catalog);

}
