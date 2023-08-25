package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.quiz;
import com.example.demo.service.quizService;

@RestController
public class QuizCon {
	@Autowired
	quizService qs;
	
	@PostMapping("/quiz")
	public quiz saveQuiz(@RequestBody quiz q) {
		return qs.addQuiz(q);
	}
	@GetMapping("/quiz/{id}")
	public Optional<quiz> getQuiz(@PathVariable int id) {
		// TODO Auto-generated method stub
		return qs.getQuiz(id);
	}
	
	@GetMapping("/quiz")
	public List<quiz> findAll(){
		return qs.findAll();
		
	}
	
	@DeleteMapping("/quiz/{id}")
	public void deleteQuiz(@PathVariable int id) {
		qs.deleteQuiz(id);
		
	}
	
	
	@PutMapping("/quiz/{id}")
	public quiz updateQuiz(@RequestBody quiz q, @PathVariable int id) {
		return qs.updateQuiz(q, id);
		
	}

}
