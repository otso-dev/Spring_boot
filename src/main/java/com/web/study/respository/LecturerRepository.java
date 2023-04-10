package com.web.study.respository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Lecturer;

@Mapper
public interface LecturerRepository {
	public void saveLecturer(Lecturer lecturer);
	public List<Lecturer> getLecturerAll();
	public Lecturer findLecturerById(int id);
}
