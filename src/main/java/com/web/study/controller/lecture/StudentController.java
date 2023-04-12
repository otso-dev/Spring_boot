package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.aop.annotation.CheckNameAspect;
import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.student.StudentReqDto;
import com.web.study.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {
	
	private final StudentService studentService;
	
	//@RequestBody => Client -> Server Data 전송(JSON)
	// JSON의 형태 -> Object -> 같은 Object형태를 class로 만들어서 받는다.
	
	@PostMapping("/student")
	public ResponseEntity<? extends ResponseDto> registeStudent(@RequestBody StudentReqDto studentReqDto){
		studentService.registeStudent(studentReqDto);
		return ResponseEntity.ok().body(ResponseDto.ofDefalut());
	}
	
	
	@CheckNameAspect
	@GetMapping("/students")
	public ResponseEntity<? extends ResponseDto> getStudentAll(){
		return ResponseEntity.ok().body(DataResponseDto.of(studentService.getStudentAll()));
	}
	@GetMapping("/student/{id}")
	public ResponseEntity<? extends ResponseDto> findStudentById(@PathVariable int id){
		return ResponseEntity.ok().body(DataResponseDto.of(studentService.findStudentById(id)));
	}
	
}
