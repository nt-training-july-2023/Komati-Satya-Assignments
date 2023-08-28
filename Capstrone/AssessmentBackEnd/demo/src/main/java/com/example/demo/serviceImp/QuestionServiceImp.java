package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.questions;
import com.example.demo.entity.quiz;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.QuestionsRepo;
import com.example.demo.repository.QuizRepo;
import com.example.demo.service.QuestionsService;


@Service
public class QuestionServiceImp implements QuestionsService{

	@Autowired
	QuestionsRepo qr;
	@Autowired
	QuizRepo qrr;
	
	@Override
	public questions addQuestion(questions q) {
		if(qrr.findById(q.getQui().getQuiz_Id()).isPresent()) {
		if(!qr.findByQuestion(q.getQuestion()).isPresent()) {
		return qr.save(q);
		}
		else {
			throw new AlreadyExistException("question already exist");
		}
		}
		else {
			throw new NotFoundException("Quiz topic is not there");
		}
	}

	@Override
	public List<questions> getQuestions() {
		if(qr.findAll().size()!=0) {
		  return qr.findAll();
		}
		else {
			throw new AllNotFoundException("no question is present");
		}
	}

	@Override
	public void delete(int id) {
	if(qr.findAll().size()!=0) {
		if(qr.findById(id).isPresent())
		  qr.deleteById(id);
		else
			throw new NotFoundException("wrong question Id,enter a valid Id");
	}
	else {
		throw new AllNotFoundException("no question is present");
	}
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
		else {
			throw new NotFoundException("wrong question Id,enter a valid Id");
		}
	}

	@Override
	public List<questions> findQueById(int id) {
		if(qr.findAll().size()!=0) {
		if(qr.findQueById(id).size()!=0) {
		  return qr.findQueById(id);
		}
		else {
			throw new NotFoundException("wrong question Id,enter a valid Id");
		}
		}
		else {
			throw new AllNotFoundException("no question is present");
		}
	}

	@Override
	public Optional<questions> findByQuestion(String name) {
		if(qr.findAll().size()!=0) {
		if(qr.findByQuestion(name).isPresent()) {
		return qr.findByQuestion(name);
		}
		else {
			throw new NotFoundException("Question is not there, enter a valid question");
		}
		}
		else {
			throw new AllNotFoundException("no question is present");
		}
	}
	
	

}
