package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudentResult;


@Repository
public interface StuResultRepo extends JpaRepository<StudentResult,Integer>{

}
