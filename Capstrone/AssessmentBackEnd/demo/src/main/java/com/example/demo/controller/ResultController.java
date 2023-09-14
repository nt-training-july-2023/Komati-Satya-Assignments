package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResultDto;
import com.example.demo.response.Responsee;
import com.example.demo.service.ResultService;

/**
 * result controller class.
 */
@RestController
@CrossOrigin(origins = "*")
public class ResultController {
    /**
     * auto wiring result service class.
     */
    @Autowired
    private ResultService rs;

    /**
     * adding result method.
     * @param sr student result
     * @return response
     */
    @PostMapping("/res")
    public final ResponseEntity<Object> addRes(
            @RequestBody final ResultDto sr) {
        try {
            ResultDto user = rs.addRes(sr);
            return Responsee.generateResponce("succcessfully add the data",
                    HttpStatus.OK, "Student result_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Student result_Information",
                    null);
        }
    }

    /**
     * getting all result by id method.
     * @param id result id
     * @return response
     */
    @GetMapping("/res/{id}")
    public final ResponseEntity<Object> getRes(@PathVariable final int id) {
        try {
            Optional<ResultDto> user = rs.getRes(id);
            return Responsee.generateResponce("succcessfully retrive the data",
                    HttpStatus.OK, "Student result_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "result_Information", null);
        }
    }

    /**
     * getting all results method.
     * @return response
     */
    @GetMapping("/res")
    public final ResponseEntity<Object> getAllRes() {
        try {
            List<ResultDto> user = rs.getAllRes();
            return Responsee.generateResponce("succcessfully retrive the data",
                    HttpStatus.OK, "Student result_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "result_Information", null);
        }
    }
}
