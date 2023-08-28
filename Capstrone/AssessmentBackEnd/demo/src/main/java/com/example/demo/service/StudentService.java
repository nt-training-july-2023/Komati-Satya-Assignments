package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDto;
import com.example.demo.entity.Student;



public interface StudentService {
   Student saveStudent(Student s);
   Optional<Student> findById(int id);
   Optional<Student> aunthenticateUser(LoginDto l);
   List<Student> findAllStu();
   Student updateStudent(Student s,int id);
   void deleteStudent(int id);
}
