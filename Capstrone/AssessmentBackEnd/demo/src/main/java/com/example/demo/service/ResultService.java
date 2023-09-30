package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.ResultDto;


/**
 * Result service interface.
 */
public interface ResultService {
    /**
     * add result.
     * @param sr student result
     * @return student result
     */
    ResultDto addResult(ResultDto sr);

    /**
     * get result method.
     * @param id student id
     * @return student result
     */
    Optional<ResultDto> getResult(int id);

    /**
     * get all result method.
     * @return student result
     */
    List<ResultDto> getResults();
}
