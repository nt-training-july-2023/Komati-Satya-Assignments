package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.StudentResult;

/**
 * Student result repository.
 */
@Repository
public interface StudentResultRepo
        extends JpaRepository<StudentResult, Integer> {
}
