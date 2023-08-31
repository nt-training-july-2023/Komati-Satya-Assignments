package com.example.demo.service;

import java.util.Optional;
import com.example.demo.entity.FinalRes;

/**
 * Final result service interface.
 */
public interface FinalResService {
    /**
     * getById method.
     * @param id student id
     * @return final result
     */
    Optional<FinalRes> getById(int id);
}
