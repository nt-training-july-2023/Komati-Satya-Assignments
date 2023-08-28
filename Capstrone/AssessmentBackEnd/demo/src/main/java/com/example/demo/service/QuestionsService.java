package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.loadtime.Options;
import org.springframework.stereotype.Service;

import com.example.demo.entity.questions;
import com.example.demo.entity.quiz;



public interface QuestionsService {

	questions addQuestion(questions q);
	
	List<questions> getQuestions();
	
	void delete(int id);
	questions updateQue(questions q,int id);
	
    List<questions> findQueById(int id);
    Optional<questions> findByQuestion(String name);
	
}
