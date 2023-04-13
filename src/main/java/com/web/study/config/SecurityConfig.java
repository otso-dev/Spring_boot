package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//WebSecurityConfigurerAdapter security를 설정할수있는 Object

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// security filter
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //csrf 페이지 token 해킹방지.
		http.authorizeRequests()
			.antMatchers("/auth/register/**","/auth/login/**")//url에 대한 권한을 설정
			.permitAll()
			.anyRequest()
			.authenticated();
	}
}
