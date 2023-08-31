package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.demo.entity.Student;

/**
 * repository for Student.
 */
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    /**
     * query for student.
     * @param email student email
     * @return student
     */
    @Query("select s from Student s where s.email=:email")
    Optional<Student> findByEmail(String email);
}
