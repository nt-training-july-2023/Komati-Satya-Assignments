package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto;
import com.example.demo.entity.Student;
import com.example.demo.response.Responsee;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService stu;
//	public StuCon(StuService stu) {
//		this.stu=stu;
//	}
//	public StuCon() {
//		super();	
//		}
	
	@PostMapping("/student")
	public ResponseEntity<Object> save(@RequestBody Student s) {
		try {
			Student user= stu.saveStudent(s);
			return Responsee.generateResponce("successfully added data", HttpStatus.OK,"User_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"User_Information", null);
		
		}
		
	}
	
	@GetMapping("/student/{id}")
   public ResponseEntity<Object> findById(@PathVariable int id) {
		
		try {
			Optional<Student> user= stu.findById(id);
			return Responsee.generateResponce("successfully get the data", HttpStatus.OK,"User_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"User_Information", null);
		
		}
	} 
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginDto l) {
		try {
			Optional<Student> user= stu.aunthenticateUser(l);
			return Responsee.generateResponce("succcessfully retrieve the data", HttpStatus.OK,"User_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"User_Information", null);
		
		}
		
	}
	
	@GetMapping("/students")
    public ResponseEntity<Object> findAllStu() {
		
		try {
			List<Student> user= stu.findAllStu();
			return Responsee.generateResponce("successfully retrieve all the data", HttpStatus.OK,"User_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"User_Information", null);
		
		}
	}
	
	@PutMapping("/student/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student s, @PathVariable int id) {
		try {
			Student user=stu.updateStudent(s, id);
			return Responsee.generateResponce("successfully update the data", HttpStatus.OK,"User_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"User_Information", null);
		
		}
		
		
	}
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable int id){
		try {
			stu.deleteStudent(id);
			return Responsee.generateResponce("successfully delete the data", HttpStatus.OK,"User_Information",null);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"User_Information", null);
		
		}
	}
	
	
}


