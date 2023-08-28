package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.StudentResult;
import com.example.demo.entity.questions;

public interface ResultService {

	StudentResult addRes(StudentResult sr);
	Optional<StudentResult> getRes(int id);
	
	List<StudentResult> getAllRes();
	
	
}
