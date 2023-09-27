package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public final ResponseEntity<String> addQuestion(
            @RequestBody @Valid final Questions q) {
            questionsService.addQuestion(q);
            LOGGER.info("Adding a  questions");
            return ResponseEntity.ok("question added successfully");
    }
    /**
     * getting all the questions method.
     * @return response
     */
    @GetMapping("/questions")
    public final ResponseEntity<List<QuestionsDto>> getQuestions() {
            LOGGER.info("Get all questions");
            List<QuestionsDto> questionsDto = questionsService.getQuestions();
            return ResponseEntity.ok(questionsDto);
    }

    /**
     * delete question method.
     * @param id question id
     * @return response
     */
    @DeleteMapping("/questions/{id}")
    public final ResponseEntity<String> delete(@PathVariable final int id) {
            questionsService.delete(id);
            LOGGER.info("Delete question");
            return ResponseEntity.ok("question deleted successfully");
    }

    /**
     * update question method.
     * @param id question id
     * @param q  question
     * @return response
     */
    @PutMapping("/questions/que/{id}")
    public final ResponseEntity<String> updateQue(
            @RequestBody @Valid final QuestionsUpdateDto q,
            @PathVariable final int id) {
            questionsService.updateQue(q, id);
            LOGGER.info("update question");
            return ResponseEntity.ok("question updated successfully");
    }

    /**
     * get question by id method.
     * @param id question id
     * @return response
     */
    @GetMapping("/questions/{id}")
    public final ResponseEntity<List<QuestionsDto>> findQueById(
            @PathVariable final int id) {
            List<QuestionsDto> questionsDto = questionsService.findQueById(id);
            LOGGER.info("Get all questions by quiz id");
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
            LOGGER.info("Get question");
          return ResponseEntity.ok(questionsDto);
    }
}
