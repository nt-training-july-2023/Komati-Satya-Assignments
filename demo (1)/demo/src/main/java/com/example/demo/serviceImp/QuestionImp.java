package com.example.demo.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.questions;
import com.example.demo.entity.quiz;
import com.example.demo.repository.QueRepo;
import com.example.demo.service.QueService;


@Service
public class QuestionImp implements QueService{

	@Autowired
	QueRepo qr;
	
	@Override
	public questions addQuestion(questions q) {
		
		return qr.save(q);
	}

	@Override
	public Optional<questions> getQuestions(int id) {
		
		return qr.findById(id);
	}

	@Override
	public void delete(int id) {
		qr.deleteById(id);
		
	}

	@Override
	public questions updateQue(questions q, int id) {
		
		Optional<questions> existingQue=qr.findById(id);
		if(existingQue.isPresent()) {
			questions exiQue=existingQue.get();
			exiQue.setOption1(q.getOption1());
			exiQue.setOption2(q.getOption2());
			exiQue.setOption3(q.getOption3());
			exiQue.setOption4(q.getOption4());
			exiQue.setCorrect_option(q.getCorrect_option());
			exiQue.setQuestion(q.getQuestion());
			return qr.save(exiQue);
		}
		return q;
	}
	
	

}
