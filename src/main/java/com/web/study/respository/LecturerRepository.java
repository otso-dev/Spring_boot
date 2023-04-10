package com.web.study.respository;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Lecturer;

@Mapper
public interface LecturerRepository {
	public void saveLecturer(Lecturer lecturer);
}
