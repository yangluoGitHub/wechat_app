package com.weili.wechat.service.manage.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.dao.system.AccountDAO;
import com.weili.wechat.hibernate.PublicAccount;
import com.weili.wechat.service.manage.AccountService;
import com.weili.wechat.vo.PublicAccountVO;

public class AccountServiceImpl extends RetInfo implements AccountService{
	private static Log log = LogFactory.getLog(AccountServiceImpl.class);

	private CommonData commonData;
	private AccountDAO accountDAO;
	
	public AccountDAO getAccountDAO() {
		return accountDAO;
	}
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	public CommonData getCommonData() {
		return commonData;
	}
	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}

	@Override
	public List qry(Map<String, Object> params) {
		String wechatName = StringUtil.parseString(params.get("wechatName"));
		String wechatId = StringUtil.parseString(params.get("wechatId"));

		StringBuffer hql = new StringBuffer();
		hql.append("from PublicAccount as a where 1=1");

		if (wechatName.length() > 0)
			hql.append(" and a.wechatName like '%").append(wechatName).append("%'");
		if (wechatId.length() > 0)
			hql.append(" and a.wechatId = '").append(wechatId).append("' ");
		List<?> list = this.commonData.getAllResult(hql.toString());
		return list;
	}

	public int addAccount(PublicAccount publicAccount){
		this.initRet();
		this.setRetMsg("添加失败!");
		log.info("开始添加！");
		this.accountDAO.save(publicAccount);
		log.info("添加成功！");
		this.setRetMsg("添加成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	
	public PublicAccount findById(String id){
		try {
			PublicAccount publicAccount  = (PublicAccount) commonData.retrieveObject(PublicAccount.class, id);
			if (publicAccount != null) {
				return  publicAccount;
			}
			else {
				return null;
			}
		}
		catch (Exception e) {
			log.debug("查询机构失败");
			e.printStackTrace();
			return null;
		}
	}
	
	public int modAccount(PublicAccount publicAccount){
		this.initRet();
		this.setRetMsg("修改失败!");
		this.accountDAO.update(publicAccount);
		this.setRetMsg("修改成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	
	public int delAccount(PublicAccount publicAccount){
		this.initRet();
		this.setRetMsg("删除失败!");
		delUserPubAccountByAccountID(publicAccount.getId());
		commonData.deleteObject(publicAccount);
		this.setRetMsg("删除成功!");
		this.setRetOK();
		return this.getRetCode();
	}
	
	/**
	 * 根据公众号ID删除用户公众号权限
	 * @param id
	 */
	private void delUserPubAccountByAccountID(String id){
		String hql = "delete UserPubaccount u where u.publicAccount.id = ? ";
		commonData.batchDeleteOrUpdate(hql, id);
		String hql2 = "update OpTable o set o.wechatId = '' where o.wechatId = ? ";
		commonData.batchDeleteOrUpdate(hql2, id);
	}
	
	 public List<?> getAuthPubAccount(String opNo){
		 
		 String hql = "select t.publicAccount from UserPubaccount t where t.id.userId = '"+opNo+"' order by t.id.paId";
		  
		 List<PublicAccount> polist = commonData.getAllResult(hql);
		 
		 return this.po2_voList(polist);
		 
	 }
	 
	 private PublicAccountVO po2_vo(PublicAccount pubAccount){
		 
		 if(pubAccount == null){
			 return null;
		 }
		 PublicAccountVO pubAccountVo = new PublicAccountVO();
		 pubAccountVo.setId(pubAccount.getId());
		 pubAccountVo.setWechatId(pubAccount.getWechatId());
		 pubAccountVo.setWechatName(pubAccount.getWechatName());
		 pubAccountVo.setWechatType(pubAccount.getWechatType());
		 pubAccountVo.setAccessToken(pubAccount.getAccessToken());
		 pubAccountVo.setAppid(pubAccount.getAppid());
		 pubAccountVo.setAppsecret(pubAccount.getAppsecret());
		 pubAccountVo.setCreator(pubAccount.getCreator());
		 pubAccountVo.setToken(pubAccount.getToken());
		 pubAccountVo.setUrl(pubAccount.getUrl());
		 
		 return pubAccountVo;
		 
	 }
	 
	 private List<PublicAccountVO> po2_voList(List<PublicAccount> polist){
		 
		 if(polist == null || polist.size() == 0){
			 return null;
		 }
		 
		 PublicAccountVO vo = null;
		 List<PublicAccountVO> list = new ArrayList<PublicAccountVO>();
		 for(PublicAccount pubAccount : polist){
			 vo = this.po2_vo(pubAccount);
			 list.add(vo);
		 }
		 
		 return list;
	 }
	 
	public String getLogoLocation(String wechatid) {
		String hql = "from PublicAccount t where t.id = '"+wechatid+"'";
		 List<PublicAccount> polist = commonData.getAllResult(hql);
		 String logo =polist.get(0).getLogoLocation();
		return logo;
	}
	 
	 
}
