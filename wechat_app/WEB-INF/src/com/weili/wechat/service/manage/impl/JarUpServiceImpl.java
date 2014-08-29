package com.weili.wechat.service.manage.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.service.manage.JarUpService;
import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.dao.system.JarDAO;
import com.weili.wechat.hibernate.JarTable;
import com.weili.wechat.hibernate.WechatFunction;
public class JarUpServiceImpl extends RetInfo implements JarUpService{
	private static Log log = LogFactory.getLog(JarUpServiceImpl.class);
	
	private JarDAO jarDAO;
	private CommonData commonData;
	
	public CommonData getCommonData() {
		return commonData;
	}
	public JarDAO getJarDAO() {
		return jarDAO;
	}
	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}
	public void setJarDAO(JarDAO jarDAO) {
		this.jarDAO = jarDAO;
	}

	public List qry(Map<String, Object> params) {
		String name = StringUtil.parseString(params.get("name"));
		String startDate = StringUtil.parseString(params.get("startDate"));
		String endDate = StringUtil.parseString(params.get("endDate"));
		
		StringBuffer hql = new StringBuffer();
		hql.append("from JarTable as a where  1=1");
		
		if (name.length() > 0)
			hql.append(" and a.name like '%").append(name).append("%'");
		
		if (!"".equals(startDate) && !"".equals(endDate)) {
			hql.append("and up_date >= '" + startDate + "' ");
			hql.append("and up_date <= '" + endDate + "' ");
		}
		List<?> list = null;
		list = this.commonData.getAllResult(hql.toString());
		return list;
		
	}
	
	public int addJar(JarTable jarTable){
		this.initRet();
		this.setRetMsg("添加失败!");
		log.info("开始添加！");
		this.jarDAO.save(jarTable);
		log.info("添加成功！");
		this.setRetMsg("添加成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	
	public JarTable findById(String id){
		try {
			JarTable jarTable  = (JarTable) commonData.retrieveObject(JarTable.class, id);
			if (jarTable != null) {
				return  jarTable;
			}
			else {
				return null;
			}
		}
		catch (Exception e) {
			log.debug("查询失败");
			e.printStackTrace();
			return null;
		}
	}
	
	public int delJar(JarTable jarTable){
		this.initRet();
		this.setRetMsg("删除失败!");
		commonData.deleteObject(jarTable);
		this.setRetMsg("删除成功!");
		this.setRetOK();
		return this.getRetCode();
	} 
	
	
}
