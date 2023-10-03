package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.demo.response.Response;
import com.example.demo.service.QuizService;
import com.example.demo.validationMessages.Messages;

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
    public final Response saveQuiz(
            @RequestBody @Valid final QuizDto q) {
            quizSevice.addQuiz(q);
            LOGGER.info(Messages.SAVE_QUIZ);
            String message = Messages.SAVE_QUIZ;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message);
       return errorResponse;
    }

    /**
     * get quiz method.
     * @param id quiz id
     * @return response
     */
    @GetMapping("/quiz/id/{id}")
    public final Response getQuiz(
            @PathVariable final int id) {
            Optional<QuizDto> quizDto = quizSevice.getQuiz(id);
            LOGGER.info(Messages.FIND_QUIZ);
            String message = Messages.FIND_QUIZ;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode,
                    message, quizDto);
       return errorResponse;
    }

    /**
     * get all quiz method.
     * @return response
     */
    @GetMapping("/quiz")
    public final Response findAll() {
            List<QuizDto> quizDto = quizSevice.findAll();
            LOGGER.info(Messages.FIND_ALLQUIZES);
            String message = Messages.FIND_ALLQUIZES;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode,
                    message, quizDto);
       return errorResponse;
    }

    /**
     * delete quiz method.
     * @param id quiz id
     * @return response
     */
    @DeleteMapping("/quiz/{id}")
    public final Response deleteQuiz(
            @PathVariable final int id) {
            quizSevice.deleteQuiz(id);
            LOGGER.info(Messages.DELETE_QUIZ);
            String message = Messages.DELETE_QUIZ;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode, message);
       return errorResponse;
    }

    /**
     * update quiz method.
     * @param q  quiz
     * @param id quiz id
     * @return response
     */
    @PutMapping("/quiz/{id}")
    public final Response updateQuiz(
            @RequestBody @Valid final QuizUpdateDto q,
            @PathVariable final int id) {
            quizSevice.updateQuiz(q, id);
            LOGGER.info(Messages.UPDATE_QUIZ);
            String message = Messages.UPDATE_QUIZ;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode,
                    message);
       return errorResponse;
    }

    /**
     * get quiz by id method.
     * @param id quiz id
     * @return response
     */
    @GetMapping("quiz/quizz/{id}")
    public final Response findQuizById(
            @PathVariable final int id) {
            List<QuizDto> quizDto = quizSevice.findQuizById(id);
            LOGGER.info(Messages.FIND_QUIZBYCATEGORYID);
            String message = Messages.FIND_QUIZBYCATEGORYID;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode,
                    message, quizDto);
       return errorResponse;
    }

    /**
     * get quiz by name method.
     * @param name quiz
     * @return response
     */
    @GetMapping("/quiz/quizByName/{name}")
    public final Response findQuizByName(
            @PathVariable final String name) {
            Optional<QuizDto> quizDto = quizSevice.findQuizByName(name);
            LOGGER.info(Messages.FIND_QUIZ);
            String message = Messages.FIND_QUIZ;
            Integer errorCode = HttpStatus.OK.value();
            Response errorResponse = new Response(errorCode,
                    message, quizDto);
       return errorResponse;
    }
}
