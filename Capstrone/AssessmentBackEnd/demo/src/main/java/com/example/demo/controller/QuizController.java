package com.example.demo.controller;

import java.util.List;
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
     * @param quizDto quiz
     * @return response
     */
    @PostMapping("/quiz")
    public final Response saveQuiz(
            @RequestBody @Valid final QuizDto quizDto) {
            quizSevice.addQuiz(quizDto);
            LOGGER.info(Messages.SAVE_QUIZ);
            String message = Messages.SAVE_QUIZ;
            Integer code = HttpStatus.OK.value();
            Response response = new Response(code, message);
       return response;
    }

    /**
     * get quiz method.
     * @param id quiz id
     * @return get quiz response
     */
    @GetMapping("/quiz/id/{id}")
    public final Response getQuiz(
            @PathVariable final int id) {
            QuizDto quizDto = quizSevice.getQuiz(id);
            LOGGER.info(Messages.FIND_QUIZ);
            String message = Messages.FIND_QUIZ;
            Integer code = HttpStatus.OK.value();
            Response response = new Response(code,
                    message, quizDto);
       return response;
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
            Integer code = HttpStatus.OK.value();
            Response response = new Response(code,
                    message, quizDto);
       return response;
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
            Integer code = HttpStatus.OK.value();
            Response response = new Response(code, message);
       return response;
    }

    /**
     * update quiz method.
     * @param quizUpdateDto  quiz
     * @param id quiz id
     * @return response
     */
    @PutMapping("/quiz/{id}")
    public final Response updateQuiz(
            @RequestBody @Valid final QuizUpdateDto quizUpdateDto,
            @PathVariable final int id) {
            quizSevice.updateQuiz(quizUpdateDto, id);
            LOGGER.info(Messages.UPDATE_QUIZ);
            String message = Messages.UPDATE_QUIZ;
            Integer code = HttpStatus.OK.value();
            Response response = new Response(code,
                    message);
       return response;
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
            Integer code = HttpStatus.OK.value();
            Response response = new Response(code,
                    message, quizDto);
       return response;
    }

    /**
     * get quiz by name method.
     * @param name quiz
     * @return response
     */
    @GetMapping("/quiz/quizByName/{name}")
    public final Response findQuizByName(
            @PathVariable final String name) {
            QuizDto quizDto = quizSevice.findQuizByName(name);
            LOGGER.info(Messages.FIND_QUIZ);
            String message = Messages.FIND_QUIZ;
            Integer code = HttpStatus.OK.value();
            Response response = new Response(code,
                    message, quizDto);
       return response;
    }
}
