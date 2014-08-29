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
		this.setRetMsg("���ʧ��!");
		log.info("��ʼ��ӣ�");
		this.jarDAO.save(jarTable);
		log.info("��ӳɹ���");
		this.setRetMsg("��ӳɹ�!");
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
			log.debug("��ѯʧ��");
			e.printStackTrace();
			return null;
		}
	}
	
	public int delJar(JarTable jarTable){
		this.initRet();
		this.setRetMsg("ɾ��ʧ��!");
		commonData.deleteObject(jarTable);
		this.setRetMsg("ɾ���ɹ�!");
		this.setRetOK();
		return this.getRetCode();
	} 
	
	
}
