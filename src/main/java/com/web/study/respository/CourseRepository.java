package com.web.study.respository;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Course;

@Mapper
public interface CourseRepository {
	public void saveCourse(Course course);
}
