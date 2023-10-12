package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.ResultDto;


/**
 * Result service interface.
 */
public interface ResultService {
    /**
     * add result.
     * @param resultDto student result
     * @return student result
     */
    ResultDto addResult(ResultDto resultDto);

    /**
     * get result method.
     * @param id student id
     * @return student result
     */
    ResultDto getResult(int id);

    /**
     * get all result method.
     * @return student result
     */
    List<ResultDto> getResults();
}
