package com.jd.framework.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UserToken extends UsernamePasswordToken{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//登录类型
	private String loginType;

	public UserToken(final String username, final String password,String loginType) {
		super(username,password);
		this.loginType = loginType;
	}

	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
}
