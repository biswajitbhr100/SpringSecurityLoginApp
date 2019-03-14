package com.spring.security.model;

public enum UserStateType {
	ACTIVE("Active"),
	INACIVE("Inactive"),
	DELETED("Deleted"),
	LOCKED("Locked");
	String userStateType;
	private UserStateType(String userStateType) {
		this.userStateType = userStateType;
	}
	public String getUserStateType() {
		return userStateType;
	}
}
