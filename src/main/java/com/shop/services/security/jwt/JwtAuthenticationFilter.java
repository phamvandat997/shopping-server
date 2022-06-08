package com.shop.services.security.jwt;

import com.shop.dto.exceptions.auth.AuthenticationsException;
import com.shop.services.security.configuration.UserServiceProvider;
import com.shop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    protected JwtTokenProvider jwtTokenProvider;

    @Autowired
    protected UserServiceProvider userService;

    @Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
		try {
			// Get JWT from request
			String jwt = getJwtFromRequest(request);
			if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
				// Get id from JWT
				AuthInformation authInformation = jwtTokenProvider.getUserIdFromJwt(jwt);
				// Get information user detail
				UserDetails userDetails = userService.loadUserByUsername(authInformation.getEmail());
				if (Objects.nonNull(userDetails)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
			filterChain.doFilter(request, response);
		} catch (Exception ex) {
			throw new AuthenticationsException(ex.getMessage());
		}
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String result = null;
		String bearerToken = request.getHeader(Constants.AUTHORIZATION);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.AUTHENTICATION_SCHEME_BASIC)) {
			result = bearerToken.substring(7);
		}
		return result;
	}
}
