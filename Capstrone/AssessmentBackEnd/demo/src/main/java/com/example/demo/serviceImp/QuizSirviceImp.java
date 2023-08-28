package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.category;
import com.example.demo.entity.quiz;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.QuizRepo;
import com.example.demo.service.quizService;

@Service
public class QuizSirviceImp implements quizService{

	@Autowired
	QuizRepo qr;
	@Autowired
	CategoryRepo cr;
	@Override
	public quiz addQuiz(quiz q) {
	  if(cr.findById(q.getCate().getCategory_Id()).isPresent()) {
		if(!qr.findQuizByName(q.getTopic_Name()).isPresent())
				return qr.save(q);
		else {
			throw new AlreadyExistException("Topic is already exist,enter a new topic");
		}
	  }
	  else {
		  throw new NotFoundException("Category is not present");
	  }
	}
	@Override
	public Optional<quiz> getQuiz(int id) {
		if(qr.findAll().size()!=0) {
		if(qr.findById(id).isPresent()) {
		return qr.findById(id);
		}
		else {
			throw new NotFoundException("wrong quiz id");
		}
		}
		else {
			throw new AllNotFoundException("no quiz is present");
		}
	}
	@Override
	public List<quiz> findAll() {
		if(qr.findAll().size()!=0) {
		   return qr.findAll();
		}
		else {
			throw new AllNotFoundException("no quiz is present");
		}
	}
	@Override
	public void deleteQuiz(int id) {
		if(qr.findAll().size()!=0) {
		if(qr.findById(id).isPresent()) {
		qr.deleteById(id);
		}
		else {
			throw new NotFoundException("wrong quiz id");
		}
		}
		else {
			throw new AllNotFoundException("no quiz is present");
		}
		
	}
	@Override
	public quiz updateQuiz(quiz q, int id) {
		Optional<quiz> existingQuiz=qr.findById(id);
		if(existingQuiz.isPresent()) {
			quiz exiQuiz=existingQuiz.get();
			exiQuiz.setTopic_Name(q.getTopic_Name());
			exiQuiz.setTopic_Description(q.getTopic_Description());
			exiQuiz.setMax_Marks(q.getMax_Marks());
			exiQuiz.setPass_Marks(q.getPass_Marks());
			
			return qr.save(exiQuiz);
		}
		else {
			throw new NotFoundException("wrong quiz id");
		}
	}
	@Override
	public List<quiz> findQuizById(int id) {
		if(qr.findQuizById(id).size()!=0) {
		return qr.findQuizById(id);
		}
		else {
			throw new AllNotFoundException("no quiz is present");
		}
		
		
	}
	@Override
	public Optional<quiz> findQuizByName(String name) {
		if(qr.findAll().size()!=0) {
		if(qr.findQuizByName(name).isPresent())
		return qr.findQuizByName(name);
		else
			throw new NotFoundException("topic is not found");
	}
	
	else {
		throw new AllNotFoundException("no quiz is present");
	}
	
	}	

}
