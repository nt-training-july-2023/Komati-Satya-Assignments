package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.QuizDto;
import com.example.demo.dto.QuizUpdateDto;


/**
 * quiz service interface.
 */
public interface QuizService {
    /**
     * add quiz method.
     * @param quizDto quiz
     * @return quiz
     */
    QuizDto addQuiz(QuizDto quizDto);

    /**
     * get quiz method.
     * @param id quiz id
     * @return quiz
     */
    Optional<QuizDto> getQuiz(int id);

    /**
     * find all method.
     * @return quiz
     */
    List<QuizDto> findAll();

    /**
     * delete quiz method.
     * @param id quiz id
     */
    void deleteQuiz(int id);

    /**
     * update quiz method.
     * @param quizUpdateDto  quiz
     * @param id quiz id
     * @return quiz
     */
    QuizUpdateDto updateQuiz(QuizUpdateDto
            quizUpdateDto, int id);

    /**
     * find by id method.
     * @param id quiz id
     * @return quiz
     */
    List<QuizDto> findQuizById(int id);

    /**
     * find quiz by name method.
     * @param name category name
     * @return quiz
     */
    Optional<QuizDto> findQuizByName(String name);
}
