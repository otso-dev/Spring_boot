package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//요청과 응답을 할 수 있는 어노테이션
public class HelloController {
	
	
	
	@GetMapping("/hello")//GET 요청
	public Map<String,String> hello(String name){
		Map<String,String> testMap = new HashMap<>();
		testMap.put("name", name);
		testMap.put("age", "29");
		testMap.put("address", "부산시 동래구 온천동");
		return testMap;
	}
}
