package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.questions;



@Repository
public interface QuestionsRepo extends JpaRepository<questions,Integer>{

	@Query("select q from questions q where q.qui.quiz_Id=:id")
    List<questions> findQueById(int id);
    
    @Query("select q from from questions q where q.question=:question")
    Optional<questions> findByQuestion(String question);
}
