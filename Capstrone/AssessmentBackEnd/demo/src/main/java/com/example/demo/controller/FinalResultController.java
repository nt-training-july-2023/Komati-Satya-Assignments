package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.ResultDto;
import com.example.demo.response.Responsee;
import com.example.demo.service.FinalResService;

/**
 * Final result controller class.
 */
@RestController
@CrossOrigin(origins = "*")
public class FinalResultController {
    /**
     * auto wiring final result service.
     */
    @Autowired
    private FinalResService finalResService;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(FinalResultController.class);
    /**
     * Final result controller get by id method.
     * @param id category id.
     * @return response.
     */
    @GetMapping("/finalResult/{id}")
    public final ResponseEntity<Object> getById(@PathVariable final int id) {
        try {
            List<ResultDto> c = finalResService.getById(id);
            LOGGER.info("get result by student id");
            System.out.println(c);
            return Responsee.generateResponce("succcessfully retrieve the data",
                    HttpStatus.OK, "User_Information", c);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Result_Information", null);
        }
    }
    /**
     * find all students result.
     * @return response
     */
    @GetMapping("/finalResult")
    public final ResponseEntity<Object> findAllResult() {
        try {
            List<ResultDto> c = finalResService.findAll();
            LOGGER.info("get all results");
            System.out.println(c);
            return Responsee.generateResponce("succcessfully retrieve the data",
                    HttpStatus.OK, "Result_Information", c);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Result_Information", null);
        }
    }
}
