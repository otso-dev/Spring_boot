package com.web.study.respository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Lecture;
import com.web.study.domain.entity.Course;
import com.web.study.domain.entity.Lecturer;
import com.web.study.domain.entity.Student;

@Mapper
public interface LectureRepository {
	
	public int registe(Lecture lecture);
	
	public List<Lecture> getLectureAll();
	
	public Lecture findLectureById(int id);
	
//	public int registeLecturer(Lecturer lecturer);
//	
//	public int regisetStudent(Student student);
//	
//	public int regisetLectureRegiste(Course lectureRegiste);
}
