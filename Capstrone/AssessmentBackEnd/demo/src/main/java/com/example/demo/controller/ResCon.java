package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.StudentResult;
import com.example.demo.service.ResService;

@RestController
public class ResCon {
	
	@Autowired
	ResService rs;
	
	@PostMapping("/res")
    public StudentResult addRes(@RequestBody StudentResult sr) {
		
		return rs.addRes(sr);
	}
	
	@GetMapping("/res/{id}")
public Optional<StudentResult> getRes(@PathVariable int id) {
		
		return rs.getRes(id);
	}
@GetMapping("/res")
public List<StudentResult> getAllRes() {
		
		return rs.getAllRes();
	}


}
