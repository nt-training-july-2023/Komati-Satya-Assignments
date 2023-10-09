package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Quiz;

/**
 * repository for quiz.
 */
@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {
    /**
     * query for quiz.
     * @param id category id
     * @return quiz
     */
    @Query("select q from Quiz q where q.category.categoryId=:id")
    List<Quiz> findQuizById(int id);

    /**
     * query for quiz.
     * @param name topic name
     * @return quiz
     */
    @Query("select q from Quiz q where q.topicName=:name")
    Optional<Quiz> findQuizByName(String name);
}
