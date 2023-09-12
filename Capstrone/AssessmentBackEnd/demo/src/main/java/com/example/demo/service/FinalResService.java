package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.ResultDto;


/**
 * Final result service interface.
 */
public interface FinalResService {
    /**
     * getById method.
     * @param id student id
     * @return final result
     */
    List<ResultDto> getById(int id);
    /**
     * find all results method.
     * @return result dto
     */
    List<ResultDto> findAll();
}
