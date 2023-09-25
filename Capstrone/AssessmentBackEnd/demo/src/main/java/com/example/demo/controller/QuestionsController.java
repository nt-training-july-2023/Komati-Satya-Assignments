package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import jakarta.validation.Valid;

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
    private QuestionsService questionsService;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(QuestionsController.class);

    /**
     * Questions add method.
     * @param q question
     * @return response
     */
    @PostMapping("/questions")
    public final ResponseEntity<Object> addQuestion(
            @RequestBody @Valid final Questions q) {
        try {
            QuestionsDto questionsDto = questionsService.addQuestion(q);
            LOGGER.info("Adding a  questions");
            return Responsee.generateResponce("succcessfully add the data",
                    HttpStatus.OK, "Questions_Information", questionsDto);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
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
            LOGGER.info("Get all questions");
            List<QuestionsDto> questionsDto = questionsService.getQuestions();
            return Responsee.generateResponce("succcessfully retrive the data",
                    HttpStatus.OK, "Questions_Information", questionsDto);
        } catch (Exception e) {
            LOGGER.error("Exceptions occur");
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
            questionsService.delete(id);
            LOGGER.info("Delete question");
            return Responsee.generateResponce("succcessfully delete the data",
                    HttpStatus.OK, "Questions_Information", null);
        } catch (Exception e) {
            LOGGER.error("Exception occcur");
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
            @RequestBody @Valid final QuestionsUpdateDto q,
            @PathVariable final int id) {
        try {
            QuestionsUpdateDto questionsDto = questionsService.updateQue(q, id);
            LOGGER.info("update question");
            return Responsee.generateResponce("succcessfully update the data",
                    HttpStatus.OK, "Questions_Information", questionsDto);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
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
            List<QuestionsDto> questionsDto = questionsService.findQueById(id);
            LOGGER.info("Get all questions by quiz id");
            return Responsee.generateResponce("succcessfully get the data",
                    HttpStatus.OK, "Questions_Information", questionsDto);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
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
            Optional<QuestionsDto> questionsDto = questionsService.
                    findByQuestion(name);
            LOGGER.info("Get question");
            return Responsee.generateResponce("succcessfully get the data",
                    HttpStatus.OK, "Questions_Information", questionsDto);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Questions_Information", null);
        }
    }
}
