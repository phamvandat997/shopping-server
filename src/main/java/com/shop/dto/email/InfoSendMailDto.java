package com.shop.dto.email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InfoSendMailDto {
	
	private String subject;
	private String sendTo;
	private String bccMail;
	private String ccMail;
	private UserInfoSendMailDto userInfoSendMailDto;
}
