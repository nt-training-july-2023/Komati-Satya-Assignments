package com.example.demo.serviceImp;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.category;
import com.example.demo.repository.CatRepo;
import com.example.demo.repository.StuRepo;
import com.example.demo.service.CatService;

@Service
public class CatServiceImp implements CatService{
	
	 
		private CatRepo a;
	 public CatServiceImp(CatRepo st) {
			
		 this.a=st;
		}
	  
		@Override
		public category saveCat(category c) {
			
			return a.save(c);
		}

		@Override
		public Optional<category> findById(int id) {
			
			return a.findById(id);
		}
		
		@Override
		public List<category> findAll() {
			
			return a.findAll();
		}

		@Override
		public category updateCat(category c, int id) {
			Optional<category> existingCategoryOptional = a.findById(id);

	        if (existingCategoryOptional.isPresent()) {
	            category existingCategory = existingCategoryOptional.get();
	            existingCategory.setCategory_Name(c.getCategory_Name());
	            existingCategory.setCategory_Description(c.getCategory_Description());
	            
	            return a.save(existingCategory);
	        }
			return c;
	    }

		@Override
		public void deleteCat(int id) {

			 a.deleteById(id);;
		}
	
		}


