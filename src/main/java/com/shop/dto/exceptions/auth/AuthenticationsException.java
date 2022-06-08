package com.shop.dto.exceptions.auth;

import com.shop.dto.exceptions.ShopBaseException;
import com.shop.utils.ErrorCodes;

public class AuthenticationsException extends ShopBaseException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8432658350759064643L;
	
	private final String errorCode;

    public AuthenticationsException(ErrorCodes message) {
        super(message.getMessage());
        this.errorCode = message.getErrorCode();
    }

    public AuthenticationsException(String message) {
        super(message);
        this.errorCode = ErrorCodes.ERROR_AUTHENTICATION.getErrorCode();
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}
