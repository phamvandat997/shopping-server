package com.shop.utils;

public enum BusinessStatus {
    OK("OK", 200),
    USER_IS_EXISTED("User is existed in system", 501),
    USER_NOT_FOUND("User not found in system", 502)
    ;

    private final String message;
    private final int code;

    BusinessStatus(String message, int status) {
        this.message = message;
        this.code = status;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
