package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.StudentResult;

/**
 * Result service interface.
 */
public interface ResultService {
    /**
     * add result.
     * @param sr student result
     * @return student result
     */
    StudentResult addRes(StudentResult sr);

    /**
     * get result method.
     * @param id student id
     * @return student result
     */
    Optional<StudentResult> getRes(int id);

    /**
     * get all result method.
     * @return student result
     */
    List<StudentResult> getAllRes();
}
