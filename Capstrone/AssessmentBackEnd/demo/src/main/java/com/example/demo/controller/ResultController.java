package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.StudentResult;
import com.example.demo.entity.questions;
import com.example.demo.response.Responsee;
import com.example.demo.service.ResultService;

@RestController
public class ResultController {
	
	@Autowired
	ResultService rs;
	
	@PostMapping("/res")
    public ResponseEntity addRes(@RequestBody StudentResult sr) {
		
		try {
			StudentResult user= rs.addRes(sr);
			return Responsee.generateResponce("succcessfully update the data", HttpStatus.OK,"Student result_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Student result_Information", null);
		}
	}
	
	@GetMapping("/res/{id}")
public ResponseEntity getRes(@PathVariable int id) {
		
		try {
			Optional<StudentResult> user= rs.getRes(id);
			return Responsee.generateResponce("succcessfully retrive the data", HttpStatus.OK,"Student result_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"result_Information", null);
		}
	}
@GetMapping("/res")
public ResponseEntity<Object> getAllRes() {
	try {
		List<StudentResult> user= rs.getAllRes();
		return Responsee.generateResponce("succcessfully retrive the data", HttpStatus.OK,"Student result_Information", user);
	}
	catch(Exception e) {
		return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"result_Information", null);
	}	
	
	}


}
