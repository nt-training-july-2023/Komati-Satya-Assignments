package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.FinalRes;
import com.example.demo.entity.category;
import com.example.demo.response.Responsee;
import com.example.demo.service.FinalResService;

@RestController
public class FinalResultController {
	
	@Autowired
	FinalResService fs;

	@GetMapping("/finalResult/{id}")
	public ResponseEntity<Object> getById(@PathVariable int id){
		try {
			Optional<FinalRes> c=fs.getById(id);
			 System.out.println(c);
				return Responsee.generateResponce("succcessfully retrieve the data", HttpStatus.OK,"User_Information",c);
			}
			catch(Exception e) {
				return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Category_Information", null);
			}
		
	}
}
