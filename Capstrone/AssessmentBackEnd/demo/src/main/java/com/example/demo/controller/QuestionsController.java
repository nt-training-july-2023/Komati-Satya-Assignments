package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.QuestionsDto;
import com.example.demo.dto.QuestionsUpdateDto;
import com.example.demo.entity.Questions;

import com.example.demo.response.Responsee;
import com.example.demo.service.QuestionsService;

/**
 * Question controller class.
 */
@RestController
@CrossOrigin(origins = "*")
public class QuestionsController {
    /**
     * auto wiring question service class.
     */
    @Autowired
    private QuestionsService qs;

    /**
     * Questions add method.
     * @param q question
     * @return response
     */
    @PostMapping("/questions")
    public final ResponseEntity<Object> addQuestion(
            @RequestBody final Questions q) {
        try {
            QuestionsDto user = qs.addQuestion(q);
            return Responsee.generateResponce("succcessfully add the data",
                    HttpStatus.OK, "Questions_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Questions_Information", null);
        }
    }

    /**
     * getting all the questions method.
     * @return response
     */
    @GetMapping("/questions")
    public final ResponseEntity<Object> getQuestions() {
        try {
            List<QuestionsDto> user = qs.getQuestions();
            return Responsee.generateResponce("succcessfully retrive the data",
                    HttpStatus.OK, "Questions_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Questions_Information", null);
        }
    }

    /**
     * delete question method.
     * @param id question id
     * @return response
     */
    @DeleteMapping("/questions/{id}")
    public final ResponseEntity<Object> delete(@PathVariable final int id) {
        try {
            qs.delete(id);
            return Responsee.generateResponce("succcessfully delete the data",
                    HttpStatus.OK, "Questions_Information", null);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Questions_Information", null);
        }
    }

    /**
     * update question method.
     * @param id question id
     * @param q  question
     * @return response
     */
    @PutMapping("/questions/que/{id}")
    public final ResponseEntity<Object> updateQue(
            @RequestBody final QuestionsUpdateDto q,
            @PathVariable final int id) {
        try {
            QuestionsUpdateDto user = qs.updateQue(q, id);
            return Responsee.generateResponce("succcessfully update the data",
                    HttpStatus.OK, "Questions_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Questions_Information", null);
        }
    }

    /**
     * get question by id method.
     * @param id question id
     * @return response
     */
    @GetMapping("/questions/{id}")
    public final ResponseEntity<Object> findQueById(
            @PathVariable final int id) {
        try {
            List<QuestionsDto> user = qs.findQueById(id);
            return Responsee.generateResponce("succcessfully get the data",
                    HttpStatus.OK, "Questions_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Questions_Information", null);
        }
    }

    /**
     * getting question information.
     * @param name question
     * @return response
     */
    @GetMapping("/questions/questionByName/{name}")
    public final ResponseEntity<Object> findByQuestion(
            @PathVariable final String name) {
        try {
            Optional<QuestionsDto> user = qs.findByQuestion(name);
            return Responsee.generateResponce("succcessfully get the data",
                    HttpStatus.OK, "Questions_Information", user);
        } catch (Exception e) {
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Questions_Information", null);
        }
    }
}
