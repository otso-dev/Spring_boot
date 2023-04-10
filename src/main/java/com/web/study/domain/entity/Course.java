package com.web.study.domain.entity;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class Course {
	private int id;
	private int lecture_id;
	private int student_id;
	private LocalDate registe_date;
	
	private Lecture lecture;
	private Student student;
}
