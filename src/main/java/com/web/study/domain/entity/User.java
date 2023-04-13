package com.web.study.domain.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private int user_id;
	private String username;
	private String password;
	private String name;
	private String email;
	
	private List<Authority> authorities;
}
