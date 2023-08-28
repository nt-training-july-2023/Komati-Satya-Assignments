package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.category;


@Repository
public interface CategoryRepo extends JpaRepository<category,Integer>{

	
	@Query("select c from category c where c.category_Name=:name")
    Optional<category> findByCategoryName(String name);
	}
