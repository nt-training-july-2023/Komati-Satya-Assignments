package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FinalRes;
import com.example.demo.entity.StudentResult;
import com.example.demo.entity.questions;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.FinalResultRepo;
import com.example.demo.repository.QuizRepo;
import com.example.demo.repository.StudentRepo;
import com.example.demo.repository.StudentResultRepo;
import com.example.demo.service.ResultService;

@Service
public class ResultServiceImp implements ResultService {
        @Autowired
		StudentResultRepo s;
        
        @Autowired
        FinalResultRepo f;
        @Autowired
        QuizRepo qr;
        @Autowired
        CategoryRepo cr;
        @Autowired
        StudentRepo srr;
	
	@Override
	public StudentResult addRes(StudentResult sr) {
			FinalRes fr=new FinalRes();
			fr.setCategoryName(sr.getQe().getCate().getCategory_Name());
			fr.setUserId(sr.getSs().getUserId());
			fr.setUserName(sr.getSs().getUserName());
			fr.setQuizTopic(sr.getQe().getTopic_Name());
			fr.setResult(sr.getResult());
			fr.setDate_and_time(sr.getDateAndTime());
			fr.setMarks(sr.getQe().getMax_Marks());
			fr.setResultId(sr.getResult_Id());
			fr.setMax_marks(sr.getMax_Marks());
			fr.setEmail(sr.getSs().getEmail());
			
			if(qr.findQuizByName(fr.getQuizTopic()).isPresent())
			{
				if(cr.findByCategoryName(fr.getCategoryName()).isPresent())
				{
					if(srr.findById(fr.getUserId()).isPresent()) {
						if(srr.findByEmail(fr.getEmail()).isPresent()) {
							f.save(fr);
						}
						else {
							throw new NotFoundException("wrong email id");
						}
					}
					else {
						throw new NotFoundException("wrong userId");
					}
				}
				else {
					throw new NotFoundException("Category is not present");
				}
				
			}
			else {
				throw new NotFoundException("Quiz is not present");
			}
			f.save(fr);
			
		return s.save(sr);
	}

	@Override
	public Optional<StudentResult> getRes(int id) {
		if(s.findAll().size()!=0) {
		if(s.findById(id).isPresent()) {
		  return s.findById(id);
		}
		else {
			throw new NotFoundException("wrong user Id,enter a valid Id");
		}
		}
		else {
			throw new AllNotFoundException("no studentresult is present");
		}
	}

	@Override
	public List<StudentResult> getAllRes() {
		if(s.findAll().size()!=0) {
		return s.findAll();
		}
		else {
			throw new AllNotFoundException("no studentresult is present");
		}
	}

}
