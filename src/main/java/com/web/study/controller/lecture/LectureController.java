package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.service.LectureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LectureController {
	
	private final LectureService lectureService;
	
	// Create
	@PostMapping("/lecture")
	public ResponseEntity<? extends ResponseDto> register(@RequestBody LectureReqDto lectureReqDto) {
		lectureService.registeLecture(lectureReqDto);
		return ResponseEntity.ok().body(ResponseDto.ofDefalut());
	}

	// Read
	public ResponseEntity<? extends ResponseDto> get() {

		return ResponseEntity.ok().body(ResponseDto.ofDefalut());
	}

	// Update
	public ResponseEntity<? extends ResponseDto> modify() {

		return ResponseEntity.ok().body(ResponseDto.ofDefalut());
	}

	// Delete
	public ResponseEntity<? extends ResponseDto> remove() {

		return ResponseEntity.ok().body(ResponseDto.ofDefalut());
	}
}

//DB Table 설계
/*
 * 학생정보
 * id, name(학생명), birth_date(생년월일)
 * 
 * 수강정보
 * id, lecture_id(강의), student_id(학생), registe_date(등록일)
 * 
 * 강의정보
 * id, name(강의명), price(가격), lecturer_id(강사)
 * 
 * 강사정보
 * id, name(강사명), birth_date(생년월일)
 */
