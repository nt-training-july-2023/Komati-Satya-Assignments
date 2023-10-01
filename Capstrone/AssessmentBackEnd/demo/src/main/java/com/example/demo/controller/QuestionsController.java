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
import com.example.demo.response.Response;
import com.example.demo.service.QuestionsService;
import com.example.demo.validationMessages.Messages;

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
    public final ResponseEntity<Response> addQuestion(
            @RequestBody @Valid final QuestionsDto q) {
            questionsService.addQuestion(q);
            LOGGER.info(Messages.SAVE_QUESTION);
            String message = Messages.SAVE_QUESTION;
            Integer errorCode = HttpStatus.CREATED.value();
            Response errorResponse = new Response(errorCode, message);
       return new ResponseEntity<Response>(errorResponse,
              HttpStatus.CREATED);
    }
    /**
     * getting all the questions method.
     * @return response
     */
    @GetMapping("/questions")
    public final ResponseEntity<List<QuestionsDto>> getQuestions() {
            LOGGER.info(Messages.FIND_ALLQUESTION);
            List<QuestionsDto> questionsDto = questionsService.getQuestions();
            return ResponseEntity.ok(questionsDto);
    }

    /**
     * delete question method.
     * @param id question id
     * @return response
     */
    @DeleteMapping("/questions/{id}")
    public final ResponseEntity<Response> delete(@PathVariable final int id) {
            questionsService.delete(id);
            LOGGER.info(Messages.DELETE_QUESTION);
            String message = Messages.DELETE_QUESTION;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message);
       return new ResponseEntity<Response>(errorResponse,
              HttpStatus.OK);
    }

    /**
     * update question method.
     * @param id question id
     * @param q  question
     * @return response
     */
    @PutMapping("/questions/que/{id}")
    public final ResponseEntity<Response> updateQue(
            @RequestBody @Valid final QuestionsUpdateDto q,
            @PathVariable final int id) {
            questionsService.updateQuestion(q, id);
            LOGGER.info(Messages.UPDATE_QUESTION);
            String message = Messages.UPDATE_QUESTION;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message);
       return new ResponseEntity<Response>(errorResponse,
              HttpStatus.OK);
    }

    /**
     * get question by id method.
     * @param id question id
     * @return response
     */
    @GetMapping("/questions/{id}")
    public final ResponseEntity<List<QuestionsDto>> findQueById(
            @PathVariable final int id) {
            List<QuestionsDto> questionsDto = questionsService.
                    findQuestionById(id);
            LOGGER.info(Messages.FIND_QUESTIONBYQUIZID);
            return ResponseEntity.ok(questionsDto);
    }

    /**
     * getting question information.
     * @param name question
     * @return response
     */
    @GetMapping("/questions/questionByName/{name}")
    public final ResponseEntity<Optional<QuestionsDto>> findByQuestion(
            @PathVariable final String name) {
            Optional<QuestionsDto> questionsDto = questionsService.
                    findByQuestion(name);
            LOGGER.info(Messages.FIND_QUESTION);
          return ResponseEntity.ok(questionsDto);
    }
}
