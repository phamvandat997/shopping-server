package com.shop.utils;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCodes {
    ERROR_SOMETHING_WENT_WRONG("Something went wrong. Please try again", 1),
    ERROR_INVALID_JWT_TOKEN("Invalid jwt token", 2),
    ERROR_EXPIRED_TOKEN("Expired jwt token", 3),
    ERROR_UNSUPPORTED_JWT_TOKEN("Unsupported JWT token", 4),
    ERROR_CLAMS_STRING_IS_EMPTY("JWT claims string is empty.", 5),
    ERROR_USER_NOT_EXIST("User not exist in system", 6),
    ERROR_PASSWORD_NOT_MATCH("Password not match", 7),
    ERROR_AUTHENTICATION("Failed on set user authentication", 8),
    ERROR_REQUIRED_PARAM(" is required!", 9),
    ERROR_MAXIMUM_EMAIL("Email is maximum 260 character!", 10),
    ERROR_INVALID_EMAIL("Invalid email", 11),
    ERROR_INVALID_PHONE_NUMBER("Invalid phone number", 12),
    ERROR_INVALID_PASSWORD("Invalid password", 13)
    ;
    private final String message;
    private final String errorCode;

    ErrorCodes(String message, int errorCode) {
        this.message = message;
        this.errorCode = "E" + errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
