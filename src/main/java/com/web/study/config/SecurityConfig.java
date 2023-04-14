package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.web.study.secuirty.jwt.JwtAuthentiationFilter;
import com.web.study.secuirty.jwt.JwtAuthenticationEntryPoint;
import com.web.study.secuirty.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

//WebSecurityConfigurerAdapter security를 설정할수있는 Object

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// security filter
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //csrf 페이지 token 해킹방지.
		http.httpBasic().disable();// 웹 기본 인증 방식
		http.formLogin().disable();// form태그 를 통한 로그인
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// session 비활성화
		http.authorizeRequests()
			.antMatchers("/auth/register/**","/auth/login/**")//url에 대한 권한을 설정
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.addFilterBefore(new JwtAuthentiationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)//UsernamePasswordAuthenticationFilter.class 고정
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint); 

	}
}
