package com.weili.wechat.service.manage;

import java.util.List;
import java.util.Map;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.hibernate.WechatArticle;
import com.weili.wechat.hibernate.WechatResource;
import com.weili.wechat.vo.ImageTextDetail;

public interface ImageTextService extends IRetInfo {
	
	/**
	 * 根据参数查询WechatResource
	 * @param params
	 * @return
	 */
	public List qryResource(Map<String, Object> params);
	
	public WechatResource qryById(String logicId);
	
	public int addImageText(WechatResource wechatResource);
	
	public int modImageText(WechatResource wechatResource);
	
	public int delImageText(String logicId);
	
	public List<ImageTextDetail> qryImageTextDetailByMultiResId(String multiresourceId);
	
	
	/**
	 * 
	 */
	public  int updateArticle(WechatArticle wechatArticle);
	
	public WechatArticle qryArticleById(String id);
	
	public List<WechatArticle> qryArticleSByFunctionName(String functionName);
	
	public Map<String, Object> clickMenuResponse(String eventKey);
	
}
