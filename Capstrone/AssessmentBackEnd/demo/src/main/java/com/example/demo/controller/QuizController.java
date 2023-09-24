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

import com.example.demo.dto.QuizDto;
import com.example.demo.dto.QuizUpdateDto;
import com.example.demo.entity.Quiz;
import com.example.demo.response.Responsee;
import com.example.demo.service.QuizService;

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
    public final ResponseEntity<Object> saveQuiz(@RequestBody final Quiz q) {
        try {
            QuizDto quizDto = quizSevice.addQuiz(q);
            LOGGER.info("Adding Quiz");
            return Responsee.generateResponce("succcessfully add the data",
                    HttpStatus.OK, "Quiz Topic_Information", quizDto);
        } catch (Exception e) {
            LOGGER.info("Exception occur");
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Quiz Topic_Information", null);
        }
    }

    /**
     * get quiz method.
     * @param id quiz id
     * @return response
     */
    @GetMapping("/quiz/id/{id}")
    public final ResponseEntity<Object> getQuiz(@PathVariable final int id) {
        try {
            Optional<QuizDto> quizDto = quizSevice.getQuiz(id);
            LOGGER.info("get quiz by quiz id");
            return Responsee.generateResponce("succcessfully retrive the data",
                    HttpStatus.OK, "Quiz_topic_Information", quizDto);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "QuizTopic_Information", null);
        }
    }

    /**
     * get all quiz method.
     * @return response
     */
    @GetMapping("/quiz")
    public final ResponseEntity<Object> findAll() {
        try {
            List<QuizDto> quizDto = quizSevice.findAll();
            LOGGER.info("Find all quizes");
            return Responsee.generateResponce("succcessfully retrive the data",
                    HttpStatus.OK, "Quiz_Information", quizDto);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Quiz_Information", null);
        }
    }

    /**
     * delete quiz method.
     * @param id quiz id
     * @return response
     */
    @DeleteMapping("/quiz/{id}")
    public final ResponseEntity<Object> deleteQuiz(@PathVariable final int id) {
        try {
            quizSevice.deleteQuiz(id);
            LOGGER.info("Delete quiz");
            return Responsee.generateResponce("succcessfully delete the data",
                    HttpStatus.OK, "Quiz_Information", null);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Quiz_Information", null);
        }
    }

    /**
     * update quiz method.
     * @param q  quiz
     * @param id quiz id
     * @return response
     */
    @PutMapping("/quiz/{id}")
    public final ResponseEntity<Object> updateQuiz(
            @RequestBody final QuizUpdateDto q,
            @PathVariable final int id) {
        try {
            QuizUpdateDto quizDto = quizSevice.updateQuiz(q, id);
            LOGGER.info("Update the quiz");
            return Responsee.generateResponce("succcessfully update the data",
                    HttpStatus.OK, "Quiz_Information", quizDto);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Quiz_Information", null);
        }
    }

    /**
     * get quiz by id method.
     * @param id quiz id
     * @return response
     */
    @GetMapping("quiz/quizz/{id}")
    public final ResponseEntity<Object> findQuizById(
            @PathVariable final int id) {
        try {
            List<QuizDto> quizDto = quizSevice.findQuizById(id);
            LOGGER.info("Fina quiz by categoryID");
            return Responsee.generateResponce("succcessfully retrive the data",
                    HttpStatus.OK, "Quiz_Information", quizDto);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Quiz_Information", null);
        }
    }

    /**
     * get quiz by name method.
     * @param name quiz
     * @return response
     */
    @GetMapping("/quiz/quizByName/{name}")
    public final ResponseEntity<Object> findQuizByName(
            @PathVariable final String name) {
        try {
            Optional<QuizDto> quizDto = quizSevice.findQuizByName(name);
            LOGGER.info("Find quiz by name");
            return Responsee.generateResponce("succcessfully update the data",
                    HttpStatus.OK, "Quiz_Information", quizDto);
        } catch (Exception e) {
            LOGGER.error("Exception occur");
            return Responsee.generateResponce(e.getMessage(),
                    HttpStatus.MULTI_STATUS, "Quiz_Information", null);
        }
    }
}
