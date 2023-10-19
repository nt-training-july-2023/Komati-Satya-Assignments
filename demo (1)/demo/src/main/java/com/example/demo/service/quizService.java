package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.category;
import com.example.demo.entity.quiz;

public interface quizService {

	quiz addQuiz(quiz q);
	Optional<quiz> getQuiz(int id);
	List<quiz> findAll();
	void deleteQuiz(int id); 
	quiz updateQuiz(quiz q,int id);
}
