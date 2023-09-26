package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private ResultService resultService;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ResultController.class);
    /**
     * adding result method.
     * @param sr student result
     * @return response
     */
    @PostMapping("/result")
    public final ResponseEntity<String> addRes(
            @RequestBody final ResultDto sr) {
            ResultDto resultDto = resultService.addRes(sr);
            LOGGER.info("Adding result");
            return ResponseEntity.ok("result added successfully");
    }

    /**
     * getting all result by id method.
     * @param id result id
     * @return response
     */
    @GetMapping("/result/{id}")
    public final ResponseEntity<Optional<ResultDto>> getRes(@PathVariable final int id) {
            Optional<ResultDto> resultDto = resultService.getRes(id);
            LOGGER.info("Get result by id");
            return ResponseEntity.ok(resultDto);
    }

    /**
     * getting all results method.
     * @return response
     */
    @GetMapping("/result")
    public final ResponseEntity<List<ResultDto>> getAllRes() {
            List<ResultDto> resultDto = resultService.getAllRes();
            LOGGER.info("getting all result");
            return ResponseEntity.ok(resultDto);
    }
}
