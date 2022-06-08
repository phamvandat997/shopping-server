package com.shop.dto.exceptions;

import com.shop.utils.BusinessStatus;

public class BusinessCustomException extends ShopBaseException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7282670246786901159L;
	private final String errorCode;

    public BusinessCustomException(BusinessStatus businessStatus) {
        super(businessStatus.getMessage());
        this.errorCode = String.valueOf(businessStatus.getCode());
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}
