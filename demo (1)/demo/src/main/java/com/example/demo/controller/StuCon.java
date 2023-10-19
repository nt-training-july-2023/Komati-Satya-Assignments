package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto;
import com.example.demo.entity.Student;
import com.example.demo.service.StuService;
import com.example.demo.serviceImp.StuServiceImp;

@RestController
public class StuCon {
	@Autowired
	private StuService stu;
//	public StuCon(StuService stu) {
//		this.stu=stu;
//	}
//	public StuCon() {
//		super();	
//		}
	
	@PostMapping("/student")
	public Student save(@RequestBody Student s) {
		return stu.saveStudent(s);
		
	}
	
	@GetMapping("/student/{id}")
   public Optional<Student> findById(@PathVariable int id) {
		
		return stu.findById(id);
	} 
	
	@GetMapping("/login")
	public String login(@RequestBody LoginDto l) {
		return stu.aunthenticateUser(l);
		
	}
	
	@GetMapping("/students")
    public List<Student> findAllStu() {
		
		return stu.findAllStu();
	}
	
	

}
