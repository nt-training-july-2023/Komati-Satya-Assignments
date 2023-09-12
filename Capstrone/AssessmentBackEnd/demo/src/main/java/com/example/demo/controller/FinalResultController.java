package com.example.demo.controller;

import java.util.List;

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
    private FinalResService fs;
    /**
     * Final result controller get by id method.
     * @param id category id.
     * @return response.
     */
    @GetMapping("/finalResult/{id}")
    public final ResponseEntity<Object> getById(@PathVariable final int id) {
        try {
            List<ResultDto> c = fs.getById(id);
            System.out.println(c);
            return Responsee.generateResponce("succcessfully retrieve the data",
                    HttpStatus.OK, "User_Information", c);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Result_Information", null);
        }
    }
    @GetMapping("/finalResult")
    public final ResponseEntity<Object> findAllResult() {
        try {
            List<ResultDto> c = fs.findAll();
            System.out.println(c);
            return Responsee.generateResponce("succcessfully retrieve the data",
                    HttpStatus.OK, "Result_Information", c);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Result_Information", null);
        }
    }
}
