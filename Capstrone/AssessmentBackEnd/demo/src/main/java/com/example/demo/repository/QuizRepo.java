package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.category;
import com.example.demo.entity.quiz;

@Repository
public interface QuizRepo extends JpaRepository<quiz,Integer> {
	
    @Query("select q from quiz q where q.cate.category_Id=:id")
    List<quiz> findQuizById(int id);
    
    @Query("select q from quiz q where q.topic_Name=:name")
    Optional<quiz> findQuizByName(String name);

}
