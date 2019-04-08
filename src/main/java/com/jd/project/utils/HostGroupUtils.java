package com.jd.project.utils;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.jd.common.constant.ZabbixAPIMethodConstant;

import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;

@SuppressWarnings("rawtypes")
public class HostGroupUtils {

	/**
	 * 获取所有主机组信息
	 * 
	 * @return
	 */
	public static List<Map> getHostGroup() {
		RequestBuilder requestBuilder = RequestBuilder.newBuilder().method(ZabbixAPIMethodConstant.HOST_GROUP_GET);
		Request request = requestBuilder.paramEntry("output", "extend").build();
		List<Map> hostEntities = JSONArray.parseArray(ZabbixUtils.zabbixRequest(request), Map.class);
		return hostEntities;
	}

	/**
	 * 根据入参筛选主机组信息
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static List<Map> getHostGroup(Map<String, Object> paramsMap) {
		Request request = ZabbixUtils.queryParams(paramsMap, ZabbixAPIMethodConstant.HOST_GROUP_GET);
		List<Map> hostEntities = JSONArray.parseArray(ZabbixUtils.zabbixRequest(request), Map.class);
		return hostEntities;
	}
}
