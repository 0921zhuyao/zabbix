package com.jd.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * zabbix配置
 * 
 * @author TrevorPhilips
 * @date 2019-04-4
 */
@Component
@ConfigurationProperties(prefix = "zabbix")
public class ZabbixConfig {

	private static String url;

	private static String userName;

	private static String password;

	private static String physicalServer;

	public static String getPhysicalServer() {
		return physicalServer;
	}

	public void setPhysicalServer(String physicalServer) {
		ZabbixConfig.physicalServer = physicalServer;
	}

	public static String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		ZabbixConfig.url = url;
	}

	public static String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		ZabbixConfig.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		ZabbixConfig.password = password;
	}

}
