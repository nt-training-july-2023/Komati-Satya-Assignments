package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.quiz;
import com.example.demo.repository.QuizRepo;
import com.example.demo.service.quizService;

@Service
public class QuizSirviceImp implements quizService{

	@Autowired
	QuizRepo qr;
	@Override
	public quiz addQuiz(quiz q) {
		// TODO Auto-generated method stub
		return qr.save(q);
	}
	@Override
	public Optional<quiz> getQuiz(int id) {
		// TODO Auto-generated method stub
		return qr.findById(id);
	}
	@Override
	public List<quiz> findAll() {
		
		return qr.findAll();
	}
	@Override
	public void deleteQuiz(int id) {
		qr.deleteById(id);
		
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
		return q;
	}
	
	

}
