package com.shop.dto;

import com.shop.utils.BusinessStatus;

import java.io.Serializable;
import java.util.Objects;

public class ResponseDto<T extends Object> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3423399528666983701L;
	
	public String status;
    public String message;
    public T data;

    public ResponseDto(BusinessStatus businessStatus, T data) {
        if (Objects.isNull(businessStatus)) {
            throw new IllegalArgumentException("APIStatus must not be null");
        }
        this.status = String.valueOf(businessStatus.getCode());
        this.message = businessStatus.getMessage();
        this.data = data;
    }

    public ResponseDto(String message, String errorCode, T data) {
        this.status = errorCode;
        this.message = message;
        this.data = data;
    }
}
