package com.example.demo.service;

import java.util.Optional;

import org.aspectj.weaver.loadtime.Options;
import org.springframework.stereotype.Service;

import com.example.demo.entity.questions;
import com.example.demo.entity.quiz;



public interface QueService {

	questions addQuestion(questions q);
	
	Optional<questions> getQuestions(int id);
	
	void delete(int id);
	questions updateQue(questions q,int id);
	
//	List<questions> 
	
}
