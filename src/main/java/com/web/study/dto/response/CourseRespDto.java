package com.web.study.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CourseRespDto {
	private int courseId;
	private int lecturePrice;
	private LocalDate registeDate;
	private String lectureName;
	private String instructorName;
	private String studentName;
}
