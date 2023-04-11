package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
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
	
	@GetMapping("/lectures")
	public ResponseEntity<? extends ResponseDto> getLectureAll(){
		return ResponseEntity.ok().body(DataResponseDto.of(lectureService.getLectureAll()));
	}
	
	@GetMapping("/lecture/{id}")
	public ResponseEntity<? extends ResponseDto> findLectureById(@PathVariable int id){
		System.out.println(id);
		return ResponseEntity.ok().body(DataResponseDto.of(lectureService.findLectureById(id)));
	}
	
	@GetMapping("/search/lecture")
	public ResponseEntity<? extends ResponseDto> searchLecture(int type, String searchValue){
		return ResponseEntity.ok().body(DataResponseDto.of(DataResponseDto.of(lectureService.searchLecture(type, searchValue))));
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
 * id, name(강의명), price(가격), instructor_id(강사)
 * 
 * 강사정보
 * id, name(강사명), birth_date(생년월일)
 */
