package com.example.demo.service;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import com.example.demo.entity.Student;
import com.example.demo.entity.category;

public interface CatService {
	
	category saveCat(category c);
	 Optional<category> findById(int id);
	 List<category> findAll();
	 category updateCat(category c,int id);
	 void deleteCat(int id);
}
