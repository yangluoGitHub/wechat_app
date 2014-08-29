package com.weili.wechat.service.manage.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.hibernate.PubaccountFunction;
import com.weili.wechat.hibernate.PubaccountFunctionId;
import com.weili.wechat.hibernate.PublicAccount;
import com.weili.wechat.hibernate.WechatFunction;
import com.weili.wechat.service.manage.MenuFunctionService;
import com.weili.wechat.vo.PubaccountFunctionVO;
import com.weili.wechat.vo.WechatFunctionVO;

public class MenuFunctionServiceImpl extends RetInfo implements MenuFunctionService{
	private static Log log = LogFactory.getLog(MenuFunctionServiceImpl.class);
	
	private CommonData commonData;
	
	public CommonData getCommonData() {
		return commonData;
	}

	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}
	
	
	public List qry(Map<String, Object> params) {
		String wechatletName = StringUtil.parseString(params.get("wechatletName"));
		String wechatId = StringUtil.parseString(params.get("wechatId"));
		
		StringBuffer hql = new StringBuffer();
		hql.append("from WechatFunction as a where  1=1");
		
		if (wechatletName.length() > 0)
			hql.append(" and a.wechatletName like '%").append(wechatletName).append("%'");
		
		if (wechatId.length() >0){
			hql.append(" and a.id in (select p.id.wechatFunction.id from PubaccountFunction p where p.id.publicAccount.id = '")
			.append(wechatId).append("')");
		}
		List<?> list = null;
		list = this.commonData.getAllResult(hql.toString());
		return list;
	}
	
	public int addFunction(WechatFunctionVO vo){
		this.initRet();
		this.setRetMsg("添加失败!");
		
		WechatFunction po = new WechatFunction();
		po.setId(vo.getId());
		po.setWechatletName(vo.getWechatletName());
		po.setDescription(vo.getDescription());
		po.setClassname(vo.getClassname());
		
		this.commonData.createObject(po);
		
		
		String[] paIDs = vo.getPaIDs();
		if(paIDs != null){
			for (int i = 0; i < paIDs.length; i++) {
				PublicAccount pa = (PublicAccount) this.commonData.retrieveObject(PublicAccount.class, paIDs[i]);
				if (pa == null) {
					this.setRetMsg("公众号" + paIDs[i] + "不存在");
					return -1;
				}
				try {
					PubaccountFunctionId id = new PubaccountFunctionId(pa, po);
					PubaccountFunction pubFun = new PubaccountFunction(id);
					this.commonData.createObject(pubFun);
				} catch (Exception e) {
					e.printStackTrace();
					this.setRetMsg("内部错误");
					return -1;
				}
			}
		}
		
		
		
		this.setRetMsg("添加成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	
	public WechatFunction findById(String id){
		try {
			WechatFunction wechatFunction  = (WechatFunction) commonData.retrieveObject(WechatFunction.class, id);
			if (wechatFunction != null) {
				return  wechatFunction;
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
	
	public int modFunction(WechatFunctionVO vo){
		this.initRet();
		this.setRetMsg("修改失败!");
		// 修改function
		WechatFunction po = new WechatFunction();
		po.setId(vo.getId());
		po.setWechatletName(vo.getWechatletName());
		po.setDescription(vo.getDescription());
		po.setClassname(vo.getClassname());
		
		this.commonData.createOrUpdateObject(po);
		
		
		String[] paIDs = vo.getPaIDs();
		if(paIDs != null){
			for (int i = 0; i < paIDs.length; i++) {
				PublicAccount pa = (PublicAccount) this.commonData.retrieveObject(PublicAccount.class, paIDs[i]);
				if (pa == null) {
					this.setRetMsg("公众号" + paIDs[i] + "不存在");
					return -1;
				}
				try {
					PubaccountFunctionId id = new PubaccountFunctionId(pa, po);
					PubaccountFunction pubFun = new PubaccountFunction(id);
					this.commonData.createObject(pubFun);
				} catch (Exception e) {
					e.printStackTrace();
					this.setRetMsg("内部错误");
					return -1;
				}
			}
		}
		
		this.setRetMsg("修改成功!");
		this.setRetOK();
		return this.getRetCode();
		
	}
	
	public int delFunction(WechatFunctionVO vo){
		this.initRet();
		this.setRetMsg("删除失败!");
		
		
		WechatFunction po = (WechatFunction) this.findById(vo.getId());
		
		//删除关系表
		delPubAccountFunction(vo.getId());
		
		//删除实体
		commonData.deleteObject(po);
		this.setRetMsg("删除成功!");
		this.setRetOK();
		return this.getRetCode();
	} 
	
	public List<String> qryPubAccountIdByFunId(String FunId){
		
		String hql = "select p.id.publicAccount.id from PubaccountFunction p where p.id.wechatFunction.id = ? order by p.id.publicAccount.id";
		List list = commonData.getAllResult(hql, FunId);
		return list;
	}
	public void delPubAccountFunction(String FunId){
		String hql = "delete from PubaccountFunction p where p.id.wechatFunction.id = ?";
		commonData.batchDeleteOrUpdate(hql, FunId);
	}
	
	public void delPubAccountFunctionByWechatId(String wechatId){
		String hql = "delete from PubaccountFunction p where p.id.publicAccount.id = ?";
		commonData.batchDeleteOrUpdate(hql, wechatId);
	}
	
	public String qryFuncNameById(String id){
		WechatFunction wef = this.findById(id);
		if(wef == null){
			return null;
		}
		return wef.getWechatletName();
	}
	public boolean isUniqueFuncName(String name, String id){
		
		String hql = "select t.wechatletName from WechatFunction t where 1=1";
		
		if(id.length() > 0){
			hql += " and t.id <> '"+id+"'";
		}
		if(name.length() > 0){
			hql += " and t.wechatletName = '"+name+"'";
		}
		List<?> list = commonData.getAllResult(hql);
		
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}
	public int savePubFuncRelations(List<PubaccountFunctionVO> list){
		this.initRet();
		this.setRetMsg("配置失败!");
		if(list.size() > 0){
			PubaccountFunctionVO vo = null;
			for(int i = 0; i < list.size(); i++){
				vo = list.get(i);
				PublicAccount pub = (PublicAccount)commonData.retrieveObject(PublicAccount.class, vo.getWechatId());
				WechatFunction wef = (WechatFunction)commonData.retrieveObject(WechatFunction.class, vo.getWechatFunctionId());
				
				PubaccountFunctionId pubFuncId = new PubaccountFunctionId(pub, wef);
				PubaccountFunction po = new PubaccountFunction(pubFuncId);
				
				commonData.createOrUpdateObject(po);
				
			}
		}
		
		this.setRetMsg("配置成功!");
		this.setRetOK();
		return this.getRetCode();
	}
}
