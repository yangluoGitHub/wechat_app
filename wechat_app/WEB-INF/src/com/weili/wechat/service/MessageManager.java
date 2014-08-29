package com.weili.wechat.service;

import java.util.Map;

public interface MessageManager {
	public Map<String, String> sendMsg(Map<String, Object> params);
}
