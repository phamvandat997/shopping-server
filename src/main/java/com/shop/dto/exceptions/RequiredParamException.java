package com.shop.dto.exceptions;

import com.shop.utils.ErrorCodes;

public class RequiredParamException extends ShopBaseException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6887672275681278195L;
	
	private final String errorCode;

    public RequiredParamException(String fieldName) {
        super(fieldName + ErrorCodes.ERROR_REQUIRED_PARAM);
        this.errorCode = ErrorCodes.ERROR_REQUIRED_PARAM.getErrorCode();
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}
