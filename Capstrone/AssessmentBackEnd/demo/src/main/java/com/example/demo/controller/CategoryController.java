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

import com.example.demo.entity.category;
import com.example.demo.response.Responsee;
import com.example.demo.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService cs;
	
	
	@PostMapping("/category")
	public ResponseEntity<Object> saveCat(@RequestBody category c)
	{
		try {
			category user= cs.saveCat(c);
			return Responsee.generateResponce("succcessfully added data", HttpStatus.OK,"Category_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Category_Information", null);
		}
		
	}
	
	@GetMapping("/cat/{id}")
	public  ResponseEntity<Object> findById(@PathVariable int id){
		try {
			Optional<category> user= cs.findById(id);
			return Responsee.generateResponce("succcessfully retrieve the data", HttpStatus.OK,"Category_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Category_Information", null);
		
		}
	}
	@GetMapping("/cat")
	public  ResponseEntity<Object> findAll(){
		try {
			List<category> user= cs.findAll();
			return Responsee.generateResponce("succcessfully retrieve the data", HttpStatus.OK,"Category_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Category_Information", null);
		}
		
	}
	
	@PutMapping("/cat/{id}")
	public ResponseEntity<Object> updateCat(@RequestBody category c, @PathVariable int id) {
		try {
			category user= cs.updateCat(c, id);
			return Responsee.generateResponce("succcessfully update the data", HttpStatus.OK,"Category_Information", user);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Category_Information", null);
		}
	}
    
	@DeleteMapping("/cat/{id}")
	public ResponseEntity<Object> deleteCat(@PathVariable int id) {
		try {
		 cs.deleteCat(id);
			return Responsee.generateResponce("succcessfully delete the data", HttpStatus.OK,"User_Information",null);
		}
		catch(Exception e) {
			return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Category_Information", null);
		}
	}
	
	@GetMapping("/category/{s}")
	public ResponseEntity<Object> findByName(@PathVariable String s) {
		try {
			Optional<category> c=cs.findByName(s);
			 //System.out.println(c);
				return Responsee.generateResponce("succcessfully retrieve the data", HttpStatus.OK,"User_Information",c);
			}
			catch(Exception e) {
				return Responsee.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS,"Category_Information", null);
			}
		
	}
   
}
