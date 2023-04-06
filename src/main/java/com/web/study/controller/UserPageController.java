package com.web.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserPageController {
	
	@GetMapping("/page/user/addition")
	public String addUser() {
		return "AddUser";
	}
	
	@GetMapping("/page/user/search")
	public String searchUser() {
		return "FindUser";
	}
	
	@GetMapping("/page/users")
	public  String getUser() {
		return "UserList";
	}

}
