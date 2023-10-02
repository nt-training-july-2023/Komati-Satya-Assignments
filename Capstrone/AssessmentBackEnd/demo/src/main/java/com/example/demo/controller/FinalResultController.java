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
import com.example.demo.response.Response;
import com.example.demo.service.FinalResService;
import com.example.demo.validationMessages.Messages;

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
    public final Response getById(
            @PathVariable final int id) {
            List<ResultDto> resultDto = finalResService.getById(id);
            String message = Messages.FIND_RESULT;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message,resultDto);
       return errorResponse;
    }
    /**
     * find all students result.
     * @return response
     */
    @GetMapping("/finalResult")
    public final Response findAllResult() {
            List<ResultDto> resultDto = finalResService.findAll();
            LOGGER.info(Messages.FIND_ALLRESULT);
            String message = Messages.FIND_ALLRESULT;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message,resultDto);
       return errorResponse;
    }
}
