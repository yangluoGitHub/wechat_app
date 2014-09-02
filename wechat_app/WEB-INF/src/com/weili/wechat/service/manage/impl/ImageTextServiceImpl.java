package com.weili.wechat.service.manage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.hibernate.WechatArticle;
import com.weili.wechat.hibernate.WechatMedia;
import com.weili.wechat.hibernate.WechatResource;
import com.weili.wechat.service.manage.ImageTextService;
import com.weili.wechat.vo.ImageTextDetail;
import com.weili.wechatCom.message.resp.Article;

public class ImageTextServiceImpl extends RetInfo implements ImageTextService  {
	
	private static Log log = LogFactory.getLog(ImageTextServiceImpl.class);
	
	private CommonData commonData;

	public CommonData getCommonData() {
		return commonData;
	}

	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}
	
	@Override
	public List qryResource(Map<String, Object> params) {
		String title = StringUtil.parseString(params.get("title"));
		String startDate = StringUtil.parseString(params.get("startDate"));
		String endDate = StringUtil.parseString(params.get("endDate"));
		String wechatid = StringUtil.parseString(params.get("wechatid"));
		String OtherConditions = StringUtil.parseString(params.get("OtherConditions"));
		StringBuffer hql = new StringBuffer("from WechatResource where 1=1 ");
		if (!"".equals(wechatid)) {
			hql.append("and wechatId = '").append(wechatid).append("' ");
		}
		if (!"".equals(title)) {
			hql.append("and resourceTittle like '%" + title + "%' ");
		}
		if (!"".equals(startDate) && !"".equals(endDate)) {
			hql.append("and createDate >= '" + startDate + "' ");
			hql.append("and createDate <= '" + endDate + "' ");
		}
		if (!"".equals(OtherConditions)) {
			if("multiresourceId is null".equals(OtherConditions)){
				hql.append("and (multiresourceId='' or "+OtherConditions+") ");
			}else{
				hql.append("and " + OtherConditions + " ");
			}
		}
		hql.append("order by createDate desc, createTime desc ");

		List list = commonData.getAllResult(hql.toString());

		return list;
	}

	public WechatResource qryById(String logicId) {
		WechatResource wechatResource = (WechatResource) commonData.retrieveObject(WechatResource.class, logicId);
		return wechatResource;
	}
	
	@Override
	public int addImageText(WechatResource wechatResource) {
		this.setRetMsg("添加图文信息失败");
		this.initRet();
		
		commonData.createOrUpdateObject(wechatResource);
		
		this.setRetMsg("添加图文信息成功");
		this.setRetOK();
		
		return this.getRetCode();
	}
	
	@Override
	public int modImageText(WechatResource wechatResource) {
		this.setRetMsg("修改图文信息失败");
		this.initRet();
		
		commonData.createOrUpdateObject(wechatResource);
		
		this.setRetMsg("修改图文信息成功");
		this.setRetOK();
		
		return this.getRetCode();
	}
	
	public int delImageText(String logicId) {
		this.setRetMsg("删除图文信息失败");
		this.initRet();
		
		WechatResource wechatResource = (WechatResource) commonData.retrieveObject(WechatResource.class, logicId);
		if (wechatResource == null) {
			this.setRetMsg("该图文信息不存在");
			return this.getRetCode();
		}
		String id = wechatResource.getId();
		commonData.deleteObject(wechatResource);
		
		//删除多图文中的信息
		List<WechatResource> list = commonData.retrieveAllObjects(WechatResource.class);
		for (WechatResource r : list) { 
			String multiResourceId = r.getMultiresourceId();
			if (multiResourceId == null || multiResourceId.equals(""))
				continue;
			if (multiResourceId.indexOf(id) > -1) {
				String[] idArr = multiResourceId.split("\\|");
				StringBuffer idsb = new StringBuffer();
				for (String thisid : idArr) {
					if (!thisid.equals(id)) {
						idsb.append(thisid + "|");
					}
				}
				String newMultiId = idsb.toString().substring(0, idsb.toString().length() - 1);
				r.setMultiresourceId(newMultiId);
				commonData.updateObject(r);
			}
		}
		// 删除wechat_article 中业务关联信息
		
		String HQL ="from WechatArticle w where w.id='"+id+"' or w.id like '"+id+"%'";
		List<WechatArticle> list1 = commonData.getAllResult(HQL);
		for(WechatArticle wechatArticle : list1){
			commonData.deleteObject(wechatArticle);
		}
		
		this.setRetMsg("删除图文信息成功");
		this.setRetOK();
		return this.getRetCode();
	}
	
	public List<ImageTextDetail> qryImageTextDetailByMultiResId(String multiresourceId){
		List<ImageTextDetail> list = new ArrayList<ImageTextDetail>();
		String[] ids = multiresourceId.split("\\|");
		for (String id : ids) {
			WechatResource wr = this.qryById(id);
			if (wr == null)
				continue;
			ImageTextDetail detail = new ImageTextDetail();
			String mediaId = wr.getMediaId();
			WechatMedia wm = (WechatMedia) this.commonData.retrieveObject(WechatMedia.class, mediaId);
			detail.setResource(wr);
			detail.setMedia(wm);
			list.add(detail);
		}
		return list;
	}

	
	public int updateArticle(WechatArticle wechatArticle) {
		this.setRetMsg("添加图文信息表失败");
		this.initRet();
		commonData.createOrUpdateObject(wechatArticle);
		
		this.setRetMsg("添加图文信息表成功");
		this.setRetOK();
		
		return this.getRetCode();
	}

	@Override
	public WechatArticle qryArticleById(String id) {
		WechatArticle wechatArticle =null;
		wechatArticle = (WechatArticle) commonData.retrieveObject(WechatArticle.class,id);
		return wechatArticle;
	}
	
	public List<WechatArticle> qryArticleSByFunctionName(String functionName){
		String HQL ="from WechatArticle w where w.function='"+functionName+"'";
		List<WechatArticle> list = commonData.getAllResult(HQL);
		return list;
	}

	@Override
	public Map<String, Object> clickMenuResponse(String eventKey) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	private List<Article> transport(List<WechatArticle> list){
//		
//		Map<String, WechatArticle> map = new HashMap<String, WechatArticle>();
//		String key = null;
//		for(WechatArticle w : list){
//		
//			key = w.getType().equals("title") ? "-1" : w.getId().split("\\|")[1];
//			map.put(key, w);
//		}
//		Article art = null;
//		for(int i = -1; i< list.size(); i++){
//			WechatArticle wa = map.get(""+i);
//			art = new Article();
//			art.setTitle(wa.getArticleTitle());
//		}
//		
//	}
//	public Map<String, Object> clickMenuResponse(String eventKey){
//		
//		//完善的 可能有一套完整的检索流程
//		//目前默认'click'响应图文信息 无图文信息时相应 文字回复
//		List<Article> list = transport(qryArticleSByFunctionName(eventKey));
//		
//	}
	
}
