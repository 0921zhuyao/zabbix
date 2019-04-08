package com.jd.framework.shiro.authc;

public enum LoginType {
	AMDIN("admin"), APP("app");

	private String type;

	private LoginType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type.toString();
	}
}
