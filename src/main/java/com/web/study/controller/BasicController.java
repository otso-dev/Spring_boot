package com.web.study.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ErrorResponseDto;
import com.web.study.dto.ResponseDto;

@RestController
public class BasicController {

//	@ResponseBody
	@GetMapping("/view/test")
	public ResponseEntity<? extends ResponseDto> view() {
		List<String> strList = new ArrayList<>();
		strList.add("a");
		strList.add("b");
		strList.add("c");
		strList.add("d");
		strList.add("e");

		if (strList.contains("e")) {
			try {
				throw new RuntimeException();
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST,e));
			}
		}
		return ResponseEntity.badRequest().body(DataResponseDto.of(strList));
	}
}

//RestController -> data만 응답하는 용도, view를 응답할려면, Controller를 써야한다. 결국 ResponseBody가 항상 붙어있다.
//Controller -> view만 응답하는 용도
//Controller는 view만 응답하니깐 ResponseBody를 써서 data를 응답하게 해줌
//return이 String일때만 text로 주고 그 외에는 JSON으로 응답을 보내준다.