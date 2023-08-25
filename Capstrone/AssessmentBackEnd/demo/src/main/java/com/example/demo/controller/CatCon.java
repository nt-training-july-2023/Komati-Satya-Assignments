  package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.category;
import com.example.demo.service.CatService;

@RestController
public class CatCon {
	@Autowired
	private CatService cs;
	
	
	@PostMapping("/category")
	public category saveCat(@RequestBody category c)
	{
		return cs.saveCat(c);
		
	}
	
	@GetMapping("/cat/{id}")
	public  Optional<category> findById(@PathVariable int id){
		return cs.findById(id);
		
	}
	@GetMapping("/cat")
	public  List<category> findAll(){
		return cs.findAll();
		
	}
	
	@PutMapping("/cat/{id}")
	public category updateCat(@RequestBody category c, @PathVariable int id) {
		return cs.updateCat(c, id);
	}
    
	@DeleteMapping("cat/{id}")
	public void deleteCat(@PathVariable int id) {
         cs.deleteCat(id);
	}
   
}
