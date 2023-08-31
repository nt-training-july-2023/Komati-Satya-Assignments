package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FinalRes;

/**
 * FinalResult repository.
 */
@Repository
public interface FinalResultRepo extends JpaRepository<FinalRes, Integer> {
    /**
     * query for final result.
     * @param id userId
     * @return final result
     */
    @Query("select f from FinalRes f where f.userId=:id")
    Optional<FinalRes> getByUserId(int id);
}
