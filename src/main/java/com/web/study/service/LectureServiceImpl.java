package com.web.study.service;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Lecture;
import com.web.study.domain.entity.LectureRegiste;
import com.web.study.domain.entity.Lecturer;
import com.web.study.domain.entity.Student;
import com.web.study.dto.request.lecture.LectureRegisteDto;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.dto.request.lecture.LecturerReqDto;
import com.web.study.dto.request.lecture.StudentReqDto;
import com.web.study.respository.LectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService{

	private final LectureRepository lectureRepository;
	
	@Override
	public void registeLecture(LectureReqDto lectureReqDto) {
		//Dto가 ENTITY로 변환
		Lecture lecture = lectureReqDto.toEntity();
		System.out.println("변환"+ lecture);
		lectureRepository.registe(lecture);
	}

	@Override
	public void registeLecturer(LecturerReqDto lecturerReqDto) {
		Lecturer lecturer = lecturerReqDto.toEntity();
		lectureRepository.registeLecturer(lecturer);
		
	}

	@Override
	public void registeStudent(StudentReqDto studentReqDto) {
		Student student = studentReqDto.toEntity();
		lectureRepository.regisetStudent(student);
	}

	@Override
	public void registeLectureRegiste(LectureRegisteDto lectureRegisteDto) {
		LectureRegiste lectureRegiste = lectureRegisteDto.toEntity();
		lectureRepository.regisetLectureRegiste(lectureRegiste);
	}
	
	

}
