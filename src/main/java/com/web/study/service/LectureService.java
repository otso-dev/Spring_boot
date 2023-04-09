package com.web.study.service;

import com.web.study.dto.request.lecture.LectureRegisteDto;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.dto.request.lecture.LecturerReqDto;
import com.web.study.dto.request.lecture.StudentReqDto;

public interface LectureService {
	public void registeLecture(LectureReqDto lectureReqDto);
	public void registeLecturer(LecturerReqDto lecturerReqDto);
	public void registeStudent(StudentReqDto studetreqDto);
	public void registeLectureRegiste(LectureRegisteDto lectureRegisteDto);
}
