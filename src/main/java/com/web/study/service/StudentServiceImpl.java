package com.web.study.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Student;
import com.web.study.dto.request.student.StudentReqDto;
import com.web.study.dto.response.StudentRespDto;
import com.web.study.respository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

	private final StudentRepository studentRepository;
	
	@Override
	public void registeStudent(StudentReqDto studentReqDto) {
		Student student = studentReqDto.toEntity();
		studentRepository.saveStudent(student);
	}

	@Override
	public List<StudentRespDto> getStudentAll() {
		List<StudentRespDto> dtos = new ArrayList<>();
		studentRepository.getStudentAll().forEach(student ->{
			dtos.add(student.toDto());
		});
		return dtos;
	}

	@Override
	public StudentRespDto findStudentById(int id) {
		return studentRepository.findStudentById(id).toDto();
	}

}
