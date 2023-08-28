package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FinalRes;
import com.example.demo.entity.StudentResult;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.FinalResultRepo;
import com.example.demo.service.FinalResService;

@Service
public class FinalResultServiceImp implements FinalResService {
	
	@Autowired
    FinalResultRepo fs;
	@Override
	public Optional<FinalRes> getById(int id) {
		if(fs.findAll().size()!=0) {
		if(fs.findById(id).isPresent()) {
		  return fs.getByUserId(id);
		}
		else {
			throw new NotFoundException("User did not take the test");
		}
		}
		else {
			throw new AllNotFoundException("No user is there");
		}
	}

	
	}


