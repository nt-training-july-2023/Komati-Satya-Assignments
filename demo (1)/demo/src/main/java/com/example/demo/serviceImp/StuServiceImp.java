package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StuRepo;
import com.example.demo.service.StuService;

@Service
public class StuServiceImp implements StuService{
	
	
    @Autowired
	private StuRepo st;
    
    
    @Override
	public String aunthenticateUser(LoginDto stu) {
    	BCryptPasswordEncoder bcrypt =new BCryptPasswordEncoder();
    	
    	if(st.findById(stu.getUserId()).isPresent()) {
    	
   	Optional<Student> student=st.findById(stu.getUserId());
  	if(student.isPresent()) {
  		Student st=student.get();
  		if(bcrypt.matches(stu.getPassword(), st.getPassword()))
  			return "successful";
  		else
  			return "user not found";
  	}
    	}
    	else {
    		return "Wrong Id";
    	}
  	return null;
  	
  }
    
    
	@Override
	public Student saveStudent(Student s) {
		BCryptPasswordEncoder bcrypt =new BCryptPasswordEncoder();
		String encrypted=bcrypt.encode(s.getPassword());
		s.setPassword(encrypted);
		return st.save(s);
		 
		}

	@Override
	public Optional<Student> findById(int id) {
		
		return st.findById(id);
	}


	@Override
	public List<Student> findAllStu() {
		
		return st.findAll();
	}


	
	
	

}
