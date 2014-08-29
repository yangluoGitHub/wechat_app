package com.weili.wechat.vo;

import com.weili.wechat.hibernate.WechatMedia;
import com.weili.wechat.hibernate.WechatResource;

public class ImageTextDetail {
	private WechatResource resource;
	private WechatMedia media;

	public WechatMedia getMedia() {
		return media;
	}

	public void setMedia(WechatMedia media) {
		this.media = media;
	}

	public WechatResource getResource() {
		return resource;
	}

	public void setResource(WechatResource resource) {
		this.resource = resource;
	}
}
