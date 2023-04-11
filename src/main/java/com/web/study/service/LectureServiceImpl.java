package com.web.study.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Lecture;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.dto.response.LectureRespDto;
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
	public List<LectureRespDto> getLectureAll() {
		List<LectureRespDto> dtos = new ArrayList<>();
		lectureRepository.getLectureAll().forEach(lecture ->{
			dtos.add(lecture.toDto());
		});
		return dtos;
	}

	@Override
	public LectureRespDto findLectureById(int id) {
		// TODO Auto-generated method stub
		return lectureRepository.findLectureById(id).toDto();
	}

	@Override
	public List<LectureRespDto> searchLecture(int type, String searchValue) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("type", type);
		parameterMap.put("searchValue", searchValue);
		List<LectureRespDto> dtos = new ArrayList<>();
		lectureRepository.searchLecture(parameterMap).forEach(entity->{
			dtos.add(entity.toDto());
		});
		return dtos;
	}

//	@Override
//	public void registeInstructor(InstructorReqDto instructorReqDto) {
//		Instructor instructor = instructorReqDto.toEntity();
//		lectureRepository.registeInstructor(instructor);
//		
//	}
//
//	@Override
//	public void registeStudent(StudentReqDto studentReqDto) {
//		Student student = studentReqDto.toEntity();
//		lectureRepository.regisetStudent(student);
//	}
//
//	@Override
//	public void registeLectureRegiste(LectureRegisteDto lectureRegisteDto) {
//		Course lectureRegiste = lectureRegisteDto.toEntity();
//		lectureRepository.regisetLectureRegiste(lectureRegiste);
//	}
	
	

}
