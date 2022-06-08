package com.shop.dto.user;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class UserRequestDto {
	
	private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private boolean gender;
    private Date birthday;
    private String avatar;
    private String code;
    private String jobTitle;
    private String department;
    private String mobileNumber;
    private String address;
    private String city;
    private Boolean status;
    private String reEnterPassword;
    private String lang;
    private String roles;
}
