package com.web.study.respository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Authority;
import com.web.study.domain.entity.User;

@Mapper
public interface UserRepository {
	public int saveUser(User user);
	public int addAuthroities(List<Authority> authorities);
	public User findUserByUsername(String username); 
}
