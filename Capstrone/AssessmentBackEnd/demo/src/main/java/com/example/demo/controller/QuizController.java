package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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
import com.example.demo.entity.quiz;
import com.example.demo.response.Responsee;
import com.example.demo.service.CategoryService;
import com.example.demo.service.quizService;

@RestController
public class QuizController {
	@Autowired
	quizService qs;
	
	@Autowired
	CategoryService cs;
	
	@PostMapping("/quiz")
	public ResponseEntity<Object> saveQuiz(@RequestBody quiz q) {
		try {
			quiz user= qs.addQuiz(q);
			return Responsee.generateResponce("succcessfully add the data", HttpStatus.OK,"Quiz Topic_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Quiz Topic_Information", null);
		}
	}
	@GetMapping("/quiz/{id}")
	public ResponseEntity<Object> getQuiz(@PathVariable int id) {
		try {
			Optional<quiz> user= qs.getQuiz(id);
			return Responsee.generateResponce("succcessfully retrive the data", HttpStatus.OK,"Quiz topic_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"QuizTopic_Information", null);
		}
	}
	
	@GetMapping("/quiz")
	public ResponseEntity<Object> findAll(){
		try {
			List<quiz> user= qs.findAll();
			return Responsee.generateResponce("succcessfully retrive the data", HttpStatus.OK,"Quiz_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Quiz_Information", null);
		}
		
	}
	
	@DeleteMapping("/quiz/{id}")
	public ResponseEntity<Object> deleteQuiz(@PathVariable int id) {
		try {
			qs.deleteQuiz(id);
			return Responsee.generateResponce("succcessfully delete the data", HttpStatus.OK,"Quiz_Information", null);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Quiz_Information", null);
		}
		
	}
	
	
	@PutMapping("/quiz/{id}")
	public ResponseEntity<Object> updateQuiz(@RequestBody quiz q, @PathVariable int id) {
		try {
			quiz user=qs.updateQuiz(q, id);
			return Responsee.generateResponce("succcessfully update the data", HttpStatus.OK,"Quiz_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Quiz_Information", null);
		}
		
	}
	 @GetMapping("/quizz/{id}")
	   public ResponseEntity<Object> findQuizById(@PathVariable int id) {
		   try {
				List<quiz> user=qs.findQuizById(id);
				return Responsee.generateResponce("succcessfully update the data", HttpStatus.OK,"Quiz_Information", user);
			}
			catch(Exception e) {
				return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Quiz_Information", null);
			}
		
		  }
	 
	 @GetMapping("/quizByName/{name}")
	 public ResponseEntity<Object> findQuizByName(@PathVariable String name) {
		 try {
				Optional<quiz> user=qs.findQuizByName(name);;
				return Responsee.generateResponce("succcessfully update the data", HttpStatus.OK,"Quiz_Information", user);
			}
			catch(Exception e) {
				return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Quiz_Information", null);
			}
		  
	 }

}
