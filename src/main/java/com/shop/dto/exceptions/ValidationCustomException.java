package com.shop.dto.exceptions;

import com.shop.utils.ErrorCodes;

public class ValidationCustomException extends ShopBaseException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4472965304956304011L;
	
	private final String errorCode;

    public ValidationCustomException(ErrorCodes messageCode) {
        super(messageCode.getMessage());
        this.errorCode = messageCode.getErrorCode();
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}
