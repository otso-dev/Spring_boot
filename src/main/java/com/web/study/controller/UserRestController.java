package com.web.study.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.UserAdditionDto;

@RestController
public class UserRestController {
	@PostMapping("/api/user/addition")
	public ResponseEntity<? extends ResponseDto> addUser(@RequestBody UserAdditionDto userAdditionDto){
		System.out.println(userAdditionDto);
		return ResponseEntity.ok().body(DataResponseDto.of(userAdditionDto));
		//created()안에 url을 넣어서 데이터생성에 성공됐으면 넣은 url주소로 이동한다.
	}
}
