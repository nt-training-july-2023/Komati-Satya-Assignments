package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentResult;
import com.example.demo.entity.questions;
import com.example.demo.repository.StuResultRepo;
import com.example.demo.service.ResService;

@Service
public class ResServiceImp implements ResService {
        @Autowired
		StuResultRepo s;
	
	@Override
	public StudentResult addRes(StudentResult sr) {
		
		return s.save(sr);
	}

	@Override
	public Optional<StudentResult> getRes(int id) {
		
		return s.findById(id);
	}

	@Override
	public List<StudentResult> getAllRes() {
		
		return s.findAll();
	}

}
