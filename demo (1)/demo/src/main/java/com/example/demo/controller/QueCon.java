package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.questions;
import com.example.demo.service.QueService;

@RestController
public class QueCon {
	
	@Autowired
	QueService qs;
	
	@PostMapping("/que")
	public questions addQuestion(@RequestBody questions q) {
		
	return qs.addQuestion(q);
	}
	
	@GetMapping("/que/{id}")
public Optional<questions> getQuestions(@PathVariable int id) {
		
		return qs.getQuestions(id);
	}

   @DeleteMapping("/que/{id}")
   public void delete(@PathVariable int id) {
	   qs.delete(id);
	   
   }
   @PutMapping("/que/{id}")
   public questions updateQue(@RequestBody questions q, @PathVariable int id) {
	   return qs.updateQue(q, id);
   }

}
