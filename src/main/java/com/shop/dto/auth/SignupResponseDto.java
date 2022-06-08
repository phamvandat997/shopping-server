package com.shop.dto.auth;

import com.shop.utils.Constants;

import java.io.Serializable;

public class SignupResponseDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2531289677427056395L;
	
	public String accessToken;
    public String accessType;

    public SignupResponseDto(String accessToken) {
        this.accessToken = accessToken;
        this.accessType = Constants.AUTHENTICATION_SCHEME_BASIC.trim();
    }
}
