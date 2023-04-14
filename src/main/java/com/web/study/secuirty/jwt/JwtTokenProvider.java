package com.web.study.secuirty.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.web.study.dto.response.auth.JwtTokenRespDto;
import com.web.study.exception.CustomException;
import com.web.study.secuirty.PrincipalUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
	private final Key key;

	public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public JwtTokenRespDto createToken(Authentication authentication) {
		StringBuilder authoritiesBuilder = new StringBuilder();

		authentication.getAuthorities().forEach(grantedAuthority -> {
			authoritiesBuilder.append(grantedAuthority.getAuthority());
			authoritiesBuilder.append(",");
		});

		authoritiesBuilder.delete(authoritiesBuilder.length() - 1, authoritiesBuilder.length());

		String authorities = authoritiesBuilder.toString();

		long now = (new Date()).getTime();
		// 1000 == 1초
		Date tokenExpriesDate = new Date(now + 1000 * 60 * 30); // 토큰 만료시간.
		PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();

		String accessToken = Jwts.builder()
				.setSubject(authentication.getName())
				.claim("userId", userDetails.getUserId())
				.claim("auth", authorities)
				.setExpiration(tokenExpriesDate)
				.signWith(key, SignatureAlgorithm.HS256).compact();

		return JwtTokenRespDto.builder().grantType("Bearer").accessToken(accessToken).build();

	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (SecurityException | MalformedJwtException e) {
			// Security 라이브러리에 오류가 있거나, Malformed JSON의 포맷이 잘못된 형식의 JWT가 들어왔을때 예외
			// SignatureException이 포함된다.
			log.info("Invalid JWT TOKEN", e);
		} catch (ExpiredJwtException e) {
			// token의 유효기간이 만료된 경우의 예외
			log.info("Expired JWT TOKEN", e);
		} catch (UnsupportedJwtException e) {
			// jwt의 형식을 지키지 않은 경우 (Header.Payload.Signature)

			log.info("Unsupported JWT TOKEN", e);
		} catch (IllegalArgumentException e) {
			// JWT token이 없을 떄
			log.info("IllegalArgument JWT TOKEN", e);
		} catch (Exception e) {
			log.info("JWT TOKEN Error", e);
		}
		return false;
	}

	public Authentication getAuthentication(String accessToken) {
		Claims claims = parseClaims(accessToken);
		Object roles = claims.get("auth");
		if (roles == null) {
			throw new CustomException("권한 정보가 없는 토큰입니다.");
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		String[] rolesArray = roles.toString().split(",");
		Arrays.asList(rolesArray).forEach(role ->{
			authorities.add(new SimpleGrantedAuthority(role));
		});
		
		UserDetails userDetails = new User(claims.getSubject(),"",authorities);//security에서 제공하는 User Object
		
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}
