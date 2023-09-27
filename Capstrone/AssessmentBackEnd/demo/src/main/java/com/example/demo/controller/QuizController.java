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

import com.example.demo.dto.QuizDto;
import com.example.demo.dto.QuizUpdateDto;
import com.example.demo.entity.Quiz;
import com.example.demo.service.QuizService;

import jakarta.validation.Valid;

/**
 * Quiz controller class.
 */
@CrossOrigin(origins = "*")
@RestController
public class QuizController {
    /**
     * Auto wiring quiz service class.
     */
    @Autowired
    private QuizService quizSevice;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(QuizController.class);
    /**
     * save quiz method.
     * @param q quiz
     * @return response
     */
    @PostMapping("/quiz")
    public final ResponseEntity<String> saveQuiz(
            @RequestBody @Valid final Quiz q) {
            quizSevice.addQuiz(q);
            LOGGER.info("Adding Quiz");
            return ResponseEntity.ok("succcessfully add the data");
    }

    /**
     * get quiz method.
     * @param id quiz id
     * @return response
     */
    @GetMapping("/quiz/id/{id}")
    public final ResponseEntity<Optional<QuizDto>> getQuiz(
            @PathVariable final int id) {
            Optional<QuizDto> quizDto = quizSevice.getQuiz(id);
            LOGGER.info("get quiz by quiz id");
            return ResponseEntity.ok(quizDto);
    }

    /**
     * get all quiz method.
     * @return response
     */
    @GetMapping("/quiz")
    public final ResponseEntity<List<QuizDto>> findAll() {
            List<QuizDto> quizDto = quizSevice.findAll();
            LOGGER.info("Find all quizes");
            return ResponseEntity.ok(quizDto);
    }

    /**
     * delete quiz method.
     * @param id quiz id
     * @return response
     */
    @DeleteMapping("/quiz/{id}")
    public final ResponseEntity<String> deleteQuiz(
            @PathVariable final int id) {
            quizSevice.deleteQuiz(id);
            LOGGER.info("Delete quiz");
            return ResponseEntity.ok("quiz deleted successfully");
    }

    /**
     * update quiz method.
     * @param q  quiz
     * @param id quiz id
     * @return response
     */
    @PutMapping("/quiz/{id}")
    public final ResponseEntity<String> updateQuiz(
            @RequestBody @Valid final QuizUpdateDto q,
            @PathVariable final int id) {
            quizSevice.updateQuiz(q, id);
            LOGGER.info("Update the quiz");
            return ResponseEntity.ok("quiz updated successfully");
    }

    /**
     * get quiz by id method.
     * @param id quiz id
     * @return response
     */
    @GetMapping("quiz/quizz/{id}")
    public final ResponseEntity<List<QuizDto>> findQuizById(
            @PathVariable final int id) {
            List<QuizDto> quizDto = quizSevice.findQuizById(id);
            LOGGER.info("Fina quiz by categoryID");
            return ResponseEntity.ok(quizDto);
    }

    /**
     * get quiz by name method.
     * @param name quiz
     * @return response
     */
    @GetMapping("/quiz/quizByName/{name}")
    public final ResponseEntity<Optional<QuizDto>> findQuizByName(
            @PathVariable final String name) {
            Optional<QuizDto> quizDto = quizSevice.findQuizByName(name);
            LOGGER.info("Find quiz by name");
            return ResponseEntity.ok(quizDto);
    }
}
