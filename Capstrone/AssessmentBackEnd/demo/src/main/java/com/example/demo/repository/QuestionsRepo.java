package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Questions;

/**
 * repository for question.
 */
@Repository
public interface QuestionsRepo extends JpaRepository<Questions, Integer> {
    /**
     * query for questions.
     * @param id quiz id
     * @return questions
     */
    @Query("select q from Questions q where q.quiz.quizId=:id")
    List<Questions> findQueById(int id);

    /**
     * query for question.
     * @param question question name
     * @return questions
     */
    @Query("select q from from Questions q where q.question=:question")
    Optional<Questions> findByQuestion(String question);
}
