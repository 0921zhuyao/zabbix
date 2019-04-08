package com.jd.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 */
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectPropertiesConfig {
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 版本
	 */
	private String version;
	/**
	 * 版权年份
	 */
	private String copyrightYear;
	/**
	 * 上传路径
	 */
	private static String profile;
	/**
	 * 获取地址开关
	 */
	private static boolean addressEnabled;

	private static int minNum;

	private static int maxNum;

	private static long companyRoleId;

	private static long streetRoleId;

	public static long getCompanyRoleId() {
		return companyRoleId;
	}

	public void setCompanyRoleId(long companyRoleId) {
		ProjectPropertiesConfig.companyRoleId = companyRoleId;
	}

	public static long getStreetRoleId() {
		return streetRoleId;
	}

	public void setStreetRoleId(long streetRoleId) {
		ProjectPropertiesConfig.streetRoleId = streetRoleId;
	}

	public static int getMinNum() {
		return minNum;
	}

	public void setMinNum(int minNum) {
		ProjectPropertiesConfig.minNum = minNum;
	}

	public static int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		ProjectPropertiesConfig.maxNum = maxNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCopyrightYear() {
		return copyrightYear;
	}

	public void setCopyrightYear(String copyrightYear) {
		this.copyrightYear = copyrightYear;
	}

	public static String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		ProjectPropertiesConfig.profile = profile;
	}

	public static boolean isAddressEnabled() {
		return addressEnabled;
	}

	public void setAddressEnabled(boolean addressEnabled) {
		ProjectPropertiesConfig.addressEnabled = addressEnabled;
	}

}
