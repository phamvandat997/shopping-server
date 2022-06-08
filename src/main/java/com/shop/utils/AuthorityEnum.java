package com.shop.utils;

public enum AuthorityEnum {
    ADMIN("Admin"), USER("User"), CUSTOMER("Customer");
	
	private final String value;
	
	private AuthorityEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
