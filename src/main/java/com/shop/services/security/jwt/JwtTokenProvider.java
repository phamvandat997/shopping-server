package com.shop.services.security.jwt;

import com.shop.domain.Roles;
import com.shop.dto.exceptions.ValidationCustomException;
import com.shop.services.security.configuration.CustomUserDetails;
import com.shop.utils.ErrorCodes;
import com.shop.utils.JsonUtils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    // JWT secret key
    @Value("${auth.jwt.secret:default_jwt_secret}")
    protected String secret;

    // JWT expiration
    @Value("${auth.jwt.expiration:1024}")
    protected Long expiration;

    // Generate token from user information
	public String generateToken(CustomUserDetails customUserDetail) {
		Date now = new Date();
		Date dateExpiration = new Date(now.getTime() + expiration);
		AuthInformation authInformation = new AuthInformation.Builder().setEmail(customUserDetail.getUser().getEmail())
				.setUserName(customUserDetail.getUsername())
				.setRoles((Objects.nonNull(customUserDetail.getUser())
						&& !CollectionUtils.isEmpty(customUserDetail.getUser().getAuthorities()))
								? customUserDetail.getUser().getAuthorities().stream().map(Roles::getName)
										.collect(Collectors.toList())
								: null)
				.build();
		return Jwts.builder().setSubject(JsonUtils.toJson(authInformation)).setIssuedAt(now)
				.setExpiration(dateExpiration).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

    public AuthInformation getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return JsonUtils.toObject(claims.getSubject(), AuthInformation.class);
    }

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			throw new ValidationCustomException(ErrorCodes.ERROR_INVALID_JWT_TOKEN);
		} catch (ExpiredJwtException ex) {
			throw new ValidationCustomException(ErrorCodes.ERROR_EXPIRED_TOKEN);
		} catch (UnsupportedJwtException ex) {
			throw new ValidationCustomException(ErrorCodes.ERROR_UNSUPPORTED_JWT_TOKEN);
		} catch (IllegalArgumentException ex) {
			throw new ValidationCustomException(ErrorCodes.ERROR_CLAMS_STRING_IS_EMPTY);
		}
	}
}
