package com.example.demo.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.category;
import com.example.demo.entity.questions;
import com.example.demo.entity.quiz;
import com.example.demo.response.Responsee;
import com.example.demo.service.QuestionsService;

@RestController
public class QuestionsController {
	
	@Autowired
	QuestionsService qs;
	
	@PostMapping("/que")
	public ResponseEntity<Object> addQuestion(@RequestBody questions q) {
		try {
			questions user= qs.addQuestion(q);
			return Responsee.generateResponce("succcessfully add the data", HttpStatus.OK,"Questions_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Questions_Information", null);
		}	
	
	}
	
	@GetMapping("/que")
public ResponseEntity<Object> getQuestions() {
		
	try {
		List<questions> user= qs.getQuestions();
		return Responsee.generateResponce("succcessfully retrive the data", HttpStatus.OK,"Questions_Information", user);
	}
	catch(Exception e) {
		return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Questions_Information", null);
	}
	}

   @DeleteMapping("/que/{id}")
   public ResponseEntity<Object> delete(@PathVariable int id) {
	   try {
			qs.delete(id);
			return Responsee.generateResponce("succcessfully update the data", HttpStatus.OK,"Questions_Information",null);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Questions_Information", null);
		}
	   
   }
   @PutMapping("/que/{id}")
   public ResponseEntity<Object> updateQue(@RequestBody questions q, @PathVariable int id) {
	   try {
			questions user= qs.updateQue(q, id);
			return Responsee.generateResponce("succcessfully get the data", HttpStatus.OK,"Questions_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Questions_Information", null);
		}
   }
   
   @GetMapping("/questions/{id}")
   public  ResponseEntity<Object>  findQueById(@PathVariable int id){
	   try {
			List<questions> user= qs.findQueById(id);
			return Responsee.generateResponce("succcessfully get the data", HttpStatus.OK,"Questions_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Questions_Information", null);
		}
   }
   
   @GetMapping("/questionByName/{name}")
   public ResponseEntity<Object> findByQuestion(@PathVariable String name){
	   try {
			Optional<questions> user= qs.findByQuestion(name);
			return Responsee.generateResponce("succcessfully get the data", HttpStatus.OK,"Questions_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Questions_Information", null);
		}
   }
  


}
