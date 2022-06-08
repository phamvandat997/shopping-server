package com.shop.web.rest;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.ResponseDto;
import com.shop.dto.auth.SignupRequestDto;
import com.shop.dto.auth.SignupResponseDto;
import com.shop.dto.exceptions.auth.AuthenticationsException;
import com.shop.services.AuthService;
import com.shop.utils.BusinessStatus;
import com.shop.utils.Constants;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<SignupResponseDto>> login(@Valid @RequestBody SignupRequestDto loginRequest) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, MessagingException, TemplateException, IOException {
        try {
            String token = authService.login(loginRequest);
            SignupResponseDto loginResponse = new SignupResponseDto(token);
            return ResponseEntity.ok()
                    .header(Constants.AUTHORIZATION, token)
                    .body(new ResponseDto<>(BusinessStatus.OK, loginResponse));
        } catch (AuthenticationsException ex) {
            return ResponseEntity.ok().body(new ResponseDto<>(ex.getMessage(), ex.getErrorCode(), null));
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<ResponseDto<String>> logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);
        return ResponseEntity.ok().body(new ResponseDto<>(BusinessStatus.OK, Constants.LOGOUT_SUCCESS));
    }
}
