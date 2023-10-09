package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.QuestionsDto;
import com.example.demo.dto.QuestionsUpdateDto;


/**
 * Question service interface.
 */
public interface QuestionsService {
    /**
     * add question question.
     * @param questionDto question
     * @return question
     */
    QuestionsDto addQuestion(QuestionsDto questionDto);

    /**
     * get question method.
     * @return question
     */
    List<QuestionsDto> getQuestions();

    /**
     * delete question.
     * @param id question id
     */
    void delete(int id);

    /**
     * update question method.
     * @param questionUpdateDto question
     * @param id question id
     * @return question
     */
    QuestionsUpdateDto updateQuestion(QuestionsUpdateDto
            questionUpdateDto, int id);

    /**
     * find by id method.
     * @param id quiz id
     * @return list of question
     */
    List<QuestionsDto> findQuestionById(int id);

    /**
     * find by question method.
     * @param name question
     * @return question
     */
    Optional<QuestionsDto> findByQuestion(String name);
}
