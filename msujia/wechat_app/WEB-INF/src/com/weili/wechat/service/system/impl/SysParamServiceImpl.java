package com.weili.wechat.service.system.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.SystemCons;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.hibernate.SysParam;
import com.weili.wechat.hibernate.SysParamCatalog;
import com.weili.wechat.service.system.SysParamService;
import com.weili.wechat.vo.SysParamVO;

public class SysParamServiceImpl extends RetInfo implements SysParamService {
	
	private static Log log = LogFactory.getLog(SysParamServiceImpl.class);

	private CommonData commonData;

	public CommonData getCommonData() {
		return commonData;
	}

	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}
	
	/**
	 * 系统参数查询
	 * @param params
	 */
	public List qrySysParam(Map<String, Object> params) {
		String catalog = StringUtil.parseString(params.get("catalog"));
		String paramName = StringUtil.parseString(params.get("paramName"));
		
		String hql = "from SysParam s,SysParamCatalog sc where s.sysParamCatalog.catalog=sc.catalog";
		if (!"".equals(catalog)) {
			hql += " and sc.catalog=" + catalog;
		}
		if (!"".equals(paramName)) {
			hql += " and s.paramName like '%" + paramName + "%'";
		}
		hql += " order by s.logicId";
		List resultList = commonData.getAllResult(hql);
		
		return resultList;
	}

	public void modSysParam(SysParamVO sysParamVO) throws IOException,ScriptException{
		SysParam sysParam = (SysParam) commonData.retrieveObject(SysParam.class, sysParamVO.getLogicId());
		SysParamCatalog sysParamCatalog = (SysParamCatalog) commonData.retrieveObject(SysParamCatalog.class, sysParamVO.getCatalog());
		sysParam.setSysParamCatalog(sysParamCatalog);
		sysParam.setParamName(sysParamVO.getParamName());
		sysParam.setParamValue(sysParamVO.getParamValue());
		sysParam.setStatement(sysParamVO.getStatement());
		sysParam.setDescription(sysParamVO.getDescription());
		commonData.updateObject(sysParam);
		
		SystemCons.sysParamMap.put(sysParamVO.getParamName(), sysParamVO.getParamValue());

		//start add by yt 2013-05-18
		//如果修改了最大加钞周期，则联动修改dev_base_info表所有记录的ADD_CLR_PERIOD、MAX_ADD_CLR_PERIOD字段
		if(sysParamVO.getParamName().equals("MAX_ADDNOTES_PERIOD")){
			String updateHql = "UPDATE DevBaseInfo d SET d.addClrPeriod=?, d.maxAddClrPeriod=? ";
			commonData.batchDeleteOrUpdate(updateHql, new Object[]{new BigDecimal(sysParamVO.getParamValue()), new BigDecimal(sysParamVO.getParamValue())});
		}
		//end add by yt
		
		if (sysParam.getLogicId().equals("10017")) {
			// 10017是auditFlag的设置
			if (sysParam.getParamValue().equalsIgnoreCase("true") || sysParam.getParamValue().equalsIgnoreCase("false"))
				SystemCons.sysParamMap.put("auditFlag", sysParam.getParamValue());
			else
				SystemCons.sysParamMap.put("auditFlag", "false");
		} else if (sysParam.getLogicId().equals("10018")) {
			// 10018是passwdmaxCount的设置
			SystemCons.sysParamMap.put("passwdmaxCount", sysParam.getParamValue());
		} else if (sysParam.getLogicId().equals("10014")) {
			// 10014是passwordUpdateDate的设置
			SystemCons.sysParamMap.put("passwordUpdateDate", sysParam.getParamValue());
		} else if (sysParam.getLogicId().equals("10016")) {
			// 10016是passwdtxDate的设置
			SystemCons.sysParamMap.put("passwdtxDate", sysParam.getParamValue());
		}
		if (sysParam.getSysParamCatalog().getCatalog() == 6) {
			String rootPath = SystemCons.getRoot();
			// 获得一个JavaScript脚本引擎，也可以是ECMAScript脚本引擎
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			BufferedReader br = new BufferedReader(new FileReader(rootPath
					+ SystemCons.FILE_SEPARATOR + "scripts"
					+ SystemCons.FILE_SEPARATOR + "constantMap.js"));
			String oldStr = "";
			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("var MaintainTaskTypeMap")) {// 日常办公为例
					oldStr = line;
				}
			}
			br.close();
			StringBuffer buf = new StringBuffer();
			BufferedReader in = new BufferedReader(new FileReader(rootPath
					+ SystemCons.FILE_SEPARATOR + "scripts"
					+ SystemCons.FILE_SEPARATOR + "constantMap.js"));
			// 保存该行前面的内容
			for (int j = 1; (line = in.readLine()) != null
					&& !line.equals(oldStr); j++) {
				buf = buf.append(line);
				buf = buf.append(System.getProperty("line.separator"));
			}

			// 将内容插入

			String[] array = new String[30];//
			String str = oldStr.substring(oldStr.indexOf("{") + 1, oldStr
					.indexOf("}"));
			String str1 = "";
			array = str.split(",");
			for (int i = 0; i < array.length; i++) {
				if (array[i].startsWith(" " + (char) 34
						+ sysParam.getParamName())) {
					array[i] = " " + (char) 34 + sysParam.getParamName()
							+ (char) 34 + ":" + (char) 34
							+ sysParam.getParamValue() + (char) 34;
				}
				if (i == 0) {
					str1 += array[i];
				} else {
					str1 += "," + array[i];
				}
			}
			String replaceStr = oldStr.substring(0, oldStr.indexOf("{") + 1)
					+ str1 + "};";
			buf = buf.append(replaceStr);

			// 保存该行后面的内容
			while ((line = in.readLine()) != null) {
				buf = buf.append(System.getProperty("line.separator"));
				buf = buf.append(line);
			}

			in.close();
			FileWriter fw = new FileWriter(rootPath
					+ SystemCons.FILE_SEPARATOR + "scripts"
					+ SystemCons.FILE_SEPARATOR + "constantMap.js");// 创建FileWriter对象，用来写入字符流
			BufferedWriter bw = new BufferedWriter(fw); // 将缓冲对文件的输出
			bw.write(buf.toString().toCharArray());
			bw.flush();
			bw.close();
		}
		log.debug("修改系统参数成功");
	}
	
	public void addSysParam(SysParamVO sysParamVO) throws IOException,ScriptException {
		
		SysParam sysParam = this.vo_po(sysParamVO);
		commonData.createObject(sysParam);
		SystemCons.sysParamMap.put(sysParamVO.getParamName(), sysParamVO.getParamValue());
		if (sysParam.getSysParamCatalog().getCatalog() == 6) {
			String rootPath = SystemCons.getRoot();
			// 获得一个JavaScript脚本引擎，也可以是ECMAScript脚本引擎
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			BufferedReader br = new BufferedReader(new FileReader(rootPath
					+ SystemCons.FILE_SEPARATOR + "scripts"
					+ SystemCons.FILE_SEPARATOR + "constantMap.js"));
			String oldStr = "";
			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("var MaintainTaskTypeMap")) {// 日常办公为例
					oldStr = line;
				}
			}
			br.close();
			StringBuffer buf = new StringBuffer();
			BufferedReader in = new BufferedReader(new FileReader(rootPath
					+ SystemCons.FILE_SEPARATOR + "scripts"
					+ SystemCons.FILE_SEPARATOR + "constantMap.js"));
			// 保存该行前面的内容
			for (int j = 1; (line = in.readLine()) != null
					&& !line.equals(oldStr); j++) {
				buf = buf.append(line);
				buf = buf.append(System.getProperty("line.separator"));
			}

			// 将内容插入
			String replaceStr = oldStr.substring(0, oldStr.indexOf("}")) + ", "
					+ (char) 34 + sysParam.getParamName() + (char) 34 + ":"
					+ (char) 34 + sysParam.getParamValue() + (char) 34 + "};";
			buf = buf.append(replaceStr);

			// 保存该行后面的内容
			while ((line = in.readLine()) != null) {
				buf = buf.append(System.getProperty("line.separator"));
				buf = buf.append(line);
			}

			in.close();
			FileWriter fw = new FileWriter(rootPath
					+ SystemCons.FILE_SEPARATOR + "scripts"
					+ SystemCons.FILE_SEPARATOR + "constantMap.js");// 创建FileWriter对象，用来写入字符流
			BufferedWriter bw = new BufferedWriter(fw); // 将缓冲对文件的输出
			bw.write(buf.toString().toCharArray());
			bw.flush();
			bw.close();
		}
		log.debug("增加系统参数成功");

	}
	
	public void delSysParam(SysParamVO sysParamVO) throws IOException,
			ScriptException {
		SysParam sysParam = (SysParam) commonData.retrieveObject(SysParam.class,
				sysParamVO.getLogicId());
		commonData.deleteObject(sysParam);
		SystemCons.sysParamMap.remove(sysParamVO.getParamName());
		if (sysParam.getSysParamCatalog().getCatalog() == 6) {
			String rootPath = SystemCons.getRoot();
			// 获得一个JavaScript脚本引擎，也可以是ECMAScript脚本引擎
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			BufferedReader br = new BufferedReader(new FileReader(rootPath
					+ SystemCons.FILE_SEPARATOR + "scripts"
					+ SystemCons.FILE_SEPARATOR + "constantMap.js"));
			String oldStr = "";
			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("var MaintainTaskTypeMap")) {// 日常办公为例
					oldStr = line;
				}
			}
			br.close();
			StringBuffer buf = new StringBuffer();
			BufferedReader in = new BufferedReader(new FileReader(rootPath
					+ SystemCons.FILE_SEPARATOR + "scripts"
					+ SystemCons.FILE_SEPARATOR + "constantMap.js"));
			// 保存该行前面的内容
			for (int j = 1; (line = in.readLine()) != null
					&& !line.equals(oldStr); j++) {
				buf = buf.append(line);
				buf = buf.append(System.getProperty("line.separator"));
			}

			// 将内容删除
			String[] array = new String[30];//
			String str = oldStr.substring(oldStr.indexOf("{") + 1, oldStr
					.indexOf("}"));
			String str1 = "";
			array = str.split(",");
			for (int i = 0; i < array.length; i++) {
				if (array[i].startsWith(" " + (char) 34
						+ sysParam.getParamName())) {
					array[i] = "";
				}
				if (i == 0) {
					str1 += array[i];
				} else {
					if (array[0].equals("") && i == 1) {
						str1 += array[i];
					} else if (!array[i].equals("")) {
						str1 += "," + array[i];
					}
				}
			}
			String replaceStr = oldStr.substring(0, oldStr.indexOf("{") + 1)
					+ str1 + "};";
			buf = buf.append(replaceStr);

			// 保存该行后面的内容
			while ((line = in.readLine()) != null) {
				buf = buf.append(System.getProperty("line.separator"));
				buf = buf.append(line);
			}

			in.close();
			FileWriter fw = new FileWriter(rootPath
					+ SystemCons.FILE_SEPARATOR + "scripts"
					+ SystemCons.FILE_SEPARATOR + "constantMap.js");// 创建FileWriter对象，用来写入字符流
			BufferedWriter bw = new BufferedWriter(fw); // 将缓冲对文件的输出
			bw.write(buf.toString().toCharArray());
			bw.flush();
			bw.close();
		}
		log.debug("删除系统参数成功");
	}
	
	public SysParam qrySysParamByNo(String logicId) {
		SysParam sysParam = (SysParam) commonData.retrieveObject(SysParam.class, logicId);
		return sysParam;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> qrySysParamByType(String paramType) {

		String hql = "select s.paramName,s.paramValue from SysParam s where s.sysParamCatalog.catalog = " + paramType
				+ " order by s.paramName ";
		List<Object> retList = commonData.getAllResult(hql);

		return retList;
	}

	public SysParamVO po_vo(SysParam sysParam) {
		if (sysParam == null)
			return null;

		SysParamVO sysParamVO = new SysParamVO();
		sysParamVO.setLogicId(sysParam.getLogicId());
		sysParamVO.setParamName(sysParam.getParamName());
		sysParamVO.setParamValue(sysParam.getParamValue());
		sysParamVO.setStatement(sysParam.getStatement());
		sysParamVO.setDescription(sysParam.getDescription());

		if (sysParam.getSysParamCatalog() != null) {
			sysParamVO.setCatalog(sysParam.getSysParamCatalog().getCatalog());
		}
		return sysParamVO;
	}
	
	public SysParam vo_po(SysParamVO sysParamVO) {
		if (sysParamVO == null)
			return null;

		SysParam sysParam = new SysParam();
		sysParam.setLogicId(sysParamVO.getLogicId());
		sysParam.setParamName(sysParamVO.getParamName());
		sysParam.setParamValue(sysParamVO.getParamValue());
		sysParam.setStatement(sysParamVO.getStatement());
		sysParam.setDescription(sysParamVO.getDescription());

		if (sysParamVO.getCatalog() != null) {
			SysParamCatalog sysParamCatalog = new SysParamCatalog();
			sysParamCatalog.setCatalog(sysParamVO.getCatalog());
			String sql = " select CATALOG_NAME from SYS_PARAM_CATALOG where CATALOG = "+sysParamVO.getCatalog(); 
			String catalogName = (String)commonData.findSQL(sql).get(0);
			sysParamCatalog.setCatalogName(catalogName);
			sysParam.setSysParamCatalog(sysParamCatalog);
		}
		return sysParam;
	}
	
	public List qrySysParamCatalog() {
		return this.commonData.getAllResult("from SysParamCatalog sc order by sc.catalog");
	}
	
	
	public HashMap<String, String> qrySysParamNVMapByCatalog(int paramCatalog) {
		String hql = "from SysParam where sysParamCatalog.catalog=" + paramCatalog;
		List<?> list = this.commonData.getAllResult(hql);
		HashMap<String, String> retMap = new HashMap<String, String>();
		SysParam sysParam;
		for (Object object : list) {
			sysParam = (SysParam) object;
			retMap.put(sysParam.getParamName(), sysParam.getParamValue());
		}
		return retMap;
	}

	public SysParam qrySysParamValueByCatalogAndName(int paramCatalog, String name) {
		String hql = "from SysParam where sysParamCatalog.catalog=" + paramCatalog + " and paramName='" + name + "'";
		List<?> list = this.commonData.getAllResult(hql);
		
		return list.size() <= 0 ? null : (SysParam) list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> qrySysParamByParamName(String paramName){
		return commonData.getAllResult("from SysParam s where s.paramName='"+paramName+"'");
	}

	public String getLogicId(Integer catalog) {
		String hql = "";
		if (catalog < 10) {
			hql = "select max(s.logicId) from SysParam s where s.logicId like '" + catalog + "0%'";
		} else {
			hql = "select max(s.logicId) from SysParam s where s.logicId like '" + catalog + "%'";
		}
		List logic = commonData.getAllResult(hql);
		if (logic == null || logic.size() == 0) {
			return catalog + "00001".substring(("" + catalog).length());
		} else {
			return "" + (Integer.parseInt((String) (logic.get(0)==null?(catalog + "00000".substring(("" + catalog).length())):logic.get(0))) + 1);
		}
	}
	
}
