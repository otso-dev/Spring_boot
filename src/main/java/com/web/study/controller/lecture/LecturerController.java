package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.lecturer.LecturerReqDto;
import com.web.study.service.LecturerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LecturerController {
	
	private final LecturerService lecturerService;
	
	@PostMapping("/lecturer")
	public ResponseEntity<? extends ResponseDto> registe(@RequestBody LecturerReqDto lecturerReqDto){
//		System.out.println(lecturerReqDto);
		lecturerService.registeLecturer(lecturerReqDto);
		return ResponseEntity.ok().body(ResponseDto.ofDefalut());
	}
}
