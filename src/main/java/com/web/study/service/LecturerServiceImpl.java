package com.web.study.service;

import org.springframework.stereotype.Service;

import com.web.study.dto.request.lecturer.LecturerReqDto;
import com.web.study.respository.LecturerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService{

	private final LecturerRepository lecturerRepository;
	
	@Override
	public void registeLecturer(LecturerReqDto lecturerReqDto) {
		System.out.println(lecturerReqDto);
		lecturerRepository.saveLecturer(lecturerReqDto.toEntity());
	}

}
