package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.BasicTestDto;


@RestController
public class BasicRestController {

	// GET 요청의 param을 처리하는 방법
	@GetMapping("/read")
	public ResponseEntity<? extends ResponseDto> read(BasicTestDto basicTestDto) {//class를 만들어서 request에 param을 받는 방법은 GET과 POST만 가능
		String userInfo =  basicTestDto.getName()+ "(" + basicTestDto.getAge() + ")";

		return ResponseEntity.ok().body(DataResponseDto.of(userInfo));
	}
	
	@GetMapping("/read2/{id}")//PathVariable이라고 부른다. POST, PUT, DELETE 다 가능하다.
	public ResponseEntity<? extends ResponseDto> read2(@PathVariable("id") int userId){
		
		Map<Integer, String> userMap = new HashMap<>();
		
		userMap.put(1, "정성일");
		userMap.put(2, "정성이");
		userMap.put(3, "정성삼");
		userMap.put(4, "정성사");
		userMap.put(5, "정성오");
		
		return ResponseEntity.ok().body(DataResponseDto.of(userMap.get(userId)));
	}
	
	//POST요청의 데이터 처리
	@PostMapping("/create")
	public ResponseEntity<? extends ResponseDto> create(@RequestBody BasicTestDto basicTestDto){//요청데이터가 JSON으로 받을려면 RequestBody를 붙여야한다.
		System.out.println("데이터 추가");
		return ResponseEntity.created(null).body(DataResponseDto.of(basicTestDto));
	}
	
	
}

//@RequestParam("age") int userAge, 매개변수에 get요청에 param을 받을 때 requestparam을 생략하고 매개변수에 이름을 param에 key값과 동일하게 쓸 수 있다.
//@RequestParam("name") String userName