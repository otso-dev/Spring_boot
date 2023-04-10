package com.web.study.respository;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Student;

@Mapper
public interface StudentRepository {
	public int saveStudent(Student student);
}
