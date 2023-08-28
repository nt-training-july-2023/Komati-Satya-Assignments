package com.example.demo.serviceImp;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.category;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.AllNotFoundException;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.StudentRepo;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{
	
    private CategoryRepo a;
	 public CategoryServiceImp(CategoryRepo st) {
			
		 this.a=st;
		}
	  
		@Override
		public category saveCat(category c) {
			if(!a.findByCategoryName(c.getCategory_Name()).isPresent()) {
			return a.save(c);
			}
			else {
				throw new AlreadyExistException("Category already present");
			}
		}

		@Override
		public Optional<category> findById(int id) {
			if(a.findAll().size()!=0) {
			if(a.findById(id).isPresent()) {
			
			return a.findById(id);
			}
			else {
				throw new NotFoundException("wrong category id");
			}
			}
			else {
				throw new AllNotFoundException("no category is there");
			}
		}
		
		@Override
		public List<category> findAll() {
			if(a.findAll().size()!=0) {
			
			return a.findAll();
			}
			else {
				throw new AllNotFoundException("No categories are there");
			}
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
	        else {
	        	throw new NotFoundException("wrong category id");
	        }
	    }

		@Override
		public void deleteCat(int id) {
			if(a.findAll().size()!=0) {
           if(a.findById(id).isPresent()) {
			 a.deleteById(id);
           }
           else {
        	   throw new NotFoundException("wrong category id");
           }
			}
			else {
				throw new AllNotFoundException("No categories are there");
			}
		}

		@Override
		public Optional<category> findByName(String s) {
			if(a.findAll().size()!=0) {
			if(a.findByCategoryName(s).isPresent()) {
			return a.findByCategoryName(s);
			}
			else {
				 throw new NotFoundException("Category not present");
			}
			}
			else {
				throw new AllNotFoundException("No categories are there");
			}
		}
	
		}


