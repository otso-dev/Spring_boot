package com.web.study.secuirty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PrincipalUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	
	private int userId;
	private String username;
	private String password;
	private List<String> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(role ->{
			authorities.add(new SimpleGrantedAuthority(role));
		});
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}
	
	//사용기간 만료
	
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	
	// 계정을 잠굼
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//비밀번호 5회틀렸을 떄
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정을 비활성화할 떄, 이메일을 인증하거나, 전화번호 인증을 할 때
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
