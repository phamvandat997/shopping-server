package com.shop.services;

import com.shop.domain.User;
import com.shop.dto.auth.SignupRequestDto;
import com.shop.dto.exceptions.BusinessCustomException;
import com.shop.dto.exceptions.auth.AuthenticationsException;
import com.shop.services.security.configuration.CustomUserDetails;
import com.shop.services.security.jwt.JwtTokenProvider;
import com.shop.utils.BusinessStatus;
import com.shop.utils.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


public interface AuthService {
    String login(SignupRequestDto request);
    void logout(HttpServletRequest request, HttpServletResponse response);

    @Service
    class AuthServiceImpl implements AuthService {
        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        JwtTokenProvider tokenProvider;

        @Autowired
        UserService userService;

        @Autowired
        protected PasswordEncoder passwordEncoder;

        @Override
        public String login(SignupRequestDto request) {
            User user = userService.findUserByEmail(request.getUserName());
            // check user is existed in system with email
            if (Objects.isNull(user)) {
                throw new BusinessCustomException(BusinessStatus.USER_NOT_FOUND);
            }
            // verify password match password user input
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new AuthenticationsException(ErrorCodes.ERROR_PASSWORD_NOT_MATCH);
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return tokenProvider.generateToken(new CustomUserDetails(user));
        }

        @Override
        public void logout(HttpServletRequest request, HttpServletResponse response) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.nonNull(auth)) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
        }
    }
}
