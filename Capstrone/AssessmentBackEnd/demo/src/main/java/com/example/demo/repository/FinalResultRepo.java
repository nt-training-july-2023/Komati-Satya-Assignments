package com.example.demo.repository;

import java.util.List;
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
     * @param userId userId
     * @return final result
     */
    @Query("select f from FinalRes f where f.userId=:userId")
    List<FinalRes> getByUserId(int userId);
}
