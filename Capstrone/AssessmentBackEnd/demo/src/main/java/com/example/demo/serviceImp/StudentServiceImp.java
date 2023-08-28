package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDto;
import com.example.demo.entity.FinalRes;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentResult;
import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.exceptions.EmailDoesNotExistException;
import com.example.demo.exceptions.PasswordMissMatchException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.repository.StudentRepo;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImp implements StudentService{
	
	
    @Autowired
	private StudentRepo st;
    
    
    @Override
	public Optional<Student> aunthenticateUser(LoginDto stu) {
    	 
    	BCryptPasswordEncoder bcrypt =new BCryptPasswordEncoder();
   
    	Optional<Student> student=st.findByEmail(stu.getEmail());
  	    if(student.isPresent()) {
  		Student st=student.get();
  		if(bcrypt.matches(stu.getPassword(), st.getPassword())) {
  	     
  			return student;}
  		else {
  			
         throw new PasswordMissMatchException("password must be same");
  			}
  	    }
  	    else
  	    {
  	    	throw new EmailDoesNotExistException("Email not exist");
  	    }
    	
  	
  	
  }
    
    
	@Override
	public Student saveStudent(Student s) {
		if(!st.findByEmail(s.getEmail()).isPresent()) {
		BCryptPasswordEncoder bcrypt =new BCryptPasswordEncoder();
		String encrypted=bcrypt.encode(s.getPassword());
		s.setPassword(encrypted);
		return st.save(s);
		}
		else {
		      throw new DuplicateEmailException("Email already exist");
		}
		 
		}

	@Override
	public Optional<Student> findById(int id) {
		if(st.findById(id).isPresent()) {
		return st.findById(id);
		}
		else {
			throw new NotFoundException("User not found");
		}
	}


	@Override
	public List<Student> findAllStu() {
		if(st.findAll().size()!=0){
		return st.findAll();
		}
		else
		{
			throw new AllNotFoundException("No user is present");
		}
	}


	@Override
	public Student updateStudent(Student s, int id) {
		Optional<Student> user=st.findById(id);
		if(user.isPresent()) {
			Student exiStudent=user.get();
			exiStudent.setDateOfBirth(s.getDateOfBirth());
			exiStudent.setGender(s.getGender());
			exiStudent.setPassword(s.getPassword());
			exiStudent.setPhoneNumber(s.getPhoneNumber());
			exiStudent.setUserName(s.getUserName());
			
			return st.save(exiStudent);
			
		}
		else {
			 throw new NotFoundException("User not found,give a correct id");
		}
		
		
		
	}


	@Override
	public void deleteStudent(int id) {
		if(st.findAll().size()!=0) {
		if(st.findById(id).isPresent())
		st.deleteById(id);	
		
	else {
		 throw new NotFoundException("User not found,give a correct id");
	}
		}
		else {

			throw new AllNotFoundException("No user is present");
		}
}
}
