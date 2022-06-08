package com.shop.dto.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoSendMailDto {
	
	private String userName;
	private String fullName;
	private String address;
	private String phoneNumber;
}
