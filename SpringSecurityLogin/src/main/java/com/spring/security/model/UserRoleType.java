package com.spring.security.model;

public enum UserRoleType {
	USER("USER"),
	ADMIN("ADMIN"),
	DBA("DBA");
	String roleType;
	private UserRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getRoleType() {
		return roleType;
	}
}
